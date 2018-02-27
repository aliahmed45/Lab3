import java.util.PriorityQueue;
import java.util.Set;

public class DFSTree {
    public PriorityQueue<nodeDFS> tree = new PriorityQueue<>();
    public Set<String> dictionary;
    public String end;

    public DFSTree(){
        System.out.println("Creating tree structure.");
    }
    public DFSTree(String start, String end, Set<String> dictionary){
        this.dictionary = dictionary;
        this.end = end;
        nodeDFS root = new nodeDFS(start);
        root.parentNode = null;
        tree.add(root);
        createTree(root);
    }

    private void createTree(nodeDFS node){
        if(node.currentWord.equals(end)){
            //figure out what to do at this point
            // add word to THE list probably
            return;
        }
        node.findChildren(node);

    }

}
