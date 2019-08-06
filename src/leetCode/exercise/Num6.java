package leetCode.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * 6. Z字形变换
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：

	P   A   H   N
	A P L S I I G
	Y   I   R
	之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"
	
	实现一个将字符串进行指定行数变换的函数:
	
	string convert(string s, int numRows);
	示例 1:
	
	输入: s = "PAYPALISHIRING", numRows = 3
	输出: "PAHNAPLSIIGYIR"
	示例 2:
	
	输入: s = "PAYPALISHIRING", numRows = 4
	输出: "PINALSIGYAHRPI"
	解释:
	
	P     I    N
	A   L S  I G
	Y A   H R
	P     I
 * @createTime 2018年4月24日 下午10:28:09
 * @author MrWang
 */
public class Num6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String result = convert("PAYPALISHIRING", 3);
		System.out.println("PAHNAPLSIIGYIR".equals(result));
		
		String s = convert("PAYPALISHIRING", 4);
		System.out.println("PINALSIGYAHRPI".equals(s));
		String s2 = convert("AB", 1);
		System.out.println(s2);*/
		
		System.out.println(-123 + "");
		System.out.println(reverse(-2147483648));
	}
	
	public static int reverse(int x) {
        boolean positive = x >= 0;
        long abs = Math.abs((long)x);
        System.out.println(abs);
        StringBuilder s = new StringBuilder(abs + "");
        s.reverse();
        System.out.println(s.toString());
        long parseLong = Long.parseLong(s.toString());
        if(parseLong > Integer.MAX_VALUE || (0 - parseLong) < Integer.MIN_VALUE){
        	return 0;
        }
        return (int) (positive?parseLong:(0-parseLong));
    }
	
	public static String convert(String s, int numRows) {
		if(s == null || "".equals(s) || numRows <= 1){
			return s;
		}
		List<Character>[] lists = new List[numRows];
		for(int i = 0; i < lists.length; i++){
			lists[i] = new ArrayList<Character>();
		}
		int k = 0;
		boolean asc = true;
		int listLength = 0;
		char[] array = s.toCharArray();
		for(int i = 0; i < array.length; i++){
			lists[k].add(array[i]);
			if(k == numRows - 1){
				asc = false;
			}
			if(k == 0){
				asc = true;
			}
			if(asc){
				k++;
			} else {
				k--;
			}
		}
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < (s.length() > lists.length? lists.length : s.length()); i++){
			List<Character> list = lists[i];
			for(int j = 0; j < list.size(); j++){
				result.append(list.get(j));
			}
		}
		return result.toString();
	}

}
