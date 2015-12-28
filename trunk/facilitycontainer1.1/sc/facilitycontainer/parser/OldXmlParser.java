/*
 * facilitycontainer1.1
 * 该软件包提供了解析xml文件的全部功能.
 * 解析程序会对用户提供的*.xml进行验证与解析.
 */
package sc.facilitycontainer.parser;

import java.io.IOException;
import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.XMLConstants;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.ConstantContext;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.context.FacilityContainerContext;
import sc.facilitycontainer.exception.FacilityContainerException;
import sc.facilitycontainer.naming.SimpleNaming;
import sc.facilitycontainer.parameter.BasicParameter;
import sc.facilitycontainer.parameter.ByNameParameter;
import sc.facilitycontainer.parameter.CollectionParameterFactory;
import sc.facilitycontainer.parameter.DefaultCollectionParameterFactory;
import sc.facilitycontainer.parameter.RefParameter;
import sc.facilitycontainer.scarfskin.ComponentScarfskinFactory;
import sc.facilitycontainer.scarfskin.DefaultComponentScarfskinFactory;
import sc.facilitycontainer.scarfskin.MethodComponentScarfskin;
import sc.facilitycontainer.scarfskin.SetterComponentScarfskin;
import sc.utils.StringUtils;
import sc.utils.io.Resource;

/**
 * 解析xml并建模.
 * 
 * @author suchen
 * @time 2008-4-13 上午11:28:56
 * @email xiaochen_su@126.com
 */
public class OldXmlParser {

	/** xml资源 */
	private Resource xmlResource = null;

	/** xsd资源 */
	private Resource xsdResource = null;

	/**
	 * 创建解析程式.
	 * 
	 * @param xmlResource
	 *            Resource 可访问的xml资源对象.
	 * @param xsdResource
	 *            Resource 可访问的xsd资源对象.
	 * 
	 */
	public OldXmlParser(Resource xmlResource, Resource xsdResource) {
		this.xmlResource = xmlResource;
		this.xsdResource = xsdResource;
	}

