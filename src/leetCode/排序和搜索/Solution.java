package leetCode.排序和搜索;

import java.util.Arrays;

/**
 * 
 * @createTime 2018年4月29日 下午2:59:27
 * @author MrWang
 */
public class Solution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums1 = {1,2,4,5,6,0};
		int[] nums2 = {3};
		merge(nums1, 5, nums2, 1);
		System.out.println(Arrays.toString(nums1));
		
	}
	
	/**
	 * 合并两个有序数组
		给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
		说明:
		初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
		你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
		示例:
		
		输入:
		nums1 = [1,2,3,0,0,0], m = 3
		nums2 = [2,5,6],       n = 3
		
		输出: [1,2,2,3,5,6]
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		if(n == 0) {
			return;
		}
		int num = m + n;
        for(int i = num - 1; i >= n; i--){
        	nums1[i] = nums1[i - n];
        }
        int index1 = n; 
        int index2 = 0;
        for(int i = 0; i < num; i++){
        	if(index1 >= num){
        		nums1[i] = nums2[index2++];
        	} else if (index2 >= n) {
        		nums1[i] = nums1[index1++];
        	} else {
        		if(nums1[index1] < nums2[index2]){
        			nums1[i] = nums1[index1++];
        		} else {
        			nums1[i] = nums2[index2++];
        		}
        	}
        }
    }
	
	/**
	 * 一种更好地解法，通过从后向前进行最大值的判断进行
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void mergeBetter(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m - 1,
             len2 = n - 1,
             lenTotal = m + n -1;

         while(len1 >= 0 && len2 >= 0) {
             if(nums1[len1] >= nums2[len2]) {
                 nums1[lenTotal--] = nums1[len1--];
             } else {
                 nums1[lenTotal--] = nums2[len2--];
             }
         }
        //  就是nums2还有剩余的元素
         if(len1 == -1) {
             while(len2 >= 0) {
                 nums1[lenTotal--] = nums2[len2--];
             }
         }
    }

}
