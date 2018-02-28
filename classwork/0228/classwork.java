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
        bst.remove(70);
        bst.remove(120);
        System.out.println(bst);
        System.out.println();
        
        bst = new BinarySearchTree<Integer>();
        bst.add(90);
        bst.add(80);
        bst.add(100);
        bst.add(70);
        bst.add(85);
        bst.add(98);
        bst.add(120);
        System.out.println(bst);
        bst.remove(80);
        bst.remove(100);
        System.out.println(bst);
        
        bst = new BinarySearchTree<Integer>();
        bst.add(90);
        bst.add(80);
        bst.add(100);
        bst.add(70);
        bst.add(85);
        bst.add(98);
        bst.add(120);
        System.out.println(bst);
        bst.remove(90);
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
    public void remove(E data) {
        remove(data, root, null);
    }
    public void remove(E data, Node<E> current, Node<E> parent) {
        if(current == null) {
            return;
        }
        if(data.compareTo(current.get()) == 0) {
            if(parent == null) {
                if(current.getLeft() == null && current.getRight() != null) {
                    // one right child
                    root = current.getRight();
                }
                else if(current.getLeft() != null && current.getRight() == null) {
                    // one left child
                    root = current.getLeft();
                }
                else if(current.getLeft() == null && current.getRight() == null) {
                    // no children
                    root = null;
                }
                else {
                    // two children
                    Node<E> lowestLeft = lowest(root.getRight());
                    System.out.println(lowestLeft.get());
                    
                }
            }
            else if(current.getLeft() == null && current.getRight() == null) {
                if(parent != null) {w
                    if(parent.getLeft() == current) {
                        parent.setLeft(null);
                    }
                    else {
                        parent.setRight(null);
                    }
                }
            }
            else if(current.getLeft() == null && current.getRight() != null) {
                // set the parent connection to the only node to the right
                if(parent.getRight() == current) {
                    parent.setRight(current.getRight());
                }
                else {
                    parent.setLeft(current.getRight());
                }
            }
            else if(current.getLeft() != null && current.getRight() == null) {
                // set the parent connection to the only node to the left
                if(parent.getRight() == current) {
                    parent.setRight(current.getLeft());
                }
                else {
                    parent.setLeft(current.getLeft());
                }
            }
            else  {
                // find the lowest node to the right and replace the current one
                Node<E> lowestLeft = lowest(current.getRight());
                if(parent.getRight() == current) {
                    parent.setRight(lowestLeft);
                }
                else {
                    parent.setLeft(lowestLeft);
                }
                // set the children of the replaced node
                lowestLeft.setLeft(current.getLeft());
                if(lowestLeft != current.getRight()) {
                    lowestLeft.setRight(current.getRight());
                }
                // remove the lowest left node duplicate from the bottom
                remove(lowestLeft.get(), lowestLeft.getRight(), lowestLeft);
            }
        }
        else {
            if(data.compareTo(current.get()) < 0)
                remove(data, current.getLeft(), current);
            else
                remove(data, current.getRight(), current);
        }
    }
    public Node<E> lowest(Node<E> current) {
        if(current.getLeft() == null) {
            return current;
        }
        else {
            return lowest(current.getLeft());
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