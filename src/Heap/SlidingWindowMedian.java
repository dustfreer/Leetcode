package Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
	public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new double[] {};
        
        List<Double> result = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        
        for (int i = 0; i < k; i++) {
            insert(maxHeap, minHeap, nums[i]);
        }
        calculateMedian(k, result, maxHeap, minHeap);
        
        for (int i = k; i < nums.length; i++) {
            if (nums[i-k] >= minHeap.peek()) {
                minHeap.remove(nums[i-k]);
            }else {
                maxHeap.remove(nums[i-k]);
            }
            insert(maxHeap, minHeap, nums[i]);
            calculateMedian(k, result, maxHeap, minHeap);
        }
        double[] temp = new double[result.size()];
        int index = 0;
        for (double d : result) {
            temp[index++] = d;
        }
        return temp;
    }
    
    public void insert(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int value) {
    	maxHeap.offer(value);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
                                    
    public void calculateMedian(int k, List<Double> result, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        double median = 0;
        if (k % 2 == 0) {
            median = (maxHeap.peek() + minHeap.peek()) / 2.0;
        }else {
            median = maxHeap.peek() * 1.0;
        }
        result.add(median);        
    }                  
    
    public static void main(String[] args) {
    	SlidingWindowMedian test = new SlidingWindowMedian();
    	test.medianSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3);
    }
}
