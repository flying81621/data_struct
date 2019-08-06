package leetCode.数字;
/**
 * 9. 回文数
题目描述提示帮助提交记录社区讨论阅读解答
判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:

输入: 121
输出: true
示例 2:

输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3:

输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 
 * @createTime 2018年5月6日 下午12:21:06
 * @author MrWang
 */
class Solution {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.isPalindrome(21));
	}
    public boolean isPalindrome(int x) {
    	int head = x;
        if(x < 0){
        	return false;
        }
        int sum = 0;
        while(x != 0){
        	sum = sum * 10 + x % 10;
        	x = x / 10;
        }
        return sum == head;
    }
}