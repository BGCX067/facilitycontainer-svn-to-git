/*
 * facilitycontainer1.1
 * 该软件包提供 供容器使用的静态方法, 处理类型,对象字符串等. 
 */
package sc.facilitycontainer.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import sc.facilitycontainer.exception.AmbiguityMethodException;

/**
 * 类型工具.
 * 
 * @author sc
 * @email xiaochen_su@126.com
 * @time 2008-4-3 下午02:40:25
 */
public final class ClassUtils {
	//	///////////////////////
	//	构造函数
	//	///////////////////////
	
	private ClassUtils() {
		
	}	
	
	/** 支持基本类型包装类 */
	private static final Class<?>[] SUSTAIN_PRIMITIVE_PACKAGE_CLASS = new Class<?>[]{
		Integer.class, Short.class, Long.class, Character.class, Boolean.class, Float.class, Double.class
	};
	
	/** 支持的基本类型 */
	private static final Class<?>[] SUSTAIN_PRIMITIVE_CLASS = new Class<?>[]{
		int.class, short.class, long.class, char.class, boolean.class, float.class, double.class
	};
	
	/** 长度的缓存 */
	private static int LENGTH = SUSTAIN_PRIMITIVE_CLASS.length;
	
	
	//throws AmbiguityMethodException 所给的参数类型或名称寻找到多个构造函数,发生不明确的情况抛出该异常.
	
	/**
	 * 根据组件类型与形参类型列表来选择一个最为适合的构造函数对象并返回.
	 * 
	 * @param clazz Class<?> 组件类型.
	 * @param parameterTypes Class<?>[] 形参类型列表.
	 * @return Constructor<?> 寻找到的构造函数.
	 * 
	 * @throws NoSuchMethodException 没有找到指定的构造函数抛出该异常.
	 * 
	 */
	public static Constructor<?> getConstructor(Class<?> clazz, Class<?>[] parameterTypes) throws NoSuchMethodException {
		
		try {
			return clazz.getConstructor(parameterTypes);
		} catch(Exception e) {
			// TODO Auto-generated catch block
		}
		
		Constructor<?>[] constructors = clazz.getConstructors();
		
		List<Constructor<?>> constructorByLength = new ArrayList<Constructor<?>>();
		
		int size = constructors.length;
		
		for(int i = 0; i < size; i++) {
			Constructor<?> constructor = constructors[i];
			
			if(constructor.getParameterTypes().length == parameterTypes.length) {
				constructorByLength.add(constructor);
			}
			
		}
		
		size = constructorByLength.size();
		
		if( size == 0 ) {
			throw new NoSuchMethodException("没有长度为 " + parameterTypes.length + " 的构造函数");
		}
		
		int count = 0;
		int index = 0;
		boolean bool = true;
		
		for(int i = 0; i < size; i++) {
			Constructor<?> constructor = constructorByLength.get(i);
			
			bool = checkType(constructor.getParameterTypes(), parameterTypes);
			
			if(bool) {
				count++;
				index = i;
			}
		} 
		
		if(count != 1) {
			throw new AmbiguityMethodException("存在了" + count + " 个 " + constructorByLength.toString() + " 构造函数. ");
		}
				
		return constructorByLength.get(index);
	}
	
	/**
	 * 返回该组件中的所有setter方法并返回.
	 * 
	 * @param clazz Class<?> 组件类型.
	 * @param fieldNames String[] fieldNames 字段名称.
	 * @param fieldTypes Class<?>[] fieldTypes 字段类型.
	 * 
	 * @return Method[] 寻找到的setter方法数组.
	 * 
	 * @throws NoSuchMethodException 没有寻找到或无法寻找到指定的数量的方法对象时发生该异常.
	 * @throws AmbiguityMethodException 所给的参数类型或名称寻找到多个setter方法,发生不明确的情况抛出该异常.
	 */
	public static Method[] getSetterMethod(Class<?> clazz, String[] fieldNames, Class<?>[] fieldTypes) throws NoSuchMethodException, AmbiguityMethodException {
		List<Method> list = new ArrayList<Method>();
		
		for(int i = 0; i < fieldNames.length; i++) {
			list.add(getSetterMethod(clazz, fieldNames[i], fieldTypes[i]));
		}
		
		return list.toArray(new Method[list.size()]);
	}
	
