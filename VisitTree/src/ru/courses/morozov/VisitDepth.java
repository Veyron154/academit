package ru.courses.morozov;

public class VisitDepth {
    public void visit(TreeNode root){
        root.printID();
        root.getChildren().forEach(this::visit);
    }
}
