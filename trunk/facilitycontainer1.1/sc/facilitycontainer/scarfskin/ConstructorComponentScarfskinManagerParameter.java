package sc.facilitycontainer.scarfskin;

import java.lang.reflect.Constructor;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.exception.ComponentNotFoundException;
import sc.facilitycontainer.exception.ParameterException;
import sc.facilitycontainer.util.ClassUtils;

/**
 * 
 * @author suchen
 * @time 2008-9-2 下午12:53:59
 * @email xiaochen_su@126.com
 */
class ConstructorComponentScarfskinManagerParameter implements ManagerParameter {
	private Constructor<?> dependentyConstructor = null;

	public Object manageParameter(FacilityContainer facilityContainer, Object[] obj) throws Exception {
		// TODO Auto-generated method stub
		
		if(dependentyConstructor == null) {
	
			Parameter[] parameters = (Parameter[]) obj[0];
			Class<?> componentType = (Class<?>) obj[1];
			
			int length = parameters.length;
			
			Object[] dependentyEntitys = new Object[length];
			
			Class<?>[] expectTypes = new Class[parameters.length];

			for (int i = 0; i < length; i++) {
				Parameter parameter = parameters[i];

				Object entity = parameter.getParamEntity(facilityContainer);

				Class<?> dependentyType = parameter.getExpectType(facilityContainer);

				expectTypes[i] = dependentyType;

				if (entity == null) {
					throw new ParameterException(new ComponentNotFoundException());
				}

				dependentyEntitys[i] = entity;
			}

			dependentyConstructor = ClassUtils.getConstructor(componentType, expectTypes);
			
			return new Object[]{dependentyConstructor, dependentyEntitys};
		} else {
		
			Parameter[] parameters = (Parameter[]) obj[0];
			int length = parameters.length;
			Object[] dependentyEntitys = new Object[length];
			
			for (int i = 0; i < length; i++) {
				Parameter parameter = parameters[i];

				Object entity = parameter.getParamEntity(facilityContainer);


				if (entity == null) {
					throw new ParameterException(new ComponentNotFoundException());
				}

				dependentyEntitys[i] = entity;
			}
			
			return new Object[]{dependentyConstructor, dependentyEntitys};
		}
		
	}

	//	update 2009-07-04 21:48:26	从sc.facilitycontainer.scarfskin.support目录移动到
	//	sc.facilitycontainer.scarfskin目录 作为外皮的实现细节不公开接口
	
	//	update 2009-07-04 22:09:53	修改了引用非单体组件时返回的是同一对象(单体)的错误
}
