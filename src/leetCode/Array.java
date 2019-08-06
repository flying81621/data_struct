package leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * leetCode中国，数组训练
 * 
 * @createTime 2018年4月22日 下午6:47:39
 * @author MrWang
 */
public class Array {
	public static void main(String[] args) {
		/*
		 * int[] nums1 = { 1, 2, 2 }, nums2 = { 2, 1, 2 }; long start =
		 * System.nanoTime(); int[] intersect = intersect2Ms(nums1, nums2); long
		 * end = System.nanoTime();
		 * System.out.println(Arrays.toString(intersect));
		 * System.out.println(end - start);
		 */

		// int[] plusOne = plusOneAgain(new int[]{4,3,2,1});
		// int[] plusOne = plusOneAgain(new int[]{9,9,9});
		// int[] plusOne = plusOneAgain(new int[]{0});
		/*
		 * int[] plusOne = plusOneAgain(new
		 * int[]{7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1
		 * ,1,1,7,4,0,0,6}); System.out.println(Arrays.toString(plusOne));
		 */

		/*
		 * int[] nums = {0, 1, 0, 3, 12}; moveZeroes(nums);
		 * System.out.println(Arrays.toString(nums));
		 */

		/*
		 * int[] nums = { 3, 2, 4 }; int[] sum = twoSum(nums, 6);
		 * System.out.println(Arrays.toString(sum));
		 */

		char[][] nums = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		boolean success = isValidSudoku(nums);
		System.out.println(success);

		char[][] testNums = { { '.', '.', '.', '.', '5', '.', '.', '1', '.' },
				{ '.', '4', '.', '3', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '3', '.', '.', '1' },
				{ '8', '.', '.', '.', '.', '.', '.', '2', '.' }, { '.', '.', '2', '.', '7', '.', '.', '.', '.' },
				{ '.', '1', '5', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '2', '.', '.', '.' },
				{ '.', '2', '.', '9', '.', '.', '.', '.', '.' }, { '.', '.', '4', '.', '.', '.', '.', '.', '.' } };
		boolean testTwo = isValidSudoku(testNums);
		System.out.println(testTwo);
	}

	/**
	 * 给定两个数组，写一个方法来计算它们的交集。 例如: 给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2,
	 * 2]. 注意： 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。 我们可以不考虑输出结果的顺序。 跟进:
	 * 如果给定的数组已经排好序呢？你将如何优化你的算法？ 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
	 * 如果nums2的元素存储在磁盘上，内存是有限的，你不能一次加载所有的元素到内存中，你该怎么办？
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int[] intersect(int[] nums1, int[] nums2) {
		Map map = new HashMap();
		Map resultMap = new HashMap();
		int[] arr = nums1.length > nums2.length ? nums1 : nums2;
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], (int) map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}
		int[] arr2 = arr == nums1 ? nums2 : nums1;
		int indexNum = 0;
		for (int i = 0; i < arr2.length; i++) {
			if (map.containsKey(arr2[i])) {
				if ((int) map.get(arr2[i]) > 0) {
					if (resultMap.containsKey(arr2[i])) {
						resultMap.put(arr2[i], (int) resultMap.get(arr2[i]) + 1);
					} else {
						resultMap.put(arr2[i], 1);
					}
					map.put(arr2[i], (int) map.get(arr2[i]) - 1);
					indexNum++;
				} else {
					map.remove(arr2[i]);
				}
			}
		}
		int[] resultArr = new int[indexNum];
		int index = 0;
		Set<Entry> entrySet = resultMap.entrySet();
		for (Entry entry : entrySet) {
			while ((int) entry.getValue() > 0) {
				resultArr[index++] = (int) entry.getKey();
				entry.setValue((int) entry.getValue() - 1);
			}
		}

		return resultArr;
	}

	/**
	 * 两毫秒的答案
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int[] intersect2Ms(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
			return new int[0];
		}

		Arrays.sort(nums1);
		Arrays.sort(nums2);

		int len1 = nums1.length;
		int len2 = nums2.length;
		int[] temp = new int[len1 < len2 ? len1 : len2];

		int j = 0, i = 0, k = 0;

		while (i < len1 && j < len2) {

			if (nums1[i] < nums2[j]) {
				i++;
			} else if (nums1[i] == nums2[j]) {
				temp[k++] = nums2[j];
				i++;
				j++;
			} else {
				j++;
			}
		}
		return Arrays.copyOf(temp, k);
	}

	/**
	 * 加一 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
	 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
	 * 
	 * 示例 1: 输入: [1,2,3] 输出: [1,2,4] 解释: 输入数组表示数字 123。 示例 2: 输入: [4,3,2,1] 输出:
	 * [4,3,2,2] 解释: 输入数组表示数字 4321。
	 * 
	 * [7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,
	 * 0,0,6]时出错，这个方法只适合数组长度短的情况
	 * 
	 * @param digits
	 * @return
	 */
	public static int[] plusOne(int[] digits) {
		int num = 0;
		int weishu = 1;
		int length = 0;
		for (int i = digits.length - 1; i >= 0; i--) {
			num += weishu * digits[i];
			length++;
			weishu *= 10;
		}
		num += 1;
		int[] result = null;
		if (num / weishu > 0) { // 说明现在的数已经比原来的位数多了一位 2750
			System.out.println(num);
			result = new int[length + 1];
		} else {
			result = new int[length];
		}
		for (int i = result.length - 1; i >= 0; i--) {
			result[i] = num % 10;
			num /= 10;
		}
		return result;
	}

