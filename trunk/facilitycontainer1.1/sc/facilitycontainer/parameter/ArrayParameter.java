/*
 * facilitycontainer1.1
 * 该软件包 提供参数对象的基本实现. 包含了对 基本类型,集合类型,引用类型的抽象形式.
 */
package sc.facilitycontainer.parameter;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.exception.ParameterException;

/**
 * 数组形式的集合参数.
 * 
 * @author suchen
 * @time 2008-4-4 下午09:55:38
 * @email xiaochen_su@126.com
 */
public final class ArrayParameter extends CollectionParameter {
	/** 元素缓存 */
	private List<Object> elementCache = new ArrayList<Object>();
	
	//	///////////////////////////////
	//	构造函数
	//	///////////////////////////////
	
	/**
	 * 构造创建一个数组形式的集合参数.
	 * 
	 * @param expectType Class<?> 预期类型,用来和目标进行类型的匹配.
	 */
	public ArrayParameter(Class<?> expectType) {
		super(expectType, expectType);
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.parameter.CollectionParameter#createParamEntity(sc.facilitycontainer.FacilityContainer)
	 */
	@Override
	Object createParamEntity(FacilityContainer facilityContainer) throws ParameterException {
		// TODO Auto-generated method stub
		int length = cache.size();
		
		for(int i = 0; i < length; i++) {
			Parameter parameter = cache.get(i);
			
			elementCache.add(parameter.getParamEntity(facilityContainer));
		}
		
		
		Object[] array = (Object[])Array.newInstance(getExpectType(facilityContainer).getComponentType(), length);
		
		for(int i = 0; i < length; i++) {
			
			array[i] = elementCache.get(i);
		}
		
		return array;
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.parameter.CollectionParameter#getCheckElement()
	 */
	@Override
	CheckElement getCheckElement() {
		// TODO Auto-generated method stub
		return new CheckArrayAndList();
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.parameter.CollectionParameter#getElementCache()
	 */
	@Override
	Object getElementCache() {
		// TODO Auto-generated method stub
		return elementCache;
	}
	
}
