package newpackage;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import javax.swing.JFrame;


public class Graph {
    private ArrayList<Node> node_list;
    private HashMap<Point,ArrayList<Point>> neighbors=new HashMap();
    private Grid grid;
    JFrame window;
    Point rootnode;
    Point endnode;
    Point A,B;
    private int lines;
    private int columns;

    public void Create_grid(){
               grid=new Grid( node_list, rootnode, endnode, A, B, columns, lines);
               window=new JFrame();
               window.setSize(lines * 10 +70, columns*10 +70);
               window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
               window.add(grid);
               window.setVisible(true);
              
    }
    
    public void Dispose(){
     if(window!=null)
     window.dispose();
    }
    
    public Graph(int i,int j){
        node_list=new ArrayList(); 
        lines=i;
        columns=j;
        for(int y=0;y<i;y++){
         for(int x=0;x<j;x++)
            neighbors.put(new Point(y,x),new ArrayList());//create empty lsit of neighbors for each node
       }
}   
    public void setRootnode(Point rootnode) {
        this.rootnode = rootnode;
    }

 
    public void setEndnode(Point endnode) {
        this.endnode = endnode;
    }

    public void setA(Point A) {
        this.A = A;
    }

    public void setB(Point B) {
        this.B = B;
    }
   
    public void AddNode(int weight,int x,int y){
        node_list.add(new Node(weight,new Point(x,y)));
    }
    
    
          
     //Store edge between nodes in neighbors in hashmap
    public void  Add_Edge(Point a,Point b){
          neighbors.get(a).add(b);
          neighbors.get(b).add(a);
    }
    
      
    //print all the neighbors for each node
    public void print_neighbors(){
        for(int x=0;x<lines;x++){
          for(int y=0;y<columns;y++){
            System.out.println(neighbors.get(new Point(x,y)));
          }  
        }
    }
    //print the weights of all ndoes
    public void All_nodes(){
        for(int i=0; i<node_list.size();i++){
            System.out.print(node_list.get(i).getWeight()+" "+node_list.get(i).getCoordinates());
            
        }
    }
    
   //create and store edges bwtween nodes
   public void Set_Edges(){
       for(int x=0;x<lines;x++){
           for(int y=0;y<columns;y++){
               if(y+1<columns){
                 if(node_list.get(x *columns +y+1).getWeight()!=-1 && node_list.get(x *columns +y).getWeight()!=-1 )
                     this.Add_Edge(new Point(x,y), new Point(x,y+1));
                 }
               if(x+1<lines){
                   if(node_list.get((x+1) *columns +y).getWeight()!=-1 && node_list.get(x *columns +y).getWeight()!=-1)
                       this.Add_Edge(new Point(x+1,y), new Point(x,y));
                }
           }
       }
   }
   
   public ArrayList<Node> get_unvisited_neighbors(Node n){
       ArrayList<Node> unvisited=new ArrayList();
       Point p=n.getCoordinates();
       for(Point point:neighbors.get(p)){
           if((node_list.get(point.x * columns +point.y).getVisited())==false)
              unvisited.add(node_list.get(point.x * columns +point.y));
       }
      
      return unvisited;
   }
   
   public ArrayList<Point> get_rest_of_path(ArrayList<Point> lista,Node n){
       ArrayList<Point> unvisited=new ArrayList();
       for(Point p:neighbors.get(n.getCoordinates())){
          if(!lista.contains(p))
              unvisited.add(p);
       }
       return unvisited;
   }
  
   public void Clean_nodes(ArrayList<Node> lista){
       for(Node n:lista){
           n.RestoreVisited();
       }
   }
   
