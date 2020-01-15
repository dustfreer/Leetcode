package Sort;

import java.util.Arrays;

public class ArraySort {
	/*
	 * mergeSort - time complexity O(nlogn) space complexity O(n);
	 */
	public void mergeSort(int[] nums) {
		mergeSort(nums, 0, nums.length-1);
	}
	public void mergeSort(int[] nums, int start, int end) {
		if ( start >= end ) return;
		int mid = (start + end) / 2;
		mergeSort(nums, start, mid);
		mergeSort(nums, mid+1, end);
		merge(nums, start, end);
	}
	
	public void merge(int[] nums, int start, int end) {
		int[] temp = new int[end-start+1];
		
		int mid = (start + end) / 2;
		int i = start, j = mid + 1, index = 0;
		while ( i <= mid && j <= end) {
			if ( nums[i] < nums[j]) {
				temp[index++] = nums[i++];
			}else {
				temp[index++] = nums[j++];
			}
		}
		while ( i <= mid ) {
			temp[index++] = nums[i++];
		}		
		while ( j <= end ) {
			temp[index++] = nums[j++];
		}
		for ( i = start, index = 0; i <= end; i++) {
			nums[i] = temp[index++];
		}		
	}
	/*
	 * QuickSort - time complexity O(nlogn) space complexity O(n);
	 */
	public void quickSort(int[] nums) {
		quickSort(nums, 0, nums.length-1);
	}
	public void quickSort(int[] nums, int start, int end) {
		if ( start < end) {
			int partition = partition(nums, start, end);
			quickSort(nums, start, partition-1);
			quickSort(nums, partition+1, end);
		}
	}
	public int partition(int[] nums, int start, int end) {
		int pivot = nums[end];
		int j = start - 1;
		for ( int i = start; i < end; i++) {
			if ( nums[i] < pivot) {
				j++;
				int temp = nums[j];
				nums[j] = nums[i];
				nums[i] = temp;
			}
		}
		int temp = nums[j+1];
		nums[j+1] = nums[end];
		nums[end] = temp;
		return j+1;
	}
	
	/*
	 * PancakeSort
	 */
	public void PancakeSort(int[] arr){
        if(arr.length == 0 || arr.length ==1)
            return;
        
        for(int i = 0 ; i < arr.length ;i ++){

            int maxIndex = GetMaxIndex(arr, i, arr.length );

            if(maxIndex != i){
                Reverse(arr, i, maxIndex);
            }
            Reverse(arr, i, arr.length -i);
        }

    }
	public int GetMaxIndex(int[] arr, int start, int end){
        if(start <0 || end < 0 || start > arr.length || end > arr.length )
            return -1;
        int index = start;
        int maxIndex = start;
        while(index < end){
            if(arr[index] > arr[maxIndex])
                maxIndex = index;
            index ++;
        }
        return maxIndex;
    }
    public void Reverse(int[] arr, int start, int end){

        if(start <0 || end < 0 || start > arr.length || end > arr.length )
            return;

        while(start < end){
        	int temp = arr[start];
        	arr[start] = arr[end];
        	arr[end] = temp;
            start ++;
            end --;
        }
    }
    
    /*
     * count sort;
     */    
    public void CountSort(int[] arr){ //count every item's amount
        int[] count = new int[10];

        for(int i = 0 ; i < arr.length; i ++){
            count[arr[i]] ++;
        }
        int index = 0;
        for(int i = 0 ; i < 10 ; i++){
            while(count[i] > 0){
                arr[index] = i;
                index ++;
                count[i] --;
            }
        }
    }
	public static void main(String[] args) {
		ArraySort test = new ArraySort();
		int[] nums = {2,5,1,6,7,3,3,9};
		test.quickSort(nums);
		System.out.println(Arrays.toString(nums));
	}
}
