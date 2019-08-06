package shujujiegou_java.package19_dongtaiguihua;

/**
 * 两个数组的最大连续子序列和
 *
 * @author wangyafei05
 * @date 2019/3/4 15:32
 */
public class MostMaxZiXuLieSum {
    public static void main(String[] args) {
        int[] arr = {-2, 11, -4, 13, -5, 2};
        System.out.println(mostMax(arr));
        arr = new int[]{1, -3, 4, -2, -1, 6};
        System.out.println(mostMax(arr));
        arr = new int[]{-8, -1, -3, -4, -2, -1, -6};
        System.out.println(mostMax(arr));
    }

    public static int mostMax(int[] x){
        int max = x[0];
        int temp = 0;
        for(int i = 0; i < x.length; i++){
            temp += x[i];
            if(temp > max){
                max = temp;
            }
            if(temp < 0){
                temp = 0;
            }
        }
        return max;
    }
}
