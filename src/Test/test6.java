package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class test6 {

	public static String addBinary(String a, String b) {
        int i = a.length()-1;
        int j = b.length()-1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int sum = 0;
        int x = 0, y = 0;
        while ( i >=0 && j >= 0){
            x = (int)(a.charAt(i) - '0');
            y = (int)(b.charAt(j) - '0');
            sum = x+y+carry;
            carry = (sum >= 2) ? 1 : 0;
            sum = sum % 2;
            sb.append((char)(sum + '0'));
            i--;
            j--;
        }
        
        while ( i >= 0){
            x = (int)(a.charAt(i) - '0');
            sum = x + carry;
            carry = (sum >= 2) ? 1 : 0;
            sum = sum % 2;
            sb.append((char)(sum + '0'));
            i--;
        }
        
        while ( j >= 0){
            y = (int)(b.charAt(j) - '0');
            sum = y + carry;
            carry = (sum >= 2) ? 1 : 0;
            sum = sum % 2;
            sb.append((char)(sum + '0'));
            j--;
        }
        if ( carry != 0)
        	sb.append((char)(carry + '0'));
        return sb.reverse().toString();
    } 
	
	
	
	public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if ( strs.length == 0 ) return result;
       
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        
        for ( int i = 0; i < strs.length; i++){
            
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            String s = String.valueOf(temp);
            if ( map.containsKey(s)){
            	map.get(s).add(strs[i]);
                map.put(s,map.get(s));
                continue;
            }else{
            	List<String> l =  new ArrayList<String>();
            	l.add(strs[i]);
                map.put(s,l);
            }
        }
        for ( Map.Entry<String, List<String>> entry : map.entrySet()) {
        	result.add(entry.getValue());
        }
        return result;
    }
	
	public static String reverseOnlyLetters(String s) {
        if ( s.length() <= 1)
            return s;
        StringBuilder sb = new StringBuilder(s);
        for ( int i = 0, j = sb.length()-1; i<j;){
            char ch1 = sb.charAt(i);
            char ch2 = sb.charAt(j);
            if ( ch1 == '-'){
                i++;
                continue;
            }
            if ( ch2 == '-'){
                j--;
                continue;
            }
            sb.replace(i,i+1,String.valueOf(ch2));
            sb.replace(j,j+1,String.valueOf(ch1));
            i++;
            j--;
            
        }
        return sb.toString();
    }
	
	public static int countBinarySubstrings(String s) {
        
        int result = 0;
        boolean upOrdown = true;
        for( int i = 0; i < s.length(); i++){
            int count = 0;
            for ( int j = i; j < s.length(); j++){
                char ch = s.charAt(j);
                if ( ch == '0'){
                    if ( count <= 1  || count >= -1 )
                        upOrdown = true;
                    else if ( !upOrdown )
                        break;
                    count++;
                }
                if ( ch == '1'){
                    if ( count <= 1 || count >= -1)
                        upOrdown = false;
                    else if ( upOrdown )
                        break;
                    count--;
                }
                if ( count == 0){
                    result++;
                    break;
                }
            }
        }
        return result;
    }
	
	public static void main(String[] args) {

		countBinarySubstrings("10110001");
		
	}
}
