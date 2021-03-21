import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;

import java.sql.*;
import java.security.CodeSource;
import java.io.*;

import javax.swing.border.*;
import javax.swing.table.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.hssf.usermodel.*;

class BackupZGrocery extends JFrame implements ActionListener
{
    JButton back=null;
    JButton backupb=null;
    JButton recovery=null;
    JPanel panel = new JPanel(null);
    JLabel backlabel=null;
    JLabel recovlabel=null;
    JLabel dis=null;
     int rowsxl = 0;
     int colsxl =4;
     String data1[][]= new String[0][0];
     int i=0,j=0;
    public BackupZGrocery()
    {
    
     super("Nitin's & Shabnam's Management System");
	addWindowListener (new WindowAdapter () {
			public void windowClosing (WindowEvent we) {
				quitApp ();
			}
		}
		);
		
		back=new JButton("<--Back");
		backupb=new JButton("Backup");
		backlabel= new JLabel("Backup your all data :) for your safety");
		
		recovlabel= new JLabel("Data is lost :( no worries!! click on recovery");
		recovery= new JButton("Recovery");
		dis= new JLabel("");
		    recovlabel.setBounds(100,120,350,30);
                   recovlabel.setForeground(Color.BLUE);
                   recovlabel.setFont(new Font("Calibri", Font.BOLD, 10));
		    recovery.setBounds(170,160,100,30);
                   recovery.setBackground(Color.RED);
                   recovery.setForeground(Color.YELLOW);
                   
                   backlabel.setBounds(120,20,350,30);
                   backlabel.setForeground(Color.RED);
                   backlabel.setFont(new Font("Calibri", Font.BOLD, 10));
                   backupb.setBounds(170,60,100,30);
                   backupb.setBackground(Color.GREEN);
                   backupb.setForeground(Color.BLACK);
                  dis.setForeground(Color.BLUE);
                  dis.setVisible(false);
                  dis.setBounds(120,280,350,30); 
                  back.setBounds(170,250,100,30); 
                  panel.add(backlabel);
                  panel.add(recovlabel);
                  panel.add(back);
                  panel.add(backupb);
                  panel.add(recovery);
                  panel.add(dis);
		   add(panel);
		   
		   back.addActionListener(this);
		   backupb.addActionListener(this);
		   recovery.addActionListener(this);
		   setSize(500,400);
		
		
		
	        setResizable(false);

	        setVisible(true);
	        setLocation(
		(Toolkit.getDefaultToolkit().getScreenSize().width-getWidth() )/2,
		(Toolkit.getDefaultToolkit().getScreenSize().height-getHeight() )/2
		);
    }
   