	/**
	 * 根据名称与类型寻找组件中的setter方法并返回.
	 * 
	 * @param clazz Class<?> 组件类型.
	 * @param fieldName String 字段名称.
	 * @param fieldType Class<?> 字段类型.
	 * 
	 * @return Method 寻找到的setter方法.
	 * 
	 * @throws NoSuchMethodException 没有找到指定的方法发生该异常.
	 * @throws AmbiguityMethodException 所给的参数类型或名称寻找到多个setter方法,发生不明确的情况抛出该异常.
	 */
	public static Method getSetterMethod(Class<?> clazz, String fieldName, Class<?> fieldType) throws NoSuchMethodException, AmbiguityMethodException {
		Method method = null;
		String methodName = StringUtils.toSetterName(fieldName);
		Method[] methods = clazz.getMethods();
		
		int count = 0;
	
		for(int i = 0 ; i < methods.length; i++) {
			
			if(methods[i].getName().equals(methodName)) {
				count++;
				method = methods[i];
			}
		}
		
		if(count == 0) {
			throw new NoSuchMethodException("没有找到名为" + methodName + "的方法");
		}
		
		if(count > 1) {
			throw new AmbiguityMethodException("存在了" + count + "个名为" + methodName + "方法. 以该名称定义的方法仅能有一个");
		}
		
		Class<?>[] temp = method.getParameterTypes();
		
		if(temp.length != 1) {
			throw new NoSuchMethodException("名为" + methodName + "的方法参数长度为 " + temp.length);
		}
		
		Class<?> parameterType = temp[0];
		
		if(parameterType.equals(fieldType)) {
			return method;
		}
		
		if(!(checkType(parameterType, fieldType))) {
			throw new NoSuchMethodException("名为" + methodName + "的方法类型与给定类型不匹配 parameterType " + parameterType + " fieldType " + fieldType);
		}
		
		return method;
	}
	
	/**
	 * 根据方法名称列表与形参类型组寻找组件中的方法并返回.
	 * 
	 * @param clazz Class<?> 组件类型.
	 * @param methodNames String[] 方法名称列表.
	 * @param paramTypeGroup Class<?>[][] 顺序与名称匹配的形参类型组.
	 * 
	 * @return Method[] 寻找到的方法对象组.
	 * 
	 * @throws NoSuchMethodException 没有找到制定的方法对象时返回该异常.
	 * @throws AmbiguityMethodException 所给的参数类型或名称寻找到多个方法对象,发生不明确的情况抛出该异常.
	 */
	public static Method[] getMethod(Class<?> clazz, String[] methodNames, Class<?>[][] paramTypeGroup) throws NoSuchMethodException, AmbiguityMethodException {
		Method[] methods = new Method[methodNames.length];
		
		for(int i = 0; i < methodNames.length; i++) {
			methods[i] = getMethod(clazz, methodNames[i], paramTypeGroup[i]);
		}
		
		return methods;
	}
	
