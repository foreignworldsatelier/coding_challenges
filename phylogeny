package task3;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
/*Notes:
    -Create new root by creating node with null parent
        -Expect new root creation
    -Create node with assigned parent
        -Label of child should match label variable. Parent will be assigned but relationship not complete
    -Add children to parent
        -Now child-parent are fully relational
        -Lambda expression to print each value for each parent should give assigned nodes
    -Add new root
        -Should replace old root
    -Add new branch on new root
        -Should not be connected to old tree
    -Adding a new root requires manual assignment of children
    -Add root above 2 separate trees
        -Trees should now be connected
    -Adding a new root should add the root to the roots list.
    -Adding a root to an existing root should remove the root from the roots list
    -Traversal: Print each element top to bottom
        -Start with Root and print each child
        -Move down a level and print children for each parent
        -repeat
        -Move to next root and repeat, starting a new counter per tree
    -Identify most populated level on contiguoues tree
        -Maintain depth counter (public hashmap?)
        -With each traversal keep a count of children
        -Add counts per level
        -Return highest number
    -Reconsider oragnization - currently it looks at node/parent relationship and determines if it's root. To complete assigment
        it may be necessary to focus on node/child relationship and have the built in parent checker determine roots
    -Test that it works with multiple roots
    -Nodes should be added to arraylist for easy storage and retrieval
    -Create hashmap for inputs and one for nodes to store values
TESTS
    - Add new node with null parent
        - Expectation: Included in roots list
    - Add higher level node, tie previous roots as children
        - Expectation: Roots list contains only new root and untied roots
        - Example: Starting roots = Animal, Plant, Fungus. Add Eukaryota root and add {Plant,  Animal} as children. Roots list should now show {Eukaryota, Fungus}
    - Creating a duplicate variable with a different parent results in an error, one parent per node
    - Add mid-level node ie "Feline" after creating "Cat" and "Mammal". This should add "Feline" to the tree structure as parent to "Cat" and child to "Mammal"
*/
public class Phylogeny {
    private static List<Node> roots = new ArrayList<Node>();
    private static ArrayList<Node> nodes = new ArrayList<Node>();
    private static ArrayList<String> nodeLabels = new ArrayList<String>();
    private static HashMap<Integer,List<Node>> levelCount = new HashMap<Integer,List<Node>>();
    private static Integer level =1;
    private static int i =0;
    
    static class Node {
        String label;
        List<Node> children = new ArrayList<Node>();
        Node parent;
        
        public Node(String label,String parent) {
            this.label = label;
            if(parent!=null){
                
                if(nodeLabels.contains(this.label) && !nodeLabels.contains(parent)){
                    for (int i = 0; i<nodes.size();i++) {
                        if(nodes.get(i).label.equals(this.label)) {
                            nodes.get(i).parent = new Node(parent,null);
                        }
                    }
                }
                else if(
                    !nodeLabels.contains(this.label) && 
                    nodeLabels.contains(parent)){
                    nodeLabels.add(this.label);
                    for (int i = 0; i<nodes.size();i++) {
                        if(nodes.get(i).label.equals(parent)) {
                            nodes.get(i).addChild(this);
                            this.parent=nodes.get(i);
                            break;
                        }
                    }
                }
                else if(nodeLabels.contains(this.label)&& nodeLabels.contains(parent)) {
                    for (int i = 0; i<nodes.size();i++) {
                        if(nodes.get(i).label.equals(parent)) {
                            for (int j = 0; j<nodes.size();j++) {
                                if(nodes.get(j).label.equals(this.label)) {
                                         
                                    nodes.get(i).addChild(nodes.get(j));
                                    nodes.get(j).parent = nodes.get(i);
                                }
                            }
                        }
                    }
                }
                else{this.parent = new Node(parent,null);}
                
            if(!nodeLabels.contains(this.label)) {
                nodeLabels.add(this.label);
                this.parent.addChild(this);
                }
                else {
                    for (int i = 0; i<nodes.size();i++) {
                   
                    if(nodes.get(i).label.equals(this.label)) {
                        nodes.get(i).parent.addChild(nodes.get(i));
                        break;
                    }
                }
                }
            }
           else if(parent == null 
            && !roots.contains(this)) {
            roots.add(this);
            nodeLabels.add(this.label);
            nodes.add(this);
            //Prune any children of the new root from the roots
            this.children.forEach((e)-> {
                System.out.println(e);
                if(roots.contains(e))
                    {roots.remove(e);}});
            }   
        }
            
        
        public void addChild(Node newChild) {
                this.children.add(newChild);   
                //Prune children from the roots as new children are connected
                if(this.parent == null && !roots.contains(this)) 
                    roots.add(this);
                this.children.forEach((e)-> {
                if(roots.contains(e))
                {roots.remove(e);}});
                }
        }
    public void busyLayer(HashMap<Integer,List<Node>> levelCount){
        int max = 0,busiestLayer=0;
        List<String> list = new ArrayList<>();
        for(int i =0;i<levelCount.size();i++)   {
          
           if(levelCount.get(i+1).size() > max) {
            max =levelCount.get(i+1).size();
            busiestLayer = i+1;
           }
            }
        System.out.println("Busiest Layer = "+busiestLayer);
        levelCount.get(busiestLayer).forEach((e)->{list.add(e.label);});
        Collections.sort(list);
        System.out.println(list);
        
    }
    public void addtoMap(Integer key,Node item) {
        if(!levelCount.containsKey(key)){
            levelCount.put(key,new ArrayList<Node>());
        }
        if(!levelCount.get(key).contains(item)){
            levelCount.get(key).add(item);
        }
    }
    
