
package newpackage;

import java.util.Comparator;


public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node n, Node n1) {
        return n.getHeuristic_value() -n1.getHeuristic_value();
        
    }
    
}
