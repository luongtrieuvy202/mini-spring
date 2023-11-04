package org.tapmedia.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface Resource {

	InputStream getInputStream() throws IOException;

}
