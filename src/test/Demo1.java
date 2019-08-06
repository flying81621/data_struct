package test;

import java.util.TreeSet;

/**
 * 实现了comparable的接口的类，默认是升序排列
 * @createTime 2018年2月17日 下午9:18:58
 * @author MrWang
 */
public class Demo1 {
	public static void main(String[] args) {
		TreeSet<Person> set = new TreeSet<>();
		set.add(new Person(18,"tom"));
		set.add(new Person(28,"jack"));
		set.add(new Person(25,"rose"));
		
		for (Person person : set) {
			System.out.println(person);
		}
		
	}
	
	private static class Person implements Comparable<Person>{
		int age;
		String name;
		
		public Person(int age, String name){
			this.age = age;
			this.name = name;
		}
		
		public String toString(){
			return "name:" + name + ",age:" + age;
		}

		@Override
		public int compareTo(Person o) {
			return this.age - o.age;
		}
	}

}
