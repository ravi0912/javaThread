package MyClass;
/**
 * Created by rk on 11/9/16.
 * Merge Sort
 */


public class MergeSort{
    /**
     * Declaring static array variable which will be passed from Main Class
     */
    private static int[] toSort ;
//
    public MergeSort(int[] toSort){
        this.toSort = toSort;
    }

    public static void main(){

        // Threaded merge sort (and printing)
        //int[] toSort = {191,2,3,5,6,7,5,3,21,3,4};
        /**
         * Printing unsorted and then sorted array
         */
        printArr(toSort);
        concurrentMergeSort(toSort);
        printArr(toSort);
    }

    /**
     * Starting the Merge Sort in array which will be Linked to ConcurrentMergeSort Class
     * @param toSort
     */
    public static void concurrentMergeSort(int[] toSort){
        int[] tmpArray = new int[toSort.length];

        try{
            // Start the mergesort
            ConcurrentMergeSort sort = new ConcurrentMergeSort(toSort, tmpArray, 0, toSort.length - 1);
            sort.start();
            sort.join();

        } catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    /**
     * printing the array
     * @param a
     */

    public static void printArr(int[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ,");
        }
        System.out.println();
    }
}








