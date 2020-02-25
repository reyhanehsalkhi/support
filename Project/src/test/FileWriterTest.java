package test;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.writer.Writer;
import hash.folder.Hashing;
import junit.framework.Assert;

public class FileWriterTest {
	Hashing hashing = new Hashing();

	@Test
	public void testNotEquals_Symmetric() throws IOException {

		File path1 = new File("/tmp/temp/sub1/sub2");
		File path2 = new File("/tmp/temp1/sub1/sub2");
		String text1 = "this is some text";
		String text2 = "this is some other text";
		Writer writer = new Writer();
		try {
			if (!path1.isDirectory()) {
				if (path1.mkdirs())
					writer.writeTo(path1 + "/file.txt", text1);

			} else {
				writer.writeTo(path1 + "/file.txt", text1);
			}

			if (!path2.isDirectory()) {
				if (path2.mkdirs()) {
					writer.writeTo(path2 + "/file.txt", text2);
				}
			} else {
				writer.writeTo(path2 + "/file.txt", text2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertEquals(hashing.compareHashforPath("/tmp/temp1", "/tmp/temp/"), false);
	}

	@Test
	public void testEquals_Symmetric() throws IOException {

		File path1 = new File("/tmp/temp/sub1/sub2");
		File path2 = new File("/tmp/temp1/sub1/sub2");
		String text = "this is some text";
		Writer writer = new Writer();
		try {
			if (!path1.isDirectory()) {
				if (path1.mkdirs())
					writer.writeTo(path1 + "/file.txt", text);
			} else {
				writer.writeTo(path1 + "/file.txt", text);
			}

			if (!path2.isDirectory()) {
				if (path2.mkdirs())
					writer.writeTo(path2 + "/file.txt", text);

			} else {
				writer.writeTo(path2 + "/file.txt", text);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertEquals(hashing.compareHashforPath("/tmp/temp1", "/tmp/temp/"), true);
	}
}
