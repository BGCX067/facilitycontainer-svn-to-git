/*
 * facilitycontainer1.1
 * 该软件包提供了基本命名实现 Naming.
 */
package sc.facilitycontainer.naming;

import sc.facilitycontainer.Naming;

/**
 * 简单的命名.
 * 		根据字符串来命名对象,是java.lang.String和sc.facilitycontainer.Naming之间的适配器.
 * 
 * @author suchen
 * @time 2008-4-4 下午12:16:18
 * @email xiaochen_su@126.com
 */
public final class SimpleNaming implements Naming {
	
	/** 使用该字符串*/
	private String naming;
	
	//	///////////////
	//	构造函数
	//	///////////////
	
	/**
	 * 创建一个根据字符串命名的对象.
	 * 
	 * @param naming String 字符串.
	 */
	public SimpleNaming(String naming) {
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
		return getClass().getName() + " " + naming;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object anObject) {
		
		if(!(anObject instanceof SimpleNaming)) {
			return false;
		}
		
		SimpleNaming temp = (SimpleNaming) anObject;
		
		
		return temp.naming.equals(this.naming);
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
