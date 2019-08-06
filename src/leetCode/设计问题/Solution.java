package leetCode.设计问题;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @createTime 2018年4月29日 下午2:19:21
 * @author MrWang
 */
public class Solution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Your Solution object will be instantiated and called as such:
		// 以数字集合 1, 2 和 3 初始化数组。
		int[] nums = { 1, 2, 3 };
		Solution obj = new Solution(nums);
		int[] param_1 = obj.shuffle();
		System.out.println(Arrays.toString(param_1));
		int[] param_2 = obj.reset();
		System.out.println(Arrays.toString(param_2));

	}

	// 创建一个备忘录
	private int[] memos;

	// 这个是内部属性
	private int[] nums;

	public Solution(int[] nums) {
		this.nums = nums;
		memos = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			memos[i] = nums[i];
		}
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		return memos;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		Random r = new Random();
		for (int i = 0; i < this.nums.length; i++) {
			int index = r.nextInt(this.nums.length);
			if (i != index) {
				nums[i] = nums[i] ^ nums[index];
				nums[index] = nums[i] ^ nums[index];
				nums[i] = nums[i] ^ nums[index];
			}
		}
		return nums;
	}

}