	/**
	 * 接着上面的继续算
	 * 
	 * @param digits
	 * @return
	 */
	public static int[] plusOneAgain(int[] digits) {
		int i = digits.length - 1;
		for (; i >= 0; i--) {
			digits[i] = (digits[i] + 1) % 10;
			if (digits[i] != 0) {
				break;
			}
		}
		if (i == -1 && digits[0] == 0) {
			int[] result = new int[digits.length + 1];
			result[0] = 1;
			for (int j = 1; j < result.length; j++) {
				result[j] = digits[j - 1];
			}
			return result;
		} else {
			return digits;
		}
	}

	/**
	 * 给定一个数组 nums, 编写一个函数将所有 0 移动到它的末尾，同时保持非零元素的相对顺序。 例如， 定义 nums = [0, 1, 0,
	 * 3, 12]，调用函数之后， nums 应为 [1, 3, 12, 0, 0]。
	 * 
	 * 注意事项: 必须在原数组上操作，不要为一个新数组分配额外空间。 尽量减少操作总数。
	 * 
	 * @param nums
	 */
	public static void moveZeroes(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}

		int index = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[index++] = nums[i];
			}
		}
		while (index < nums.length) {
			nums[index++] = 0;
		}
	}

	/**
	 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
	 * 
	 * 示例: 给定 nums = [2, 7, 11, 15], target = 9 因为 nums[0] + nums[1] = 2 + 7 = 9
	 * 所以返回 [0, 1]
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return new int[0];
		}
		int[] result = new int[2];
		for (int i = 0; i < nums.length - 1; i++) {
			int current = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				if (current + nums[j] == target) {
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
		}
		return new int[0];
	}

	/**
	 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
	 * 
	 * 数字 1-9 在每一行只能出现一次。 数字 1-9 在每一列只能出现一次。 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次
	 * 
	 * @param board
	 * @return
	 */
	public static boolean isValidSudoku(char[][] board) {
		Set set = new HashSet();
		int symmetry = 0;

		for (int i = 0, j = 0; i < 9; i++, j++) {
			// 按行判断
			char[] sub = board[i];
			for (int k = 0; k < 9; k++) {
				if (set.contains(sub[k]) && sub[k] != '.') {
					return false;
				} else {
					set.add(sub[k]);
				}
			}
			set.clear();

			// 按列判断
			for (int k = 0; k < 9; k++) {
				char c = board[k][j];
				if (set.contains(c) && c != '.') {
					return false;
				} else {
					set.add(c);
				}
			}
			set.clear();

			// 九宫格判断
			if (i == symmetry) {
				int temp = symmetry + 3;
				int start = symmetry;
				int end = symmetry + 3;

				// 横向判断
				for (int m = start; start < 9 && m < end; m++) {
					int n = symmetry;
					for (; n < temp; n++) {
						char c = board[n][m];
						if (set.contains(c) && c != '.') {
							return false;
						} else {
							set.add(c);
						}
					}
					if (m == end - 1) {
						set.clear();
						start += 3;
						end += 3;
					}
				}

				// 纵向判断
				start = symmetry + 3;
				end = symmetry + 6;
				for (int m = start; start < 9 && m < end; m++) {
					int n = symmetry;
					for (; n < temp; n++) {
						char c = board[m][n];
						if (set.contains(c) && c != '.') {
							return false;
						} else {
							set.add(c);
						}
					}
					if (m == end - 1) {
						set.clear();
						start += 3;
						end += 3;
					}
				}

				symmetry += 3;
				temp = symmetry + 3;
			}
		}

		return true;
	}
	
	/**
	 * 九宫格判断最快的方法
	 * @param board
	 * @return
	 */
	public boolean isValidSudokuMostFast(char[][] board) {
        boolean[][][] has = new boolean[3][9][9];
        int tmp;
        for(int i = 0 ; i < 9 ; i++) {
            for(int j = 0 ; j < 9 ; j++) {
                if(board[i][j]=='.') {
                    continue;
                }else {
                    tmp = board[i][j] - '1';    
                }
                if(has[0][i][tmp]||has[1][j][tmp]||has[2][3*(i/3)+j/3][tmp]){
                    return false;
                }else{
                    has[0][i][tmp] = true;
                    has[1][j][tmp] = true;
                    has[2][3*(i/3)+j/3][tmp] = true;
                }
            }
        }
        return true;
    }

}
