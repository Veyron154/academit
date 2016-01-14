package ru.courses.morozov;

import java.util.ArrayList;

public class TreeNode {
    private int indexID;
    private ArrayList<TreeNode> children;

    public TreeNode(int indexID) {
        this.indexID = indexID;
        children = new ArrayList<>();
    }

    public void printID() {
        System.out.println(indexID);
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public ArrayList<TreeNode> getChildren() {
        return this.children;
    }

    public int getIndexID(){
        return this.indexID;
    }
}
