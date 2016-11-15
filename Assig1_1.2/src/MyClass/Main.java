package MyClass;

import java.util.*;


/**
 * Created by rk on 11/9/16.
 * Assignment 1 Ques 1 part 1
 */

public class Main {
    /**
     * Declaring List which will store Data for a particular thread
     */

    private static List<Integer>[] store = new List[10];


    /**
     * Function for generating random 8 bits Binary String
     * @return 8 bit Binary String
     */
    private static String  generateBinary(){
        String b=""; // Storing  bits...
        Random bool = new Random();
        for (int i=0;i<8;i++){
            b+= bool.nextInt(2);
        }
        return b;
    }

    /**
     * Function for detecting operations
     * @param s sum
     * @param m multiply
     * @param avg average
     */

    private static void  detectOps(int s, long m, float avg){
        if (s >= 10000) {
            System.out.println("state detected from add");
        } else {
            System.out.println("state not detected from add");
        }

        if (m >= 100000) {
            System.out.println("state detected from multiply");
        } else {
            System.out.println("state not detected from multiply");
        }
        if (avg >= 1000) {
            System.out.println("state detected from average");
        } else {
            System.out.println("state not detected from average");
        }
    }

    /**
     * Creating a runnable1 thread
     * This thread will be generating random 8 bits binary string and then sleep for 1 sec
     * Then pushing the binary string to thread's Array List of LinkedList
     */

    public static class Runnable1 extends Thread{
        private final int thread_num;
        Runnable1(int thread_num){
            this.thread_num = thread_num;
        }

        @Override
        public void run() {
            while(true){
                String b = generateBinary();
                // System.out.println(thread_num);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int n = Integer.parseInt(b,2);
                if(store[thread_num]==null) store[thread_num] = new LinkedList<Integer>();

                store[thread_num].add(n);
            }

        }
    }

    /**
     * Creating a runnable2 thread
     * This thread check for the 1st element from all Array List of threads of Runnable1
     * If empty then it will wait for the 1st thread for input
     * Doing some operations and detect operations
     */

    public static class Runnable2 extends Thread{
        @Override
        public void run() {
            while(true){
                int s=0;
                long m =1L;
                int i=0;
                while(i<10){
                    while(store[i]==null || store[i].isEmpty()){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                    int e = store[i].remove(0);
                    s+=e;
                    m*=e;
                    float avg = s / 10;
                    detectOps(s,m,avg);
                    i++;
                }
            }
        }
    }

    /**
     * Generating an Array List(size 10) of thread and then starting the threads(Runnable1) one by one..
     * And then starting the thread(Runnable2)
     * @param args
     */
    public static void main(String[] args){

        List<Thread> threads = new ArrayList<Thread>(10);

        for(int i=0;i<10;i++){
            Thread newthread = new Thread(new Runnable1(i));
            threads.add(newthread);
            newthread.start();
        }
        Thread thread = new Thread(new Runnable2());
        thread.start();
    }


}

