package org.tapmedia.beans.factory;


import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BeanFactoryTest {
    @Test
    public void testGetBean() throws Exception {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("helloService", new HelloService());
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");

        assertThat(helloService).isNotNull();
        assert(helloService.sayHello()).equals("hello");
    }

    class HelloService{
        public String sayHello(){
            System.out.println("hello");
            return "hello";
        }
    }
}
