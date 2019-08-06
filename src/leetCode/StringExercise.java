package leetCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串训练
 * 
 * @createTime 2018年4月22日 下午10:17:47
 * @author MrWang
 */
public class StringExercise {
	public static void main(String[] args) {
		/*
		 * int index = firstUniqCharBetter("loveleetcode");
		 * System.out.println(index);
		 */

		/*
		 * int maxLength = lengthOfLongestSubstring("pwwkew");
		 * System.out.println(maxLength);
		 */

		/*String result = longestPalindrome("acdadca");
		System.out.println(result);*/
		
		/*int result = myAtoi("  0000000000012345678");
		System.out.println(result);
		char c = '0';
		char c1 = '9';
		System.out.println(c < '9');
		System.out.println(c1);*/
		
		/*boolean equal = isAnagram("anagram", "nagaram");
		System.out.println(equal);*/
		
		System.out.println(isPalindrome2("A man, a plan, a canal: Panama"));
	}

	/**
	 * 最长公共前缀 编写一个函数来查找字符串数组中的最长公共前缀。 如果不存在公共前缀，返回空字符串 ""。 示例 1: 输入:
	 * ["flower","flow","flight"] 输出: "fl" 示例 2: 输入: ["dog","racecar","car"] 输出:
	 * "" 解释: 输入不存在公共前缀。 说明: 所有输入只包含小写字母 a-z 。
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
		return null;
	}

	/**
	 * 字符串中的第一个唯一字符 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 案例: s =
	 * "leetcode" 返回 0. s = "loveleetcode", 返回 2. 注意事项：您可以假定该字符串只包含小写字母
	 * 
	 * @param s
	 * @return
	 */
	public static int firstUniqChar(String s) {
		if (s == null || s.equals("")) {
			return -1;
		}
		Map<Character, Integer> map = new LinkedHashMap<>();
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (map.get(c) != null) {
				map.put(c, -1);
			} else {
				map.put(c, i);
			}
		}
		Set<Entry<Character, Integer>> set = map.entrySet();
		for (Entry<Character, Integer> entry : set) {
			Integer value = entry.getValue().intValue();
			if (value != -1) {
				return value;
			}
		}
		return -1;
	}

	/**
	 * 更好地解法 当然还有利用string.indexOf和lastIndexOf函数的，我觉得不好 注意事项：您可以假定该字符串只包含小写字母
	 * 
	 * @param s
	 * @return
	 */
	public static int firstUniqCharBetter(String s) {
		if (s == null || s.equals("")) {
			return -1;
		}
		int[] chars = new int[26];
		char[] array = s.toCharArray();
		for (int i = 0; i < array.length; i++) {
			int temp = chars[array[i] - 'a'];
			if (temp != 0) {
				chars[array[i] - 'a'] = -1;
			} else {
				chars[array[i] - 'a'] = 1;
			}
		}
		for (int i = 0; i < array.length; i++) {
			if (chars[array[i] - 'a'] > 0) {
				return i;
			}
		}
		return -1;
		/*
		 * int[] result = new int[26]; for(int i=0; i<s.length(); i++){
		 * result[s.charAt(i) - 'a']++; }
		 * 
		 * for(int i=0; i<s.length(); i++){ if(result[s.charAt(i) - 'a'] == 1)
		 * return i; } return -1;
		 */
	}

	/**
	 * 无重复字符的最长子串 给定一个字符串，找出不含有重复字符的最长子串的长度。 示例： 给定 "abcabcbb" ，没有重复字符的最长子串是
	 * "abc" ，那么长度就是3。 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。 给定 "pwwkew" ，最长子串是 "wke"
	 * ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串 abcfdcevn
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s) {
		if (s == null || "".equals(s)) {
			return 0;
		}
		int maxLength = 0;
		int start = 0;
		char[] array = s.toCharArray();
		Map<Character, Integer> map = new HashMap<>();
		int i;
		for (i = 0; i < array.length; i++) {
			int temp = i - start;
			if (temp > maxLength) {
				maxLength = temp;
			}
			if (map.containsKey(array[i]) && start <= map.get(array[i])) {
				start = map.get(array[i]) + 1;
			}
			map.put(array[i], i);
		}
		if (i == array.length) {
			int temp = i - start;
			if (temp > maxLength) {
				maxLength = temp;
			}
		}
		return maxLength;
	}

	public int lengthOfLongestSubstringBetter(String s) { // 我觉得好的方式
		if (s == null || s.length() < 1)
			return 0;
		int len = s.length();
		int maxLen = -1;
		int start = 0;
		for (int i = 1; i < len; i++) {
			char temp = s.charAt(i);
			for (int j = i; j > start; j--) {
				if (s.charAt(j - 1) == temp) {
					maxLen = (maxLen > (i - start)) ? maxLen : (i - start);
					start = j;
				}
			}
		}
		// 若最后一次遍历没有重复的字符，则将最后一次遍历的长度和之前的最长子串长度进行比较
		maxLen = (maxLen > (len - start)) ? maxLen : (len - start);
		return maxLen;
	}

	/**
	 * 5. 最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。 示例 1： 输入: "babad"
	 * 输出: "bab" 注意: "aba"也是一个有效答案。 示例 2： 输入: "cbbd" 输出: "bb"
	 * 
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {
		if (s == null || "".equals(s)) {
			return "";
		}
		char[] arr = s.toCharArray();
		String result = arr[0] + "";
		int maxLength = 0;
		Map map = new HashMap();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				int lastIndex = (int) map.get(arr[i]);
				while (lastIndex < i) {
					if (i - lastIndex + 1 > maxLength) {
						int j = lastIndex;
						int k = i;
						while (j < k) {
							if (arr[j] != arr[k]) {
								break;
							}
							j++;
							k--;
						}
						if (j >= k) {
							maxLength = i - lastIndex + 1;
							result = new String(arr, lastIndex, maxLength);
						}
					}
					lastIndex = s.indexOf(arr[i], lastIndex + 1);
				}
			} else {
				map.put(arr[i], i);
			}
		}
		return result;
	}
	
	/**
	 * 8. 字符串转整数 (atoi)   程序错误
	 * @param str
	 * @return
	 */
	public static int myAtoi(String str) {
		if(str == null || "".equals(str)){
			return 0;
		}
		String temp = "";
		str = str.trim();
		if(str.startsWith("+")){
			if(str.length() > 1){
				str = str.substring(1, str.length());
			} else {
				return 0;
			}
		}
		char[] array = str.toCharArray();
		Pattern compile = Pattern.compile("^(-\\d)|\\d");
		Matcher matcher = compile.matcher(str);
		if(matcher.find()){
			String group = matcher.group();
			if(!str.startsWith(group)){
				return 0;
			}
			if(array[0] != '0'){
				temp += array[0];
			}
			boolean start = false;
			for (int i = 1; i < array.length; i++) {
				if(!start){
					if(array[i] != '0'){
						start = true;
					}
				}
				if(start){
					if(array[i] <= '9' && array[i] >= '0'){
						temp += array[i];
					} else{
						break;
					}
				}
			}
		} else {
			return 0;
		}
		if("".equals(temp)){
			return 0;
		}
		
		if(array[0] == '-'){
			if(temp.length() > (Integer.MIN_VALUE + "").length() || Long.parseLong(temp) < Integer.MIN_VALUE){
				return Integer.MIN_VALUE;
			}
			return Integer.parseInt(temp);
		}
		if(temp.length() > (Integer.MAX_VALUE + "").length() || Long.parseLong(temp) > Integer.MAX_VALUE){
			return Integer.MAX_VALUE;
		}
		return Integer.parseInt(temp);
    }
	
	/**
	 * 有效的字母异位词
		给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
		
		例如，
		s = "anagram"，t = "nagaram"，返回 true
		s = "rat"，t = "car"，返回 false
		
		注意:
		假定字符串只包含小写字母。
	 * @param s
	 * @param t
	 * @return
	 */
	 public static boolean isAnagram(String s, String t) {
	        if((s == null && t == null) || ("".equals(s) && "".equals(t))){
	            return true;
	        }
	        if(s.length() != t.length()){
	            return false;
	        }
	        Map<Character, Integer> map = new HashMap<>();
	        char[] arr = s.toCharArray();
	        for(int i = 0; i < arr.length; i++){
	            if(map.containsKey(arr[i])){
	                map.put(arr[i], map.get(arr[i]) + 1);
	            } else {
	                map.put(arr[i], 1);
	            }
	        }
	        char[] tArr = t.toCharArray();
	        for(int i = 0; i < tArr.length; i++){
	            Integer c = map.get(tArr[i]);
	            if(c == null || c == 0){
	                return false;
	            } else {
	                map.put(tArr[i], c - 1);
	            }
	        }
	        return true;
	    }
	 
	 /**
	  * 验证回文字符串
		给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
		
		说明：本题中，我们将空字符串定义为有效的回文串。
		
		示例 1:
		
		输入: "A man, a plan, a canal: Panama"
		输出: true
		示例 2:
		
		输入: "race a car"
		输出: false
	  * @param s
	  * @return
	  */
	 public static boolean isPalindrome(String s) {
		 if(s == null){
			 return false;
		 }
		 if("".equals(s)){
			 return true;
		 }
	     Pattern pattern = Pattern.compile("[a-zA-Z]+|[0-9]+"); 
	     Matcher matcher = pattern.matcher(s);
	     StringBuilder sb = new StringBuilder();
	     while(matcher.find()){
	    	 sb.append(matcher.group());
	     }
	     String result = sb.toString().toLowerCase();
	     char[] charArray = result.toCharArray();
	     int length = charArray.length / 2;
	     for(int i = 0; i < length; i++){
	    	 if(charArray[i] != charArray[charArray.length - i -1]){
	    		 return false;
	    	 }
	     }
	     return true;
	 }
	 
	 /**
	  * 一种更好的解法
	  * @param s
	  * @return
	  */
	 public static boolean isPalindrome2(String s) {
		 if(s == null){
			 return false;
		 }
		 if("".equals(s)){
			 return true;
		 }
		 char[] array = s.toCharArray();
		 char[] resultArr = new char[array.length];
		 int resultIndex = 0;
		 for(int i = 0; i < array.length; i++){
			 if(array[i] >= '0' && array[i] <= '9' || (array[i] >= 'a' && array[i] <= 'z')){
				 resultArr[resultIndex++] = array[i];
			 } else if (array[i] >= 'A' && array[i] <= 'Z'){
				 resultArr[resultIndex++] = (char) (array[i] + 32);
			 }
		 }
		 int index = 0;
		 int end = resultIndex - 1;
		 while(index < end){
			 if(resultArr[index++] != resultArr[end--]){
				 return false;
			 }
		 }
		 return true;
	 }

}
