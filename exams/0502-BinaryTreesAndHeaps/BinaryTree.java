public class BinaryTree<E extends Comparable<E>> {
    private Node<E> root;
    public BinaryTree() {
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
    private String inOrderString(Node<E> current) {
        String out = "";
        if(current != null) {
            out += inOrderString(current.getLeft());
            out += current.get()+" ";
            out += inOrderString(current.getRight());
        }
        return out;
    }
    public String toString() {
        return inOrderString(root);
    }
}