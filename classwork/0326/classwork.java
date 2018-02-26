public class classwork  {
    public static void main(String[] args) {
        Node<Integer> root = new Node<Integer>(1);
        Node<Integer> two = new Node<Integer>(2);
        Node<Integer> three = new Node<Integer>(3);
        root.setLeft(two);
        root.setRight(three);
        two.setLeft(new Node<Integer>(4));
        two.setRight(new Node<Integer>(5));
        three.setLeft(new Node<Integer>(6));
        three.setRight(new Node<Integer>(7));
        
        printInOrder(root);
        System.out.println();
        printPreOrder(root);
        System.out.println();
        printPostOrder(root);
        System.out.println();
        printReverseOrder(root);
    }
    public static void printInOrder(Node<Integer> current) {
        if(current != null) {
            printInOrder(current.getLeft());
            System.out.print(current.get()+" ");
            printInOrder(current.getRight());
        }
    }
    public static void printPreOrder(Node<Integer> current) {
        if(current != null) {
            System.out.print(current.get()+" ");
            printPreOrder(current.getLeft());
            printPreOrder(current.getRight());
        }
    }
    public static void printPostOrder(Node<Integer> current) {
        if(current != null) {
            printPostOrder(current.getLeft());
            printPostOrder(current.getRight());
            System.out.print(current.get()+" ");
        }
    }
    public static void printReverseOrder(Node<Integer> current) {
        if(current != null) {
            printReverseOrder(current.getRight());
            System.out.print(current.get()+" ");
            printReverseOrder(current.getLeft());
        }
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