package Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Trie {
	static class TrieNode{
	    HashMap<Character, TrieNode> children;
	    String word;
	    public TrieNode(){
	        children = new HashMap<Character, TrieNode>();
	        word = null;
	    }
	}
	
	
	static int m;
	static int n;
	static Set<String> result;
    static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
    public static List<String> findWords(char[][] board, String[] words) {
        result = new HashSet<String>();
        if ( board.length == 0) return new ArrayList<String>(result);
        m = board.length;
        n = board[0].length;
        
        TrieNode root = new TrieNode();
        constructTrie(root, words);
        for ( int i = 0; i < m; i++)
            for ( int j = 0; j < n; j++){
                dfs(board, i, j, root); 
            }
        return new ArrayList<String>(result);
    }
    
    public static void constructTrie(TrieNode root, String[] words){
        for ( String word : words){
            TrieNode node = root;
            
            for ( Character c : word.toCharArray()){
                if ( node.children.containsKey(c)){
                    node = node.children.get(c);
                }else{
                    TrieNode newNode = new TrieNode();
                    node.children.put(c, newNode);
                    node = newNode;
                }
            }
            node.word = word;
        }
    }
    
    public static void dfs(char[][] board, int i, int j, TrieNode parent){
        
        Character letter = board[i][j];
        TrieNode curNode = parent.children.get(letter);
        if ( curNode == null )
        	return;
        if ( curNode.word != null ){
            result.add(curNode.word);
            curNode.word = null;
        }
        char temp = board[i][j];
        board[i][j] = '-';
        
        for ( int k = 0; k < dir.length; k++){
            int x = i + dir[k][0];
            int y = j + dir[k][1];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }
            if (curNode.children.containsKey(board[x][y])) {
                dfs(board, x, y, curNode);
            }
        }

        board[i][j] = temp;
        if (curNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
    
    public static void main(String[] args) {
    	char[][] board = {{'o','a','a','n'},
    				  	  {'e','t','a','e'},
    				  	  {'i','h','k','r'},
    				  	  {'i','f','l','v'}};
    	//char[][] board = {{'a','a'}};
    	//System.out.println(findWords(board, new String[] {"oath","pea","eat","rain"}).toString());
    	Stack<Integer> s = new Stack<Integer>();
    	int i = 0;
    	
    	s.push(1);
    	s.push(2);
    	s.push(5);
    	System.out.println(s.firstElement());
    	
    		
    	
    	
    }
}
