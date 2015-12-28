/*
 * facilitycontainer1.1
 * 该软件包中提供了facilitycontainer所使用的异常. 其中所有得异常都继承FacilityContainerException.
 */
package sc.facilitycontainer.exception;

/**
 * 组件没有找到时抛出该异常.
 * 
 * @author suchen
 * @time 2008-4-6 下午04:23:00
 * @email xiaochen_su@126.com
 */
public class ComponentNotFoundException extends ParameterException {

	private static final long serialVersionUID = 20080406L;
	
	public ComponentNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
	public ComponentNotFoundException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
	
	public ComponentNotFoundException(Throwable t) {
		super(t);
	}
	
	public ComponentNotFoundException() {
		super();
	}
}
