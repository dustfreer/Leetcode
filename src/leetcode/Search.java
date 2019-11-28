package leetcode;

public class Search {

	public static int search(int[] a, int start, int end, int n) {
		if ( start > end) return -1;
		
		int i = (start + end)/2;
		if ( a[i] == n) 
			return i;
		else if ( a[i] > n)
				return search(a,start, i-1, n);
		else 
			return search(a, i+1, end, n);
	}
	
	public static void quicksort(int[] a, int low, int high) {
		if (low < high) {
		int p = partition(a, 0, high);
		quicksort(a, 0, p-1);
		quicksort(a, p+1, high);
		}
	}
	
	public static int partition(int[] a, int low, int high) {
		int pivot = a[high];
		int i = low -1;
		for ( int j = 0; j <= high-1; j++) {
			if ( a[j] < pivot) {
				i++;
				swap(a, i,j);
			}
		}
		swap(a, i+1, high);
		return i+1;
	}
	
	public static void swap( int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void main(String[] args) {
//		int[] a = {0,2,5,6,8,10,11};
//		System.out.println(search(a,0,a.length,8));
//		//System.out.println(1/2);
//		
//		int[] b= {2,6,10,1,3,16,4};
//		quicksort(b, 0, b.length-1);
//		for ( int n : b)
//			System.out.print(n+" ");
//		
//		double aa= 3.0, bb=3.00;
//		if (aa == bb) {
//			System.out.print(" \n11");
//		}
		
		int n = 10;
		System.out.println(10>>1);
	}
}
