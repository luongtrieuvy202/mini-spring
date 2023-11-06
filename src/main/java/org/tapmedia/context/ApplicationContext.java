package org.tapmedia.context;

import org.tapmedia.beans.factory.HierarchicalBeanFactory;
import org.tapmedia.beans.factory.ListableBeanFactory;
import org.tapmedia.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {

}
