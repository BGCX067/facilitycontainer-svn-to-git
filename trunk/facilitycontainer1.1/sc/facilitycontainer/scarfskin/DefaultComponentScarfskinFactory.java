package sc.facilitycontainer.scarfskin;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.Naming;
import sc.facilitycontainer.ConstantContext;
import sc.facilitycontainer.Parameter;

/**
 * 默认的组件外皮工厂.
 * 
 * @author suchen
 * @time 2008-6-10 下午02:49:32
 * @email xiaochen_su@126.com
 */
public class DefaultComponentScarfskinFactory extends ComponentScarfskinFactory {
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.scarfskin.ComponentScarfskinFactory#createComponentScarfskin(sc.facilitycontainer.Naming, boolean, java.lang.Object[], int)
	 */
	@Override
	ComponentScarfskin createComponentScarfskin(Naming naming, boolean isSingle, Object[] entity, int tag) {
		// TODO Auto-generated method stub
		ComponentScarfskin componentScarfskin = null;
		
		switch(tag) {
		case ConstantContext.BASIC_SCARFSKIN : {
			componentScarfskin = new BasicComponentScarfskin((Class<?>)entity[0], naming, isSingle);
		}; break; 
		case ConstantContext.CONSTRUCTOR_SCARFSKIN : {
			componentScarfskin = new ConstructorComponentScarfskin((Class<?>)entity[0], naming, isSingle, (Parameter[]) entity[1]);
		}; break; 
		default : throw new java.lang.IllegalArgumentException(" 组件工厂标记提供异常 [tag] " + tag + " 不存在");
		}
		
		return componentScarfskin;
	}

}
