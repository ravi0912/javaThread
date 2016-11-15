package MyClass;


import java.util.Arrays;

/**
 * Created by rk on 11/9/16.
 */

/**
 * Starting the Thread MergeSort
 */
public class ConcurrentMergeSort extends Thread{

    private int[] a;
    private int[] tmpArray;
    private int left;
    private int right;

    /**
     *
     * @param a containing the unsroted array
     * @param tmparray Temporary array to store the array
     * @param left storing left index of array
     * @param right storing right index of array
     */

    public ConcurrentMergeSort(int[] a, int[] tmpArray, int left, int right){
        this.a = a;
        this.tmpArray = tmpArray;
        this.left = left;
        this.right = right;
    }

    /**
     * comparing the left and right index by calling this Class again
     * And then splitting into two Array by calling this Class again
     * And then Merge these two sorted array by calling ConcurrentMerge Class
     */
    public void run(){
        if(this.left < this.right){
            try{
                int center = ( this.left + this.right ) / 2;
                ConcurrentMergeSort p = new ConcurrentMergeSort(this.a, this.tmpArray, this.left, center);
                ConcurrentMergeSort q = new ConcurrentMergeSort(this.a, this.tmpArray, center + 1, this.right);
                ConcurrentMerge r = new ConcurrentMerge(this.a, this.tmpArray, this.left, center + 1, this.right);

                // Sort
                p.start();
                q.start();
                p.join();
                q.join();

                // Merge
                r.start();
                r.join();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public int[] getA(){
        return this.a;
    }

}