package shujujiegou_java.package19_dongtaiguihua;

/**
 * 时间空间最快的斐波那契算法
 *
 * @author wangyafei05
 * @date 2019/3/4 15:21
 */
public class FastFeiBoNaQie {
    public static void main(String[] args) {
        System.out.println(cal(6));
    }

    public static int cal(int n) {
        if(n <= 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        int smaller = 0;
        int bigger = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = smaller + bigger;
            smaller = bigger;
            bigger = sum;
        }
        return sum;
    }
}
