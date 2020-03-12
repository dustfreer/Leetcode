package Trie;

import java.util.HashMap;
import java.util.Map;

public class ImplementTrie {
	class TrieNode {
        Map<Character, TrieNode> children;
        boolean endWord;
        
        TrieNode () {
            children = new HashMap<>();
            endWord = false;
        }
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public ImplementTrie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        Map<Character, TrieNode> curChildren = cur.children;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            System.out.print(ch+" ");
            if (curChildren.containsKey(ch)) {
                cur = curChildren.get(ch);
            }else {
                TrieNode newTrieNode = new TrieNode();
                curChildren.put(ch, newTrieNode);
                cur = newTrieNode;
            }
            curChildren = cur.children;
            if (i == word.length() - 1) {
                cur.endWord = true;
            }
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode temp = searchWord(word);
        if (temp == null) {
            return false;
        }else {
            return temp.endWord;
        }
    }        
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode temp = searchWord(prefix);
        if (temp == null) {
            return false;
        }
        return true;
    }
    
    public TrieNode searchWord(String word) {
        TrieNode cur = root;
        Map<Character, TrieNode> curChildren = cur.children;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!curChildren.containsKey(ch)) {
                return null;
            }
            cur = curChildren.get(ch);
            curChildren = cur.children;
            if (i == word.length() -1) {
                return cur;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
    	ImplementTrie test = new ImplementTrie();
    	test.insert("apple");
    }
}
