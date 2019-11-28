package Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test1 {
	public int[][] indexPairs(String text, String[] words) {
        List<int[]> temp = new ArrayList<int[]>();
        if ( text.length() == 0 || words.length == 0) return null;
        
        for ( int i = 0; i < text.length(); i++)
            for ( int j = 0; j < words.length; j++){
                String s = words[j];
                int k = i;
                if ( text.charAt(i) != s.charAt(0) ) continue;
                else{
                    
                    while ( k - i < s.length() && text.charAt(k) == words[j].charAt(k-i) ){
                        k++;
                    }
                }
                if ( k - i == s.length() ){
                    temp.add(new int[] {i,k-1});
                }
                
            }  
        int[][] result = new int[temp.size()][2];
        int n = 0;
        for ( int[] aa : temp){
            result[n++] = aa;
        }
        return result;
            
    }
	
	public boolean shouldPrintMessage(int timestamp, String message) {
        Map<String, Integer> temp = new HashMap<String, Integer>();
        if ( !temp.containsKey(message) ){
            temp.put(message, timestamp);
            return true;
        }
        else{
            if ( Math.abs( temp.get(message) - timestamp) > 10){
                temp.put(message, timestamp);
                return true;
            }else return false;
                    
        }
	}
     
	public static void main(String[] args) {
		
	}
        
}
