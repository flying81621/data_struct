package test;

/**
 * 
 * @createTime 2018年4月15日 下午6:59:24
 * @author MrWang
 */
public class Jack extends Father {
	private int age;
	private String name;

	@Override
	public String make() {
		// TODO Auto-generated method stub
		return "jack的饭：米饭";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jack other = (Jack) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
