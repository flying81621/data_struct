package leetCode.竞赛;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 830. Positions of Large Groups
用户通过次数 0
用户尝试次数 0
通过次数 0
提交次数 0
题目难度 Easy
In a string S of lowercase letters, these letters form consecutive groups of the same character.

For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".

Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.

The final answer should be in lexicographic order.

 

Example 1:

Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
Example 2:

Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.
Example 3:

Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]
 * @createTime 2018年5月6日 上午9:33:35
 * @author MrWang
 */
class Solution {
	public static void main(String[] args) {
		Solution so = new Solution();
		/*String s = "abbxxxxzzy";
		List<List<Integer>> list = so.largeGroupPositions(s);
		System.out.println(list);*/
		/*String result = so.maskPII("+(501321)-50-23431");
		System.out.println(result);
		
		System.out.println("86-".matches("[0-9]{2}-"));*/
		
		long start = System.nanoTime();
		int result = so.consecutiveNumbersSum(855877922);
		long end = System.nanoTime();
		System.out.println(result);
		System.out.println("花费；" + (end - start) + "纳秒");
		System.out.println("花费；" + (end - start) / 1000000 + "毫秒");
		
		int sum = 0;
		long start1 = System.nanoTime();
		for(int i = 0; i < 855877922; i++){
			sum += i;
		}
		long end1 = System.nanoTime();
		System.out.println("花费；" + (end1 - start1) / 1000000 + "毫秒");
	}
	
    public List<List<Integer>> largeGroupPositions(String S) {
    	List<List<Integer>> result = new ArrayList<>();
        if(S == null || "".equals(S)){
        	return result;
        }
        char[] arr = S.toCharArray();
        int num = 1;
        int startIndex = -1;
        char c = arr[0];
        
        for(int i = 1; i < arr.length; i++){
        	if(c == arr[i]){
        		num++;
        	} else {
        		if(num >= 3){
        			startIndex = i - num;
        			List<Integer> list = new ArrayList<>();
        			list.add(startIndex);
        			list.add(i - 1);
        			result.add(list);
        		}
        		num = 1;
        		c = arr[i];
        	}
        }
        if(num >= 3){
        	startIndex = arr.length - num;
        	List<Integer> list = new ArrayList<>();
			list.add(startIndex);
			list.add(arr.length - 1);
			result.add(list);
        }
        return result;
    }
    
