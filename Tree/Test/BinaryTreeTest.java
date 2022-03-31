package test;

import org.junit.Before;
import org.junit.Test;
import trees.BinaryNode;
import trees.BinaryTreeAlgorithms;

import java.util.*;
import java.util.stream.Collectors;

import static junit.framework.TestCase.*;

public class BinaryTreeTest {
    /*
                  50
         25               75
     10      40       60      90
           30  45           80  100
                                   110

    */
    BinaryNode<Integer> binarySearchTree;

    @Before
    public void setUp() {
        binarySearchTree = new BinaryNode<>(50);

        BinaryNode<Integer> layer1Left = new BinaryNode<>(25);
        BinaryNode<Integer> layer1Right = new BinaryNode<>(75);

        binarySearchTree.left = layer1Left;
        binarySearchTree.right = layer1Right;

        BinaryNode<Integer> layer2Left = new BinaryNode<>(10);
        BinaryNode<Integer> layer2Right = new BinaryNode<>(40);

        layer1Left.left = layer2Left;
        layer1Left.right = layer2Right;

        BinaryNode<Integer> layer3Left = new BinaryNode<>(60);
        BinaryNode<Integer> layer3Right = new BinaryNode<>(90);

        layer1Right.left = layer3Left;
        layer1Right.right = layer3Right;

        BinaryNode<Integer> layer4Left = new BinaryNode<>(30);
        BinaryNode<Integer> layer4Right = new BinaryNode<>(45);

        layer2Right.left = layer4Left;
        layer2Right.right = layer4Right;

        BinaryNode<Integer> layer5Left = new BinaryNode<>(80);
        BinaryNode<Integer> layer5Right = new BinaryNode<>(100);
        BinaryNode<Integer> layer6Right = new BinaryNode<>(110);

        layer5Right.right = layer6Right;
        layer3Right.right = layer5Right;
        layer3Right.left = layer5Left;
    }

    /*
                  50
         25               75
     10      40       60      90
           30  45           80  100
                                   110

    */
    @Test
    public void InOrderTest() {
        List<Integer> inOrder = BinaryTreeAlgorithms.inOrder(binarySearchTree.left);
        String output = inOrder.stream().map(Objects::toString).collect(Collectors.joining(" "));

        assertEquals("10 25 30 40 45", output);

        inOrder = BinaryTreeAlgorithms.inOrder(binarySearchTree.right);
        output = inOrder.stream().map(Objects::toString).collect(Collectors.joining(" "));

        assertEquals("60 75 80 90 100 110", output);

        inOrder = BinaryTreeAlgorithms.inOrder(binarySearchTree);
        output = inOrder.stream().map(Objects::toString).collect(Collectors.joining(" "));

        assertEquals("10 25 30 40 45 50 60 75 80 90 100 110", output);
    }

    @Test
    public void PreOrderTest() {
        List<Integer> preOrder = BinaryTreeAlgorithms.preOrder(binarySearchTree.left);
        String output = preOrder.stream().map(Objects::toString).collect(Collectors.joining(" "));

        assertEquals("25 10 40 30 45", output);

        preOrder = BinaryTreeAlgorithms.preOrder(binarySearchTree.right);
        output = preOrder.stream().map(Objects::toString).collect(Collectors.joining(" "));

        assertEquals("75 60 90 80 100 110", output);

        preOrder = BinaryTreeAlgorithms.preOrder(binarySearchTree);
        output = preOrder.stream().map(Objects::toString).collect(Collectors.joining(" "));

        assertEquals("50 25 10 40 30 45 75 60 90 80 100 110", output);

    }

    @Test
    public void PostOrderTest() {
        List<Integer> postOrder = BinaryTreeAlgorithms.postOrder(binarySearchTree.left);
        String output = postOrder.stream().map(Objects::toString).collect(Collectors.joining(" "));

        assertEquals("10 30 45 40 25", output);

        postOrder = BinaryTreeAlgorithms.postOrder(binarySearchTree.right);
        output = postOrder.stream().map(Objects::toString).collect(Collectors.joining(" "));

        assertEquals("60 80 110 100 90 75", output);

        postOrder = BinaryTreeAlgorithms.postOrder(binarySearchTree);
        output = postOrder.stream().map(Objects::toString).collect(Collectors.joining(" "));

        assertEquals("10 30 45 40 25 60 80 110 100 90 75 50", output);
    }

