/*
 * facilitycontainer1.1
 * 该软件包中提供了facilitycontainer所使用的异常. 其中所有得异常都继承FacilityContainerException.
 */
package sc.facilitycontainer.exception;

/**
 * 
 * @author suchen
 * @time 2008-4-13 上午09:54:07
 * @email xiaochen_su@126.com
 */
public class FacilityContainerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FacilityContainerException() {
		super();
	}
	
	public FacilityContainerException(String errorMessage) {
		super(errorMessage);
	}
	
	public FacilityContainerException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
	
	public FacilityContainerException(Throwable t) {
		super(t);
	}
}
