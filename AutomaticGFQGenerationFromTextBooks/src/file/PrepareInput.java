/*
 * Author: Duong Quang Vu - K57CLC,UET,VNU
 */
package file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jvnsegmenter.WordSegmenting;

public class PrepareInput {
	public static void fixEndLine(String location) throws IOException{
		String location2 = "input/input.txt";
		List<String> output = new ArrayList<String>();
		List<String> lines = FileLoader.execute(location);
		for( int i = 0; i < lines.size(); i ++ ){
			String line = lines.get(i);
			System.out.println(lines.get(i));
			if( lines.get(i).startsWith("title:") || lines.get(i).startsWith("sub_title:")){
				output.add(line+".");
				//System.out.println(lines.get(i));
			}
			else {
				String newline = "first==>";
				while (!line.endsWith(".") && line.contains("title:")){
					newline += line + " ";
					i++;
					line = lines.get(i);
				}
				newline += lines.get(i);
				output.add(newline);
				//System.out.println(newline);
			}
		}
		System.out.println(lines.size());
		FileWritter.execute(output, location2);
	}
	
}
