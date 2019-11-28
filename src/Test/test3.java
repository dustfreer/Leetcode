package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class test3 {
	public static int twoSumLessThanK(int[] A, int K) {
        if ( A.length <= 1) return -1;
        
        Arrays.sort(A);
        int max = -1;
        for ( int i = 0, j = A.length-1; i < j; ){
            int sum = A[i] + A[j];
            if ( sum < K ) {
                max = Math.max(max,sum);
                i++;
            }
            else
            	j--;
        }
        return max;
	}
	
	public static void quickSort(int[] n) {
		quickSort(n, 0, n.length-1);
	}
	public static void quickSort(int[] n, int start, int end) {
		
		if ( start < end ) {
			int partition = partition(n, start, end);
			
			quickSort(n, start, partition-1
					);
			quickSort(n, partition+1, end);
		}
		
	}
	
	public static int partition(int[] n, int start, int end) {
		int pivot = n[end];
		int i = start-1;
		for ( int j = start; j < end; j++) {
			if (n[j] < pivot ) {
				i++;
				swap(n,i,j);
			}
		}
		swap(n, i+1,end);
		return i+1;
	}
	
	public static void swap(int[] n, int i, int j) {
		int temp = n[i];
		n[i] = n[j];
		n[j] = temp;
	}
	
	public static String[] reorderLogFiles(String[] logs) {
        if ( logs.length <= 1) return logs;
        
        List<String> dig = new ArrayList<String>();
        List<String> let = new ArrayList<String>();      
        
        for ( int i = 0; i < logs.length; i++){
            String[] str = logs[i].split(" ", 0);
            char ch = str[1].charAt(0);            
            if ( Character.isDigit(ch) ) dig.add(logs[i]);
            else let.add(logs[i]); 
        }
        
        for ( int i = 0; i < let.size()-1; i++)
            for ( int j = i+1; j < let.size(); j++){
                if ( !isLessLetter(let.get(i), let.get(j)) ){
                    String temp = let.get(i);                    
                    let.set(i,let.get(j));
                    let.set(j,temp);
                }                    
            }
        
        System.out.println(let.toString());
        String[] result = new String[dig.size() + let.size()];
        int i = 0;
        for ( ; i < let.size(); i++){
            result[i] = let.get(i);
        }
        for ( int j = 0; j < dig.size(); j++){
            result[i++] = dig.get(j);
        }
        return result;
    }
    
    public static boolean isLessLetter(String s1, String s2){
    	String[] str1 = s1.split(" ", 0);
    	String[] str2 = s2.split(" ", 0);
    	int length = Math.min(str1.length, str2.length);
    	for ( int i = 1; i < length; i++) {
    		int length1 = Math.min(str1[i].length(), str2[i].length());
            for ( int j = 0; j< length1; j++){
            	char ch1 = str1[i].charAt(j);
            	char ch2 = str2[i].charAt(j);
                if ( ch1 < ch2 )
                    return true;
                else if ( ch1 > ch2 ) return false;
            }
            if ( str1[i].length() < str2[i].length() ) return true;
            else if ( str1[i].length() > str2[i].length()) return false;
    	}
        return (str1.length < str2.length ) ? true : false;
    }
	
    class compareString implements Comparator<String>{
    	
    	public int compare(String o1, String o2) {
    		return -1;
    	}
    	
    }
	
	public static void main(String[] args) {
		int[] A = {34,23,1,24,75,33,54,8};
		int k = 60;
		quickSort(A);
		
	}
}
