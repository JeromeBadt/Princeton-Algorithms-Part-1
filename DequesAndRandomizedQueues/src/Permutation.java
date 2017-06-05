import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Client that prints a random permutation of size {@code k} from a String
 * sequence of size {@code n}.
 * 
 * @author Jerome Badt
 */
public class Permutation {
	/**
	 * Reads a sequence of {@code n} Strings from standard input and prints a
	 * random permutation of size k.
	 * 
	 * @param args size of permutation
	 */
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		String[] sequence = StdIn.readAllStrings();
		int n = sequence.length;
		int[] rndIndices = new int[n];

		for (int i = 0; i < n; i++) {
			rndIndices[i] = i;
		}
		StdRandom.shuffle(rndIndices);

		Deque<String> permutation = new Deque<String>();
		for (int i = 0; i < k; i++) {
			permutation.addFirst(sequence[rndIndices[i]]);
		}

		for (String s : permutation) {
			System.out.println(s);
		}
	}
}
