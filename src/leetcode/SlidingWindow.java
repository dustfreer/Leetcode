package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {
	
	/*
	 * sliding window optimization with HashMap:
	 * leetcode 76;
	 */
	public static String minWindow(String s, String t) {
        if ( t.length() == 0) return "";
        
        Map<Character, Integer> dict = new HashMap<Character, Integer>();      
        for ( int i = 0; i < t.length(); i++){
        	char ch = t.charAt(i);
            dict.put(ch, dict.getOrDefault(ch, 0)+1);
        }
        
        int i = 0, j = 0, len = s.length(), count = 0, min = Integer.MAX_VALUE;
        String result = "";
        for ( ; i < len && j < len; ){
        	char ch = s.charAt(j++);
            if ( dict.containsKey(ch) ){                            
            	dict.put(ch, dict.get(ch)-1);
            	if ( dict.get(ch) == 0)
                    count++;
            }    

            while( count == dict.size() ){
            	if ( min > j-i){
                    min = j-i;
                    result = s.substring(i,j);
                }
            	char ch1 = s.charAt(i++);
                if ( dict.containsKey(ch1)) {
                	dict.put(ch1, dict.get(ch1)+1);
                	if ( dict.get(ch1) > 0)
                        count--;
                }
            } 
        }
        return result;
    }
	
	/*
	 * sliding window optimization with array:
	 * leetcode 76;
	 */
	public String minWindow1(String s, String t) {
        if ( t.length() == 0) return "";
        
        int[] dict = new int[128];
        int size = 0;
        for ( int i = 0; i < t.length(); i++){
        	char ch = t.charAt(i);
            if ( dict[ch]++ == 0)
                size++;
        }
        
        int i = 0, j = 0, len = s.length(), count = 0, min = Integer.MAX_VALUE;
        String result = "";
        for ( ; i < len && j < len; ){	
            if ( dict[s.charAt(j++)]-- == 1 ){                            
                count++;
            }    
            while( count == size ){
            	if ( min > j-i){
                    min = j-i;
                    result = s.substring(i,j);
                }
                if ( ++dict[s.charAt(i++)] > 0) {
                    count--;
                }
            }
        }
        return result;
    }
}
