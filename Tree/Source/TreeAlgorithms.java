package trees;

import java.util.*;

public class TreeAlgorithms {
    /**
     * Finds the maximum Integer in a tree.
     * @param root Root of the tree.
     * @return The maximum Integer contained in the tree; null if the root is null.
     */
    public static Integer max(TreeNode<Integer> root) {
        if(root == null) return null;
        else if(root.children == null) return root.payload;
        else {
            Integer max = root.payload;
            for(TreeNode<Integer> curr : root.children) {
                Integer childMax = max(curr); // compare using depth first search
                if(childMax != null && max < childMax) max = childMax;
            }
            return max;
        }
    }

    /**
     * Finds the minimum Integer in a tree.
     * @param root Root of the tree.
     * @return The minimum Integer contained in the tree; null if the root is null.
     */
    public static Integer min(TreeNode<Integer> root) {
        if(root == null) return null;
        else if (root.children == null) return root.payload;
        else {
            Integer min = root.payload;
            for (TreeNode<Integer> curr : root.children) {
                Integer childMin = min(curr);
                if(childMin != null && childMin < min) min = childMin;
            }
            return min;
        }
    }

    /**
     * Finds all the tree leaves (nodes with no children) in a tree.
     * @param root Root of the tree.
     * @return A LinkedList of leaf TreeNodes from the tree.
     */
    public static LinkedList<TreeNode<Integer>> leaves(TreeNode<Integer> root) {
        LinkedList<TreeNode<Integer>> res = new LinkedList<>();
        if(root != null) {
            if(root.children.isEmpty()) {
                res.add(root);
            } else {
                for(TreeNode<Integer> curr : root.children) {
                    res.addAll(leaves(curr));
                }
            }
        }
        return res;
    }

    /**
     * Counts the number of nodes in a tree.
     * @param root Root of the tree.
     * @return
     */
    public static int count(TreeNode<Integer> root) {
        int res = 1;
        if(root == null) return 0;
        if(root.children.isEmpty()) return 1;
        else{
            for(TreeNode<Integer> curr : root.children) {
                res += count(curr);
            }
        }
        return res;
    }

    /**
     * Computes the depth (height) of a tree.
     * A single node by itself has zero depth;
     * a single node and a single child has a depth of 1.
     * @param root Root of the tree.
     * @return The depth (height) of the tree.
     */
    public static <T> int depth(TreeNode<T> root) {
        int depth = 0;
        if(root == null || root.children == null || root.children.isEmpty()) return 0;
        for(TreeNode<T> curr : root.children) {
            int currDepth = 1 + depth(curr);
            depth = Math.max(currDepth,depth);
        }
        return depth;
    }

    /**
     * Determines if two trees are equal in value.
     * @param A Root of the first tree.
     * @param B Root of the second tree.
     * @param <T> Type of value contained by the tree.
     * @return True or false depending on the equality of the two trees.
     */
    public static <T> boolean equals(TreeNode<T> A, TreeNode<T> B) {

        if (A == null && B == null) {
            return true;
        }
        else if(A!= null && B != null) {
            if(A.payload == B.payload) {
                if (A.children.size() != B.children.size()) {
                    return false;
                } else {
                    if(A.children.isEmpty() && B.children.isEmpty()) return true;
                    else {
                        for (int i = 0; i < A.children.size(); i++) {
                            return equals(A.children.get(i), B.children.get(i));
                        }
                    }
                }
            }
        }
        return false;

    }
    /**
     * Conducts a breadth first search on a tree, from top to bottom, left to right.
     *
     * Hint: use a Java Queue, rather than recursion (which depends on the Stack).
     * You can add and remove to the queue using Queue::add(e) and Queue::remove, respectively.
     *
     * @param root Root of the tree.
     * @return List of elements in the tree, in order of BFS search.
     */
    public static LinkedList<Integer> bfs(TreeNode<Integer> root) {
        LinkedList<Integer> res = new LinkedList<>();
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        if(root != null) q.add(root);
        while(!q.isEmpty()) {
            TreeNode<Integer> temp = q.remove();
            res.add(temp.payload);
            for(TreeNode<Integer> child : temp.children) {
                if(child != null) q.add(child);
            }
        }
        return res;
    }

    /**
     * Finds the path from a tree root to a target element.
     *
     * Note: unlike BinaryTreeAlgorithms::path, this method returns a list of nodes
     * rather than a list of directions (enums). Furthermore, this method returns
     * an empty list when there is no path, while the BinaryTreeAlgorithms::path method
     * will return null if there is no path.
     *
     * @param root Root of the tree.
     * @param value Value to search for.
     * @return A LinkedList of TreeNodes, starting with the root node, describing the path of nodes
     * from the root to the node containing the target value.
     * If the target element is not present in the tree, return an empty list.
     */
    public static <T> LinkedList<TreeNode<T>> path(TreeNode<T> root, T value) {
        LinkedList<TreeNode<T>> res = new LinkedList<>();
        if(root == null) return new LinkedList<>();
        if(root.payload == value) res.add(root);
        for(TreeNode<T> curr: root.children) {
            if(!path(curr,value).isEmpty()) {
                res.add(root);
                res.addAll(path(curr,value));
            }
        }
        return res;
    }
}
