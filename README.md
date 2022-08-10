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
key: K | Data member key of type K
value: V | Data member value of type V

MapEntry(K k, V v) | Constructor to initialize **key** to **K** and **value** to **V**
getKey(): K | Retrns the value of **key**
getValue(): V | Returns the value of value
toString(): String | Retusn **key** and **value** in a formatted string
