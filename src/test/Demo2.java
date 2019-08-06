package test;

/**
 * 
 * @createTime 2018年4月15日 下午5:52:02
 * @author MrWang
 */
public class Demo2 {
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		boolean isNum = isNum(3333333333333333333L);
		long end = System.nanoTime();
		System.out.println(isNum);
		System.out.println("耗时：" + (end - start) + "纳秒");
		
		long n = cal(33333333);
		System.out.println(n);
	}

	/**
	 * 判断num是否是3的幂次方
	 * @param num
	 * @return
	 */
	public static boolean isNum(long num) {
		long base = 1;
		while (base <= num) {
			if (base == num) {
				return true;
			}
			base *= 3;
		}
		return false;
	}
	
	/**
	 * 找出一个比num大的最小的3得幂次方的数出来
	 * @param num
	 * @return
	 */
	public static long cal(long num) {
		long base = 1;
		int i = 0;
		while (base <= num) {
			base *= 3;
			i++;
		}
		System.out.println("次方是:" + i);
		return base;
	}

}
