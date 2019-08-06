package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @createTime 2018年4月21日 上午11:26:42
 * @author MrWang
 */
public class Test01 {
	public static void main(String[] args) {
//		System.out.println(get("23"));
//		int[] nums = {1,2,3,4,3,2,2,4,5,6,67,4,3};
//		System.out.println(removeDuplicates(nums));
//		for (int i : nums) {
//			System.out.println(i);
//		}
//		int[] prices = {7,6,4,3,1};
//		System.out.println(maxProfit(prices));
		int[] nums = {1,2,3,4,5,6};
		rotate(nums,3);
		System.out.println(Arrays.toString(nums));
//		for (int i : nums) {
//			System.out.println(i);
//		}
	}
	
	public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        int i = 0;
        int tempi = 0 ;
        int tempj = nums[0];
        int j = 0;
        while(count < nums.length) {
        	j = (i + k) > nums.length - 1 ? (i + k) % nums.length : i + k;
        	tempi = tempj;
        	tempj = nums[j];
        	nums[j] = tempi;
        	count++;
        	i = j;
        	if(nums.length % 2 == 0 && k == nums.length / 2 && count % 2 == 0) {
        		i++;
        		tempi = tempj = nums[i];
        	}
        }
        for (int a : nums) {
			System.out.println(a);
		}
    }
	
	
	
	
	
	
	
	
	
	 public static int maxProfit(int[] prices) {
		 if(prices.length == 0)
			 return 0;
		 int currentPrice = prices[0];
		 int totalProfit = 0;
		 for(int i = 1 ; i < prices.length ; i++) {
			 if(prices[i] > currentPrice) {
				 totalProfit += prices[i] - currentPrice;
				 currentPrice = prices[i];
			 }else{
				 currentPrice = prices[i];
			 }
				 
		 }
		 return totalProfit;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int removeDuplicates(int[] nums) {
        int repeatNum ;
        int returnNum = nums.length;
        for(int i = 0 ; i < nums.length ; i++) {
        	repeatNum = nums[i];
        	int step = 0;
        	for(int j = i + 1 ; j < returnNum; j++) {
        		if(nums[j] == repeatNum) {
        			step++;
        			continue;
        		}
        		if(step != 0) {
//        			int temp = nums[j - step];
        			nums[j - step] = nums[j];
//        			nums[j] = temp;
        		}
        	}
        	returnNum = returnNum - step;
        }
		return returnNum;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<String> get(String digits){
		class M {
			private int index = 0;
			private String arr;
			
			public M(int index , String arr) {
				this.index = index;
				this.arr = arr;
			}
			public int getIndex() {
				return index;
			}
			public void setIndex(int index) {
				this.index = index;
			}
			public String getArr() {
				return arr;
			}
			public void setArr(String arr) {
				this.arr = arr;
			}
			@Override
			public String toString() {
				return "M [index=" + index + ", arr=" + arr + "]";
			}
			
			
		}
		List<String> answerList = new ArrayList<>();
		if(digits == "" || digits == null || digits.length() == 0) {
			return answerList;
		}
		String[] arr = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
		String[] inArr = new String[digits.length()];
		M[] m = new M[digits.length()];
		for(int i = 0 ; i < digits.length() ; i++){
			m[i] = new M(0, arr[digits.charAt(i) - 48 - 2]);
		}
		
//		for (M m2 : m) {
//			System.out.println(m2);
//		}
		if(digits.length() == 1) {
			for(int i = 0 ; i < m[0].getArr().length(); i++) {
				answerList.add(m[0].getArr().charAt(i) + "");
			}
			return answerList;
		}	
		StringBuilder stb = new StringBuilder();
		while(m[0].getIndex() < m[0].getArr().length()) {
			for (M m2 : m) {
				stb.append(m2.getArr().charAt(m2.getIndex()));
			}
			answerList.add(stb.toString());
			m[m.length - 1].setIndex(m[m.length - 1].getIndex() + 1);
			for(int i = m.length - 1 ; i >= 0 ; i--) {
				if(m[i].getIndex() > m[i].getArr().length() - 1 && i > 0) {
					m[i - 1].setIndex(m[i - 1].getIndex() + 1);
					m[i].setIndex(0);
				}
			}
			stb.delete(0, stb.length());
		}
		return answerList;
		
	}
}
