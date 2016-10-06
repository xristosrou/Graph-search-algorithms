package newpackage;

import java.awt.Frame;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

   
    public static void main(String[] args) throws FileNotFoundException, IOException {
      
       String line;
       Scanner user_input=new Scanner(System.in);
       Integer  command;
       int lines=0;
       ArrayList<String[]> list=new ArrayList();
       BufferedReader br= new BufferedReader(new FileReader(new File("input.txt")));
       while((line=br.readLine())!=null){
           list.add(line.split(" "));
           lines++;
           
       }
       
      Graph g=new Graph(lines,list.get(0).length);
        
      for(int i=0;i<list.size();i++){
        for(int k=0;k< list.get(i).length;k++){
           String cur = list.get(i)[k];
           try{
             g.AddNode(Integer.parseInt(cur), i, k);                     
           }catch (NumberFormatException e){
               if(cur.contains("S")) {
                   g.setRootnode(new Point(i,k));
               } else if(cur.contains("G")) {
                   g.setEndnode(new Point(i,k));
               } else if(cur.contains("A")) {
                   g.setA(new Point(i,k));
               } else if (cur.contains("B")) {
                   g.setB(new Point(i,k));
               }
               g.AddNode(1, i, k);
           }
      }
     }
    
      
      g.Set_Edges();
      loop: while(true){
          g.Create_grid();
          System.out.print("Choose from 1 to 6 \n");
          System.out.println("1)bfs \n2)dfs \n3)branch and bound \n4)best first \n5)A* \n6)quit ");
          command=user_input.nextInt();
          switch(command){
              case 6:
                  break loop;
              case 1:
                  g.run("bfs");
                  break;
              case 2:
                  g.run("dfs");
                  break;
              case 3:
                  g.run("b&b");
                  break;
              case 4:
                  g.run("best");
                  break;
              case 5:
                  g.run("A*");
                  break;
               default :
                  System.out.println("no such choice rtard");
          }
          
      }
        Frame[] a=Frame.getFrames();
        for(int i=0; i<a.length;i++){
            a[i].dispose();
        }
      
    }
    
}