import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.sql.*;

class ScrollDemo
{
 
   public ScrollDemo()
   {
    JFrame frame = new JFrame("Creating a Scrollable JTable!");
   JPanel panel = new JPanel();
   String data1[][]= new String[0][0]; 
 
   
  
  String col1[] = {"Unique ID","Name","Quantity","Price"};
  
               try
		{  
                   Class.forName("com.mysql.jdbc.Driver");  
		   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                  //here NitinShabnam is database name, root is username and password is Shabnam@1989
                  Statement stmt=con.createStatement();  
                  Statement stmt1=con.createStatement(); 
                  ResultSet rs=stmt.executeQuery("select * from GroceryFull ORDER BY Serial_Number asc"); 
                  ResultSet rs1=stmt1.executeQuery("select * from GroceryFull ORDER BY Serial_Number asc");
                      int i=0,j=0;
                      
                      while(rs.next())
                   {
                   
                        ++j;  
                   }    
                   
                   
                   System.out.println("Rows are " + j);
                   data1= new String[j][4];  
                   while(rs1.next())
                   {
                   
                     
                     
                         
                              data1[i][0]=rs1.getString("Serial_Number"); 
                              
                              data1[i][1]=rs1.getString("Name");
                              
                              data1[i][2]=rs1.getString("Quantity");
                              
                              data1[i][3]=rs1.getString("Price");
                              
                              i++;
                              
                              
                         
                   }
                  
                  }
                  catch(Exception e){System.out.println(e);}
                  
                 JTable table = new JTable(data1,col1);
                 JTableHeader header = table.getTableHeader();
                 header.setBackground(Color.yellow);
                 JScrollPane pane = new JScrollPane(table);
                 //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                 panel.add(pane);
                 frame.add(panel);
                 frame.setSize(500,400);
                 frame.setUndecorated(true);
                 frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 frame.setVisible(true);
  }
   
   
   public static void main(String[] arg)
   {
      new ScrollDemo();
   }
}


 
