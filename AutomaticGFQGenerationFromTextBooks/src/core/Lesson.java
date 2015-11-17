package core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import file.FileLoader;

public class Lesson {
	Map<String, String> sent_title = new HashMap<String, String>();
	Map<String, Double> sent_weight = new HashMap<String, Double>();
	Map<String, String> title_content = new HashMap<String, String>();
	
	public Lesson(){
		setSent_title();
		setSent_weight();
		setTitle_content();
	}
	
	public Map<String, String> getSent_title() {
		return sent_title;
	}
	
	public void setSent_title() {
		
		List<String> lines = FileLoader.execute("input/input.txt.sent.wseg");
		String title = "";
		for( int i = 0; i < lines.size(); i ++ ){
			if( lines.get(i).contains("title") ){
				title = lines.get(i).split(":",2)[1];
			}
			else{
				while( !lines.get(i).contains("title") ){
					//System.out.println(lines.get(i));
					sent_title.put(lines.get(i).replace("first==>", ""), title);
					i++;
					if(i >= lines.size())
						break;
				}
				i--;
			}
		}
	}
	
	public Map<String, Double> getSent_weight() {
		return sent_weight;
	}
	
	public void setSent_weight() {	
		SentSelection ss = new SentSelection();
		for( String sent : sent_title.keySet() ){
			double weight = ss.computeWeight(sent_title.get(sent), sent);
			sent_weight.put(sent, weight);
			//System.out.println(sent+"==>"+sent_title.get(sent));
		}
	}
	
	public Map<String, String> getTitle_content() {
		return title_content;
	}
	
	public void setTitle_content() {
		List<String> lines = FileLoader.execute("input/input.txt.sent.wseg");
		String title = "";
		for( int i = 0; i < lines.size(); i ++ ){
			String content = "";
			if( lines.get(i).contains("title:") ){
				title = lines.get(i).split(":",2)[1];
			}
			else{
				while( !lines.get(i).contains("title:")){
					if( !lines.get(i).equals("/n") )
						content += lines.get(i);
					i++;
					if(i >= lines.size())
						break;
				}
				title_content.put(title, content);
				//System.out.println(title);
				//System.out.println(content);
				i--;
			}
			
		}
	}

	
}
