package sc.facilitycontainer.parser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import javax.xml.validation.SchemaFactory;
import javax.xml.XMLConstants;
import org.xml.sax.helpers.XMLReaderFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import sc.facilitycontainer.ComponentScarfskin;
import sc.facilitycontainer.Naming;
import sc.facilitycontainer.Parameter;
import sc.facilitycontainer.context.FacilityContainerContext;
import sc.facilitycontainer.naming.SimpleNaming;
import sc.facilitycontainer.parameter.BasicParameter;
import sc.facilitycontainer.parameter.CollectionParameterFactory;
import sc.facilitycontainer.parameter.DefaultCollectionParameterFactory;
import sc.facilitycontainer.parameter.RefParameter;
import sc.facilitycontainer.scarfskin.ComponentScarfskinFactory;
import sc.facilitycontainer.scarfskin.DefaultComponentScarfskinFactory;
import sc.utils.io.Resource;
import sc.utils.StringUtils;
import sc.facilitycontainer.parameter.ByNameParameter;

/**
 * 
 * @author suchen
 * @time 2008-7-27 下午02:22:33
 * @email xiaochen_su@126.com
 */
public final class XmlParser {
	private XmlParser() {

	}

	/**
	 * 解析xml文件,将组件外皮放到上下文中.
	 * 
	 * @param xsdResource
	 *            Resource 代表facilitycontainer.xsd的Resource对象.
	 * @param xmlResource
	 *            Resource 代表facilitycontainer.xml的Resource对象.
	 * 
	 * @param facilityContainerContext
	 *            FacilityContainerContext 将组件外皮放到这个上下文中.
	 * 
	 * @throws SAXException
	 *             解析过程中发生的异常.
	 * @throws IOException
	 *             操作资源时发生的异常.
	 */
	public static void parser(Resource xsdResource, Resource xmlResource,
			FacilityContainerContext facilityContainerContext)
			throws SAXException, IOException {
		vaildator(xsdResource.getInputStream(), xmlResource.getInputStream());
		parser(xmlResource.getInputStream(), facilityContainerContext);
	}

	private static void parser(InputStream xmlInputStream,
			FacilityContainerContext facilityContainerContext)
			throws SAXException, IOException {

		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		xmlReader.setContentHandler(new FacilityContainerContentHandler(
				facilityContainerContext));
		xmlReader.setErrorHandler(new FacilityContainerErrorHandler());
		xmlReader.parse(new InputSource(xmlInputStream));

	}

	private static void vaildator(InputStream xsdInputStream,
			InputStream xmlInputStream) throws SAXException, IOException {
		Schema schema = SchemaFactory.newInstance(
				XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(
				new StreamSource(xsdInputStream));
		Validator validator = schema.newValidator();
		validator.setErrorHandler(new FacilityContainerErrorHandler());
		validator.validate(new StreamSource(xmlInputStream));
	}

//	public static void main(String[] args) {
//		StringBuffer buffer = new StringBuffer();
//
//		for (int i = 152, size = 253; i < size; i++) {
//			buffer.append(" ,STACK_TRACE_").append(i);
//		}
//
//		System.out.println(buffer.toString());
//	}
}

/**
 * 
 * @author suchen
 * @time 2008-7-30 上午10:36:50
 * @email xiaochen_su@126.com
 */
class FacilityContainerErrorHandler implements ErrorHandler {

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

/**
 * 简易容器的xml上下文处理类. 实现的方式是采用堆栈记录元素的轨迹.
 * 
 * @author suchen
 * @time 2008-7-30 上午10:36:52
 * @email xiaochen_su@126.com
 */
class FacilityContainerContentHandler implements ContentHandler {

	public FacilityContainerContentHandler(
			FacilityContainerContext facilityContainerContext) {
		this.facilityContainerContext = facilityContainerContext;
	}

	
	// =============XML元素堆栈轨迹开始=========================
	private static final String STACK_TRACE_ROOT = "[components]";
	private static final String STACK_TRACE_1 = "[components, component]";
	// ----------------------------------
	private static final String STACK_TRACE_2 = "[components, component, constructor]";
	private static final String STACK_TRACE_3 = "[components, component, constructor, property]";
	private static final String STACK_TRACE_4 = "[components, component, constructor, property, short]";
	private static final String STACK_TRACE_5 = "[components, component, constructor, property, int]";
	private static final String STACK_TRACE_6 = "[components, component, constructor, property, long]";
	private static final String STACK_TRACE_7 = "[components, component, constructor, property, float]";
	private static final String STACK_TRACE_8 = "[components, component, constructor, property, double]";
	private static final String STACK_TRACE_9 = "[components, component, constructor, property, boolean]";
	private static final String STACK_TRACE_10 = "[components, component, constructor, property, string]";

	private static final String STACK_TRACE_11 = "[components, component, constructor, property, ref]";

	private static final String STACK_TRACE_12 = "[components, component, constructor, property, map]";
	private static final String STACK_TRACE_13 = "[components, component, constructor, property, map, key]";
	private static final String STACK_TRACE_14 = "[components, component, constructor, property, map, key, string]";
	private static final String STACK_TRACE_15 = "[components, component, constructor, property, map, key, ref]";

	private static final String STACK_TRACE_16 = "[components, component, constructor, property, set]";
	private static final String STACK_TRACE_17 = "[components, component, constructor, property, set, item]";
	private static final String STACK_TRACE_18 = "[components, component, constructor, property, set, item, string]";
	private static final String STACK_TRACE_19 = "[components, component, constructor, property, set, item, ref]";

	private static final String STACK_TRACE_20 = "[components, component, constructor, property, list]";
	private static final String STACK_TRACE_21 = "[components, component, constructor, property, list, item]";
	private static final String STACK_TRACE_22 = "[components, component, constructor, property, list, item, string]";
	private static final String STACK_TRACE_23 = "[components, component, constructor, property, list, item, ref]";

	private static final String STACK_TRACE_24 = "[components, component, constructor, property, array]";
	private static final String STACK_TRACE_25 = "[components, component, constructor, property, array, item]";
	private static final String STACK_TRACE_26 = "[components, component, constructor, property, array, item, string]";
	private static final String STACK_TRACE_27 = "[components, component, constructor, property, array, item, ref]";

	// ----------------------------------

	private static final String STACK_TRACE_28 = "[components, component, setter]";
	private static final String STACK_TRACE_29 = "[components, component, setter, property]";
	private static final String STACK_TRACE_30 = "[components, component, setter, property, short]";
	private static final String STACK_TRACE_31 = "[components, component, setter, property, int]";
	private static final String STACK_TRACE_32 = "[components, component, setter, property, long]";
	private static final String STACK_TRACE_33 = "[components, component, setter, property, float]";
	private static final String STACK_TRACE_34 = "[components, component, setter, property, double]";
	private static final String STACK_TRACE_35 = "[components, component, setter, property, boolean]";
	private static final String STACK_TRACE_36 = "[components, component, setter, property, string]";

