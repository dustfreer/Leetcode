package Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		String s = "leetcode";
		List<String> wordDict = Arrays.asList("leet","code");
		System.out.println(wordBreak(s, wordDict));
	}
}
