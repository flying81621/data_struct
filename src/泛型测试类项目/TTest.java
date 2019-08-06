package 泛型测试类项目;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 测试证明， List<T>中可以添加任何T的子类
 * @createTime 2018年3月12日 下午5:34:21
 * @author MrWang
 */
public class TTest {

	public static void main(String[] args) {
		/*List<A> list = new ArrayList<>(); 
		list.add(new B("hello"));
		System.out.println(list);*/
		
		List<List> list = new ArrayList<>();
		list.add(new ArrayList());
		list.add(new LinkedList());
		System.out.println(list);
	}
	
	static class A{
		private String name;
		
		public A(String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
 
		@Override
		public String toString() {
			return "A [name=" + name + "]";
		}
	}
	static class B extends A{

		public B(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "B []";
		}
		
	}
	
}
