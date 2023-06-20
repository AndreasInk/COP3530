package maps;

import java.io.*;
import java.util.ArrayList;

public class WordCounter {
	TreeMapBST<String,Integer> T = new TreeMapBST<>();
		
	public WordCounter(String fileName) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(fileName));
	
		String line;
				
    	while ((line = input.readLine()) != null) {  
			 StringBuilder s = new StringBuilder();
			 for(int i = 0; i < line.length(); i++) {		 
				 if( Character.isLetter( line.charAt(i) ) )
					 s.append( Character.toLowerCase( line.charAt(i) ) );
				 else {
					 if( s.length() >= 1) 
						 add(s.toString());
					 s.setLength(0);
				 }
			 }
		 }
		input.close();
	}

	public int getuniqueWordsCount() {
		return T.size();
	}
	
	private void add(String w) {
		Integer currentCount = T.get(w);
		if( currentCount == null ) T.put(w, 1);
		else T.updateValue(w,currentCount+1);
	}

	public Integer countOccurrenceOf(String w) {
		Integer count = T.get(w);
		return ( count == null )? 0 : count;
	}

	public void getWordFrequenciesSortedLexicographically() {
		ArrayList<String> words = new ArrayList<>();
		T.getAllKeys(words);

		ArrayList<Integer> counts = new ArrayList<>();
		T.getAllValues(counts);

		if( words.size() != counts.size() )
			throw new IllegalArgumentException("Something is wrong!");

		for(int i = 0; i < words.size(); i++)
			System.out.println("<" + words.get(i) + ", " + counts.get(i) + ">");
	}
}