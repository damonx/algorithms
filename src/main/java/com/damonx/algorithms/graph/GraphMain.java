package com.damonx.algorithms.graph;

public class GraphMain
{
    public static void main(String[] args)
    {
        final Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        graph.printGraph();

        graph.removeVertex("D");
        graph.printGraph();
    }
}
