/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banker.algorithm;

import java.util.Scanner;

class Start {

    public final int nor = 3;
    private int[] resources;
    private int numberOfProcesses;
    private int[] available;
    private int[] numberOfInstances;
    private int[][] need;
    private int[][] allocation;
    private int[][] max;
    Scanner sc = new Scanner(System.in);

    Start() {
        this.numberOfInstances = new int[nor];
        System.out.println("Enter number of processes");
        this.numberOfProcesses = sc.nextInt();
        for (int i = 0; i < nor; i++) {
            System.out.println("Enter number of instances of resource " + i);
            this.numberOfInstances[i] = sc.nextInt();
        }
    }

    public void checkDeadlock() {
        int finish[], temp, flag = 1, k, c1 = 0;

        int dead[];
        int safe[];
        int i, j;

        finish = new int[this.numberOfProcesses];
        dead = new int[100];
        safe = new int[100];

        for (i = 0; i < this.numberOfProcesses; i++) {
            finish[i] = 0;
        }
        //find need matrix
        this.getAvailable();
        this.getNeed();
        
        while (flag == 1) {
            flag = 0;
            for (i = 0; i < this.numberOfProcesses; i++) {
                int c = 0;
                for (j = 0; j < this.nor; j++) {
                    if ((finish[i] == 0) && (need[i][j] <= available[j])) {
                        c++;
                        if (c == this.nor) {
                            for (k = 0; k < this.nor; k++) {
                                available[k] += this.allocation[i][j];
                                finish[i] = 1;
                                flag = 1;
                            }
                            if (finish[i] == 1) {
                                i = this.numberOfProcesses;
                            }
                        }
                    }
                }
            }
        }
        j = 0;
        flag = 0;
        for (i = 0; i < this.numberOfProcesses; i++) {
            if (finish[i] == 0) {
                dead[j] = i;
                j++;
                flag = 1;
            }
        }
        if (flag == 1) {

            System.out.println("\n\nSystem is in Deadlock and the Deadlock process are\n");
            for (i = 0; i < this.numberOfProcesses; i++) {
                System.out.println("P" + dead[i]);
            }
        } else {
            System.out.println("No deadlock occure");
        }
    }

    public void initializeAllocation() {
        this.allocation = new int[this.numberOfProcesses][this.nor];
        for (int i = 0; i < this.numberOfProcesses; i++) {
            for (int j = 0; j < nor; j++) {
                System.out.println("Enter the allocations for process " + i + " and resource " + j);
                allocation[i][j] = sc.nextInt();
            }
        }
    }

    public void initializeMax() {
        this.max = new int[this.numberOfProcesses][this.nor];
        for (int i = 0; i < this.numberOfProcesses; i++) {
            for (int j = 0; j < nor; j++) {
                System.out.println("Enter the max for process " + i + " and resource " + j);
                max[i][j] = sc.nextInt();
            }
        }
    }

    public int[] getAvailable() {
        this.available = new int[nor];
        int sum = 0;
        for (int i = 0; i < nor; i++) {
            sum = 0;
            for (int j = 0; j < this.numberOfProcesses; j++) {
                //sum= the sum of the column (resource instances)
                sum += allocation[j][i];
            }
            /*
            We get the available of each resource by subtracting
            the number of instances with the sum of each column
             */
            this.available[i] = this.numberOfInstances[i] - sum;
        }
        return this.available;
    }

    public int[][] getNeed() {
        this.need = new int[this.numberOfProcesses][this.nor];
        for (int i = 0; i < this.numberOfProcesses; i++) {
            for (int j = 0; j < nor; j++) {
                this.need[i][j] = this.max[i][j] - this.allocation[i][j];
            }
        }
        return this.need;
    }
}

public class BankerAlgorithm {

    /**
     * @param args the command line arguments
     */
//    public static void prnt1d(int []arr){
//        for (int i = 0; i < arr.length; i++) 
//            System.out.print(arr[i] + "|");
//        System.out.print("\n");
//    }
//    public static void prnt2d(int [][]arr){
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) 
//                System.out.print(arr[i][j] + "|");
//            System.out.print("\n");
//        }
//    }
    public static void main(String[] args) {
        Start s = new Start();
        s.initializeAllocation();
        s.initializeMax();
//        System.out.println("Need : ");
//        prnt2d(s.getNeed());
//        System.out.println("Available : ");
//        prnt1d(s.getAvailable());
        s.checkDeadlock();

    }

}
