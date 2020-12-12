package mahajan.prateek.prep.graphs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static mahajan.prateek.prep.graphs.Graph.Node.State.VISITED;
import static mahajan.prateek.prep.graphs.Graph.Node.State.VISITING;

/**
 * Created by: pramahajan on 11/6/19 11:39 PM GMT+05:30
 */

@Getter @Setter
public class Graph<T> {

    private Map<String, Node<T>> nodes = new HashMap<>();
    private int size = 0;
    private Map<String, List<Node<T>>> adjacent = new HashMap<>();

    public Node<T> addNode(String key, T value) {
        Node<T> node = new Node<>(key, value);
        nodes.put(key, node);
        adjacent.put(key, new ArrayList<>());
        size++;
        return node;
    }

    public Node<T> getNode(String key) {
        return nodes.get(key);
    }

    public void addEdge(Node<T> source, Node<T> target) {
        addEdge(source, target, false);
    }

    public void addEdge(Node<T> source, Node<T> target, boolean bidirectional) {
        if (nodes.containsKey(source.key) && nodes.containsKey(target.key)) {
            List<Node<T>> children = adjacent.get(source.key);
            children.add(target);
            adjacent.put(source.key, children);

            if (bidirectional) {
                children = adjacent.get(target.key);
                children.add(source);
                adjacent.put(target.key, children);
            }
        }
    }

    // Best implementation of DFS at https://leetcode.com/submissions/detail/345565408/ - doesn't use enums, detects cycle, prints all nodes, etc.
    public void dfs(Node<T> start) {
        markAllNodesUnvisited();   // important for subsequent runs
        dfsUtil(start);
    }

    public void bfs(Node<T> start) {
        if (start == null) return;

        markAllNodesUnvisited();

        Queue<Node<T>> queue = new LinkedList<>();

        start.state = VISITING; // mark before enqueue
        queue.add(start);

        while (!queue.isEmpty()) {
            Node<T> node = queue.remove();
            visit(node);

            for (Node<T> neighbour: adjacent.get(node.key)) {
                if (neighbour.isUnvisited()) {
                    neighbour.state = VISITING; // need to mark visiting explicitly in BFS due to while loop, in DFS it is handled by recursion
                    queue.add(neighbour);
                }
            }

            // mark a node visited only when all work is done on this node -
            // i.e. node has been visited and its children have been enqueued
            node.state = VISITED;
        }

    }

    public void printNode(Node<T> node) {
        System.out.println(node.key + " " + node.value);
    }


    private void dfsUtil(Node<T> start) { // just like preorder in tree
        if (start == null) {
            return;
        }

        start.state = Node.State.VISITING;  // mark visiting
        visit(start);  // visit

        for (Node<T> neighbour : adjacent.get(start.key)) {
            if (neighbour.isUnvisited()) {   // if neighbours are unvisited, recurse on them
                dfsUtil(neighbour); // in DFS, no need to mark visiting explicitly as it is handled by recursion but BFS needs explicit marking to visiting
            }
        }

        start.state = VISITED; // mark visited
    }

    private void visit(Node<T> node) {
        printNode(node);
    }

    private void markAllNodesUnvisited() {
        for (Map.Entry<String, Node<T>> entry : nodes.entrySet()) {
            entry.getValue().state = Node.State.UNVISITED;
        }
    }

    @Getter @Setter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    static class Node<T> {
        enum State {UNVISITED, VISITING, VISITED}

        private State state = State.UNVISITED;
        @NonNull // Lombok's @NonNull - used by @RequiredArgsConstructor
        private String key;
        @NonNull
        private T value;

        public boolean isUnvisited() {
            return state == State.UNVISITED;
        }

        public boolean isVisiting() {
            return state == State.VISITING;
        }

        public boolean isVisited() {
            return state == VISITED;
        }

    }

}
