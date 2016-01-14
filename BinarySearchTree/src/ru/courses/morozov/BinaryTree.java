package ru.courses.morozov;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.remove();
            BinaryTreeNode leftChild = node.getLeftChild();
            BinaryTreeNode rightChild = node.getRightChild();

            if (leftChild != null) {
                if (leftChild.getIndexID() > node.getIndexID()) {
                    System.out.println("Узлы не соответствуют двоичному дереву");
                    return;
                }
                queue.add(leftChild);
            }
            if (rightChild != null) {
                if (rightChild.getIndexID() <= node.getIndexID()) {
                    System.out.println("Узлы не соответствуют двоичному дереву");
                    return;
                }
                queue.add(rightChild);
            }
        }
        this.root = root;
    }

    public boolean contains(BinaryTreeNode searchingNode) {
        BinaryTreeNode node = root;
        int searchingNodeID = searchingNode.getIndexID();

        while (node.getIndexID() != searchingNodeID) {
            if (node.getIndexID() > searchingNodeID && node.getLeftChild() != null) {
                node = node.getLeftChild();
                continue;
            }
            if (node.getIndexID() < searchingNodeID && node.getRightChild() != null) {
                node = node.getRightChild();
                continue;
            }
            if (node.getIndexID() > searchingNodeID && node.getLeftChild() == null) {
                return false;
            }
            if (node.getIndexID() < searchingNodeID && node.getRightChild() == null) {
                return false;
            }
        }
        return true;
    }

    public void addNode(BinaryTreeNode addedNode) {
        BinaryTreeNode node = root;
        int addedNodeID = addedNode.getIndexID();

        while (true) {
            if (node.getIndexID() >= addedNodeID && node.getLeftChild() != null) {
                node = node.getLeftChild();
                continue;
            }
            if (node.getIndexID() < addedNodeID && node.getRightChild() != null) {
                node = node.getRightChild();
                continue;
            }
            if (node.getIndexID() >= addedNodeID && node.getLeftChild() == null) {
                node.setLeftChild(addedNode);
                return;
            }
            if (node.getIndexID() < addedNodeID && node.getRightChild() == null) {
                node.setRightChild(addedNode);
                return;
            }
        }
    }

    public void removeNode(BinaryTreeNode removedNode) {
        BinaryTreeNode leftChild = removedNode.getLeftChild();
        BinaryTreeNode rightChild = removedNode.getRightChild();

        if (removedNode == root) {
            root = rightChild;
            BinaryTreeNode tmpNode = rightChild;
            while (tmpNode.getLeftChild() != null) {
                tmpNode = tmpNode.getLeftChild();
            }
            tmpNode.setLeftChild(leftChild);
            return;
        }
        if (leftChild == null && rightChild == null) {
            BinaryTreeNode parent = removedNode.getParent(root);
            if (parent.getLeftChild() == removedNode) {
                parent.setLeftChild(null);
            }
            if (parent.getRightChild() == removedNode) {
                parent.setRightChild(null);
            }
        }
        if (leftChild == null) {
            BinaryTreeNode parent = removedNode.getParent(root);
            if (parent.getLeftChild() == removedNode) {
                parent.setLeftChild(rightChild);
            }
            if (parent.getRightChild() == removedNode) {
                parent.setRightChild(rightChild);
            }
        }
        if (rightChild == null) {
            BinaryTreeNode parent = removedNode.getParent(root);
            if (parent.getLeftChild() == removedNode) {
                parent.setLeftChild(leftChild);
            }
            if (parent.getRightChild() == removedNode) {
                parent.setRightChild(leftChild);
            }
        }
        if (leftChild != null && rightChild != null) {
            BinaryTreeNode parent = removedNode.getParent(root);
            if (parent.getLeftChild() == removedNode) {
                parent.setLeftChild(rightChild);
                BinaryTreeNode tmpNode = rightChild;
                while (tmpNode.getLeftChild() != null) {
                    tmpNode = tmpNode.getLeftChild();
                }
                tmpNode.setLeftChild(leftChild);
            }
            if (parent.getRightChild() == removedNode) {
                parent.setRightChild(rightChild);
                BinaryTreeNode tmpNode = rightChild;
                while (tmpNode.getLeftChild() != null) {
                    tmpNode = tmpNode.getLeftChild();
                }
                tmpNode.setLeftChild(leftChild);
            }
        }
    }

    public BinaryTreeNode getRoot() {
        return this.root;
    }
}