	private static final String STACK_TRACE_37 = "[components, component, setter, property, ref]";

	private static final String STACK_TRACE_38 = "[components, component, setter, property, map]";
	private static final String STACK_TRACE_39 = "[components, component, setter, property, map, key]";
	private static final String STACK_TRACE_40 = "[components, component, setter, property, map, key, string]";
	private static final String STACK_TRACE_41 = "[components, component, setter, property, map, key, ref]";

	private static final String STACK_TRACE_42 = "[components, component, setter, property, set]";
	private static final String STACK_TRACE_43 = "[components, component, setter, property, set, item]";
	private static final String STACK_TRACE_44 = "[components, component, setter, property, set, item, string]";
	private static final String STACK_TRACE_45 = "[components, component, setter, property, set, item, ref]";

	private static final String STACK_TRACE_46 = "[components, component, setter, property, list]";
	private static final String STACK_TRACE_47 = "[components, component, setter, property, list, item]";
	private static final String STACK_TRACE_48 = "[components, component, setter, property, list, item, string]";
	private static final String STACK_TRACE_49 = "[components, component, setter, property, list, item, ref]";

	private static final String STACK_TRACE_50 = "[components, component, setter, property, array]";
	private static final String STACK_TRACE_51 = "[components, component, setter, property, array, item]";
	private static final String STACK_TRACE_52 = "[components, component, setter, property, array, item, string]";
	private static final String STACK_TRACE_53 = "[components, component, setter, property, array, item, ref]";

	// ----------------------------------

	private static final String STACK_TRACE_54 = "[components, component, method]";
	private static final String STACK_TRACE_55 = "[components, component, method, property]";
	private static final String STACK_TRACE_56 = "[components, component, method, property, short]";
	private static final String STACK_TRACE_57 = "[components, component, method, property, int]";
	private static final String STACK_TRACE_58 = "[components, component, method, property, long]";
	private static final String STACK_TRACE_59 = "[components, component, method, property, float]";
	private static final String STACK_TRACE_60 = "[components, component, method, property, double]";
	private static final String STACK_TRACE_61 = "[components, component, method, property, boolean]";
	private static final String STACK_TRACE_62 = "[components, component, method, property, string]";

	private static final String STACK_TRACE_63 = "[components, component, method, property, ref]";

	private static final String STACK_TRACE_64 = "[components, component, method, property, map]";
	private static final String STACK_TRACE_65 = "[components, component, method, property, map, key]";
	private static final String STACK_TRACE_66 = "[components, component, method, property, map, key, string]";
	private static final String STACK_TRACE_67 = "[components, component, method, property, map, key, ref]";

	private static final String STACK_TRACE_68 = "[components, component, method, property, set]";
	private static final String STACK_TRACE_69 = "[components, component, method, property, set, item]";
	private static final String STACK_TRACE_70 = "[components, component, method, property, set, item, string]";
	private static final String STACK_TRACE_71 = "[components, component, method, property, set, item, ref]";

	private static final String STACK_TRACE_72 = "[components, component, method, property, list]";
	private static final String STACK_TRACE_73 = "[components, component, method, property, list, item]";
	private static final String STACK_TRACE_74 = "[components, component, method, property, list, item, string]";
	private static final String STACK_TRACE_75 = "[components, component, method, property, list, item, ref]";

	private static final String STACK_TRACE_76 = "[components, component, method, property, array]";
	private static final String STACK_TRACE_77 = "[components, component, method, property, array, item]";
	private static final String STACK_TRACE_78 = "[components, component, method, property, array, item, string]";
	private static final String STACK_TRACE_79 = "[components, component, method, property, array, item, ref]";

	// ==============集合类型中新支持的基本类型===============

	// constructor
	private static final String STACK_TRACE_80 = "[components, component, constructor, property, array, item, int]";
	private static final String STACK_TRACE_81 = "[components, component, constructor, property, array, item, short]";
	private static final String STACK_TRACE_82 = "[components, component, constructor, property, array, item, long]";
	private static final String STACK_TRACE_83 = "[components, component, constructor, property, array, item, float]";
	private static final String STACK_TRACE_84 = "[components, component, constructor, property, array, item, double]";
	private static final String STACK_TRACE_85 = "[components, component, constructor, property, array, item, boolean]";

	private static final String STACK_TRACE_86 = "[components, component, constructor, property, set, item, int]";
	private static final String STACK_TRACE_87 = "[components, component, constructor, property, set, item, short]";
	private static final String STACK_TRACE_88 = "[components, component, constructor, property, set, item, long]";
	private static final String STACK_TRACE_89 = "[components, component, constructor, property, set, item, float]";
	private static final String STACK_TRACE_90 = "[components, component, constructor, property, set, item, double]";
	private static final String STACK_TRACE_91 = "[components, component, constructor, property, set, item, boolean]";

	private static final String STACK_TRACE_92 = "[components, component, constructor, property, list, item, int]";
	private static final String STACK_TRACE_93 = "[components, component, constructor, property, list, item, short]";
	private static final String STACK_TRACE_94 = "[components, component, constructor, property, list, item, long]";
	private static final String STACK_TRACE_95 = "[components, component, constructor, property, list, item, float]";
	private static final String STACK_TRACE_96 = "[components, component, constructor, property, list, item, double]";
	private static final String STACK_TRACE_97 = "[components, component, constructor, property, list, item, boolean]";

	private static final String STACK_TRACE_98 = "[components, component, constructor, property, map, key, int]";
	private static final String STACK_TRACE_99 = "[components, component, constructor, property, map, key, short]";
	private static final String STACK_TRACE_100 = "[components, component, constructor, property, map, key, long]";
	private static final String STACK_TRACE_101 = "[components, component, constructor, property, map, key, float]";
	private static final String STACK_TRACE_102 = "[components, component, constructor, property, map, key, double]";
	private static final String STACK_TRACE_103 = "[components, component, constructor, property, map, key, boolean]";

	// setter
	private static final String STACK_TRACE_104 = "[components, component, setter, property, array, item, int]";
	private static final String STACK_TRACE_105 = "[components, component, setter, property, array, item, short]";
	private static final String STACK_TRACE_106 = "[components, component, setter, property, array, item, long]";
	private static final String STACK_TRACE_107 = "[components, component, setter, property, array, item, float]";
	private static final String STACK_TRACE_108 = "[components, component, setter, property, array, item, double]";
	private static final String STACK_TRACE_109 = "[components, component, setter, property, array, item, boolean]";

	private static final String STACK_TRACE_110 = "[components, component, setter, property, set, item, int]";
	private static final String STACK_TRACE_111 = "[components, component, setter, property, set, item, short]";
	private static final String STACK_TRACE_112 = "[components, component, setter, property, set, item, long]";
	private static final String STACK_TRACE_113 = "[components, component, setter, property, set, item, float]";
	private static final String STACK_TRACE_114 = "[components, component, setter, property, set, item, double]";
	private static final String STACK_TRACE_115 = "[components, component, setter, property, set, item, boolean]";

