package com.damonx.algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph via adjacency list.
 */
public class Graph
{
    private Map<String, List<String>> adjList = new HashMap<>();

    public boolean addVertex(final String vertex)
    {
        if (adjList.get(vertex) == null) {
            adjList.put(vertex, new ArrayList<>());
            return true;
        }
        return false;
    }

    public void printGraph()
    {
        System.out.println(adjList);
    }
}
