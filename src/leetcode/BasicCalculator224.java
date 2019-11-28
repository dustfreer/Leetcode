package leetcode;

import java.util.HashMap;
import java.util.Map;

public class BasicCalculator224 {

	//(1+(4+5+2)-3)+(6+8)	
	public static int calculator(String s) {
		if ( s.length() == 0 ) return 0;
		int[] i = {0};
		return help(s, i);
	}
	
	public static int help(String s, int[] i) {		
		
		int result = 0;
		int sign = 1;
		while ( i[0] < s.length() ) {
			char c = s.charAt(i[0]);
			if ( Character.isDigit(c)) {
				result += getNumber(i, s) * sign;
				continue;			
			}else if ( c == '(') {
				i[0]++;
				result += help(s, i) * sign;
			}else if ( c == ')') {
				return result;				
			}else if ( c == '+') {
				sign = 1;
			}else if ( c == '-') {
				sign = -1;
			}
			i[0]++;
		}
		return result;
	}
	
	public static int getNumber(int[] i, String s) {
		int number = 0;
		while ( i[0] < s.length() && Character.isDigit(s.charAt(i[0])) ) {
			number = number * 10 + (int)(s.charAt(i[0]++) - '0') ;
		}
		return number;
	}
	
	
	
	public static void main(String[] args) {
	}

}
