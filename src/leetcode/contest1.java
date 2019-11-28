package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.print.attribute.standard.MediaSize.NA;

public class contest1 {

	public static int balancedStringSplit(String s) {
		if (s.length() <= 1)
			return 0;

		Stack<Character> sk = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			sk.push(s.charAt(i));

		}
		int result = 0;
		int count = 1;
		char temp = sk.pop();
		while (!sk.isEmpty()) {

			if (sk.pop() == temp)
				count--;
			else
				count++;
			if (count == 0) {
				result++;
				temp = sk.pop();
			}

		}
		return result;

	}

	String s = "RLRRLLRLRL";

	public static int balancedStringSplit1(String s) {
		if (s.length() <= 1)
			return 0;

		String sub = "";
		int pair = 1;
		int tempres = 0;
		int res = 0;
		for (int i = 0; i <= s.length(); i++) {
			sub = s.substring(0, i);
			for (int j = 0; j < sub.length() - 1; j++) {
				for (int k = j + 1; k < sub.length(); k++) {
					if (sub.charAt(j) == sub.charAt(k)) {
						pair++;
						continue;
					} else {
						pair--;
					}
					if (pair == 0) {
						tempres++;
						j = k + 1;
						k = j;
						pair = 1;
					}
				}
			}
			res = Math.max(res, tempres);
			tempres = 0;
			pair = 1;

		}

		return res;

	}

	public static List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
		List<List<Integer>> queen = new ArrayList<List<Integer>>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int[] n : queens) {
			List<Integer> temp = new ArrayList<>();
			temp.add(n[0]);
			temp.add(n[1]);
			queen.add(temp);
		}
		int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
		for (int[] n : direction) {
			int[] tempKing = new int[2];
			tempKing[0] = king[0];
			tempKing[1] = king[1];
			while (true) {
				tempKing[0] += n[0];
				tempKing[1] += n[1];
				List<Integer> temp = new ArrayList<>();
				temp.add(tempKing[0]);
				temp.add(tempKing[1]);
				if (queen.contains(temp)) {
					result.add(temp);
					break;
				}
				if (tempKing[0] < 0 || tempKing[0] > 8 || tempKing[1] < 0 || tempKing[1] > 8)
					break;
			}

		}

		return result;

	}

	public static int balancedStringSplit11(String s) {
		int counter = 0, j = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'R')
				j++;
			if (s.charAt(i) == 'L')
				j--;
			if (j == 0)
				counter++;
		}
		return counter;
	}

	public static void main(String[] args) {
//		int[][] queens = { { 0, 1 }, { 1, 0 }, { 4, 0 }, { 0, 4 }, { 3, 3 }, { 2, 4 } };
//		int[] king = { 0, 0 };
//		queensAttacktheKing(queens, king);
		int[] count = new int[2];
		for ( int n : count)
			System.out.print(n  + " ");

	}

}
