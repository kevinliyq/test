package com.alu.study.algorithm;

import java.util.Arrays;

public class HeapSort {
	private static int heapsize = 0;
	public static void main(String[] args) {
	    //a sample input
	    int [] A = {3, 7, 2, 11, 3, 4, 9, 2, 18, 0};
	    System.out.println("Input: " + Arrays.toString(A));
	    
	    heapsort(A);
	    System.out.println("Output: " + Arrays.toString(A));

	}
	
	public static void heapsort(int[] A){
		heapsize = A.length;
		//1. build the max heap, and get the biggest value at index 0 (this is root node)
	    buildMaxHeap(A);
	    
	    int step = 1;
	    //2. move the biggest to a[N-1], then a[0] to a[N-2] is not more max heap
	    //then need to rebuild from root node
	    for (int i = A.length - 1; i > 0; i--) {
	        int temp = A[i];
	        A[i] = A[0];
	        A[0] = temp;
	        heapsize--;
	        System.out.println("Step: " + (step++) + Arrays.toString(A));
	        maxHeapify(A,0);
	    }        
	}
	public static void buildMaxHeap(int [] A){	   
	    for (int i = parent(heapsize - 1); i >= 0; i--)
	        maxHeapify(A,i);        
	}
	private static void maxHeapify(int[] A, int i){
	    int l = left(i);
	    int r = right(i);
	    int largest = i;
	    if (l <= heapsize - 1 && A[l] > A[i])
	        largest = l;
	    if (r <= heapsize - 1 && A[r] > A[largest])
	        largest = r;
	    if (largest != i) {
	        int temp = A[i];
	        // swap
	        A[i] = A[largest];
	        A[largest] = temp;
	        maxHeapify(A,largest);
	    }
	}
	
	private static int parent(int i) {return (i - 1) / 2;}
	private static int left(int i) {return 2 * i + 1;}
	private static int right(int i) {return 2 * i + 2;}
}