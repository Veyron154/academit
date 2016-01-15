package ru.courses.morozov;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode root) {
        try {
            isCorrect(root);
        } catch (IllegalBinaryTreeNodesException e) {
            System.out.println("Введённые узлы не соответствуют бинарному дереву");
        }
        this.root = root;
    }

    public boolean contains(BinaryTreeNode searchingNode) {
        if(root == null){
            throw new BinaryTreeRootNullException();
        }
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
        if(root == null){
            throw new BinaryTreeRootNullException();
        }
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
        if(root == null){
            throw new BinaryTreeRootNullException();
        }
        BinaryTreeNode leftChild = removedNode.getLeftChild();
        BinaryTreeNode rightChild = removedNode.getRightChild();

        if (removedNode == root) {
            if(rightChild == null && leftChild == null){
                root = null;
            }
            if(rightChild == null){
                root = leftChild;
                return;
            }
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

    private boolean isCorrect(BinaryTreeNode root) throws IllegalBinaryTreeNodesException{
        if(root == null){
            throw new BinaryTreeRootNullException();
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.remove();
            BinaryTreeNode leftChild = node.getLeftChild();
            BinaryTreeNode rightChild = node.getRightChild();

            if (leftChild != null) {
                if (leftChild.getIndexID() > node.getIndexID()) {
                    throw new IllegalBinaryTreeNodesException();
                }
                queue.add(leftChild);
            }
            if (rightChild != null) {
                if (rightChild.getIndexID() <= node.getIndexID()) {
                    throw new IllegalBinaryTreeNodesException();
                }
                queue.add(rightChild);
            }
        }
        return true;
    }

    public String toString () {
        if(root == null){
            throw new BinaryTreeRootNullException();
        }
        return addNodeToString(root);
    }

    private String addNodeToString(BinaryTreeNode node){
        StringBuilder builder = new StringBuilder();
        builder.append("(")
                .append(node.getIndexID());
        if(node.getLeftChild() == null){
            builder.append("x");
        }
        if(node.getLeftChild() != null){
            builder.append(addNodeToString(node.getLeftChild()));
        }
        if(node.getRightChild() != null){
            builder.append(addNodeToString(node.getRightChild()));
        }
        if(node.getRightChild() == null){
            builder.append("x");
        }
        builder.append(")");
        return builder.toString();
    }
}
