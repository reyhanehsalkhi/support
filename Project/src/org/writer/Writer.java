package org.writer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Writer {
	OutputStreamWriter writer;

	public void writeTo(String path, String content) throws IOException {
		File f = new File(path);
		if (f.exists()) {
			f.delete();
		}
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8");
		writer.write(content);
		writer.close();
	}

}
