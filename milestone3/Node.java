package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
    private String name;
    private List<Node> edges;
    private Message msg;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getEdges() {
        return edges;
    }

    public void setEdges(List<Node> edges) {
        this.edges = edges;
    }

    public Message getMessage() {
        return msg;
    }

    public void setMessage(Message msg) {
        this.msg = msg;
    }

    public void addEdge(Node n) {
        edges.add(n);
    }

    public boolean hasCycles() {
        Set<Node> visited = new HashSet<>();
        Set<Node> recStack = new HashSet<>();
        return hasCyclesDFS(this, visited, recStack);
    }

    private boolean hasCyclesDFS(Node node, Set<Node> visited, Set<Node> recStack) {
        visited.add(node);
        recStack.add(node);

        for (Node neighbor : node.edges) {
            if (!visited.contains(neighbor)) {
                if (hasCyclesDFS(neighbor, visited, recStack)) {
                    return true;
                }
            } else if (recStack.contains(neighbor)) {
                return true;
            }
        }

        recStack.remove(node);
        return false;
    }
}