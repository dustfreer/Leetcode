package Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class test4 {
	
	
	
	static class compareString implements Comparator<String>{
		public int compare(String o1, String o2) {
			return -1;
		}
	}
	
	public static int maxSubArray(int[] nums) {
        if ( nums.length == 0 ) return 0;
        
        int max = nums[0];
        for ( int i = 1; i < nums.length; i++ ) {
        	nums[i] = Math.max(nums[i], nums[i] + nums[i-1]);
        	if ( nums[i] > max) max = nums[i];
        }
        return max;
    }
	
	public static String mostCommonWord(String paragraph, String[] banned) {
        String[] symbol = {"!","?","'",",",";","."};
        
        for ( int i = 0; i < symbol.length; i++)
        	paragraph = paragraph.replace(symbol[i], " ");
        
        String[] str = paragraph.split(" ", 0);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for ( int i = 0; i < str.length; i++){
        	if ( str[i].length() == 0 ) continue;
            StringBuilder sb = new StringBuilder(str[i]);
            for( int k = 0; k < sb.length(); k++) {
            	if ( sb.charAt(k) >= 'a') {
            		continue;
            	}
                int temp = (int)sb.charAt(k) + 32;
                sb.setCharAt(k, (char)temp);
            }
            String s = sb.toString();
            if ( !map.containsKey(s) ) map.put(s, 1);
            else
                map.put(s,map.get(s)+1);
        }
        
        for ( int i = 0; i < banned.length; i++){
            String s = banned[i];
            if ( map.containsKey(s) ) map.put(s, 0);            
        }
        String result = "";
        int max = 0;
        for ( Map.Entry<String, Integer> entry: map.entrySet()){
            if ( entry.getValue() > max )  {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
	}
	
	public static void main(String[] args) {
		String s = "\"j. t? T. z! R, v, F' x! L; l! W. M; S. y? r! n; O. q; I? h; w. t; y; X? y, p. k! k, h, J, r? w! U! V; j' u; R! z. s. T' k. P? M' I' j! y. P, T! e; X. w? M! Y, X; G; d, X? S' F, K? V, r' v, v, D, w, K! S? Q! N. n. V. v. t? t' x! u. j; m; n! F, V' Y! h; c! V, v, X' X' t? n; N' r; x. W' P? W; p' q, S' X, J; R. x; z; z! G, U; m. P; o. P! Y; I, I' l' J? h; Q; s? U, q, x. J, T! o. z, N, L; u, w! u, S. Y! V; S? y' E! O; p' X, w. p' M, h! R; t? K? Y' z? T? w; u. q' R, q, T. R? I. R! t, X, s? u; z. u, Y, n' U; m; p? g' P? y' v, o? K? R. Q? I! c, X, x. r' u! m' y. t. W; x! K? B. v; m, k; k' x; Z! U! p. U? Q, t, u' E' n? S' w. y; W, x? r. p! Y? q, Y. t, Z' V, S. q; W. Z, z? x! k, I. n; x? z; V? s! g, U; E' m! Z? y' x? V! t, F. Z? Y' S! z, Y' T? x? v? o! l; d; G' L. L, Z? q. w' r? U! E, H. C, Q! O? w! s? w' D. R, Y? u. w, N. Z? h. M? o, B, g, Z! t! l, W? z, o? z, q! O? u, N; o' o? V; S! z; q! q. o, t! q! w! Z? Z? w, F? O' N' U' p? r' J' L; S. M; g' V. i, P, v, v, f; W? L, y! i' z; L? w. v, s! P?" ; 
		String[] ss = {"m","q","e","l","c","i","z"};
		System.out.println(mostCommonWord(s,ss));
		char ch = 'A';
		Character.isLowerCase(ch);
		Character.toUpperCase(ch);
		String str = "kai,dskls";
		System.out.println(str.contains("kai"));
//		LinkNode l = new LinkNode(1);
//		l.next = new LinkNode(2);
//		LinkedList<String> object = new LinkedList<String>(); 
//		
	}
	
	class LinkNode{
		LinkNode pre;
		LinkNode next;
		int val;
		LinkNode(int val){
			this.val = val;
		}
	}
}
