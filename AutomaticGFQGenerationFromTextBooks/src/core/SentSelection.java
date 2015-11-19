/*
 * Author: Duong Quang Vu - K57CLC,UET,VNU
 */
package core;

import java.util.ArrayList;
import java.util.List;

import jvntagger.MaxentTagger;
import jvntagger.POSTagger;

public class SentSelection {
	public static String modelDir = "models/maxent";
	public static POSTagger tagger = new MaxentTagger(modelDir);
	public String title;
	public String sentence;
	public String TaggedSent;
	public String TaggedTitle;
	
	public double computeWeight(String title, String sentence){
		this.title = title;
		this.sentence = sentence;
		TaggedTitle = tagger.tagging(title);
		TaggedSent = tagger.tagging(sentence);
		//System.out.println(TaggedTitle);
		//System.out.println(TaggedSent);
		double f = (double)firstSent(sentence);
		double sim = (double)countOfWordInTitle()/sentence.split(" ").length;
		double abb = (double)containAbbreviation();
		double Super = (double)containSuperlativeDegree();
		double discon =(double)DiscourseConnectives();
		double l = (double)numberOfWord()/20;
		double nouns = numberOfNoun();
		double pronouns = numberOfPronoun();
		return (f+sim+abb+Super+discon+l+nouns+pronouns);
	}
	
	// 
	public int firstSent(String sentence){
		if( sentence.startsWith("first==>") ){
			return 1;
		}
		return 0;
	}
	
	//
	public int countOfWordInTitle(){
		int countOfWord = 0;
		//System.out.println(outputSent);
		String tokens[] = TaggedSent.split(" ");
		String labels[] = {"/N","/Np","/Nc","/Nu","/Ny","/A"};
		List<String> NandA = new ArrayList<String>();
		for( int i = 0; i < tokens.length; i ++ ){
			for( int j = 0; j < labels.length; j ++ ){
				if( tokens[i].endsWith(labels[j]) ){
					NandA.add(tokens[i].substring(0, tokens[i].length()-labels[j].length()));
					break;
				}
			}
		}
		
		for( int i = 0; i < NandA.size(); i ++ ){
			//System.out.println(NandA.get(i));
			if( TaggedTitle.contains(NandA.get(i)) ){
				countOfWord++;
			}
		}
		//System.out.println(countOfWord);
		return countOfWord;
	}
	
	//
	public int containAbbreviation(){
		String tokens[] = TaggedSent.split(" ");
		String labels[] = {"/Ny","/Y"};
		for( int i = 0; i < tokens.length; i ++ ){
			if( tokens[i].endsWith("/Ny") || tokens[i].endsWith("/Y") )
				return 1;
		}
		return 0;
	}
	
	//
	public int containSuperlativeDegree(){
		String tokens[] = sentence.split(" ");
		for( int i = 0; i < tokens.length; i ++ ){
			if( tokens[i].contains("_nhất")|| tokens[i].contains("rất") )
				return 1;
		}
		return 0;
	}
	
	//
	public int DiscourseConnectives(){
		String discourses[] = {"Thế_là", "Thế_nên", "Vậy_nên", "Tóm_lại", "Như_vậy"};
		for( int i = 0; i < discourses.length; i ++ ){
			if( sentence.startsWith(discourses[i]) ){
				return 1;
			}
		}
		
		return 0;
	}
	
	//
	public int numberOfWord(){
		String tokens[] = sentence.split(" ");
		return tokens.length;
	}
	
	//
	public double numberOfNoun(){
		int numberOfNoun = 0;
		String tokens[] = TaggedSent.split(" ");
		String labels[] = {"/N","/Np","/Nc","/Nu","/Ny"};
		for( int i = 0; i < tokens.length; i ++ ){
			for( int j = 0; j < labels.length; j ++ ){
				if( tokens[i].endsWith(labels[j]) ){
					numberOfNoun++;
					break;
				}
			}
		}
		return (double)numberOfNoun/tokens.length;
	}
	
	//
	public double numberOfPronoun(){
		int numberOfPronoun = 0;
		String tokens[] = TaggedSent.split(" ");
		for( int i = 0; i < tokens.length; i ++ ){
			if( tokens[i].endsWith("/P") ){
				numberOfPronoun++;
			}
		}
		return (double)numberOfPronoun/tokens.length;
	}
}