    /*
                  50
         25               75
     10      40       60      90
           30  45           80  100
                                   110

    */
    @Test
    public void BinarySearchTest() {
        BinaryNode<Integer> target50 = binarySearchTree;
        assertTrue(target50 == BinaryTreeAlgorithms.binarySearch(binarySearchTree, 50));

        BinaryNode<Integer> target80 = binarySearchTree.right.right.left;
        assertTrue(target80 == BinaryTreeAlgorithms.binarySearch(binarySearchTree, 80));

        BinaryNode<Integer> target45 = binarySearchTree.left.right.right;
        assertTrue(target45 == BinaryTreeAlgorithms.binarySearch(binarySearchTree, 45));

        BinaryNode<Integer> target110 = binarySearchTree.right.right.right.right;
        assertTrue(target110 == BinaryTreeAlgorithms.binarySearch(binarySearchTree, 110));

        assertTrue(null == BinaryTreeAlgorithms.binarySearch(binarySearchTree, -1));
        assertTrue(null == BinaryTreeAlgorithms.binarySearch(binarySearchTree, 51));
        assertTrue(null == BinaryTreeAlgorithms.binarySearch(binarySearchTree, 115));
        assertTrue(null == BinaryTreeAlgorithms.binarySearch(binarySearchTree, 39));
    }

    /*
                  50
         25               75
     10      40       60      90
           30  45           80  100
                                   110

    */
    @Test
    public void InsertTest() {
        BinaryNode<Integer> expectedInsert = new BinaryNode<>(5);
        assertEquals(expectedInsert.payload, BinaryTreeAlgorithms.insert(null, 5).payload);

        expectedInsert = BinaryTreeAlgorithms.insert(binarySearchTree, 50);
        assertEquals(binarySearchTree, expectedInsert);
        assertEquals(binarySearchTree.payload, expectedInsert.payload);

        expectedInsert = BinaryTreeAlgorithms.insert(binarySearchTree, 5);
        assertEquals(binarySearchTree.left.left.left, expectedInsert);
        assertEquals(binarySearchTree.left.left.left.payload, expectedInsert.payload);

        expectedInsert = BinaryTreeAlgorithms.insert(binarySearchTree, 115);
        assertEquals(binarySearchTree.right.right.right.right.right, expectedInsert);
        assertEquals(binarySearchTree.right.right.right.right.right.payload, expectedInsert.payload);

        expectedInsert = BinaryTreeAlgorithms.insert(binarySearchTree, 65);
        assertEquals(binarySearchTree.right.left.right, expectedInsert);
        assertEquals(binarySearchTree.right.left.right.payload, expectedInsert.payload);

        expectedInsert = BinaryTreeAlgorithms.insert(binarySearchTree, 42);
        assertEquals(binarySearchTree.left.right.right.left, expectedInsert);
        assertEquals(binarySearchTree.left.right.right.left.payload, expectedInsert.payload);

        expectedInsert = BinaryTreeAlgorithms.insert(binarySearchTree,41);
        assertEquals(binarySearchTree.left.right.right.left.left, expectedInsert);
        assertEquals(binarySearchTree.left.right.right.left.left.payload, expectedInsert.payload);

        expectedInsert = BinaryTreeAlgorithms.insert(binarySearchTree, 35);
        assertEquals(binarySearchTree.left.right.left.right, expectedInsert);
        assertEquals(binarySearchTree.left.right.left.right.payload, expectedInsert.payload);
    }

