package shujujiegou_java.package19_dongtaiguihua;

/**
 * 求解数组最大子序列和，要求：这个子序列的选取的元素，任意两个元素不能相邻
 *
 * @author wangyafei05
 * @date 2019/3/4 19:16
 */
public class ZuiDaBuLianXuZiXuLieHe {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 7, 3, 2, 8};
        System.out.println(maxZiXulieHe(arr));
        System.out.println(maxZiXulieHe3(arr));
    }

    /**
     * 求解数组最大子序列和，要求：这个子序列的选取的元素，任意两个元素不能相邻
     *
     * 假设arr的长度大于等于2
     * @param arr
     * @return
     */
    public static int maxZiXulieHe(int[] arr){
        int[] m = new int[arr.length];
        m[0] = arr[0];
        m[1] = Math.max(arr[0], arr[1]);
        for(int i = 2; i < arr.length; i++){
            m[i] = Math.max(arr[i] + m[i - 2], m[i - 1]);
        }
        return m[m.length - 1];
    }

    /**
     * 求解数组最大子序列和，要求：这个子序列的选取的元素，不能选取3个相邻的元素
     *
     * 假设arr的长度大于等于23
     * @param arr
     * @return
     */
    public static int maxZiXulieHe3(int[] arr){
        int[] m = new int[arr.length];
        m[0] = arr[0];
        m[1] = arr[0] + arr[1];
        m[2] = Math.max(m[1], m[0] + m[2]);
        for(int i = 3; i < arr.length; i++){
            m[i] = Math.max(Math.max(m[i - 3] + arr[i - 1] + arr[i], m[i - 2] + arr[i]), m[i - 1]);
        }
        return m[m.length - 1];
    }
}
