package picounit;


import java.io.File;

public interface FileVerify {

	void sameType(File expected, File actual);

	void sameContents(File expected, File actual);

}