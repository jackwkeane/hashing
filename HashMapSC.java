/*
HashMapSC.java
@author Jack Keane
Due date: 5/5/2020
*/

import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapSC<K, V> {
	private int size;
	private double loadFactor;
	private LinkedList<MapEntry<K,V>>[] hashTable;

	public HashMapSC() {
		this(100, 0.9);
	}

	public HashMapSC(int c) {
		this(c, 0.9);
	}

	public HashMapSC(int c, double lf) {
		hashTable = new LinkedList[trimToPowerOf2(c)];
		loadFactor = lf;
	}


	
	/** 
	 * trimToPowerOf2() method
	 * @param c
	 * @return int
	 * O(log n)
	 */
	private int trimToPowerOf2(int c) {
		int capacity = 1;
		while (capacity < c)
			capacity = capacity << 1;
		return capacity;
	}

	
	/** 
	 * hash() Method
	 * @param hashCode
	 * @return int
	 * O(1)
	 */
	private int hash(int hashCode) {
		return hashCode & (hashTable.length - 1);
	}
	/**
	 * rehash() method
	 * will rehash the map
	 * O(n)
	 */
	private void rehash() {
		ArrayList<MapEntry> list = toList();
		hashTable = new LinkedList[hashTable.length << 1];
		size = 0;
		for (MapEntry<K,V> entry : list) {
			put(entry.getKey(), entry.getValue());
		}
	}

	
	/** 
	 * size() method
	 * @return int
	 * returns the size of the hashmap
	 * O(1)
	 */
	public int size() {
		return size;
	}
	/**
	 * clear() method
	 * O(n)
	 * Will clear the hashmap
	 */
	public void clear() {
		size = 0;
		for (int i = 0; i < hashTable.length; i++)
			if (hashTable[i] != null)
				hashTable[i].clear();
	}
	
	
	/** 
	 * isEmpty() method
	 * @return boolean
	 * O(1)
	 * returns true if map is empty
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	
	/** 
	 * conatainsKey() method
	 * @param key
	 * @return boolean
	 * O(1)
	 * Checks if the mpa contains a certain key
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
	 * Will serch through the map to get a certain value
	 */
	public V get(K key) {
		HashMaps.scIterations = 0;
		int bucketIndex = hash(key.hashCode());
		if (hashTable[bucketIndex] != null) {
			LinkedList<MapEntry<K, V>> bucket = hashTable[bucketIndex];
			for (MapEntry entry : bucket) {
				HashMaps.scIterations++;
				if (entry.getKey().equals(key))
					return (V) entry.getValue();
			}
		}
		return null;
	}

	
	/** 
	 * remove() method
	 * @param key
	 * O(1)
	 * Goes through map and removes a certain entry by its key
	 */
	public void remove(K key) {
		int bucketIndex = hash(key.hashCode());
		if (hashTable[bucketIndex] != null) { 
			LinkedList<MapEntry<K, V>> bucket = hashTable[bucketIndex];
			for (MapEntry entry : bucket) {
				if (entry.getKey().equals(key)) {
					bucket.remove(entry);
					size--;
					break;
				}
			}
		}
	}

	
	/** 
	 * put() method
	 * @param key
	 * @param value
	 * @return V
	 * O(1)
	 * Will put a value into the map
	 */
	public V put(K key, V value) {
		if (get(key) != null) {
			int bucketIndex = hash(key.hashCode());
			LinkedList<MapEntry<K, V>> bucket = hashTable[bucketIndex];
			for (MapEntry entry : bucket) {//O(1)
				if (entry.getKey().equals(key)) {
					V old = (V) entry.getValue();
					entry.value = value;
					return old;
				}
			}
		}

		if (size >= hashTable.length * loadFactor) {
			rehash();
		}
		int bucketIndex = hash(key.hashCode());
		if (hashTable[bucketIndex] == null) {
			hashTable[bucketIndex] = new LinkedList<MapEntry<K,V>>();
		}
		hashTable[bucketIndex].add(new MapEntry(key, value));
		size++;
		return value;
	}
	
	
	/** 
	 * toList() maethod
	 * @return ArrayList<MapEntry>
	 * O(n)
	 */
	public ArrayList<MapEntry> toList(){
		ArrayList<MapEntry> list = new ArrayList<>();
		for(int i=0; i< hashTable.length; i++) {
			if(hashTable[i]!= null) {
				LinkedList<MapEntry<K, V>> bucket = hashTable[i];
				for(MapEntry entry: bucket)
					list.add(entry);
			}
		} 
		return list;
	}

	
	/** 
	 * toString() method
	 * @return String
	 * O(1)
	 */
	public String toString() {
		String out = "[";
		for(int i=0; i<hashTable.length; i++) {
			if(hashTable[i]!=null) {
				for(MapEntry entry: hashTable[i]) {
					out += entry.toString();
				}
				out += "\n";
			}
		}
		out += "]"; return out;
	}
}
