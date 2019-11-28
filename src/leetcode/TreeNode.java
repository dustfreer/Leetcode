package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;


public class TreeNode {
	int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    
    public static boolean isSubtree1(TreeNode s, TreeNode t) {
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	findRoot1(q, s,t);
    	boolean result = false;
    	while ( !q.isEmpty()) {
    		//TreeNode temp = q.poll();
    		result =  checkSubtree(q.poll(),t)||result ;
    	}
        
        return result;
        
    }
    
    public static boolean checkSubtree1(TreeNode s, TreeNode t) {
    	if ( s == null || t == null ) {
    		if ( s == t) return true;
    		return false;    			
    	}else if ( s.val != t.val){
    		return false;
    	}else {
    		if ( !checkSubtree(s.left,t.left) ) return false;
    		return checkSubtree(s.right,t.right);
    	}
    }
    
    public static void findRoot1(Queue<TreeNode> q, TreeNode s, TreeNode t) {
    	if ( s == null ) return ;
    	if ( s.val == t.val) {
    		q.add(s);
    	}
    	findRoot1(q, s.left, t);
    	findRoot1(q, s.right, t);    	    	
    }
    
    public static boolean isSubtree(TreeNode s, TreeNode t) {        
        return findRoot(s,t);
        
    }
    
    public static boolean checkSubtree(TreeNode s, TreeNode t) {
    	if ( s == null || t == null ) {
    		if ( s == t) return true;
    		return false;    			
    	}
    	if ( s.val != t.val) 
    		return false;
    	
    	return checkSubtree(s.left, t.left) && checkSubtree(s.right, t.right);
    }
    
    public static boolean findRoot(TreeNode s, TreeNode t) {
    	if ( s == null ) return false;
    	
    	return checkSubtree(s,t) || findRoot(s.left, t) || findRoot(s.right, t);
    }
    
    
    public static boolean isValidBST(TreeNode root) {
    	return isValidBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);	
	}
    public static  boolean isValidBSTUtil(TreeNode root, long minValue, long maxValue) {
    	if ( root == null )
    		return true;
    	if ( root.val < minValue || root.val > maxValue)
    		return false;
    	return isValidBSTUtil(root.left, minValue, root.val) &&
    			isValidBSTUtil(root.right, root.val, maxValue);
    }
    
    
    public static TreeNode LCAOfBST(TreeNode root, TreeNode p, TreeNode q) {
    	if ( root.val > p.val && root.val > q.val)
    		return LCAOfBST(root.left, p ,q);
    	if ( root.val < p.val && root.val < q.val)
    		return LCAOfBST(root.right, p, q);
    	return root;
    }
    
    public static TreeNode LCAOfBST1(TreeNode root, TreeNode p, TreeNode q) {
    	int qVal = q.val;
    	int pVal = p.val;
    	
    	while ( root != null) {
    		if ( root.val < qVal && root.val < pVal)
    			root = root.right;
    		else if ( root.val > qVal && root.val > pVal)
    			root = root.left;
    		else 
    			break;
    	}

    	return root;
    }
    
    public static TreeNode LCAOfBST2(TreeNode root, TreeNode p, TreeNode q) {
    	Stack<TreeNode> parentOfLeft = new Stack<TreeNode>();
    	Stack<TreeNode> parentOfRight = new Stack<TreeNode>();
    	
    	LCAOfBST2Util(parentOfLeft, root, p);
    	LCAOfBST2Util(parentOfRight, root, q);
    	TreeNode result = null;
    	while ( parentOfLeft.peek() == parentOfRight.peek() ) {
    		result = parentOfLeft.pop();
    		parentOfRight.pop();
    		if ( parentOfLeft.isEmpty() || parentOfRight.isEmpty())
    			break;
    	}
    	return result;
    }
    
    public static boolean LCAOfBST2Util(Stack<TreeNode> parents, TreeNode root , TreeNode p) {
    	if ( root == null ) return false;
    	
    	if ( root.val == p.val ) {
    		parents.add(root);
    		return true;
    	}
    	boolean parentOfLeft = LCAOfBST2Util(parents, root.left, p);
    	boolean parentOfRight = LCAOfBST2Util(parents, root.right, p);
    	if (parentOfLeft || parentOfRight)
    		parents.add(root);
    	return parentOfLeft||parentOfRight;
    }
    
    static TreeNode anx ;
    public static TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {    	
    	LCAUtil(root, p ,q);
    	return anx;
    }
    
    public static int LCAUtil(TreeNode root, TreeNode p, TreeNode q) {
    	if ( root == null ) return 0;
    	if ( root == p || root == q) {
    		anx = root;
    		return 1;
    	}
    	int left = LCAUtil(root.left, p, q);
    	int right = LCAUtil(root.right, p, q);
    	if ( left + right < 1) return 0;
    	if ( left + right == 1 ) return 1;
    	else
    		anx = root;
    	return 0;
    }
    
    
    public static void printTreeLevelByLevel(TreeNode root) {        
        if ( root == null ) return ;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);

        while( !q.isEmpty() ){
            Queue<TreeNode> temp = new LinkedList<TreeNode>();
            while( !q.isEmpty() ){
                TreeNode t = q.poll();
                System.out.print(t.val + " ");
                if ( t.left != null){
                    temp.add(t.left);
                }
                if ( t.right != null){
                    temp.add(t.right);
                }
            }
            q = temp;            
            System.out.println();
        }
    }
    
    static int count = 0;
    static int max = 0;
    public static int[] findMode(TreeNode root) {
        if ( root == null ) return new int[0];
        
        Set<Integer> set = new HashSet<Integer>();
        help(root, root.val, set);
        int[] result = new int[set.size()];
        int i = 0;
        for ( int n : set)
            result[i++] = n;
        return result;
    }
    public static void help(TreeNode root, int pre, Set<Integer> set){
        if ( root == null) return ;
        
        help(root.left, pre, set);
        int cur = root.val;
        System.out.print(cur+" ");
        
        if ( cur == pre){
            count++;
            if ( count == max){
                set.add(cur);
            }else if ( count > max){
                set.clear();
                set.add(cur);
                max = count;
            }
        }else{
            pre = cur;
            count = 1;
        }
        System.out.println();
        System.out.print(pre+" ");
        help(root.right, pre, set);
    }
    
    
    
    
    public static void main(String[] args) {
    	TreeNode s = new TreeNode(1);
    	//s.left = new TreeNode(2);
    	s.right = new TreeNode(2);
    	//s.left.left = new TreeNode(6);
    	//s.left.right = new TreeNode(2);
    	s.right.left = new TreeNode(2);
//    	s.right.right = new TreeNode(8);
//    	s.left.right.left = new TreeNode(7);
//    	s.left.right.right = new TreeNode(4);
    	
    	TreeNode t = new TreeNode(1);
//    	t.left = new TreeNode(1);
//    	t.right = new TreeNode(2);

    	//System.out.println(!!(false||true) && true);
    	
    	TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
    	map.put(4,1);
    	map.put(2,1);
    	map.put(1,1);
    	
    	
    	
    }
}
