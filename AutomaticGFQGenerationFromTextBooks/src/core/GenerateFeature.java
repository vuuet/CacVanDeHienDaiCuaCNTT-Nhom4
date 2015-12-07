package core;

public class GenerateFeature {

	public static float comparisonFeature(String selectedWord, String selectedSentence, String content, String currentWord, String currentSentence) {

		content = content.toLowerCase();
		
		SelectedWord selectedWordObj = getSelectedWord(selectedWord, selectedSentence);
		SelectedWord currentWordObj = getSelectedWord(currentWord, currentSentence);
		if(currentWordObj == null || selectedWordObj == null)
			return 0;
		//if (currentWordObj.getTag().contains("/N"))
			return computeWeightContext(selectedWordObj, currentWordObj) + computeWeightSentence(SentSelection.tagger.tagging(selectedSentence), SentSelection.tagger.tagging(currentSentence)) + computeWeightFrequencies(selectedWord, currentWord, content);
		//return 0;
	}
	
	private static int computeWeightContext(SelectedWord selectedWord, SelectedWord currentWord){
		int weight = 0;
		if(selectedWord.getIndex() == currentWord.getIndex())
			weight++;
		if(selectedWord.getPrevious_tag_1().equals(currentWord.getPrevious_tag_1()))
			weight++;
		if(selectedWord.getPrevious_tag_2().equals(currentWord.getNext_tag_2()))
			weight++;
		if(selectedWord.getNext_tag_1().equals(currentWord.getNext_tag_1()))
			weight++;
		if(selectedWord.getNext_tag_2().equals(currentWord.getNext_tag_2()))
			weight++;
		
		return (selectedWord.getName().equals(currentWord.getName())) ? 0 : weight;
		
	}

	private static SelectedWord getSelectedWord(String selectedWord, String selectedSentence) {
		String senlectedSentenceWithTags = SentSelection.tagger.tagging(selectedSentence);

		String[] tokenWords = senlectedSentenceWithTags.split(" ");

		for (int i = 0; i < tokenWords.length; i++) {

			String[] splitTag = tokenWords[i].split("/");
			
			if (splitTag[0].equals(selectedWord)) {
				
				String previous_tag_1 = (i > 0) ? tokenWords[i - 1].split("/")[1] : "";
				String previous_tag_2 = (i > 1) ? tokenWords[i - 2].split("/")[1] : "";
				String next_tag_1 = (i < tokenWords.length - 1) ? tokenWords[i + 1].split("/")[1] : "";
				String next_tag_2 = (i < tokenWords.length - 2) ? tokenWords[i + 2].split("/")[1] : "";

				return new SelectedWord(selectedWord, i, previous_tag_1, previous_tag_2, next_tag_1, next_tag_2,splitTag[1]);
			}
		}
		return null;
	}
	
	private static int computeWeightSentence(String selectedSentenceWithTag, String currentSentenceWithTag){
		
		int numOfWordInCommon = 0;
		
		String [] tokenWorkSelected = selectedSentenceWithTag.split(" ");
		String [] tokenWorkCurrent = currentSentenceWithTag.split(" ");
		
		for(String selected : tokenWorkSelected)
			for(String current : tokenWorkCurrent){
				if(selected.split("/")[0].equals(current.split("/")[0]) && (selected.endsWith("/A") || selected.contains("/N")))
					numOfWordInCommon += 1.5;
			}
		return numOfWordInCommon;
		//return (2 * numOfWordInCommon) / (tokenWorkCurrent.length + tokenWorkSelected.length);
	}
	
	private static float computeWeightFrequencies(String selectedWord, String currentWord, String content){
		
		int numOfAppearSelectedWord = 0;
		int numOfAppearCurrentWord = 0;
		
		String [] tokenContent = content.toLowerCase().split(" ");
		
		for(String token : tokenContent){
			if(token.equals(selectedWord))
				numOfAppearSelectedWord++;
			if(token.equals(currentWord))
				numOfAppearCurrentWord++;
		}
		if(Math.abs(numOfAppearSelectedWord - numOfAppearCurrentWord) == 0)
			return numOfAppearSelectedWord + numOfAppearCurrentWord;
		return ((float)(numOfAppearSelectedWord + numOfAppearCurrentWord)) / (numOfAppearSelectedWord - numOfAppearCurrentWord);
		
	}
}
