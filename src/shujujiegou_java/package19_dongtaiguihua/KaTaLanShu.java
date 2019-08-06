package shujujiegou_java.package19_dongtaiguihua;

/**
 * 卡塔兰数--n个节点的二叉搜索树共有多少种
 *
 * @author wangyafei05
 * @date 2019/3/4 19:36
 */
public class KaTaLanShu {
    public static void main(String[] args) {
        System.out.println(cal(1));
        System.out.println(cal(2));
        System.out.println(cal(3));
        System.out.println(cal(4));
        System.out.println(cal(5));
    }

    /**
     * 非动态规划版
     * @param n
     * @return
     */
    public static int calMine(int n){
        if(n <= 1){
            return 1;
        }
        int count = 0;
        for(int i = 1; i <= n; i++){
            count += calMine(i - 1) * calMine(n - i);
        }
        return count;
    }

    public static int[] arr = new int[1];
    /**
     * 动态规划版
     * @param n
     * @return
     */
    public static int cal(int n){
        if(n <= 1){
            return 1;
        }
        if(n > arr.length){
            int[] copyArr = new int[n];
            for(int i = 0; i < arr.length; i++){
                copyArr[i] = arr[i];
            }
            arr = copyArr;
        }
        if(arr[n - 1] != 0){
            return arr[n - 1];
        }
        for(int i = 1; i <= n; i++){
            arr[n - 1] += cal(i - 1) * cal(n - i);
        }
        return arr[n - 1];
    }
}
