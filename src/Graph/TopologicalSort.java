package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TopologicalSort {
	public static boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        
        for (int i = 1; i <= org.length; i++) {
            edges.put(i, new HashSet<>());
            indegree.put(i, 0);
        }
        
        for (List<Integer> list : seqs) {
            for (int i = 1; i < list.size(); i++) {
                if (edges.get(list.get(i-1)).add(list.get(i))) {
                    indegree.put(list.get(i), list.get(i)+1);
                }
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        int count = 0;
        for (int i = 0; i < org.length; i++) {
            if (indegree.get(org[i]) == 0) {
                queue.offer(org[i]);                
            }
        }
        while (queue.size() == 1) {
            int cur = queue.poll();
            if (cur != org[count]) {
                return false;
            }
            for (Integer n : edges.get(cur)) {
                indegree.put(n, indegree.get(n)-1);
                if (indegree.get(n) == 0) {
                    queue.offer(n);                    
                }
            }
            count ++;
        }
        return count == org.length;
    }
	
	public static void main(String[] args) {
		List<List<Integer>> list = new ArrayList<>();
		list.add(Arrays.asList(1,2));
		list.add(Arrays.asList(1,3));
		list.add(Arrays.asList(2,3));
		
		sequenceReconstruction(new int[] {1,2,3}, list);
	}
}
