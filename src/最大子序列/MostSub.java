package 最大子序列;

/**
 * 求一个数组的最大子序列
 * @author MrWang
 */
public class MostSub {
	
	public static void main(String[] args) {
		int[] arr = new int[]{2,-8,-6,3,-1,9,-8,-7,9,-4,5};
		int max = arr[0];
		int sum = arr[0];
		int startIndex = 0;
		int endIndex = 0;
		int tempStart = 0;
		int tempEnd = 0;
		for(int i = 1; i < arr.length; i++) {
			if(sum < 0){
				sum = arr[i];
				tempStart = i;
				tempEnd = i;
			} else {
				sum += arr[i];
				tempEnd = i;
			}
			if(sum >= max){
				max = sum;
				startIndex = tempStart;
				endIndex = tempEnd;
			}
		}
		System.out.println("最大值：" + max + ",开始索引：" + startIndex + "，结束索引:" + endIndex);
		
	}

}
