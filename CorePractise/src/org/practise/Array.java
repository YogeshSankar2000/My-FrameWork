package org.practise;

public class Array {
	public static void main(String[] args) {
		// 10 1 8 6 
		// 4 2 15 10
		// 7 1 3 9
		int a[][] = { { 10, 7, 8, 6 }, { 4, 2, 15, 10 }, { 7, 4, 3, 4 } };
		int min = a[0][0];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length+1; j++) {
				if (a[i][j] < min) {
					min = a[i][j];
				}
			}
		}
		int max = 0;
		boolean flag = false;
		System.out.println(min+" Min value of matrix");
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length+1; j++) {
				if (a[i][j] == min) {
					int[] b = a[i];
					for (int k = 0; k < b.length; k++) {
						if (a[i][k] > max) {
							max = a[i][k];
						}
					}
					flag = true;
				}
			}
			if(flag) {
				break;
			}
		}
		System.out.println(max+" Max value of row where  min is present");
		int max1 = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length+1; j++) {
				if (a[i][j] == min) {
					int b[] = new int[a.length];
					for (int k = 0; k < a.length; k++) {
						b[k] = a[k][j];
					}
					for(int k=0;k<b.length;k++) {
						if(max1<b[k]) {
							max1=b[k];
						}
					}
					flag = false;
				}
				if(!flag) {
					break;
				}
			}
		}
		System.out.println(max1+" Max value of col where min is present");
		int d1[] = {2,0,4,7,0,8,9,0,5,5}; int count = 1;
	}
}




