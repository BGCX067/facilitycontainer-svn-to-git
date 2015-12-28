/*
 * facilitycontainer1.1
 * 该软件包 提供参数对象的基本实现. 包含了对 基本类型,集合类型,引用类型的抽象形式.
 */
package sc.facilitycontainer.parameter;

import java.util.LinkedHashSet;
import java.util.Set;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.exception.ParameterException;

/**
 * Set形式的集合参数.
 * 
 * @author suchen
 * @time 2008-4-4 下午09:37:12
 * @email xiaochen_su@126.com
 */
public final class SetParameter extends CollectionParameter {
	/** 元素缓存 */
	private Set<Object> elementCache = new LinkedHashSet<Object>();
	
	//	///////////////////////////////
	//	构造函数
	//	///////////////////////////////
	
	/**
	 * 构造创建一个Set形式的集合参数.
	 */
	public SetParameter() {
		super(Set.class, Set.class);
		// TODO Auto-generated constructor stub
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
		
		return elementCache;
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.parameter.CollectionParameter#getCheckElement()
	 */
	@Override
	CheckElement getCheckElement() {
		// TODO Auto-generated method stub
		return new CheckSetAndMap();
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
