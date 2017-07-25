package com.alu.study.algorithm;

public class ShellSort {
	public static void main(String[] args){
		int[] a = {11, 8, 15, 3};
		//, 8, 9, 2, 6, 4, 7, 1
		print("ori:",a);
		
		shellSort(a);
		print("sorted:",a);
	}
	
	/**
     * Shell sort
     *
     * @param num
     */
    public static void shellSort(int num[]) {
        int temp;
        int step = num.length;
        while (true) {
            step = step / 2;
            for (int i = 0; i < step; i++) {
                for ( int j = i + step; j < num.length; j = j + step) {
                    temp=num[j];
                    int k;
                   for( k=j-step;k>=0;k=k-step){
                       if(num[k]>temp){
                           num[k+step]=num[k];
                       }else{
                           break;
                       }
                   }
                   num[k+step]=temp;
                }
            }
            if (step == 1) {
                break;
            }
        }
    }

	private static void print(String message, int[] a) {
		System.out.print(message);
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
}
