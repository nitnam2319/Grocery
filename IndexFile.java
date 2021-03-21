import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;

import java.sql.*;
class IndexFile extends JFrame implements ActionListener
{
   ImageIcon i[]= new ImageIcon[100];
   JMenuBar menubar;
   JMenu grocery,finance,others;
   JLabel l;
   JMenuItem gview,gadd,gdelete,gupdate,fview,backup;
   IndexFile()
   {
        super("Nitin's & Shabnam's Management System");
	addWindowListener (new WindowAdapter () {
			public void windowClosing (WindowEvent we) {
				quitApp ();
			}
		}
		);
		
	 i[0] = new ImageIcon("inventory.jpg");
	 i[1] = new ImageIcon("new.png");
	 i[2] = new ImageIcon("search.png");
	 i[3] = new ImageIcon("update.png");
	 i[4] = new ImageIcon("exit.png");
	 i[5] = new ImageIcon("aboutus.png");
	 i[6] = new ImageIcon("delete.png");	
	 i[7] = new ImageIcon("Copyoflogin.JPG"); 
	 l = new JLabel(i[0]);
	 menubar = new JMenuBar();
	 grocery = new JMenu("Grocery");
	 finance = new JMenu("Finance");
	 others = new JMenu("Others");
	 gview = new JMenuItem("View Inventory",i[2]);
	 gadd = new JMenuItem("Add Inventory",i[3]);
	 gdelete = new JMenuItem("Delete Inventory",i[6]);
	 gupdate = new JMenuItem("Update Inventory",i[3]);
	 fview = new JMenuItem("View Finance",i[5]);
	 backup= new JMenuItem("Backup",i[7]);
	 
	  setJMenuBar(menubar);
	  add(l); 	
	  menubar.add(grocery);
	  menubar.add(finance);
	  menubar.add(others);
	  grocery.add(gview);
	  grocery.add(gadd);
	  grocery.add(gdelete);
	  grocery.add(gupdate);
	  finance.add(fview);
	  others.add(backup);	
	  gview.addActionListener(this);
	  gadd.addActionListener(this);
	  gdelete.addActionListener(this);	
	  gupdate.addActionListener(this);
	  fview.addActionListener(this);
	  backup.addActionListener(this);
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
	
	
	if(o==gview){
	 System.out.println("View Object");
	 this.dispose();
	 new SearchZGrocery();
	}
	else if(o==gadd){
	
	System.out.println("Add Object");
	this.dispose();
	 new AddZGrocery();
	}
	else if(o==gdelete){
	
	System.out.println("Delete Object");
	this.dispose();
	new DeleteZGrocery();
	}
	
        else if (o==gupdate)
        {
        
           this.dispose();
           new UpdateZGrocery();
        }
        else if(o==fview)
        {
          System.out.println("Finance View Page");
          this.dispose();
          new FinanceZGrocery();
        
        }
        else if(o==backup)
        {
        
          this.dispose();
          new BackupZGrocery();
        }
        else
        {
           System.out.println("No Object Found");
        }
	
   }
   // Method when you press cross at the top.
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
   public static void main(String[] arg)
   {
   
     System.out.println("Yes");
     
     try
     {
       UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
     }
     catch(Exception e){}

     JFrame f=new IndexFile();
     f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
   }
}