	private static final String STACK_TRACE_116 = "[components, component, setter, property, list, item, int]";
	private static final String STACK_TRACE_117 = "[components, component, setter, property, list, item, short]";
	private static final String STACK_TRACE_118 = "[components, component, setter, property, list, item, long]";
	private static final String STACK_TRACE_119 = "[components, component, setter, property, list, item, float]";
	private static final String STACK_TRACE_120 = "[components, component, setter, property, list, item, double]";
	private static final String STACK_TRACE_121 = "[components, component, setter, property, list, item, boolean]";

	private static final String STACK_TRACE_122 = "[components, component, setter, property, map, key, int]";
	private static final String STACK_TRACE_123 = "[components, component, setter, property, map, key, short]";
	private static final String STACK_TRACE_124 = "[components, component, setter, property, map, key, long]";
	private static final String STACK_TRACE_125 = "[components, component, setter, property, map, key, float]";
	private static final String STACK_TRACE_126 = "[components, component, setter, property, map, key, double]";
	private static final String STACK_TRACE_127 = "[components, component, setter, property, map, key, boolean]";

	// method
	private static final String STACK_TRACE_128 = "[components, component, method, property, array, item, int]";
	private static final String STACK_TRACE_129 = "[components, component, method, property, array, item, short]";
	private static final String STACK_TRACE_130 = "[components, component, method, property, array, item, long]";
	private static final String STACK_TRACE_131 = "[components, component, method, property, array, item, float]";
	private static final String STACK_TRACE_132 = "[components, component, method, property, array, item, double]";
	private static final String STACK_TRACE_133 = "[components, component, method, property, array, item, boolean]";

	private static final String STACK_TRACE_134 = "[components, component, method, property, set, item, int]";
	private static final String STACK_TRACE_135 = "[components, component, method, property, set, item, short]";
	private static final String STACK_TRACE_136 = "[components, component, method, property, set, item, long]";
	private static final String STACK_TRACE_137 = "[components, component, method, property, set, item, float]";
	private static final String STACK_TRACE_138 = "[components, component, method, property, set, item, double]";
	private static final String STACK_TRACE_139 = "[components, component, method, property, set, item, boolean]";

	private static final String STACK_TRACE_140 = "[components, component, method, property, list, item, int]";
	private static final String STACK_TRACE_141 = "[components, component, method, property, list, item, short]";
	private static final String STACK_TRACE_142 = "[components, component, method, property, list, item, long]";
	private static final String STACK_TRACE_143 = "[components, component, method, property, list, item, float]";
	private static final String STACK_TRACE_144 = "[components, component, method, property, list, item, double]";
	private static final String STACK_TRACE_145 = "[components, component, method, property, list, item, boolean]";

	private static final String STACK_TRACE_146 = "[components, component, method, property, map, key, int]";
	private static final String STACK_TRACE_147 = "[components, component, method, property, map, key, short]";
	private static final String STACK_TRACE_148 = "[components, component, method, property, map, key, long]";
	private static final String STACK_TRACE_149 = "[components, component, method, property, map, key, float]";
	private static final String STACK_TRACE_150 = "[components, component, method, property, map, key, double]";
	private static final String STACK_TRACE_151 = "[components, component, method, property, map, key, boolean]";

	// ----------------------
	private static final String STACK_TRACE_152 = "[components, decorator, method, property, array, item, int]";
	private static final String STACK_TRACE_153 = "[components, decorator, method, property, array, item, short]";
	private static final String STACK_TRACE_154 = "[components, decorator, method, property, array, item, long]";
	private static final String STACK_TRACE_155 = "[components, decorator, method, property, array, item, float]";
	private static final String STACK_TRACE_156 = "[components, decorator, method, property, array, item, double]";
	private static final String STACK_TRACE_157 = "[components, decorator, method, property, array, item, boolean]";

	private static final String STACK_TRACE_158 = "[components, decorator, method, property, set, item, int]";
	private static final String STACK_TRACE_159 = "[components, decorator, method, property, set, item, short]";
	private static final String STACK_TRACE_160 = "[components, decorator, method, property, set, item, long]";
	private static final String STACK_TRACE_161 = "[components, decorator, method, property, set, item, float]";
	private static final String STACK_TRACE_162 = "[components, decorator, method, property, set, item, double]";
	private static final String STACK_TRACE_163 = "[components, decorator, method, property, set, item, boolean]";

	private static final String STACK_TRACE_164 = "[components, decorator, method, property, list, item, int]";
	private static final String STACK_TRACE_165 = "[components, decorator, method, property, list, item, short]";
	private static final String STACK_TRACE_166 = "[components, decorator, method, property, list, item, long]";
	private static final String STACK_TRACE_167 = "[components, decorator, method, property, list, item, float]";
	private static final String STACK_TRACE_168 = "[components, decorator, method, property, list, item, double]";
	private static final String STACK_TRACE_169 = "[components, decorator, method, property, list, item, boolean]";

	private static final String STACK_TRACE_170 = "[components, decorator, method, property, map, key, int]";
	private static final String STACK_TRACE_171 = "[components, decorator, method, property, map, key, short]";
	private static final String STACK_TRACE_172 = "[components, decorator, method, property, map, key, long]";
	private static final String STACK_TRACE_173 = "[components, decorator, method, property, map, key, float]";
	private static final String STACK_TRACE_174 = "[components, decorator, method, property, map, key, double]";
	private static final String STACK_TRACE_175 = "[components, decorator, method, property, map, key, boolean]";

	private static final String STACK_TRACE_176 = "[components, decorator, method]";

	private static final String STACK_TRACE_177 = "[components, decorator, method, property]";
	private static final String STACK_TRACE_178 = "[components, decorator, method, property, short]";
	private static final String STACK_TRACE_179 = "[components, decorator, method, property, int]";
	private static final String STACK_TRACE_180 = "[components, decorator, method, property, long]";
	private static final String STACK_TRACE_181 = "[components, decorator, method, property, float]";
	private static final String STACK_TRACE_182 = "[components, decorator, method, property, double]";
	private static final String STACK_TRACE_183 = "[components, decorator, method, property, boolean]";
	private static final String STACK_TRACE_184 = "[components, decorator, method, property, string]";

	private static final String STACK_TRACE_185 = "[components, decorator, method, property, ref]";

	private static final String STACK_TRACE_186 = "[components, decorator, method, property, map]";
	private static final String STACK_TRACE_187 = "[components, decorator, method, property, map, key]";
	private static final String STACK_TRACE_188 = "[components, decorator, method, property, map, key, string]";
	private static final String STACK_TRACE_189 = "[components, decorator, method, property, map, key, ref]";

