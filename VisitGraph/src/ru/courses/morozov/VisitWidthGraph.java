package ru.courses.morozov;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class VisitWidthGraph {
    private GraphNode[] nodes;
    private int[][] graph;

    public VisitWidthGraph(GraphNode[] nodes, int[][] graph) {
        this.nodes = nodes;
        this.graph = graph;
    }

    public void visit(GraphNode node) {
        ArrayList<GraphNode> executedNodes = new ArrayList<>();
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        executedNodes.add(node);
        while (!queue.isEmpty()) {
            GraphNode graphNode = queue.remove();
            graphNode.printID();
            for (int i = 0; i < graph.length; ++i) {
                if (graph[graphNode.getIndexID() - 1][i] == 1 && !executedNodes.contains(nodes[i])) {
                    queue.add(nodes[i]);
                    executedNodes.add(nodes[i]);
                }
            }
        }
    }
}
