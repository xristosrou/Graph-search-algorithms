package newpackage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grid extends JPanel {
     private List<Point> wallCells;
          private List<Point> pathCells;
          //private List<Point> cafeCells;
          private List<Point> solution;
          private Point start;
          private Point finish;
          private int width ;
          private int height ;
          private Point a,b;
          
          public Grid(){
              wallCells = new ArrayList<>();
              pathCells = new ArrayList<>();
              //cafeCells = new ArrayList<>();
          }
          
         public Grid(ArrayList<Node> maze,Point start,Point finish,Point a,Point b,int lines,int columns){
              wallCells = new ArrayList<>();
              pathCells = new ArrayList<>();
              solution=new ArrayList();
              this.start=start;
              this.finish=finish;
              this.a=a;
              this.b=b;
              width=lines;
              height=columns;
              for(Node node:maze){
                  if(node.getWeight()==-1)
                      wallCells.add(node.getCoordinates());
                  else 
                      pathCells.add(node.getCoordinates());
              }
          }
        
          
          protected void paintComponent(Graphics g){
              super.paintComponent(g);
            
             
              
              // painting wall cells
              for(Point cell:wallCells){
                int cellX = 10 + (cell.x * 10);
                int cellY = 10 + (cell.y * 10);
                g.setColor(Color.black);
                g.fillRect(cellY, cellX, 10, 10);
              }
              
              // painting path cells
              for(Point cell:pathCells){
                  //System.out.println(cell);
                int cellX = 10 + (cell.x * 10);
                int cellY = 10 + (cell.y * 10);
                g.setColor(Color.WHITE);
                g.fillRect(cellY, cellX, 10, 10);
              }
              
                //System.out.println("edo");
               for(Point cell:solution){
                //System.out.println(cell);
                int cellX = 10 + (cell.x * 10);
                int cellY = 10 + (cell.y * 10);
                g.setColor(Color.RED);
                g.fillRect(cellY, cellX, 10, 10);
              }
              
              
                g.setColor(Color.black);
                int cellX = 10 + (start.x * 10);
                int cellY = 10 + (start.y * 10);
                g.drawString("S", cellY, cellX+10);
                
                cellX = 10 + (finish.x * 10);
                cellY = 10 + (finish.y * 10);
                g.drawString("G",cellY,cellX+10);

                cellX = 10 + (a.x * 10);
                cellY = 10 + (a.y * 10);
                g.drawString("A",cellY,cellX+10);
                
                cellX = 10 + (b.x * 10);
                cellY = 10 + (b.y * 10);
                g.drawString("B",cellY,cellX+10);
                
                
               g.setColor(Color.black);
               g.drawRect(10, 10, width * 10, height * 10);

              for (int i = 10; i <= width * 10; i += 10) {
                g.drawLine(i, 10, i, height * 10 + 10);
               }

              for (int i = 10; i <= height * 10; i += 10) {
               g.drawLine(10, i, width * 10 + 10, i);
              }
               
              //super.paintComponent(g);
             // Dimension d=this.getSize();
              //for(int i=0;i<d.height;i++){
                  
              //}
          }
          
           public void fillWallCell(int x, int y) {
            wallCells.add(new Point(x, y));
            repaint();
           }
           
           public void fillPathCell(int x, int y) {
            pathCells.add(new Point(x, y));
            repaint();
           }
           
           public void fillCafeCell(int x, int y) {
            //cafeCells.add(new Point(x, y));
            repaint();
           }
           
           public void fillStartCell(int x, int y) {
               start = new Point(x, y);
               repaint();
           }
           
           public void fillFinishCell(int x, int y) {
               finish = new Point(x, y);
               repaint();
           }
           
           public void fill(ArrayList<Point> a){
               /*for(Point p:a){
                   solution.add(p);
               }*/
               //solution.addAll(a);
               solution=a;
               repaint();
           }
       
           
           /*public static void main(String args[]){
                Grid grid = new Grid();
                JFrame window = new JFrame();
                window.setSize(4 * 10 + 40, 4 * 10 + 60);
                //window.setSize(500, 500);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.add(grid);
                window.setVisible(true);
                grid.fillStartCell(0, 1);
                grid.fillFinishCell(3, 3);
                grid.fillPathCell(1, 2);
           }*/
    }

