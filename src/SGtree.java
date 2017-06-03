
public class SGtree {
	
	float alpha;        // Holds alpha passed in at first line
    int size;
    int maxSize;
    Node root = null ;

    // Build tree with alpha value passed in
    public SGtree(float a){
        alpha = a;
        size = 0; maxSize = 0;
        root = null;
    }

    // FIND SIZE OF TREE FROM SUBROOT =========================================================
    public int sizeOf(Node node){
        if (node == null) return 0;
        else return 1 + sizeOf(node.right) + sizeOf(node.left);
    }
    
    // SEARCH TREE ============================================================================
    public boolean search(int key){
    	Node node = root;
    	while (node != null){
    		if(node.key > key) node = node.left;
    		else if (node.key < key) node = node.right;
    		// Must be equal to key
    		else return true;
    	}
    	// Searched whole tree, not here
    	return false;
    }
    
    // INSERT INTO TREE ========================================================================
    // Creates new node with key and inserts into the tree. 
    // After check if tree needs to be rebuilt
    public void insert(int key){
        Node node = new Node(key);
        // add to tree, function returns what depth Node was Inserted
        int depth = insertToTree(node);
    	
    	// Check if we need to rebuild
    	if (isDeep(depth) ){
    		System.out.println("tree is Deep enough, rebuilding......................................");
    		Node scapegoat = node.parent;
    		// Climb the tree until we reach a scapegoat that is not violating the alpha height
    		while (sizeOf(scapegoat) <= alpha*sizeOf(scapegoat.parent)){
    			//System.out.println(sizeOf(scapegoat));
    			//System.out.println(sizeOf(scapegoat.parent));
                scapegoat = scapegoat.parent;
    		}
    		rebuild(scapegoat.parent);
    	}
    }
    
    // Inserts NODE Into tree, returns the depth of insert
    public int insertToTree(Node node) {
        Node tmp_root = root;
        
        // If root is null then new Node becomes new root
        if (tmp_root == null) {
            root = node;
            size++; 		// we added a node
            maxSize++;
            return 0;		// Root node is on level 0
        }
        // Already has root node
        boolean stop = false;   // throw flag to stop
        int depth = 0;		// depth of insert
        
        while(stop == false) {
        	if (node.key < tmp_root.key) {
                if (tmp_root.left == null) {
                    tmp_root.left = node;
                    node.parent = tmp_root;
                    stop = true;
                } 
                else tmp_root = tmp_root.left;
            } 
            else if (node.key > tmp_root.key) {
                if (tmp_root.right == null) {
                    tmp_root.right = node;
                    node.parent = tmp_root;
                    stop = true;
                }
                tmp_root = tmp_root.right;
            } 
        	// next level, increase depth
            depth++;
        }
        size++; 
        maxSize++;
        return depth;
    }
    
    //DELETE NODE FROM TREE =====================================================================
    public void delete(int delKey){
    	Node node = root;
    	Node parent = null;
    	Node delete = null;
    	boolean isLeft = true;
    	Node successor = null;
    	int depth = 0;
    	
    	if (node == null) {
            return;
        }
    	
    	while (node.key != delKey){
            parent = node;
            if (delKey > node.key){
                node = node.right;
                isLeft = false;
            }
            else {
                node = node.left;
                isLeft = true;
            }    
            depth++;
    	}
    	
    	// case 1: Node to be delete has no children
        if (node.left == null && node.right == null){
            //node = null;
        }
        // case 2: Node has only a right child
        else if (node.left == null){
            successor = node.right;
        }
        // case 3: Node has only a left child
        else if (node.right == null){
            successor = node.left;
        }
        else {
        	// find successor
            successor = minimumKey(node.right);
            // the successor is the node's right child 
            if (successor == node.right){
                successor.left = node.left;
            }
            // complicated case
            else {
                successor.left = node.left;
                delete = successor.right;
                successor.right = node.right;
                node.right.left = delete;
            }
        }
        // Replace the node
        if (parent == null)
            root = successor;
        else if (isLeft){
        	node.parent.left = successor;
            parent.left = successor;
        }
        else {
            parent.right = successor;
            node.parent.right = successor;
        }
        
        size -= 1;
        //root.parent = null;
        if (isDeep(depth) && depth != 0){
    		System.out.println("tree is Deep enough, rebuilding......................................");
    		Node scapegoat = node.parent;
    		// Climb the tree until we reach a scapegoat that is not violating the alpha height
    		while (sizeOf(scapegoat) <= alpha*sizeOf(scapegoat.parent)){
    			//System.out.println(sizeOf(scapegoat));
    			//System.out.println(sizeOf(scapegoat.parent));
                scapegoat = scapegoat.parent;
    		}
    		rebuild(scapegoat.parent);
    	}
    }
    
    // Get the minimum key from Node passed in
    public Node minimumKey(Node node){
    	while (node.left != null) node = node.left;
    	return node;
    }
    
    // CHECK IF TREE HAS EXCEDED ALPHA WEIGHT ===================================================
    // Check if tree is deep enough
    public boolean isDeep(int depth){
    	return depth > alphaHight();
    }
    // Calculates alpha Height
    public float alphaHight(){
    	return (float) (Math.log(size) / Math.log(1/alpha));
    }
    
    // REBUILD TREE AT SCAPEGOAT ================================================================
    public void rebuild(Node node) {
        int size = sizeOf(node);
        Node parent = node.parent;
        Node[] nodeArray = new Node[size];
        createNodeArray(node, nodeArray, 0);
        if (parent == null) {
            root = buildTreeFromArray(nodeArray, 0, size);
            root.parent = null;
        } 
        else if (parent.right == node) {
            parent.right = buildTreeFromArray(nodeArray, 0, size);
            parent.right.parent = parent;
        } 
        else {
            parent.left = buildTreeFromArray(nodeArray, 0, size);
            parent.left.parent = parent;
        }
    }
    
    // Create list of nodes in order from tree. save in passed input array
    public int createNodeArray(Node root, Node[] array, int size) {
        if (root == null) return size;
        size = createNodeArray(root.left, array, size);
        array[size++] = root;
        return createNodeArray(root.right, array, size);
    }
    
    // Using created Node array, rebuild the tree balanced
    public Node buildTreeFromArray(Node[] array, int start, int end) {
        if (end == 0)
            return null;
        int mid = end / 2;
        array[start + mid].left = buildTreeFromArray(array, start, mid);
        if (array[start + mid].left != null)
            array[start + mid].left.parent = array[start + mid];
        array[start + mid].right = buildTreeFromArray(array, start + mid + 1, end - mid - 1);
        if (array[start + mid].right != null)
            array[start + mid].right.parent = array[start + mid];
        return array[start + mid];
    }
    
    // PRINT STATEMENTS =============================================================

    // INORDER
    public void inOrder(Node node, int level){
    	if(node != null){
    		inOrder(node.left, level + 1);
    		// Print a tab on a new line * how many levels
    		for (int i = 0; i< level; i++) System.out.print('\t');
    		System.out.println(node.key);
    		inOrder(node.right,level+1);
    	}
    }
    
    // PRINT
    public void printTree(){
    	inOrder(root, 0);
    }
    
}
