package MyClass;

/**
 * Created by rk on 11/9/16.
 */

/**
 * Merging the two array in sorted order
 */
public class ConcurrentMerge extends Thread{
    private int[] a;
    private int[] tmpArray;
    private int leftPos;
    private int rightPos;
    private int rightEnd;

    public ConcurrentMerge(int[] a, int[] tmpArray, int leftPos, int rightPos, int rightEnd){
        this.a = a;
        this.tmpArray = tmpArray;
        this.leftPos = leftPos;
        this.rightPos = rightPos;
        this.rightEnd = rightEnd;
    }

    public void run(){
        int leftEnd = rightPos - 1;
        int tmpPos  = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while( leftPos <= leftEnd && rightPos <= rightEnd )
            if( a[ leftPos ] <= a[ rightPos ] )
                tmpArray[ tmpPos++ ] = a[ leftPos++ ];
            else
                tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        // Copy rest of the left half
        while( leftPos <= leftEnd )
            tmpArray[ tmpPos++ ] = a[ leftPos++ ];

        // Copy rest of the right half
        while( rightPos <= rightEnd )
            tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        // Copy tmpArray back
        for( int i = 0; i < numElements; i++){
            a[ rightEnd ] = tmpArray[ rightEnd-- ];
        }
    }

}
