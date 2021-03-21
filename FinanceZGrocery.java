import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.hssf.usermodel.*;
import java.io.*;
import java.time.*;

class FinanceZGrocery extends JFrame implements ActionListener
{
    JPanel panel= new JPanel(null);
    JButton back=null;
    JButton submit=null;
    JLabel currentamount=null,rdetails=null,rdetails2=null,currentdate=null;
    JTextField jtcurrentamount=null,jtcurrentdate=null;
    int totalAmount[]=null;
    int setTotal=0,county=0;
		   String setTotalstring= new String();
		   String getData1= new String();
		   int countu=0;
		   String temp=null;
		   LocalDate dd=null;
		   LocalDate dd1=null;
		   LocalDate ld=null;
		   String checkDate = null;
                   Month checkmonth1=null, checkmonth2=null;
                   int checkyear1=0; 
                   int checkyear2=0;
    String amountdata[]=null;
    public FinanceZGrocery()
    {
    
     super("Nitin's & Shabnam's Management System");
	addWindowListener (new WindowAdapter () {
			public void windowClosing (WindowEvent we) {
				quitApp ();
			}
		}
		);
		   
		   try
		  {
		   
		      Class.forName("com.mysql.jdbc.Driver");  
		      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                     //here NitinShabnam is database name, root is username and password is Shabnam@1989
                     Statement stmt=con.createStatement();  
                     
                     //ResultSet rs=stmt.executeQuery("select * from GroceryFull ORDER BY Name asc"); 
	             
	             ResultSet rs1=stmt.executeQuery("select * from GroceryFull ORDER BY Name asc");	   
		      int total=0;
		      /*while(rs.next())
		      {
		      
		         ++total;
		      }
		      
		      */
		      
		      totalAmount = new int[1000];
		      amountdata= new String[1000];
		      
		      
		      while(rs1.next())
		      {
		      
		         amountdata[county]=rs1.getString("Price");
		         System.out.println("Amount is " + amountdata[county]);
		         totalAmount[county]=Integer.parseInt(amountdata[county]);
		         ++county;
		      }
		      
		      
		      
		      //System.out.println(county);
		      for(int r=0;r<county;r++)
		      {
		         
		          setTotal=totalAmount[r]+setTotal;
		      }
		      System.out.println("Total items are :" + county); 
		      setTotalstring=String.valueOf(setTotal);
		      con.close();
		      rs1.close();
		      
		   }
		   catch(Exception e)
		   {
		      System.out.println("Issue is :" +e);
		   }
		   
		   try
		   {
		   
		     Class.forName("com.mysql.jdbc.Driver");  
		     Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                     //here NitinShabnam is database name, root is username and password is Shabnam@1989
                     Statement stmt1=con1.createStatement();
                     ResultSet rs2=stmt1.executeQuery("select * from FinanceFull ORDER BY Dob asc");
                     //LocalDate ld=LocalDate.parse(rs2.getDate("Dob"));
                     //Date ld= new Date();
                     System.out.println(rs2);
                     while(rs2.next())
                     {
                       
                      // System.out.println("Date is " + rs2.getDate("Dob"));
                       String dfg=rs2.getDate("Dob").toString();
                       dd=LocalDate.parse(dfg);
                       ++countu;
                     } 
                     if(countu==0)
                     {
                       getData1="Recent details of Empty is saved";
                     }
                     else
                     {
                        getData1="Recent details of " + dd +" is saved";
                     }
		     
		   
		   }
		   catch(Exception e)
		   {
		     
		     System.out.println("Issue is " + e);
		   }
		   
		   currentamount= new JLabel("Current amount in the inventory--");
		   currentamount.setForeground(Color.BLUE);
		   currentamount.setBounds(10,30,250,30);
		   
		   jtcurrentamount= new JTextField();
		   jtcurrentamount.setText(setTotalstring);
		   jtcurrentamount.setEditable(false);
		   jtcurrentamount.setBounds(300,30,150,30);
		   
		   
	           rdetails= new JLabel(getData1);
		   rdetails.setForeground(Color.RED);
		   rdetails.setBounds(50,100,300,30);
		   
		   String getData2="If you want to save it as new budget please save it";
		   rdetails2= new JLabel(getData2);
		   rdetails2.setForeground(Color.RED);
		   rdetails2.setBounds(50,130,400,30);
		   
		   
	           currentdate= new JLabel("Current date is");
	           currentdate.setForeground(Color.BLUE);
	           currentdate.setBounds(60,190,150,30);
	           jtcurrentdate= new JTextField();
		   jtcurrentdate.setBounds(260,190,100,30);
		   ld=java.time.LocalDate.now();
		   String newDate = ld.toString();
		   jtcurrentdate.setText(newDate);
		   jtcurrentdate.setEditable(false);
	           
	           back =new JButton("<--Back");  
                   back.setBounds(260,250,100,30);  
                   
                   //add(back);
                   submit =new JButton("Save");  
                   submit.setBounds(130,250,100,30);	
                   panel.add(currentamount);
                   panel.add(jtcurrentamount);
                   panel.add(rdetails);
                   panel.add(rdetails2);
                   panel.add(jtcurrentdate);
                   panel.add(currentdate);
                    panel.add(back);
                    panel.add(submit);		
		     add(panel);
		   setSize(500,400);
		
		back.addActionListener(this);
		submit.addActionListener(this);
	        setResizable(false);

	        setVisible(true);
	        setLocation(
		(Toolkit.getDefaultToolkit().getScreenSize().width-getWidth() )/2,
		(Toolkit.getDefaultToolkit().getScreenSize().height-getHeight() )/2
		);
    }
 
