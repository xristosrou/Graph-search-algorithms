package newpackage;

import java.awt.Point;



public class Node  {
    private int weight;
    private  Boolean visited;
    private  Point coordinates;
    private int heuristic_value;
    private int new_weight;

    public int getNew_weight() {
        return new_weight;
    }

    public void setNew_weight(int new_weight) {
        this.new_weight = new_weight;
    }
    
    
    
    public int getHeuristic_value() {
        return heuristic_value;
    }

    public void setHeuristic_value(int heuristic_value) {
        this.heuristic_value = heuristic_value;
    }
    
    
    public Node(int weight, Point coordinates) {
        this.weight = weight;
        this.coordinates = coordinates;
        visited=false;
    }

    public Point getCoordinates(){
        return coordinates;
    }
    
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Boolean getVisited() {
        return visited;
    }
    
    public void RestoreVisited(){
        visited=false;
    }

    public void setVisited() {
        visited=true;
    }

    
}
