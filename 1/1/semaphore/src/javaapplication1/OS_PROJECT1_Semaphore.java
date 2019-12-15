package javaapplication1;

import java.util.LinkedList;
import java.util.Queue;

class semaphore
{
    int permits; //permits is the initial number of permits available
    protected Queue<Thread>  q = new LinkedList<Thread>();
    
public semaphore(int permits ) {
          this.permits=permits;
   }
public synchronized void Acquire() throws InterruptedException {
   permits--;
        if(permits<0)
          try
          { System.out.println("Blocked");
          q.add(Thread.currentThread());      
          wait();
          }
        catch(InterruptedException e){ e.printStackTrace();}
}
    

public synchronized void Release() 
{
          permits++;
        if(permits <= 0)
        {
            System.out.println("Released");
            notify();
            q.remove(q.peek());
        }
}
}
 class Thread1 implements Runnable{
 
    semaphore semaphore1;
    
    public Thread1(semaphore semaphore1) {
           this.semaphore1=semaphore1;        
    }
    
    public void run(){
           System.out.println(Thread.currentThread().getName()+
                        " is waiting for permit");
           try {
                  semaphore1.Acquire();
                  System.out.println(Thread.currentThread().getName()+
                               " has got permit");
           
                  for(int i=0;i<5;i++){
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+
                                      " > "+OS_PROJECT1_Semaphore.Value++);
                  }
                  
           } catch (InterruptedException e) {
                  e.printStackTrace();
           }
           
           System.out.println(Thread.currentThread().getName()+
                        " has released permit");
           semaphore1.Release();
    
    }
    
}
 class Thread2 implements Runnable{
 
    semaphore semaphore1;
    public Thread2(semaphore semaphore1){
           this.semaphore1=semaphore1;
    }
    
    public void run(){
           System.out.println(Thread.currentThread().getName()+
                        " is waiting for permit");
           
           try {
                  semaphore1.Acquire();
                  System.out.println(Thread.currentThread().getName()+
                               " has got permit");
           
                  for(int i=0;i<5;i++){          
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+
                                      " >"+OS_PROJECT1_Semaphore.Value--);
                  }
                  
           } catch (InterruptedException e) {
                  e.printStackTrace();
           }
           
           
           System.out.println(Thread.currentThread().getName()+
                        " has released permit");
           semaphore1.Release();
           
           
    }
 }
    

 

public class OS_PROJECT1_Semaphore {
    static int Value=0;
    
    public static void main(String[] args) throws InterruptedException {
        semaphore semaphore=new semaphore(1);
           System.out.println("Semaphore with " +semaphore.permits+ " permit has been created");
           
           Thread1 thread1 =new Thread1(semaphore);
           new Thread(thread1,"Thread1").start();
           
           Thread2 thread2 =new Thread2(semaphore);
           new Thread(thread2,"Thread2").start();
           
           Thread1 thread3 =new Thread1(semaphore);
           new Thread(thread3,"Thread3").start();
           
           Thread2 thread4 =new Thread2(semaphore);
           new Thread(thread4,"Thread4").start();
           
           
    }
}
    
   
