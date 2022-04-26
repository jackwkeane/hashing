/*
HashMapQP.java
@author Jack Keane
Due date: 5/5/2020
*/

import java.util.ArrayList;

public class HashMapQP<K, V> {
	private int size;
	private double loadFactor;
	private MapEntry<K,V>[] hashTable;
	private String[] keys;


	public HashMapQP() {//O(log n)
		this(100, 0.9);
	}
	
	public HashMapQP(int c) {//O(log n)
		this(c, 0.9);
	}

	public HashMapQP(int c, double lf) {//O(log n)
		hashTable = new MapEntry[trimToPowerOf2(c)];
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
	 * hashMethod
	 * @param hashCode
	 * @return int
	 */
	//O(1)
	private int hash(int hashCode) {
		return hashCode & (hashTable.length - 1);
		
	}
	/**
	 * rehash() method
	 * O(n)
	 * 
	 */
	private void rehash() {
		ArrayList<MapEntry<K,V>> list = toList();//O(n)
		hashTable = new MapEntry[hashTable.length << 1];
		size = 0;
		for (MapEntry<K,V> entry : list)
			put(entry.getKey(), entry.getValue());
	}

	
	/** 
	 * size() method
	 * @return int
	 * O(1)
	 */
	//
	public int size() {
		return size;
	}
	/**
	 * clear() method
	 * O(N)
	 * Clears the hashmap
	 */
	public void clear() {
		size = 0;
		for (int i = 0; i < hashTable.length; i++)
				hashTable[i] =null;//clear linked list at index i
	}
	
	/** 
	 * isEmpty() method
	 * @return boolean
	 * O(1)
	 * returns true if the hashmap is empty
	 */
	
	public boolean isEmpty() {
		return (size == 0);
	}

	
	/** 
	 * containsKey() method
	 * @param key
	 * @return boolean
	 * search for key in  hash map, return true if found
	//O(1)
	 * 
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
	 * returns value of key if found, otherwise null
	 * O(1)
	 * 
	 */
	
	public V get(K key) {
		HashMaps.qpIterations = 0;
		int x = 0;
		int originial = 0;
		int bucketIndex = hash(key.hashCode());
		originial=bucketIndex;
		while(hashTable[bucketIndex] != null) {
			HashMaps.qpIterations++;
			if(hashTable[bucketIndex].equals(key)) {
				return hashTable[bucketIndex].getValue();
			}
			else {
				x = x + 1;
				bucketIndex = (originial+(x * x)) % size();
			}
		}
		return null;
	}

	
	/** 
	 * remove() method
	 * remove
	 * @param key
	 * //remove a key if found
	//O(1)
	 * 
	 */
	
	public void remove(K key) {
		int bucketIndex = hash(key.hashCode());
		int originial;
		originial=bucketIndex;
		int x = 0;
		while(hashTable[bucketIndex]!= null) {
			if(hashTable[bucketIndex].equals(key)) {
				hashTable[bucketIndex]= null;
			}
			else {
				x = x + 1;
				bucketIndex = (originial + (x * x)) % size();
			}
		}	
	}
	

    
	/** 
	 * put() method
	 * @param key
	 * @param value
	 * @return V
	 * //adds new key or modifies  existing key
	 * O(1)
	 * 
	 */
	
	public V put(K key, V value) { 
		int x = 0;
		int originial;
		if (get(key) != null) {
			int bucketIndex = hash(key.hashCode());
			originial = bucketIndex;
			while(hashTable[bucketIndex] != null) {
				if(hashTable[bucketIndex].equals(key)) {
					V old = hashTable[bucketIndex].getValue();
					hashTable[bucketIndex]= new MapEntry(key,value);
					return old;
				}
				else {
					x = x + 1;
					bucketIndex = (originial + (x * x)) % size();
				}
			}				
		}
		int y = 0;
		//key not in the hash map -> check load factor
		if (size >= hashTable.length * loadFactor)
			rehash();
		int bucketIndex = hash(key.hashCode());
		originial = bucketIndex;
		
		if(hashTable[bucketIndex] == null) {
			hashTable[bucketIndex]= new MapEntry<K,V>(key,value);
		}
		else {
			while(hashTable[bucketIndex] != null) {
				y = y + 1;
				bucketIndex = (originial + (y * y)) % size();
			}
		hashTable[originial + (y * y)] = new MapEntry<K,V>(key,value);
		}
		size++;
		return value;
	}
	
	/** 
	 * toList() method
	 * @return ArrayList<MapEntry<K, V>>
	 * //return elements of the hash map as list
	 * O(n)
	 * 
	 */
	
	public ArrayList<MapEntry<K,V>> toList(){
		ArrayList<MapEntry<K,V>> list = new ArrayList<>();
		for(int i=0; i< hashTable.length; i++) {
			if(hashTable[i]!= null) {
				list.add(hashTable[i]);
			}
		} 
		return list;
	}
	
	/** 
	 * toString() method
	 * @return String
	 * eturns elements of hash map as string
	 * O(1)
	 * 
	 */
	
	public String toString() {
		String out = "[";
		for(int i=0; i<hashTable.length; i++) {
			if(hashTable[i]!=null) {
				out += hashTable[i].toString();
				out += "\n";
			}
		}
		out += "]"; return out;
	}
}
