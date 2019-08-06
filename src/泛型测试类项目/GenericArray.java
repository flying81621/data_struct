package 泛型测试类项目;

/**
 * 泛型数组的测试
 * @createTime 2018年4月9日 下午11:36:06
 * @author MrWang
 */
public class GenericArray<T extends Comparable<? super T>> {
	
	public static void main(String[] args) {
		GenericArray<Integer> gai = new GenericArray<>(10);
//		Integer[] ia = gai.rep();     //抛出java.lang.ClassCastException
		Object[] ia = gai.rep();      //这样可以
		System.out.println("成功运行");
	}
	
	private T[] array;
	
	@SuppressWarnings("unchecked")
	public GenericArray(int sz){
//		array = (T[]) new Object[sz];
		array = (T[]) new Comparable[ sz + 1 ];
	}
	
	public void put(int index, T item) {
		array[index] = item;
	}
	
	public T get(int index) {
		return array[index];
	}
	
	public T[] rep() {
		return array;
	}

}
 