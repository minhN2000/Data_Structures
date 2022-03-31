package test;

import org.junit.Before;
import org.junit.Test;
import trees.TreeAlgorithms;
import trees.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static junit.framework.TestCase.*;

public class TreeTest {
    /*
              1
        2     3       4
       5 6    7     8 9 10
      11    12 13  -1
               14
    */
    TreeNode<Integer> tree;

    @Before
    public void setUp() {
        tree = new TreeNode<>(1);

        tree.children = new ArrayList<>(Arrays.asList(
                new TreeNode<>(2),
                new TreeNode<>(3),
                new TreeNode<>(4)
        ));

        tree.getChild(0).children = new ArrayList<>(Arrays.asList(
                new TreeNode<>(5),
                new TreeNode<>(6)
        ));

        tree.getChild(1).children = new ArrayList<>(Arrays.asList(
                new TreeNode<>(7)
        ));

        tree.getChild(2).children = new ArrayList<>(Arrays.asList(
                new TreeNode<>(8),
                new TreeNode<>(9),
                new TreeNode<>(10)
        ));

        tree.getChild(0).getChild(0).children = new ArrayList<>(Arrays.asList(
                new TreeNode<>(11)
        ));

        tree.getChild(1).getChild(0).children = new ArrayList<>(Arrays.asList(
                new TreeNode<>(12),
                new TreeNode<>(13)
        ));

        tree.getChild(1).getChild(0).getChild(1).children = new ArrayList<>(Arrays.asList(
                new TreeNode<>(14)
        ));

        tree.getChild(2).getChild(0).children = new ArrayList<>(Arrays.asList(
                new TreeNode<>(-1)
        ));
    }

    @Test
    public void MaxTest() {
        assertEquals(null, TreeAlgorithms.max(null));
        assertEquals(Integer.valueOf(14), TreeAlgorithms.max(tree));

        tree.getChild(1).getChild(0).getChild(1).children = null;
        assertEquals(Integer.valueOf(13), TreeAlgorithms.max(tree));
    }

    @Test
    public void MinTest() {
        assertEquals(null, TreeAlgorithms.min(null));
        assertEquals(Integer.valueOf(-1), TreeAlgorithms.min(tree));

        tree.getChild(2).getChild(0).children = null;
        assertEquals(Integer.valueOf(1), TreeAlgorithms.min(tree));
    }

    /*
              1
        2     3       4
       5 6    7     8 9 10
      11    12 13  -1
               14
    */
    @Test
    public void LeavesTest() {
        assertEquals(new LinkedList<>(), TreeAlgorithms.leaves(null));

        LinkedList<TreeNode<Integer>> leaves = new LinkedList<>(Arrays.asList(
                tree.getChild(0).getChild(0).getChild(0),
                tree.getChild(0).getChild(1),
                tree.getChild(1).getChild(0).getChild(0),
                tree.getChild(1).getChild(0).getChild(1).getChild(0),
                tree.getChild(2).getChild(0).getChild(0),
                tree.getChild(2).getChild(1),
                tree.getChild(2).getChild(2)
        ));

        LinkedList<TreeNode<Integer>> computedLeaves = TreeAlgorithms.leaves(tree);
        assertEquals(leaves, computedLeaves);
    }

    @Test
    public void CountTest() {
        assertEquals(0, TreeAlgorithms.count(null));
        assertEquals(1, TreeAlgorithms.count(new TreeNode<Integer>(1)));
        assertEquals(15, TreeAlgorithms.count(tree));
        assertEquals(4, TreeAlgorithms.count(tree.getChild(0)));
        assertEquals(5, TreeAlgorithms.count(tree.getChild(1)));
        assertEquals(5, TreeAlgorithms.count(tree.getChild(2)));
    }

    @Test
    public void DepthTest() {
        assertEquals(0, TreeAlgorithms.depth(null));
        assertEquals(0, TreeAlgorithms.depth(new TreeNode<Integer>(0)));

        TreeNode<Integer> shortTree = new TreeNode<>(0);
        shortTree.addChild(new TreeNode<>(1));
        assertEquals(1, TreeAlgorithms.depth(shortTree));

        assertEquals(4, TreeAlgorithms.depth(tree));

        tree.getChild(1).getChild(0).getChild(1).children = null;
        assertEquals(3, TreeAlgorithms.depth(tree));
    }