	private static final String STACK_TRACE_190 = "[components, decorator, method, property, set]";
	private static final String STACK_TRACE_191 = "[components, decorator, method, property, set, item]";
	private static final String STACK_TRACE_192 = "[components, decorator, method, property, set, item, string]";
	private static final String STACK_TRACE_193 = "[components, decorator, method, property, set, item, ref]";

	private static final String STACK_TRACE_194 = "[components, decorator, method, property, list]";
	private static final String STACK_TRACE_195 = "[components, decorator, method, property, list, item]";
	private static final String STACK_TRACE_196 = "[components, decorator, method, property, list, item, string]";
	private static final String STACK_TRACE_197 = "[components, decorator, method, property, list, item, ref]";

	private static final String STACK_TRACE_198 = "[components, decorator, method, property, array]";
	private static final String STACK_TRACE_199 = "[components, decorator, method, property, array, item]";
	private static final String STACK_TRACE_200 = "[components, decorator, method, property, array, item, string]";
	private static final String STACK_TRACE_201 = "[components, decorator, method, property, array, item, ref]";

	private static final String STACK_TRACE_202 = "[components, decorator, setter, property, array, item, int]";
	private static final String STACK_TRACE_203 = "[components, decorator, setter, property, array, item, short]";
	private static final String STACK_TRACE_204 = "[components, decorator, setter, property, array, item, long]";
	private static final String STACK_TRACE_205 = "[components, decorator, setter, property, array, item, float]";
	private static final String STACK_TRACE_206 = "[components, decorator, setter, property, array, item, double]";
	private static final String STACK_TRACE_207 = "[components, decorator, setter, property, array, item, boolean]";

	private static final String STACK_TRACE_208 = "[components, decorator, setter, property, set, item, int]";
	private static final String STACK_TRACE_209 = "[components, decorator, setter, property, set, item, short]";
	private static final String STACK_TRACE_210 = "[components, decorator, setter, property, set, item, long]";
	private static final String STACK_TRACE_211 = "[components, decorator, setter, property, set, item, float]";
	private static final String STACK_TRACE_212 = "[components, decorator, setter, property, set, item, double]";
	private static final String STACK_TRACE_213 = "[components, decorator, setter, property, set, item, boolean]";

	private static final String STACK_TRACE_214 = "[components, decorator, setter, property, list, item, int]";
	private static final String STACK_TRACE_215 = "[components, decorator, setter, property, list, item, short]";
	private static final String STACK_TRACE_216 = "[components, decorator, setter, property, list, item, long]";
	private static final String STACK_TRACE_217 = "[components, decorator, setter, property, list, item, float]";
	private static final String STACK_TRACE_218 = "[components, decorator, setter, property, list, item, double]";
	private static final String STACK_TRACE_219 = "[components, decorator, setter, property, list, item, boolean]";

	private static final String STACK_TRACE_220 = "[components, decorator, setter, property, map, key, int]";
	private static final String STACK_TRACE_221 = "[components, decorator, setter, property, map, key, short]";
	private static final String STACK_TRACE_222 = "[components, decorator, setter, property, map, key, long]";
	private static final String STACK_TRACE_223 = "[components, decorator, setter, property, map, key, float]";
	private static final String STACK_TRACE_224 = "[components, decorator, setter, property, map, key, double]";
	private static final String STACK_TRACE_225 = "[components, decorator, setter, property, map, key, boolean]";

	private static final String STACK_TRACE_226 = "[components, decorator, setter]";
	private static final String STACK_TRACE_227 = "[components, decorator, setter, property]";
	private static final String STACK_TRACE_228 = "[components, decorator, setter, property, short]";
	private static final String STACK_TRACE_229 = "[components, decorator, setter, property, int]";
	private static final String STACK_TRACE_230 = "[components, decorator, setter, property, long]";
	private static final String STACK_TRACE_231 = "[components, decorator, setter, property, float]";
	private static final String STACK_TRACE_232 = "[components, decorator, setter, property, double]";
	private static final String STACK_TRACE_233 = "[components, decorator, setter, property, boolean]";
	private static final String STACK_TRACE_234 = "[components, decorator, setter, property, string]";

	private static final String STACK_TRACE_235 = "[components, decorator, setter, property, ref]";

	private static final String STACK_TRACE_236 = "[components, decorator, setter, property, map]";
	private static final String STACK_TRACE_237 = "[components, decorator, setter, property, map, key]";
	private static final String STACK_TRACE_238 = "[components, decorator, setter, property, map, key, string]";
	private static final String STACK_TRACE_239 = "[components, decorator, setter, property, map, key, ref]";

	private static final String STACK_TRACE_240 = "[components, decorator, setter, property, set]";
	private static final String STACK_TRACE_241 = "[components, decorator, setter, property, set, item]";
	private static final String STACK_TRACE_242 = "[components, decorator, setter, property, set, item, string]";
	private static final String STACK_TRACE_243 = "[components, decorator, setter, property, set, item, ref]";

	private static final String STACK_TRACE_244 = "[components, decorator, setter, property, list]";
	private static final String STACK_TRACE_245 = "[components, decorator, setter, property, list, item]";
	private static final String STACK_TRACE_246 = "[components, decorator, setter, property, list, item, string]";
	private static final String STACK_TRACE_247 = "[components, decorator, setter, property, list, item, ref]";

	private static final String STACK_TRACE_248 = "[components, decorator, setter, property, array]";
	private static final String STACK_TRACE_249 = "[components, decorator, setter, property, array, item]";
	private static final String STACK_TRACE_250 = "[components, decorator, setter, property, array, item, string]";
	private static final String STACK_TRACE_251 = "[components, decorator, setter, property, array, item, ref]";

