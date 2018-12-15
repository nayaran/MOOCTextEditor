package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		TrieNode node = root;
		System.out.println("\nRequested to add - " + word);
		boolean wordAdded = false;
		boolean sizeIncremented = false;

		System.out.println("\nAttempting to add - " + word);
		System.out.println("Tree before - ");
		
		//this.printTree();
		
		// Loop over each character of the word
		// to find correct node in the trie for insertion
		for (int index = 0; index < word.length(); index++){
			char character = word.toLowerCase().charAt(index);
			System.out.println("\nAt char - " + character);;
			System.out.println("node.getText() before- " + node.getText());
			
			// Continue the search as long as the characters in the word
			// exist in the trie
			if (node.getValidNextCharacters().contains(character)) {
				node = node.getChild(character);
				continue;				
			}
			// Reached the leaf, add character to the leaf node
			node.insert(character);
			node = node.getChild(character);
			wordAdded = true;
			System.out.println("Added - " + character);
			System.out.println("node.getText() after- " + node.getText());

		}

		// Mark it as word & increment size only if its a new word
		if (!node.endsWord()) {
			node.setEndsWord(true);
			System.out.println("Incrementing size");
			size++;
			sizeIncremented = true;
		}
		
		System.out.println(word + ", added - " + wordAdded + ", sizeIncremented - " + sizeIncremented + ", size - " + size());
		System.out.println("Tree after - ");

		return wordAdded;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
		TrieNode node = root;
		System.out.println("\nSearching for - " + s);
		if (s.isEmpty())
			return false;
		for (int index = 0; index < s.length(); index++){
			char character = s.toLowerCase().charAt(index);
			System.out.println("At char - " + character);;
			System.out.println("node.getText() - " + node.getText());
			System.out.println("node.getValidNextCharacters() - " + node.getValidNextCharacters());
			if (node.getValidNextCharacters().contains(character))
				node = node.getChild(character);
		}
		return node.endsWord() && node.getText().equals(s.toLowerCase());
	}
	
	/** Returns the node in the trie which contains the string s
	 */
	private TrieNode getWordNode(String s) 
	{
		TrieNode node = root;
		System.out.println("\nSearching for - " + s);

		for (int index = 0; index < s.length(); index++){
	
			char character = s.toLowerCase().charAt(index);
			System.out.println("At char - " + character);;
			System.out.println("node.getText() - " + node.getText());
			System.out.println("node.getValidNextCharacters() - " + node.getValidNextCharacters());
			if (node.getValidNextCharacters().contains(character))
				node = node.getChild(character);
		}
		
		if (node.getText().equals(s.toLowerCase()))
			return node;

		return null;
	}
	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 	// Initialize list of completions 
    	 	ArrayList<String> completions = new ArrayList<String>();
 	 	
    	 	// Get to the stem
    	 	TrieNode wordNode = getWordNode(prefix);
     	if (wordNode == null || numCompletions == 0)
     		return completions;

    	 	// BFS on wordNode
    	 	LinkedList<TrieNode> queue = new LinkedList<TrieNode>();

    	 	queue.add(wordNode);
    	 	
   	
    	 	while (!queue.isEmpty() && numCompletions > 0) {
    	 		// Pop the item and examine if it needs to be added to completions
    	 		TrieNode nodeToExamine = queue.pop();
    	 		if (nodeToExamine.endsWord()) {
    	 			completions.add(nodeToExamine.getText());
    	 			numCompletions--;
    	 		}
    	 			
    	 		// Add all children of the node to queue
    	 		for (Character character : nodeToExamine.getValidNextCharacters()) {
        	 		queue.add(nodeToExamine.getChild(character));
        	 	}
    	 	}    	 		
    	 	return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.print(curr.getText());
 		/*
 		if(curr.endsWord())
 			System.out.println(" - word");
 		else
 			System.out.println(" - not a word");
 		*/

 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	/*
 	public static void main(String[] args) {
		AutoCompleteDictionaryTrie smallDict = new AutoCompleteDictionaryTrie();

		smallDict.addWord("downhill");
		//smallDict.addWord("downhill");
		//smallDict.addWord("downhille");
		smallDict.printTree();
		//System.out.println("size - " + smallDict.size());
		//System.out.println("is downhil a word- " + smallDict.isWord("downhil"));
		//System.out.println("is downhill a word- " + smallDict.isWord("downhill"));
		System.out.println("is downhille a word- " + smallDict.isWord("downhille"));
 	}
 	*/
 	

	
}