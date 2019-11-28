package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DP {

	public static int state( int n ) {
		int[] dp = new int[n+1];
		return memo(dp,n);
	}
	
	public static int memo(int[] dp,int n) {
		if ( n < 0)
			return 0;
		if ( n == 0)
			return 1;
		
		if ( dp[n] != 0)
			return dp[n];
		return dp[n] = memo(dp,n-1) + memo(dp,n-3)+memo(dp,n-5);
	}
	
	public static int LCS(String s1, String s2) {
		if ( s1.length() == 0)
			return 0;
		if ( s2.length() == 0)
			return 0;
		int n1 = s1.length() ;
		int n2 = s2.length() ;
		if( s1.charAt(n1-1) == s2.charAt(n2-1)) {
			return 1+LCS(s1.substring(0, n1-1), s2.substring(0, n2-1));
		}else 
			return Math.max(LCS(s1.substring(0, n1), s2.substring(0, n2-1)), LCS(s1.substring(0, n1-1), s2.substring(0, n2)));
	}
	
	public static int LCS1(String s1, String s2) {
		
		int out = memo(s1, s2);
		return out;
	}
	
	public static int memo(String s1, String s2) {
		int[][] res = new int[s1.length()+1][s2.length()+1];
		for ( int i = 0; i <= s1.length(); i++)
			for ( int j = 0; j <= s2.length(); j++) {
				if ( i == 0 || j == 0 )
					res[i][j] = 0;
				else if ( s1.charAt(i-1) == s2.charAt(j-1))
					res[i][j] = 1 + res[i-1][j-1];
				else
					res[i][j] = Math.max(res[i-1][j], res[i][j-1]);
			}
		return res[s1.length()][s2.length()];
	}
	
	/////////////________________________________________________________________________________________________
	
	
	public static int LIS(int[] arr) {
		int[] res = new int[arr.length+1];
		
		int result = 0;
		for ( int i = 0; i < arr.length; i++) {
			int temp = memo(arr, res, i);
			result = Math.max(result, temp);
		}
		return result;
		
	}
	
	public static int memo(int[] arr, int[] res, int i) {
		if ( i == 0)
			res[i] = 1;
		for( int j = 0; j < i; j++) {
			if ( arr[i] > arr[j]) {
				if (res[i] < res[j] + 1)
					res[i] = res[j] + 1;
			}else 
				res[i] = Math.max(res[i], res[j]);
		}
		return res[i];
	}
	
	public static boolean matchParenthese(String s, String p) {
		return help(s.toCharArray(),p.toCharArray(),s.length()-1, p.length()-1);
	}
	
	public static boolean help(char[] s, char[] p ,int i, int j) {
		if ( i < 0 ) {
			if ( j > 0 ) {
				if ( p[j] == '*')
					return help(s,p, i, j-2);
			}else if ( j == -1)
				return true;
			return false;
		}		
		if ( i >= 0 && j < 0)
			return false;
		
		
		if ( s[i] == p[j] || p[j] == '.') {
			return help(s,p, i-1, j-1);
		}else if ( p[j] == '*') {
			boolean temp = help(s, p, i, j-2);
			if ( p[j-1] == '.' || s[i] == p[j-1]) {
				temp = temp | help(s,p,i-1,j);
			}
			return temp;
		}
		return false;
			
	}
	
	
	public static String longestPalindrome(String s) {
        int len = s.length();
        if ( len <= 1) return s;
        boolean[][] dp = new boolean[len][len];
        
        int maxlen = 0;
        String result = "";
        for ( int i = 0; i < len; i++) {
        	dp[i][i] = true;
        }
        
        for ( int i = 0; i < len-1; i++) {
        	if ( s.charAt(i) == s.charAt(i+1) )
        		dp[i][i+1] = true; 
        }
        for ( int k = 3; k <= len; k++){
            for ( int i = 0; i < len - k + 1 ; i++){
            	int j = i + k - 1;
                char ch1 = s.charAt(i);
                char ch2 = s.charAt(j);
                if ( ch1 == ch2 && dp[i+1][j-1] == true)
                    dp[i][j] = true;
                
                if ( dp[i][j]){
                    if ( k > maxlen){
                        maxlen = k;
                        result = s.substring(i,j+1);
                    }
                }
            }
        }
        return result;
    }
	
	public static boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        
        for ( int i = 0; i < len; i++){
            char ch = s.charAt(i);
            if ( wordDict.contains(String.valueOf(ch)))
                dp[i][i] = true;
        }
        
        for ( int k = 2; k <= len; k++){
            for( int i = 0; i < len - k +1; i++){
                int j = i+k-1;
                String temp = s.substring(i,j+1);
                if ( wordDict.contains(temp)){
                    dp[i][j] = true;
                }else{
                	for ( int part = i; part < j; part++) {
                		if ( dp[i][part] && dp[part+1][j]) {
                			dp[i][j] = true;
                			break;
                		}
                	}
                }
            }
        }
        return dp[0][len-1];
    }
	
	public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] memo = new int[amount+1];
        int[] record = new int[amount+1];
        memo[0] = 0;
        for ( int i = 1; i <= amount; i++){
            memo[i] = Integer.MAX_VALUE-1;  
            record[i] = -1;
        }
        for( int i = 0; i < coins.length; i++){
            for ( int j = 0; j <= amount; j++){
                int val = coins[i];
                if ( j >= val){
                	if ( memo[j - val] + 1 < memo[j]) {
                		memo[j] = memo[j-val] + 1;
                		record[j] = val;
                	}
                }
            }
        }
        
        
        return (record[amount] != -1) ? memo[memo.length-1] : -1;
    }
	
	public static int coinChange1(int[] coins, int amount) {
        Arrays.sort(coins);
        int[][] memo = new int[coins.length][amount+1];
        memo[0][0] = 0;
        for ( int i = 1; i <= amount; i++){
                memo[0][i] = Integer.MAX_VALUE-1;    
        }
        
        for ( int i = 0; i <= amount; i++){
            if ( i >= coins[0]){
            	if ( memo[0][i-coins[0]] + 1 < memo[0][i]) {
            			memo[0][i] = memo[0][i-coins[0]] + 1;
            	}
            }
        }
        for( int i = 1; i < coins.length; i++){
            for ( int j = 0; j <= amount; j++){
                int val = coins[i];
                if ( j >= val){
                	if ( memo[i][j-val] + 1 < memo[i-1][j]) {
                		memo[i][j] = memo[i][j-val] + 1;
                		continue;
                	}
                }
                memo[i][j] = memo[i-1][j];
            }
        }
        
        return (memo[coins.length-1][amount] < Integer.MAX_VALUE-1) ? memo[coins.length-1][amount] : -1;
    }
	
	public static void main(String[] args) { 
		String s = "leetcode";
		List<String> wordDict = Arrays.asList("leet","code");
		System.out.println(coinChange1(new int[] {2,3}, 9));
	
	}

}