	/**
	 * 根据名称与形参类型列表返回一个方法对象并返回.
	 * 
	 * @param clazz Class<?> 组件类型.
	 * @param methodName String 方法名称.
	 * @param paramTypes Class[]形式参数类型列表.
	 * 
	 * @return Method 寻找到的方法对象.
	 * 
	 * @throws NoSuchMethodException 没有找到制定的方法对象时返回该异常.
	 * @throws AmbiguityMethodException 所给的参数类型或名称寻找到多个方法对象,发生不明确的情况抛出该异常.
	 */
	public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) throws NoSuchMethodException, AmbiguityMethodException {
		
		try {
			return clazz.getMethod(methodName, paramTypes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 没有根据类型直接找到方法这个很正常
			//因为存在 实际类型是参数类型实现或子类型的情况;在发生的异常的时候没有必要作出动作仅需要继续进行就可以了
		}
		
		Method[] allMethod = clazz.getMethods();
		
		List<Method> methodByName = new ArrayList<Method>();
		
		for(int i = 0; i < allMethod.length; i++) {
			Method method = allMethod[i];
			
			if(method.getName().equals(methodName)) {
				methodByName.add(method);
			}
		}
		
		if(methodByName.size() == 0) {
			throw new NoSuchMethodException("没有名为 " + methodName + " 的方法");
		}
		
		int size = methodByName.size();
		
		List<Method> methodByLength = new ArrayList<Method>();
		
		for(int i = 0; i < size; i++) {
			Method method = methodByName.get(i);
			
			if(method.getParameterTypes().length == paramTypes.length) {
				methodByLength.add(method);
			}
		}
		
		if(methodByLength.size() == 0) {
			throw new NoSuchMethodException("已经包含 " + size + " 个名为 " + methodName + " 的方法,但形参列表长度不符合" );
		}
		
		size = methodByLength.size();
		
		int count = 0;
		int index = 0;
		boolean bool = false;
		
		for(int i = 0; i < size; i++) {
			Method method = methodByLength.get(i);
			Class<?>[] classes = method.getParameterTypes();
			
			bool = checkType(classes, paramTypes);
			
			if(bool) {
				count++;
				index = i;
			}
			
		}
		
		if(count != 1) {
			throw new AmbiguityMethodException("存在了" + count + "个 " + methodByLength.toString() + " 方法. ");
		}
		
	
		return methodByLength.get(index);
	}
	
	
	//	对于容器来说参数类型是组件成员方法或构造函数的实际参数类型.实体类型是客户给定的预期类型.
	
	/**
	 * 验证一组类型是否每个元素都有血缘关系.
	 * 
	 * @param parameterTypes Class[] 参数类型.
	 * @param entityTypes Class[] 实体类型.
	 * 
	 * @return boolean 如果这组类型每个元素匹配成功返回true,反之返回false.
	 */
	public static boolean checkType(Class<?>[] parameterTypes, Class<?>[] entityTypes) {
		for(int i = 0; i < parameterTypes.length; i++) {
			if(!checkType(parameterTypes[i], entityTypes[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 验证parameterType与entityType是否有血缘关系.
	 * 		1.大部分验证是单方向的, 验证parameterType是否为entityType的抽象.
	 * 		2.进行基本类型与对应包装类型的验证是双向的.
	 * 		3.本方法用来根据实体类型选择方法对象.
	 * 		
	 * @param parameterType Class<?> 参数类型.
	 * @param entityType Class<?> 实体类型.
	 * 
	 * @return boolean 两个类型是否有关系.
	 */
	public static boolean checkType(Class<?> parameterType, Class<?> entityType) {
		if(parameterType.isAssignableFrom(entityType)) {
			return true;
		}
		
		boolean bool1 = false;
		boolean bool2 = false;
		
		if(parameterType.isPrimitive()) {
			bool1 = true;
		}
		
		if(entityType.isPrimitive()) {
			bool2 = true;
		}
		
		if((bool1 && bool2) || !bool1 && !bool2) {
			return false;
		}
		
		int index = -1;
		
		if(bool1) {
			for(int i = 0; i < LENGTH; i++) {
				if(SUSTAIN_PRIMITIVE_CLASS[i] == parameterType) {
					index = i;
				}
			}
			
			if(index == -1) {
				return false;
			}
			
			return checkPackagePrimitive(SUSTAIN_PRIMITIVE_PACKAGE_CLASS[index], parameterType);
		} else {
			for(int i = 0; i < LENGTH; i++) {
				if(SUSTAIN_PRIMITIVE_CLASS[i] == entityType) {
					index = i;
				}
			}
			
			if(index == -1) {
				return false;
			}
			
			return checkPackagePrimitive(SUSTAIN_PRIMITIVE_PACKAGE_CLASS[index], entityType);
		}
		
	}
	
	/**
	 * 判断包装类型与基本类型是否能够对应上.
	 * 		
	 * @param packageType Class<?> 包装器类型.
	 * @param primitiveType Class<?> 基本类型.
	 * 
	 * @return boolean 如果可以对应就返回true.
	 */
	private static boolean checkPackagePrimitive(Class<?> packageType, Class<?> primitiveType) {
		if(primitiveType.equals(short.class) && packageType.equals(Short.class)) {
			return true;
		}
		if(primitiveType.equals(int.class) && packageType.equals(Integer.class)) {
			return true;
		}
		if(primitiveType.equals(long.class) && packageType.equals(Long.class)) {
			return true;
		}
		if(primitiveType.equals(char.class) && packageType.equals(Character.class)) {
			return true;
		}
		if(primitiveType.equals(float.class) && packageType.equals(Float.class)) {
			return true;
		}
		if(primitiveType.equals(double.class) && packageType.equals(Double.class)) {
			return true;
		}
		return false;
	}
		
	/**
	 * 返回组件类型对应的数组类型.
	 * 
	 * @param componentType Class<?> 组件类型.
	 * 
	 * @return Class<?> 组件数组类型.
	 */
	public static Class<?> getArrayClass(Class<?> componentType) {
		return java.lang.reflect.Array.newInstance(componentType, 0).getClass();
	}
	
}