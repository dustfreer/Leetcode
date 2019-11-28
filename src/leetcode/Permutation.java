package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Permutation {
	public static void permute(String input) {
    	Map<Character, Integer> map = new HashMap<Character, Integer>();
    	for ( char ch : input.toCharArray()) {
    		map.compute(ch, (key, val) -> (val == null) ?  1: val+1);
    	}

    	char[] result = new char[input.length()];
    	int[] count = {1,1,1,1};
    	help1(input.toCharArray(),result,count, 0);
    }

    public static void help1(char[] input, char[] result, int[] count, int level) {
    	if ( level == result.length) {
    		System.out.println(Arrays.toString(result));
    		return;
    	}
    	for ( int i = 0; i < input.length; i++) {
    		if (count[i] == 0)
    			continue;
    		count[i]--;
    		result[level] = input[i];
    		help1(input, result, count, level+1);
    		count[i]++;
    	}
    }
    
    
    
    public static List<String> letterCombinations(String digits) {
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        
        List<String> result = new ArrayList<String>();
        help(map, digits, "", result);
        return result;
    }
    
    public static void help(HashMap<Character, String> map, String digit, String cur, List<String> result){
        if ( digit.length() == 0){
            result.add(cur);
            return;
        }
        
        String l = map.get(digit.charAt(0));
        for ( int i = 0; i < l.length(); i++){
            cur += l.charAt(i);
            help(map, digit.substring(1), cur, result);
            cur = cur.substring(0,cur.length()-1);
        }
    }
    
    public static void permutation1(int[] input) {
        int[] arr = new int[3];
        permutation1Util(arr, input, 0, 0);
        
    }
    
    
    public static void permutation1Util(int[] arr, int[] input, int cur, int i) {
        if ( cur == arr.length ) {
        	System.out.println(Arrays.toString(arr));
        	return ;
        }
        if ( i == input.length)
        	return ;
        
        arr[cur] = input[i];
        permutation1Util(arr, input, cur+1, i+1);
        while ( i+1 < input.length && input[i] == input[i+1])
        	i++;
        permutation1Util(arr, input, cur, i+1);
        
    }
    
    
    public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
     
    	if (n <= 0 || n < k)
    		return result;
     
    	ArrayList<Integer> item = new ArrayList<Integer>();
    	dfs(n, k, 1, item, result); // because it need to begin from 1
     
    	return result;
    }
     
    private static void dfs(int n, int k, int i, ArrayList<Integer> item,
    		ArrayList<ArrayList<Integer>> res) {
    	if (item.size() == k) {
    		res.add(new ArrayList<Integer>(item));
    		System.out.println(item.toString());
    		return;
    	}
    	
    	if ( i > n)
    		return;
     
    	item.add(i);
    	dfs(n, k, i + 1, item, res);
    	item.remove(item.size() - 1);
    	dfs(n, k, i + 1, item, res);
    }
    
    
    static void printSubsets(char set[]) 
    { 
        int n = set.length; 
  
        for (int i = 0; i < (1<<n); i++) 
        { 
            System.out.print("{ "); 
   
            for (int j = 0; j < n; j++)  
                if ((i & (1 << j)) > 0) 
                    System.out.print(set[j] + " "); 
  
            System.out.println("}"); 
        } 
    } 
    
	public static void main(String[] args) {		
		int[] input = {1,2,2,3,3,4,5};
		char set[] = {'a', 'b', 'c', 'd'}; 
		
		List<int[]> l = Arrays.asList(input); 
		//printSubsets(set);
		System.out.println(8>>4);
		
		int[] ints = {1, 2, 3};
		List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
	}

}
