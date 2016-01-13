package ru.courses.morozov;

public class GraphMain {
    public static void main(String[] args) {
        int countOfNodes = 7;
        GraphNode[] nodes = new GraphNode[countOfNodes];
        for (int i = 0; i < countOfNodes; ++i) {
            nodes[i] = new GraphNode(i + 1);
        }

        int[][] graph = new int[countOfNodes][countOfNodes];
        graph[0] = new int[]{0, 1, 0, 0, 0, 0, 0};
        graph[1] = new int[]{1, 0, 1, 1, 1, 1, 0};
        graph[2] = new int[]{0, 1, 0, 0, 0, 0, 1};
        graph[3] = new int[]{0, 1, 0, 0, 0, 0, 0};
        graph[4] = new int[]{0, 1, 0, 0, 0, 1, 0};
        graph[5] = new int[]{0, 1, 0, 0, 1, 0, 1};
        graph[6] = new int[]{0, 0, 1, 0, 0, 1, 0};


        //VisitWidthGraph visitWidthGraph = new VisitWidthGraph(nodes, graph);
        //visitWidthGraph.printTable();
        //visitWidthGraph.visit(nodes[0]);

        VisitDepthGraph visitDepthGraph = new VisitDepthGraph(nodes, graph);
        visitDepthGraph.printTable();
        visitDepthGraph.visit(nodes[0]);
    }
}
