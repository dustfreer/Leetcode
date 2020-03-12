package Trie;

import java.util.HashMap;
import java.util.Map;

class AddAndSearchWorld {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;
        
        TrieNode() {
            children = new HashMap<>();
            isEnd = false;
        }
    }

    /** Initialize your data structure here. */
    TrieNode root;
    public AddAndSearchWorld() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        Map<Character, TrieNode> curChildren = root.children;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!curChildren.containsKey(ch)) {
                curChildren.put(ch, new TrieNode());
            }
            cur = curChildren.get(ch);
            curChildren = cur.children;
            if (i == word.length() -1) {
                cur.isEnd = true;
            }
        }
    }
    
    public boolean find(String word, int index, TrieNode root) {
        if (index == word.length() || root == null) {
            return false;
        }
        char ch = word.charAt(index);
        System.out.print(ch+" ");
        if (ch != '.') {
            Map<Character, TrieNode> curChildren = root.children;
            if (!curChildren.containsKey(ch)) {
                return false;
            }else if (index == word.length() - 1 && curChildren.get(ch).isEnd) {
                return true;
            }
            return find(word, index+1, curChildren.get(ch));
        }else {
            boolean findFromChildren = false;
            Map<Character, TrieNode> curChildren = root.children;
            for (Map.Entry<Character, TrieNode> entry : curChildren.entrySet()){
                if (index == word.length() -1 && entry.getValue().isEnd) {
                    return true;
                }
                if (find(word, index+1, entry.getValue())) {
                    findFromChildren = true;
                }
            }
            return findFromChildren;
        }
    }
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode cur = root;
        return find(word, 0, cur);
    }
    
    public static void main(String[] args) {
    	WordDictionary test = new WordDictionary();
    	test.addWord("bad");
    	test.addWord("dad");
    	test.addWord("mad");
    	System.out.println(test.search("bad"));
    }
}


class WordDictionary {
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        
        TrieNode() {
        	children = new TrieNode[26];
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            isEnd = false;
        }
    }

    /** Initialize your data structure here. */
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        TrieNode[] curChildren = root.children;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curChildren[ch-'a'] == null) {
                curChildren[ch-'a'] = new TrieNode();
            }            
            cur = curChildren[ch-'a'];
            curChildren = cur.children;
            if (i == word.length() -1) {
                cur.isEnd = true;
            }
        }
    }
    
    public boolean find(String word, int index, TrieNode root) {
        if (index == word.length() || root == null) {
            return false;
        }
        char ch = word.charAt(index);
        TrieNode[] curChildren = root.children;
        if (ch != '.') {
            if (curChildren[ch-'a'] != null) {
                if (index == word.length() - 1 && curChildren[ch-'a'].isEnd) {
                    return true;
                }else {
                    return find(word, index+1, curChildren[ch-'a']);
                }
            }               
        }else {
            boolean findFromChildren = false;
            for (int i = 0; i < 26; i++) {
                if (curChildren[i] != null) {
                    if (index == word.length() -1 && curChildren[i].isEnd) {
                        return true;
                    }
                    if (find(word, index+1, curChildren[i])) {
                        findFromChildren = true;
                    }
                }                                
            }
            return findFromChildren;
        }
        return false;
    }
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode cur = root;
        return find(word, 0, cur);
    }
}