    /*
              1
        2     3       4
       5 6    7     8 9 10
      11    12 13  -1
               14
    */
    @Test
    public void EqualsTest() {
        TreeNode<Integer> base = new TreeNode<>(1);
        assertFalse(TreeAlgorithms.equals(base, tree));
        assertFalse(TreeAlgorithms.equals(tree, base));

        base.addChild(new TreeNode<>(2));
        assertFalse(TreeAlgorithms.equals(base, tree));

        base.addChild(new TreeNode<>(3));
        base.addChild(new TreeNode<>(4));
        assertFalse(TreeAlgorithms.equals(base, tree));

        assertTrue(TreeAlgorithms.equals(tree, tree));

        assertFalse(TreeAlgorithms.equals(tree.getChild(0), tree));
        assertFalse(TreeAlgorithms.equals(tree.getChild(1), tree));
        assertFalse(TreeAlgorithms.equals(tree.getChild(2), tree));

        assertTrue(TreeAlgorithms.equals(tree.getChild(1), tree.getChild(1)));

        TreeNode<Integer> sideRoot = new TreeNode<Integer>(4);
        sideRoot.children = new ArrayList<>(Arrays.asList(
                new TreeNode<>(8),
                new TreeNode<>(9),
                new TreeNode<>(10)
        ));
        assertFalse(TreeAlgorithms.equals(tree.getChild(2), sideRoot));
        sideRoot.getChild(0).children = new ArrayList<>(Arrays.asList(
                new TreeNode<>(-1)
        ));

        assertTrue(TreeAlgorithms.equals(tree.getChild(2), sideRoot));

        TreeNode<Integer> oldRoot = tree;
        setUp();

        assertTrue(TreeAlgorithms.equals(oldRoot, tree));
    }

    /*
              1
        2     3       4
       5 6    7     8 9 10
      11    12 13  -1
               14
    */
    @Test
    public void BfsTest() {
        assertEquals(new LinkedList<>(), TreeAlgorithms.bfs(null));
        assertEquals(new LinkedList<Integer>(Arrays.asList(1)), TreeAlgorithms.bfs(new TreeNode<>(1)));

        LinkedList<Integer> bfsExpected = new LinkedList<>(Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, -1, 14
        ));

        assertEquals(bfsExpected, TreeAlgorithms.bfs(tree));

        bfsExpected = new LinkedList<>(Arrays.asList(
                3, 7, 12, 13, 14
        ));

        assertEquals(bfsExpected, TreeAlgorithms.bfs(tree.getChild(1)));
    }

    /*
              1
        2     3       4
       5 6    7     8 9 10
      11    12 13  -1
               14
    */
    @Test
    public void PathTest() {
        assertEquals(new LinkedList<>(), TreeAlgorithms.path(null, 1));

        assertEquals(new LinkedList<TreeNode<Integer>>(Arrays.asList(
                tree.getChild(0),
                tree.getChild(0).getChild(1)
        )), TreeAlgorithms.path(tree.getChild(0), 6));

        assertEquals(new LinkedList<TreeNode<Integer>>(Arrays.asList(
                tree.getChild(2),
                tree.getChild(2).getChild(0),
                tree.getChild(2).getChild(0).getChild(0)
        )), TreeAlgorithms.path(tree.getChild(2), -1));

        assertEquals(new LinkedList<TreeNode<Integer>>(Arrays.asList(
                tree,
                tree.getChild(2),
                tree.getChild(2).getChild(0),
                tree.getChild(2).getChild(0).getChild(0)
        )), TreeAlgorithms.path(tree, -1));

        assertEquals(new LinkedList<TreeNode<Integer>>(Arrays.asList(
                tree,
                tree.getChild(1),
                tree.getChild(1).getChild(0),
                tree.getChild(1).getChild(0).getChild(1),
                tree.getChild(1).getChild(0).getChild(1).getChild(0)
        )), TreeAlgorithms.path(tree, 14));

        assertEquals(new LinkedList<>(), TreeAlgorithms.path(tree, 999));
    }
}
