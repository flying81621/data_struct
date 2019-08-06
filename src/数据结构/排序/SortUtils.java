package 数据结构.排序;

/**
 * 各种排序法的工具类,除了第一个采用泛型的任意的元素的排序，其余的直接采用了int数组进行测试,但都可以转换为任意类型的排序 全部做一个升序
 * 
 * @createTime 2018年2月9日 下午1:42:58
 * @author MrWang
 */
public class SortUtils {

	/**
	 * 插入排序的实现 时间复杂度为o（n*n)
	 * 
	 * @param a
	 *            任何实现了comparable的类的数组
	 */
	public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
		for (int i = 1; i < a.length; i++) {
			AnyType temp = a[i];
			for (int j = i; j > 0 && temp.compareTo(a[j - 1]) < 0; j--) {
				a[j] = a[j - 1];
				a[j - 1] = temp;
			}
		}
	}

	/**
	 * 希尔排序，也成为增量排序 时间复杂度取决于增量的选择 这里分别采用hill增量（最坏
	 * O(N^2)），hibbard增量(最坏O(N^(3/2)),最坏(O(N^(5/4)),进行测试
	 * hill增量：arr.length/2,然后依次递减除以2 hibbard增量：2的幂次方-1（最大的小于数组长度的值），依次递减
	 * 
	 * @param arr
	 */
	public static void hillSort(int[] arr) {
		// 外层控制增量的递减
		for (int i = arr.length / 2; i > 0; i = i / 2) {
			// 内层采用插入排序
			for (int j = i; j < arr.length; j++) {
				for (int k = j; k - i >= 0 && arr[k] < arr[k - i]; k -= i) {
					int temp = arr[k];
					arr[k] = arr[k - i];
					arr[k - i] = temp;
				}
			}
		}
	}

	/**
	 * 冒泡排序
	 * 
	 * @param arr
	 */
	public static void maopaoSort(int[] arr) {
		// 外层定义比较次数
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 直接排序，记录位置交换,也称选择排序
	 * 
	 * @param arr
	 */
	public static void zhiJiePaiXu(int[] arr) {
		for (int k = 0; k < arr.length - 1; k++) {
			int index = k;
			for (int i = k + 1; i < arr.length; i++) {
				if (arr[index] > arr[i]) {
					index = i;
				}
			}
			if (index != k) {
				int temp = arr[k];
				arr[k] = arr[index];
				arr[index] = temp;
			}
		}
	}

	/**
	 * 插入排序
	 * 
	 * @param arr
	 * @param start
	 *            开始的位置
	 * @param end
	 *            结束的位置
	 */
	public static void insertionSort(int[] arr, int start, int end) {
		if (start < end) {
			for (int i = start + 1; i <= end; i++) {
				for (int j = i; j > start && arr[j] < arr[j - 1]; j--) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}
			}
		}
	}

	/**
	 * 堆排序 每次从开始元素构建最大堆，然后将最大堆的元素和数组的最后一个元素进行交换，然后向前迭代
	 * 
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		//这个是建立最大堆的过程，arr.length / 2 - 1  这个位置是第一个有子树的位置,因为这个数组下标是从0开始，因此要往前挪一位
		for (int i = arr.length / 2 - 1; i >= 0; i--) { 
			percolateDown(arr, i, arr.length);
		}
		//把最大堆的第一个元素（最大值）挪到最后去  j>0 表示在只有一个元素的时候，经过这个过程后，第一个元素已经是最小的了，没必要再做操作了
		for (int j = arr.length - 1; j > 0; j--) {
			arr[0] = arr[0] ^ arr[j];
			arr[j] = arr[0] ^ arr[j];
			arr[0] = arr[0] ^ arr[j];
			percolateDown(arr, 0, j);
		}
	}

	/**
	 * 将hole开始的元素进行上滤使得hole这个位置是最大的元素,前提假设hole的两个儿子已经是最大堆了
	 * @param arr
	 * @param hole 表示要上滤这个节点的子元素
	 * @param num 表示上滤元素从0开始的数量
	 */
	private static void percolateDown(int[] arr, int hole, int num) {
		// 首先保证要上滤的元素确实有子元素，否则还上滤什么
		int child = hole * 2 + 1;
		while(child <= num - 1){  //num - 1是最后一个有意义的索引位置
			if(child < num - 1 && arr[child] < arr[child + 1]){
				child++;
			}
			if (arr[hole] < arr[child]) {
				arr[hole] = arr[hole] ^ arr[child];
				arr[child] = arr[hole] ^ arr[child];
				arr[hole] = arr[hole] ^ arr[child];
			} else {
				break;
			}
			//因为交换父子之后，现在子位置就不一定比它的两个儿子都大了，需要继续过滤
			hole = child;
			child = hole * 2 + 1;
		}
	}
	
	
	//======================开始归并排序
	/**
	 * 归并排序
	 * @param arr
	 */
	public static void guibingSort(int[] arr) {
		//递归方式
//		guibingSortRecursion(arr, new int[arr.length], 0, arr.length - 1);
		
		//非递归方式
		guibingSortImrecursion(arr);
	}
	
	/**
	 * 归并排序--非递归的实现方式
	 * @param arr
	 */
	public static void guibingSortImrecursion(int[] arr) {
		int length = 1;
		int[] tmp = new int[arr.length];
		while(length < arr.length){
			guibingSortImrecursion(arr, tmp, arr.length, length);
			length *= 2;
			//在反着来将tmp归并到arr中
			guibingSortImrecursion(tmp, arr, arr.length, length);
			length *= 2;
		}
	}
	
	/**
	 * 归并排序--非递归的实现方式
	 * @param arr
	 * @param tmp
	 * @param num 这个是表示排序的元素的数量
	 * @param length 这个是每个子序列的长度
	 */
	public static void guibingSortImrecursion(int[] arr, int[] tmp, int num, int length) {
		//因为不能判断num可以分为多少个片段，所有预留两个位置出来，单独处理
		int i = 0;
		for(i = 0; i <= num - length * 2; i += length * 2){
			guibingSortRecursionMerge(arr, tmp, i, i + length - 1, i + length * 2 - 1);
		}
		//说明剩下两个片段的时候，而最后一个子序列可能长度还是小于length，所以结束条件应该置为num-1
		if(i + length < num){
			guibingSortRecursionMerge(arr, tmp, i, i + length - 1, num - 1);
		} else{
			//说明就剩下一个片段了，直接归到tmp中即可
			for(int j = i; j < num; j++){
				tmp[j] = arr[j];
			}
		}
	}
	
	/**
	 * 归并排序--递归的实现方式
	 * @param arr
	 * @param tmp
	 * @param left
	 * @param right
	 */
	public static void guibingSortRecursion(int[] arr, int[] tmp, int left, int right){
		if (left < right) {
			int baseIndex = (left + right) / 2;
			guibingSortRecursion(arr, tmp, left, baseIndex);
			guibingSortRecursion(arr, tmp, baseIndex + 1, right);
			//经过上面两步后，现在baseIndex两边的数组已经是有序的了，开始进行左右的合并
			guibingSortRecursionMerge(arr, tmp, left, baseIndex, right);
		}
	}
	
	/**
	 * 归并排序--递归的实现方式  这个是负责将左右有序数组进行合并的方式
	 * @param arr 原数组
	 * @param tmp 临时数组
	 * @param left 归并开始的左边的节点
	 * @param right 归并开始的左边的结束的节点
	 * @param rightEnd 归并开始的右边的结束的节点
	 */
	public static void guibingSortRecursionMerge(int[] arr, int[] tmp, int left, int right, int rightEnd) {
		int leftStart = left;    //左边的数组的开始节点索引
		int rightStart = right + 1;   //右边的数组的开始节点索引
		for(int i = left; i <= rightEnd; i++){
			if(leftStart > right){
				while(rightStart <= rightEnd) {
					tmp[i++] = arr[rightStart++];
				}
				break;
			}
			if(rightStart > rightEnd) {
				while(leftStart <= right) {
					tmp[i++] = arr[leftStart++];
				}
				break;
			}
			if(arr[leftStart] <= arr[rightStart]){
				tmp[i] = arr[leftStart++];
			} else {
				tmp[i] = arr[rightStart++];
			}
		}
		//写回原来的数组
		for(int i = left; i <= rightEnd; i++){
			arr[i] = tmp[i];
		}
		
	}  
	

	/**
	 * 快排--挖坑填数
	 */
	public static void quickSortWaKeng(int[] arr, int start, int end) {
		if (end > start) {
			int base = arr[start];
			int i = start;
			int j = end;
			while (i < j) {
				while (i < j && arr[j] >= base) {
					j--;
				}
				if (j > i) {
					arr[i] = arr[j];
 					i++;
				}
				while (i < j && arr[i] <= base) {
					i++;
				}
				if (j > i) {
					arr[j] = arr[i];
					j--;
				}
			}
			arr[i] = base;
			quickSortWaKeng(arr, start, i - 1);
			quickSortWaKeng(arr, i + 1, end);
		}
	}

	/**
	 * 快排--慕课浙大原版  升序
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 */
	public static void kuaiPaiZheDa(int[] arr, int start, int end) {
		if (end > start) {
			int base = arr[start];
			int i = start;
			int j = end + 1;
			while (j > i) {
				while (--j > i && arr[j] >= base) {
				}
				while (++i < j && arr[i] < base) {
				}
				if (j > i) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
			//基数交换
			if (j > start) {
				arr[start] = arr[j];
				arr[j] = base;
			}
			kuaiPaiZheDa(arr, start, j - 1);
			kuaiPaiZheDa(arr, j + 1, end);
		}
	}

	/**
	 * 快排--慕课浙大的思想
	 * 
	 * @param arr
	 * @param start 开始的索引
	 * @param end 结束的索引
	 */
	public static void kuaiPaiMuKeZheDa(int[] arr, int start, int end) {
		if (end > start) {
			int base = arr[start];
			int i = start + 1;
			int j = end;
			while (i <= j) {
				while (i <= j && arr[j] >= base) {
					j--;
				}
				while (i <= j && arr[i] <= base) {
					i++;
				}
				if (i < j) { // 只有在i和j处于一前一后的时候才进行交换位置
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					i++;
					j--;
				} else {
					break;
				}
			}
			// 开始将基准数归位，现在基准数在start的位置上,确保在j不处于基准位置的时候，不做无用的交换工作
			if (start < j) {
				arr[start] = arr[j];
				arr[j] = base;
			}
			kuaiPaiMuKeZheDa(arr, start, j - 1);
			kuaiPaiMuKeZheDa(arr, i, end);
		}
	}

	private static final int THRESHOLD = 100;

	/**
	 * 我的快速排序，结合插入排序进行(可以降低栈的方法区)
	 */
	public static void kuaiPai(int[] arr, int start, int end) {
		if (start < end) {
			if (end - start > THRESHOLD) {
				int base = median(arr, start, end);
				int i = start + 1;
				// 经过median操作后，现在的基准位于end-1的位置上，end上的元素比end-1上的元素大,第一个数也就是start上的数一定比base小
				int j = end - 2;
				while (i <= j) {
					while (i < end && arr[i] <= base) {
						i++;
					}
					while (j > start && arr[j] >= base) {
						j--;
					}
					if (i < j) {
						int temp = arr[i];
						arr[i] = arr[j];
						arr[j] = temp;
						i++;
						j--;
					} else {
						break;
					}
				}
				// 现在基元处于end-1的位置上，开始把基元交换到正确的位置上,现在从start到j的元素都是比基元base小的元素，从i到end（除了基元）都是比base大的元素
				if (end - 1 > i) {
					// 只有i这个位置确实在end-1（基元的位置）之前的时候才进行交换位置
					int temp = arr[end - 1];
					arr[end - 1] = arr[i];
					arr[i] = temp;
				}

				kuaiPai(arr, start, j);
				kuaiPai(arr, i + 1, end);
			} else {
				insertionSort(arr, start, end);
			}
		}
	}

	/**
	 * 这个方法主要是找出一个作为快排的比较的基准值出来，这个值选为最左边值、最右边值、中间位置的值的中间值，于是这个值肯定不是这三个中最大的，
	 * 做了这个操作之后，将最小值放在最左边，最大值放在最右边，作为参考的值放在最右边的左边，也就是end-1的位置
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	private static int median(int[] arr, int start, int end) {
		if (start >= end) {
			return arr[start];
		}
		// 两个元素的时候的情况
		if (end - start == 1 && arr[start] > arr[end]) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			return arr[start];
		}

		if (arr[start] > arr[(start + end) / 2]) {
			int temp = arr[start];
			arr[start] = arr[(start + end) / 2];
			arr[(start + end) / 2] = temp;
		}
		if (arr[start] > arr[end]) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
		}
		if (arr[(start + end) / 2] > arr[end]) {
			int temp = arr[end];
			arr[end] = arr[(start + end) / 2];
			arr[(start + end) / 2] = temp;
		}
		int temp = arr[end - 1];
		arr[end - 1] = arr[(start + end) / 2];
		arr[(start + end) / 2] = temp;
		return arr[end - 1];
	}

	public static void quickSortZhiHu(int[] numbers, int start, int end) {
		if (start < end) {
			int base = numbers[start]; // 选定的基准值（第一个数值作为基准值）
			int temp; // 记录临时中间值
			int i = start, j = end;
			do {
				while ((numbers[i] < base) && (i < end))
					i++;
				while ((numbers[j] > base) && (j > start))
					j--;
				if (i <= j) {
					temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
					i++;
					j--;
				}
			} while (i <= j);
			if (start < j)
				quickSortZhiHuOrigin(numbers, start, j);
			if (end > i)
				quickSortZhiHuOrigin(numbers, i, end);
		}
	}

	/**
	 * 快排--知乎原版 这个的特点是：每次基准值最后没有放在中间
	 * 
	 * @param numbers
	 * @param start
	 * @param end
	 */
	public static void quickSortZhiHuOrigin(int[] numbers, int start, int end) {
		if (start < end) {
			int base = numbers[start]; // 选定的基准值（第一个数值作为基准值）
			int temp; // 记录临时中间值
			int i = start, j = end;
			do {
				while ((numbers[i] < base) && (i < end))
					i++;
				while ((numbers[j] > base) && (j > start))
					j--;
				if (i <= j) {
					temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
					i++;
					j--;
				}
			} while (i <= j);
			if (start < j)
				quickSortZhiHuOrigin(numbers, start, j);
			if (end > i)
				quickSortZhiHuOrigin(numbers, i, end);
		}
	}

	/**
	 * 快排 -- 卢帅
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 */
	public static void quickSortLu(int[] arr, int start, int end) {
		if (start < end) {
			int baseNum = arr[start];
			int i = start;
			int j = end;
			while (i < j) {
				while (arr[j] >= baseNum && i < j)
					j--;
				arr[i] = arr[j];
				i++;
				while (arr[i] <= baseNum && i < j)
					i++;
				arr[j] = arr[i];
			}
			arr[i] = baseNum;
			quickSortLu(arr, start, i - 1);
			quickSortLu(arr, i + 1, end);
		}
	}

	/**
	 * @author wangyafei
	 * @createdTime ����12:04:56
	 * @description TODO 我的伪快排
	 * @param arr
	 * @param start
	 * @param end
	 */
	public static void kuaisupaixu(int[] arr, int start, int end) {
		if (start >= end)
			return;
		int m = start;
		for (int i = start + 1; i <= end; i++) {
			if (arr[m] > arr[i]) {
				int temp = arr[i];
				for (int j = i; j > m; j--) {
					arr[j] = arr[j - 1];
				}
				arr[m++] = temp;
			}
		}
		kuaisupaixu(arr, start, m - 1);
		kuaisupaixu(arr, m + 1, end);
	}

	/**
	 * 快排---百度百科
	 * 
	 * @param a
	 * @param low
	 * @param high
	 */
	public static void quickSortBaiKe(int[] arr, int low, int high) {
		int start = low;
		int end = high;
		int base = arr[low];

		while (start < end) {
			while (start < end && arr[end] >= base)
				end--;
			if (start < end) {
				int temp = arr[end];
				arr[end] = arr[start];
				arr[start] = temp;
				start++;
			}

			while (start < end && arr[start] <= base)
				start++;
			if (start < end) {
				int temp = arr[end];
				arr[end] = arr[start];
				arr[start] = temp;
				end--;
			}
		}
		// print(arr);
		// System.out.print("l="+(l+1)+"h="+(h+1)+"povit="+povit+"\n");
		if (start > low)
			quickSortBaiKe(arr, low, start - 1);
		if (end < high)
			quickSortBaiKe(arr, start + 1, high);
	}

	/**
	 * 快排2
	 * 
	 * @param a
	 * @param low
	 * @param high
	 */
	public static void sort2(int[] a, int low, int high) {
		int start = low;
		int end = high;
		int key = a[low];

		while (end > start) {
			// 从后往前比较
			while (end > start && a[end] >= key) // 如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
				end--;
			if (a[end] <= key) {
				int temp = a[end];
				a[end] = a[start];
				a[start] = temp;
			}
			// 从前往后比较
			while (end > start && a[start] <= key) // 如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
				start++;
			if (a[start] >= key) {
				int temp = a[start];
				a[start] = a[end];
				a[end] = temp;
			}
			// 此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
		}
		// 递归
		if (start > low)
			sort2(a, low, start - 1);// 左边序列。第一个索引位置到关键值索引-1
		if (end < high)
			sort2(a, end + 1, high);// 右边序列。从关键值索引+1到最后一个
	}
	
	/**
	 * 选出最大的num个元素
	 * 其实一开始的快拍做不做都无所谓了，只不过当num大的时候，先做一次快排速度会快一些
	 * @param arr
	 * @param num  等于1000时是20毫秒左右，10000时是500毫秒左右，100000时已经出不来结果了
	 * @return int[] 
	 */
	public static int[] mostNum(int[] arr, int num){
		int[] result = new int[num];
		for(int i = 0; i < result.length; i++){
			result[i] = arr[i];
		}
		//先调用快排把这个基本数组拍个升序
		kuaiPaiZheDaDesc(result, 0, result.length - 1);
		for(int i = result.length; i < arr.length; i++){
			int target = arr[i];
			if(target > result[result.length - 1]){
				for(int j = result.length - 2; j >=0; j-- ){
					if(target > result[j]){
						result[j + 1] = result[j];
					} else {
						result[j + 1] = target;
						break;
					}
					if(j == 0){   //这说明已经把第一个都给比下去了，现在这个外来入侵数字是最大的
						result[j] = target;
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 选出最大的num个元素
	 * 其实一开始的快拍做不做都无所谓了，只不过当num大的时候，先做一次快排速度会快一些
	 * @param arr
	 * @param num
	 * @return int[] 
	 */
	public static int[] mostNumWithKuaiPai(int[] arr, int num){
		int[] result = new int[num];
		for(int i = 0; i < result.length; i++){
			result[i] = arr[i];
		}
		//先调用快排把这个基本数组拍个升序
		kuaiPaiZheDaDesc(result, 0, result.length - 1);
		for(int i = result.length; i < arr.length; i++){
			int target = arr[i];
			if(target > result[result.length - 1]){
				result[result.length - 1] = target;
				kuaiPaiZheDaDesc(result, 0, result.length - 1);
			}
		}
		return result;
	}
	
	/**
	 * 快排  慕课浙大  逆序
	 * @param arr
	 * @param start
	 * @param end
	 */
	public static void kuaiPaiZheDaDesc(int[] arr, int start, int end) {
		if (end > start) {
			int base = arr[start];
			int i = start;
			int j = end + 1;
			for (;;) {
				while (--j >= i && arr[j] < base) {
				}
				while (++i <= j && arr[i] > base) {
				}
				if (j > i) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				} else {
					break;
				}
			}
			if (j > start) {
				arr[start] = arr[j];
				arr[j] = base;
			}
			kuaiPaiZheDaDesc(arr, start, j - 1);
			kuaiPaiZheDaDesc(arr, j + 1, end);
		}
	}

}
