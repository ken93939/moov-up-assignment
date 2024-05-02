package com.kenkwok.assignment.domain.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.kenkwok.assignment.domain.graph.GraphTraversal;
import com.kenkwok.assignment.domain.graph.model.Graph;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class GraphTraversalTest {

	private Graph graph;
	private GraphTraversal traversal;

	@BeforeAll
	public void BeforeAll() {
		graph = new Graph(8);
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
		traversal = new GraphTraversal();
	}

	@Test
	void testPossiblePathsReturnCorrectResult() {
		List<String> paths = traversal.possiblePaths(graph, 0, 7);
		String[] expected = new String[]{
			"0>1>2>3>4>5>6>7",
			"0>1>2>3>4>7",
			"0>1>2>5>4>7",
			"0>1>2>5>6>7",
			"0>1>3>2>5>4>7",
			"0>1>3>2>5>6>7",
			"0>1>3>4>5>6>7",
			"0>1>3>4>7",
			"0>7",
			"0>3>1>2>5>4>7",
			"0>3>1>2>5>6>7",
			"0>3>2>5>4>7",
			"0>3>2>5>6>7",
			"0>3>4>5>6>7",
			"0>3>4>7"
		};
		assertArrayEquals(expected, paths.toArray());
	}

	@Test
	void testPossiblePathsReturnNoResultForDisconnectedGraph() {
		Graph graph = new Graph(4);
		graph.addEdge(0, 1);
		graph.addEdge(2, 3);
		List<String> result = traversal.possiblePaths(graph, 0, 3);
		assertEquals(0, result.size());
	}

	@Test
	void testShortestPathReturnCorrectResult() {
		String result = traversal.shortestPath(graph, 0, 7);
		String result2 = traversal.shortestPath(graph, 0, 5);
		assertEquals("0>7", result);
		assertEquals("0>1>2>5", result2);
	}

	@Test
	void testShortestPathReturnNullIfNoPathFound() {
		Graph graph = new Graph(5);
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(2, 4);
		String result = traversal.shortestPath(graph, 0, 4);
		assertNull(result);
	}
}