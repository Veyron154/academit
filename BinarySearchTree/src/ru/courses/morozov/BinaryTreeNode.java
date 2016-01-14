package ru.courses.morozov;

public class BinaryTreeNode {
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;
    private int indexID;

    public BinaryTreeNode(int indexID) {
        this.indexID = indexID;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getIndexID() {
        return this.indexID;
    }

    public BinaryTreeNode getLeftChild() {
        return this.leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return this.rightChild;
    }

    public BinaryTreeNode getParent(BinaryTreeNode root) {
        if (root.getIndexID() == indexID) {
            return null;
        }
        BinaryTreeNode node = root;
        while (true) {
            if (node.getLeftChild() != null && node.getLeftChild().getIndexID() == indexID) {
                return node;
            }
            if (node.getRightChild() != null && node.getRightChild().getIndexID() == indexID) {
                return node;
            }
            if (node.getIndexID() > indexID && node.getLeftChild() != null) {
                node = node.getLeftChild();
                continue;
            }
            if (node.getIndexID() < indexID && node.getRightChild() != null) {
                node = node.getRightChild();
                continue;
            }
            if (node.getIndexID() > indexID && node.getLeftChild() == null) {
                return null;
            }
            if (node.getIndexID() < indexID && node.getRightChild() == null) {
                return null;
            }
        }
    }
}
