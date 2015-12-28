package sc.facilitycontainer.context;

import java.io.IOException;
import java.net.URL;

import sc.utils.io.impl.URLResourceAdapter;

/**
 * 通过适配的URL所代表的资源创建上下文.
 * 
 * @author suchen
 * @time 2008-5-20 下午10:52:51
 * @email xiaochen_su@126.com
 */
public class URLAdapterFacilityContainerContext extends AbstractAdapterFacilityContainerContext {
	
	/**
	 * 创建上下文 资源由适配的URL获得.
	 * 
	 * @param xmlURL URL 代表facilitycontainer.xml的资源.
	 * 
	 * @throws IOException 操作资源时可能会抛出该异常.
	 */
	public URLAdapterFacilityContainerContext(URL xmlURL) throws IOException {
		super(new URLResourceAdapter(xmlURL));
	}
	
	/**
	 * 创建上下文 资源由适配的URL获得.
	 * 
	 * @param xmlURL URL 代表facilitycontainer.xml的资源.
	 * @param propertiesURL URL 代表containerstrategy.properties的资源.
	 *  
	 * @throws IOException 操作资源时可能会抛出该异常.
	 */
	public URLAdapterFacilityContainerContext(URL xmlURL, URL propertiesURL) throws IOException {
		super(new URLResourceAdapter(xmlURL), new URLResourceAdapter(propertiesURL));
	}
	
}
