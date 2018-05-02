public class Node<E> {
    private E data;
    private Node<E> left;
    private Node<E> right;
    public Node(E data) {
        this.data = data;
        left = null;
        right = null;
    }
    public E get() {
        return data;
    }
    public void setLeft(Node<E> left) {
        this.left = left;
    }
    public void setRight(Node<E> right) {
        this.right = right;
    }
    public Node<E> getLeft() {
        return left;
    }
    public Node<E> getRight() {
        return right;
    }
}