    /**
     * 831. Masking Personal Information
	用户通过次数 0
	用户尝试次数 0
	通过次数 0
	提交次数 0
	题目难度 Medium
	We are given a personal information string S, which may represent either an email address or a phone number.
	
	We would like to mask this personal information according to the following rules:
	
	
	1. Email address:
	
	We define a name to be a string of length ≥ 2 consisting of only lowercase letters a-z or uppercase letters A-Z.
	
	An email address starts with a name, followed by the symbol '@', followed by a name, followed by the dot '.' and followed by a name. 
	
	All email addresses are guaranteed to be valid and in the format of "name1@name2.name3".
	
	To mask an email, all names must be converted to lowercase and all letters between the first and last letter of the first name must be replaced by 5 asterisks '*'.
	
	
	2. Phone number:
	
	A phone number is a string consisting of only the digits 0-9 or the characters from the set {'+', '-', '(', ')', ' '}. You may assume a phone number contains 10 to 13 digits.
	
	The last 10 digits make up the local number, while the digits before those make up the country code. Note that the country code is optional. We want to expose only the last 4 digits and mask all other digits.
	
	The local number should be formatted and masked as "***-***-1111", where 1 represents the exposed digits.
	
	To mask a phone number with country code like "+111 111 111 1111", we write it in the form "+***-***-***-1111".  The '+' sign and the first '-' sign before the local number should only exist if there is a country code.  For example, a 12 digit phone number mask should start with "+**-".
	
	Note that extraneous characters like "(", ")", " ", as well as extra dashes or plus signs not part of the above formatting scheme should be removed.
	
	 
	
	Return the correct "mask" of the information provided.
	
	 
	
	Example 1:
	
	Input: "LeetCode@LeetCode.com"
	Output: "l*****e@leetcode.com"
	Explanation: All names are converted to lowercase, and the letters between the
	             first and last letter of the first name is replaced by 5 asterisks.
	             Therefore, "leetcode" -> "l*****e".
	Example 2:
	
	Input: "AB@qq.com"
	Output: "a*****b@qq.com"
	Explanation: There must be 5 asterisks between the first and last letter 
	             of the first name "ab". Therefore, "ab" -> "a*****b".
	Example 3:
	
	Input: "1(234)567-890"
	Output: "***-***-7890"
	Explanation: 10 digits in the phone number, which means all digits make up the local number.
	Example 4:
	
	Input: "86-(10)12345678"
	Output: "+**-***-***-5678"
	Explanation: 12 digits, 2 digits for country code and 10 digits for local number. 
	Notes:
	
	S.length <= 40.
	Emails have length at least 8.
	Phone numbers have length at least 10.
     */
    public String maskPII(String S) {
    	if(S == null || "".equals(S)){
    		return S;
    	}
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        Matcher matcher = pattern.matcher(S);
        Pattern numP = Pattern.compile("\\d+");
        Matcher numM = numP.matcher(S);
        Pattern strP = Pattern.compile("\\[a-zA-Z]+");
        Matcher strM = strP.matcher(S);
        if(matcher.matches() && !numM.matches()){
        	String s = S.substring(0, S.indexOf("@"));
        	s = s.charAt(0) + "*****" + s.charAt(s.length() - 1) + S.subSequence(S.indexOf("@"), S.length());
        	return s.toLowerCase();
        } else {
        	/*
        	 * Input: "86-(10)12345678"
				Output: "+**-***-***-5678"
        	 */
    		StringBuilder sb = new StringBuilder();
    		while(numM.find()){
    			sb.append(numM.group());
    		}
			StringBuilder sb2 = new StringBuilder();
			StringBuilder reverse = sb.reverse();
			String string = reverse.toString().toString();
			char[] arr = string.toCharArray();
			int flag = 1;
			for(int i = 0; i < arr.length; i++){
				if(i < 4){
					sb2.append(arr[i]);
				} else {
					if(i == 4){
						sb2.append('-');
					}
					sb2.append("*");
					if(flag % 3 == 0){
						sb2.append('-');
						flag = 0;
					}
					flag++;
				}
			}
			if(S.startsWith("+")){
				if(sb2.charAt(sb2.length() - 1) == '-'){
					sb2.deleteCharAt(sb2.length() - 1);
				}
				sb2.append("+");
			}
			if(sb2.charAt(sb2.length() - 1) == '-'){
				sb2.deleteCharAt(sb2.length() - 1);
			}
			if(S.substring(0,3).matches("[0-9]{2}-")){
				sb2.append("+");
			}
			/*if(S.substring(0,3).matches("[0-9]{2}-")){
				if(!"-".equals(sb2.charAt(sb2.length() - 1))){
					sb2.append("**+");
				} else {
					sb2.append("-**+");
				} 
    		} else {
    			if(flag % 3 == 0){
    				sb2.append("-**");
    			} else {
    				sb2.append("**");
    			}
    			if(S.startsWith("+")){
    				sb2.append("+");
    			}
    		}*/
			return sb2.reverse().toString();
        }
    }
    
    /**
     * 829. Consecutive Numbers Sum
	Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?
	
	Example 1:
	
	Input: 5
	Output: 2
	Explanation: 5 = 5 = 2 + 3
	Example 2:
	
	Input: 9
	Output: 3
	Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
	Example 3:
	
	Input: 15
	Output: 4
	Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
     * @param N
     * @return
     */
    public int consecutiveNumbersSum(int N) {   //超出时间限制
    	if(N <= 2){
    		return 1;
    	}
    	int result = 0;
        int start = N / 2 + 1;
        int head = start;
        int sum = 0;
        for(int i = start; i > 0; i--){
        	sum += i;
        	while(sum >= N){
        		if(sum == N){
        			result++;
        		}
        		sum -= head--;
        	}
        }
        return result + 1;
    }
    
    public int consecutiveNumbersSum2(int N) {   //尝试更快的方法,时间的花费主要在遍历的上面，必须得到log(n)的时间复杂度，怎么做？？？？？？
    	
    	return 0;
    }
}