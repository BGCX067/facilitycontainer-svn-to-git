package sc.facilitycontainer.scarfskin;

import java.util.Map;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.ConstantContext;
import sc.facilitycontainer.Naming;
import sc.facilitycontainer.Parameter;


/**
 * 组件外皮工厂.
 * 
 * @author suchen
 * @time 2008-6-10 下午02:47:56
 * @email xiaochen_su@126.com
 */
public abstract class ComponentScarfskinFactory {
	
	/**
	 * 返回组件外皮.
	 * 
	 * @param naming Naming 命名对象.
	 * @param isSingle boolean 是否以单体保存.
	 * @param entity Object[] 创建外皮所依赖的数组. <br />
	 * 元素约束如果不按照这样的约束将会造成一些列的异常<br />
	 * <table>
	 * 	<tr><td>适用的外皮对象</td><td>约束</td></tr>
	 * 	<tr><td>BasicComponentScarfskin</td><td>entity的规格:长度为1的数组,1.组件类型.</td></tr>
	 *  <tr><td>ConstructorComponentScarfskin</td><td>entity的规格:长度为2的数组;1.组件类型,2.参数数组.</td></tr>
	 * </table>
	 * 
	 * @param tag int 根据该标记选择组件外皮. 参见 sc.facilitycontainer.ConstantContext. 
	 * 
	 * @return ComponentScarfskin 组件外皮.
	 */
	public ComponentScarfskin getComponentScarfskin(Naming naming, boolean isSingle, Object[] entity, int tag) {
		return createComponentScarfskin(naming, isSingle, entity, tag);
	}
	
	/**
	 * 返回基本的组件外皮.
	 * 
	 * @param naming Naming 命名对象.
	 * @param isSingle boolean 是否以单体保存. 
	 * @param componentType Class<?> 组件类型.
	 * @return ComponentScarfskin 组件外皮.
	 */
	public ComponentScarfskin getBasicComponentScarfskin(Naming naming, boolean isSingle, Class<?> componentType) {
		return getComponentScarfskin(naming, isSingle, new Object[]{componentType}, ConstantContext.BASIC_SCARFSKIN);
	}
	
	/**
	 * 返回构造函数的组件外皮.
	 * 
	 * @param naming Naming 命名对象.
	 * @param isSingle boolean 是否以单体保存. 
	 * @param componentType Class<?> 组件类型.
	 * @param parameter 参数数组.
	 * 
	 * @return ComponentScarfskin 组件外皮.
	 */
	public ComponentScarfskin getConstructorComponentScarfskin(Naming naming, boolean isSingle, Class<?> componentType, Parameter[] parameter) {
		return getComponentScarfskin(naming, isSingle, new Object[]{componentType, parameter}, ConstantContext.CONSTRUCTOR_SCARFSKIN);
	}
	
	public ComponentScarfskin getSetterComponentScarfskin(ComponentScarfskin componentScarfskin, Parameter[] parameter) {
		return new SetterComponentScarfskin(componentScarfskin, parameter);
	}
	
	public ComponentScarfskin getMethodComponentScarfskin(ComponentScarfskin componentScarfskin, Map<String, Parameter[]> methodCache) {
		return new MethodComponentScarfskin(componentScarfskin, methodCache);
	}
	
	/**
	 * 创建组件外皮.
	 * 
	 * @param naming Naming 命名对象.
	 * @param isSingle boolean 是否以单体保存.
	 * @param entity Object[] 创建外皮所依赖的数组. <br />
	 * 元素约束如果不按照这样的约束将会造成一些列的异常<br />
	 * <table>
	 * 	<tr><td>适用的外皮对象</td><td>约束</td></tr>
	 * 	<tr><td>BasicComponentScarfskin</td><td>entity的规格:长度为1的数组,1.组件类型.</td></tr>
	 *  <tr><td>ConstructorComponentScarfskin</td><td>entity的规格:长度为2的数组;1.组件类型,2.参数数组.</td></tr>
	 * </table>
	 * 
	 * @param tag int 根据该标记选择组件外皮. 参见 sc.facilitycontainer.ConstantContext. 
	 * 
	 * @return ComponentScarfskin 组件外皮.
	 */
	abstract ComponentScarfskin createComponentScarfskin(Naming naming, boolean isSingle, Object[] entity, int tag);
}
