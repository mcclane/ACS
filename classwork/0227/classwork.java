public class classwork {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(90);
        bst.add(80);
        bst.add(100);
        bst.add(70);
        bst.add(85);
        bst.add(98);
        bst.add(120);
        System.out.println(bst);
    }
}

class Node<E> {
    private E data;
    private Node<E> left, right;
    public Node(E data) {
        this.data = data;
        left = null;
        right = null;
    }
    public E get() {
        return data;
    }
    public Node<E> getLeft() {
        return left;
    }
    public Node<E> getRight() {
        return right;
    }
    public void setRight(Node<E> right) {
        this.right = right;
    }
    public void setLeft(Node<E> left) {
        this.left = left;
    }
}

class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;
    public BinarySearchTree() {
        root = null;
    }
    public void add(E data) {
        if(root == null) {
            root = new Node<E>(data);
        }
        else {
            add(data, root);
        }
    }
    public void add(E data, Node<E> current) {
        if(data.compareTo(current.get()) < 0) {
            if(current.getLeft() == null) {
                current.setLeft(new Node<E>(data));
            }
            else {
                add(data, current.getLeft());
            }
        }
        else if(data.compareTo(current.get()) > 0) {
            if(current.getRight() == null) {
                current.setRight(new Node<E>(data));
            }
            else {
                add(data, current.getRight());
            }
        }
    }
    public String toString() {
        return InOrderString(root);
    }
    public String InOrderString(Node<E> current) {
        if(current != null) {
            String out = InOrderString(current.getLeft());
            out += " "+current.get().toString();
            out += InOrderString(current.getRight());
            return out;
        }
        return "";
    }
}