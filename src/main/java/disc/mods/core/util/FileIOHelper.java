package disc.mods.core.util;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class FileIOHelper {
	public enum FileType {
		Json(".json", "{}"), Text(".txt", "");

		private final String CleanText;
		private final String Extension;

		FileType(String Extention, String CleanText) {
			this.CleanText = CleanText;
			this.Extension = Extention;
		}

		void openClean(File file) {
			if (FilenameUtils.getExtension(file.getAbsolutePath()) != this.Extension)
				throw new InvalidPathException(file.getAbsolutePath(),
						"Invalid File Extension for extention: " + this.Extension);
			WriteFile(file, this.CleanText);
		}
	}

	public static String ReadFile(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			return new String(data, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public static void WriteFile(File file, String Content) {
		boolean flag = true;
		if (!file.exists()) {
			flag = CreateFile(file);
		}
		if (flag) {
			try (FileWriter writer = new FileWriter(file)) {
				writer.write(Content);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean CreateFile(File file) {
		try {
			file.getParentFile().mkdirs();
			return file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static File PrepareFile(String pathName, FileType type) {
		File file = new File(pathName);
		if (!file.exists()) {
			CreateFile(file);
			type.openClean(file);
		}
		return file;
	}
}
