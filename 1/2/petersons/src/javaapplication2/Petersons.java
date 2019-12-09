public class Petersons{
    int counter=0; 
    boolean flag[] = new boolean[2];
    int i=0;
    int j=1;
    int turn;
    int Critical_variable=100 ;
    
    private class FirstThread extends Thread                    
    { 
        public void run(){
                try{
                    do{
                        flag[i]= true;
                        turn=j;
                        while(flag[j]&&turn==j);
                        //critical sectionn
                        Critical_variable++;
                        System.out.println("X is inside critical section");
                        counter++;
                        System.out.println("counter equal = "+counter);
                        System.out.println("critcial variable eaquals ="+Critical_variable);
                        //
                        flag[i]= false;
                        //remainder section
                    }while(counter<10); // 10 is our limit to remove infinite looping
                }
                catch(Exception ex){
                    System.out.println("something is wrong");
                }
            }
        }
    private class SecondThread extends Thread                                
    { 
            public void run(){
                try{
                    do{
                        flag[j]= true;
                        turn=i;
                        while(flag[i]&&turn==i);
                        //critical section
                        Critical_variable++;
                        System.out.println(" Y is inside critical section");
                        counter++;
                        System.out.println("counter equal = "+counter);
                        System.out.println("Critical variable equals ="+Critical_variable);
                        flag[j]= false;
                        //remainder section
                    }while(counter<10); 
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    public Petersons(){
        System.out.println("Starting Processes");
        Thread X= new FirstThread();
        Thread Y = new SecondThread();
        X.start(); 
        Y.start(); 
    }
        public static void main(String[] args){
        Petersons c1 = new Petersons();
    }
}