	private static final String[] STACK_TRACE_GROUP = new String[] {
			STACK_TRACE_ROOT, STACK_TRACE_1, STACK_TRACE_2, STACK_TRACE_3,
			STACK_TRACE_4, STACK_TRACE_5, STACK_TRACE_6, STACK_TRACE_7,
			STACK_TRACE_8, STACK_TRACE_9, STACK_TRACE_10, STACK_TRACE_11,
			STACK_TRACE_12, STACK_TRACE_13, STACK_TRACE_14, STACK_TRACE_15,
			STACK_TRACE_16, STACK_TRACE_17, STACK_TRACE_18, STACK_TRACE_19,
			STACK_TRACE_20, STACK_TRACE_21, STACK_TRACE_22, STACK_TRACE_23,
			STACK_TRACE_24, STACK_TRACE_25, STACK_TRACE_26, STACK_TRACE_27,
			STACK_TRACE_28, STACK_TRACE_29, STACK_TRACE_30, STACK_TRACE_31,
			STACK_TRACE_32, STACK_TRACE_33, STACK_TRACE_34, STACK_TRACE_35,
			STACK_TRACE_36, STACK_TRACE_37, STACK_TRACE_38, STACK_TRACE_39,
			STACK_TRACE_40, STACK_TRACE_41, STACK_TRACE_42, STACK_TRACE_43,
			STACK_TRACE_44, STACK_TRACE_45, STACK_TRACE_46, STACK_TRACE_47,
			STACK_TRACE_48, STACK_TRACE_49, STACK_TRACE_50, STACK_TRACE_51,
			STACK_TRACE_52, STACK_TRACE_53, STACK_TRACE_54, STACK_TRACE_55,
			STACK_TRACE_56, STACK_TRACE_57, STACK_TRACE_58, STACK_TRACE_59,
			STACK_TRACE_60, STACK_TRACE_61, STACK_TRACE_62, STACK_TRACE_63,
			STACK_TRACE_64, STACK_TRACE_65, STACK_TRACE_66, STACK_TRACE_67,
			STACK_TRACE_68, STACK_TRACE_69, STACK_TRACE_70, STACK_TRACE_71,
			STACK_TRACE_72, STACK_TRACE_73, STACK_TRACE_74, STACK_TRACE_75,
			STACK_TRACE_76, STACK_TRACE_77, STACK_TRACE_78, STACK_TRACE_79,
			STACK_TRACE_80, STACK_TRACE_81, STACK_TRACE_82, STACK_TRACE_83,
			STACK_TRACE_84, STACK_TRACE_85, STACK_TRACE_86, STACK_TRACE_87,
			STACK_TRACE_88, STACK_TRACE_89, STACK_TRACE_90, STACK_TRACE_91,
			STACK_TRACE_92, STACK_TRACE_93, STACK_TRACE_94, STACK_TRACE_95,
			STACK_TRACE_96, STACK_TRACE_97, STACK_TRACE_98, STACK_TRACE_99,
			STACK_TRACE_100, STACK_TRACE_101, STACK_TRACE_102, STACK_TRACE_103,
			STACK_TRACE_104, STACK_TRACE_105, STACK_TRACE_106, STACK_TRACE_107,
			STACK_TRACE_108, STACK_TRACE_109, STACK_TRACE_110, STACK_TRACE_111,
			STACK_TRACE_112, STACK_TRACE_113, STACK_TRACE_114, STACK_TRACE_115,
			STACK_TRACE_116, STACK_TRACE_117, STACK_TRACE_118, STACK_TRACE_119,
			STACK_TRACE_120, STACK_TRACE_121, STACK_TRACE_122, STACK_TRACE_123,
			STACK_TRACE_124, STACK_TRACE_125, STACK_TRACE_126, STACK_TRACE_127,
			STACK_TRACE_128, STACK_TRACE_129, STACK_TRACE_130, STACK_TRACE_131,
			STACK_TRACE_132, STACK_TRACE_133, STACK_TRACE_134, STACK_TRACE_135,
			STACK_TRACE_136, STACK_TRACE_137, STACK_TRACE_138, STACK_TRACE_139,
			STACK_TRACE_140, STACK_TRACE_141, STACK_TRACE_142, STACK_TRACE_143,
			STACK_TRACE_144, STACK_TRACE_145, STACK_TRACE_146, STACK_TRACE_147,
			STACK_TRACE_148, STACK_TRACE_149, STACK_TRACE_150, STACK_TRACE_151,
			STACK_TRACE_152, STACK_TRACE_153, STACK_TRACE_154, STACK_TRACE_155,
			STACK_TRACE_156, STACK_TRACE_157, STACK_TRACE_158, STACK_TRACE_159,
			STACK_TRACE_160, STACK_TRACE_161, STACK_TRACE_162, STACK_TRACE_163,
			STACK_TRACE_164, STACK_TRACE_165, STACK_TRACE_166, STACK_TRACE_167,
			STACK_TRACE_168, STACK_TRACE_169, STACK_TRACE_170, STACK_TRACE_171,
			STACK_TRACE_172, STACK_TRACE_173, STACK_TRACE_174, STACK_TRACE_175,
			STACK_TRACE_176, STACK_TRACE_177, STACK_TRACE_178, STACK_TRACE_179,
			STACK_TRACE_180, STACK_TRACE_181, STACK_TRACE_182, STACK_TRACE_183,
			STACK_TRACE_184, STACK_TRACE_185, STACK_TRACE_186, STACK_TRACE_187,
			STACK_TRACE_188, STACK_TRACE_189, STACK_TRACE_190, STACK_TRACE_191,
			STACK_TRACE_192, STACK_TRACE_193, STACK_TRACE_194, STACK_TRACE_195,
			STACK_TRACE_196, STACK_TRACE_197, STACK_TRACE_198, STACK_TRACE_199,
			STACK_TRACE_200, STACK_TRACE_201, STACK_TRACE_202, STACK_TRACE_203,
			STACK_TRACE_204, STACK_TRACE_205, STACK_TRACE_206, STACK_TRACE_207,
			STACK_TRACE_208, STACK_TRACE_209, STACK_TRACE_210, STACK_TRACE_211,
			STACK_TRACE_212, STACK_TRACE_213, STACK_TRACE_214, STACK_TRACE_215,
			STACK_TRACE_216, STACK_TRACE_217, STACK_TRACE_218, STACK_TRACE_219,
			STACK_TRACE_220, STACK_TRACE_221, STACK_TRACE_222, STACK_TRACE_223,
			STACK_TRACE_224, STACK_TRACE_225, STACK_TRACE_226, STACK_TRACE_227,
			STACK_TRACE_228, STACK_TRACE_229, STACK_TRACE_230, STACK_TRACE_231,
			STACK_TRACE_232, STACK_TRACE_233, STACK_TRACE_234, STACK_TRACE_235,
			STACK_TRACE_236, STACK_TRACE_237, STACK_TRACE_238, STACK_TRACE_239,
			STACK_TRACE_240, STACK_TRACE_241, STACK_TRACE_242, STACK_TRACE_243,
			STACK_TRACE_244, STACK_TRACE_245, STACK_TRACE_246, STACK_TRACE_247,
			STACK_TRACE_248, STACK_TRACE_249, STACK_TRACE_250, STACK_TRACE_251

	};
	private static final Stack<String> stack = new Stack<String>();

	// ================XML元素堆栈轨迹结束=========================

	private ComponentScarfskin currentComponentScarfskin = null;

	private Naming naming = null;

	private boolean isSingle = true;

	private Class<?> componentType = null;

	private ComponentScarfskinFactory componentScarfskinFactory = new DefaultComponentScarfskinFactory();

	private CollectionParameterFactory collectionParameterFactory = new DefaultCollectionParameterFactory();

