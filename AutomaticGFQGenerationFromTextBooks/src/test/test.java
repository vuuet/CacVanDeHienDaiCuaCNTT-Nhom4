/*
 * Author: Duong Quang Vu - K57CLC,UET,VNU
 */
package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import core.Lesson;
import core.SentSelection;
import file.FileLoader;
import file.PrepareInput;
import jvnsegmenter.WordSegmenting;
import jvnsensegmenter.JVnSenSegmenter;

public class test {

	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		// chuẩn bị dữ liệu, tách từ, tách câu
	/*	PrepareInput.fixEndLine(location);
		JVnSenSegmenter senSegmenter = new JVnSenSegmenter();
		senSegmenter.main(args);
		WordSegmenting ws = new WordSegmenting();
		ws.main(args);*/
		
		//tạo đối tượng lưu trữ văn bản đầu vào
		Lesson lesson = new Lesson();
		
		// các câu và title tương ứng
		Map<String, String> sent_title = lesson.getSent_title();
		// các câu và trọng số tương ứng
		Map<String, Double> sent_weight = lesson.getSent_weight();
		// Các mục và nội dung tương ứng
		Map<String, String> title_content = lesson.getTitle_content();
		
		int dem = 0;
		for( String sent : sent_weight.keySet() ){
			if( sent_weight.get(sent) > 3.0){
				System.out.println(sent+"==>"+sent_weight.get(sent));
				dem++;
			}
		}
		System.out.println("Total: "+dem);
		
		//Sort RelaProducts map
		/* Set<Entry<String, Integer>> set = sent_weight.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
		{
			public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
		    {
		    	return (o2.getValue()).compareTo( o1.getValue() );
		    }
		 } );
		        
		 for(Map.Entry<String, Integer> entry:list){

		 }*/
	}

}
