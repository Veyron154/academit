package ru.courses.morozov;

public class TreeNode {
    private int indexID;
    private TreeNode[] children;

    public TreeNode(int indexID, int countOfChildren) {
        this.indexID = indexID;
        this.children = new TreeNode[countOfChildren];
    }

    public void printID() {
        System.out.println(indexID);
    }

    public void setChild(TreeNode child) {
        for (int i = 0; i < children.length; ++i) {
            if (children[i] == null) {
                children[i] = child;
                break;
            }
        }
    }

    public TreeNode[] getChildren() {
        return this.children;
    }
}
