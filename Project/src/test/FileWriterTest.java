package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.writer.Writer;

import hash.folder.Hashing;
import junit.framework.Assert;

public class FileWriterTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	FileWriter writer;
	Hashing hashing = new Hashing();
	File file;

	/*
	 * @Test public void testEquals_Symmetric() throws IOException { if (new
	 * File("/tmp/temp/sub3").mkdirs()) { writer.writeTo("/tmp/temp/sub3/file.txt",
	 * "this is some random text"); if (new File("/tmp/temp1/sub3").mkdirs()) {
	 * writer.writeTo("/tmp/temp1/sub3/file.txt", "this is some random text"); } }
	 * Assert.assertEquals(hashing.compareHashforPath("/tmp/temp1",
	 * "/tmp/temp/"),true);
	 * 
	 * }
	 */

	@Test
	public void testNotEquals_Symmetric() throws IOException {

		File f1 = new File("/tmp/temp/sub3");
		File f2 = new File("/tmp/temp1/sub3");
		String text1 = "this is some text";
		String text2 = "this is some other text";
		Writer writer = new Writer();
		try {
			if (!f1.isDirectory()) {
				if (f1.mkdirs()) {
					writer.writeTo("/tmp/temp/sub3/file.txt", text1);
				}
			} else {
				writer.writeTo("/tmp/temp/sub3/file.txt", text1);
			}

			if (!f2.isDirectory()) {
				if (f2.mkdirs()) {
					writer.writeTo("/tmp/temp1/sub3/file.txt", text2);
				}
			} else {
				writer.writeTo("/tmp/temp1/sub3/file.txt", text2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertEquals(hashing.compareHashforPath("/tmp/temp1", "/tmp/temp/"), false);
	}

	@Test
	public void testEquals_Symmetric() throws IOException {

		File path1 = new File("/tmp/temp/sub3");
		File path2 = new File("/tmp/temp1/sub3");
		String text1 = "this is some text";
		String text2 = "this is some text";
		Writer writer = new Writer();
		try {
			if (!path1.isDirectory()) {
				if (path1.mkdirs())
					writer.writeTo(path1 + "/file.txt", text1);
			} else {
				writer.writeTo(path1 + "/file.txt", text1);
			}

			if (!path2.isDirectory()) {
				if (path2.mkdirs())
					writer.writeTo(path2 + "/file.txt", text2);

			} else {
				writer.writeTo(path2 + "/file.txt", text2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertEquals(hashing.compareHashforPath("/tmp/temp1", "/tmp/temp/"), true);
	}
}
