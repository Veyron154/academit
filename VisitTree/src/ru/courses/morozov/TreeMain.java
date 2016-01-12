package ru.courses.morozov;

public class TreeMain {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1, 2);
        TreeNode treeNode2 = new TreeNode(2, 1);
        TreeNode treeNode3 = new TreeNode(3, 3);
        TreeNode treeNode4 = new TreeNode(4, 2);
        TreeNode treeNode5 = new TreeNode(5, 0);
        TreeNode treeNode6 = new TreeNode(6, 0);
        TreeNode treeNode7 = new TreeNode(7, 1);
        TreeNode treeNode8 = new TreeNode(8, 0);
        TreeNode treeNode9 = new TreeNode(9, 0);
        TreeNode treeNode10 = new TreeNode(10, 0);

        treeNode1.setChild(treeNode2);
        treeNode1.setChild(treeNode3);
        treeNode2.setChild(treeNode4);
        treeNode3.setChild(treeNode5);
        treeNode3.setChild(treeNode6);
        treeNode3.setChild(treeNode7);
        treeNode4.setChild(treeNode8);
        treeNode4.setChild(treeNode9);
        treeNode7.setChild(treeNode10);

        //VisitWidth visitWidth = new VisitWidth();
        //visitWidth.visit(treeNode1);
        VisitDepth visitDepth = new VisitDepth();
        visitDepth.visit(treeNode1);
    }
}
