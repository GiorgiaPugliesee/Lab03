package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	List<String> dictionary = new LinkedList<>();
	
	public void loadDictionary(String language) {
		
		try { 
			FileReader fr = new FileReader(language + ".txt"); 
			BufferedReader br = new BufferedReader(fr); 
			String word; 
			while ((word = br.readLine()) != null) { 
			// Aggiungere parola alla struttura dati 
				dictionary.add(word);
			} 
			br.close(); 
		} catch (IOException e){ 
		 System.out.println("Errore nella lettura del file"); 
		} 

	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		List<RichWord> wordsChecked = new LinkedList<>();
		
		for(String s : inputTextList) {
			if(dictionary.contains(s)) {
				RichWord word = new RichWord(s, false);
				wordsChecked.add(word);
			} else {
				RichWord word = new RichWord(s, true);
				wordsChecked.add(word);
			}
		}
		
		return wordsChecked;
		
	}

}
