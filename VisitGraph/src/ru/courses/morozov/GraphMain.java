package ru.courses.morozov;

public class GraphMain {
    public static void main(String[] args) {
        int countOfNodes = 7;
        GraphNode[] nodes = new GraphNode[countOfNodes];
        for (int i = 0; i < countOfNodes; ++i) {
            nodes[i] = new GraphNode(i + 1);
        }

        int[][] graph = {
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1, 0}
        };

        printTable(graph);

        //VisitWidthGraph visitWidthGraph = new VisitWidthGraph(nodes, graph);
        //visitWidthGraph.visit(nodes[0]);

        VisitDepthGraph visitDepthGraph = new VisitDepthGraph(nodes, graph);
        visitDepthGraph.visit(nodes[0]);
    }

    public static void printTable(int[][] graph) {
        for (int[] row : graph) {
            for (int edge : row) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }
}
