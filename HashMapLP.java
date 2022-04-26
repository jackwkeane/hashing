/*
HashMapLP.java
@author Jack Keane
Due date: 5/5/2020
*/

import java.util.ArrayList;

public class HashMapLP<K, V> {
	private int size;
	private double loadFactor;
	private MapEntry<K, V>[] hashTable;
	private String[] keys;

	// Time Complexity: O(log n)
	public HashMapLP() {
		this(100, 0.9);
	}

	// Time Complexity: O(log n)
	public HashMapLP(int c) {
		this(c, 0.9);
	}

	// Time Complexity: O(log n)
	public HashMapLP(int c, double lf) {
		hashTable = new MapEntry[trimToPowerOf2(c)];
		loadFactor = lf;
	}

	/**
	 * trimToPowerOf2() Method
	 * @param c
	 * @return int
	 * Time Complexity: O(log n)1,2,4,8,16
	 */
	private int trimToPowerOf2(int c) {
		int capacity = 1;
		while (capacity < c)
			capacity = capacity << 1; // * 2
		return capacity;
	}

	/**
	 * hash() method
	 * @param hashCode
	 * @return int
	 * Time Complexity: O(1)
	 */
	private int hash(int hashCode) {
		return hashCode & (hashTable.length - 1);
	}

	/**
	 * rehash() method
	 * O(n)
	 */
	private void rehash() {
		ArrayList<MapEntry> list = toList();// O(n)
		hashTable = new MapEntry[hashTable.length << 1];
		size = 0;
		for (MapEntry<K, V> entry : list)
			put(entry.getKey(), entry.getValue());
	}

	/**
	 * size() method
	 * public interface
	 * @return int
	 * Time Complexity: O(1)
	 */
	public int size() {
		return size;
	}
 
	/**
	 * clear() method
	 * O(n)
	 */
	public void clear() {
		size = 0;
		for (int i = 0; i < hashTable.length; i++)
			hashTable[i] = null;// clear linked list at index i
	}

	/**
	 * isEmpty() method
	 * @return boolean
	 * O(1)
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * containsKey() method
	 * @param key
	 * @return boolean
	 * O(1)
	 * search for key in hash map, return true if found
	 */
	public boolean containsKey(K key) {
		if (get(key) != null)
			return true;
		return false;
	}

	/**
	 * get() method
	 * @param key
	 * @return V
	 * O(1)
	 * returns value of key if found, else null
	 */
	
	public V get(K key) {
		HashMaps.lpIterations = 0;
		int bucketIndex = hash(key.hashCode());
		while (hashTable[bucketIndex] != null) {
			HashMaps.lpIterations++;
			if (hashTable[bucketIndex].equals(key)) {
				return hashTable[bucketIndex].getValue();
			} else {
				bucketIndex = (bucketIndex + 1) % size();
			}
		}
		return null;
	}

	/**
	 * remove() method
	 * @param key
	 * / remove a key if found
	* O(1)
	 * 
	 */
	
	public void remove(K key) {
		int bucketIndex = hash(key.hashCode());
		while (hashTable[bucketIndex] != null) {
			if (hashTable[bucketIndex].equals(key)) {
				hashTable[bucketIndex] = null;
			} else {
				bucketIndex = (bucketIndex + 1) % size();
			}
		}
	}

	/**
	 * put() method
	 * @param key
	 * @param null
	 * @return V
	 * O(1)
	 * Will find an available index and put the value there
	 */
	
	public V put(K key, V value) { // key is in the hash map

		if (get(key) != null) {
			int bucketIndex = hash(key.hashCode());
			while (hashTable[bucketIndex] != null) {
				if (hashTable[bucketIndex].equals(key)) {
					V old = hashTable[bucketIndex].getValue();
					hashTable[bucketIndex] = new MapEntry(key, value);
					return old;
				} else {
					bucketIndex = (bucketIndex + 1) % size();
				}
			}
		}

		// key not in the hash map -> check load factor
		if (size >= hashTable.length * loadFactor)
			rehash();
		int bucketIndex = hash(key.hashCode());
		if (hashTable[bucketIndex] == null) {
			hashTable[bucketIndex] = new MapEntry<K, V>(key, value);
		} else {
			while (hashTable[bucketIndex] != null) {
				bucketIndex = (bucketIndex + 1) % size();
			}
			hashTable[bucketIndex + 1] = new MapEntry<K, V>(key, value);
		}
		size++;
		return value;
	}

	/**
	 * @return ArrayList<MapEntry>
	 * // return elements of the hash map as list
	 * O(n)
	 * toList() method
	 */
	
	public ArrayList<MapEntry> toList() {
		ArrayList<MapEntry> list = new ArrayList<>();
		for (int i = 0; i < hashTable.length; i++) {
			list.add(hashTable[i]);
		}
		return list;
	}

	/**
	 * @return String
	 * O(n)
	 * Formats it to a string
	 */
	//
	public String toString() {
		String out = "[";
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				out += hashTable[i].toString();
				out += "\n";
			}
		}
		out += "]";
		return out;
	}
}
