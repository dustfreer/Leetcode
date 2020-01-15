package BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class BFS {

	static class Graph {
		private int V; // No. of vertices
		private LinkedList<Integer> adj[]; // Adjacency Lists

		// Constructor
		Graph(int v) {
			V = v;
			adj = new LinkedList[v];
			for (int i = 0; i < v; ++i)
				adj[i] = new LinkedList();
		}

		// Function to add an edge into the graph
		void addEdge(int v, int w) {
			adj[v].add(w);
		}

		// prints BFS traversal from a given source s
		void BFS(int s) {
			// Mark all the vertices as not visited(By default
			// set as false)
			boolean visited[] = new boolean[V];

			// Create a queue for BFS
			LinkedList<Integer> queue = new LinkedList<Integer>();

			// Mark the current node as visited and enqueue it
			visited[s] = true;
			queue.add(s);

			while (queue.size() != 0) {
				// Dequeue a vertex from queue and print it
				s = queue.poll();
				System.out.print(s + " ");

				// Get all adjacent vertices of the dequeued vertex s
				// If a adjacent has not been visited, then mark it
				// visited and enqueue it
				Iterator<Integer> i = adj[s].listIterator();
				while (i.hasNext()) {
					int n = i.next();
					if (!visited[n]) {
						visited[n] = true;
						queue.add(n);
					}
				}
			}
		}

		public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
			boolean[][] visited = new boolean[maze.length][maze[0].length];
			Queue<int[]> queue = new LinkedList<int[]>();
			queue.add(start);
			visited[start[0]][start[1]] = true;

			int[] dir1 = { 1, -1, 0, 0 };
			int[] dir2 = { 0, 0, 1, -1 };
			while (!queue.isEmpty()) {
				int[] s = queue.poll();
				System.out.println();
				if (s[0] == destination[0] && s[1] == destination[1])
					return true;
				for (int i = 0; i < 4; i++) {
					int x = s[0] + dir1[i];
					int y = s[1] + dir2[i];
					if (x >= 0 && y >= 0 && x <= maze.length - 1 && y <= maze[0].length - 1) {
						if (maze[x][y] == 0 && visited[x][y] == false) {
							System.out.print(" {" + x + "," + y + "}");
							queue.add(new int[] { x, y });
							visited[x][y] = true;
						}
					}

				}
			}
			return false;

		}

		public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
			Set<String> set = new HashSet<String>(wordList);
			set.remove(beginWord);
			Queue<String> q = new LinkedList<String>();
			q.add(beginWord);

			int count = 0;
			while (!q.isEmpty()) {
				System.out.println(q.toString());
				Queue<String> p = new LinkedList<String>();
				while (!q.isEmpty()) {
					String cur = q.poll();
					if (cur.equals(endWord))
						return count+1;		
					
					char[] curChar = cur.toCharArray();
					for (int i = 0; i < curChar.length; i++) {
						char temp = curChar[i];
						for ( char c = 'a'; c <= 'z'; c++ ) {
							curChar[i] = c;
							if ( set.contains(new String(curChar))) {
								p.add(new String(curChar));
								set.remove(new String(curChar));
							}
						}
						curChar[i] = temp;
							
					}
				}
                if ( !p.isEmpty()){
                    count ++;
				    q = p;
                }
			}
			return count;
		}
		
		
		
		static int[][] direction = {{-1,0},{1,0},{0,1},{0,-1}};
		static int m, n;
		static int[][] matrix;
	    public static int cutOffTree(List<List<Integer>> forest) {
	        m = forest.size();
	        n = forest.get(0).size();
	        matrix = new int[m][n];
	        
	        List<int[]> nums = new ArrayList<int[]>();
	        
	        int i = 0, j = 0;
	        for ( List<Integer> l : forest){
	            for ( int num : l){
	                if ( num > 0)
	                    nums.add(new int[] { num, i, j });
	                matrix[i][j++] = num;
	            }
	            i++;
	            j = 0;
	        }
	        Collections.sort(nums, (a,b) -> a[0] - b[0]);
	        i = 0;
	        j = 0;
	        int result = 0;
	        for ( int[] num: nums ){
	            int ti = num[1];
	            int tj = num[2];
	            int path = bfs(i,j,ti,tj);
	            if ( path == -1){
	                return -1;
	            }
	            result += path;
	            i = ti;
	            j = tj;
	        }
	        return result;
	    }
	    
	    public static int bfs(int i, int j, int ti, int tj){
	        Queue<int[]> q = new LinkedList<int[]>();
	        boolean[][] visited = new boolean[m][n];
	        q.offer(new int[] {i,j,0}); visited[i][j] = true;
	        
	        while ( !q.isEmpty() ){
	            int[] cur = q.poll();
	            if ( cur[0] == ti && cur[1] == tj){
	                return cur[2];                
	            }
	            for ( int k = 0; k < direction.length; k++){
	                int x = cur[0] + direction[k][0];
	                int y = cur[1] + direction[k][1];
	                if( x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] != 0 && !visited[x][y]){
	                    q.offer(new int[] {x,y,cur[2]+1});
	                    visited[x][y] = true;
	                }
	            }
	        }
	        return -1;
	    }    
	    
	    
	    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
	        List<Integer> result = new ArrayList<>();
	        if ( edges.length == 0 ) return result;
	        
	        Queue<Integer> q = new LinkedList<>();
	        
	        int count = 0, min = Integer.MAX_VALUE;
	        for ( int i = 0; i < n; i++){
	            boolean[] visited = new boolean[n];
	            q.offer(i);
	            visited[i] = true;
	            while ( !q.isEmpty() ){
	            	int size = q.size();
	                for ( int j = 0; j < size; j++ ){
	                    int temp = q.poll();
	                    System.out.print(temp+" ");
	                    for ( int row = 0; row < edges.length; row++ ){
	                        if ( edges[row][0] == temp ){
	                            if ( !visited[edges[row][1]] ){
	                                q.offer(edges[row][1]);
	                                visited[edges[row][1]] = true;
	                            }
	                        }else if ( edges[row][1] == temp ){
	                            if ( !visited[edges[row][0]] ){
	                                q.offer(edges[row][0]);
	                                visited[edges[row][0]] = true;
	                            }
	                        }
	                    }
	                }
	                System.out.println();
	                count++;                                
	            }
	            if ( min > count){
	                min = count;
	                result.clear();
	                result.add(i);
	            }else if ( min == count) {
	            	result.add(i);
	            }
                count = 0;
	        }
	        return result;
	    }

		public static void main(String args[]) {	
			int[][] edges = {{0,3},
							 {1,3},
							 {2,3},
							 {4,3},
							 {5,4}};
			System.out.println(findMinHeightTrees(6, edges).toString());
			
		}

	}

}
