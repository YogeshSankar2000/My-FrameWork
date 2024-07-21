package org.practise;

public class Array2 {
	public static void main(String[] args) {
		int a[] = { 4, 6, 9, 3, 8, 2, };
		int even = 0;
		int odd = 0;
		for (int b : a) {
			if (b % 2 == 0) {
				even++;
			} else {
				odd++;
			}
		}
		System.out.println(even + " " + odd);
		System.out.println();
		System.out.println("Finding the diff bten smallest and larget element of array");
		int[] b = {23,45,34,60,98,67,88};
		for(int i=0;i<b.length;i++) {
			for(int j=i+1;j<b.length;j++) {
				int temp = b[i];
				if(b[j]>b[i]) {
					b[i] = b[j];
					b[j] = temp;
				}
			}
		}
		System.out.println(b[0]-b[b.length-1]);
		System.out.println("==============");
		int sum = 0;
		System.out.println("average value of an array of integers except the largest and smallest values.");
		for(int i = 1;i<b.length-1;i++) {
			sum = sum + b[i];
		}
		System.out.println(sum/b.length);
		System.out.println("==========");
		System.out.println(" check if an array of integers is without 0 and -1.");
		int x[] = {2,3,4,9,5}; boolean flag = true;
		for(int y : x) {
			if(y==0||y<0) {
				flag = false;
				break;
			}
			flag = true;
		}
		System.out.println(flag);
	}
}
