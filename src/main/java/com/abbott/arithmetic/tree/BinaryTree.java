package com.abbott.arithmetic.tree;

public class BinaryTree {

    //获得二叉树的深度  递归的思路
    int maxDeepth(TreeNode treeNode){
        if(treeNode == null){
            return 0;
        }

        int left = maxDeepth(treeNode.left);
        int right = maxDeepth(treeNode.right);

        return Math.max(left,right)+1;

    }
}
