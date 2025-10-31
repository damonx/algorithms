package com.damonx.algorithms.hashtable;

import java.util.ArrayList;
import java.util.List;

public class HashTable
{
    private int size = 7;
    private Node[] dataMap;

    public HashTable()
    {
        dataMap = new Node[size];
    }

    class Node
    {
        private String key;
        private int value;
        private Node next;

        public Node(String key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }

    public void set(final String key, final int value)
    {
        final int index = hash(key);
        final Node newNode = new Node(key, value);
        if (dataMap[index] == null) {
            dataMap[index] = newNode;
        } else {
            Node temp = dataMap[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public int get(final String key)
    {
        final int index = hash(key);
        Node temp = dataMap[index];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return -1;
    }

    public List<String> keys()
    {
        List <String> allKeys = new ArrayList();
        for (int i = 0; i < dataMap.length; i++) {
            Node temp = dataMap[i];
            while (temp != null) {
                allKeys.add(temp.key);
                temp = temp.next;
            }
        }
        return allKeys;
    }



    public void printTable()
    {
        for (int i = 0; i < dataMap.length; i++)
        {
            System.out.println(i + ":");
            Node temp = dataMap[i];
            while (temp != null)
            {
                System.out.println("   {" + temp.key + "=" + temp.value + "}");
                temp = temp.next;
            }
        }
    }

    private int hash(String key) {
        int hash = 0;
        char[] keyChars = key.toCharArray();
        for (int i = 0; i < keyChars.length; i++) {
            int asciiValue = keyChars[i];
            hash += asciiValue * 23 * (i + 1); // add positional weight
        }
        return Math.abs(hash) % dataMap.length;
    }
}
