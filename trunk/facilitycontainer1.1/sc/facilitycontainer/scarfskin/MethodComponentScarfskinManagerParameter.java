package sc.facilitycontainer.scarfskin;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.util.ClassUtils;

/**
 * 
 * @author suchen
 * @time 2008-9-2 下午12:36:57
 * @email xiaochen_su@126.com
 */
class MethodComponentScarfskinManagerParameter implements ManagerParameter {

	private Method[] dependentyMethods = null;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see sc.facilitycontainer.scarfskin.support.ManagerParameter#manageParameter(sc.facilitycontainer.FacilityContainer,
	 *      java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public Object manageParameter(FacilityContainer facilityContainer,
			Object[] obj) throws Exception {
		// TODO Auto-generated method stub

		if(dependentyMethods == null) {
			Map<String, Parameter[]> methodCache = (Map<String, Parameter[]>) obj[0];
			Class<?> componentType = (Class<?>) obj[1];
			
			Set<String> methodNames = methodCache.keySet();

			Iterator<String> iterator = methodNames.iterator();

			int index = 0;

			Method[] dependentyMethods = new Method[methodNames.size()];
			Object[][] dependentyEntitys = new Object[methodNames.size()][];

			while (iterator.hasNext()) {
				String methodName = iterator.next();

				Parameter[] parameters = methodCache.get(methodName);

				Class<?>[] params = new Class[parameters.length];
				Object[] entity = new Object[parameters.length];

				for (int i = 0; i < params.length; i++) {
					entity[i] = parameters[i].getParamEntity(facilityContainer);
					params[i] = parameters[i].getExpectType(facilityContainer);
				}

				methodName = methodName.substring(0, methodName.indexOf("#"));
				Method method = ClassUtils.getMethod(componentType, methodName,
						params);

				dependentyMethods[index] = method;
				dependentyEntitys[index++] = entity;
			}
			
			this.dependentyMethods = dependentyMethods;
			return new Object[] { dependentyMethods, dependentyEntitys };
		} else {
			Map<String, Parameter[]> methodCache = (Map<String, Parameter[]>) obj[0];

			Set<String> methodNames = methodCache.keySet();

			Iterator<String> iterator = methodNames.iterator();

			int index = 0;
		
			Object[][] dependentyEntitys = new Object[methodNames.size()][];

			while (iterator.hasNext()) {
				String methodName = iterator.next();

				Parameter[] parameters = methodCache.get(methodName);

				Object[] entity = new Object[parameters.length];

				for (int i = 0; i < parameters.length; i++) {
					entity[i] = parameters[i].getParamEntity(facilityContainer);
				}

				dependentyEntitys[index++] = entity;
			}

			return new Object[] { dependentyMethods, dependentyEntitys };
		}
		
	}
	
	//	update 2009-07-04 21:48:26	从sc.facilitycontainer.scarfskin.support目录移动到
	//	sc.facilitycontainer.scarfskin目录 作为外皮的实现细节不公开接口
	
	//	update 2009-07-04 22:09:53	修改了引用非单体组件时返回的是同一对象(单体)的错误
}
