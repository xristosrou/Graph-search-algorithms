
package newpackage;

import java.util.Comparator;

public class PathComparable implements Comparator<Path> {

    @Override
    public int compare(Path p1, Path p2) {
        return p1.get_total_cost() -p2.get_total_cost();
    }
    
}
