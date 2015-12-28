/*
 * facilitycontainer1.1
 * 该软件包中提供了facilitycontainer所使用的异常. 其中所有得异常都继承FacilityContainerException.
 */
package sc.facilitycontainer.exception;


/**
 * 不明确的方法异常.
 * 		在查找方法时刻给定依赖不能够准确找到方法,存在多个可能性的方法时发生该异常.
 * 
 * @author sc
 * @email xiaochen_su@126.com
 * @time 2008-4-1 下午01:08:45
 */
public class AmbiguityMethodException extends RuntimeException {

	private static final long serialVersionUID = 20080401L;
	
	public AmbiguityMethodException(String errorMessage) {
		super(errorMessage);
	}
	
	public AmbiguityMethodException(Throwable t) {
		super(t);
	}
	
	public AmbiguityMethodException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
}	
