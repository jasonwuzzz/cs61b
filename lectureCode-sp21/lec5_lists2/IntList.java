package lec5_lists2;

public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if (rest == null) {
			return 1;
		}
		return 1 + this.rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		IntList p = this;
		int totalSize = 0;
		while (p != null) {
			totalSize += 1;
			p = p.rest;
		}
		return totalSize;
	}

	/** Returns the ith item of this IntList. */
	public int get(int i) {
		if (i == 0) {
			return first;
		}
		return rest.get(i - 1);
	}

	/** List 2 study guide: C Level Question 1 */
	public void addFrist(int x) {
		IntList tmp = new IntList(first, rest);
		first = x;
		rest = tmp;
	}

	/** List 2 study guide: A Level Question 1
	 *
	 * If 2 numbers in a row are the same,
	 * we add them together and make one large node.
	 * For example:
	 * 1 → 1 → 2 → 3 becomes 2 → 2 → 3 which becomes 4 → 3
	 */
	public void addAdjacent() {
		if (size() >= 2) {
			if (first == rest.first) {
				first = first * 2;
				rest = rest.rest;
				this.addAdjacent();
			} else {
				rest.addAdjacent();
			}
		}
	}

	/** List 2 study guide: A Level Question 2
	 *
	 * Modify the Intlist class so that every time you add a value you
	 * “square” the IntList.
	 * Additionally, you are provided the constraint that you can only
	 * access the size() function one time during the entire process of adding a node.
	 */
	public void squareList(int x) {
		IntList front = this;
		while (front.rest != null) {
			IntList tmp = front.rest;
			int squared = front.first *  front.first;
			front.rest = new IntList(squared, tmp);
			front = front.rest.rest;
		}
		front.rest = IntList.of(front.first *  front.first, x);
	}

	/** Method to return a string representation of an IntList */
	public String toString() {
		if (rest == null) {
			// Converts an Integer to a String!
			return String.valueOf(first);
		} else {
			return first + " -> " + rest.toString();
		}
	}

	/**
	 * Method to create an IntList from an argument list.
	 * You don't have to understand this code. We have it here
	 * because it's convenient with testing. It's used like this:
	 *
	 * IntList myList = IntList.of(1, 2, 3, 4, 5);
	 * will create an IntList 1 -> 2 -> 3 -> 4 -> 5 -> null.
	 *
	 * You can pass in any number of arguments to IntList.of and it will work:
	 * IntList mySmallerList = IntList.of(1, 4, 9);
	 */
	public static IntList of(int ...argList) {
		if (argList.length == 0)
			return null;
		int[] restList = new int[argList.length - 1];
		System.arraycopy(argList, 1, restList, 0, argList.length - 1);
		return new IntList(argList[0], IntList.of(restList));
	}
}