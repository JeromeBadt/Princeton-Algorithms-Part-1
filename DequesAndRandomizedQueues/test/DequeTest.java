import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DequeTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void basicTest() {
		Deque<String> deque = new Deque<String>();

		assertTrue(deque.isEmpty());
		assertEquals(deque.size(), 0);

		deque.addFirst("Lorem");
		deque.addLast("Ipsum");

		assertFalse(deque.isEmpty());
		assertEquals(deque.size(), 2);

		Iterator<String> iter = deque.iterator();

		assertEquals(iter.hasNext(), true);

		assertEquals(iter.next(), "Lorem");
		assertEquals(iter.next(), "Ipsum");

		assertEquals(deque.removeFirst(), "Lorem");
		assertEquals(deque.removeLast(), "Ipsum");

		assertTrue(deque.isEmpty());
		assertEquals(deque.size(), 0);
	}

	@Test
	public void orderedTest() {
		Deque<Integer> deque = new Deque<Integer>();

		final int n = 100;
		for (int i = 0; i < n; i++) {
			deque.addFirst(i);
		}

		int[] out = new int[n];
		for (int i = 0; i < n; i++) {
			out[i] = deque.removeLast();
		}

		Assert.assertArrayEquals(out, IntStream.range(0, 100).toArray());
	}

	@Test
	public void noSuchElementTest1() throws NoSuchElementException {
		Deque<Integer> deque = new Deque<Integer>();

		thrown.expect(NoSuchElementException.class);
		deque.removeFirst();
	}

	@Test
	public void noSuchElementTest2() throws NoSuchElementException {
		Deque<Integer> deque = new Deque<Integer>();

		thrown.expect(NoSuchElementException.class);
		deque.removeLast();
	}

	@Test
	public void noSuchElementIterTest() throws NoSuchElementException {
		Deque<Integer> deque = new Deque<Integer>();

		thrown.expect(NoSuchElementException.class);
		deque.iterator().next();
	}

	@Test
	public void nullPointerTest1() throws NullPointerException {
		Deque<Integer> deque = new Deque<Integer>();

		thrown.expect(NullPointerException.class);
		deque.addFirst(null);
	}

	@Test
	public void nullPointerTest2() throws NullPointerException {
		Deque<Integer> deque = new Deque<Integer>();

		thrown.expect(NullPointerException.class);
		deque.addLast(null);
	}
}
