package com.jitaida.tiancai.leetcode.hard;

public class BinaryTreeSerializeDFS {
    public static final String delimiter = ",";

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Result{
        int index;
        TreeNode node;

        Result(TreeNode node, int index){
            this.index = index;
            this.node = node;
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] list = data.split(",");
        return deserialize(list, 0).node;
    }

    public Result deserialize(String[] data, int i){
        if (data[i].equals("None")){
            return new Result(null, i);
        }

        TreeNode node = new TreeNode(Integer.parseInt(data[i]));
        Result leftResult = deserialize(data, i + 1);

        node.left = leftResult.node;
        Result rightResult = deserialize(data, leftResult.index + 1);
        node.right = rightResult.node;

        return new Result(node, rightResult.index);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // ÖÐÐò±éÀú
        if (root == null){
            return "None,";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        sb.append(delimiter);

        sb.append(serialize(root.left));
        sb.append(serialize(root.right));

        return sb.toString();
    }

    public static void main(String[] args) {
        BinaryTreeSerializeDFS.TreeNode root = new BinaryTreeSerializeDFS.TreeNode(5);
        BinaryTreeSerializeDFS.TreeNode node2 = new BinaryTreeSerializeDFS.TreeNode(2);
        BinaryTreeSerializeDFS.TreeNode node3 = new BinaryTreeSerializeDFS.TreeNode(3);
        BinaryTreeSerializeDFS.TreeNode node4 = new BinaryTreeSerializeDFS.TreeNode(2);
        BinaryTreeSerializeDFS.TreeNode node5 = new BinaryTreeSerializeDFS.TreeNode(4);
        BinaryTreeSerializeDFS.TreeNode node6 = new BinaryTreeSerializeDFS.TreeNode(3);
        BinaryTreeSerializeDFS.TreeNode node7 = new BinaryTreeSerializeDFS.TreeNode(1);

        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;
        node4.left = node6;
        node4.right = node7;

        BinaryTreeSerializeDFS dfs = new BinaryTreeSerializeDFS();
        System.out.println(dfs.serialize(root));
        System.out.println(dfs.serialize(dfs.deserialize(dfs.serialize(root))));
    }
}
