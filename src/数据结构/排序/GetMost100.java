package 数据结构.排序;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

/**
 * * 思路：
 * 1、生成数据
 * 2、将数据分组
 * 3、拿出每组前十个不重复数据，排序后，与其他数据进行比较
 * 4、比前十个最小的大，则放入，并删除最小的一个，始终保持只有十个数据
 * 5、将数据汇总，取出最终最大的十个
 * 
 * @createTime 2018年4月12日 下午4:57:48
 * @author MrWang
 */
public class GetMost100 {
	private static final int NUM_AMOUNT = 10000000;	//数据量
	private static int[] arr = new int[NUM_AMOUNT];		//原数据
	private static HashMap<Thread, TreeSet<Integer>> map = new HashMap<>();		//存放每个线程获取的前十个最大数
	private static CountDownLatch countDownLatch = new CountDownLatch(10);		//控制主线程等待其它运行完毕
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();//记录运行开始时间
		creatRandomNums(arr);				//创建一亿个数据
		for(int i = 0 ; i < 10 ; i++) {		//生产十个线程，并控制数据分组
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					//数据分组
					int[] myArr = getMyArr(arr, j * 1000000, (j + 1) * 1000000 - 1);
					//创建Treeset，降序排列，用于存放top 10
					TreeSet<Integer> top10 = new TreeSet<>(new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o2 - o1;
						}
					});
					//放入前十个不重复数据
					int k = 0 ;
					for(; k < myArr.length ; k++) {
						top10.add(myArr[k]);
						if(top10.size() == 10)
							break;
					}
					//从前十个不重复数据的下一个开始比较
					for(k = k + 1; k < myArr.length ; k++) {
						//找到大于前十最小的数，将其插入集合
						if(myArr[k] > top10.last()) {
							top10.add(myArr[k]);
							//集合中数据量始终保持10个
							top10.pollLast();
						}
					}
					//查找完毕，将其存入map中
					map.put(Thread.currentThread(), top10);
					countDownLatch.countDown();
				}
			}).start();
		}
		try {
			countDownLatch.await();
			//存放最终结果
			TreeSet<Integer> result = new TreeSet<>();
			//遍历十个线程的数据，将所有结果放入result中
			Set<Entry<Thread, TreeSet<Integer>>> entrySet = map.entrySet();
			for (Entry<Thread, TreeSet<Integer>> entry : entrySet) {
				for (Integer num : entry.getValue()) {
					result.add(num);
			}
			}
			//取出最终结果的前十个数据
			int i = 0;
			for (Integer num : result) {
				System.out.println(num);
				i++;
				if(i == 10)
					break;
			}
			//输出程序运行时间
			System.out.println(System.currentTimeMillis() - startTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void creatRandomNums(int[] arr) {
		Random random = new Random();
		for(int i = 0 ; i < arr.length ; i++)
			arr[i] = random.nextInt(NUM_AMOUNT);
	}
	
	public static int[] getMyArr(int[] arr, int start, int end) {
		return Arrays.copyOfRange(arr, start, end);
	}
}
