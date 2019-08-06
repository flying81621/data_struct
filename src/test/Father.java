package test;

/**
 * 
 * @createTime 2018年4月15日 下午6:57:40
 * @author MrWang
 */
public abstract class Father {
	
	public void eat(){
		System.out.println("开始做饭");
		String fan = make();
		System.out.println("饭做好了，是 " + fan);
	}
	
	public String make(){
		throw new RuntimeException();
	}

}
