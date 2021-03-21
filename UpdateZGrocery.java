import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.sql.*;
class UpdateZGrocery extends JFrame implements ActionListener
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
   JButton ptname= null;
   JTextField ptquant= null;
   JTextField ptamount= null;
   JComboBox jComboBox= null;
   String patternmatch = "-----------";
   
   
  
   UpdateZGrocery()
   {
     super("Update Inventory");
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
                   submit =new JButton("Update");  
                   submit.setBounds(130,250,100,30);
                   //add(submit);
                   jComboBox= new JComboBox();
                   ptname = new JButton("Find");
                   ptquant= new JTextField();
                   ptamount= new JTextField();
                   warning = new JLabel("Warning");
                   pname.setBounds(50,10,100,30);
                   ptname.setBounds(170,10,80,30);
                   jComboBox.setBounds(260,10,168,30);
                   pquantity.setBounds(50,50,200,30);
                   ptquant.setBounds(230,50,200,30);
                   ptquant.setEditable(false);
                   pamount.setBounds(50,100,200,30);
                   ptamount.setBounds(230,100,200,30);
                   ptamount.setEditable(false);
                   warning.setBounds(110,130,700,30);
                   disclaimer.setBounds(50,150,700,30);
                   disclaimer.setFont(new Font("Calibri", Font.ITALIC, 10));
                   warning.setFont(new Font("Calibri", Font.BOLD, 10));
                   disclaimer.setForeground(Color.BLUE);
                   warning.setForeground(Color.RED);
                   warning.setVisible(false);
                   panel.add(pname);
                   panel.add(ptname);
                   panel.add(jComboBox);
                   panel.add(pquantity);
                   panel.add(ptquant);
                   
                   panel.add(pamount);
                   panel.add(ptamount);
                   panel.add(warning);
                   
                   //panel.add(disclaimer);
                   panel.add(back);
                   panel.add(submit);
                   
                   
                   
                   add(panel); 
                 
                
               
               
               back.addActionListener(this);
               submit.addActionListener(this);
               ptname.addActionListener(this);
               jComboBox.addActionListener(this);
               
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
	Statement stmt;
		Connection con;
		ResultSet rs;
	
	if(o==back){
	 System.out.println("back Object");
	 this.dispose();
	 new IndexFile();
	}
	
	if(o==submit)
	{
	          Object datat=jComboBox.getSelectedItem();
	          String data = String.valueOf(datat);
                  System.out.println("Item Selected is " + data);
	   
	   if(data.equals(patternmatch))
	  {
	      warning.setText("please select item in dropdown");
	      warning.setVisible(true);
	      
          }
          else if(data.equals(" "))
          {
          
              warning.setText("please select item in dropdown");
             warning.setVisible(true);
	     
          }
          else if(ptquant.getText().equals(""))
                     
          {
             warning.setText("please click at the find button first");
             warning.setVisible(true);
	      
          
          }
          else if(ptamount.getText().equals(""))
          {
          
             warning.setText("please click at the find button first");
             warning.setVisible(true);
          }
          else
          {
          
             try
		{  
                   String ptr; 
                   ptr=ptname.getText().substring(0,3).concat("1923");
                   Class.forName("com.mysql.jdbc.Driver");  
		   con =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                  //here NitinShabnam is database name, root is username and password is Shabnam@1989
                  stmt =con.createStatement();  
                  String queryy = ("update GroceryFull set Quantity = ? , Price = ? where Name=?");
                  PreparedStatement preparedStmt = con.prepareStatement(queryy);
                  
                  preparedStmt.setString(1, ptquant.getText());
                  preparedStmt.setString(2, ptamount.getText());
                  preparedStmt.setString(3, data);
                  
                  

                  // execute the preparedstatement
                  preparedStmt.execute();
                  
                  
                  //warning.setVisible(true);
                  //warning.setText("Done");
                  //warning.setForeground(Color.GREEN);
                            String jatt = "Data is Updated";
                            JOptionPane.showMessageDialog(null, jatt);
                  ptquant.setText(" ");
                  ptamount.setText(" ");
                  ptquant.setEditable(false);
                  ptamount.setEditable(false);
                           
                  con.close();
                  
                  //to set the combobox again
                  jComboBox.removeAllItems();
                  con =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                  //here NitinShabnam is database name, root is username and password is Shabnam@1989
                  stmt =con.createStatement();  
                  rs=stmt.executeQuery("select * from GroceryFull ORDER BY Serial_Number asc");  
                  jComboBox.addItem(patternmatch);
                   while (rs.next()) {
                   String pat = rs.getString("Name");
                   System.out.println("Data fetched is " + pat);
                   jComboBox.addItem(pat);
                   
                 }
                 
                 con.close();
                  
                  
                  
                  
             }
             
                
                catch(Exception e){ System.out.println(e);} 
          
          }
          
	
	}
	
	if(o==ptname)
	{
	        
	  try
		{  
                   Class.forName("com.mysql.jdbc.Driver");  
		   con =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                  //here NitinShabnam is database name, root is username and password is Shabnam@1989
                  stmt =con.createStatement();  
                  rs=stmt.executeQuery("select * from GroceryFull ORDER BY Serial_Number asc");  
                  jComboBox.removeAllItems();
                  jComboBox.addItem(patternmatch);
                   while (rs.next()) {
                   String pat = rs.getString("Name");
                   System.out.println("Data fetched is " + pat);
                   jComboBox.addItem(pat);
                   
                 }
                 
                 con.close();
                 
                 
                  
             }
             
                
                catch(Exception e){ System.out.println(e);} 
 
	
	}
	if(o==jComboBox)
	{
	
	          Object datat=jComboBox.getSelectedItem();
	          String data = String.valueOf(datat);
                  System.out.println("Item Selected is " + data);
                  warning.setVisible(false);
                  if(data.equals(patternmatch))
                  {
                     ptquant.setText("");
                     ptamount.setText("");
                     ptquant.setEditable(false);
                     ptamount.setEditable(false);
                  }
                  else
                  {
                  
                     String query = "SELECT * FROM GroceryFull WHERE Name='" + data + "'";
                     try
                     {
                        Class.forName("com.mysql.jdbc.Driver");  
		         Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/NitinShabnam","root","Shabnam@1989");  
                        //here NitinShabnam is database name, root is username and password is Shabnam@1989
                        Statement stmtt =conn.createStatement();  
                        ResultSet rss=stmtt.executeQuery(query); 
                        while(rss.next())
                        {
                           ptquant.setText(rss.getString("Quantity"));
                           ptamount.setText(rss.getString("Price"));
                        }
                        ptquant.setEditable(true);
                        ptamount.setEditable(true);
                     }
                     catch(Exception e){ System.out.println(e);} 
                  }
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
