package com.alu.study.algorithm;


public class QuickSort {
	private static int number = 0;
	
	public static void main(String[] args){
		int[] a = {10, 5, 13, 12, 11, 8, 15, 3, 8, 9, 2, 6, 4, 7, 1};
		//int[] a = {11, 8, 15, 3};
		print("ori:",a);
		int lo = 0;
		int hi = a.length - 1;
		
		quickSort(a,lo,hi);
		
		print("sorted:",a);
	}

	private static void quickSort(int[] a, int lo, int hi) {
		if(lo >= hi){
			return;
		}
		int key = a[lo];
		int i = lo;
		int j = hi;
		
		while(i < j){
			while(j > i && a[j] >= key){
				j --;
			}
			if(a[j] < key){
				int temp = a[j];
				a[j] = a[i];
				a[i] = temp;
			}
			
			while(i < j && a[i] <= key){
				i++;
			}
			if(a[i] > key){
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
			
		}
		
		print("Number "+(++number)+",key="+key+",i="+i+",j="+j+": ",a);
		
		quickSort(a,lo,i-1);
		quickSort(a,i+1,hi);
		
	}

	private static void print(String message, int[] a) {
		System.out.print(message);
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
}
