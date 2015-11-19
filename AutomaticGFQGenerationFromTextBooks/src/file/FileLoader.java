/*
 * Author: Duong Quang Vu - K57CLC,UET,VNU
 */
package file;

/*
 * Create by DUONG QUANG VU - K57CLC - UET,VNU
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
	public static List<String> execute(String location){
		List<String> lines = new ArrayList<String>();
		FileReader fr;
		try {
			fr = new FileReader(location);
			BufferedReader br = new BufferedReader(fr);
			String line;
			try {
				while( (line = br.readLine()) != null ){
					lines.add(line);
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e.toString());
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return lines;
	}
}
