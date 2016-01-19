package ru.courses.morozov;

import java.util.Deque;
import java.util.HashMap;
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

        print(treeNode1);
        //printTree(treeNode1);
        //System.out.println();
        //VisitWidth visitWidth = new VisitWidth();
        //visitWidth.visit(treeNode1);
        VisitDepth visitDepth = new VisitDepth();
        visitDepth.visit(treeNode1);
    }

    public static void printTree(TreeNode root) {
        System.out.print("(");
        System.out.print(root.getIndexID());
        for (int i = 0; i < root.getChildren().size(); ++i) {
            printTree(root.getChildren().get(i));
        }
        if (root.getChildren().size() == 0) {
            System.out.print("x");
        }
        System.out.print(")");
    }

    public static void print(TreeNode root) {
        HashMap<TreeNode, Integer> mapOfNodes = new HashMap<>();
        int rootWidth = countWidthOfNode(root, mapOfNodes);
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int currentWidth = 0;
        while (!deque.isEmpty()) {
            TreeNode node = deque.remove();
            int nodeWidth = mapOfNodes.get(node);
            for (int i = 1; i <= (nodeWidth / 2); ++i) {
                System.out.print(" ");
            }
            if (node.getIndexID() == -1) {
                System.out.print(" ");
            } else {
                System.out.print(node.getIndexID());
            }
            for (int i = (nodeWidth / 2) + 1; i < nodeWidth; ++i) {
                System.out.print(" ");
            }
            System.out.print(" ");
            currentWidth = currentWidth + nodeWidth;
            ++currentWidth;
            if (currentWidth >= rootWidth) {
                System.out.println();
                currentWidth = 0;
            }
            deque.addAll(node.getChildren());
            if (node.getChildren().size() == 0 && node.getIndexID() != -1) {
                deque.add(new TreeNode(-1));
                mapOfNodes.put(deque.getLast(), nodeWidth);
            }
        }
        System.out.println();
    }

    public static int countWidthOfNode(TreeNode node, HashMap<TreeNode, Integer> map) {
        int countOfChildren = node.getChildren().size();
        if (countOfChildren == 0) {
            if (node.getIndexID() < 10) {
                map.put(node, 1);
                return 1;
            }
            if (node.getIndexID() < 100) {
                map.put(node, 2);
                return 2;
            }
        }
        int width = 0;
        for (int i = 0; i < countOfChildren; ++i) {
            width = width + countWidthOfNode(node.getChildren().get(i), map);
        }
        width = width + (countOfChildren - 1);
        map.put(node, width);
        return width;
    }
}
