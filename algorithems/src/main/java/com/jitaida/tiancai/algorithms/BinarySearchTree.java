package com.jitaida.tiancai.algorithms;

import com.sun.source.tree.Tree;

public class BinarySearchTree {
    /**
     * BinarySearchTree is not necessarily be balance tree!
     */
    public TreeNode root;
    public int size;

    public class TreeNode{
        public TreeNode parent;
        public TreeNode left;
        public TreeNode right;
        public Integer value;

        public TreeNode(Integer value){
            this.value = value;
        }
    }

    public Boolean insert(Integer value){
        if (null == root) {
            root = new TreeNode(value);
            return true;
        }

        TreeNode node = new TreeNode(value);
        TreeNode currentNode = root;

        while(true){
            if (value < currentNode.value){
                if (currentNode.left == null){
                    currentNode.left = node;
                    node.parent = currentNode;
                    break;
                }

                // if left node is not null, then currentnode = left node
                currentNode = currentNode.left;
            } else if (value > currentNode.value){
                if (currentNode.right == null){
                    currentNode.right = node;
                    node.parent = currentNode;
                    break;
                }

                // if right node is not null, then currentnode = right node
                currentNode = currentNode.right;
            }
            // if the currentnode value equals to the value
            else{
                System.out.println("already has the value in the tree");
                return false;
            }
        }

        size++;
        return true;
    }

    public Boolean remove(Integer value){
        TreeNode result = this.find(value);

        if (result == null){
            return false;
        }

        /**
         *                            10
         *                           /  \
         *                          7    16
         *                              /  \
         *                            12    18
         *
         */
        TreeNode parent = result.parent;
        TreeNode current = result;
        if (result.value > parent.value){
            // right tree
            while(current != null && current.right != null){
                current.parent.right = current.right;
                current = current.right;
            }
        } else if (result.value > parent.value){
            // left tree
            while(current != null && current.left != null){
                current.parent.left = current.left;
                current = current.left;
            }
        } else{
            System.out.println("invalid tree, same parent and child same value");
            return false;
        }

        size--;
        return true;
    }

    public TreeNode find(Integer value){
        TreeNode current = this.root;

        while(current != null){
            if (value > current.value){
                current = current.right;
            } else if (value < current.value){
                current = current.left;
            } else {
                return current;
            }
        }

        return null;
    }

    public void preOrderSearch(TreeNode node){
        if (node == null){
            return;
        }

         if (node.left != null){
            preOrderSearch(node.left);
        }
        if (node.right != null){
            preOrderSearch(node.right);
        }
    }

    public void midOrderSearch(TreeNode node){
        if (node == null){
            return;
        }

        if (node.left != null){
            midOrderSearch(node.left);
        }

        System.out.print(" " + node.value);

        if (node.right != null){
            midOrderSearch(node.right);
        }
    }

    public void postOrderSearch(TreeNode node){
        if (node == null){
            return;
        }

        if (node.left != null){
            postOrderSearch(node.left);
        }

        if (node.right != null){
            postOrderSearch(node.right);
        }

        System.out.print(" " + node.value);
    }


    public static void main(String[] args){
        BinarySearchTree bst = new BinarySearchTree();

        int [] ex ={7,4,13,1,5,10,15,2,12,14,18};
//        int[] ex = {5,8,2,6,1,3,7,9,10,4};
        for (int i : ex){
            bst.insert(i);
        }

        bst.insert(11);
        bst.remove(11);


        bst.midOrderSearch(bst.root);
        System.out.println("\n" + "root: " + bst.root.value);
    }
}
