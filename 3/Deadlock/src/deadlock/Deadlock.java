/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package deadlock;


/**
 *
 * @author Lenovo
 */



public class Deadlock {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();
    
    public static class Thread2 extends Thread {

    @Override
    public void run() {
        synchronized(lock1){
            try{
                Thread.sleep(10);
            }
            catch(Exception e){
                
            }
        synchronized (lock2){
            
        }            
        }

    }
    
}

    public static class Thread1 extends Thread {

    @Override
    public void run() {
        synchronized(lock2){
            try{
                Thread.sleep(10);
            }
            catch(Exception e){
                
            }
        synchronized (lock1){
            
        }                
        }
    
    }
    
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
    }

}
