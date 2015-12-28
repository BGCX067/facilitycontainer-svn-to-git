package sc.facilitycontainer.context;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

import org.xml.sax.SAXException;

import sc.facilitycontainer.ControllableFacilityContainer;
import sc.facilitycontainer.container.StaticContainerFactory;
import sc.facilitycontainer.parser.XmlParser;
//import sc.facilitycontainer.parser.XmlParser;
import sc.utils.ClassUtils;
import sc.utils.io.Resource;

class DefaultContextStrategy implements ContextStrategy {

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.context.ContextStrategy#getControllableFacilityContainer(sc.utils.io.Resource)
	 */
	public ControllableFacilityContainer getControllableFacilityContainer(
			Resource propertiesResource) throws IOException {
		if(propertiesResource == null) {
			return StaticContainerFactory.createContainer(null);
		}
		
		// TODO Auto-generated method stub
		Properties properties = new Properties();
		properties.load(propertiesResource.getInputStream());

		return StaticContainerFactory.createContainer(properties);
	}

	/*
	 * (non-Javadoc)
	 * @see sc.facilitycontainer.context.ContextStrategy#init(sc.facilitycontainer.context.FacilityContainerContext, sc.utils.io.Resource)
	 */
	public void init(FacilityContainerContext facilityContainerContext, Resource xmlResource) {
		// TODO Auto-generated method stub
		Resource xsdResource = new Resource() {

			public InputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return ClassUtils.getResourceAsStream("sc/facilitycontainer/file/facilitycontainerschema.xsd");
			}

			public String getResourceName() {
				// TODO Auto-generated method stub
				return null;
			}

			public File toFile() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			public URL toURL() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			public OutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

		};

//		XmlParser parser = new XmlParser(xmlResource, xsdResource);
//
//		try {
//			parser.vaildate();
//			parser.parser(facilityContainerContext);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			XmlParser.parser(xsdResource, xmlResource, facilityContainerContext);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
