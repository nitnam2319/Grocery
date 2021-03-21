class A
{
   
   public void rest()
   {
      
      System.out.println("A");
   }
}


class B extends A
{

    
    public void rest()
    {
    
      
      System.out.println("B");
    }

}

class C extends A
{

    
    public void rest()
    {
    
      
      System.out.println("C");
    }

}



public class demo
{

   public static void main(String[] arg)
   {
     A b1= new A();
     A b2=  new C();    
     b1=(A)b2;
      
     A b3 =(B) b2;
     b1.rest();
     b3.rest();  
     
   /* System.out.print("Answer number 1 : "); 
       String ta = "A ";
       ta= ta.concat("B ");
       String tb= "C ";
       ta=ta.concat(tb);
       ta.replace("C","D");
       ta=ta.concat(tb);
       System.out.println(ta);*/ 
   }

}
