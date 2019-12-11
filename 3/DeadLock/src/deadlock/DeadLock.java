package deadlock;

import java.util.concurrent.locks.ReentrantLock;

//Resource class 
class Resource extends ReentrantLock {
//represent the Thread ID Occupy particular resource 
    // Ex. : Thread1 => thread number 1
    private int threadNumber = -1;

    public void lock(int threadNumber) {
        super.lock();
        this.threadNumber = threadNumber;
    }

    public void unlock() {
        super.unlock();
        this.threadNumber = -1;
    }

    public int getThreadNumber() {
        return threadNumber;
    }
}

//First thread class
// resource 0 then resource 1
class Thread1 extends Thread {

    Resource resources[] = new Resource[2];

    Thread1(Resource [] resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        resources[1].lock(1);
        try {
            Thread.sleep(10);
            System.out.println("T1 " + resources[1]);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(resources[1].isLocked());
        resources[0].lock(1);
        resources[1].unlock();
        System.out.println("T1 " + resources[0]);
        resources[0].unlock();
    }
}

//Second thread class
// resource 1 then resource 0
class Thread2 extends Thread {

    Resource resources[] = new Resource[2];

    Thread2(Resource []r) {
        this.resources = r;
    }

    @Override
    public void run() {
        resources[0].lock(2);
        try {
            Thread.sleep(10);
            System.out.println("T1 " + resources[0]);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(resources[1].isLocked());
        resources[1].lock(2);
        resources[0].unlock();
        System.out.println("T1 " + resources[1]);
        resources[1].unlock();
    }
}

//main program
public class DeadLock {

    public static void main(String[] args) {
        int NumberOfResources = 2;
        Resource resources [] = new Resource[NumberOfResources];
        for (int i = 0; i < NumberOfResources; i++) {
            resources[i] = new Resource();
        }
        Thread t1 = new Thread1(resources);
        Thread t2 = new Thread2(resources);
        t1.start();
        t2.start();
    }
}
