package leetcode;

public class DFS {

	public static int numberOfIslands(int[][] matrix) {
		if ( matrix.length == 0) return 0;
		
		int result = 0;
		boolean[][] isVisited = new boolean[matrix.length][matrix[0].length]; 
		for( int i = 0; i < matrix.length; i++)
			for ( int j = 0; j < matrix[0].length; j++) {
				if ( matrix[i][j] == 1 && !isVisited[i][j]) {
					DFS(isVisited, matrix, i , j);
					result++;
				}
			}
		return result;
	}
	
	public static boolean isSafe(boolean[][] isVisited, int[][] matrix, int r, int c) {
		int row = matrix.length;
		int column = matrix[0].length;
		if ( (r >= 0 && r < row) && (c >= 0 && c < column) && ( matrix[r][c] == 1 && !isVisited[r][c]))
			return true;
		else 
			return false;
	}
	
	public static void DFS(boolean[][] isVisited, int[][] matrix, int r, int c) {
		int[] row =    {-1,1,0, 0,1, 1,-1,-1};
		int[] column = { 0,0,1,-1,1,-1, 1,-1};
	
		isVisited[r][c] = true;
		for ( int i = 0; i < 8; i++) {
			if ( isSafe(isVisited, matrix, r + row[i], c + column[i]) )
				DFS(isVisited, matrix, r + row[i], c + column[i]);
		}
	}
	
	
	public static int numberOfIslands1(int[][] matrix) {
		int r = matrix.length;
		if ( r == 0) return 0;
		int c = matrix[0].length;
		
		int result = 0;
		for( int i = 0; i < r; i++)
			for ( int j = 0; j < c; j++) {
				if ( matrix[i][j] == 1 ) {
					DFS1(matrix, i , j);
					result++;
				}
			}
		return result;
	}
	
	public static void DFS1(int[][] matrix, int r, int c) {
		int row = matrix.length;
		int col = matrix[0].length;
		
		if ( r < 0 || r == row || c < 0 || c == col || matrix[r][c] == 0)
			return;
		matrix[r][c] = 0;
		DFS1(matrix, r-1,c);
		DFS1(matrix, r+1,c);
		DFS1(matrix, r,c-1);
		DFS1(matrix, r,c+1);
	}
	
	
	public static void main(String[] args) {
		int[][] matrix = {{1,1,0,0,0},
						  {1,1,0,0,0},
						  {0,0,1,0,0},
						  {0,0,0,0,0},
						  {0,0,0,1,1}};
		System.out.println(numberOfIslands1(matrix));
		
	}
}
