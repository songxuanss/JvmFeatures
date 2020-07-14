package com.jitaida.tiancai.leetcode.easy;

import java.nio.file.Path;

/**
 * ����һ����������һ��Ŀ��ͣ��жϸ������Ƿ���ڸ��ڵ㵽Ҷ�ӽڵ��·��������·�������нڵ�ֵ��ӵ���Ŀ��͡�
 *
 * ˵��:?Ҷ�ӽڵ���ָû���ӽڵ�Ľڵ㡣
 *
 * ʾ��:?
 * �������¶��������Լ�Ŀ��� sum = 22��
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * ���� true, ��Ϊ����Ŀ���Ϊ 22 �ĸ��ڵ㵽Ҷ�ӽڵ��·�� 5->4->11->2��
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
