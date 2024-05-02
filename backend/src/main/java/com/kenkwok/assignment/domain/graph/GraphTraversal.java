package com.kenkwok.assignment.domain.graph;

import com.kenkwok.assignment.domain.graph.model.Graph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class GraphTraversal {

	public static void main(String[] args) {
		Graph graph = new Graph(8);
		GraphTraversal traversal = new GraphTraversal();
		graph.addEdge(0, 1);
		graph.addEdge(0, 7);
		graph.addEdge(0, 3);

		graph.addEdge(1, 2);
		graph.addEdge(1, 3);

		graph.addEdge(2, 3);
		graph.addEdge(2, 5);

		graph.addEdge(3, 4);

		graph.addEdge(4, 5);
		graph.addEdge(4, 7);

		graph.addEdge(5, 6);

		graph.addEdge(6, 7);

		traversal.possiblePaths(graph, 0, 7);
		traversal.shortestPath(graph, 0, 5);
	}

	List<String> possiblePaths(Graph graph, int source, int dest) {
		boolean[] isVisted = new boolean[graph.getVertices()];
		List<List<Integer>> pathList = new ArrayList<>();
		List<Integer> tempPathList = new ArrayList<>();
		tempPathList.add(source);
		dfs(graph, source, dest, isVisted, tempPathList, pathList);
		return toResult(pathList);
	}

	String shortestPath(Graph graph, int source, int dest) {
		boolean[] isVisited = new boolean[graph.getVertices()];
		int[] distTo = new int[graph.getVertices()];
		int[] edgeTo = new int[graph.getVertices()];
		Queue<Integer> queue = new LinkedList<>();
		for (int v = 0; v < graph.getVertices(); v++) {
			distTo[v] = Integer.MAX_VALUE;
		}
		distTo[source] = 0;
		queue.add(source);
		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			for (int w : graph.adj(vertex)) {
				if (!isVisited[w]) {
					queue.add(w);
					isVisited[w] = true;
					distTo[w] = distTo[vertex] + 1;
					edgeTo[w] = vertex;
				}
			}
		}
		if (!isVisited[dest]) {
			return null;
		}
		Deque<Integer> path = new ArrayDeque<>() {
		};
		for (int v = dest; v != source; v = edgeTo[v]) {
			path.push(v);
		}
		path.push(source);
//		System.out.println("Shortest path:");
		String result = path.stream().map(v -> v.toString()).collect(Collectors.joining(">"));
//		System.out.println(result);
		return result;
	}

	private void dfs(Graph graph, int source, int dest, boolean[] isVisited,
		List<Integer> tempPathList, List<List<Integer>> pathList) {
		if (source == dest) {
			pathList.add(List.copyOf(tempPathList));
		}
		isVisited[source] = true;
		for (Integer vertex : graph.adj(source)) {
			if (!isVisited[vertex]) {
				tempPathList.add(vertex);
				dfs(graph, vertex, dest, isVisited, tempPathList, pathList);
				tempPathList.remove(vertex);
			}
		}
		isVisited[source] = false;
	}

	private List<String> toResult(List<List<Integer>> pathList) {
		List<Integer[]> arrList = pathList.stream()
			.map(list -> list.toArray(new Integer[0]))
			.collect(Collectors.toList());
		List<String> result = new ArrayList<>();
//		System.out.println("Paths:");
		for (Integer[] arr : arrList) {
			String str = Arrays.stream(arr)
				.map(i -> i.toString())
				.collect(Collectors.joining(">"));
			result.add(str);
//			System.out.println(str);
		}
		return result;
	}
}
