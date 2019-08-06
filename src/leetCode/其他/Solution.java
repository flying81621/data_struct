package leetCode.其他;

/**
 * 
 * @createTime 2018年4月29日 下午11:37:23
 * @author MrWang
 */
public class Solution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solution s = new Solution();
		/*System.out.println(s.hammingWeight((Integer.MAX_VALUE + 1) * 2 - 1));
		System.out.println(Integer.MIN_VALUE % 2);
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE >> 2));*/
		
		System.out.println(s.hammingDistance(0,Integer.MAX_VALUE));
	}
	
	/**
	 * 位1的个数
	编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
	
	示例 :
	
	输入: 11
	输出: 3
	解释: 32位整数 11 的二进制表示为 00000000000000000000000000001011 。
	致谢:
	特别感谢 @ts 添加此问题并创建所有测试用例。
	 * @param n
	 * @return
	 */
	public int hammingWeight(long n) {
//		n = Math.abs(n);
//		System.out.println(n);
        int i = 0;
        int count = 0;
        while(n != 0){
        	if(count >= 32){
        		break;
        	}
    		if(n % 2 != 0){
    			i++;
    		}
    		n = n >> 1;
    		count++;
        }
        return i;
//		return Long.bitCount(n);
    }
	
	/**
	 * // you need to treat n as an unsigned value
	 * 最好的办法
	 * @param n
	 * @return
	 */
	 public int hammingWeight(int n) {
        int i = 0;
        while (n != 0) {
            n &= n - 1;
            i ++;
        }
        return i;
	 }
	 
	 
	 /**
	  * 汉明距离
		两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
		
		给出两个整数 x 和 y，计算它们之间的汉明距离。
		
		注意：
		0 ≤ x, y < 231.
		
		示例:
		
		输入: x = 1, y = 4
		
		输出: 2
		
		解释:
		1   (0 0 0 1)
		4   (0 1 0 0)
		       ↑   ↑
		
		上面的箭头指出了对应二进制位不同的位置。
	  * @param x
	  * @param y
	  * @return
	  */
	 public int hammingDistance(int x, int y) {
        //我的思路：先异或一下，然后算汉明重量
		 int temp = x ^ y;
		 int i = 0;
		 while(temp != 0){
			 temp &= temp -1;
			 i++;
		 }
		 return i;
	 }
	 
	 
	 /**
	  * 颠倒给定的 32 位无符号整数的二进制位。

	示例:
	
	输入: 43261596
	输出: 964176192  {1,2,4,3,5,6}
	解释: 43261596 的二进制表示形式为 00000010100101000001111010011100 ，
	     返回 964176192，其二进制表示形式为 00111001011110000010100101000000 。
	进阶:
	如果多次调用这个函数，你将如何优化它？
	  * @param n
	  * @return
	  */
	// you need treat n as an unsigned value
    public long reverseBits(long n) {
        String s = n + "";
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length / 2; i++){
        	char c = arr[i];
        	arr[i] = arr[arr.length - i - 1];
        	arr[arr.length - 1 - i] = c;
        }
        String result = new String(arr);
        return Long.parseLong(result);
    }

}
