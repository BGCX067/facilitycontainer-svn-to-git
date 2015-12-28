/*
 * facilitycontainer1.1
 * 该软件包提供 供容器使用的静态方法, 处理类型,对象字符串等. 
 */
package sc.facilitycontainer.util;

/**
 * 字符串工具.
 * 
 * @author sc
 * @email xiaochen_su@126.com
 * @time 2008-4-1 上午08:41:30
 */
public final class StringUtils {
	private StringUtils() {
		
	}
	
	/**
	 * 将字段名称转换为setter方法名称.
	 * 
	 * @param filedName String 字段名称.
	 * 
	 * @return String setter方法名称.
	 */
	public static String toSetterName(String filedName) {
		return "set" + filedName.substring(0,1).toUpperCase() + filedName.substring(1);
	} 
	

//	public static String toString(Object[] array) {
//		StringBuilder builder = new StringBuilder();
//		
//		for( int i = 0, size = array.length; i < size; i++ ) {
//			
//			builder.append(i).append(". ").append( array[i].getClass()).append("\n");
//		}
//		
//		return builder.toString();
//	}

}