	/*
	 * 这里的"方法"比较特殊 特殊一. 方法的名称可以重复. 特殊二. 方法的参数有多个. 基于以上两项可以得出.
	 * setter方法名称基于bean的命名规则不可能重复。
	 * constructor名称都是一致的,但是基于该实现是选择一个构造函数去创建对象,所以不存在多个构造函数的存储.
	 */
	/** 构造函数参数的缓存 */
	private List<Parameter> constructorParameter = null;
	/** 构造函数参数的缓存 */
	private List<Parameter> setterParameter = null;
	/** 构造函数参数的缓存 */
	private List<Parameter> methodParameter = null;
	/** 参数对象中元素参数的缓存 */
	private List<Parameter> collectionParameterItem = null;
	/** 方法的缓存 */
	private Map<String, Parameter[]> methodCache = null;
	/** 数组类型的缓存 */
	private Class<?> arrayType = null;
	/** <map><key name = ""> map.key.name属性缓存 */
	private String key = null;

	private String setterName = null;

	private String methodName = null;

	private String value = null;

	private FacilityContainerContext facilityContainerContext = null;

	private int functionIndex = 0;

	private Object lock = new Object();

	private StringBuilder strBuilder =	new StringBuilder();
	
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
	
		String temp = String.valueOf(ch, start, length);

		if (temp != null && temp.trim().length() > 0) {
			value = temp;
			
			strBuilder.append(temp);
		}
	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		int index = StringUtils.containsIndex(STACK_TRACE_GROUP, stack
				.toString());

		if (index == 0) {
			// root
		} else if (index == 1) {
			// component

			if (currentComponentScarfskin == null) {
				currentComponentScarfskin = componentScarfskinFactory
						.getBasicComponentScarfskin(naming, isSingle,
								componentType);
			}

			if (setterParameter.size() > 0) {
				currentComponentScarfskin = componentScarfskinFactory
						.getSetterComponentScarfskin(currentComponentScarfskin,
								setterParameter
										.toArray(new Parameter[setterParameter
												.size()]));
			}

			if (methodCache.size() > 0) {
				currentComponentScarfskin = componentScarfskinFactory
						.getMethodComponentScarfskin(currentComponentScarfskin,
								methodCache);
			}

			facilityContainerContext
					.registerComponent(currentComponentScarfskin);

			currentComponentScarfskin = null;
			setterParameter = methodParameter = constructorParameter = collectionParameterItem = null;
			key = setterName = null;
			arrayType = null;
			methodCache = null;
		} else if (index == 2) { // constructor
			currentComponentScarfskin = componentScarfskinFactory
					.getConstructorComponentScarfskin(naming, isSingle,
							componentType, constructorParameter
									.toArray(new Parameter[constructorParameter
											.size()]));
		} else if (index == 28) { // setter
			setterName = null;
		} else if (index == 54) { // method
			if (methodName == null || methodParameter == null) {
				throw new RuntimeException("临时的异常 方法参数或名称不能能为Null");
			}

			methodCache.put(methodName, methodParameter
					.toArray(new Parameter[methodParameter.size()]));

			methodName = null;
			methodParameter = null;
		} else if (index == 13 || index == 39 || index == 65) {
			// constructor/setter/method.property.map.key
			key = null;
		}

