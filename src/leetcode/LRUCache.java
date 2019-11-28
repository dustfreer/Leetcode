package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, Integer> cache;
    
	private int capacity;
	
	public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer, Integer>(capacity, 1, true){
        	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        		  return size() > capacity;
        	}
        };
	}
        
    
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        cache.put(key, value);
    }

}
