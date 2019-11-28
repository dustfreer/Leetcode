package leetcode;

import java.io.*;
import java.util.*;

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
	        
	        int[] dir1 = {1,-1,0,0};
	        int[] dir2 = {0,0,1,-1};
	        while( !queue.isEmpty() ) {
	        	int[] s = queue.poll();
	        	System.out.println();
	        	if ( s[0] == destination[0] && s[1] == destination[1])
	        		return true;
	        	for ( int i = 0; i < 4; i++) {
	        		int x = s[0] + dir1[i];
	        		int y = s[1] + dir2[i];
	        		if (x >= 0 && y >= 0 && x <= maze.length-1 && y <= maze[0].length-1 ){
                        if ( maze[x][y] == 0 && visited[x][y] == false){
                        	System.out.print(" {" + x + "," +y + "}");
	        		        queue.add(new int[] {x,y});
	        		        visited[x][y] = true;
                        }
                    }
	        			
	        	}
	        }
	        return false;
	        
	    }
		public static void main(String args[]) {
			Graph g = new Graph(4);

			g.addEdge(0, 1);
			g.addEdge(0, 2);
			g.addEdge(1, 2);
			g.addEdge(2, 0);
			g.addEdge(2, 3);
			g.addEdge(3, 3);

			//System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");

			//g.BFS(2);
			int[][] maze = {{0,0,0,0,0},
							{1,1,0,0,1},
							{0,0,0,0,0},
							{0,1,0,0,1},
							{0,1,0,0,0}};
			hasPath(maze,new int[] {4,3}, new int[] {0,1});
			PriorityQueue<Integer> l = new PriorityQueue<Integer>();
		}

	}
	

}
