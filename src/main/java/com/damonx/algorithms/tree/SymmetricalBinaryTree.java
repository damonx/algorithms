package com.damonx.algorithms.tree;

/**
 * Given a binary tree, check whether it is mirror symmetrical.
 *
 * For example, a binary tree [1,2,2,3,4,4,3] is symmetrical.
 *
 *      1
 *    /  \
 *   2    2
 *  / \  / \
 * 3  4 4   3
 *
 *
 * But the following [1,2,2,null,3,null,3] is not mirror symmetrical:
 *
 *     1
 *    / \
 *   2   2
 *    \ \
 *    3 3
 */
public class SymmetricalBinaryTree
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(final int val)
        {
            this.val = val;
        }
    }

    public boolean isSymmetrical(TreeNode root)
    {
        if (root == null) {
            return true;
        }
        return recur(root.left, root.right);
    }

    public boolean recur(TreeNode l, TreeNode r)
    {
        if (l == null && r == null)
        {
            return true;
        }
        if (l == null || r == null || l.val != r.val)
        {
            return false;
        }
        return recur(l.left, r.right) && recur(l.right, r.left);
    }
}
