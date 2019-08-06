package 树;

/**
 * 这是一个测试类,验证二叉查找树的各项功能
 * @createTime 2018年2月17日 下午9:56:51
 * @author MrWang
 */
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<String> source = new BinarySearchTree<>();
		//测试添加去重的功能
		source.add("hello");
		source.add("HELLO");
		source.add("HELLO");
		source.add("abcde");
		source.add("xyz");
		source.add("xyz");
		source.add("NIHAO");
		
		//中序遍历
		System.out.println("----------------先序遍历------------------");
		source.preorderTraversal(source);
		System.out.println("----------------中序遍历------------------");
		source.inorderTraversal(source);
		System.out.println("----------------------------------");
		
		//测试是否包含数据
		System.out.println(source.contains("abcde"));
		System.out.println(source.contains("hello world"));
		System.out.println("----------------------------------");
		
		//测试获取最小值和最大值
		System.out.println("最小值是：" + source.findMin());
		System.out.println("最大值是：" + source.findMax());
		
		//测试删除
		System.out.println("----------------------------------");
		System.out.println("删除根节点之后的数据如下：");
		source.remove("hello");
//		source.remove("NIHAO");
		System.out.println("----------------先序遍历------------------");
		source.preorderTraversal(source);
		System.out.println("----------------中序遍历------------------");
		source.inorderTraversal(source);
		
	}

}
