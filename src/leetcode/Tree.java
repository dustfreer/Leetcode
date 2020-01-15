package leetcode;

public class Tree {
	public static class TreeNode {
		int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	static boolean isVisited = false;
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        
        inorderSuccessor(root.left, p);
        if (isVisited) {
            return root;
        }
        if (root.val == p.val) {
            isVisited = true;
        } 
        inorderSuccessor(root.right, p);
        
        return null;
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(1);
    	root.right = new TreeNode(2);
    	inorderSuccessor(root,root);
    	
    }
}
