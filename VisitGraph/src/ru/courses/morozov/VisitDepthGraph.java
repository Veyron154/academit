package ru.courses.morozov;

import java.util.ArrayList;
import java.util.Queue;

public class VisitDepthGraph {
    private GraphNode[] nodes;
    private int[][] graph;
    private ArrayList<GraphNode> executedNode;

    public VisitDepthGraph(GraphNode[] nodes, int[][] graph) {
        this.nodes = nodes;
        this.graph = graph;
        executedNode = new ArrayList<>();
    }

    public void visit(GraphNode node) {
        node.printID();
        executedNode.add(node);
        for (int i = 0; i < graph.length; ++i) {
            if (graph[node.getIndexID() - 1][i] == 1 && !executedNode.contains(nodes[i])) {
                visit(nodes[i]);
            }
        }
    }
}
