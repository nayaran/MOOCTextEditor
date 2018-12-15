package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		LLNode<E> header = new LLNode<E>();
		head = header;
		
		LLNode<E> trailer = new LLNode<E>();
		tail = trailer;
		
		head.next = tail;
		tail.prev = head;
		
		size = 0;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		else {
			int count = 0;
			LLNode<E> currentNode = head.next;
			while (currentNode != tail) {
				if (count == index)
					return currentNode.data;
				else {
					count += 1;
					currentNode = currentNode.next;
				}
			}
		}
		return null;
	}

	/** Get the LLNode at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public LLNode<E> getNodeAtIndex(int index) 
	{
		// TODO: Implement this method.
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		else {
			int count = 0;
			LLNode<E> currentNode = head.next;
			while (currentNode != tail) {
				if (count == index)
					return currentNode;
				else {
					count += 1;
					currentNode = currentNode.next;
				}
			}
		}
		return null;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if (element == null)
			throw new NullPointerException();
		
		LLNode<E> newNode = new LLNode<E>(element);
		
		newNode.next = tail;
		newNode.prev = tail.prev;
		
		tail.prev.next = newNode;
		tail.prev = newNode;
		size ++;
		return true;			
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method.
		if (element == null)
			throw new NullPointerException();

		LLNode<E> nodeToDisplace = getNodeAtIndex(index);
		
		// Adding an element at index Zero on an empty list
		// essentially means replace tail
		if (nodeToDisplace == null)
			nodeToDisplace = tail;

		LLNode<E> newNode = new LLNode<E>(element);
		newNode.next = nodeToDisplace;
		newNode.prev = nodeToDisplace.prev;
		
		nodeToDisplace.prev.next = newNode;
		nodeToDisplace.prev = newNode;
		size ++;

	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E> nodeToRemove = getNodeAtIndex(index);
		nodeToRemove.prev.next = nodeToRemove.next;
		nodeToRemove.next.prev = nodeToRemove.prev;
		
		size--;
		return nodeToRemove.data;

	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (element == null)
			throw new NullPointerException();
		LLNode<E> nodeToReplace = getNodeAtIndex(index);
		nodeToReplace.data = element;
		return element;
	}   
	
    public String toString() {
		LLNode<E> currentNode = head.next;
		String stringRepresentation = "";
		if (size > 0) {
			stringRepresentation = currentNode.data.toString();
			currentNode = currentNode.next;
			while (currentNode != tail) {
				stringRepresentation += " -> " + currentNode.data.toString();
				currentNode = currentNode.next;
			}
		}
		return stringRepresentation;
    }
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode() {
		this.data = null;
		this.prev = null;
		this.next = null;		
	}
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
}
