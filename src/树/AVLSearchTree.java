package 树;

/**
 * 平衡二叉树的粗略实现
 * @createTime 2018年4月8日 下午9:25:12
 * @author MrWang
 */
public class AVLSearchTree<T extends Comparable<? super T>> {
	
	private static final int BALANCE_FACTOR = 1;
	
	//这个是根节点
	private AVLNode<T> root;
	
	/**
	 * 使用一个新的空节点作为开始
	 */
	public AVLSearchTree(){
		root = null;
	}
	
	/**
	 * 返回任一节点的高度
	 * 之所以在node=null的时候赋值为-1，是为了计算左右的高度差的，比如一个节点有两个叶节点，
	 * 其中右节点是null，左节点的高度是0，那此时左节点的高度应该比右节点的高度高了1才对
	 * @param node
	 * @return
	 */
	private int height(AVLNode<T> node) {
		return node == null? -1 : node.height;
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
	private boolean contains(T t, AVLNode<T> root) {
		// 思路：遍历查找数据进行比较，因为这个二叉树有序，所以时间复杂度类似于二分查找，为O(logN)
		// 随便一个数据传进来的时候，不匹配的概率大，所以应该将不匹配放在前面
		if (root == null || t == null) {
			return false;
		}
		int result = t.compareTo(root.element);
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
		return this.findMin(root).element;
	}

	/**
	 * 寻找数据最小的那个节点，这个节点按照本二叉查找树的添加规则，一定是最左边的那课叶子节点
	 * 本方法采用递归
	 * @param root
	 * @return
	 */
	private AVLNode<T> findMin(AVLNode<T> root) {
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
		return this.findMax(root).element;
	}

	/**
	 * 寻找数据最大的那个节点，这个节点按照本二叉查找树的添加规则，一定是最右边的那课叶子节点
	 * 本方法采用非递归
	 * @param root
	 * @return
	 */
	private AVLNode<T> findMax(AVLNode<T> root) {
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
	 * 添加方法
	 * @param element
	 */
	public void add(T element) {
		if(element == null) {
			return;
		}
		root = this.add(element, root);
	}
	
	private AVLNode<T> add(T element, AVLNode<T> root) {
		//说明这个用于添加数据进行比较的节点是空的，可以用来构造节点
		if (root == null) {
			return new AVLNode<T>(element, null, null);
		}
		if(element.compareTo(root.element) < 0){
			root.left = add(element, root.left);
		} else if (element.compareTo(root.element) > 0) {
			root.right = add(element, root.right);
		} else{
			System.out.println("数据重复，不做任何处理，重复的数据为：" + element);
			return root;
		}
		
//		这里应该少了一个在添加完节点后，将当前节点的高度进行更新的方法，应该需要先判断高度是否发生了变化，然后更新
		root.height = calculateHeight(root, 0);
		
		return balance(root);
	}
	
	/**
	 * 用来计算节点的高度的方法  测试中
	 * @param root
	 * @param 要计算一个节点的高度的时候，这个值必须从0开始
	 * @return
	 */
	private static <T> int calculateHeight(AVLNode<T> root, int current) {
		if(root == null){
			return -1;
		}
		if(root.left == null && root.right == null){
			return current;
		}
		current = Math.max(calculateHeight(root.left, current + 1), calculateHeight(root.right, current + 1));
		return current;
	}
	
	public static void main(String[] args) {
		//测试高度变化
		/*AVLNode<Integer> avl = new AVLNode<>(8, null, null);
		avl.left = new AVLNode<>(7, null, null);
		avl.right = new AVLNode<>(10, null, null);
		avl.left.left = new AVLNode<>(2, null, null);
		avl.left.left.right = new AVLNode<>(4, null, null);
		System.out.println(calculateHeight(avl, 0));*/
		
		//avl完整测试
		AVLSearchTree<Integer> tree = new AVLSearchTree<>();
		tree.add(10);
		tree.add(6);
		tree.add(16);
		tree.add(4);
		tree.add(7);
		//导致树不平衡的添加开始
		tree.add(3);
		
		tree.inorderTraversal(tree);
	}
	
	/***
	 * 用于移除元素的方法
	 * @param t
	 * @return
	 */
	public T remove(T t) {
		if (t == null) {
			return null;
		}
		root = this.remove(t, root);
		return t;
	}
	
	/**
	 * 用于移除元素的方法
	 * @param t
	 * @param root
	 * @return
	 */
	private AVLNode<T> remove(T element, AVLNode<T> root){
		if(element == null || root == null){
			return null;
		}
		int compareTo = element.compareTo(root.element);
		if(compareTo < 0){
			root.left = remove(element, root.left);
		} else if (compareTo > 0) {
			root.right = remove(element, root.right);
		} else if (root.left != null && root.right != null) {
			AVLNode<T> min = findMin(root.right);
			root.element = min.element;
			//重新计算右面的指向，把右边的最小元素删除掉
			root.right = remove(min.element, root.right);
		} else if(root.left == null && root.right == null){
			//这是没有任何子节点的时候的情况，直接置为null，然后返回将数据返回给父节点，这样就断开了父节点和这个节点的关系
			root = null;
		} else {
			if (root.left != null) {
				//在直接返回子子节点之前，将现在的父节点和子节点断开，加快垃圾回收（虽然不断开也不影响正常的垃圾回收）
				AVLNode<T> left = root.left;
				root.left = null;
				root = left;
			} else {
				AVLNode<T> right = root.right;
				root.right = null;
				root = right;
			}
		}
		//开始做平衡树,先重新计算节点的高度
//		root.height = calculateHeight(root, 0);
		
		return balance(root);
	}
	
	/**
	 * 用来进行平衡节点的方法      
	 * @param root
	 * @return 为什么要返回呢，是因为需要返回一个节点给上一个节点进行指向，看上一个方法，每次添加的时候， 所有的节点的指向都会改变
	 */
	private AVLNode<T> balance(AVLNode<T> root) {
		if (root == null) {
			return null;
		}
		//说明是左树高于右树超过了平衡因子
		if(height(root.left) - height(root.right) > BALANCE_FACTOR){
			//由于在左树的左子树插入数据引起的不平衡，只需要做一次单旋转
			//这里为甚么会有等号呢，按理说在插入引起不平衡的收是不可能出现等号的，一定是两个子树不一样深，但是等号却可能出现在删除操作
			//中，本来就是一样深的两个子树，因为在本机点的另外一侧进行了删除操作导致本侧不平衡
			if(height(root.left.left) >= height(root.left.right)){ 
				//这里为什么要用root接受然后返回呢，因为这个节点需要和重新计算，比如是根节点，现在的根节点可能已经不是根节点了，需要重新计算出要给根节点出出来
				root = rotateLeftChild(root);
			} else {
				//由于在左树的右子树插入数据引起的不平衡，需要做两次旋转
				root = doubleRotateLeftChild(root);
			}
		} else if(height(root.right) - height(root.left) > BALANCE_FACTOR){  //说明是右树导致的不平衡 
			//由于在右树的右子树插入数据引起的不平衡，只需要做一次单旋转
			if (height(root.right.right) >= height(root.right.left)) {
				root = rotateRightChild(root);
			} else {
				//由于在右树的左子树插入数据引起的不平衡，需要做两次旋转
				root = doubleRotateRightChild(root);
			}
		}
		//开始重新折算当前节点的高度
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		return root;
	}
	
	/**
	 * 对左子树进行单旋转
	 * 将当前节点作为左子树的右节点，而左子树的右节点作为当前节点的左节点
	 * @param root
	 * @return
	 */
	private AVLNode<T> rotateLeftChild(AVLNode<T> root){
		AVLNode<T> childLeft = root.left;
		AVLNode<T> childChildRight = childLeft.right;
		root.left = childChildRight;
		childLeft.right = root;
		//开始重新计算高度，要从下往上计算，也就是先计算生成的新的子节点
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		childLeft.height = Math.max(height(childLeft.left), height(childLeft.right)) + 1;
		return childLeft;
	}
	
	/**
	 * 对左子树进行双旋转
	 * 对当前节点的左节点的右子树插入数据导致当前节点不平衡，需要进行此操作
	 * 需要先对左节点做一次右旋转，将非平衡因素转移到左侧，然后再对左节点做一次左旋转
	 * @param root
	 * @return
	 */
	private AVLNode<T> doubleRotateLeftChild(AVLNode<T> root){
		root.left = rotateRightChild(root.left);
		return rotateLeftChild(root);
	}
	
	/**
	 * 对右子树进行单旋转
	 * @param root
	 * @return
	 */
	private AVLNode<T> rotateRightChild(AVLNode<T> root){
		AVLNode<T> rightChild = root.right;
		root.right = rightChild.left;
		rightChild.left = root;
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		rightChild.height = Math.max(height(rightChild.left), height(rightChild.right)) + 1;
		return rightChild;
	}
	
	/**
	 * 对右子树进行双旋转
	 * @param root
	 * @return
	 */
	private AVLNode<T> doubleRotateRightChild(AVLNode<T> root){
		root.right = rotateLeftChild(root.right);
		return rotateRightChild(root);
	}
	
	private static class AVLNode<T> {
		public AVLNode(T element){
			this(element, null, null);
		}
		
		public AVLNode(T element, AVLNode<T> left, AVLNode<T> right){
			this.element = element;
			this.left = left;
			this.right = right;
			this.height = 0;
		}
		
		T element;
		AVLNode<T> left;
		AVLNode<T> right;
		int height;   //树的高度
	}
	
	/**
	 * 三大遍历之一：先序遍历
	 * @param source
	 */
	public void preorderTraversal(AVLSearchTree<T> source) {
		if (source == null) {
			return;
		}
		preorderTraversal(source.root);
	}

	private void preorderTraversal(AVLNode<T> source) {
		if (source == null) {
			return;
		}
		System.out.println(source.element);
		inorderTraversal(source.left);
		inorderTraversal(source.right);
	}

	/**
	 * 三大遍历之二：中序遍历
	 * @param source
	 */
	public void inorderTraversal(AVLSearchTree<T> source) {
		if (source == null) {
			return;
		}
		inorderTraversal(source.root);
	}

	private void inorderTraversal(AVLNode<T> source) {
		if (source == null) {
			return;
		}
		inorderTraversal(source.left);
		System.out.println(source.element);
		inorderTraversal(source.right);
	}

	/**
	 * 三大遍历之三：后序遍历
	 * @param source
	 */
	public void postorderTraversal(AVLSearchTree<T> source) {
		if (source == null) {
			return;
		}
		postorderTraversal(source.root);
	}

	private void postorderTraversal(AVLNode<T> source) {
		if (source == null) {
			return;
		}
		inorderTraversal(source.left);
		inorderTraversal(source.right);
		System.out.println(source.element);
	}

}
