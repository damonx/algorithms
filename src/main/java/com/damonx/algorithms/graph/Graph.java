package com.damonx.algorithms.graph;

import org.apache.commons.lang3.ObjectUtils;

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

    public boolean addEdge(final String vertex1, final String vertex2)
    {
        if (ObjectUtils.allNotNull(adjList.get(vertex1), adjList.get(vertex2))) {
            adjList.get(vertex1).add(vertex2);
            adjList.get(vertex2).add(vertex1);
            return true;
        }
        return false;
    }

    public boolean removeEdge(final String vertex1, final String vertex2)
    {
        if (ObjectUtils.allNotNull(adjList.get(vertex1), adjList.get(vertex2))) {
            adjList.get(vertex1).remove(vertex2);
            adjList.get(vertex2).remove(vertex1);
            return true;
        }
        return false;
    }

    public boolean removeVertex(final String vertex)
    {
         if (!adjList.containsKey(vertex)) {
             return false;
         }
         for (String otherVertex : adjList.get(vertex)) {
             adjList.get(otherVertex).remove(vertex);
         }
         adjList.remove(vertex);
         return true;
    }

    public void printGraph()
    {
        System.out.println(adjList);
    }
}
