# Scapegoat Tree  

“In computer science, a scapegoat tree is a self-balancing binary search tree … It provides
worst-case O(log n) lookup time, and O(log n) amortized insertion and deletion time. Unlike
other self-balancing binary search trees that provide worst case O(log n) lookup time, 
scapegoat trees have no additional per-node overhead compared to a regular binary search 
tree. This makes scapegoat trees easier to implement and, due to data structure alignment, 
can reduce node overhead by up to one-third.”

## How To Build
1) Boot macOSX partition in WSUV labs
2) Load Eclipse
3) Open Project
```
File > Import > General > Exsisting Project into Workspace > Select Archive file
```
4) Select Browse. Find the archive file SGT.zip
5) Select Finish. Click play to run code

## Test Cases
To use provided test cases, simply type: <br><br>
Test: Small Input Tree 
```
treeSmall.txt       
```
Test: Big Tree (provided by assignment)
```
tree.txt            
```
Test: Medium Input Tree
```
treeMed.txt
```
## Archive Files
Explain how to run the automated tests for this system
```
/ScapeGoat                - root folder
    /src                  - Holds all source code
        /Node.Java        - Class structure for nodes of tree
        /ScapeGoat.java   - Main Class for ScapeGoat. Parses input and builds tree
        /SGtree.java      - Class for scapegoat tree's
        /tree.txt         - Big input test tree
        /treeMed.txt      - Medium input test tree
        /treeSmall.txt    - Small input test tree
    /README.md            - README for project   
```

### Built With
* Eclipse
* Java

## Authors
* **Spencer Kitchen** - sckitchen.dev@gmail.com - spencer.kitchen@wsu.edu

### Acknowledgments
* [Scapegoat Trees: the original publication describing scapegoat trees](http://cglab.ca/~morin/teaching/5408/refs/gr93.pdf)
* [Scapegoat tree Wiki](https://en.wikipedia.org/wiki/Scapegoat_tree)


