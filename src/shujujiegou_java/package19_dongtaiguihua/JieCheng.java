package shujujiegou_java.package19_dongtaiguihua;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用动态规划的方式计算阶乘
 * @author wangyafei05
 * @date 2019/3/1 15:16
 */
public class JieCheng {
    public static void main(String[] args) {
        System.out.println(cal(10));
        System.out.println(cal(8));

        System.out.println(calDT(10));
        System.out.println(calDT(8));
    }

    /**
     * 非动态规划版
     * @param n
     * @return
     */
    public static double cal(int n){
        if(n < 0){
            return 0;
        }
        if(n == 0 || n == 1){
            return 1;
        }
        return n * cal(n - 1);
    }


    private static final Map<Integer, Double> CACHE = new HashMap<>();
    /**
     * 动态规划版
     * @param n
     * @return
     */
    public static double calDT(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        if(CACHE.get(n) != null){
            return CACHE.get(n);
        }
        double result = n * calDT(n - 1);
        CACHE.put(n, result);
        return result;
    }
}
