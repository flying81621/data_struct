package 树;

/**
 * 二叉查找树 注意：此二叉树的添加的数据必须实现了comparable接口，添加的数据最后的结果是不可重复，即带有自动去重的效果
 * 
 * @createTime 2018年2月17日 下午9:29:15
 * @author MrWang
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
	private BinaryNode<T> root;

	/**
	 * 构造方法使用一个空的节点引用作为开始
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * 定义一个清空此二叉树的方法
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * 判断这个二叉树是否有数据
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * 判断这个二叉树中是否包含这个数据
	 * @param t 要查找的目标数据
	 * @return
	 */
	public boolean contains(T t) {
		return this.contains(t, root);
	}

	/**
	 * 当然这个方法也可以采用非递归的方法来进行判断，可以参考findMax方法的做法
	 * @param t
	 * @param root
	 * @return
	 */
	private boolean contains(T t, BinaryNode<T> root) {
		// 思路：遍历查找数据进行比较，因为这个二叉树有序，所以时间复杂度类似于二分查找，为O(logN)
		// 随便一个数据传进来的时候，不匹配的概率大，所以应该将不匹配放在前面
		if (root == null || t == null) {
			return false;
		}
		int result = t.compareTo(root.t);
		if (result < 0) {
			return this.contains(t, root.left);
		} else if (result > 0) {
			return this.contains(t, root.right);
		} else {
			return true;
		}
	}

	/**
	 * 寻找最小值
	 * 
	 * @return
	 */
	public T findMin() {
		// 校验这个二叉树有数据
		if (isEmpty()) {
			throw new UnderflowException();
		}
		// 判空后，可以直接调用返回值数据
		return this.findMin(root).t;
	}

	/**
	 * 寻找数据最小的那个节点，这个节点按照本二叉查找树的添加规则，一定是最左边的那课叶子节点
	 * 本方法采用递归
	 * @param root
	 * @return
	 */
	private BinaryNode<T> findMin(BinaryNode<T> root) {
		if (root == null) {
			return null;
		}
		if (root.left == null) {
			return root;
		}
		return findMin(root.left);
	}

	/**
	 * 寻找最大值
	 * @return
	 */
	public T findMax() {
		// 校验这个二叉树有数据
		if (isEmpty()) {
			throw new UnderflowException();
		}
		// 判空后，可以直接调用返回值数据
		return this.findMax(root).t;
	}

	/**
	 * 寻找数据最大的那个节点，这个节点按照本二叉查找树的添加规则，一定是最右边的那课叶子节点
	 * 本方法采用非递归
	 * @param root
	 * @return
	 */
	private BinaryNode<T> findMax(BinaryNode<T> root) {
		// TODO Auto-generated method stub
		if(root == null) {
			return null;
		}
		while (root.right != null) {
			root = root.right;
		}
		return root;
	}

	/**
	 * 移除给定的数据，并返回这个老数据
	 * @return
	 */
	public T remove(T t) {
		if(t == null){
			return null;
		}
		root = this.remove(t, root);
		return t;
	}

	/**
	 * 删除思路：沿着右子树进行遍历查找删除
	 * @param root
	 */
	private BinaryNode<T> remove(T t, BinaryNode<T> root) {
		if(root == null || t == null){
			return root;
		}
		int result = t.compareTo(root.t);
		if (result < 0) {
			root.left = remove(t, root.left);
		} else if (result > 0) {
			root.right = remove(t, root.right);
		} else if (root.left != null && root.right != null) {
			//这时候说明有两个孩子，这时候需要：1.找到其右边的最小节点的数据，赋值给当前节点；2.递归删除那个右侧最小数据的节点
			//删除方法一直到找到这个要删除的数据只有一个子节点或没有子节点的时候，才算删除完成
			BinaryNode<T> findMin = findMin(root.right);
			T element = findMin.t;
			root.t = element;
			root.right = this.remove(element, root.right);
		} else if (root.left == null && root.right == null) {
			//这是没有任何子节点的时候的情况，直接置为null，然后返回将数据返回给父节点，这样就断开了父节点和这个节点的关系
			root = null;
		} else {
			//这是只有一个子节点的时候，将其子节点取出来，然后断开和子节点的关系，加快垃圾回收，
			//同时将这个引用指向子节点,这样完成其父节点指向了其子节点，同时其断开了和其子节点的关联
			if (root.left != null) {
				BinaryNode<T> left = root.left;
				root.left = null;
				root = left;
			} else{
				BinaryNode<T> right = root.right;
				root.right = null;
				root = right;
			}
		}
		return root;
	}

	/**
	 * 定义二叉树的添加,不允许添加空值
	 * @param t 要添加的数据
	 */
	public void add(T t) {
		if (t == null) {
			return;
		}
		root = this.add(t, root);
	}

	/**
	 * 定义二叉树的添加方法，当数据相同的时候，采用直接忽略的策略
	 * 
	 * @param t
	 * @param root
	 * @return
	 */
	private BinaryNode<T> add(T t, BinaryNode<T> root) {
		if (root == null) {
			return new BinaryNode<T>(t, null, null);
		}
		if (t.compareTo(root.t) < 0) {
			root.left = this.add(t, root.left);
		} else if (t.compareTo(root.t) > 0) {
			root.right = this.add(t, root.right);
		} else {
			// 这时候说明是重复的数据,直接结束添加过程
			return root;
		}
		return root;
	}

	/**
	 * 三大遍历之一：先序遍历
	 * @param source
	 */
	public void preorderTraversal(BinarySearchTree<T> source) {
		if (source == null) {
			return;
		}
		preorderTraversal(source.root);
	}

	private void preorderTraversal(BinaryNode<T> source) {
		if (source == null) {
			return;
		}
		System.out.println(source.t);
		inorderTraversal(source.left);
		inorderTraversal(source.right);
	}

	/**
	 * 三大遍历之二：中序遍历
	 * @param source
	 */
	public void inorderTraversal(BinarySearchTree<T> source) {
		if (source == null) {
			return;
		}
		inorderTraversal(source.root);
	}

	private void inorderTraversal(BinaryNode<T> source) {
		if (source == null) {
			return;
		}
		inorderTraversal(source.left);
		System.out.println(source.t);
		inorderTraversal(source.right);
	}

	/**
	 * 三大遍历之三：后序遍历
	 * @param source
	 */
	public void postorderTraversal(BinarySearchTree<T> source) {
		if (source == null) {
			return;
		}
		postorderTraversal(source.root);
	}

	private void postorderTraversal(BinaryNode<T> source) {
		if (source == null) {
			return;
		}
		inorderTraversal(source.left);
		inorderTraversal(source.right);
		System.out.println(source.t);
	}

	/**
	 * 构造一个二叉树内部类节点
	 * 
	 * @createTime 2018年2月17日 下午9:32:02
	 * @author MrWang
	 * @param <T>
	 */
	private static class BinaryNode<T> {
		T t;
		BinaryNode<T> left;
		BinaryNode<T> right;

		public BinaryNode(T t) {
			this(t, null, null);
		}

		public BinaryNode(T t, BinaryNode<T> left, BinaryNode<T> right) {
			super();
			this.t = t;
			this.left = left;
			this.right = right;
		}

		public BinaryNode() {
		}

	}

}