   private void quitApp ()
	 {

		try 
		{
			//Show a Confirmation Dialog.
		    	int reply = JOptionPane.showConfirmDialog (this,
					" Do you really want to exit?",
					"Application System - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			//Check the User Selection.
			if (reply == JOptionPane.YES_OPTION) 
			{
				setVisible (false);	//Hide the Frame.
				dispose();            	//Free the System Resources.
				System.out.println ("Thanks for Using ");
				System.exit (0);        //Close the Application.
			}
			else if (reply == JOptionPane.NO_OPTION) 
			{
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		} 

		catch (Exception e) {}

	}
	public void actionPerformed(ActionEvent ae)
   {
      Object o= ae.getSource();
      Workbook wb = new HSSFWorkbook(); 
      Sheet xlxsheet = wb.createSheet("Back-upInventory");
      if(o==back)
      {
      
        this.dispose();
        new IndexFile();
      }
      
      else if(o==recovery)
      {
              try
              {  
                Class.forName("com.mysql.jdbc.Driver");  
		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                 //here NitinShabnam is database name, root is username and password is Shabnam@1989
                 
                  
                  
                DatabaseMetaData dbm = con.getMetaData();
               // check if "employee" table is there
               ResultSet tables = dbm.getTables(null, null, "GroceryFull", null);
               if (tables.next()) 
               {
                     // Table exists
                     System.out.println("Table Exists");
                     
                     
                       String excelFilePath = "/home/shabnam/Downloads/JAVACODEFIRST/ProjectMain/backupfiles/invenbackup1.xls";
 
                      // int batchSize = 20;
 
                      
                        try 
                        {
                        
                              Statement stmt =con.createStatement();  
                              String queryy = "Delete from GroceryFull";
                              Statement preparedStmt = con.createStatement();
                  

                              // execute the preparedstatement
                              preparedStmt.executeUpdate(queryy);
 
                             long start = System.currentTimeMillis();
             
                             FileInputStream inputStream = new FileInputStream(excelFilePath);
 
                             Workbook workbook = new HSSFWorkbook(inputStream);
 
                             Sheet firstSheet = workbook.getSheetAt(0);
                             Iterator<Row> rowIterator = firstSheet.iterator();
 
                             
                             con.setAutoCommit(false);
  
                             String sql = "INSERT INTO GroceryFull (Serial_Number, Name, Quantity, Price) VALUES (?, ?, ?, ?)";
                             PreparedStatement statement = con.prepareStatement(sql);    
             
                            // int count = 0;
             
                             rowIterator.next(); // skip the header row
             
                            while (rowIterator.hasNext()) 
                            {
                                Row nextRow = rowIterator.next();
                                Iterator<Cell> cellIterator = nextRow.cellIterator();
 
                                while (cellIterator.hasNext()) 
                                {
                                  Cell nextCell = cellIterator.next();
 
                                  int columnIndex = nextCell.getColumnIndex();
 
                                  switch (columnIndex) 
                                  {
                                    case 0:
                                    String s_no = nextCell.getStringCellValue();
                                    statement.setString(1, s_no);
                                    break;
                                    case 1:
                                    String name = nextCell.getStringCellValue();
                                    statement.setString(2, name);
                                    break;
                                    case 2:
                                    String quant = nextCell.getStringCellValue();
                                    statement.setString(3, quant);
                                    break;
                                    case 3:
                                    String pr = nextCell.getStringCellValue();
                                    statement.setString(4, pr);
                                    break;
                                 }
 
                              }
                 
                              statement.addBatch();
                 
                              
                                    statement.executeBatch();
                                         
 
                           }
 
                           //workbook.close();
             
                           // execute the remaining queries
                           statement.executeBatch();
  
                           con.commit();
                           con.close();
             
                           long end = System.currentTimeMillis();
                            System.out.printf("Import done in %d ms\n", (end - start));
                            //dis.setVisible(true);
                            //dis.setText("Data-Restored :)in "+ (end-start) + " time !1happy??");
                            String jatt = "Data-Restored :)in "+ (end-start) + " time !1happy??";
                            JOptionPane.showMessageDialog(null, jatt);
                        } 
                        catch (IOException ex1) {
                             System.out.println("Error reading file");
                             ex1.printStackTrace();
                          }
                          catch (SQLException ex2) {
                          System.out.println("Database error");
                          ex2.printStackTrace();
                        }
                     
                     
                     
               }
               else {
                   // Table does not exist
                   Statement stmt=con.createStatement();  
             
                  String query= "CREATE TABLE GroceryFull " +
                   "(Serial_Number VARCHAR(255), " +
                   " Name VARCHAR(255), " + 
                   " Quantity VARCHAR(255), " + 
                   " Price INTEGER)"; 

                     stmt.executeUpdate(query);
                   System.out.println("Table created");
                    
                    
                    
                     //dis.setVisible(true);
                     //dis.setText("Data-Restored :) happy??");
                            String jatt = "Data-Restored :) !1happy??";
                            JOptionPane.showMessageDialog(null, jatt);
               }
               
               
               
              }
              catch(Exception e){System.out.println("Issue is "+ e);}
     
      }
      else if(o==backupb)
      {
         String path="/home/shabnam/Downloads/JAVACODEFIRST/ProjectMain/backupfiles/invenbackup1.xls";
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
                  
               try
               {
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
                
                
                
                
               FileOutputStream fop= new FileOutputStream(path);
               wb.write(fop);
               //dis.setVisible(true);
               //dis.setText("Backup File is created");
               String jatt = "Backup File is created";
                            JOptionPane.showMessageDialog(null, jatt);
           }
          catch(Exception e){System.out.println(e);}
                  
         
      }
      
     else
     {
     
       System.out.println("No input");
     }
   }

}
