/*
 * facilitycontainer1.1
 * 该软件包中提供了facilitycontainer所使用的异常. 其中所有得异常都继承FacilityContainerException.
 */
package sc.facilitycontainer.exception;

/**
 * 参数异常.
 * 		处理参数时发生的异常;引发该异常的原因通常在外皮对象使用参数时发生.
 * 
 * @author suchen
 * @time 2008-4-4 下午08:50:32
 * @email xiaochen_su@126.com
 */
public class ParameterException extends RuntimeException {

	private static final long serialVersionUID = 20080404L;
	
	public ParameterException() {
		
	}
	
	public ParameterException(String errorMessage) {
		super(errorMessage);
	}
	
	public ParameterException(Throwable t) {
		super(t);
	}
	
	public ParameterException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
}