   public ArrayList dfs(Node startpoint,Node endpoint){
       ArrayList<Node> visited=new ArrayList();
       ArrayList<Node> children;
       ArrayList<Point> path=new ArrayList();
       Stack<Node> stack=new Stack();
       startpoint.setVisited();
       stack.push(startpoint);
       visited.add(startpoint);
       String v;
       String tmp;
       String childs;
       while(!stack.isEmpty()){
           Node n=(Node)stack.peek();
           path.add(new Point(n.getCoordinates()));
           tmp="";
           v="";
           childs="";
           children=get_unvisited_neighbors(n);
           for(Node node:visited)
             v+="("+node.getCoordinates().x+","+node.getCoordinates().y+")";
           for(int i=0;i<stack.size();i++)
               tmp+="("+stack.get(i).getCoordinates().x+","+stack.get(i).getCoordinates().y+")";
           for(Node node:children)
             childs+="("+node.getCoordinates().x+","+node.getCoordinates().y+")";
           System.out.println("Search frontier: "+tmp + ", Current Node:("+n.getCoordinates().x+","+n.getCoordinates().y+")"+", Children:"+childs+", Visited:"+v);
           if(n.equals(endpoint)){
               Clean_nodes(visited);
               return path;
           }
          
          
           stack.pop();
            
            //if the node has any children
            if(!children.isEmpty()){
               for(Node node:children){
                   node.setVisited();
                   visited.add(node);
                   stack.push(node);       
                }
            }
   }
       Clean_nodes(visited);
       return path;
  }
  
   
   public void run(String algorithm){
       ArrayList<Point> list=null;
       ArrayList<Point> list2=null;
       ArrayList<Point> list3=null;
       ArrayList<Point> list4=null;
      
       Random rand ;
       switch(algorithm){
           case "bfs":
               System.out.println("Path from root node to A");
               list=bfs(fetch(rootnode),fetch(A));
               System.out.println("\n");
               System.out.println("Path from root node to B");
               list2=bfs(fetch(rootnode),fetch(B));
               System.out.println("\n");
               System.out.println("Path from cafeteria A to the class");
               list3=bfs(fetch(A),fetch(endnode));
               System.out.println("\n");
               System.out.println("Path from cafeteria B to the class");
               list4=bfs(fetch(B),fetch(endnode));
               break;
           case "dfs":
               System.out.println("Path from root node to A"); 
               list=dfs(fetch(rootnode),fetch(A));
               System.out.println("\n");
               System.out.println("Path from root node to B");
               list2=dfs(fetch(rootnode),fetch(B));
               System.out.println("\n");
               System.out.println("Path from cafeteria A to the class");
               list3=dfs(fetch(A),fetch(endnode));
               System.out.println("\n");
               System.out.println("Path from cafeteria B to the class");
               list4=dfs(fetch(B),fetch(endnode));
               break;
           case "best":
               System.out.println("Path from root node to A"); 
               list=heuristic_search(fetch(rootnode),fetch(A),0);
               System.out.println("\n");
               System.out.println("from root node to B");
               list2=heuristic_search(fetch(rootnode),fetch(B),0);
               System.out.println("\n");
               System.out.println("Path from cafeteria A to the class");
               list3=heuristic_search(fetch(A),fetch(endnode),0);
               System.out.println("\n");
               System.out.println("Path from cafeteria B to the class");
               list4=heuristic_search(fetch(B),fetch(endnode),0);
               
               break;
               
           case "A*":
               System.out.println("Path from root node to A");
               list=heuristic_search(fetch(rootnode),fetch(A),1);
               System.out.println("\n");
               System.out.println("from root node to B");
               list2=heuristic_search(fetch(rootnode),fetch(B),1);
               System.out.println("\n");
               System.out.println("Path from cafeteria A to the class");
               list3=heuristic_search(fetch(A),fetch(endnode),1);
               System.out.println("\n");
               System.out.println("Path from cafeteria B to the class");
               list4=heuristic_search(fetch(B),fetch(endnode),1);
               
               break;
               
           case "b&b":
               System.out.println("Path from root node to A");
               list=BandB(fetch(rootnode),fetch(A));
               System.out.println("\n");
               System.out.println("from root node to B");
               list2=BandB(fetch(rootnode),fetch(B));
               System.out.println("\n");
               System.out.println("Path from cafeteria A to the class");
               list3=BandB(fetch(A),fetch(endnode));
               System.out.println("\n");
               System.out.println("Path from cafeteria B to the class");
               list4=BandB(fetch(B),fetch(endnode));
               
               break;
       }
      
         System.out.println("\n");
         list.remove(list.size()-1);
         list2.remove(list2.size()-1);
         list.addAll(list3);
         list2.addAll(list4);
         if(find_cost(list) >find_cost(list2)){
             System.out.println("The best path iincludes cafeteria B and is\n");
             for(Point n:list2)
                System.out.print("("+n.x+","+n.y+")");
             grid.fill(list2);
             System.out.println("\n");
         }else if(find_cost(list) < find_cost(list2)){
             System.out.println("The best path includes cafeteria A and is\n");
             for(Point n:list)
                System.out.print("("+n.x+","+n.y+")");
              grid.fill(list);
             System.out.println("\n");
         }else{
             System.out.println("Both paths hava equal cost randomly choosing\n");
             rand=new Random();
             int r=rand.nextInt(1);
             if(r==1){
                 System.out.println("Path with cafeteria A\n");
                 for(Point n:list)
                    System.out.print("("+n.x+","+n.y+")");
                 System.out.println("\n");
                  grid.fill(list);
             } 
             else{
                System.out.println("Path with cafeteria B\n");
                for(Point n:list2)
                   System.out.print("("+n.x+","+n.y+")");
                System.out.println("\n");    
                 grid.fill(list2);
            }
         }
         
       
   }
   
   public void show(){
       for(int i=0; i<node_list.size();i++)
           System.out.println(node_list.get(i).getVisited());
       
   }
   
