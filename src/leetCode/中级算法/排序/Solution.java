package leetCode.中级算法.排序;

import java.util.Random;

/**
 * 
 * @createTime 2018年5月16日 下午11:01:41
 * @author MrWang
 */
public class Solution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solution solu = new Solution();
		long start = System.currentTimeMillis();
		int[] nums = {3,2,3,1,2,50,18,7,19};
		System.out.println(findKthLargest3(nums, 7));
		for(int i = 0; i < 1; i++){
			nums = makeArr(100000000);
			int findKthLargest = solu.findKthLargest3(nums, nums.length / 3);
//			System.out.println(findKthLargest);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	private static int[] makeArr(int length) {
		Random r = new Random();
		int[] arr = new int[length];
		for(int i = 0; i < length; i++){
			arr[i] = r.nextInt((int) (length * 1.5));
		}
		return arr;
	}


	/**
	 * 数组中的第K个最大元素
	在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
	
	示例 1:
	
	输入: [3,2,1,5,6,4] 和 k = 2
	输出: 5
	示例 2:
	
	输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
	输出: 4
	说明:
	
	你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
	 */
	public int findKthLargest(int[] nums, int k) {
		if(k > nums.length){
			return -1;
		}
		int target = -1;
		if(k <= nums.length / 2){
			//构建最大堆
			for(int i = nums.length / 2 - 1; i >= 0; i--){
	        	percolateDown(nums, i, nums.length);
	        }
			//连续pop k次即可
			for(int i = 1; i <= k; i++){
				if(i != k){
					nums[0] = nums[nums.length - i];
					percolateDown(nums, 0, nums.length - i);
				} else {
					target = nums[0];
				}
			}
		} else {
			//构建最小堆
			for(int i = nums.length / 2 - 1; i >= 0; i--){
				percolateDownMin(nums, i, nums.length);
			}
			//连续pop nums.length - k + 1次即可
			for(int i = 1; i <= nums.length - k + 1; i++){
				if(i != nums.length - k + 1){
					nums[0] = nums[nums.length - i];
					percolateDownMin(nums, 0, nums.length - i);
				} else {
					target = nums[0];
				}
			}
		}
        return target;
    }
	
	private void percolateDown(int[] arr, int start, int num){
		int hole = start;
		int child = 2 * hole + 1;
		while(child <num){
			if(child < num - 1 && arr[child] < arr[child + 1]){
				child++;
			}
			if(arr[child] > arr[hole]){
				arr[child] = arr[child] ^ arr[hole];
				arr[hole] = arr[child] ^ arr[hole];
				arr[child] = arr[child] ^ arr[hole];
			}
			hole = child;
			child = 2 * hole + 1;
		}
	}
	
	private void percolateDownMin(int[] arr, int start, int num){
		int hole = start;
		int child = 2 * hole + 1;
		while(child <= num - 1){
			if(child != num - 1 && arr[child] > arr[child + 1]){
				child++;
			}
			if(arr[child] < arr[hole]){
				arr[child] = arr[child] ^ arr[hole];
				arr[hole] = arr[child] ^ arr[hole];
				arr[child] = arr[child] ^ arr[hole];
			}
			hole = child;
			child = 2 * hole + 1;
		}
	}


	/**
	 * 今日重做--寻找无序数组的第k大的值
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargest2(int[] nums, int k) {
		//构建前k的最小二叉树
		int start = k / 2;
		for(int i = start; i >= 0; i--){
			buildHeap(nums, k, i);
		}
		for(int i = k + 1; i < nums.length; i++){
			if(nums[i] > nums[0]){
				int temp = nums[i];
				nums[i] = nums[0];
				nums[0] = temp;
			}
			buildHeap(nums, k, 0);
		}
		return nums[0];
	}

	/**
	 * @param nums
	 * @param end 需要构建的数组长度
	 * @param start 开始位置
	 */
	private void buildHeap(int[] nums, int end, int start) {
		int father = start;
		int child = father * 2 + 1;
		while(child < end){
			if(child < end - 1 && nums[child] > nums[child + 1]){
				child++;
			}
			if(nums[child] < nums[father]){
				int temp = nums[child];
				nums[child] = nums[father];
				nums[father] = temp;
			}
			father = child;
			child = father * 2 + 1;
		}
	}


	/**
	 *采用分治法来寻找
	 *
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int findKthLargest3(int[] nums, int k) {
		find(nums, k - 1, 0, nums.length - 1);
		return nums[k - 1];
	}

	/**
	 * 找到一个元素，这个元素满足，左边有numLeft比他大
	 *
	 * @param arr
	 * @param numLeft
	 * @param start
	 * @param end
	 * @return
	 */
	public static void find(int[] arr, int numLeft, int start, int end){
		if(start < end){
			int referencdIndex = start;
			int reference = arr[start];
			int referenceEnd = end;
			start = start + 1;
			while(start < end){
				while(arr[start] >= reference && start <= end){
					start++;
				}
				while(arr[end] <= reference && start <= end){
					end--;
				}
				if(start < end){
					swapElement(arr, start, end);
				}
			}
			//start == end 的时候说明reference和start/end上的元素是相等的，为了维持稳定性，不应该交换
			if(start > end){
				//左大右小时，交换左边的阈值点，相反反之,现在是左大右小，end处于start的左边
				swapElement(arr, referencdIndex, end);
			}
			if(end - referencdIndex == numLeft){
				return;
			}
			if(end - referencdIndex < numLeft){
				//表示前面已经有end - referencdIndex个元素比第一个元素大了，从右侧偏移一位出去，应再在右侧找numLeft - (end - referencdIndex) + 1个比右侧第一个元素大的
				find(arr, numLeft - (end - referencdIndex) + 1, end + 1, referenceEnd);
			} else {
				find(arr, numLeft, referencdIndex, end - 1);
			}
		}
	}

	private static void swapElement(int[] arr, int start, int end) {
		int temp = arr[start];
		arr[start] = arr[end];
		arr[end] = temp;
	}
}
