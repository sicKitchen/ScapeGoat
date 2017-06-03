import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;




public class ScapeGoat {
	// General debug
	private static boolean DEBUG = false;
	// statements related to parsing the file input
	private static boolean DEBUG_PARSER = false;
	// Statements related to Insert into tree function
	private static boolean DEBUG_INSERT = true;
	// Statements related to Delete from tree function
	private static boolean DEBUG_DELETE = true;
	// Prints tree after every insert
	private static boolean DEBUG_PRINT = true;


    static SGtree tree;
    
    public static String[] parseLine(String line){
    	String token[] = null;
    	token = line.split("\\s+");
    	if (DEBUG_PARSER) System.out.println("parsed input: " + Arrays.toString(token));
    	return token;
    }
	
	
	public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
		// Get the RSC file		
		
        File file;
        Scanner scanner;          // initiate scanner		
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter File to read : ");
        URL location = ClassLoader.getSystemResource(input.nextLine());
        
        // We cant find the input file
        if (location == null){
        	System.out.println("Sorry your file doesnt exist! ");
        }else {
	        file = new File(location.toURI());	// Get file & open scanner
	        scanner = new Scanner(file);
	
	        // Do this for every line of scanner
	        while (scanner.hasNextLine()){
	        	String array[];
	            String line = scanner.nextLine();
	            
	            // DEBUG: print what scanner got as input
	            if (DEBUG) System.out.println("Line Input: " + line);       
	            array = parseLine(line);
	            // DEBUG: Print out parsed Line
	            if (DEBUG_PARSER) {
	            	System.out.print("array: ");
	            	for (int i = 0; i < array.length; i++ ){
	            		System.out.print(array[i] + " ");
	            	}
	            	System.out.println("");
	            }
	            	
	            String cmd = array[0];
	            switch (cmd){
	            // CHECK WHAT WE READ FROM LINE OF FILE
	            case "BuildTree":
	            	// Get the float value and create tree
	            	float alpha = Float.parseFloat(array[1]);
	            	tree = new SGtree(alpha);
	            	tree.insert(Integer.parseInt(array[2]));
	    			break;
	            
	            case "Insert":
	            	if (DEBUG_INSERT) System.out.println("INSERT TO TREE: "+ array[1]);
	            	tree.insert(Integer.parseInt(array[1]));
	            	break;
	            	
	            case "Search":
	            	if(tree.search(Integer.parseInt(array[1])))
	            		System.out.print(array[1]+" is IN the Tree. \n");
	            	else 
	            		System.out.print(array[1]+" is NOT in the Tree. \n");
	            	break;
	    			
	            case "Delete":
	            	if (DEBUG_DELETE) System.out.println("DELETE FROM TREE: "+ array[1]);
	            	tree.delete(Integer.parseInt(array[1]));
	            	break;
	            	
	            case "Done":
	            	System.out.println("End of input, Closing...");
	            	System.exit(0);
	            	
	            default:
	    			System.out.println("INVALID COMMAND");
	            }
	            
	            
	            // DEBUG: print the tree
	            if (DEBUG_PRINT){
	            	System.out.println("PRINTING TREE");
	            	System.out.println("--------------------------------------------------------");
	            	tree.printTree();
	            	System.out.println("--------------------------------------------------------");
	            	System.out.println();
	            }
	
	        }
	        
	        scanner.close();
	        
        }
        input.close();
        System.out.println("Please Try Again, Closing...");
        System.exit(1);
	}

}
