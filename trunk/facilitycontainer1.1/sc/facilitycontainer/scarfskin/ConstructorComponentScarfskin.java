/*
 * facilitycontainer1.1
 * 该软件包提供外皮对象的基本实现.
 */
package sc.facilitycontainer.scarfskin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Naming;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.ConstantContext;

/**
 * 构造函数组件外皮.
 * 		根据提供的parameters从componentType中选择一个最为适合的构造函数对象并使用naming用作唯一的标识以isSingle为存储方式保存在一个容其中.
 * 		
 * @author suchen
 * @time 2008-4-6 下午05:40:21
 * @email xiaochen_su@126.com
 */
public final class ConstructorComponentScarfskin extends AbstractComponentScarfskin {
	/** 该构造函数所依赖的参数对象 */
	private Parameter[] parameters = null;
	/** 构造函数参数实体 */
	private Object[] dependentyEntitys = null;
	/** 根据参数对象所寻找到的构造函数 */
	private Constructor<?> dependentyConstructor = null;
	/** 参数管理器 */
	private final ManagerParameter managerParameter = new ConstructorComponentScarfskinManagerParameter();
	
	//	////////////////////
	//	构造函数
	//	////////////////////

	/**
	 * 创建一个构造函数组件外皮.
	 * 
	 * @param componentType Class 组件类型.
	 * @param naming Naming 在容器中的命名.
	 * @param isSingle boolean 是否以单体保存.
	 * @param parameters Parameter[] 参数对象.
	 */
	public ConstructorComponentScarfskin(Class<?> componentType, Naming naming, boolean isSingle, Parameter[] parameters) {
		super(componentType, naming, isSingle);
		// TODO Auto-generated constructor stub
		
		this.parameters = parameters;
		this.dependentyEntitys = new Object[parameters.length];
	}
	
	/**
	 * 创建一个构造函数组件外皮.
	 * 
	 * @param componentType Class 组件类型.
	 * @param naming Naming 在容器中的命名.
	 * @param isSingle boolean 是否以单体保存.
	 */
	public ConstructorComponentScarfskin(Class<?> componentType, Naming naming, boolean isSingle) {
		super(componentType, naming, isSingle);
		
		this.parameters = ConstantContext.EMPTY_PARAMETER_ARRAY;
		this.dependentyEntitys = ConstantContext.EMPTY_OBJECT_ARRAY;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.scarfskin.AbstractComponentScarfskin#createComponent()
	 */
	@Override
	Object createComponent() throws InstantiationException, IllegalAccessException, InvocationTargetException  {
		// TODO Auto-generated method stub
		return dependentyConstructor.newInstance(dependentyEntitys);
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.scarfskin.AbstractComponentScarfskin#manageParameter(sc.facilitycontainer.FacilityContainer)
	 */
	@Override
	void manageParameter(FacilityContainer facilityContainer) throws Exception {
		// TODO Auto-generated method stub
		
		Object[] array = (Object[])managerParameter.manageParameter(facilityContainer, new Object[]{parameters, getComponentType()});
		
		dependentyConstructor = (Constructor<?>) array[0];
		dependentyEntitys = (Object[]) array[1];
		
//		Class<?>[] expectTypes = new Class[parameters.length];
//		
//		for(int i = 0; i < parameters.length; i++) {
//			Parameter parameter = parameters[i];
//			
//			Object entity = parameter.getParamEntity(facilityContainer);
//			
//			Class<?> dependentyType = parameter.getExpectType(facilityContainer);
//			
//			expectTypes[i] = dependentyType;
//			
//			if(entity == null) {
//				throw new ParameterException(new ComponentNotFoundException());
//			}
//			
//			dependentyEntitys[i] = entity;
//		}
//		
//		dependentyConstructor = ClassUtils.getConstructor(getComponentType(), expectTypes);

	}

}
