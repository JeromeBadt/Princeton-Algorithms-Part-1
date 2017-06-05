import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * This class implements a randomized queue using a resizing array.
 * 
 * @param <Item> the type of elements held in this deque
 * 
 * @author Jerome Badt
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	/**
	 * The array buffer into which the elements of the RandomizedQueue are
	 * stored. The capacity of the RandomizedQueue is the length of this array
	 * buffer.
	 */
	private Item[] elementData;
	/**
	 * The size of the RandomizedQueue (the number of elements it contains).
	 */
	private int size;

	/**
	 * Initializes the resizing array
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		elementData = (Item[]) new Object[1];
	}

	/**
	 * Returns {@code true} if this queue contains no elements.
	 * 
	 * @return {@code true} if this queue contains no elements
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of elements in this queue.
	 *
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Enqueues the specified element.
	 *
	 * @param item element to be appended to this queue
	 * @throws NullPointerException if item is {@code null}
	 */
	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}

		ensureCapacity(size + 1);

		elementData[size++] = item;
	}

	/**
	 * Removes and returns a random element from the queue.
	 *
	 * @return the removed element
	 * @throws NoSuchElementException if the queue is empty
	 */
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		ensureCapacity(size - 1);

		int index = StdRandom.uniform(size);
		Item item = elementData[index];
		elementData[index] = elementData[--size];
		elementData[size] = null;

		return item;
	}

	/**
	 * Returns a random element from the queue (without removing it).
	 *
	 * @return a random element from the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return elementData[StdRandom.uniform(size)];
	}

	/**
	 * Ensures the size of the backing array is always within 25% to 100% of the
	 * required capacity
	 * 
	 * @param minCapacity minimum required capacity for the elements in the
	 *            queue
	 */
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = elementData.length;

		if (minCapacity > oldCapacity) {
			resize(oldCapacity * 2);
		} else if (minCapacity > 0 && oldCapacity / 4 >= minCapacity) {
			resize(oldCapacity / 2);
		}
	}

	/**
	 * Resizes the backing array to the specified capacity
	 * 
	 * @param capacity new size
	 */
	private void resize(int capacity) {
		elementData = Arrays.copyOf(elementData, capacity);
	}

	/**
	 * Returns an iterator over the elements in this queue in random sequence.
	 * <p>
	 * The returned iterator is not <i>fail-fast</i>.
	 *
	 * @return an iterator over the elements in this queue in random sequence
	 */
	public Iterator<Item> iterator() {
		return new RandomziedQueueIterator();
	}

	/**
	 * An iterator over a randomized queue.
	 *
	 * @param <Item> the type of elements returned by this iterator
	 */
	private class RandomziedQueueIterator implements Iterator<Item> {
		/**
		 * This array holds the shuffled indices to iterate over
		 */
		private int[] rndIndices = new int[size];
		/**
		 * Index of next element to return
		 */
		private int cursor;

		/**
		 * Initializes the iterator
		 */
		public RandomziedQueueIterator() {
			for (int j = 0; j < size; j++) {
				rndIndices[j] = j;
			}
			StdRandom.shuffle(rndIndices);
		}

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			return elementData[rndIndices[cursor++]];
		}
	}
}
