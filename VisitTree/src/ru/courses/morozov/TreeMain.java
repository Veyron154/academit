package ru.courses.morozov;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeMain {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode10 = new TreeNode(10);

        treeNode1.addChild(treeNode2);
        treeNode1.addChild(treeNode3);
        treeNode2.addChild(treeNode4);
        treeNode3.addChild(treeNode5);
        treeNode3.addChild(treeNode6);
        treeNode3.addChild(treeNode7);
        treeNode4.addChild(treeNode8);
        treeNode4.addChild(treeNode9);
        treeNode7.addChild(treeNode10);

        printTree(treeNode1);
        System.out.println();
        VisitWidth visitWidth = new VisitWidth();
        visitWidth.visit(treeNode1);
        //VisitDepth visitDepth = new VisitDepth();
        //visitDepth.visit(treeNode1);
    }

    public static void printTree(TreeNode root){
        System.out.print("(");
        System.out.print(root.getIndexID());
        for(int i = 0; i < root.getChildren().size(); ++i){
            printTree(root.getChildren().get(i));
        }
        if(root.getChildren().size() == 0){
            System.out.print("x");
        }
        System.out.print(")");
    }
}
