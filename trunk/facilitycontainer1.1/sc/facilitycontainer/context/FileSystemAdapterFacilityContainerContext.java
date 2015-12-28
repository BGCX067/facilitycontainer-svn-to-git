package sc.facilitycontainer.context;

import java.io.File;
import java.io.IOException;

import sc.utils.io.impl.FileResourceAdapter;

/**
 * 通过适配的File所代表的资源创建上下文.
 * 
 * @author suchen
 * @time 2008-5-19 下午10:29:07
 * @email xiaochen_su@126.com
 */
public class FileSystemAdapterFacilityContainerContext extends AbstractAdapterFacilityContainerContext {
	
	/**
	 * 创建上下文 资源由适配的File获得.
	 * 
	 * @param xmlFile File 代表facilitycontainer.xml的资源.
	 * 
	 * @throws IOException 操作资源时可能会抛出该异常.
	 */
	public FileSystemAdapterFacilityContainerContext(File xmlFile) throws IOException {
		super(new FileResourceAdapter(xmlFile));
	}
	
	/**
	 * 创建上下文 资源由适配的File获得.
	 * 
	 * @param xmlFile File 代表facilitycontainer.xml的资源.
	 * @param proptiesFile File 代表containerstrategy.properties的资源.
	 * 
	 * @throws IOException 操作资源时可能会抛出该异常.
	 */
	public FileSystemAdapterFacilityContainerContext(File xmlFile, File propertiesFile) throws IOException {
		super(new FileResourceAdapter(xmlFile), new FileResourceAdapter(propertiesFile));
	}
	

}
