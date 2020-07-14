package com.jitaida.tiancai.leetcode.easy;

import java.nio.file.Path;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明:?叶子节点是指没有子节点的节点。
 *
 * 示例:?
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 */

public class PathSum {
    public class TreeNode{
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode(Integer value){
            this.val = value;
        }
    }


    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }

        boolean ret = false;
        sum = sum - root.val;

        if (sum == 0 && root.left == null && root.right == null){
            return true;
        }

        if (root.left != null) {
            ret = hasPathSum(root.left, sum);
        }

        if (ret){
            return true;
        }

        if (root.right != null) {
            ret = hasPathSum(root.right, sum);
        }

        return ret;
    }

    public void run(){
        PathSum.TreeNode tr = new TreeNode(1);
        PathSum ps = new PathSum();
        System.out.println(ps.hasPathSum(tr, 13));
    }

    public static void main(String[] args){
        PathSum ps = new PathSum();
        ps.run();
    }
}
