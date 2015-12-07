package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jvntagger.MaxentTagger;
import jvntagger.POSTagger;

import java.util.Set;

public class GapSelection {
	public static String modelDir = "models/maxent";
	public static POSTagger tagger = new MaxentTagger(modelDir);
	//public static String Sent = "Một_số kiểm thử_viên đôi_khi nghĩ kiểm thử là tìm sai_sót và khi kiểm_tra chương_trình chỉ chú_trọng vào việc tìm các vấn_đề của sản_phẩm hơn là kiểm_tra mức_độ đúng_đắn của chương_trình .";
	//public static String Title = "Tổng_quan về kiểm thử .";
	//public static String Content = "first==>Mục đích của kiểm thử là đánh_giá chất_lượng hoặc tính chấp_nhận được của sản_phẩm . Các chương còn_lại sẽ mô_tả bức_tranh này chi_tiết hơn .";
	public static List<String> keys = new ArrayList<String>();
	public static Map<String, Double> key_weight = new HashMap<String, Double>();
	
	// tạo ra các key ứng cử viên
	public static void listKey(String TaggedSent){
		String tokens[] = TaggedSent.split(" ");
		String labels[] = {"/N","/Np","/Nc","/Nu","/Ny"};
		for( int i = 0; i < tokens.length; i ++ ){
			for( int j = 0; j < labels.length; j ++ ){
				if( tokens[i].endsWith(labels[j]) ){
					keys.add(tokens[i].substring(0, tokens[i].length()-labels[j].length()));
					//System.out.println(tokens[i].substring(0, tokens[i].length()-labels[j].length()));
					break;
				}
			}
		}
	}
	
	// thuôc tính title có chứa key
	public static void titleContainsKey(String title){
		for( int i = 0; i < keys.size(); i ++ ){
			if( title.contains(keys.get(i)) ){
				key_weight.put(keys.get(i), new Double(1));
			}
			else
				key_weight.put(keys.get(i), new Double(0));
		}
	}
	// thuôc tính số lần key xuất hiện trong nội dung mục tương ứng
	public static void Count(String content){
		String tokens[] = content.split(" ");
		for( int i = 0; i < keys.size(); i ++ ){
			String key = keys.get(i);
			if (key.contains("(") || key.contains(")"))
				continue;
			int count = tokens.length - content.replaceAll(key, "").split(" ").length;
			double ratio = (double)count/tokens.length;
			key_weight.put(key, new Double(key_weight.get(key)+ratio));
		}
	}
	
	// thuộc tính độ cao trong cây cú pháp
	public static void height(){
		
	}
	
	// Độ dài key
	public static void len(){
		for( int i = 0; i < keys.size(); i ++ ){
			String key = keys.get(i);
			if (key.contains("(") || key.contains(")"))
				continue;
			if (key.split("_").length > 1){
				key_weight.put(key, new Double(key_weight.get(key)+1));
			}
		}
	}
	
	// tính toán trọng số
	public static void computeWeight(String sentence, String title, String content){
		listKey(tagger.tagging(sentence));
		titleContainsKey(tagger.tagging(title));
		Count(tagger.tagging(content));
		len();
	}
	// trả về key có trọng số cao nhất
	public static String bestKey(){
		//Sort key_weight map
		Set<Entry<String, Double>> set = key_weight.entrySet();
		List<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>()
		{
			public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 )
		    {
		    	return (o2.getValue()).compareTo( o1.getValue() );
		    }
		 } );
		String bestKey = list.get(0).getKey();
		keys.clear();
		key_weight.clear();
		return bestKey;
	}
}
