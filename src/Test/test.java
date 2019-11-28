package Test;

import java.util.ArrayList;
import java.util.List;

public class test {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public int maxPoints(int[][] points) {
		for (int i = 0; i < points.length; i++)
			for (int j = 0; j < points[i].length; j++)
				System.out.print("points[i][j]" + " ");

		return 5;
	}

	public static ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null || head.next == null)
			return head;
		if (m == n)
			return head;
		ListNode temp = head;
		ListNode preHead = null;
		ListNode mid;
		ListNode back = null;

		while (m - 1 != 0) {
			preHead = temp;
			temp = temp.next;
			m--;
			n--;
		}
		while (n != 0) {
			mid = temp.next;
			temp.next = back;
			back = temp;
			temp = mid;

			n--;
		}
		if (preHead != null)
			preHead.next = back;
		else {
			head= back;
		}
		while (back.next != null)
			back = back.next;
		back.next = temp;
		

		return head;
	}

	public static void PrintList(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.val + "-> ");
			temp = temp.next;
		}
		System.out.println("NULL");
	}

    public static String reverseWords(String s) {
        if ( s.length() == 0 || s == null) return s;
        
        StringBuilder res = new StringBuilder();
        String[] str = s.split(" ", 0);
        char[] c = null;
        for ( int i = 0; i < str.length; i++){
        	c = str[i].toCharArray();
            for ( int j = 0, k = str[i].length()-1; j <= k; j++,k--){
                char temp;
                temp = c[j];
                c[j] = c[k];
                c[k] = temp;
            }
            res.append(new String(c));
            res.append(" ");
            
        }
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }
    
    public static String removeDuplicates(String S) {
        if ( S.length() <= 1 || S == null) return S;
        
        char[] c = S.toCharArray();
        boolean sign = false;
        for ( int i = 0; i < c.length - 1; i++){
            if ( c[i] == c[i+1] ){
                c[i] = ' ';
                c[i+1] = ' ';
                sign = true;
            }           
        }
        S = new String(c);
        S = S.replaceAll(" ", "");
        if (sign) S = removeDuplicates(S);
        return S;
        
    }
	
    public static void permulation(List<String> l, String str, String res) {
    	if ( str.length() == 0) {
    		l.add(res);
    		return ;
    	}
    	
    	for ( int i = 0; i < str.length(); i++) {
    		char c = str.charAt(i);
    		String temp = str.substring(0,i)+str.substring(i+1);
    		permulation(l, temp, res + c);
    	} 
    	return ;
    	
    }
    
    
    
    
    public static List<List<Integer>> permute(int[] nums) {
        if ( nums.length == 0) return null;
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        for ( int n : nums) l1.add(n);
        help(result, l1, l2);
        return result;
        
    }
    
    public static void help(List<List<Integer>> result, List<Integer> ll1, List<Integer> ll2){
    	List<Integer> l1 = new ArrayList<Integer>(ll1);
        List<Integer> l2 = new ArrayList<Integer>(ll2);
        if (l1.size() == 0){
            result.add(new ArrayList<Integer>(l2));
            return;
        }
        
        for ( int i = 0; i <= l1.size(); i++){
            int temp = l1.get(i);
            l1.remove(i);
            l2.add(temp);
            help(result, l1, l2);
            
        }
        return;
    }
    
    
	public static void main(String[] args) {
		List<List<Integer>> l = new ArrayList<List<Integer>>();
		int[] nums = {1,2,3};
		l = permute(nums);
		System.out.println(l.toString());
		

	}
}
