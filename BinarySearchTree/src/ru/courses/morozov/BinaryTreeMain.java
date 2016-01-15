package ru.courses.morozov;

public class BinaryTreeMain {
    public static void main(String[] args) {
        BinaryTreeNode node1 = new BinaryTreeNode(1);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node7 = new BinaryTreeNode(7);
        BinaryTreeNode node8 = new BinaryTreeNode(8);
        BinaryTreeNode node9 = new BinaryTreeNode(9);

        node2.setLeftChild(node1);
        node2.setRightChild(node3);
        node6.setLeftChild(node5);
        node6.setRightChild(node7);
        node4.setLeftChild(node2);
        node4.setRightChild(node6);

        BinaryTree tree = new BinaryTree(node4);

        System.out.println(tree.contains(node3));
        System.out.println(tree.contains(node9));
        System.out.println(tree.toString());
        tree.addNode(node8);
        System.out.println(tree.toString());
        tree.removeNode(node4);
        System.out.println(tree.toString());
    }
}
