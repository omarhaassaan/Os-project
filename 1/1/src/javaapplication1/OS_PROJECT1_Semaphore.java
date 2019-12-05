package javaapplication1;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
  class Count{
       static int co = 0;
             }
class T extends Thread
{
    Semaphore s1 ;
    String t1;
    public T(Semaphore s1 , String t1)
    {
        super(t1);
        this.s1=s1;
        this.t1=t1;
        
    }
    @Override
    public void run()
    {
        
        if(this.getName().equals("FRIST ONE"))
        {
            
            System.out.println("The Following Thread is waiting for the permit = "+t1);
        try {
   
            s1.acquire();
            
            System.out.println("The Following thread got the permit = "+t1);
            
            for(int i =0;i<10;i++)
            {
                Count.co ++ ;
                System.out.println(t1 + "=" + Count.co) ;
                
            }
            Thread.sleep(10);
        }
        catch (Exception e) {
       System.out.println("Error");
        } 
        System.out.println("The Following thread  released the permit = "+t1);
        s1.release();
        }
        else
        {
              if(this.getName().equals("SECOND ONE"))
        {
            
            System.out.println("The Following Thread is waiting for the permit = "+t1);
        try {
   
            s1.acquire();
            
            System.out.println("The Following thread got the permit = "+t1);
            
            for(int i =0;i<10;i++)
            {
                Count.co -- ;
                System.out.println(t1 + "=" + Count.co) ;
                
            }
            Thread.sleep(10);
        }
        catch (Exception e) {
       System.out.println("Error");
        } 
        System.out.println("The Following thread  released the permit = "+t1);
        s1.release();
        }  
                }
    }
    
}
public class OS_PROJECT1_Semaphore {
    
    public static void main(String[] args) throws InterruptedException {
         Semaphore s1 = new Semaphore(1);
        T Frist = new T(s1,"FRIST ONE");
        T Second = new T(s1,"SECOND ONE");
        
        Frist.start();

        Second.start();

        Frist.join();
        
        Second.join();
                
         
        System.out.println("" + Count.co);
        
    }
    
}