		switch (index) {
		case 4: {
			constructorParameter.add(shortBasicParameter());
		}
			;
			break;
		case 5: {
			constructorParameter.add(intBasicParameter());
		}
			;
			break;
		case 6: {
			constructorParameter.add(longBasicParameter());
		}
			;
			break;
		case 7: {
			constructorParameter.add(floatBasicParameter());
		}
			;
			break;
		case 8: {
			constructorParameter.add(doubleBasicParameter());
		}
			;
			break;
		case 9: {
			constructorParameter.add(booleanBasicParameter());
		}
			;
			break;
		case 10: {
			constructorParameter.add(stringBasicParameter());
		}
			;
			break;
		case 11: {
			constructorParameter.add(refParameter());
		}
			;
			break;

		case 14: {
			collectionParameterItem.add(byNameParameter(stringBasicParameter(),
					key));
		}
			;
			break;
		case 15: {
			collectionParameterItem.add(byNameParameter(refParameter(), key));
		}
			;
			break;
		case 18: {
			collectionParameterItem.add(stringBasicParameter());
		}
			;
			break;
		case 19: {
			collectionParameterItem.add(refParameter());
		}
			;
			break;
		case 22: {
			collectionParameterItem.add(stringBasicParameter());
		}
			;
			break;
		case 23: {
			collectionParameterItem.add(refParameter());
		}
			;
			break;
		case 26: {
			collectionParameterItem.add(stringBasicParameter());
		}
			;
			break;
		case 27: {
			collectionParameterItem.add(refParameter());
		}
			;
			break;

		case 12: {
			constructorParameter.add(collectionParameterFactory
					.getMapParameter(collectionParameterItem));
		}
			;
			break;
		case 16: {
			constructorParameter.add(collectionParameterFactory
					.getSetParameter(collectionParameterItem));
		}
			;
			break;
		case 20: {
			constructorParameter.add(collectionParameterFactory
					.getListParameter(collectionParameterItem));
		}
			;
			break;
		case 24: {
			constructorParameter.add(collectionParameterFactory
					.getArrayParameter(arrayType, collectionParameterItem));
		}
			;
			break;

		case 30: {
			setterParameter.add(byNameParameter(shortBasicParameter(),
					setterName));
		}
			;
			break;
		case 31: {
			setterParameter
					.add(byNameParameter(intBasicParameter(), setterName));
		}
			;
			break;
		case 32: {
			setterParameter.add(byNameParameter(longBasicParameter(),
					setterName));
		}
			;
			break;
		case 33: {
			setterParameter.add(byNameParameter(floatBasicParameter(),
					setterName));
		}
			;
			break;
		case 34: {
			setterParameter.add(byNameParameter(doubleBasicParameter(),
					setterName));
		}
			;
			break;
		case 35: {
			setterParameter.add(byNameParameter(booleanBasicParameter(),
					setterName));
		}
			;
			break;
		case 36: {
			setterParameter.add(byNameParameter(stringBasicParameter(),
					setterName));
		}
			;
			break;
		case 37: {
			setterParameter.add(byNameParameter(refParameter(), setterName));
		}
			;
			break;

		case 40: {
			collectionParameterItem.add(byNameParameter(stringBasicParameter(),
					key));
		}
			;
			break;
		case 41: {
			collectionParameterItem.add(byNameParameter(refParameter(), key));
		}
			;
			break;
		case 44: {
			collectionParameterItem.add(stringBasicParameter());
		}
			;
			break;
		case 45: {
			collectionParameterItem.add(refParameter());
		}
			;
			break;
		case 48: {
			collectionParameterItem.add(stringBasicParameter());
		}
			;
			break;
		case 49: {
			collectionParameterItem.add(refParameter());
		}
			;
			break;
		case 52: {
			collectionParameterItem.add(stringBasicParameter());
		}
			;
			break;
		case 53: {
			collectionParameterItem.add(refParameter());
		}
			;
			break;

		case 38: {
			setterParameter.add(byNameParameter(collectionParameterFactory
					.getMapParameter(collectionParameterItem), setterName));
		}
			;
			break;
		case 42: {
			setterParameter.add(byNameParameter(collectionParameterFactory
					.getSetParameter(collectionParameterItem), setterName));
		}
			;
			break;
		case 46: {
			setterParameter.add(byNameParameter(collectionParameterFactory
					.getListParameter(collectionParameterItem), setterName));
		}
			;
			break;
		case 50: {
			setterParameter.add(byNameParameter(collectionParameterFactory
					.getArrayParameter(arrayType, collectionParameterItem),
					setterName));
		}
			;
			break;

		case 56: {
			methodParameter.add(shortBasicParameter());
		}
			;
			break;
		case 57: {
			methodParameter.add(intBasicParameter());
		}
			;
			break;
		case 58: {
			methodParameter.add(longBasicParameter());
		}
			;
			break;
		case 59: {
			methodParameter.add(floatBasicParameter());
		}
			;
			break;
		case 60: {
			methodParameter.add(doubleBasicParameter());
		}
			;
			break;
		case 61: {
			methodParameter.add(booleanBasicParameter());
		}
			;
			break;
		case 62: {
			methodParameter.add(stringBasicParameter());
		}
			;
			break;
		case 63: {
			methodParameter.add(refParameter());
		}
			;
			break;

		case 66: {
			collectionParameterItem.add(byNameParameter(stringBasicParameter(),
					key));
		}
			;
			break;
		case 67: {
			collectionParameterItem.add(byNameParameter(refParameter(), key));
		}
			;
			break;
		case 70: {
			collectionParameterItem.add(stringBasicParameter());
		}
			;
			break;
		case 71: {
			collectionParameterItem.add(refParameter());
		}
			;
			break;
		case 74: {
			collectionParameterItem.add(stringBasicParameter());
		}
			;
			break;
		case 75: {
			collectionParameterItem.add(refParameter());
		}
			;
			break;
		case 78: {
			collectionParameterItem.add(stringBasicParameter());
		}
			;
			break;
		case 79: {
			collectionParameterItem.add(refParameter());
		}
			;
			break;

		case 64: {
			methodParameter.add(collectionParameterFactory
					.getMapParameter(collectionParameterItem));
		}
			;
			break;
		case 68: {
			methodParameter.add(collectionParameterFactory
					.getSetParameter(collectionParameterItem));
		}
			;
			break;
		case 72: {
			methodParameter.add(collectionParameterFactory
					.getListParameter(collectionParameterItem));
		}
			;
			break;
		case 76: {
			methodParameter.add(collectionParameterFactory.getArrayParameter(
					arrayType, collectionParameterItem));
		}
			;
			break;

		case 80: {
			collectionParameterItem.add(intBasicParameter());
		}
			;
			break;
		case 81: {
			collectionParameterItem.add(shortBasicParameter());
		}
			;
			break;
		case 82: {
			collectionParameterItem.add(longBasicParameter());
		}
			;
			break;
		case 83: {
			collectionParameterItem.add(floatBasicParameter());
		}
			;
			break;
		case 84: {
			collectionParameterItem.add(doubleBasicParameter());
		}
			;
			break;
		case 85: {
			collectionParameterItem.add(booleanBasicParameter());
		}
			;
			break;

		case 86: {
			collectionParameterItem.add(intBasicParameter());
		}
			;
			break;
		case 87: {
			collectionParameterItem.add(shortBasicParameter());
		}
			;
			break;
		case 88: {
			collectionParameterItem.add(longBasicParameter());
		}
			;
			break;
		case 89: {
			collectionParameterItem.add(floatBasicParameter());
		}
			;
			break;
		case 90: {
			collectionParameterItem.add(doubleBasicParameter());
		}
			;
			break;
		case 91: {
			collectionParameterItem.add(booleanBasicParameter());
		}
			;
			break;

		case 92: {
			collectionParameterItem.add(intBasicParameter());
		}
			;
			break;
		case 93: {
			collectionParameterItem.add(shortBasicParameter());
		}
			;
			break;
		case 94: {
			collectionParameterItem.add(longBasicParameter());
		}
			;
			break;
		case 95: {
			collectionParameterItem.add(floatBasicParameter());
		}
			;
			break;
		case 96: {
			collectionParameterItem.add(doubleBasicParameter());
		}
			;
			break;
		case 97: {
			collectionParameterItem.add(booleanBasicParameter());
		}
			;
			break;

		case 98: {
			collectionParameterItem.add(byNameParameter(intBasicParameter(),
					key));
		}
			;
			break;
		case 99: {
			collectionParameterItem.add(byNameParameter(shortBasicParameter(),
					key));
		}
			;
			break;
		case 100: {
			collectionParameterItem.add(byNameParameter(longBasicParameter(),
					key));
		}
			;
			break;
		case 101: {
			collectionParameterItem.add(byNameParameter(floatBasicParameter(),
					key));
		}
			;
			break;
		case 102: {
			collectionParameterItem.add(byNameParameter(doubleBasicParameter(),
					key));
		}
			;
			break;
		case 103: {
			collectionParameterItem.add(byNameParameter(
					booleanBasicParameter(), key));
		}
			;
			break;

		case 104: {
			collectionParameterItem.add(intBasicParameter());
		}
			;
			break;
		case 105: {
			collectionParameterItem.add(shortBasicParameter());
		}
			;
			break;
		case 106: {
			collectionParameterItem.add(longBasicParameter());
		}
			;
			break;
		case 107: {
			collectionParameterItem.add(floatBasicParameter());
		}
			;
			break;
		case 108: {
			collectionParameterItem.add(doubleBasicParameter());
		}
			;
			break;
		case 109: {
			collectionParameterItem.add(booleanBasicParameter());
		}
			;
			break;

		case 110: {
			collectionParameterItem.add(intBasicParameter());
		}
			;
			break;
		case 111: {
			collectionParameterItem.add(shortBasicParameter());
		}
			;
			break;
		case 112: {
			collectionParameterItem.add(longBasicParameter());
		}
			;
			break;
		case 113: {
			collectionParameterItem.add(floatBasicParameter());
		}
			;
			break;
		case 114: {
			collectionParameterItem.add(doubleBasicParameter());
		}
			;
			break;
		case 115: {
			collectionParameterItem.add(booleanBasicParameter());
		}
			;
			break;

		case 116: {
			collectionParameterItem.add(intBasicParameter());
		}
			;
			break;
		case 117: {
			collectionParameterItem.add(shortBasicParameter());
		}
			;
			break;
		case 118: {
			collectionParameterItem.add(longBasicParameter());
		}
			;
			break;
		case 119: {
			collectionParameterItem.add(floatBasicParameter());
		}
			;
			break;
		case 120: {
			collectionParameterItem.add(doubleBasicParameter());
		}
			;
			break;
		case 121: {
			collectionParameterItem.add(booleanBasicParameter());
		}
			;
			break;

		case 122: {
			collectionParameterItem.add(byNameParameter(intBasicParameter(),
					key));
		}
			;
			break;
		case 123: {
			collectionParameterItem.add(byNameParameter(shortBasicParameter(),
					key));
		}
			;
			break;
		case 124: {
			collectionParameterItem.add(byNameParameter(longBasicParameter(),
					key));
		}
			;
			break;
		case 125: {
			collectionParameterItem.add(byNameParameter(floatBasicParameter(),
					key));
		}
			;
			break;
		case 126: {
			collectionParameterItem.add(byNameParameter(doubleBasicParameter(),
					key));
		}
			;
			break;
		case 127: {
			collectionParameterItem.add(byNameParameter(
					booleanBasicParameter(), key));
		}
			;
			break;

		case 128: {
			collectionParameterItem.add(intBasicParameter());
		}
			;
			break;
		case 129: {
			collectionParameterItem.add(shortBasicParameter());
		}
			;
			break;
		case 130: {
			collectionParameterItem.add(longBasicParameter());
		}
			;
			break;
		case 131: {
			collectionParameterItem.add(floatBasicParameter());
		}
			;
			break;
		case 132: {
			collectionParameterItem.add(doubleBasicParameter());
		}
			;
			break;
		case 133: {
			collectionParameterItem.add(booleanBasicParameter());
		}
			;
			break;

		case 134: {
			collectionParameterItem.add(intBasicParameter());
		}
			;
			break;
		case 135: {
			collectionParameterItem.add(shortBasicParameter());
		}
			;
			break;
		case 136: {
			collectionParameterItem.add(longBasicParameter());
		}
			;
			break;
		case 137: {
			collectionParameterItem.add(floatBasicParameter());
		}
			;
			break;
		case 138: {
			collectionParameterItem.add(doubleBasicParameter());
		}
			;
			break;
		case 139: {
			collectionParameterItem.add(booleanBasicParameter());
		}
			;
			break;

		case 140: {
			collectionParameterItem.add(intBasicParameter());
		}
			;
			break;
		case 141: {
			collectionParameterItem.add(shortBasicParameter());
		}
			;
			break;
		case 142: {
			collectionParameterItem.add(longBasicParameter());
		}
			;
			break;
		case 143: {
			collectionParameterItem.add(floatBasicParameter());
		}
			;
			break;
		case 144: {
			collectionParameterItem.add(doubleBasicParameter());
		}
			;
			break;
		case 145: {
			collectionParameterItem.add(booleanBasicParameter());
		}
			;
			break;

		case 146: {
			collectionParameterItem.add(byNameParameter(intBasicParameter(),
					key));
		}
			;
			break;
		case 147: {
			collectionParameterItem.add(byNameParameter(shortBasicParameter(),
					key));
		}
			;
			break;
		case 148: {
			collectionParameterItem.add(byNameParameter(longBasicParameter(),
					key));
		}
			;
			break;
		case 149: {
			collectionParameterItem.add(byNameParameter(floatBasicParameter(),
					key));
		}
			;
			break;
		case 150: {
			collectionParameterItem.add(byNameParameter(doubleBasicParameter(),
					key));
		}
			;
			break;
		case 151: {
			collectionParameterItem.add(byNameParameter(
					booleanBasicParameter(), key));
		}
			;
			break;

		}

