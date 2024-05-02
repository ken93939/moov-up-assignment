package com.kenkwok.assignment.domain.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
  private final int vertices;
  private List<Integer>[] adj;

  public Graph(int vertices) {
    this.vertices = vertices;
    adj = new ArrayList[vertices];
    for(int v = 0; v < vertices; v++) {
      adj[v] = new ArrayList<>();
    }
  }

  public void addEdge(int source, int dest) {
    adj[source].add(dest);
    adj[dest].add(source);
  }

  public Iterable<Integer> adj(int vertex) {
    return adj[vertex];
  }

  public int getVertices() {
    return vertices;
  }
}
