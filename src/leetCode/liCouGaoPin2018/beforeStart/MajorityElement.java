package leetCode.liCouGaoPin2018.beforeStart;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 求众数
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * @author wangyafei05
 * @date 2019/2/27 22:23
 */
public class MajorityElement {
    public static void main(String[] args) {
        MajorityElement element = new MajorityElement();
        int[] arr = {2,2,1,1,1,2,2};
        System.out.println(element.majorityElement(arr));
        arr = new int[]{3,2,3,2,3,2,3};
        System.out.println(element.majorityElementBest(arr));
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if(!map.containsKey(num)){
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if(entry.getValue() > nums.length / 2){
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * 体会：这种数一定满足，出现一次加一次，没出现减一次，最终能能保证数字大于0
     * @param nums
     * @return
     */
    public int majorityElementBest(int[] nums) {
        int count=1;
        int res=nums[0];
        for(int i=1; i<nums.length; i++){
            if(res==nums[i])
                count++;
            else{
                count--;
                if(count==0)
                    res=nums[i+1];
            }
        }
        return res;
    }
}
