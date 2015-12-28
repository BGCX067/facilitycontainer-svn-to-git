/*
 * facilitycontainer1.1
 * 该软件包 提供参数对象的基本实现. 包含了对 基本类型,集合类型,引用类型的抽象形式.
 */
package sc.facilitycontainer.parameter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sc.facilitycontainer.FacilityContainer;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.exception.ParameterException;


/**
 * 集合参数.
 * 		该参数引用了其他参数对象中包裹着组件实体作为集合中的元素.
 * 
 * 		集合参数 根据所包含的集合对象进行比较.
 * 
 * @author suchen
 * @time 2008-4-4 下午08:17:35
 * @email xiaochen_su@126.com
 */
public abstract class CollectionParameter extends AbstractParameter {
	
	/** 集合参数的缓存 */
	List<Parameter> cache = null;
	/** 保存错误信息的变量 */
	private String errorInfo = null;
	/** 实体缓存 */
	private Object entityCache = null;
	
	//	//////////////////////////
	//	构造函数
	//	//////////////////////////
	
	/**
	 * 基本的集合构造函数.
	 * 		
	 * @param entityType Class<?> 代表某一组件的实体类型.
	 * @param expectType Class<?> 预期类型,用来和目标进行类型的匹配.
	 */
	public CollectionParameter(Class<?> entityType, Class<?> expectType) {
		super(entityType, expectType);
		// TODO Auto-generated constructor stub
	}
		
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.Parameter#getParamEntity(sc.facilitycontainer.FacilityContainer)
	 */
	public Object getParamEntity(FacilityContainer facilityContainer) throws ParameterException {
		// TODO Auto-generated method stub
		if(entityCache != null) {
			return entityCache;
		}
		
		if(!getCheckElement().checkElement()) {
			throw new ParameterException(errorInfo);
		}
		
		synchronized(this) {
			return entityCache = createParamEntity(facilityContainer);
		}
	}
	
	//	//////////////////////
	//	钩子
	//	//////////////////////
	
	/**
	 * 创建参数实体.
	 * 
	 * @param facilityContainer FacilityContainer 需要引用到的容器.
	 * 
	 * @return Object 参数实体.
	 */
	abstract Object createParamEntity(FacilityContainer facilityContainer) throws ParameterException;
	
	
	/**
	 * 返回验证参数对象.
	 * 
	 * @return CheckElement 验证参数对象.
	 */
	abstract CheckElement getCheckElement();
	
	/**
	 * 返回子类各自不同的集合元素缓存.
	 * 
	 * @return Object 集合元素缓存.
	 */
	abstract Object getElementCache();
	
	/**
	 * 验证类型接口
	 * 
	 * @author suchen
	 * @time 2008-4-4 下午09:26:14
	 * @email xiaochen_su@126.com
	 */
	interface CheckElement {
		public boolean checkElement();
	}
	
	/**
	 * 验证Set和Map形式的集合类型参数对象.
	 * 
	 * @author suchen
	 * @time 2008-4-4 下午09:26:27
	 * @email xiaochen_su@126.com
	 */
	class CheckSetAndMap implements CheckElement {
		
		/*
		 * (non-Javadoc)
		 * @see sc.facilitycontainer.parameter.CollectionParameter.CheckElement#checkElement()
		 */
		public boolean checkElement() {
			// TODO Auto-generated method stub
			Set<Parameter> set = new HashSet<Parameter>();
			int length = cache.size();
			
			for(int i = 0; i < length; i++) {
				Parameter element = cache.get(i);
				
				if(set.contains(element)) {
					errorInfo = "element " + element + " 在 "  +  this + " 中出现多次 ";
					return false;
				}
				
				set.add(element);
			}
			
			return true;
		}
		
	}
	
	/**
	 * 验证List和Array形式的集合类型参数对象.
	 * 
	 * @author suchen
	 * @time 2008-4-4 下午09:27:05
	 * @email xiaochen_su@126.com
	 */
	class CheckArrayAndList implements CheckElement {
		
		/*
		 * (non-Javadoc)
		 * @see sc.facilitycontainer.parameter.CollectionParameter.CheckElement#checkElement()
		 */
		public boolean checkElement() {
			// TODO Auto-generated method stub
			return true;
		}
	}
	
	//	//////////////////////////////
	//	逻辑方法
	//	//////////////////////////////
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.parameter.CollectionParameter#toString()
	 */
	public String toString() {
		String toString = getClass().getName();
		Object elementCache = getElementCache();
		String elements = elementCache.toString();
		
		toString += " ";
		toString += elements.substring(1, elements.length() - 1);
		
		return toString;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.parameter.AbstractParameter#equals(java.lang.Object)
	 */
	public boolean equals(Object anObject) {
		if(anObject == this) {
			return true;
		}
		
		if(!CollectionParameter.class.isInstance(anObject)) {
			return false;
		}
		
		CollectionParameter temp = (CollectionParameter)anObject;
		
		return temp.getElementCache().equals(getElementCache());
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = 17;
		Object elementCache = getElementCache();
		
		result = result * 37 + elementCache.hashCode();
		
		return result;
	}
		
}
