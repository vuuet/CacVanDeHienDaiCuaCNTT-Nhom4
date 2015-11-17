package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GapSelection {
	public List<String> keys = new ArrayList<String>();
	public Map<String, Double> key_weight = new HashMap<String, Double>();
	
	// tạo ra các key ứng cử viên
	public void listKey(String TaggedSent){
		String tokens[] = TaggedSent.split(" ");
		String labels[] = {"/N","/Np","/Nc","/Nu","/Ny"};
		for( int i = 0; i < tokens.length; i ++ ){
			for( int j = 0; j < labels.length; j ++ ){
				if( tokens[i].endsWith(labels[j]) ){
					keys.add(tokens[i].substring(0, tokens[i].length()-labels[j].length()));
					break;
				}
			}
		}
	}
	
	// thuôc tính title có chứa key
	public void titleContainsKey(String title){
		for( int i = 0; i < keys.size(); i ++ ){
			if( title.contains(keys.get(i)) ){
				key_weight.put(keys.get(i), new Double(1));
			}
			else
				key_weight.put(keys.get(i), new Double(0));
		}
	}
	// thuôc tính số lần key xuất hiện trong mục tương ứng
	public void Count(String content){
		String tokens[] = content.split(" ");
		for( int i = 0; i < keys.size(); i ++ ){
			String key = keys.get(i);
			int count = tokens.length - content.replaceAll(key, "").length();
			double ratio = (double)count/tokens.length;
			key_weight.put(key, new Double(key_weight.get(key)+ratio));
		}
	}
	
	// thuộc tính độ cao trong cây cú pháp
	public void height(){
		
	}
	
	// tính toán trọng số
	public void computeWeight(String sentence, String title, String content){
		listKey(sentence);
		titleContainsKey(title);
		Count(content);
	}
	// trả về key có trọng số cao nhất
	public String bestKey(){
		return null;
	}
}
