package ru.courses.morozov;

public class GraphNode {
    private int indexID;

    public GraphNode(int indexID) {
        this.indexID = indexID;
    }

    public void printID() {
        System.out.println(indexID);
    }

    public int getIndexID() {
        return this.indexID;
    }
}
