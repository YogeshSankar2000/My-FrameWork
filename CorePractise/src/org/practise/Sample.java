package org.practise;

public class Sample {
	public static void main(String[] args) {
		int a = 1;
		for(int i=0;i<4;i++) {
			for(int j=0;j<(4-i);j++) {
				System.out.print(a+" ");
				a++;
			}
			System.out.println();
		}
		System.out.println("**********");
		int b = 1;
		for(int i=0;i<4;i++) {
			for(int j=0;j<(i+1);j++) {
				System.out.print(b+" ");
				b++;
			}
			System.out.println();
		}
		System.out.println("**********");
		int c = 1;
		for(int i=0;i<4;i++) {
			for(int j=0;j<(i+1);j++) {
				System.out.print(c+" ");
				c++;
			}
			System.out.println();
			c = 1;
		}
		System.out.println("**********");
		int d = 3;
		for(int i=0;i<3;i++) {
			for(int j=0;j<(i+1);j++) {
				System.out.print(d+" ");
				d = d+3;
			}
			System.out.println();
		}
		System.out.println("**********");
	}
}
