package BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class FindPeekElementII {
	/*
	 *@param: int[][] matrix
	 *@return : List<Integer>, index of peek;
	 *Algorithm: Binary search;
	 *1. binary search row, at this row, binary search to check col, 
	 *until find matrix[row][col] > matrix[row-1][col] and matrix[row][col] > matrix[row+1][col] 
	 *2. check row find matrix[row][col] > matrix[row][col+1] and matrix[row][col] > matrix[row][col-1]
	 *return result row and col; 
	 */
	
	public List<Integer> findPeekElement(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		if (matrix.length == 0 || matrix[0].length == 0) {
			return result;
		}
		
		int start = 1, end = matrix.length -1;
		int col = 0;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			col = findCol(mid, matrix);
			if (matrix[mid][col] < matrix[mid-1][col]) {
				end = mid;
			}else if (matrix[mid][col] < matrix[mid+1][col]) {
				start = mid;
			}else {
				result.add(mid);
				result.add(col);
				return result;
			}
		}
		// should find the col again, since don' know what row is;
		if (matrix[start][col] < matrix[end][col]) {
			result.add(end);
			result.add(findCol(end,matrix));
		}else {
			result.add(start);
			result.add(findCol(start,matrix));
		}
		return result;
	}
	
	public int findCol(int row, int[][] matrix) {
		int start = 0, end = matrix[0].length-1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (matrix[row][mid] < matrix[row][mid+1]) {
				start = mid;
			}else if (matrix[row][mid] < matrix[row][mid-1]) {
				end = mid;
			}else {
				return mid;
			}
		}
		if (matrix[row][start] < matrix[row][end]) {
			return end;
		}else {
			return start;			
		}
	}
	
	public static void main(String[] args) {
		FindPeekElementII test = new FindPeekElementII();
		int[][] matrix = {{1, 2,  3, 6,5},
						  {16,42,23,22,6},
						  {15,17,25,21,7},
						  {14,18,19,20,10},
						  {13,14,11,10,9}};
		System.out.print(test.findPeekElement(matrix).toString());
	}
}
