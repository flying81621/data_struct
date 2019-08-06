package 数据结构.排序;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序的测试类
 * @createTime 2018年2月9日 下午1:42:32
 * @author MrWang
 */
public class SortClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] arr = {4,23,7,28,5,46,8};
		SortUtils.insertionSort(arr);
		System.out.println(Arrays.toString(arr));
		
		int[] ar = {49, 38, 65, 30, 49, 54, 97, 13, 76, 27};
		exerciseQuickSort(ar, 0, ar.length - 1);
		System.out.println(Arrays.toString(ar));
		SortUtils.kuaiPaiZheDa(ar, 0, ar.length- 1);
		System.out.println(Arrays.toString(ar));
		ar = new int[]{3,48};
		exerciseQuickSort(ar, 0, ar.length - 1);
		System.out.println(Arrays.toString(ar));
		SortUtils.kuaiPaiZheDa(ar, 0, ar.length - 1);
		System.out.println(Arrays.toString(ar));
		
		
//		int[] a = {4,23,7,28,5,46,8,1,2};
		int[] a = new int[10000000];
		Random r = new Random();
		for(int i = 0; i < a.length; i++){
			a[i] = r.nextInt(10000000);
//			a[i] = i;
		}
		
//		SortUtils.maopaoSort(a);
//		SortUtils.zhiJiePaiXu(a);bbbbbbbbbbbbbbbbbbbb
		long l = System.currentTimeMillis();
//		SortUtils.quickSortWaKeng(a, 0, a.length - 1); 
//		SortUtils.kuaiPaiMuKeZheDa(a, 0, a.length - 1); 
		SortUtils.guibingSort(a); 
		long l1 = System.currentTimeMillis();
		System.out.println("排序耗时："+(l1 - l) + "毫秒");
		for(int i = 0; i < 100; i++){
			System.out.print(a[i] + " ");
			if(i % 20 == 0 && i != 0){
				System.out.println();
			}
		}
//		calculateWhoFast();
	}

	/**
	 * insertionSort  
	 * kuaiPai
	 * @return
	 */
	private static int calculateWhoFast(){
		
		int[] arr = new int[90];
		Random r = new Random();
		for(int i = 0; i < arr.length; i++){
			arr[i] = r.nextInt(100000000);
//			arr[i] = i;
		}
		
		long start = System.nanoTime();
		SortUtils.kuaiPai(arr, 0, arr.length - 1);
		long end = System.nanoTime();
		System.out.println(end - start);
		return 0;
	}

	public static void exerciseQuickSort(int[] arr, int start, int end){
		if(start < end){
			int base = arr[start];
			int leftIndex = start;
			int rightIndex = end + 1;
			while(rightIndex > leftIndex){
				while(--rightIndex > leftIndex && arr[rightIndex] >= base){
				}
				while(++leftIndex < rightIndex && arr[leftIndex] < base){
				}
				//前后交换
				if(rightIndex > leftIndex){
					int temp = arr[leftIndex];
					arr[leftIndex] = arr[rightIndex];
					arr[rightIndex] = temp;
				}
			}
			if(rightIndex > start){
				int temp = arr[start];
				arr[start] = arr[rightIndex];
				arr[rightIndex] = temp;
			}
			exerciseQuickSort(arr, start, rightIndex - 1);
			exerciseQuickSort(arr, rightIndex + 1, end);
		}
	}
}
