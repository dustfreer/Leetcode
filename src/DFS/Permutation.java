package DFS;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if ( nums.length == 0 ) return result;
        
        List<Integer> combination = new ArrayList<>();
        permutation(0, nums, combination, result);
        return result;
        
    }
    
    public void permutation(int start,
                            int[] nums,
                            List<Integer> combination,
                            List<List<Integer>> result){
        
        if ( combination.size() == nums.length ){
            result.add(new ArrayList<> (combination));
            return;
        }
        
        for ( int i = start; i < nums.length; i++){
            combination.add(nums[i]);
            permutation(i, nums, combination, result);
            combination.remove(combination.size() - 1);
        }
    }
    
    public static void main(String[] args) {
    	Permutation test = new Permutation();
    	System.out.println(test.permute(new int[] {1,2,3}));
    }
}
