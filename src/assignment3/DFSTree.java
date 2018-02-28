package assignment3;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.*;

public class DFSTree {
    public PriorityQueue<nodeDFS> tree = new PriorityQueue<>();
    public static Set<String> dictionary;
    public static String end;
    public ArrayList<String> ladder = new ArrayList<String>();
    private static int flag = 0;

    public DFSTree(String start, String end, Set<String> dictionary){
        this.dictionary = dictionary;
        this.end = end;
    }

    private void createTree(nodeDFS node){
        if(node.currentWord.equals(end.toUpperCase())){
            flag = 1;
            while(true){
                if(node != null) {
                    ladder.add(node.currentWord.toLowerCase());
                    node = node.parentNode;
                }
                else{
                    Collections.reverse(ladder);
                    break;
                }
            }
            return;
        }
        node.findChildren(node);

        if(node.childrenArray.size()==0){
            //System.out.println("No Children");
        }else {
            for (int i = 0; i < node.childrenArray.size(); i = i + 1) {
                createTree(node.childrenArray.get(i));
            }
        }
    }

    public ArrayList<String> startTree(String start) {
        nodeDFS root = new nodeDFS(start, end,null, dictionary);
        tree.add(root);
        createTree(root);
        if (flag == 0) {
            ladder.add(start);
            ladder.add(end);
            return ladder;
        } else {
            return ladder;
        }
    }
}
