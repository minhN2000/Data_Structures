package trees;

public class BinaryNode<T> {
    public BinaryNode<T> left;
    public BinaryNode<T> right;
    public T payload;

    public BinaryNode(T payload) {
        this.payload = payload;
    }

    public BinaryNode(T payload, BinaryNode<T> left, BinaryNode<T> right) {
        this.payload = payload;
        this.left = left;
        this.right = right;
    }

    public enum Direction {
        left,
        right
    }
}
