package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class test2 {
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if ( nums1.length == 0){
            int n1 = help(nums2, 0, nums2.length);
            if ( nums2.length % 2 == 0){
                int n2 = help( nums2, 0, nums2.length-1);
                return (n1+n2)/2.0;
            }else
                return n1/1.0;
        }else if ( nums2.length == 0){
             int n1 = help(nums1, 0, nums1.length);
            if ( nums1.length % 2 == 0){
                int n2 = help( nums1, 0, nums1.length-1);
                return (n1+n2)/2.0;
            }else
                return n1/1.0;
        }
		
		
		int start1 = 0;
	    int end1 = nums1.length-1;
	    int start2 = 0;
	    int end2 = nums2.length-1;
        int n1 = 0;
        int n2 = 0;
        
        while ( (end1-start1)>1 || (end2-start2)>1){
                n1 = help(nums1, start1, end1);
                n2 = help(nums2, start2, end2);
                if ( n1 == n2)
                    return n1/2.0;
                else if ( n1 < n2){
                    start1 = (start1+end1)/2 + 1;
                    end2 = (start2+end2)/2 - 1;
                }else{
                    end1 = (start1+end1)/2 - 1;
                    start2 = (start2+end2)/2 + 1;
                }
            }
        return (Math.max(nums1[start1], nums2[start2]) + Math.min(nums1[end1], nums2[end2])) / 2.0;
    }
    
        

    public static int help(int[] nums, int start, int end){
        int i = (start+end)/2;
        return nums[i];
    }
	
	public static void main(String[] args) {
		int[] nums1 = {1,3};
		int[] nums2 = {2,4,5};
		
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}

}
