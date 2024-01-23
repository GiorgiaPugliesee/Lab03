package it.polito.tdp.spellchecker.model;

import java.util.Objects;

public class RichWord {
	
	private String word;
	private boolean isWrong;
	
	public RichWord(String word, boolean isWrong) {
		this.word = word;
		this.isWrong = isWrong;
	}
	
	public String getWord() {
		return word;
	}
	
	public boolean isWrong() {
		return isWrong;
	}
	
	@Override
	public String toString() {
		return "RichWord [word=" + word + ", isWrong=" + isWrong + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(isWrong, word);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichWord other = (RichWord) obj;
		return isWrong == other.isWrong && Objects.equals(word, other.word);
	}
	


}
