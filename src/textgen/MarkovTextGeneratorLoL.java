package textgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) {
		// TODO: Implement this method
		if (wordList.size() > 0)
			return;
		
		List<String> sourceTextArray = Arrays.asList(sourceText.split(" "));
		//getTokens(sourceText, "[a-zA-Z]+");
		if (sourceTextArray.size() == 0)
			return;
		starter = sourceTextArray.get(0);
		System.out.println("-----");
		System.out.println(sourceTextArray);
		System.out.println("-----");

		for (int i = 0; i < sourceTextArray.size(); i++) {
			System.out.println("\nAt - " + sourceTextArray.get(i));
			ListNode wordNode = getWordNode(sourceTextArray.get(i));
			if (wordNode == null) {
				System.out.println("Word doesn't exist, adding to wordList");
				wordNode = new ListNode(sourceTextArray.get(i));
				wordList.add(wordNode);
			}
			System.out.println("updating nextWord array");
			if (i < sourceTextArray.size() - 1)
				wordNode.addNextWord(sourceTextArray.get(i + 1));
			else
				wordNode.addNextWord(sourceTextArray.get(0));
			System.out.println("Training data after update - \n" + this);

		}

	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		// TODO: Implement this method
		if (wordList.size() == 0 || numWords == 0)
			return "";
		
		String currentWord = starter;
		String generatedText = currentWord;

		int wordsGenerated = 1;
		while (wordsGenerated < numWords) {
			ListNode currentWordNode = getWordNode(currentWord);
			String randomNextWord = currentWordNode.getRandomNextWord(rnGenerator);
			generatedText += " " + randomNextWord;
			currentWord = randomNextWord;
			wordsGenerated++;
		}
		return generatedText;
	}

	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		train(sourceText);
	}

	// TODO: Add any private helper methods you need here.
	ListNode getWordNode(String wordToSearch) {
		// System.out.println("Word to search - " + wordToSearch);
		// System.out.println("wordList - " + wordList);
		for (ListNode wordNode : wordList) {
			if (wordNode.getWord().equals(wordToSearch))
				return wordNode;
		}
		return null;
	}

	private List<String> getTokens(String text, String pattern) {
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}

	/**
	 * This is a minimal set of tests. Note that it can be difficult to test
	 * methods/classes with randomized behavior.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random());
		// String textString = "Hello. Hello there. This is a test. Hello there. Hello
		// Bob. Test again.";
		String textString = "hi there hi Leo";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);

		System.out.println("\nGenerated words - " + gen.generateText(4));
		/*
		 * String textString2 = "You say yes, I say no, "+
		 * "You say stop, and I say go, go, go, "+
		 * "Oh no. You say goodbye and I say hello, hello, hello, "+
		 * "I don't know why you say goodbye, I say hello, hello, hello, "+
		 * "I don't know why you say goodbye, I say hello. "+
		 * "I say high, you say low, "+ "You say why, and I say I don't know. "+
		 * "Oh no. "+ "You say goodbye and I say hello, hello, hello. "+
		 * "I don't know why you say goodbye, I say hello, hello, hello, "+
		 * "I don't know why you say goodbye, I say hello. "+
		 * "Why, why, why, why, why, why, "+ "Do you say goodbye. "+ "Oh no. "+
		 * "You say goodbye and I say hello, hello, hello. "+
		 * "I don't know why you say goodbye, I say hello, hello, hello, "+
		 * "I don't know why you say goodbye, I say hello. "+ "You say yes, I say no, "+
		 * "You say stop and I say go, go, go. "+ "Oh, oh no. "+
		 * "You say goodbye and I say hello, hello, hello. "+
		 * "I don't know why you say goodbye, I say hello, hello, hello, "+
		 * "I don't know why you say goodbye, I say hello, hello, hello, "+
		 * "I don't know why you say goodbye, I say hello, hello, hello,";
		 * System.out.println(textString2); gen.retrain(textString2);
		 * System.out.println(gen); //System.out.println(gen.generateText(20));
		 */
	}

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator) {
		// TODO: Implement this method
		// The random number generator should be passed from
		// the MarkovTextGeneratorLoL class
		System.out.print("\n\ngenerating random word from - ");
		System.out.print(this);
		int randomIndex = generator.nextInt(nextWords.size());
		System.out.print("randomIndex = " + randomIndex + " " + "generatedRandomWord = " + nextWords.get(randomIndex));
		return nextWords.get(randomIndex);

	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	// hi Leo hi Leo hi there hi there hi Leo hi Leo hi there hi there hi there hi
	// Leo hi
	// hi Leo hi Leo hi there hi there hi Leo hi Leo hi there hi there hi there hi
	// Leo hi
	// hi Leo hi Leo hi there hi there hi Leo hi Leo hi there hi there hi there hi
	// Leo hi

}
