package sc.facilitycontainer.naming;

import sc.facilitycontainer.Naming;

/**
 * 命名适配器
 * 	根据任意来命名对象,是java.lang.Object和sc.facilitycontainer.Naming之间的适配器.
 * 
 * @author suchen
 * @time 2008-4-4 下午05:07:44
 * @email xiaochen_su@126.com
 */
public final class NamingAdapter implements Naming {
	/** 以该对象进行命名 */
	private Object naming = null;
	
	//	///////////////////////
	//	构造函数
	//	///////////////////////
	
	/**
	 * 以参数作为命名对象.
	 * 
	 * @param naming Object 命名.
	 */
	public NamingAdapter(Object naming) {
		this.naming = naming;
	}
	
	//	//////////////////////
	//	逻辑方法
	//	//////////////////////
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getClass().getName() + " " + naming.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object anObject) {
		if(!(anObject instanceof NamingAdapter)) {
			return false;
		}

		NamingAdapter temp = (NamingAdapter) anObject;
		
		return naming.equals(temp.naming);
	} 
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = 17;
		
		result = result * 37 + naming.hashCode();
		
		return result;
	}
	
}
