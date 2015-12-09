public class A3233GMWordHash {
	// The array holding all of the words.
	private A3233GMWordHolder[] array;
	private A3233GMWordHolder highestHolder;

	// Use the amount of characters in a file divided by the average word length + 2 (spaces)
	// to try and estimate the number of words that will appear in the text read.
	public A3233GMWordHash(int estimate) {
		array = new A3233GMWordHolder[estimate];
	}

	// Allows both the string and the count to be stored in the same reference.
	private class A3233GMWordHolder implements Comparable<String> {
		String word;
		int count;

		public A3233GMWordHolder(String word) {
			this.word = word;
			this.count = 0;
		}

		public String getWord() {
			return word;
		}

		public int compareTo(String s) {
			return word.compareTo(s);
		}


	}

	public static int hash(String key, int tableSize) {
		int hashVal = 0;

		for(int i = 0; i < key.length(); i++) {
			hashVal = 37 * hashVal + key.charAt(i);
		}

		hashVal %= tableSize;
		if(hashVal < 0) {
			hashVal += tableSize;
		}

		return hashVal;
	}

	public void add(String word) {
		A3233GMWordHolder holder = new A3233GMWordHolder(word);
		array[hash(holder.getWord(), array.length)] = holder;
	}

	public void set(String word) {
		
	}

	public A3233GMWordHolder get(String word) {
		int index = this.getIndex(word);
		if (index > 0) {
			return array[index];
		}

		return null;
	}

	public int getIndex(String word) {
		int index = hash(word, array.length);
		while (array[index] != null && array[index].compareTo(word) != 0) {
			index++;
		}

		return index;
	}
}
