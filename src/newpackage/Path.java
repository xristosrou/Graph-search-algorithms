
package newpackage;

import java.awt.Point;
import java.util.ArrayList;


public class Path {
  private ArrayList<Point> path;
  private int weight;
  private int total_cost;

   
   public Path(Point a){
    path=new ArrayList();
    path.add(a);
    total_cost=0;
    weight=0;
   }
   
   public Path(ArrayList<Point> p,int a,int b){
       path=(ArrayList<Point>)p.clone();
       total_cost=b;
       weight=a;
   }  
  
    public int getWeight() {
        return weight;
    }
  
  
    public int get_total_cost() {
        return total_cost;
    }

    public void set_total_cost(int value) {
        total_cost = value;
    }
  
   public Point get_last_node(){
       return path.get(path.size()-1);
   }
   
   public String represantation(){
       String string="";
       for(Point p:path){
           string+="("+p.x+","+p.y+")";
       }
       return string;
   }
  
   
    
    public ArrayList<Point> getPath() {
        return path;
    }

    public void add_to_path(Point p){
        path.add(p);
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
    }
    
    
    public int getweight() {
        return weight;
    }

    public void setweight(int weight) {
        this.weight = weight;
    }
  
}
