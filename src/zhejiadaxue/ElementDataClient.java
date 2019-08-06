package zhejiadaxue;

/**
 * @author wangyafei05
 * @date 2019/4/13 22:12
 */
public class ElementDataClient {
    public static void main(String[] args) {
        //{3, 5, 4, 2, 7, 8, 9}
        // -1 0 -1  1  3  3  2
        ElementData[] arr = {new ElementData(3, -4), new ElementData(5, 0), new ElementData(4, -2),
                new ElementData(2, 1), new ElementData(7, 3), new ElementData(8, 3), new ElementData(9, 2)};
        ElementDataClient client = new ElementDataClient();
        int i = client.find(arr, 9);
        System.out.println("9的根节点:" + i);

        //将元素9和元素7的集合进行合并
        client.union(arr, 9, 7);
        i = client.find(arr, 9);
        System.out.println("9的根节点:" + i);
        System.out.println("7的根节点:" + i);
        i = client.find(arr, 8);
        System.out.println(arr[5]);
        System.out.println(arr[3]);
    }

    /**
     * 查找元素data集合arr中的根节点是谁，同时进行路径压缩
     *
     * @param arr
     * @param data
     * @return
     */
    public int find(ElementData[] arr, int data) {
        int i = 0;  //找到元素data的位置
        for (; i < arr.length; i++) {
            if (data == arr[i].data) {
                break;
            }
        }
        if (i >= arr.length) {
            return -1;  //说明没找到
        }
        return findRoot(arr, i);
    }

    //寻找根节点，同时路径上的节点记性路径压缩
    private int findRoot(ElementData[] arr, int index) {
        if (arr[index].parent < 0) {
            return index;
        }
        return arr[index].parent = findRoot(arr, arr[index].parent);
    }

    /**
     * 基于数高的方式，但和路径压缩不兼容
     * //     * 将两个不同的集合进行合并，为了尽量不增大树的高度，将小树并到大树上
     *
     * @param arr
     * @param x
     * @param y
     */
    public void unionBaseHeight(ElementData[] arr, int x, int y) {
        int rootX = find(arr, x);
        int rootY = find(arr, y);
        if (rootX != rootY) {
            //按秩归并
            if (arr[rootX].parent < arr[rootY].parent) {
                arr[rootY].parent = rootX;
            } else if (arr[rootX].parent < arr[rootY].parent) {
                arr[rootY].parent = rootX;
                arr[rootX].parent--;
            } else {
                arr[rootX].parent = rootY;
            }
        }
    }

    /**
     * 基于集合大小的方式
     * 将两个不同的集合进行合并，将元素个数小的归并到个数大的集合上
     *
     * @param arr
     * @param x
     * @param y
     */
    public void union(ElementData[] arr, int x, int y) {
        int rootX = find(arr, x);
        int rootY = find(arr, y);
        if (rootX != rootY) {
            //按秩归并
            if (arr[rootX].parent <= arr[rootY].parent) {
                arr[rootX].parent += arr[rootY].parent;
                arr[rootY].parent = rootX;
            } else {
                arr[rootY].parent += arr[rootX].parent;
                arr[rootX].parent = rootY;
            }
        }
    }

    /**
     * @author wangyafei05
     * @date 2019/4/13 22:03
     */
    public static class ElementData {
        //集合元素
        public int data;
        //本集合元素的父节点,负数时为树的高度  因为路径压缩和基于树高的方式不兼容，所以现在定义为子节点的个数
        public int parent = -1;

        public ElementData(int data, int parent) {
            this.data = data;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "ElementData{" +
                    "data=" + data +
                    ", parent=" + parent +
                    '}';
        }
    }
}
