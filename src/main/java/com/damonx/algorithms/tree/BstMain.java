package com.damonx.algorithms.tree;

public class BstMain
{
    public static void main(String[] args)
    {
        final BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(47);
        binarySearchTree.insert(21);
        binarySearchTree.insert(76);
        binarySearchTree.insert(18);
        binarySearchTree.insert(52);
        binarySearchTree.insert(82);
        binarySearchTree.insert(27);

        System.out.println(binarySearchTree.root.left.right.value);
        System.out.println("BST contains 18? :" + binarySearchTree.contains(18));
        System.out.println("BST contains 28? :" + binarySearchTree.contains(28));
    }
}
