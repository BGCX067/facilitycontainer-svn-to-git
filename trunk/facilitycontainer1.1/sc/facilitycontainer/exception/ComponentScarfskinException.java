/*
 * facilitycontainer1.1
 * 该软件包中提供了facilitycontainer所使用的异常. 其中所有得异常都继承FacilityContainerException.
 */
package sc.facilitycontainer.exception;

/**
 * 组件外皮发生的异常.
 * 
 * @author suchen
 * @time 2008-5-23 下午02:28:04
 * @email xiaochen_su@126.com
 */
public class ComponentScarfskinException extends RuntimeException {
	
	private static final long serialVersionUID = 20080406L;
	
	public ComponentScarfskinException(Throwable t) {
		super(t);
	}
	
	public ComponentScarfskinException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
	
	public ComponentScarfskinException(String errorMessgae) {
		super(errorMessgae);
	}
	
	
}
