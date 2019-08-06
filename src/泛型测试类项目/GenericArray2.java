package 泛型测试类项目;

import java.lang.reflect.Array;

/**
 * 泛型数组的测试
 * 这才是泛型数组的正确创建的方法
 * @createTime 2018年4月9日 下午11:36:06
 * @author MrWang
 */
public class GenericArray2<T> {
	
	public static void main(String[] args) {
		GenericArray2<Integer> gai = new GenericArray2<>(Integer.class, 10);
		Object[] ia = gai.rep();      //这样可以
		System.out.println("成功运行");
	}
	
	private T[] array;
	
	@SuppressWarnings("unchecked")
	public GenericArray2(Class<T> type, int sz){
		array = (T[]) Array.newInstance(type, sz);
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
 