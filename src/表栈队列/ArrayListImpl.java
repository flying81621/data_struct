package 表栈队列;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayList的简单实现
 * LinkedList的简单实现和这个实际上差不多，区别就是数组的移动扩容换成了链表的头尾添加、删除、查找和插入
 * @createTime 2018年2月11日 下午7:23:06
 * @author MrWang
 */
public class ArrayListImpl<E> implements Iterable<E> {
	private static final int DEFAULT_CAPACITY = 10;

	// 这个参数代表的是当前数组的实际使用的空间的大小
	private int theSize;
	private E[] theItems;

	public ArrayListImpl() {
		doClear();
	}

	public void clear() {
		doClear();
	}

	/**
	 * 将数组的大小调整到默认的大小
	 */
	private void doClear() {
		// TODO Auto-generated method stub
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}

	/**
	 * 获取当前集合实际上已经存储的元素数量
	 * 
	 * @return
	 */
	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * 将数组的大小调整为当前元素的数量大小
	 */
	public void trimToSize() {
		ensureCapacity(size());
	}

	/**
	 * 将数组的大小调整为新的给定值defaultCapacity
	 * 
	 * @param defaultCapacity
	 */
	private void ensureCapacity(int newCapacity) {
		// 因为新的数组需要接受以前的数组的内容，所以新数组的大小必须比数组中已经有的元素数量大
		if (newCapacity < theSize) {
			return;
		}
		E[] old = theItems;
		theItems = (E[]) new Object[newCapacity];
		// 数据迁移
		for (int i = 0; i < size(); i++) {
			theItems[i] = old[i];
		}
		// 加快垃圾回收
		old = null;
	}

	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return theItems[index];
	}

	/**
	 * 将指定索引处的值，替换成新的指定的值
	 * 
	 * @param index
	 * @param e
	 * @return
	 */
	public E set(int index, E e) {
		E old = get(index);
		theItems[index] = e;
		return old;
	}

	public boolean add(E e) {
		add(size(), e);
		return true;
	}

	/**
	 * 指定位置添加新元素
	 * 
	 * @param index
	 * @param e
	 */
	public void add(int index, E e) {
		// 如果数组满了，扩容
		if (theItems.length == size())
			ensureCapacity(size() * 2 + 1);
		// 开始将元素添加到指定的位置
		for (int i = theSize; i > index; i--) {
			theItems[i] = theItems[i - 1];
		}
		theItems[index] = e;
		theSize++;
	}

	public E remove(int index) {
		E e = theItems[index];
		//为了防止索引越界，将限制条件设为theSize-1,在下面这个循环的操作之后，实际上原来最后一个元素还存在，即数组最后的两个元素是一样的，但是
		//这个时候theSize已经自减了，所以在使用上没有任何影响
		for (int i = index; i < theSize - 1; i++) {
			theItems[i] = theItems[i + 1];
		}
		theSize--;
		return e;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator<E> {
		private int current = 0;   //这是迭代器的索引
		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public E next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			return theItems[current++];
		}

		@Override
		public void remove() {
			//这里之所以要进行先--，是因为这个操作是在next()函数调用之后进行的，此时的current已经++过了，所以要先进行--操作
			ArrayListImpl.this.remove(--current);
		}

	}

}
