package javaapplication1;

import java.util.LinkedList;
import java.util.Queue;

class semaphore {

    int permits; //permits is the initial number of permits available
    protected Queue<Thread> q = new LinkedList<Thread>();

    public semaphore(int permits) {
        this.permits = permits;
    }

    public synchronized void Acquire() throws InterruptedException {
        permits--;
        if (permits < 0) {
            try {
                System.out.println("Blocked");
                q.add(Thread.currentThread());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void Release() {
        permits++;
        if (permits <= 0) {
            System.out.println("Released");
            notify();
            q.remove(q.peek());
        }
    }
}

class Thread1 implements Runnable {
    
    static semaphore s= new semaphore (1);
    public Thread1() {
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()
                + " is waiting for permit");
        try {
            s.Acquire();
            System.out.println(Thread.currentThread().getName()
                    + " has got permit");

            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()
                        + " > " + OS_PROJECT1_Semaphore.Value++);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()
                + " has released permit");
        s.Release();

    }

}



public class OS_PROJECT1_Semaphore {

    static int Value = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread1 thread1 = new Thread1();
        Thread1 thread2 = new Thread1();
        Thread1 thread3 = new Thread1();
        Thread1 thread4 = new Thread1();
       
        new Thread(thread1, "Thread1").start();
        new Thread(thread2, "Thread2").start();
        new Thread(thread3, "Thread3").start();
        new Thread(thread4, "Thread4").start();

    }
}
