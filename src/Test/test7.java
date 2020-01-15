package Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class test7 {
	public static boolean wordBreak(String s, List<String> wordDict) {
		return help(s,wordDict);
		
	}
	public static boolean help(String s, List<String> wordDict) {
		if ( s.length() == 0) return true;
		for ( int i = 0; i <= s.length(); i++) {
			String str = s.substring(0,i);
			if ( wordDict.contains(str) ) {
				return help(s.substring(i), wordDict);
			}
		}
		return false;
	}
	
	public static boolean wordBreak1(String s, List<String> wordDict) {
		boolean[] dp = new boolean[s.length()+1];
		dp[0] = true;
		for ( int i = 1; i <= s.length(); i++) {
			for ( int j = 0; j < i; j++) {
				if ( dp[j] & wordDict.contains(s.substring(j, i))){
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()+1];
	}
	public static void main(String[] args) {
		String str = "leetcode";
		List<String> wordDict = Arrays.asList("leet","code");
		//System.out.println(wordBreak(str, wordDict));
		Stack<Integer> s = new Stack<>();
		Deque<Integer> d = new LinkedList<>();
		d.offerFirst(1);
		d.offerFirst(2);
		d.push(3);
		d.offerLast(4);
		Iterator i = d.iterator();
		
		while ( i.hasNext() ) {
			System.out.print(i.next()+"");
		}
	}
	
	
}
