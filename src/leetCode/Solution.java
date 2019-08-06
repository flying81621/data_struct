package leetCode;

import java.util.Arrays;
import java.util.List;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 * 
 * @createTime 2018年4月19日 下午11:09:28
 * @author MrWang
 */
class Solution {
	public static void main(String[] args) {
		int[] nums1 = { 1, 3 };
		int[] nums2 = { 2, 4,7,8};
		double mediumNum = findMedianSortedArrays(nums1, nums2);
		System.out.println(mediumNum);
		
		String str = "abc0j1*j3#";
		str = str.replaceAll("1|\\*|0|#", "");
		System.out.println(str);
		
		letterCombinations("268");
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if(nums1.length == 0 && nums2.length != 0){
			if(nums2.length % 2 == 0){
				return (1.0 * nums2[nums2.length /2 - 1] + nums2[nums2.length /2]) / 2;
			} else {
				return nums2[nums2.length /2];
			}
		} else if(nums1.length != 0 && nums2.length == 0){
			if(nums1.length % 2 == 0){
				return (1.0 * nums1[nums1.length /2 - 1] + nums1[nums1.length /2]) / 2;
			} else {
				return nums1[nums1.length /2];
			}
		} else if (nums1.length == 0 && nums2.length == 0){
			return -1;
		}
		int totalLength = nums1.length + nums2.length;
		int index = 0;
		int index1 = 0;
		int index2 = 0;
		if (totalLength % 2 == 0) {
			int mediumNum1 = nums1[0];
			int mediumNum2 = nums2[0];
			int mediumIndex = totalLength / 2;
			while (index <= mediumIndex - 1) {
				if (index1 < nums1.length && index2 < nums2.length) {
					if (nums1[index1] < nums2[index2]) {
						mediumNum1 = nums1[index1];
						index1++;
					} else {
						mediumNum1 = nums2[index2];
						index2++;
					}
				} else if (index1 < nums1.length) {
					mediumNum1 = nums1[index1];
					index1++;
				} else {
					mediumNum1 = nums2[index2];
					index2++;
				}
				index++;
			}
			if(index1 < nums1.length && index2 < nums2.length){
				mediumNum2 = nums1[index1] < nums2[index2] ? nums1[index1] : nums2[index2];
			} else if (index1 < nums1.length) {
				mediumNum2 = nums1[index1];
			} else {
				mediumNum2 = nums2[index2];
			}
			return (mediumNum1 * 1.0 + mediumNum2) / 2;

		} else {
			int flag = nums1[0] < nums2[0] ? 1 : 2;
			int mediumNum1 = nums1[0];
			int mediumIndex = totalLength / 2;
			while (index <= mediumIndex) {
				if (index1 < nums1.length && index2 < nums2.length) {
					if (nums1[index1] < nums2[index2]) {
						flag = 1;
						mediumNum1 = nums1[index1];
						index1++;
					} else {
						flag = 2;
						mediumNum1 = nums2[index2];
						index2++;
					}
				} else if (index1 < nums1.length) {
					flag = 1;
					mediumNum1 = nums1[index1];
					index1++;
				} else {
					flag = 2;
					mediumNum1 = nums2[index2];
					index2++;
				}
				index++;
			}
			return mediumNum1;
		}
	}
	
	
	public static List<String> letterCombinations(String digits) {
		String[] digitMap = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		digits.replaceAll("1|\\*|0|#", "");
        char[] charArray = digits.toCharArray();
        char[][] tempStr = new char[charArray.length][];
        int resultLength = 1;
        for(int i = 0; i < charArray.length; i++){
        	tempStr[i] = digitMap[charArray[i] - 2 - 48].toCharArray();
        	resultLength *= tempStr[i].length;
        }
        for (char[] cs : tempStr) {
			System.out.println(Arrays.toString(cs));
		}
        String[] resultTemp = new String[resultLength];
        
        return null;
    }
	
}