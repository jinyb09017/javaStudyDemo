package com.abbott.arithmetic.tree;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

import java.util.Stack;

public class BinaryTree {

    //获得二叉树的深度  递归的思路
    int maxDeepth(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        int left = maxDeepth(treeNode.left);
        int right = maxDeepth(treeNode.right);

        return Math.max(left, right) + 1;

    }

    public TreeNode createTree() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        root.left = node2;
        root.right = node3;

        TreeNode node4 = new TreeNode(4);
        node2.left = node4;

        TreeNode node5 = new TreeNode(5);
        node3.right = node5;

        TreeNode node6 = new TreeNode(6);
        node4.right = node6;

        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node6.left = node7;
        node6.right = node8;

        return root;

    }


    /**
     * 递归先序遍历
     *
     * @param root
     */
    public void recursionPreOrderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");

            recursionPreOrderTraversal(root.left);
            recursionPreOrderTraversal(root.right);
        }

    }

    public void preOrderTraversal(TreeNode node) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                System.out.print(node.val + " ");
                treeNodeStack.push(node);
                node = node.left;
            }

            if (!treeNodeStack.isEmpty()) {
                TreeNode treeNode = treeNodeStack.pop();
                node = treeNode.right;
            }
        }

    }

    /**
     * 递归中序遍历
     *
     * @param root
     */
    public void recursionMidOrderTraversal(TreeNode root) {
        if (root != null) {
            recursionMidOrderTraversal(root.left);
            System.out.print(root.val + " ");
            recursionMidOrderTraversal(root.right);
        }
    }

    public void midOrderTraversal(TreeNode node) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;

            }

            if (!treeNodeStack.isEmpty()) {
                TreeNode treeNode = treeNodeStack.pop();
                System.out.print(treeNode.val + " ");
                node = treeNode.right;
            }
        }
    }

    /**
     * 递归后序遍历
     *
     * @param root
     */
    public void recursionLastOrderTraversal(TreeNode root) {
        if (root != null) {
            recursionLastOrderTraversal(root.left);
            recursionLastOrderTraversal(root.right);
            System.out.print(root.val + " ");
        }
    }

    // 非递归后序遍历
    public static void lastOrderTraversal(TreeNode root) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        TreeNode node = root;
        TreeNode lastVisit = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }
            //查看当前栈顶元素
            node = treeNodeStack.peek();
            //如果其右子树也为空，或者右子树已经访问
            //则可以直接输出当前节点的值
            if (node.right == null || node.right == lastVisit) {
                System.out.print(node.val + " ");
                treeNodeStack.pop();
                lastVisit = node;
                node = null;
            } else {
                //否则，继续遍历右子树
                node = node.right;
            }
        }
    }


    @Timer
    public void test() {
        TreeNode treeNode = createTree();
        recursionPreOrderTraversal(treeNode);
        System.out.println();
        preOrderTraversal(treeNode);
        System.out.println();
        recursionMidOrderTraversal(treeNode);
        System.out.println();
        midOrderTraversal(treeNode);
        System.out.println();
        recursionLastOrderTraversal(treeNode);
        System.out.println();
        lastOrderTraversal(treeNode);
        System.out.println();
    }

    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }
}
