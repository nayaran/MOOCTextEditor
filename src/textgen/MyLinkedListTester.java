/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try {
			emptyList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.remove(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		
		try {
			shortList.add(null);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
		
		}
		
		// Test adding to an empty list
		emptyList.add(8);
		assertEquals("AddEnd: check element added to an empty list ", (Integer)8, emptyList.get(0));
		
		// Add a number at the end & verify the same by retrieving the number at the last index
		int size = list1.size();
		list1.add(99);
        assertEquals("AddEnd: check element added at the end is correct ", (Integer)99, list1.get(size));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		// Verify size of an empty list
		assertEquals("Size: check size of an empty list ", 0, emptyList.size());
		// Remove a number and verify that the size has decremented
		list1.remove(0);
        assertEquals("Size: check size decrements after removing an element ", 2, list1.size());
		// Add a number and verify that the size has incremented
		list1.add(9);
        assertEquals("Size: check size increments after adding an element ", 3, list1.size());	
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		
		try {
			emptyList.add(null);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
		
		}
		// Test adding an element in empty list at index -1

		try {
			emptyList.add(-1, 8);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// Test setting an element at index > size
		
		try {
			emptyList.add(8);
			emptyList.add(2, 7);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// Test adding an element at n
		int size = longerList.size();
		longerList.add(4, 9);
		assertEquals("AddAtIndex: check added element ",(Integer)9, longerList.get(4));
		assertEquals("AddAtIndex: check element next to the added one ",(Integer)4, longerList.get(5));
        assertEquals("AddAtIndex: check size increments after adding an element ", size+1, longerList.size());

	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		// Test setting an element in empty list at index -1
		try {
			emptyList.set(-1, 8);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// Test setting an element at index > size
		
		try {
			emptyList.add(8);
			emptyList.set(2, 7);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// Test setting an element at negative index
		try {
			emptyList.add(8);
			emptyList.add(-1, 7);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// Test setting an element at n
		int size = longerList.size();
		longerList.set(4, 9);
		assertEquals("AddAtIndex: check set element ",(Integer)9, longerList.get(4));
		assertEquals("AddAtIndex: check element next to the set one ",(Integer)5, longerList.get(5));
        assertEquals("AddAtIndex: check size doesn't increment after setting an element ", size, longerList.size());

	}

	
	// TODO: Optionally add more test methods.
	
}
