package eg.edu.alexu.csd.datastructure.hangman.cs22;

import java.util.*;

public class Hangman implements IHangman{
	private boolean[] guessed;
	private String word;
	private int maxWrongGuesses;
	private List<String> dictionary;
	
	public String guess(Character c) throws Exception{
		String newWord = "";
		boolean trueGuess = false;
		c = Character.toLowerCase(c);
		for(int i = 0; i < guessed.length; i++) {
			if(!Character.isLetter(this.word.charAt(i))) throw new Exception("The word is buggy.");
			if(guessed[i]) {
				newWord += this.word.charAt(i);
				if(c.equals(Character.toLowerCase(this.word.charAt(i)))) trueGuess =true;
			}
			else if(c.equals(Character.toLowerCase(this.word.charAt(i)))) {
				newWord += this.word.charAt(i);
				guessed[i] = true;
				trueGuess = true;
			}
			else if(Character.isWhitespace(this.word.charAt(i))) { //this only runs one time for each whitespace
				guessed[i] = true;
				newWord += " ";
			}
			else {
				newWord += '-';
			}
		}
		if(!trueGuess) {
			maxWrongGuesses--;
			if(maxWrongGuesses == 0) {
				throw new Exception("You lose.");
				//return null;??
				}
		}
		System.out.printf("Remaining guesses: %d\n", this.maxWrongGuesses);
		return newWord;
	}
	
	public String selectRandomSecretWord() {
		String word;
		Random r = new Random();
		word = dictionary.get(r.nextInt(dictionary.size()));
		this.guessed = new boolean[word.length()];
		this.setMaxWrongGuesses(25/word.length());
		return word;
	}
	
	public void setDictionary(String[] words) {
		dictionary = Arrays.asList(words);
		this.word = this.selectRandomSecretWord();
	}
	
	public void setMaxWrongGuesses(Integer max) {
		this.maxWrongGuesses = max;
	}
}
