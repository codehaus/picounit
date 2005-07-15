package picounit;

import java.io.File;

public interface Environment {
	File getRootTempDirectory();
	File createTempFile(String fileName);
	File createTempDirectory(String directoryName);
}