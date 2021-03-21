import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.sql.*;
class AddZGrocery extends JFrame implements ActionListener
{
  
  
   int i=0; 
   String fetchedSerialNumber; 
   JButton back= new JButton();
   JButton submit= new JButton();
   JPanel panel = new JPanel(null);
   JLabel pname= new JLabel("Product Name"); 
   JLabel pquantity= new JLabel("Product Quantity");
   JLabel pamount= new JLabel("Product Amount");
   JLabel warning = null;
   JLabel disclaimer= new JLabel("In case you don't know the amount please enter NA and you can update it later.");
   JTextField ptname= null;
   JTextField ptquant= null;
   JTextField ptamount= null;
   
   
  
   AddZGrocery()
   {
     super("Add Inventory");
	addWindowListener (new WindowAdapter () {
			public void windowClosing (WindowEvent we) {
				quitApp ();
			}
		}
		);
		
		
		                  
                   
                   //to add back button
                   back =new JButton("<--Back");  
                   back.setBounds(260,250,100,30);  
                   
                   //add(back);
                   submit =new JButton("Add");  
                   submit.setBounds(130,250,100,30);
                   //add(submit);
                   ptname= new JTextField();
                   ptquant= new JTextField();
                   ptamount= new JTextField();
                   warning = new JLabel("Warning");
                   pname.setBounds(50,10,100,30);
                   ptname.setBounds(230,10,200,30);
                   pquantity.setBounds(50,50,200,30);
                   ptquant.setBounds(230,50,200,30);
                   pamount.setBounds(50,100,200,30);
                   ptamount.setBounds(230,100,200,30);
                   warning.setBounds(110,130,700,30);
                   disclaimer.setBounds(50,150,700,30);
                   disclaimer.setFont(new Font("Calibri", Font.ITALIC, 10));
                   warning.setFont(new Font("Calibri", Font.BOLD, 10));
                   disclaimer.setForeground(Color.BLUE);
                   warning.setForeground(Color.RED);
                   warning.setVisible(false);
                   panel.add(pname);
                   panel.add(ptname);
                   panel.add(pquantity);
                   panel.add(ptquant);
                   panel.add(pamount);
                   panel.add(ptamount);
                   panel.add(warning);
                   
                   panel.add(disclaimer);
                   panel.add(back);
                   panel.add(submit);
                   
                   
                   
                   add(panel); 
                 
                
               
               
               back.addActionListener(this);
               submit.addActionListener(this);
               
               setSize(500,400);
		
		
		
	        setResizable(false);

	        setVisible(true);
	        setLocation(
		(Toolkit.getDefaultToolkit().getScreenSize().width-getWidth() )/2,
		(Toolkit.getDefaultToolkit().getScreenSize().height-getHeight() )/2
		);
   }
   
   

//Event Performer
   public void actionPerformed(ActionEvent ae)
   {
	Object o =ae.getSource();
	int flag =0;
	
	if(o==back){
	 System.out.println("back Object");
	 this.dispose();
	 new IndexFile();
	}
	else if(o==submit)
	{
	        Statement stmt;
		Connection con;
		ResultSet rs;
	  try
		{  
                   Class.forName("com.mysql.jdbc.Driver");  
		   con =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                  //here NitinShabnam is database name, root is username and password is Shabnam@1989
                  stmt =con.createStatement();  
                  rs=stmt.executeQuery("select * from GroceryFull ORDER BY Serial_Number asc");  
                  System.out.println("Value of rs after success is " + rs);
                  i=0;
                  while(rs.next())
                  {
                  
                       ++i;       
                       fetchedSerialNumber  = rs.getString("Serial_Number");  
                       if(rs.getString("Name").trim().equalsIgnoreCase(ptname.getText()))
                       {
                       
                           flag=1;
                       }   
                  }
                  System.out.println("no of data in database is" + i);
                  
                  
                  System.out.println("Data in database is" + fetchedSerialNumber);
                 
               if(ptname.getText().equals(""))
               {   
                    System.out.println("You cannot leave name empty");
                    warning.setText("You cannot leave name empty");
                    warning.setVisible(true);
                     
               }
               
               else if(ptquant.getText().equals(""))
               {
                  System.out.println("You cannot leave quantity empty");
                    warning.setText("You cannot leave quantity empty");
                    warning.setVisible(true);
               }
               
               else if(ptamount.getText().equals(""))
               {
                 System.out.println("You cannot leave amount empty");
                 warning.setText("You cannot leave amount empty");
                    warning.setVisible(true);
               }
               
               else if(flag==1)
               {
                 
                 warning.setText("This name is already present please check in view");
                 warning.setVisible(true);
                 ptname.setText("");
                 ptquant.setText("");
                 ptamount.setText("");
               
               }
               else
               {
                   warning.setVisible(false);
                  if(i==0)
                  {
                    
                     int j = i;
                     ++j;
                    String str; 
                    str=ptname.getText().substring(0,3).concat("1923").trim(); 
                    System.out.println(ptname.getText());
                    System.out.println(ptquant.getText());
                    System.out.println(ptamount.getText());
                    String query = ("insert into GroceryFull (Serial_Number, Name, Quantity, Price) values (?,?,?,?)");

                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString(1, str);
                    preparedStmt.setString(2, ptname.getText().trim());
                    preparedStmt.setString(3, ptquant.getText().trim());
                    preparedStmt.setString(4, ptamount.getText().trim());
      

                    // execute the preparedstatement
                     preparedStmt.execute();
                     warning.setText("Done");
                     warning.setForeground(Color.GREEN);
                     warning.setVisible(true);
                     ptname.setText(" ");
                     ptquant.setText(" ");
                     ptamount.setText(" ");

                  }
                  else if(i>0)
                  {
                    ++i;
                    String str= ptname.getText().substring(0,3).concat("1923").trim();
                    System.out.println(String.valueOf(i)); 
                    System.out.println(ptname.getText());
                    System.out.println(ptquant.getText());
                    System.out.println(ptamount.getText());
                    String query = ("insert into GroceryFull (Serial_Number, Name, Quantity, Price) values (?,?,?,?)");

                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setString(1, str);
                    preparedStmt.setString(2, ptname.getText().trim());
                    preparedStmt.setString(3, ptquant.getText().trim());
                    preparedStmt.setString(4, ptamount.getText().trim());
      

                    // execute the preparedstatement
                     preparedStmt.execute();
                            String jatt = "Data Added";
                            JOptionPane.showMessageDialog(null, jatt);
                     //warning.setText("Done");
                     //warning.setForeground(Color.GREEN);
                     //warning.setVisible(true);
                     ptname.setText(" ");
                     ptquant.setText(" ");
                     ptamount.setText(" ");
                    
                  }
                  else{System.out.println("Out of Scope");}
                   con.close();
               
                
                }
             }
             
                
                catch(Exception e){ System.out.println(e);} 
 
	
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
