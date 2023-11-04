package org.tapmedia.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.PropertyValue;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.config.BeanReference;
import org.tapmedia.beans.factory.support.AbstractBeanDefinitionReader;
import org.tapmedia.beans.factory.support.BeanDefinitionReader;
import org.tapmedia.beans.factory.support.BeanDefinitionRegistry;
import org.tapmedia.core.io.Resource;
import org.tapmedia.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

	public static final String BEAN_ELEMENT = "bean";

	public static final String PROPERTY_ELEMENT = "property";

	public static final String ID_ATTRIBUTE = "id";

	public static final String NAME_ATTRIBUTE = "name";

	public static final String CLASS_ATTRIBUTE = "class";

	public static final String VALUE_ATTRIBUTE = "value";

	public static final String REF_ATTRIBUTE = "ref";

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		super(registry);
	}

	@Override
	public void loadBeanDefinitions(Resource resource) throws BeansException {
		try (InputStream inputStream = resource.getInputStream()) {
			doLoadBeanDefinitions(inputStream);
		}
		catch (Exception ex) {
			throw new BeansException("IOException parsing XML document from " + resource, ex);
		}
	}

	@Override
	public void loadBeanDefinitions(String location) throws BeansException {
		ResourceLoader resourceLoader = getResourceLoader();
		Resource resource = resourceLoader.getResource(location);
		loadBeanDefinitions(resource);
	}

	protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
		Document document = XmlUtil.readXML(inputStream);
		Element root = document.getDocumentElement();
		NodeList childNodes = root.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			if (childNodes.item(i) instanceof Element) {
				if (BEAN_ELEMENT.equals(((Element) childNodes.item(i)).getNodeName())) {
					Element bean = (Element) childNodes.item(i);
					String id = bean.getAttribute(ID_ATTRIBUTE);
					String name = bean.getAttribute(NAME_ATTRIBUTE);
					String className = bean.getAttribute(CLASS_ATTRIBUTE);
					// className = className.substring(className.lastIndexOf(".") + 1);
					Class<?> clazz = Class.forName(className);

					String beanName = StrUtil.isNotEmpty(id) ? id : name;
					if (StrUtil.isEmpty(beanName)) {
						beanName = StrUtil.lowerFirst(clazz.getSimpleName());
					}

					BeanDefinition beanDefinition = new BeanDefinition(clazz);

					for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
						if (bean.getChildNodes().item(j) instanceof Element
								&& PROPERTY_ELEMENT.equals(((Element) bean.getChildNodes().item(j)).getNodeName())) {
							Element property = (Element) bean.getChildNodes().item(j);
							String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
							String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
							String refAttribute = property.getAttribute(REF_ATTRIBUTE);

							Object value = valueAttribute;

							if (StrUtil.isNotEmpty(refAttribute)) {
								value = new BeanReference(refAttribute);
							}

							PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
							beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
						}
					}

					getRegister().registerBeanDefinition(beanName, beanDefinition);
				}
			}
		}
	}

}
