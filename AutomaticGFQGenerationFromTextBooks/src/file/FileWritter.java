/*
 * Author: Duong Quang Vu - K57CLC,UET,VNU
 */
package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWritter {
	public static void execute(List<String> lines, String location) throws IOException {
		File file = new File(location);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(location);
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < lines.size(); i++) {
			bw.write(lines.get(i) + "\n");
		}
		bw.close();
	}
}
