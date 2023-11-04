package org.tapmedia.test.ioc;

import cn.hutool.core.io.IoUtil;
import org.junit.Test;
import org.tapmedia.core.io.DefaultResourceLoader;
import org.tapmedia.core.io.FileSystemResource;
import org.tapmedia.core.io.Resource;
import org.tapmedia.core.io.UrlResource;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceAndResourceLoaderTest {

	@Test
	public void testResourceLoader() throws Exception {
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

		// Test classpath resource
		Resource resource = resourceLoader.getResource("classpath:hello.txt");
		InputStream inputStream = resource.getInputStream();
		String content = IoUtil.readUtf8(inputStream);
		System.out.println(content);
		assertThat(content).isEqualTo("hello world");

		// Test filesystem resource
		resource = resourceLoader.getResource("src/test/java/org/tapmedia/test/resources/hello.txt");
		assertThat(resource instanceof FileSystemResource).isTrue();
		inputStream = resource.getInputStream();
		assertThat(content).isEqualTo("hello world");

		// Test URL resource
		resource = resourceLoader.getResource("https://www.uit.edu.vn/");
		assertThat(resource instanceof UrlResource).isTrue();
		inputStream = resource.getInputStream();
		content = IoUtil.readUtf8(inputStream);
	}

}
