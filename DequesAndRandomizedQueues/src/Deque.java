import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The Deque class implements a double ended queue using a linked list.
 * 
 * @param <Item> the type of elements held in this deque
 * 
 * @author Jerome Badt
 */
public class Deque<Item> implements Iterable<Item> {
	/**
	 * Pointer to first node.
	 * <p>
	 * Invariant: (first == null && last == null) || (first.prev == null &&
	 * first.item != null)
	 */
	private Node<Item> first;
	/**
	 * Pointer to last node.
	 * <p>
	 * Invariant: (first == null && last == null) || (last.next == null &&
	 * last.item != null)
	 */
	private Node<Item> last;
	/**
	 * The size of the Deque (the number of elements it contains).
	 */
	private int size;

	/**
	 * Returns {@code true} if this deque contains no elements.
	 * 
	 * @return {@code true} if this deque contains no elements
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of elements in this deque.
	 *
	 * @return the number of elements in this deque
	 */
	public int size() {
		return size;
	}

	/**
	 * Inserts the specified element at the beginning of this deque.
	 *
	 * @param item the element to add
	 */
	public void addFirst(Item item) {
		final Node<Item> newNode = new Node<Item>(item, first, null);
		if (first == null) {
			last = newNode;
		} else {
			first.prev = newNode;
		}
		first = newNode;
		size++;
	}

	/**
	 * Appends the specified element to the end of this deque.
	 *
	 * @param item the element to add
	 */
	public void addLast(Item item) {
		final Node<Item> newNode = new Node<Item>(item, null, last);
		if (last == null) {
			first = newNode;
		} else {
			last.next = newNode;
		}
		last = newNode;
		size++;
	}

	/**
	 * Removes and returns the first element from this deque.
	 *
	 * @return the first element from this deque
	 * @throws NoSuchElementException if this deque is empty
	 */
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Item item = first.item;
		if (first == last) {
			first = last = null;
		} else {
			first = first.next;
			first.prev = null;
		}
		size--;

		return item;
	}

	/**
	 * Removes and returns the last element from this deque.
	 *
	 * @return the last element from this deque
	 * @throws NoSuchElementException if this deque is empty
	 */
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Item item = last.item;
		if (first == last) {
			first = last = null;
		} else {
			last = last.prev;
			last.next = null;
		}
		size--;

		return item;
	}

	/**
	 * Returns an iterator over the elements in this deque in proper sequence.
	 * <p>
	 * The returned iterator is not <i>fail-fast</i>.
	 *
	 * @return an iterator over the elements in this deque in proper sequence
	 */
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	/**
	 * An iterator over a randomized queue.
	 *
	 * @param <Item> the type of elements returned by this iterator
	 */
	private class DequeIterator implements Iterator<Item> {
		/**
		 * Pointer to the next node
		 */
		private Node<Item> next = first;

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			Item item = next.item;
			next = next.next;
			return item;
		}
	}

	/**
	 * Static nested class representing a node in the deque. Nodes are double
	 * linked.
	 * 
	 * @param <Item> the type of element carried by this node
	 */
	private static class Node<Item> {
		Item item;
		Node<Item> next;
		Node<Item> prev;

		Node(Item item, Node<Item> next, Node<Item> prev) {
			if (item == null) {
				throw new NullPointerException();
			}
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}
}
