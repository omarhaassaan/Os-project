package deadlock;

import java.util.concurrent.locks.ReentrantLock;

//Resource class 
class Resource extends ReentrantLock {
//represent the Thread ID Occupy particular resource 
    // Ex. : Thread1 => thread number 1
    // thread number = -1 when Resource is free

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

    Thread1(Resource[] resources) {
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

    Thread2(Resource[] resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        resources[0].lock(2);
        try {
            Thread.sleep(10);
            System.out.println("T2 " + resources[0]);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(resources[0].isLocked());
        resources[1].lock(2);
        resources[0].unlock();
        System.out.println("T2 " + resources[1]);
        resources[1].unlock();
    }
}

//main program
public class DeadLock {

    //Test Function 
    /*public static void prnt2d(int arr[][]) {
        System.out.println("Need: ");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(arr[i][j] + "|");
            }
            System.out.print("\n");
        }
    }

    public static void prnt1d(int arr[]) {
        System.out.println("Available: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "|");
        }
    }
     */
 /*
    Compare 2 arrays
    -1 => arr1 < arr2
    0 => arr1 = arr2
    1 => arr1 > arr2
     */
    public static int compare(int arr1[], int arr2[]) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] < arr2[i]) {
                return -1;
            } else if (arr1[i] > arr2[i]) {
                return 1;
            }
        }
        return 0;
    }

    public static boolean checkDeadLock(Resource[] resources, Thread t1, Thread t2) {
        int[] available = new int[2];
        int[][] need = new int[2][2];
        //Check available resources and put it in an array
        for (int i = 0; i < available.length; i++) {
            if (resources[i].getThreadNumber() == -1) {
                available[i] = 1;
            } else {
                available[i] = 0;
            }
        }
        // initialize the need 2d array
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                need[i][j] = 1;
            }
        }
        /* Check what each thread hold and put it with 0 
        Example:
           | R1 | R2
        T1 | 0  | 1
        T2 | 1  | 0
        T1 needs R2, T2 needs R1
         */
        for (int i = 0; i < 2; i++) {
            if (resources[i].getThreadNumber() != -1) {
                need[resources[i].getThreadNumber() - 1][i] = 0;
            }
        }
        //  prnt1d(available);
        //  prnt2d(need);
        //Check each thread needs to the available, if all need < available => no deadlock detected 
        for (int i = 0; i < 2; i++) {
            if (compare(need[i], available) == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        int NumberOfResources = 2;
        Resource resources[] = new Resource[NumberOfResources];
        for (int i = 0; i < NumberOfResources; i++) {
            resources[i] = new Resource();
        }
        Thread t1 = new Thread1(resources);
        Thread t2 = new Thread2(resources);
        t1.start();
        t2.start();
        //Assume each resource can execute only one thread
        //DeadLock detection
        while (t1.isAlive() || t2.isAlive()) {
            if (checkDeadLock(resources, t1, t2)) {
                System.out.println("DeadLock Detected !!!!");
                break;
            }
            Thread.sleep(20);
        }

    }
}
