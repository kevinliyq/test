package com.alu.study.algorithm;

import java.util.Arrays;

public class MergeSort {
	private static int number = 0;
	public static void main(String[] args) {
//		int[] a = { 1, 3, 5, 7, 9, 11, 14, 16, 18 ,20};
//		int[] b = { 2, 4, 6, 8, 8, 10, 12, 13, 15, 15, 17, 19 };
//		int[] target = new int[a.length + b.length];

		int[] notOrderArray = { 8, 3, 4, 10, 6, 9, 7, 8 };
		//target = mergeSort(a, 0, a.length - 1, b, 0, b.length -1);
		//print(target);
		print("Ori",notOrderArray);
		Sort(notOrderArray, 0, notOrderArray.length - 1);
		print("sorted",notOrderArray);
		// sort(a,b,target);
		
	}

//	private static void sort_merge(int[] array, int lo, int hi) {
//		if(lo >= lo)
//			return;
//		
//		int center = (hi + lo) / 2;
//		System.out.println("Center="+center);
//		if (center <= 1) {
//			return array;
//		}
//		//System.arraycopy(src, low, dest, destPos, length);
//		sort_merge(array, lo, center);
//		sort_merge(array, center + 1, hi);
//		//mergeSort(left, lo, center, right, center + 1, hi);
//		return target;
//	}
	
	private static void Sort(int[] a, int left, int right) {
        if(left>=right)
            return;
    
        int mid = (left + right) / 2;
        
        Sort(a, left, mid);
        Sort(a, mid + 1, right);
        merge(a, left, mid, right);

    }


    private static void merge(int[] a, int left, int mid, int right) {
        int[] target = new int[right-left+1];
        int left_lo = left;
        int left_hi = mid;
        int right_lo = mid + 1;
        int right_hi = right;
        
        int index = 0;
        
        while(left_lo <= left_hi && right_lo <= right_hi) {
            if (a[left_lo] <= a[right_lo]) 
            	target[index++] = a[left_lo++];
            else
            	target[index++] = a[right_lo++];
        }
            while (left_lo <= left_hi) {
            	target[index++] = a[left_lo++];
            }
            while ( right_lo <= right_hi ) {
            	target[index++] = a[right_lo++];
            }
            
            System.arraycopy(target, 0, a, left, right-left+1);
            
            System.out.println("Number "+(++number)+" order:\t");
            print(a,left,mid,right);
            System.out.println(Arrays.toString(a));
        }
    


    private static void print(int[] target,int lo, int mid, int hi) {
    	StringBuffer buf = new StringBuffer("");
		for (int i = lo; i <= mid; i++) {
			buf.append(target[i]);
			buf.append(" ");
		}
		System.out.print(buf.toString());
		
		buf = new StringBuffer(" | ");
		for (int i = mid+1; i <= hi; i++) {
			buf.append(target[i]);
			buf.append(" ");
		}
		System.out.println(buf.toString());

	}
	private static void print(String message,int[] target) {
		System.out.print(message+":");
		for (int i = 0; i < target.length; i++) {
			System.out.print(target[i] + " ");
		}
		System.out.println("");

	}

	
//	public static void sort(int[] a, int[] b, int[] c) {
//
//		int aIndex = 0;
//		int bIndex = 0;
//		int cIndex = 0;
//
//		while (true) {
//			if (aIndex >= a.length || bIndex >= b.length) {
//				break;
//			}
//			if (a[aIndex] > b[bIndex]) {
//				c[cIndex++] = b[bIndex++];
//			} else if (a[aIndex] < b[bIndex]) {
//				c[cIndex++] = a[aIndex++];
//			} else {
//				c[cIndex++] = a[aIndex++];
//				bIndex++;
//			}
//		}
//
//		if (aIndex < a.length) {
//			System.arraycopy(a, aIndex, c, cIndex, a.length - aIndex);
//		}
//		if (bIndex < b.length) {
//			System.arraycopy(b, bIndex, c, cIndex, b.length - bIndex);
//		}
//
//	}
}
