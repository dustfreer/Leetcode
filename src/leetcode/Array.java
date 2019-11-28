package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Array {
	public static int[][] merge(int[][] intervals) {
        if ( intervals.length <= 1) return intervals;
        
        List<int[]> l = new ArrayList<int[]>();        
        for ( int i = 0; i < intervals.length; i++){
        	int[] temp = new int[2];
        	temp[0] = intervals[i][0];
        	temp[1] = intervals[i][1];
            l.add(temp);
        }
        Collections.sort(l, (o1,o2) -> o1[0] - o2[0] );
        
        for( int i = 1; i < l.size(); i++) {        	
        	if ( l.get(i)[0] <= l.get(i-1)[1] ) {
        		if ( l.get(i)[1] > l.get(i-1)[1] )        	
        			l.get(i-1)[1] = l.get(i)[1];
        		l.remove(i);
        		i--;
        	}        	
        }
        
        int[][] result = new int[l.size()][2];
        int i = 0;
        for ( int[] temp : l) {
        	result[i++] = temp;
        }
        return result;
    }
	
	public static int[][] highFive(int[][] items) {
        List<int[]> result = new ArrayList<int[]>();
        Arrays.sort(items, (o1,o2) -> {
			if ( o1[0] != o2[0])
				return o1[0] - o2[0];
			else
				return o2[1] - o1[1];
		});
        for ( int i = 0; i < items.length-1; i++ ){
            int n0 = items[i][0];
            int count = 5;
            int[] student = new int[2];
        	student[0] = n0;
            for ( ; i < items.length; i ++) {
            	if ( items[i] != items[i+1])
            		break;
            	if ( count > 0) {
            		int n1 = items[i][1];
            		student[1] += n1;
            	}
            	count --;
            	
            }
            student[1] /= 5;
            result.add(student);
        }
        
        return result.toArray(new int[result.size()][2]);
    }

	public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        for( int i = 0, j = 0, k = 0; j < arr1.length; ){
            if ( arr1[i] == arr2[k]){
                int temp = arr1[i];
                arr1[i] = arr1[j];
                arr1[j] = temp;
                j++;
            }
            if ( i == arr1.length-1){
                k++;
                i = j;
            }else
            	i++;
            if ( k == arr2.length) {
            	for ( ; i < arr1.length; i++)
            		for ( j = i+1; j < arr1.length; j++) {
            			if ( arr1[i] > arr1[j]){
                            int temp = arr1[i];
                            arr1[i] = arr1[j];
                            arr1[j] = temp;
            			}
            		}
                break;
            }
        }
        return arr1;
    }
	
	public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        int count = 1;
        for ( int i = 1; i < arr.length; i++){
            int subtraction = arr[i] - arr[i-1];
            if ( min > subtraction ){
                min = subtraction;
                count = 1;
            }else{
                count ++;
            }
        }
        int[][] temp = new int[count][2];
        for ( int i = 1,j = 0; i < arr.length; i++){
            int subtraction = arr[i] - arr[i-1];
            if ( min == subtraction ){
                temp[j][0] = arr[i-1];
                temp[j][1] = arr[i];
                j++;
            }
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> l = new ArrayList<Integer>();
        for ( int[] n : temp){
        	l = new ArrayList<Integer>();
            l.add(n[0]);
            l.add(n[1]);
            result.add(l);
        }
        return result;
    }
	
	public static void quicksort(int[] arr) {
		int start = 0;
		int end = arr.length-1;
		quicksort(arr, start, end);
	}
	
	public static void quicksort(int[] arr, int start, int end) {
		
		
		if ( start < end) {
			int partition = partition(arr, start, end);
			quicksort(arr, start, partition - 1);
			quicksort(arr, partition + 1, end);
		}
	}
	
	public static int partition(int[] arr, int start, int end) {
		int partition = end;
		int j = start-1;
		for ( int i = start; i < end; i++) {
			if ( arr[i] < arr[partition]) {
				j++;
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
			}
		}
		int temp = arr[j+1];
		arr[j+1] = arr[partition];
		arr[partition] = temp;
		return j+1;
	}
	
	
	public static List<String> commonChars(String[] A) {
        int[] letters = new int[26];
        
        for ( char ch : A[0].toCharArray()){
            letters[ch-'a']++;
        }
        
        for ( int i = 1; i < A.length; i++){
            int[] temp = letters.clone();
            for ( char ch : A[i].toCharArray()){
                temp[ch-'a']--;
                if ( temp[ch-'a'] < 0){
                    letters[ch-'a'] = 0;
                    break;
                }
            }
        }
        List<String> result = new ArrayList<String>();
        for ( int i = 0; i < 26; i++){
            int n = letters[i];
            while( n > 0){
                result.add(String.valueOf((char)(i +'a')));
                n--;
            }
        }
        return result;
    }
	
	
	public static int numRookCaptures(char[][] board) {
        int i = 0;
        int j = 0;
        for ( i = 0; i < 8; i++)
            for ( j = 0; j < 8; j++){
                if ( board[i][j] == 'R' )
                    break;
            }
        
        int count = 0;
        int x = i;
        while ( x >= 0 ){
            if ( board[x][j] == 'B')
                break;
            if ( board[x][j] == 'p')
                count++;
            x--;
        }
        x = i;
        while ( x < 8 ){
            if ( board[x][j] == 'B')
                break;
            if ( board[x][j] == 'p')
                count++;
            x++;
        }
        int y = j;
        while ( y >= 0 ){
            if ( board[i][y] == 'B')
                break;
            if ( board[i][y] == 'p')
                count++;
            y--;
        }
        
        y = j;            
        while ( y < 8 ){
            if ( board[i][y] == 'B')
                break;
            if ( board[i][y] == 'p')
                count++;
            y++;
        }
        return count;
    }
	
	public static void main(String[] args) {
		
	}
		

	
	
	
}
