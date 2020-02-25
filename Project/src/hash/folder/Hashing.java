package hash.folder;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class Hashing {
	public String hashDirectory(String directoryPath, boolean includeHiddenFiles) throws IOException {
		File directory = new File(directoryPath);

		if (!directory.isDirectory()) {
			throw new IllegalArgumentException("Not a directory");
		}

		Vector<FileInputStream> fileStreams = new Vector<>();
		collectFiles(directory, fileStreams, includeHiddenFiles);

		try (SequenceInputStream sequenceInputStream = new SequenceInputStream(fileStreams.elements())) {
			return DigestUtils.md5Hex(sequenceInputStream);
		}
	}

	private void collectFiles(File directory, List<FileInputStream> fileInputStreams, boolean includeHiddenFiles)
			throws IOException {
		File[] files = directory.listFiles();

		if (files != null) {
			Arrays.sort(files, Comparator.comparing(File::getName));

			for (File file : files) {
				if (includeHiddenFiles || !Files.isHidden(file.toPath())) {
					if (file.isDirectory()) {
						collectFiles(file, fileInputStreams, includeHiddenFiles);
					} else {
						fileInputStreams.add(new FileInputStream(file));
					}
				}
			}
		}
	}

	public boolean compareHashforPath(String source, String target) throws IOException{

		if(this.hashDirectory(source, true).equals(this.hashDirectory(target, true)))
				return true;
		else
			return false;
	}

}