		stack.pop();
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
		stack.push(localName);

		int index = StringUtils.containsIndex(STACK_TRACE_GROUP, stack
				.toString());

		if (index == 0) {
			// root
		} else if (index == 1) {
			// component

			String id = atts.getValue("id");
			String clazz = atts.getValue("class");
			String single = atts.getValue("single");

//			String _abstract = atts.getValue("abstract");
//			String parent = atts.getValue("parent");
//			
//			if(parent != null && (_abstract != null || clazz != null)) {
//				throw new RuntimeException("");
//			}
						
			if (single != null) {
					this.isSingle = Boolean.valueOf(single);
//				single = "true";
			}

			try {
				componentType = Class.forName(clazz);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}

			naming = new SimpleNaming(id);

			setterParameter = new ArrayList<Parameter>();

			methodCache = new HashMap<String, Parameter[]>();
		} else if (index == 2) {
			//	constructor
			constructorParameter = new ArrayList<Parameter>();
		} else if (index == 28) { // setter
			setterName = atts.getValue("name");
		} else if (index == 54) { // method

			synchronized (lock) {
				methodName = atts.getValue("name") + "#" + functionIndex++;
			}

			methodParameter = new ArrayList<Parameter>();
		} else {

			if (index == 13 || index == 39 || index == 65) {
				//constructor/setter/method.property.map.key
				key = atts.getValue("name");
			} else if (index == 12 || index == 38 || index == 64) {
				//map
				collectionParameterItem = new ArrayList<Parameter>();
			} else if (index == 16 || index == 42 || index == 68) {
				//set
				collectionParameterItem = new ArrayList<Parameter>();
			} else if (index == 20 || index == 46 || index == 72) {
				//list
				collectionParameterItem = new ArrayList<Parameter>();
			} else if (index == 24 || index == 50 || index == 76) {
				//array

				try {
					arrayType = Array.newInstance(
							Class.forName(atts.getValue("type")), 0).getClass();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}

				collectionParameterItem = new ArrayList<Parameter>();
			}

		}
	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	//	//////////////////////
	//	创建参数对象的便捷方法.
	//	//////////////////////

	private BasicParameter shortBasicParameter() {
		strBuilder =	new StringBuilder();
		return basicParameter(Short.valueOf(value), short.class);
	}

	private BasicParameter intBasicParameter() {
		strBuilder =	new StringBuilder();
		return basicParameter(Integer.valueOf(value), int.class);
	}

	private BasicParameter longBasicParameter() {
		strBuilder =	new StringBuilder();
		return basicParameter(Long.valueOf(value), long.class);
	}

	private BasicParameter floatBasicParameter() {
		strBuilder =	new StringBuilder();
		return basicParameter(Float.valueOf(value), float.class);
	}

	private BasicParameter doubleBasicParameter() {
		strBuilder =	new StringBuilder();
		return basicParameter(Double.valueOf(value), double.class);
	}

	private BasicParameter booleanBasicParameter() {
		strBuilder =	new StringBuilder();
		return basicParameter(Boolean.valueOf(value), boolean.class);
	}

	private BasicParameter stringBasicParameter() {
		
		BasicParameter basicParameter = basicParameter(strBuilder.toString(), String.class);
		strBuilder =	new StringBuilder();
		return basicParameter;
	}

	private RefParameter refParameter() {
		return new RefParameter(new SimpleNaming(value));
	}

	private ByNameParameter byNameParameter(Parameter parameter, String name) {
		return new ByNameParameter(parameter, name);
	}

	private BasicParameter basicParameter(Object obj, Class<?> clazz) {
		return new BasicParameter(obj, clazz);
	}
}
