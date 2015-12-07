/*
 * Author: Duong Quang Vu - K57CLC,UET,VNU
 */
package test;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import core.GapSelection;
import core.GenerateFeature;
import core.Lesson;
import jvntextpro.util.StopWord;

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
		
		String content = "";
		
		for(String sent : sent_title.keySet()){
			content += sent_title.get(sent) + "|";
		}
		for( String sent : sent_weight.keySet() ){
			if( sent_weight.get(sent) > 3.5){
				GapSelection.computeWeight(sent, sent_title.get(sent), title_content.get(sent_title.get(sent)));
				
				String selectedWord = GapSelection.bestKey();
				String selectedSentence = sent;
				System.out.println(sent.replaceFirst(selectedWord, "_________"));
				System.out.println("=>"+selectedWord);
				String [] sentenceContents = content.split("\\|");
				HashMap<String,Float> maps = new HashMap<String,Float>();
				for(String currentSentence : sentenceContents){
					
					if(currentSentence.equals(sent) || currentSentence.length() < 2)
						continue;
					
					String [] tokens = currentSentence.split(" ");
					for(String currentWord : tokens){
						if(currentSentence.length() < 2)
							continue;
						if(StopWord.stopWords.contains(currentWord.toLowerCase()))
							continue;
						if(currentWord.split("_").length < 2)
							continue;
						if(currentWord.equals(selectedWord))
							continue;
						if(currentWord.startsWith("first==>"))
							continue;
						float w = GenerateFeature.comparisonFeature(selectedWord, selectedSentence, content, currentWord, currentSentence);
						//System.out.println(currentWord + " : " + w + " : " + selectedWord);
						maps.put(currentWord, w);
					}
					
				}
				
				TreeMap<String, Float> sortedMap = test.SortByValue(maps);
				int k = 0;
				for(String key : sortedMap.keySet()){
					if(k > 2)
						break;
					System.out.println(key);
					k++;
				}
				System.out.println("------------------------------------------------------------");
				dem++;
			}
		}
		System.out.println("Total: "+dem);
	}
	
	public static TreeMap<String, Float> SortByValue(HashMap<String, Float> maps) {
		ValueComparator vc = new ValueComparator(maps);
		TreeMap<String, Float> sortedMap = new TreeMap<String, Float>(vc);
		sortedMap.putAll(maps);
		return sortedMap;
	}
	

}
