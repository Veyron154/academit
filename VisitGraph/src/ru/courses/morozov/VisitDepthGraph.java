package ru.courses.morozov;

import java.util.ArrayList;

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
        int[] row = graph[node.getIndexID() - 1];
        for (int i = 0; i < graph.length; ++i) {
            if (row[i] == 1 && !executedNode.contains(nodes[i])) {
                visit(nodes[i]);
            }
        }
    }
}