   public ArrayList bfs(Node startpoint,Node endpoint){
      ArrayList<Point> path=new ArrayList();
      ArrayList<Node> visited=new ArrayList();
      String childs;
      String v;
      Queue<Node> q=new LinkedList();
      ArrayList<Node> children;
      String tmp;
      startpoint.setVisited();
      q.add(startpoint);
      visited.add(startpoint);
      while(!q.isEmpty()){
         Node n=(Node)q.peek();
         path.add(new Point(n.getCoordinates()));
         tmp="";
         childs="";
         v="";
         for(Node node:visited)
             v+="("+node.getCoordinates().x+","+node.getCoordinates().y+")";
         for(Node node:q)
             tmp+="("+node.getCoordinates().x+","+node.getCoordinates().y+")";
         children=get_unvisited_neighbors(n);
         for(Node node:children)
             childs+="("+node.getCoordinates().x+","+node.getCoordinates().y+")";
         System.out.println("Search frontier: "+tmp + ", Current Node:("+n.getCoordinates().x+","+n.getCoordinates().y+")"+", Children:"+childs+", Visited:"+v);
         if(n.equals(endpoint)){
               //clean all nodes
               Clean_nodes(visited);
               return path;
           }
          q.remove();
          if(!children.isEmpty()){
              for(Node node:children){
                   node.setVisited();
                   visited.add(node);
                   q.add(node);       
                }
          }
      }
      Clean_nodes(visited);
      return path;
   }
   
   public Node fetch(Point p){
       return node_list.get(p.x * columns +p.y);
   }
  

   public int Manhattan(Point a,Point b){
       return Math.abs(a.x -b.x)+Math.abs(a.y-b.y);
   }
  
  public ArrayList heuristic_search(Node startpoint,Node endpoint,int var){
       ArrayList<Point> path= new ArrayList();
       ArrayList<Node> visited=new ArrayList();
       ArrayList<Node> children=new ArrayList();
       PriorityQueue<Node> pq=new PriorityQueue(new NodeComparator());
       String tmp;
       String v, childs;
       startpoint.setVisited();
       startpoint.setNew_weight(0);
       visited.add(startpoint);
       pq.add(startpoint);
       while(!pq.isEmpty()){
           Node n=(Node)pq.peek();
           path.add(new Point(n.getCoordinates()));
           tmp="";
           v=childs="";
           for(Node node:visited)
             v+="("+node.getCoordinates().x+","+node.getCoordinates().y+")";
           for(Node node:pq){
               if(var==0)
                    tmp+="("+node.getCoordinates().x+","+node.getCoordinates().y+")"+" h(s):"+node.getHeuristic_value()+" " ;
               else
                    tmp+="("+node.getCoordinates().x+","+node.getCoordinates().y+")"+" h(s):"+Manhattan(node.getCoordinates(),endpoint.getCoordinates())+",d(s):"+node.getNew_weight()+" ";
           }
            children=get_unvisited_neighbors(n);
            for(Node node:children)
             childs+="("+node.getCoordinates().x+","+node.getCoordinates().y+")";
           System.out.println("Search frontier: "+tmp + ", Current Node:("+n.getCoordinates().x+","+n.getCoordinates().y+")"+", Children:"+childs+", Visited:"+v);
            if(n.equals(endpoint)){
               //clean all nodes
               Clean_nodes(visited);
               return path;
           }
         
          pq.remove();
          if(!children.isEmpty()){
              for(Node node:children){
                  node.setVisited();
                  visited.add(node);
                  if(var==1){
                      node.setNew_weight(n.getNew_weight()+ node.getWeight());
                      node.setHeuristic_value(Manhattan(node.getCoordinates(),endpoint.getCoordinates())+node.getNew_weight());
                  } 
                else {
                      node.setHeuristic_value(Manhattan(node.getCoordinates(),endpoint.getCoordinates()));
                  }
                pq.add(node);
              }
          }
       }
       
       Clean_nodes(visited);
       return path;
  }
  
  public ArrayList BandB(Node startpoint,Node endpoint){
     Path tmp;
     Node node;
     String childs="";
     Path current_path;
     ArrayList<Point> children=new ArrayList();
     int new_weight;
     PriorityQueue<Path> pq=new PriorityQueue(new PathComparable());
     pq.add(new Path(startpoint.getCoordinates()));
     while(!pq.isEmpty()){
         current_path=pq.peek();
         Node n=(Node)fetch(pq.peek().get_last_node());
            System.out.println("All paths");
         for(Path path:pq){
             node=fetch(path.get_last_node());
             children=get_rest_of_path(path.getPath(), node);
             childs="";
             for(Point p:children){
                 childs+="("+p.x+","+p.y+")";
             }
             System.out.println("path:"+path.represantation()+", total cost: "+path.get_total_cost()+", d(s):"+path.getWeight()+", h(s):"+ Manhattan(node.getCoordinates(),endpoint.getCoordinates())+", children of last node "+childs);//+", children of last node "+childs
         }
         System.out.println("Path to branch"+current_path.represantation());
         if(n.equals(endpoint))
             return pq.peek().getPath();
         children=get_rest_of_path(pq.peek().getPath(), n);
         
         pq.remove();
         if(!children.isEmpty()){
             for(Point p:children){
                 new_weight=current_path.getWeight()+fetch(p).getWeight();
                 tmp=new Path(current_path.getPath(),new_weight,Manhattan(p,endpoint.getCoordinates())+new_weight);
                 tmp.add_to_path(p);
                 pq.add(tmp);
             }
         }
         
     }
     return  null;
  }
      
  
  
  public int find_cost(ArrayList<Point> path){
      int value=0;
      for(Point p:path){
          value+=fetch(p).getWeight();
      }
      return value;
  }
  
   
}
