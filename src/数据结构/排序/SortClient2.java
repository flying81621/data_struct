package 数据结构.排序;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * 继续测试各种排序
 * @createTime 2018年4月11日 下午7:02:35
 * @author MrWang
 */
public class SortClient2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random r = new Random();
		testMostNum(r);
	}
	
	private static void testMostNum(Random r){
//		int[] arr = {49, 38, 65, 30, 54, 97, 13, 76, 27};
		int[] arr = new int[10000000];
		for(int i  = 0; i < arr.length; i++){
			arr[i] = r.nextInt(10000000);
		}
		long startNanoTime = System.nanoTime();
		int[] result = SortUtils.mostNum(arr, 10000);
		long endNanoTime = System.nanoTime();
		System.out.println("耗时：" + (endNanoTime - startNanoTime) + "纳秒");
		System.out.println("耗时：" + (endNanoTime - startNanoTime)/1000000 + "毫秒");
		System.out.println(Arrays.toString(result));
		
	}
	
	private static void testSort() {
		Integer[] arr = {49, 38, 65, 30, 54, 97, 13, 76, 27};
		Arrays.sort(arr, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2.compareTo(o1);
			}
		});
	}

}
