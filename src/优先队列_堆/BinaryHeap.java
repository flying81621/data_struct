package 优先队列_堆;import javax.swing.plaf.basic.DefaultMenuLayout;

import 树.UnderflowException;

/**
 * 这个是最小二叉堆，用于实现优先队列，使用一个数组表示：
 * 可以证明：一个高为h的完全二叉树拥有2^h到2^(h+1)-1个节点，
 * 用一个数组表示既是：在数组任意位置i上的元素，其父节点在i/2位置上，其左儿子在2i位置上，其右儿子在2i+1位置上，数组长度一开始设置为一个差不多不会短期扩张的初始值
 * @createTime 2018年4月9日 下午11:18:27
 * @author MrWang
 */
public class BinaryHeap<T extends Comparable<? super T>> {
	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize;   //表示现在有多少个数据在这个堆中
	private T[] array;
	
	public BinaryHeap(){
		this(DEFAULT_CAPACITY);
	}
	
	public BinaryHeap(int capacity){
		//这样定义泛型数组后，无法用一个T[]来直接获取这个数组，详见泛型测试类项目这个包中的正确创建泛型数组的方式，但是如果不做获取操作的话，这样并不会问题
		array = (T[]) new Comparable[capacity];
	}
	
	//在这种一开始就给定的情况下，应该直接在构造方法内部进行构造堆，先放着里
	public BinaryHeap(T[] items){
		currentSize = items.length;
		array = (T[]) new Comparable[(currentSize + 2) * 11/10];
		for (int i = 1; i < currentSize; i++) {
			array[i] = items[i - 1];
		}
		buildHeap();
	}
	
	/**
	 * 往这个堆中进行插入数据
	 * 和父亲进行比较，如果发现比父亲小，就进行交换位置
	 * @param t
	 */
	public void insert(T t) {
		if(currentSize >= array.length - 1){
			enlargeArray(array.length * 2 + 1);
		}
		int i = ++currentSize;
		//第一个数组内的元素空着，也就是下标为0的位置是不放东西的
		for(; i > 1 && array[i/2].compareTo(t) > 0;i /= 2){
			array[i] = array[i/2];
		}
		array[i] = t;
	}
	
	public T findMin() {
		if (isEmpty()) {
			throw new UnderflowException("无法在一个空树中寻找最小值");
		}
		return array[1];
	}
	
	/**
	 * 删除最小值，实际删除的就是根节点，然后返回这个值
	 * @return
	 */
	public T deleteMin() {
		if (isEmpty()) {
			throw new UnderflowException("不能在一个空树中进行删除操作");
		} 
		T min = findMin();
		//先直接把最后一个数放在根部，然后再调整这个堆
		array[1] = array[currentSize--];
		percolateDown(1);
		return min;
	}
	
	public boolean isEmpty() {
		return currentSize >= 0;
	}
	
	
	/**
     * Make the priority queue logically empty.
     */
	public void makeEmpty() {
		if(isEmpty()){
			return;
		}
		for (int i = 1; i <= currentSize; i++) {
			array[i] = null;
		}
		currentSize = 0;
	}
	
	/**
	 * 调整位置的，保证从hole这个位置开始的子树是一个最小二叉堆，前提假设hole的两个儿子已经是最小堆了
	 * @param hole
	 */
	private void percolateDown (int hole) {
		int child;
		//hole * 2 < currentSize 这个操作是为了保证当前位置的元素有孩子
		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			//child < currentSize表示在child后面还有一个元素，这意味着现在的节点有两个孩子，于是这两个孩子进行比较，选出最小的那个
			if(child < currentSize && array[child].compareTo(array[child + 1]) > 0){
				child++;
			}
			if(array[hole].compareTo(array[child]) > 0) {
				T temp = array[hole];
				array[hole] = array[child];
				array[child] = temp;
			} else {
				//这样说明这个父节点比两个孩子都要小
				break;
			}
		}
	}
	
	/**
	 * 扩容
	 * @param newCapacity
	 */
	public void enlargeArray(int newCapacity) {
		if (newCapacity < array.length) {
			return;
		}
		T[] old = array;
		array = (T[]) new Comparable[newCapacity];
		for (int i = 0; i < old.length; i++){
			array[i] = old[i];
		}
	}
	
	/**
	 * 根据现在的数组进行建立二叉堆
	 */
	public void buildHeap() {
		//这个操作意思是：从最后一个父节点开始进行调整子树的操作·根据二叉堆的添加特性，从currentSize/2这个节点开始，一直往前的节点，一定是有子节点的
		for (int i = currentSize/2; i > 0; i--) {
			percolateDown(i);
		} 
	}
	
	public static void main(String[] args) {
		Integer i = 10;
		int to = i.compareTo(null);
		System.out.println(to);
	}
	
	
}
