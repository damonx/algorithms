package com.damonx.algorithms.hashtable;

public class HashTableMain
{
    public static void main(String[] args)
    {
        final HashTable hashTable = new HashTable();
        //hashTable.printTable();
        hashTable.set("nail", 100);
        hashTable.set("tile", 200);
        hashTable.set("lumber", 300);
        hashTable.set("screw", 400);
        hashTable.printTable();

        System.out.println("Find nail: " + hashTable.get("nail"));
        System.out.println("Find damon: " + hashTable.get("damon"));
        System.out.println("Find screw: " + hashTable.get("screw"));

        System.out.println("all keys: " + hashTable.keys());
     }
}
