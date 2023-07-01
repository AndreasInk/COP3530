package tester;

import priorityqueues.HeapSort;
import recursion.MedOfThreeNonRecQuickSort;
import recursion.MergeSort;

import java.util.Random;

public class TestHeapSort {
    public static void main(String[] args) {

        int n = 1000000;

        Random r = new Random();

        Integer[] A = new Integer[n];
        for(int i = 0; i < n; i++)
            A[i] = r.nextInt(n);

        long start = System.currentTimeMillis();
        HeapSort.sort(A);
        System.out.println("Heap sort time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        MergeSort.sort(A);
        System.out.println("Merge sort time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        MedOfThreeNonRecQuickSort.sort(A);
        System.out.println("Median of three Quick sort time: " + (System.currentTimeMillis() - start));

    }
}
