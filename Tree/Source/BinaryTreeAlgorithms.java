package trees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeAlgorithms {
    /**
     * Performs a pre-order traversal of a binary tree.
     * @param root Root of binary tree.
     * @param <T> Type of node payload.
     * @return A Collection containing the node payloads in traversal order.
     */
    public static <T> List<T> preOrder(BinaryNode<T> root) {
        // Create a list
        List<T> res = new ArrayList<>();
        if(root != null) {
            res.add(root.payload);
            res.addAll(preOrder(root.left)); // add all left node val after add root
            res.addAll(preOrder(root.right)); // add all right node val after add root and left
        }
        return res;
    }

    /**
     * Performs a in-order traversal of a binary tree.
     * @param root Root of binary tree.
     * @param <T> Type of node payload.
     * @return A Collection containing the node payloads in traversal order.
     */
    public static <T> List<T> inOrder(BinaryNode<T> root) {
        List<T> res = new ArrayList<>(); //create result list
        if(root!=null) {
            res.addAll(inOrder(root.left));
            res.add(root.payload);
            res.addAll(inOrder(root.right));
        }
        return res;
    }

    /**
     * Performs a post-order traversal of a binary tree.
     * @param root Root of binary tree.
     * @param <T> Type of node payload.
     * @return A Collection containing the node payloads in traversal order.
     */
    public static <T> List<T> postOrder(BinaryNode<T> root) {
        List<T> res = new ArrayList<>(); // create result list
        if(root != null) {
            res.addAll(postOrder(root.left));
            res.addAll(postOrder(root.right));
            res.add(root.payload);
        }
        return res;
    }

    /**
     * Conduct a binary search on a binary search tree for a target value.
     * @param root Root of the binary search tree.
     * @param value The value to search for.
     * @return The node containing the value, or null if the value is not present in the tree.
     */
    public static BinaryNode<Integer> binarySearch(BinaryNode<Integer> root, Integer value) {
        if(root == null) return null;
        else if (root.payload == value ) return root;
        else if (root.payload < value) return binarySearch(root.right, value); // go to the right branch when value is larger than root
        else return binarySearch(root.left,value); // go to the left branch if value small than root
    }

    /**
     * Inserts an Integer value into a Binary Search Tree.
     * @param root Root of the binary search tree.
     * @param value The value to insert.
     * @return The BinaryNode containing the newly inserted value, or an existing BinaryNode with an equal value.
     */
    public static BinaryNode<Integer> insert(BinaryNode<Integer> root, Integer value) {
        if(root == null) return new BinaryNode<>(value);
        else {
            if(value < root.payload) {
                if(root.left == null) {
                    root.left = insert(root.left, value); // insert the value to root.left if root left is null
                    return root.left; // then return the root left
                } return insert(root.left,value); // else just retrieve to the left branch
            } else if (value > root.payload) {
                if(root.right == null) {
                    root.right = insert(root.right, value); // insert the value to the root right if it is null
                    return root.right; // then return it.
                } return insert(root.right, value);//else just retrieve to the right branch

            }
        }
        return root; // if the value equal the root payload, dont have to add new root.
    }

    /**
     * Determines if two BSTs are equal in value.
     * @param A Root of first tree.
     * @param B Root of second tree.
     * @return True or false depending on the equality of the two trees.
     */
    public static boolean equals(BinaryNode<Integer> A, BinaryNode<Integer> B) {
        if(A == null && B == null) return true; // check null condition
        if(A != null && B != null) { // un null condition
            return A.payload == B.payload && equals(A.left,B.left) && equals(A.right,B.right);
            // Check the root payload than check left children and right children
        }
        return false;
    }

    /**
     * Finds the path from the tree root to a target element.
     * This algorithm does NOT assume the tree is a Binary Search Tree,
     * only that it is a Binary Tree.
     *
     * Hint: This method is a bit harder than the ones above.
     * Consider implementing some TreeAlgorithms first to get some more recursion practice.
     *
     * Hint: You can use the LinkedList::addAll method to append all the contents of
     * another list to a list (like a join, but copies and is non-destructive).
     * You may also use the LinkedList::addFirst method to push to the front of the list.
     *
     * @param root Root of the tree.
     * @param value The value to search for.
     * @param <T> The type of the value to search for.
     * @return A LinkedList of Directions that lead to the target value.
     * If the target value is at the root element, return an empty list.
     * If the target value is not present in the tree, return null.
     */
    public static <T> LinkedList<BinaryNode.Direction> path(BinaryNode<T> root, T value) {
        LinkedList<BinaryNode.Direction> res = new LinkedList<>();
        if(root == null) return null;
        else if (root.payload == value) return res; // at the end, no more direction

        // check from left to right so the search can go like depth search
        if(root.left != null) {
            if(root.left.payload == value) {
                // if the pay load of left child is the value add the left direction
                res.add(BinaryNode.Direction.left);
                return res;
            } else if(path(root.left, value) != null){ // keep going down if the root left is not equal the value
                res.add(BinaryNode.Direction.left); //add the left direction since you just move left 1
                res.addAll(path(root.left,value));//every time going down add to the list;
            }
        }
        if(root.right != null) { // instruction like when you go down by left child
            if(root.right.payload == value) {
                res.add(BinaryNode.Direction.right);
                return res;
            } else if(path(root.right,value) != null) {
                res.add(BinaryNode.Direction.right);
                res.addAll(path(root.right,value));
            }
        }
        if(res.isEmpty()) return null;
        return res;
    }
}
