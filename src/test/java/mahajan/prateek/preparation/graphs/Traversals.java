package mahajan.prateek.preparation.graphs;

import org.junit.Test;

/**
 * Created by: pramahajan on 11/7/19 12:43 AM GMT+05:30
 */
public class Traversals {

    @Test
    public void dfs() {
        Graph<Integer> graph = getGraph();

        System.out.println("DFS1");
        graph.dfs(graph.getNode("0"));

        System.out.println("DFS2");
        graph.dfs(graph.getNode("0"));
    }

    @Test
    public void bfs() {
        Graph<Integer> graph = getGraph();

        System.out.println("BFS1");
        graph.bfs(graph.getNode("0"));

        System.out.println("BFS2");
        graph.bfs(graph.getNode("0"));
    }

    private Graph<Integer> getGraph() {
        Graph<Integer> graph = new Graph<>();
        graph.addNode("0", 0);
        graph.addNode("1", 1);
        graph.addNode("2", 2);
        graph.addNode("3", 3);
        graph.addNode("4", 4);
        graph.addNode("5", 5);

        graph.addEdge(graph.getNode("0"), graph.getNode("1"));
        graph.addEdge(graph.getNode("0"), graph.getNode("4"));
        graph.addEdge(graph.getNode("0"), graph.getNode("5"));


        graph.addEdge(graph.getNode("1"), graph.getNode("3"));
        graph.addEdge(graph.getNode("1"), graph.getNode("4"));

        graph.addEdge(graph.getNode("2"), graph.getNode("1"));

        graph.addEdge(graph.getNode("3"), graph.getNode("2"));
        graph.addEdge(graph.getNode("3"), graph.getNode("4"));
        return graph;
    }

}
