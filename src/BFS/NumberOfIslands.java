package BFS;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class NumberOfIslands {
	class Coordinate {
        int x;
        int y;
        Coordinate (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int numIslands(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        
        
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!visited[i][j] && matrix[i][j] == '1') {
                    bfs(matrix, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void bfs(char[][] matrix, int x, int y, boolean[][] visited) {
        
        int[] directionX = {-1, 1, 0, 0};
        int[] directionY = { 0, 0,-1, 1};
        
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(x,y));        
        visited[x][y] = true;
        
        while (!queue.isEmpty()) {
        	Coordinate coordinate = queue.poll();
            
            for (int i = 0; i < 4; i++) {
            	Coordinate cur = new Coordinate(
                    coordinate.x + directionX[i],
                    coordinate.y + directionY[i]                
            	);
                
                if (!isSafe(cur, matrix, visited)) {
                    continue;
                }
                queue.add(cur);
                visited[cur.x][cur.y] = true;
            }
        }
        
    }
    
    public boolean isSafe(Coordinate coordinate, char[][] matrix, boolean[][] visited) {
        if (coordinate.x < 0 || coordinate.y < 0 || coordinate.x >= matrix.length 
            || coordinate.y >= matrix[0].length || visited[coordinate.x][coordinate.y] 
            || matrix[coordinate.x][coordinate.y] == '0')
            return false;
        return true;
    }
	
	public static void main(String[] args) {
		NumberOfIslands n = new NumberOfIslands();
		
		char[][] matrix = {{'1','1','1','1','0'},
							{'1','1','0','1','0'},
							{'1','1','1','1','0'},
							{'0','0','0','0','0'}};
		n.numIslands(matrix);
		
		Map<Integer, List<Integer>> graph = new HashMap<>();
		graph.values();
	}
}
