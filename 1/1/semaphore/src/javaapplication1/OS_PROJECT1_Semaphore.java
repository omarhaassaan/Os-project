package javaapplication1;
class semaphore
{
    int permits; //permits is the initial number of permits available
public semaphore(int permits) {
          this.permits=permits;
   }
public synchronized void Acquire() throws InterruptedException {
    if(permits > 0)         // Acquires a permit
           
    {
                  permits--;
    }
    else                      //permit is not available wait
    {
                  this.wait();
                  permits-- ;
    }
    
}
public synchronized void Release() 
{
           //increases the number of available permits by 1
           permits++;
           
           //If permits are greater than 0 . notify waiting threads
           if(permits > 0)
                  this.notify();
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
           
           Thread3 thread3 =new Thread3(semaphore);
           new Thread(thread3,"Thread3").start();
           
           Thread4 thread4 =new Thread4(semaphore);
           new Thread(thread4,"Thread4").start();
           
           
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
    

 class Thread3 implements Runnable{
 
    semaphore semaphore1;
    
    public Thread3(semaphore semaphore1) {
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
 
        
        
        
    
    
class Thread4 implements Runnable{
 
    semaphore semaphore1;
    
    public Thread4(semaphore semaphore1) {
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
                                      " > "+OS_PROJECT1_Semaphore.Value--);
                  }
                  
           } catch (InterruptedException e) {
                  e.printStackTrace();
           }
           
           System.out.println(Thread.currentThread().getName()+
                        " has released permit");
           semaphore1.Release();
    
    }
}