    /*
                  50
         25               75
     10      40       60      90
           30  45           80  100
                                   110

    */
    @Test
    public void EqualsTest() {
        assertFalse(BinaryTreeAlgorithms.equals(binarySearchTree, new BinaryNode<Integer>(-999)));
        assertFalse(BinaryTreeAlgorithms.equals(binarySearchTree, new BinaryNode<Integer>(50)));

        BinaryNode<Integer> fakeRoot = new BinaryNode<>(50);
        fakeRoot.left = new BinaryNode<>(25);
        fakeRoot.right = new BinaryNode<>(75);
        fakeRoot.right.left = new BinaryNode<>(60);
        assertFalse(BinaryTreeAlgorithms.equals(binarySearchTree, fakeRoot));

        assertTrue(BinaryTreeAlgorithms.equals(binarySearchTree, binarySearchTree));

        BinaryNode<Integer> oldRoot = binarySearchTree;
        setUp();
        assertTrue(BinaryTreeAlgorithms.equals(oldRoot, binarySearchTree));

        oldRoot.right.right.right.right.payload = 105;
        assertFalse(BinaryTreeAlgorithms.equals(oldRoot, binarySearchTree));

        BinaryNode<Integer> rootA = new BinaryNode<>(50);
        rootA.left = new BinaryNode<>(25);
        rootA.left.left = new BinaryNode<>(10);

        BinaryNode<Integer> rootB = new BinaryNode<>(25);
        rootB.left = new BinaryNode<>(10);
        rootB.right = new BinaryNode<>(50);

        assertFalse(BinaryTreeAlgorithms.equals(rootA, rootB));

        BinaryNode<Integer> rootC = new BinaryNode<>(50);
        assertFalse(BinaryTreeAlgorithms.equals(rootA, rootC));
        assertFalse(BinaryTreeAlgorithms.equals(rootC, rootA));

        rootC.left = new BinaryNode<>(10);
        assertFalse(BinaryTreeAlgorithms.equals(rootA, rootC));

        rootC.right = new BinaryNode<>(25);
        assertFalse(BinaryTreeAlgorithms.equals(rootA, rootC));

        rootC.left.payload = 25;
        rootC.right.payload = 10;
        assertFalse(BinaryTreeAlgorithms.equals(rootA, rootC));

        rootC.left.left = new BinaryNode<>(10);
        assertFalse(BinaryTreeAlgorithms.equals(rootA, rootC));

        rootC.right = null;
        assertTrue(BinaryTreeAlgorithms.equals(rootA, rootC));
        assertTrue(BinaryTreeAlgorithms.equals(rootC, rootA));
    }

    /*
                  50
         25               75
     10      40       60      90
           30  45           80  100
                                   110

    */
    @Test
    public void PathTest() {
        assertEquals(null, BinaryTreeAlgorithms.path(null, 0));
        assertEquals(new LinkedList<BinaryNode.Direction>(),
                BinaryTreeAlgorithms.path(new BinaryNode<>(5), 5));

        assertEquals(new LinkedList<BinaryNode.Direction>(
                Arrays.asList(
                        BinaryNode.Direction.right,
                        BinaryNode.Direction.left
                )
        ), BinaryTreeAlgorithms.path(binarySearchTree.left, 30));

        assertEquals(new LinkedList<BinaryNode.Direction>(
                Arrays.asList(
                        BinaryNode.Direction.right,
                        BinaryNode.Direction.right,
                        BinaryNode.Direction.right
                )
        ), BinaryTreeAlgorithms.path(binarySearchTree.right, 110));

        assertEquals(new LinkedList<BinaryNode.Direction>(
                Arrays.asList(
                        BinaryNode.Direction.left,
                        BinaryNode.Direction.right,
                        BinaryNode.Direction.right
                )
        ), BinaryTreeAlgorithms.path(binarySearchTree, 45));

        BinaryNode<Integer> oldRoot = binarySearchTree;
        oldRoot.right.payload = 12;
        oldRoot.left.payload = 90;
        oldRoot.right.left.right = new BinaryNode<>(42);
        oldRoot.right.left.right.left = new BinaryNode<>(9);

        assertEquals(new LinkedList<BinaryNode.Direction>(
                Arrays.asList(
                        BinaryNode.Direction.right,
                        BinaryNode.Direction.left,
                        BinaryNode.Direction.right,
                        BinaryNode.Direction.left
                )
        ), BinaryTreeAlgorithms.path(oldRoot, 9));

        assertEquals(new LinkedList<BinaryNode.Direction>(
                Arrays.asList(
                        BinaryNode.Direction.left,
                        BinaryNode.Direction.left
                )
        ), BinaryTreeAlgorithms.path(oldRoot, 10));
    }
}
