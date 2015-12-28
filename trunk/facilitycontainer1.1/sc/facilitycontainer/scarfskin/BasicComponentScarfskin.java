package sc.facilitycontainer.scarfskin;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Naming;

/**
 * 提供一个基本组件外皮.
 * 
 * @author suchen
 * @time 2008-6-10 下午02:17:54
 * @email xiaochen_su@126.com
 */
public class BasicComponentScarfskin extends AbstractComponentScarfskin {
	
	/**
	 * 创建一个基本的组件外皮.
	 * 
	 * @param componentType Class 组件类型.
	 * @param naming Naming 在容器中的命名.
	 * @param isSingle boolean 是否以单体保存.
	 */
	public BasicComponentScarfskin(Class<?> componentType, Naming naming, boolean isSingle) {
		super(componentType, naming, isSingle);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.scarfskin.AbstractComponentScarfskin#createComponent()
	 */
	@Override
	Object createComponent() throws InstantiationException, IllegalAccessException  {
		// TODO Auto-generated method stub
		return getComponentType().newInstance();
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.scarfskin.AbstractComponentScarfskin#manageParameter(sc.facilitycontainer.FacilityContainer)
	 */
	@Override
	void manageParameter(FacilityContainer facilityContainer) {
		// TODO Auto-generated method stub
		
	}

}
