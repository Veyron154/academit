package ru.courses.morozov;

import java.util.LinkedList;
import java.util.Queue;

public class VisitWidth {
    public void visit(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.remove();
            treeNode.printID();
            for(int i = 0; i < treeNode.getChildren().size(); ++i){
                queue.add(treeNode.getChildren().get(i));
            }
        }
    }
}