  public void actionPerformed(ActionEvent ae)
   {
	Object o =ae.getSource();
	
	 if(o==back)
	 {
	    this.dispose();
	    new IndexFile();
	 } 
	 else if(o==submit)
	 {
	     
	        Statement stmt3;
		Connection con3;
		ResultSet rs3;
	      try
		{  
                   Class.forName("com.mysql.jdbc.Driver");  
		   con3 =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                  //here NitinShabnam is database name, root is username and password is Shabnam@1989
                  stmt3 =con3.createStatement();  
                  rs3=stmt3.executeQuery("select * from FinanceFull ORDER BY Dob asc");  
                  
                  while(rs3.next())
                  {
                           checkDate=rs3.getDate("Dob").toString();
                              // System.out.println("Date is " + checkDate);
	          }
	          
	          //System.out.println("yo Date is " + checkDate);
	          dd1=LocalDate.parse(checkDate);
	          checkmonth1=ld.getMonth();
	          
	          checkyear1=ld.getYear();
	          checkmonth2=dd1.getMonth();
	          
	          checkyear2=dd1.getYear();
	          System.out.println(checkmonth1 + " " + checkmonth2 + " " + checkyear1 + " " + checkyear2);
	          
	          if(ld.isEqual(dd1) )
	          {
	              try{
	               System.out.println("same date"); 
	               Class.forName("com.mysql.jdbc.Driver");  
	               String data = ld.toString();
		       Connection con4 =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                      //here NitinShabnam is database name, root is username and password is Shabnam@1989
                      Statement stmt4 =con4.createStatement();  
                      String queryy = "Delete from FinanceFull where Dob = ?";
                      PreparedStatement preparedStmt = con4.prepareStatement(queryy);
                      preparedStmt.setDate(1,java.sql.Date.valueOf(dd));

                     // execute the preparedstatement
                     preparedStmt.execute();
                  
                  
                     //warning.setVisible(true);
                     //warning.setText("Done");
                     //warning.setForeground(Color.GREEN);
                      String jatt = "New Budget Enterd";
                            JOptionPane.showMessageDialog(null, jatt);
                            
                      }
                      catch(Exception e)
                      {
                       System.out.println("Issue is " + e);
                      }
                      
                      //Add data now
                     try{ 
                      Class.forName("com.mysql.jdbc.Driver");  
	               String data = dd1.toString();
		       Connection con5 =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                    String query = ("insert into FinanceFull (Dob,Items,Amount) values values (?,?,?)");

                    PreparedStatement preparedStmt1 = con5.prepareStatement(query);
                    preparedStmt1.setDate(1, java.sql.Date.valueOf(LocalDate.parse(jtcurrentdate.getText())));
                    preparedStmt1.setInt(2, county);
                    preparedStmt1.setInt(3, Integer.parseInt((jtcurrentamount.getText())));
                    
                    
                         

                    // execute the preparedstatement
                     preparedStmt1.execute();
                     }
                     catch(Exception e)
                     {
                     
                       System.out.println("Issue is " + e);
                     }
	          }
	          
	          //String re = ld.getMonth();
	          
	         
	          else if((ld.isAfter(dd1)) && (checkmonth1.equals(checkmonth2)))
	          {
	          
	             System.out.println("date after saved one but same month");
	             
	             try{
	               System.out.println("same date"); 
	               Class.forName("com.mysql.jdbc.Driver");  
	               String data = dd1.toString();
		       Connection con6 =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                      //here NitinShabnam is database name, root is username and password is Shabnam@1989
                      Statement stmt6 =con6.createStatement();  
                      String queryy = "Delete from FinanceFull where Dob = ?";
                      PreparedStatement preparedStmt3 = con6.prepareStatement(queryy);
                      preparedStmt3.setDate(1, java.sql.Date.valueOf(LocalDate.parse(jtcurrentdate.getText())));

                     // execute the preparedstatement
                     preparedStmt3.execute();
                  
                  
                     //warning.setVisible(true);
                     //warning.setText("Done");
                     //warning.setForeground(Color.GREEN);
                      String jatt = "New Budget Enterd";
                            JOptionPane.showMessageDialog(null, jatt);
                            
                      }
                      catch(Exception e)
                      {
                       System.out.println("Issue is " + e);
                      }
                      
                      //Add data now
                      
                      try{ 
                      Class.forName("com.mysql.jdbc.Driver");  
	               String data = dd1.toString();
		       Connection con5 =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                    String query = ("insert into FinanceFull (Dob,Items,Amount) values values (?,?,?)");

                    PreparedStatement preparedStmt1 = con5.prepareStatement(query);
                    preparedStmt1.setDate(1, java.sql.Date.valueOf(LocalDate.parse(jtcurrentdate.getText())));
                    preparedStmt1.setInt(2, county);
                    preparedStmt1.setInt(3, Integer.parseInt((jtcurrentamount.getText())));
                    
                         

                    // execute the preparedstatement
                     preparedStmt1.execute();
                     }
                     catch(Exception e)
                     {
                     
                       System.out.println("Issue is " + e);
                     }
                      
                      
                      
	          }
	          
	          else if((ld.isAfter(dd1)))
	          {
	            
	            System.out.println("date after saved one Next month");
	            try{ 
                      Class.forName("com.mysql.jdbc.Driver");  
	               String data = dd1.toString();
		       Connection con5 =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                    String query = ("insert into FinanceFull (Dob,Items,Amount) values values (?,?,?)");

                    PreparedStatement preparedStmt1 = con5.prepareStatement(query);
                    preparedStmt1.setDate(1, java.sql.Date.valueOf(LocalDate.parse(jtcurrentdate.getText())));
                    preparedStmt1.setInt(2, county);
                    preparedStmt1.setInt(3, Integer.parseInt((jtcurrentamount.getText())));
                    
                         

                    // execute the preparedstatement
                     preparedStmt1.execute();
                     }
                     catch(Exception e)
                     {
                     
                       System.out.println("Issue is " + e);
                     }
	          }
	          else if((ld.isBefore(dd1)))
	          {
	             
	             System.out.println("Chance Over");
	             
	          }
	          else
	          {
	          
	          }
	       }
	       catch(Exception e)
	       {
	          System.out.println("Issue is "+ e);
	       }
	 }
	 else
	 {
	 
	 }
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

}
