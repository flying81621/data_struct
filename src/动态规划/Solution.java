package 动态规划;

/**
 * 动态规划联系
 * @createTime 2018年4月29日 上午12:30:54
 * @author MrWang
 */
public class Solution {
	public static void main(String[] args) {
		System.out.println(maxProfit(new int[]{}));
	}
	
	/**
	 *  买卖股票的最佳时机
	给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
	
	如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
	
	注意你不能在买入股票前卖出股票。
	
	示例 1:
	
	输入: [7,1,5,3,6,4]
	输出: 5
	解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
	     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
	示例 2:
	
	输入: [7,6,4,3,1]
	输出: 0
	解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
		if(prices == null || prices.length == 0){
			return 0;
		}
		int buyIndex = 0;
		int saleIndex = prices.length - 1;
		int max = 0;
		int current = prices[buyIndex];
		for(int i = 1; i < prices.length; i++){
			if(prices[i] < current){
				current = prices[i];
				buyIndex = i;
			} else {
				int temp = prices[i] - current;
				if(temp > max){
					max = temp;
					saleIndex = i;
				}
			}
		}
		System.out.println("买入的价钱：" + current + "，卖出的价钱:" + prices[saleIndex]);
		return max;
    }

}
