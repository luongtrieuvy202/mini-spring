package org.tapmedia.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;

public class AbstractResource implements Resource {

	@Override
	public boolean exists() {
		try {
			return getFile().exists();
		}
		catch (IOException ex) {
			try {
				InputStream inputStream = getInputStream();
				inputStream.close();
				return true;
			}
			catch (Throwable isEx) {
				return false;
			}
		}
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return null;
	}

	@Override
	public File getFile() throws IOException {
		throw new FileNotFoundException("resource cannot be resolved to absolute file path");
	}

	@Override
	public URL getURL() throws IOException {
		throw new FileNotFoundException("resource cannot be resolve to URL");
	}

}
