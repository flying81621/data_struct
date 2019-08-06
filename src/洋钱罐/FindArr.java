package 洋钱罐;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 给定一个数，数组，求和等于给定的数的组合
 * 
 * @author wangyafei
 */
public class FindArr {
	public static void main(String[] args) {
		int[] arr = new int[] { 3, 6, 5, -1, 12, 10, 9, 18, -3, 4 };
		int[][] newArr = find(arr, 10);
		for (int i = 0; i < newArr.length; i++) {
			System.out.println(Arrays.toString(newArr[i]));
		}
		System.out.println("========分界线==========");

	}

	public static int[][] find(int[] arr, int target) {
		long num = (long) Math.scalb(1, arr.length);
		System.out.println(Long.toBinaryString(num - 1));
		int[][] returnTempArr = new int[(int) (num - 1)][];
		int arrIndex = 0;
		for (int i = 1; i < num; i++) {
			String binaryString = Long.toBinaryString(i);
			char[] charArray = binaryString.toCharArray();
			int lastIndex = binaryString.lastIndexOf('1');
			int tempSum = arr[charArray.length - 1 - lastIndex];
			int[] resultTempArr = new int[charArray.length];
			int indexNum = 0;
			resultTempArr[indexNum++] = tempSum;
			for (int j = lastIndex - 1; j >= 0; j--) {
				if (charArray[j] == '1') {
					tempSum += arr[charArray.length - 1 - j];
					resultTempArr[indexNum++] = arr[charArray.length - 1 - j];
				}
			}
			if (tempSum == target) {
				int[] resultArr = new int[indexNum];
				for (int j = 0; j < indexNum; j++) {
					resultArr[j] = resultTempArr[j];
				}
				returnTempArr[arrIndex++] = resultArr;
			}
		}
		int[][] returnArr = new int[(int) (arrIndex)][];
		for (int i = 0; i < arrIndex; i++) {
			returnArr[i] = returnTempArr[i];
		}
		return returnArr;
	}
	
	public static int[][] findRecursion(int[] arr, int target) {
		int[][] resultTempArr = new int[arr.length][];
		int result = recursion(arr, 0, true);
		if(result == target) {
			
		}
		recursion(arr, 0, false);
		return null;
	}

	/**
	 * 未完待续。。。。。
	 * @param arr
	 * @param i
	 * @param b
	 */
	private static int recursion(int[] arr, int i, boolean b) {
		// TODO Auto-generated method stub
		return 0;
	}

}
