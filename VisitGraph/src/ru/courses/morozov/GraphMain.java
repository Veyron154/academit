package ru.courses.morozov;

public class GraphMain {
    public static void main(String[] args) {
        int countOfNodes = 7;
        GraphNode[] nodes = new GraphNode[countOfNodes];
        for (int i = 0; i < countOfNodes; ++i) {
            nodes[i] = new GraphNode(i + 1);
        }

        int[][] graph = new int[countOfNodes][countOfNodes];
        graph[0][1] = 1;
        graph[1][0] = 1;
        graph[1][2] = 1;
        graph[1][3] = 1;
        graph[1][4] = 1;
        graph[1][5] = 1;
        graph[2][1] = 1;
        graph[2][6] = 1;
        graph[3][1] = 1;
        graph[4][1] = 1;
        graph[4][5] = 1;
        graph[5][1] = 1;
        graph[5][4] = 1;
        graph[5][6] = 1;
        graph[6][2] = 1;
        graph[6][5] = 1;

        VisitWidthGraph visitWidthGraph = new VisitWidthGraph(nodes, graph);
        visitWidthGraph.visit(nodes[0]);
        //VisitDepthGraph visitDepthGraph = new VisitDepthGraph(nodes, graph);
        //visitDepthGraph.visit(nodes[0]);
    }
}
