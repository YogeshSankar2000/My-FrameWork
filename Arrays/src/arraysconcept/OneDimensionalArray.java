package arraysconcept;

public class OneDimensionalArray {
	public static void main(String[] args) {
		int[] a = new int[5];
		a[0] = 1;
		a[1] = 2;
		a[2] = 3;
		a[3] = 4;
		a[4] = 5;
//		int[] a = {1,2,3,4,5};
		for(int i=0;i<=a.length-1;i++) {
			System.out.println(a[i]);
		}
		
		System.out.println("-----");
		
		for(int b:a) {
			System.out.println(b);
		}
	}
}
