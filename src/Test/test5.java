package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;


public class test5 {
	
	public static int connectSticks(int[] sticks) {
		if ( sticks.length == 1) return 0;
        if ( sticks.length == 2) return sticks[0] + sticks[1];
        
        PriorityQueue<Integer> pq= new PriorityQueue<Integer>();
        for ( int n : sticks)
        	pq.add(n);
        
        int result = 0;
        while ( pq.size() > 1 ) {
        	int n1 = pq.poll();
        	int n2 = pq.poll();
        	result += n1+n2;
        	pq.add(n1+n2);
        }
        return result;
	}
	
	public static String countAndSay(int n) {
        String result = "1";
        for ( int i = 2; i <= n; i++){
            StringBuilder sb = new StringBuilder();
            int j = 0;
            while( j < result.length() ){                
                char c = result.charAt(j);
                int count = 1;
                while( j < result.length()-1 && result.charAt(j+1) == result.charAt(j)) {
                    count++;
                    j++;
                }
                sb.append((char) (count+'0'));
                sb.append(c);
                j++;
            }
            result = sb.toString();
        }
        return result;
    }
	
	
	static int count;
    static char ch = '0';
    public static boolean isMatch(String s1, String p1) {
        boolean[][] result = new boolean[s1.length()+1][p1.length()+1];
        
        result[0][0] = true;
        char[] s = s1.toCharArray();
        char[] p = p1.toCharArray();
        for ( int i = 1; i < result.length; i++) {
        	if ( p[i-1] == '*')
        		result[0][i] = result[0][i-2]; 
        }
        
        for ( int i = 1; i < result.length; i++)
        	for ( int j = 1; j < result[0].length; i++) {
        		if ( s[i-1] == p[j-1] || p[j-1] == '.') {
        			result[i][j] = result[i-1][j-1];
        		}else if ( p[j-2] == '*') {
        				result[i][j] = result[i][j-2];
        				if ( p[j-1] == '.' || s[i-1] == p[j-1])
        					result[i][j] = result[i][j] | result[i-1][j];
        		}else
        			result[i][j] = false;
        		
        	}
       return result[s1.length()+1][p1.length()+1];
    }
    
    public static boolean help(char[] s, char[] p, int i, int j, boolean[][] result){
    	if ( i < 0 || j < 0) {
    		if ( i == j) return true;
    		return false;
    	}
        if ( s[i] == p[j] || p[j] == '.' ) {
        	result[i][j] = help(s,p,i-1, j-1,result);
        }
        else if ( p[j] == '.') {
        	result[i][j] = help(s,p,i-1,j-1,result);
        }else if ( p[j] == '*') {
        	result[i][j] = help (s, p, i-1, j, result) || help(s,p, i,j-2, result);
        }
        return false;
    }
    
   
    
    
    
    
    
    
    
    
    
    
    public static boolean isMatch1(String s1, String p1) {
    	char[] s = s1.toCharArray();
    	char[] p = p1.toCharArray();
    	
    	boolean[][] out = new boolean[s.length+1][p.length+1];
    	
    	out[0][0] = true;
    	
    	for ( int i = 1; i < out[0].length; i++) {
    		if ( p[i-1] == '*') {
    			out[0][i] = out[0][i-2];
    		}else
    			out[0][i] = false;
    	}
    	
    	for ( int i = 1; i < out.length; i++) 
    		for ( int j = 1; j < out[0].length; j++) {
    			if ( s[i-1] == p[j-1] || p[j-1] == '.')
    				out[i][j]  = out[i-1][j-1];
    			else if ( p[j-1] == '*') {
    				out[i][j] = out[i][j-2];
    				if ( s[i-1] == p[j-2] || p[j-2] == '.')
    					out[i][j] = out[i][j] | out[i-1][j];
    			}
    			return false;
    		}
    	return out[out.length][out[0].length];
    }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	public static void main(String[] args) {
		System.out.println(isMatch("aa", 
				                   "a*"));
		
	}
}
