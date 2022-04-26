/*
HashMapa.java
@author Jack Keane
Due date: 5/5/2020
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class HashMaps {
	public static int scIterations = 0;
	public static int lpIterations = 0;
	public static int qpIterations = 0;
	
	public static void main(String[] args) {
		//Instantiating the different hashmaps
		HashMapSC<String, String> hashSc = new HashMapSC<>();
		HashMapLP<String, String> hashLp = new HashMapLP<>();
		HashMapQP<String, String> hashQp = new HashMapQP<>();
		ArrayList<MapEntry<String, String>> mapEntry = new ArrayList<>();
		/**
		 *Reading the file and adding each of the terms to MapEntry arraylist
		 */
		File file = new File("dictionary.txt");
		Scanner readFile = null;
		try {
			readFile = new Scanner(file);
		}catch(FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}
		
		while(readFile.hasNextLine()) {
			String line = readFile.nextLine();
			String[] tokens = line.split("\\|");
			MapEntry<String, String> mp = new MapEntry<>(tokens[0], tokens[1]);
			mapEntry.add(mp);
		}
		readFile.close();
		//Setting the capacity of each hashmap
		hashSc = new HashMapSC<>(500000);
		hashLp = new HashMapLP<>(500000);
		hashQp = new HashMapQP<>(500000);
		/**
		 * Putting each of the values into each different hashmap
		 */
		for(int i = 0; i < mapEntry.size(); i++) {
			MapEntry<String, String> mp = mapEntry.get(i);
			hashSc.put(mp.getKey(), mp.getValue());
			hashLp.put(mp.getKey(), mp.getValue());
			hashQp.put(mp.getKey(), mp.getValue());
		}
		
		Random rand = new Random();
		double scTotal = 0;
		double lpTotal = 0;
		double qpTotal = 0;

		double scGTotal =0;
		double lpGTotal =0;
		double qpGTotal = 0;
		
		System.out.println("Word" + "\t\t\t\t\t" +  "Separate Chaining  Linear Probing\tQuadratic Probing");
		System.out.println();
		/**
		 * Searching for random values and finding the amount of iterations.
		 */
		for(int i = 0; i < 100; i++) {
			int index = rand.nextInt(mapEntry.size());
			MapEntry<String, String> mp = mapEntry.get(index);
			
			hashSc.containsKey(mp.getKey());
			scTotal = scIterations;
			scGTotal += scTotal;
			hashLp.containsKey(mp.getKey());
			lpTotal = lpIterations;
			lpGTotal += scTotal;
			hashQp.containsKey(mp.getKey());
			qpTotal = qpIterations;
			qpGTotal += qpTotal;
			System.out.printf(mp.getKey().toString() + "\t\t" + scIterations + "\t" + lpIterations + "\t" + qpIterations);
			System.out.println();
		}
		//Printing the average number of iterations
		System.out.println();
		System.out.printf("%-40s %-10.2f\t\t%-10.2f\t%-10.2f\n", "Average", scGTotal/100, lpGTotal/100, qpGTotal/100);	

	}
}
