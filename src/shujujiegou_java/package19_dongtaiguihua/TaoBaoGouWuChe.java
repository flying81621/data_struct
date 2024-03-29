package shujujiegou_java.package19_dongtaiguihua;

/**
 * 【动态规划】
 * 功能：实现淘宝 “满减凑单”
 *
 * @author wangyafei05
 * @date 2019/3/5 15:59
 */
public class TaoBaoGouWuChe {
    static String[] Snacks = {"百草鸭脖", "烧烤味牛肉干", "科尔牛板筋", "三只松鼠核桃", "兰花豆", "芒果干", "乐事薯片", "妙芙欧式蛋糕", "瑞士卷", "白葡萄干", "三只松鼠猪肉干"};
    static String[] p = {"26.9元", "24.9元", "49.9元", "28.9元", "14.9元", "16.9元", "19.9元", "19.0元", "14.9元", "14.9元", "26.9元"};

    public static void main(String[] args) {
        int[] prices = {269, 249, 499, 289, 149, 169, 199, 190, 149, 149, 269};
        ShoppingSnacks(prices, 11, 1990);
    }

    /**
     * @param prices 各个商品的价格
     * @param n      商品的个数
     * @param w      满减条件（满 199-99元）
     */
    public static void ShoppingSnacks(int[] prices, int n, int w) {
        //将商品的价格扩展到三倍
        boolean[][] tree = new boolean[n][3 * w + 1];
        tree[0][0] = true;
        tree[0][prices[0]] = true;

        //动态规划
        for (int i = 1; i < n; i++) {

            // 不购买当前商品
            for (int j = 0; j <= 3 * w; j++) {
                //寻找上一个商品决策状态
                if (tree[i - 1][j] == true) {
                    tree[i][j] = true;
                }
            }

            // 购买当前商品
            for (int j = 0; j <= 3 * w - prices[i]; j++) {
                //寻找上一个商品决策状态
                if (tree[i - 1][j] == true) {
                    tree[i][j + prices[i]] = true;
                }
            }
        }

        //找出需要凑单的商品
        int j;
        for (j = w; j < 3 * w + 1; j++) {
            //在最后一个商品寻找满足最接近 200 的条件状态
            if (tree[n - 1][j] == true) {
                System.out.println("满减的最大条件为" + (float) j / 10 + "元");
                break;
            }
        }

        //没有可选择零食
        if (j == -1) {
            return;
        }

        // 倒推遍历满足条件的商品
        for (int i = n - 1; i >= 1; i--) {
            //当前账单的总金额大必须于当前商品金额
            //且上一个商品的决策状态为 true
            if (j - prices[i] >= 0 && tree[i - 1][j - prices[i]] == true) {
                //已购买该商品
                System.out.println(Snacks[i] + p[i]);
                j = j - prices[i];
            } else {
                //没有购买该商品

            }
        }
        if (j != 0) {
            System.out.print(Snacks[1] + p[0]);
        }
    }
}
