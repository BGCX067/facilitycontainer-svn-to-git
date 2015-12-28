package sc.facilitycontainer.scarfskin;

import java.lang.reflect.Method;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.util.ClassUtils;

/**
 * 
 * @author suchen
 * @time 2008-9-2 下午12:33:11
 * @email xiaochen_su@126.com
 */
class SetterComponentScarfskinManagerParameter implements ManagerParameter {
	private Method[] dependentyMethods = null;
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.scarfskin.support.ManagerParameter#manageParameter(sc.facilitycontainer.FacilityContainer, java.lang.Object[])
	 */
	public Object manageParameter(FacilityContainer facilityContainer, Object[] obj) throws Exception {
		// TODO Auto-generated method stub
		
		if ( dependentyMethods == null ) {
			Parameter[] parameters = (Parameter[]) obj[0];
			Class<?> componentType = (Class<?>) obj[1];
			
			int length = parameters.length;
			
			Object[][] dependentyEntitys = new Object[length][];
			Method[] dependentyMethods = new Method[length];
			
			for (int i = 0; i < length; i++) {
				Parameter parameter = parameters[i];
				
				Object dependentyEntity = parameter.getParamEntity(facilityContainer);
				
				Class<?> dependentyType = parameter.getExpectType(facilityContainer);
			
				String fieldName = parameters[i].getByName();

				if (dependentyType == null || dependentyEntity == null || fieldName == null || fieldName.equals("")) {
					throw new NullPointerException(parameter.toString());
				}
				
				Method method = ClassUtils.getSetterMethod(componentType, fieldName, dependentyType);

				dependentyEntitys[i] = new Object[]{dependentyEntity};
				dependentyMethods[i] = method;
			}
			
			this.dependentyMethods = dependentyMethods;
			return new Object[]{dependentyMethods, dependentyEntitys};
		} else {
			
			Parameter[] parameters = (Parameter[]) obj[0];
		
			int length = parameters.length;
			
			Object[][] dependentyEntitys = new Object[length][];
			
			for (int i = 0; i < length; i++) {
				Parameter parameter = parameters[i];
				
				Object dependentyEntity = parameter.getParamEntity(facilityContainer);
				
				dependentyEntitys[i] = new Object[]{dependentyEntity};
				
			}
			
			return new Object[]{dependentyMethods, dependentyEntitys};
		}
		
		
	}
	
	//	update 2009-07-04 21:48:26	从sc.facilitycontainer.scarfskin.support目录移动到
	//	sc.facilitycontainer.scarfskin目录 作为外皮的实现细节不公开接口
	
	//	update 2009-07-04 22:09:53	修改了引用非单体组件时返回的是同一对象(单体)的错误
}

