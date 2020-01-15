package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	
	
	static int[][] direction = {{-1,0},{1,0},{0,1},{0,-1}};
    static int max = Integer.MIN_VALUE;
    public static int longestIncreasingPath(int[][] matrix) {
        if ( matrix.length == 0 ) return 0;
        int[][] memo = new int[matrix.length][matrix[0].length];
        for ( int i = 0; i < matrix.length; i++)
            for ( int j = 0; j < matrix[0].length; j++){
                if ( memo[i][j] != 0){
                    max = Math.max(max,memo[i][j]);
                }else
                    max = Math.max(max,dfs(matrix, i, j, memo));
            }
        return max;
    }
    
    public static int dfs(int[][] matrix, int i, int j, int[][] memo){
        int path = 0;
        for ( int k = 0; k < direction.length; k++){
            int x = i + direction[k][0];
            int y = j + direction[k][1];
            if ( x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] > matrix[i][j]){
                if (memo[x][y] != 0){
                    path = Math.max(path, memo[x][y]);
                }
                else{
                    path = Math.max(path, dfs(matrix, x, y,memo));
                    
                }
            }
                
        }
        memo[i][j] = path+1;
        return path+1;
    }
	
    
    static int m;
    static int n;
    public static void solve(char[][] board) {
        if ( board.length == 0 ) return;
        m = board.length;
        n = board[0].length;
        
        if ( m == 0 || n == 0 ) return;
        boolean[][] visited = new boolean[m][n];
        for ( int i = 0; i < n; i++){
            border(board, 0, i, visited);
            border(board, m-1,i,visited);            
        }
        for ( int i = 0; i < m; i++){
            border(board, i, 0, visited);
            border(board, i,n-1,visited);            
        }
        
        for ( int i = 1; i < m-1; i++ )
            for ( int j = 1; j < n-1; j++){
                if ( board[i][j] == 'O' && !visited[i][j]){
                    dfs(board, i, j,visited);
                }      
            }        
    }
    
    public static void border(char[][] board, int i, int j, boolean[][] visited){
        if ( i < 0 || i > m-1 || j < 0 || j > n-1 || board[i][j] == 'X' || visited[i][j])
            return;
        
        visited[i][j] = true;
        dfs(board, i+1, j, visited);
        dfs(board, i-1, j, visited);
        dfs(board, i, j+1, visited);
        dfs(board, i, j-1, visited);
            
    }
    
    public static void dfs(char[][] board, int i, int j, boolean[][] visited){
        if ( i < 1 || i >= m-1 || j < 1 || j >= n-1 || board[i][j] == 'X' || visited[i][j])
            return;
        board[i][j] = 'X';
        dfs(board, i+1, j, visited);
        dfs(board, i-1, j, visited);
        dfs(board, i, j+1, visited);
        dfs(board, i, j-1, visited);
            
    }
    
    
    static Set<String> result;
    public static List<String> findWords(char[][] board, String[] words) {
    	result = new HashSet<String>();
        if ( board.length == 0) return new ArrayList<String>(result);
        m = board.length;
        n = board[0].length;
                
        for ( int i = 0; i < m; i++)
            for ( int j = 0; j < n; j++){
            	boolean[][] visited = new boolean[m][n];
                dfs(board, i, j, words, "", visited); 
            }
        return new ArrayList<String>(result);
    }
    
    public static boolean dfs(char[][] board, int i, int j, String[] words, String cur, boolean[][] visited){
    	if ( i < 0 || i >= m || j < 0 || j >= n || visited[i][j] ) 
        	return false;
        
    	if ( cur != "") {
            boolean valid = false;
            for ( int k = 0; k < words.length; k++){
                if ( words[k].indexOf(cur) == 0 ){
                    valid = true;
                    if ( words[k].equals(cur) ){
                        result.add(cur);
                    }                
                }                        
            }
            if ( !valid ) {
                return false;
            }
    	}
        
    	cur += board[i][j];
        visited[i][j] = true;
        boolean found = dfs(board, i+1, j, words, cur, visited);
        if ( !found )
        	dfs(board, i-1, j, words, cur, visited);
        if ( !found )
        	dfs(board, i, j+1, words, cur, visited);
        if ( !found )
        	dfs(board, i, j-1, words, cur, visited);

        return false;  
        
        
    }
	
	public static void main(String[] args) {

//		char[][] board = {{'a','a','c'},
//				  		  {'a','e','d'},
//						  {'a','f','g'}};
		char[][] board = {{'a','a'}};
		System.out.println(findWords(board, new String[] {"aa"}).toString());
	}
}
