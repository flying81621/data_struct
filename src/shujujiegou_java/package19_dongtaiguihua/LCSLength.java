package shujujiegou_java.package19_dongtaiguihua;

/**
 * 求解两个字符串的最长公共子序列(不一定是连续的，只求曾经出现过的相同的)的长度
 *
 * @author wangyafei05
 * @date 2019/3/4 0:44
 */
public class LCSLength {
    public static void main(String[] args) {
        String x = "acb";
        String y = "fab";
        System.out.println(LCSLengthMine(x.toCharArray(), 0, y.toCharArray(), 0));
    }

    /**
     * 递归，非动态规划版
     *
     * @param x
     * @param i
     * @param y
     * @param j
     * @return
     */
    public static int LCSLengthMine(char[] x, int i, char[] y, int j){
        if(i >= x.length || j >= y.length ){
            return 0;
        }
        if(x[i] == y[j]){
            return 1 + LCSLengthMine(x, i + 1, y, j + 1);
        }
        return Math.max(LCSLengthMine(x, i + 1, y, j ), LCSLengthMine(x, i, y, j + 1));
    }

    /**
     * 动态规划版
     *
     * @param x
     * @param i
     * @param y
     * @param j
     * @return
     */
    public static int LCSLength(char[] x, int i, char[] y, int j){
        return 0;
    }
}