	/**
	 * 验证程式.
	 * 
	 * @throws SAXException
	 *             验证过程中可能会发生该异常.往往由于客户给定的xml没有遵循xsd的定义.
	 * @throws IOException
	 *             验证过程中可能会发生该异常.
	 */
	public void vaildate() throws SAXException, IOException {

		SchemaFactory factory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = factory.newSchema(new StreamSource(xsdResource
					.getInputStream()));

			Validator validator = schema.newValidator();
			validator.setErrorHandler(new FCErrorHandler());
			validator.validate(new StreamSource(xmlResource.getInputStream()));

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	/**
	 * 解析并建模.
	 * 
	 * @param facilityContainerContext
	 *            FacilityContainerContext 上下文.
	 * 
	 * @throws SAXException
	 *             解析过程中可能会发生该异常.
	 * @throws IOException
	 *             解析过程中可能会发生该异常.
	 */
	public void parser(FacilityContainerContext facilityContainerContext)
			throws SAXException, IOException {
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();

			reader.setContentHandler(new FCContentHandler(
					facilityContainerContext));
			reader.setErrorHandler(new FCErrorHandler());

			reader.parse(new InputSource(xmlResource.getInputStream()));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

}

/**
 * 完成解析的主要工作的类.
 * 
 * @author suchen
 * @time 2008-4-27 下午03:25:42
 * @email xiaochen_su@126.com
 */
class FCContentHandler implements ContentHandler {

	/** 上下文 将解析的结果装入该上下文中 */
	private FacilityContainerContext facilityContainerContext = null;

	/** 是否为基本类型 */
	private boolean isFundamentalType = false;

	/** 基本类型 并非java的基本类型 */
	private String primitiveType = null;

	/** 集合参数工厂 */
	private CollectionParameterFactory collectionParameterFactory = new DefaultCollectionParameterFactory();

	/** 组件外皮工厂 */
	private ComponentScarfskinFactory componentScarfskinFactory = new DefaultComponentScarfskinFactory();

	// //////////////////////////////////////////////
	//
	// start parameter cache
	//
	// 方法使用的各种参数对象的缓存;每种参数形式形式都有对应的缓存.
	// 以下注意几点
	//		
	// 1.在任何情况下都有constructor缓存或者说缓存不是null的,唯一的区别是有没有参数对象.
	// 2.只有包含了setter,method时缓存才出现或者说缓存不是null.
	// 2.1对于setter缓存来说每一个参数对象对应的就是一个setter方法;
	// 也就是说如果存在setter缓存,那么这个缓存至少有一个参数对象.
	// 2.2对于method/constructor缓存可以没有参数也可以有多个.
	//
	// /////////////////////////////////////////////

	/** 构造函数对象 */
	private List<Parameter> constructorParamCache = null;

	/** setter方法对象 */
	private List<Parameter> setterParamCache = null;

	/** method方法对象 */
	private List<Parameter> methodParamCache = null;

	/** 集合类型参数缓存 */
	private List<Parameter> collectionParamCache = null;

	/** 集合参数-数组类型 */
	private Class<?> arrayType = null;

	/** map参数中key的当前值 */
	private String key = null;

	// end collection parameter

	/** setter对象的名称 */
	private String setterName = null;

	/** 当前值 所代表 0 constructor 1 setter 2 method 3 list 4 map 5 set 6 array */
	private int state = -1;

	private int tempState = -1;

	/** 引用对象 引用着上下文中对象的引用对象 */
	private String ref = null;

	/** 基本类型元素名称列表 非java基本类型 */
	private static final String[] FUNDAMENTAL_TYPES = new String[] { "short",
			"int", "long", "float", "double", "string", "boolean" };

	/** 结合类型元素名称列表 */
	private static final String[] COLLECTION_TYPES = new String[] {
			"constructor", "setter", "method", "list", "map", "set", "array" };
	
	// ////////////////////////
	// start component dependency
	// 组件所依赖的一些对象
	// ////////////////////////

	/** 组件id */
	private String id;

	/** 组件类型 */
	private Class<?> componentType;

	/** 是否以单体保存 */
	private boolean isSingle = true;

	// end component dependency

	// ////////////////////////////////////
	// start method dependency
	// MethodComponentScarfskin 所依赖的对象.
	// ////////////////////////////////////

	/** 是否开始setter对象的解析 */
	private boolean isSetter = false;

	/** 是否开始ref对象的解析 */
	private boolean isRef = false;

	/** 是否开始method对象的解析 */
	private boolean isMethodCache = false;

	/** 方法参数对象缓存 */
	private Map<String, Parameter[]> methodCache = null;

	/** 方法名称 */
	private String methodName = null;

	// end method dependency

	// /////////////////////////
	// 构造函数
	// /////////////////////////

	/**
	 * 
	 * @param facilityContainerContext
	 */
	public FCContentHandler(FacilityContainerContext facilityContainerContext) {
		this.facilityContainerContext = facilityContainerContext;
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String value = String.valueOf(ch, start, length).trim();

		if (value.length() == 0) {
			return;
		}

		if (isFundamentalType) {
			primitiveType = value;
		}

		if (isRef) {
			ref = value;
		}
	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equals("property")) {
			tempState = -1;
		}

		// TODO Auto-generated method stub
		if (localName.equals("component")) {

			ComponentScarfskin componentScarfskin = null;

			if (constructorParamCache == null) {
				// BasicComponentScarfskin
				componentScarfskin = componentScarfskinFactory
						.getComponentScarfskin(new SimpleNaming(id), isSingle,
								new Object[] { componentType },
								ConstantContext.BASIC_SCARFSKIN);
			} else {
				componentScarfskin = componentScarfskinFactory
						.getComponentScarfskin(
								new SimpleNaming(id),
								isSingle,
								new Object[] {
										componentType,
										constructorParamCache
												.toArray(new Parameter[constructorParamCache
														.size()]) },
								ConstantContext.CONSTRUCTOR_SCARFSKIN);
				constructorParamCache = null;
			}

			if (setterParamCache != null) {
				componentScarfskin = new SetterComponentScarfskin(
						componentScarfskin,
						setterParamCache.toArray(new Parameter[setterParamCache
								.size()]));
				setterParamCache = null;
			}

			if (methodCache != null) {
				componentScarfskin = new MethodComponentScarfskin(
						componentScarfskin, methodCache);
				isMethodCache = false;
				methodName = null;
				methodCache = null;
			}

			if (!facilityContainerContext.registerComponent(componentScarfskin)) {
				throw new java.lang.IllegalArgumentException("以 "
						+ componentScarfskin.getNaming().toString()
						+ " 命名的组件可能已经存在容器中");
			}

			componentScarfskin = null;

			isSetter = false;
		}

		if (localName.equals("method")) {
			methodCache.put(methodName, methodParamCache
					.toArray(new Parameter[methodParamCache.size()]));
			methodName = null;
			isMethodCache = false;
		}

		// ///////////////////////////
		// 以下是对依赖的解析.
		// ///////////////////////////

		// start -> 处理集合类型装载并清空缓存.
		int collectionIndex = StringUtils.containsIndex(COLLECTION_TYPES, localName);

		switch (collectionIndex) {
		case 3: {
			Parameter listParameter = collectionParameterFactory
					.getCollectionParameter(null, collectionParamCache,
							ConstantContext.LIST_PARAMETER);
			setCollectionParameter(listParameter); // listParameter = null;
			collectionParamCache = null;
		}
			break;
		case 4: {
			Parameter mapParameter = collectionParameterFactory
					.getCollectionParameter(null, collectionParamCache,
							ConstantContext.MAP_PARAMETER);
			setCollectionParameter(mapParameter); // mapParameter = null;
			collectionParamCache = null;
		}
			break;
		case 5: {
			Parameter setParameter = collectionParameterFactory
					.getCollectionParameter(null, collectionParamCache,
							ConstantContext.SET_PARAMETER);
			setCollectionParameter(setParameter); // setParameter = null;
			collectionParamCache = null;
		}
			break;
		case 6: {
			Parameter arrayParameter = collectionParameterFactory
					.getCollectionParameter(arrayType, collectionParamCache,
							ConstantContext.ARRAY_PARAMETER);
			setCollectionParameter(arrayParameter); // arrayParameter = null;
			arrayType = null;
			collectionParamCache = null;
		}
			break;
		default:
			break;
		}

		// end -> 处理集合类型装在并清空缓存.

		// start -> 开始处理基本类型
		int otherIndex = StringUtils
				.containsIndex(FUNDAMENTAL_TYPES, localName);

		switch (otherIndex) {
		case 0: {
			setPrimitiveOtherParameter(Short.valueOf(primitiveType),
					short.class);
		}
			break;// short
		case 1: {
			setPrimitiveOtherParameter(Integer.valueOf(primitiveType),
					int.class);
		}
			break;// int
		case 2: {
			setPrimitiveOtherParameter(Long.valueOf(primitiveType), long.class);
		}
			break;// long
		case 3: {
			setPrimitiveOtherParameter(Float.valueOf(primitiveType),
					float.class);
		}
			break;// float
		case 4: {
			setPrimitiveOtherParameter(Double.valueOf(primitiveType),
					double.class);
		}
			break;// double
		case 5: {
			setPrimitiveOtherParameter(primitiveType, String.class);
		}
			break;// string
		case 6: {
			setPrimitiveOtherParameter(Boolean.valueOf(primitiveType),
					boolean.class);
		}
			break;// boolean
		default:
			break;
		}

		// start -> 开始处理引用类型.
		if (localName.equals("ref")) {
			RefParameter parameter = new RefParameter(new SimpleNaming(ref));

			if (tempState == -1)
				switch (state) {
				case 0: {
					constructorParamCache.add(parameter);
				}
					break;
				case 1: {
					setterParamCache.add(new ByNameParameter(parameter,
							setterName));
				}
					break;
				case 2: {
					methodParamCache.add(parameter);
				}
					break;
				default:
					break;
				}

			switch (tempState) {
			case 3: {
				collectionParamCache.add(parameter);
			}
				break;
			case 4: {
				collectionParamCache.add(new ByNameParameter(parameter, key));
			}
				break;
			case 5: {
				collectionParamCache.add(parameter);
			}
				break;
			case 6: {
				collectionParamCache.add(parameter);
			}
				break;
			default:
				break;
			}

			isRef = false;
		}

		if (localName.equals("key")) {
			key = null;
		}

	}

	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		// TODO Auto-generated method stub

		if (localName.equals("component")) {

			id = atts.getValue("id");

			try {
				componentType = Class.forName(atts.getValue("class"));
			} catch (ClassNotFoundException e) {
				throw new FacilityContainerException(e);
			}

			String temp = atts.getValue("single");

			if (temp == null) {
				isSingle = true;
			} else {
				isSingle = Boolean.valueOf(temp).booleanValue();
			}

		}

		if (localName.equals("constructor")) {
			state = 0;
			constructorParamCache = new ArrayList<Parameter>();
		}

		if (localName.equals("setter")) {
			state = 1;
			setterName = atts.getValue("name");

			if (!isSetter) {
				isSetter = true;

				setterParamCache = new ArrayList<Parameter>();
			}

		}
		if (localName.equals("method")) {
			state = 2;
			if (!isMethodCache) {
				isMethodCache = true;
				methodName = atts.getValue("name");
				methodCache = new LinkedHashMap<String, Parameter[]>();
			}

			methodParamCache = new ArrayList<Parameter>();
		}
		
		if (StringUtils.contains(FUNDAMENTAL_TYPES, localName)) {
			isFundamentalType = true;
		}

		if (localName.equals("ref")) {
			isRef = true;
		}

		int index = StringUtils.containsIndex(COLLECTION_TYPES, localName);

		if (index >= 3 && index <= 6) {

			if (index == 6) {
				try {
					arrayType = Array.newInstance(
							Class.forName(atts.getValue("type")), 0).getClass();
				} catch (NegativeArraySizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// state = index;
			tempState = index;

			collectionParamCache = new ArrayList<Parameter>();
		}

		if (localName.equals("key")) {
			key = atts.getValue("name");
		}
	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
	}

	/**
	 * 装载集合参数.
	 * 
	 * @param state
	 *            int 参数 state域
	 * @param parameter
	 *            Parameter 被放入的参数对象.
	 */
	private void setCollectionParameter(Parameter parameter) {
		if (constructorParamCache != null && methodParamCache == null
				&& setterParamCache == null) {
			constructorParamCache.add(parameter);
		} else if (setterParamCache != null && methodParamCache == null) {
			setterParamCache.add(new ByNameParameter(parameter, setterName));
		} else if (methodParamCache != null) {
			methodParamCache.add(parameter);
		}
	}

	/**
	 * 装载基本类型参数.
	 * 
	 * @param param
	 *            Object 基本类型参数.
	 * @param clazz
	 *            Class 基本类型的Class.
	 */
	private void setPrimitiveOtherParameter(Object param, Class<?> clazz) {
		if (tempState == -1)
			switch (state) {
			case 0: {
				constructorParamCache.add(new BasicParameter(param, clazz));
			}
				break;
			case 1: {
				setterParamCache.add(new ByNameParameter(new BasicParameter(
						param, clazz), setterName));
			}
				break;
			case 2: {
				methodParamCache.add(new BasicParameter(param, clazz));
			}
				break;

			default:
				break;
			}

		switch (tempState) {
		case 3: {
			collectionParamCache.add(new BasicParameter(param, clazz));
			// listParameter.addElement(new BasicParameter(param,clazz));
		}
			break;
		case 4: {
			collectionParamCache.add(new ByNameParameter(new BasicParameter(
					param, clazz), key));
			// mapParameter.addElement(new ByNameParameter(new
			// BasicParameter(param, clazz), key));
		}
			break;
		case 5: {
			collectionParamCache.add(new BasicParameter(param, clazz));
			// setParameter.addElement(new BasicParameter(param, clazz));
		}
			break;
		case 6: {
			collectionParamCache.add(new BasicParameter(param, clazz));
			// arrayParameter.addElement(new BasicParameter(param, clazz));
		}
			break;
		default:
			break;
		}
	}
}

/**
 * 错误处理类
 * 
 * @author suchen
 * @time 2008-5-3 下午01:38:52
 * @email xiaochen_su@126.com
 */
class FCErrorHandler implements ErrorHandler {

	public void error(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub
		throw exception;
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub
		throw exception;
	}

	public void warning(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub
		throw exception;
	}

}