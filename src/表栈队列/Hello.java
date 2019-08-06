package 表栈队列;
public class Hello{
	public static void main(String[] args){
		Cat cat = new Cat();
		cat.name = "tom";
		cat.age = 20;
		System.out.println("name:" + cat.name + ", age:" + cat.age);
	}

	private static class Cat{
		String name;
		int age;
	}
	
}