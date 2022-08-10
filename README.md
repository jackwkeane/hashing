# Grade: 98.0/100.0
Completed Spring of 2021 as a part of Lehigh University's CSE 017: Programming and Data Structures. 

## Learning Outcomes
1. Implement the class **HashMapSC** class using separate chaining for the resolution of
the collisions.
2. Implement the class **HashMapLP** to use linear probing to resolve collisions.
3. Implement the class **HashMapQP** to use quadratic probing to resolve collisions.
4. Compare the performance of the three different implementations of the hash map.


## Project Outline
1. Create the generic class MapEntry as shown in the UML diagram below:

MapEntry<K, V> | Generic class with two types
------------ | -------------
key: K | Data member **key** of type **K**
value: V | Data member **value** of type **V**
MapEntry(K k, V v) | Constructor to initialize **key** to **K** and **value** to **V**
getKey(): K | Retrns the value of **key**
getValue(): V | Returns the value of **value**
toString(): String | Retusn **key** and **value** in a formatted string
2. Use the implementation of the class HashMap seen in class to create a new class named HashMapSC (Hash Map with Separate Chaining)
3. Use the implementation of the class HashMapSC and modify it to create a new class named HashMapLP (Hash Map with Linear Probing) that handles collisions using linear probing.
4. Use the implementation of the class HashMapLP and modify it to create a new class named HashMapQP (Hash Map with Quadratic Probing) that handles collisions using quadratic probing.
5. The three classes should use the same class MapEntry described in the UML diagram above (with package access for all its members). Remove the inner private class MapEntry from the three classes.
6. Test your three versions of the class HashMap in a test program HashMaps as follows:
  a. In the main method, Instantiate the three classes for types <String, String>. Insert the data from the file dictionary.txt in the three hash maps.
  b. Perform 100 random search operations on the three data structures. Display the number of iterations for each word searched in the three data structures. Finally, display the average number of iterations for the three data structures.
