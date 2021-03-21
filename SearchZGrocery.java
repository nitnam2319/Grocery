import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.hssf.usermodel.*;
import java.io.*;


class SearchZGrocery implements ActionListener
{
  
  JFrame frame = new JFrame("View Inventory");
  JTable table= new JTable();
 // JScrollPane js= new JScrollPane();
  JPanel panel_1 = new JPanel();
  JButton back= new JButton("<--Back");
  JButton savedata= new JButton("Save");
  JLabel jl = new JLabel("");
  int rowsxl = 0;
  int colsxl =4;
     String data1[][]= new String[0][0]; 
 
   SearchZGrocery()
   {
     
		int i=0,j=0;
		JPanel panel = new JPanel();
                
              //  jl.setVisible(false);
                String col1[] = {"Unique ID","Name","Quantity","Price"};
  
               try
		{  
                  Class.forName("com.mysql.jdbc.Driver");  
		   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                  //here NitinShabnam is database name, root is username and password is Shabnam@1989
                  Statement stmt=con.createStatement();  
                  Statement stmt1=con.createStatement(); 
                  ResultSet rs=stmt.executeQuery("select * from GroceryFull ORDER BY Name asc"); 
                  ResultSet rs1=stmt1.executeQuery("select * from GroceryFull ORDER BY Name asc");
                  
                      
                      while(rs.next())
                   {
                   
                        ++j;  
                   }    
                   
                   
                   System.out.println("Rows are " + j);
                   rowsxl=j;
                   
                   data1= new String[j][colsxl];  
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
                 back.setBounds(140,430,100,50);
                 savedata.setBounds(250,430,100,50);
                 jl.setBounds(380,430,200,50);
                 jl.setForeground(Color.RED);
                 jl.setText("Total Items are: " + String.valueOf(j));
                 frame.add(back);
                 frame.add(savedata);
                 frame.add(jl);
                 
                 frame.add(panel);
                 
                 frame.setBounds(500,100,530,540);
                 frame.setUndecorated(true);
                 frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 frame.setVisible(true);
		
		
               
               back.addActionListener(this);
               savedata.addActionListener(this);
               
		
		
		
	        frame.setResizable(false);

	        frame.setVisible(true);
	       
   }
   
  

//Event Performer
   public void actionPerformed(ActionEvent ae)
   {
	Object o =ae.getSource();
	
	Workbook wb = new HSSFWorkbook(); 
        Sheet xlxsheet = wb.createSheet("Inventory");
	
	
	if(o==back){
	 System.out.println("back Object");
	 frame.dispose();
	 new IndexFile();
	}
	
	if(o==savedata)
	{
	
	   String path="inven1.xls";
           try {

                
                /////////////////////////
                /*
                Row rowNew = new Row[];
	        Cell cellNew = new Cell[];
                System.out.println("Entering into the database data");
                System.out.println("No of rows we need is " + rowsxl);
                System.out.println("No of coloumns we need is " + colsxl);
                for(int y=0;y<rowsxl;y++)
                {
                
                   for(int po=0;po<colsxl;po++)
                   {
                      System.out.println("data1["+y+"]["+po+"]"+data1[y][po]);
                   }
                }
                System.out.println("Exiting from the database data");
                */
                //////////////////////////
                
                ++rowsxl;
                System.out.println("No of rows we need is " + rowsxl);
                
                System.out.println("No of coloumns we need is " + colsxl);
                Row xlxrow1[]= new Row[rowsxl];
                xlxrow1[0]= xlxsheet.createRow(0);
                
                Cell xlxcell1[]= new Cell[((rowsxl)*colsxl)];
               
                
                int totalData =(rowsxl*colsxl+(colsxl));
                
                System.out.println("No of cells we need is " + ((rowsxl)*colsxl));
                xlxcell1[0] = xlxrow1[0].createCell(0);
                xlxcell1[0].setCellValue("Unique Number");
                xlxcell1[1] = xlxrow1[0].createCell(1);
                xlxcell1[1].setCellValue("Name");
                xlxcell1[2] = xlxrow1[0].createCell(2);
                xlxcell1[2].setCellValue("Quantity");
                xlxcell1[3] = xlxrow1[0].createCell(3);
                xlxcell1[3].setCellValue("Price");
               
                int f=1,al=0,s=0,colsel=0,rowsel=1;
                for(f=1,al=0;f<rowsxl;f++,al++,++rowsel)
                {
                   xlxrow1[f]= xlxsheet.createRow(f);
                   for(s=0;s<colsxl;s++,++colsel)
                   {
                   
                      xlxcell1[s]=xlxrow1[f].createCell(s);
                      //System.out.println("Data for file is " +data1[d][s]);
                      xlxcell1[s].setCellValue(data1[al][s]);
                      System.out.println("Data for file is " +xlxcell1[s]);
                   }
                   
                   
                }
                
               JOptionPane.showMessageDialog(null, "File is saved");
                
                
               FileOutputStream fop= new FileOutputStream(path);
               wb.write(fop);
               System.out.println("End");
               
           }
          catch(Exception e){}
          
     
     
	
	}
	
	
   }


   
}