    //Traverse from roots down, adding children to the level hashmap
    public void traverse(List<Node> roots) {
        for(Node e: roots) {
           addtoMap(level, e);
            for(Node c:e.children) {
                level++;
                traverse(e.children);
                level --;   
            }
        }
    }
        
public static void main(String[] args) {
    Phylogeny phyloTree = new Phylogeny();
    String[][] inputs= new String[20][2];
    String parent, child;
    Node newNode;
    nodes = new ArrayList<Node>();
    inputs[0][0]="Canine";
    inputs[0][1]="Mammal";
    inputs[1][0]="Mammal";
    inputs[1][1]="Animal";
    inputs[2][0]="Feline";
    inputs[2][1]="Mammal";
    inputs[3][0]="Monotreme";
    inputs[3][1]="Mammal";
    inputs[4][0]="Tree";
    inputs[4][1]="Plant";
    inputs[5][0]="Dog";
    inputs[5][1]="Canine";
    inputs[6][0]="Wolf";
    inputs[6][1]="Canine";
    inputs[7][0]="Pomeranian";
    inputs[7][1]="Dog";
    inputs[8][0]="St Bernard";
    inputs[8][1]="Dog";
    inputs[9][0]="Poodle";
    inputs[9][1]="Dog";
    inputs[10][0]="Iguana";
    inputs[10][1]="Reptile";
    inputs[11][0]="Crocodile";
    inputs[11][1]="Reptile";
    inputs[12][0]="Oak";
    inputs[12][1]="Tree";
    inputs[13][0]="Fern";
    inputs[13][1]="Plant";
    inputs[14][0]="Persian";
    inputs[14][1]="Cat";
    inputs[15][0]="Lion";
    inputs[15][1]="Feline";
    inputs[16][0]="Jaguar";
    inputs[16][1]="Feline";
    inputs[17][0]="Fungus";
    inputs[18][0]="Cat";
    inputs[18][1]="Feline";
    inputs[19][0]="Reptile";
    inputs[19][1]="Animal";   
    for(i = 0;i<inputs.length;i++){
        parent = inputs[i][1];
        child = inputs[i][0];
        if(nodeLabels.contains(child)) {
        newNode = new Node(inputs[i][0],inputs[i][1]);}
        else {newNode = new Node(inputs[i][0],inputs[i][1]);
        
        nodes.add(newNode);
        }
    }
    phyloTree.traverse(roots);
    for(int i =0;i<levelCount.size();i++)   {
    System.out.print(i+1+": ");
    for(int j = 0;j<levelCount.get(i+1).size();j++){
    System.out.print(levelCount.get(i+1).get(j).label+", ");
    }
    System.out.println();
    }
    phyloTree.busyLayer(levelCount);
    
}
}
