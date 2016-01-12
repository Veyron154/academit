package ru.courses.morozov;

public class VisitDepth {
    public void visit(TreeNode root){
        root.printID();
        for(TreeNode child : root.getChildren()){
            visit(child);
        }
    }
}
