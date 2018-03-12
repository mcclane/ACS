class Node<E> {
    private E data;
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;
    public Node(E data) {
        this.data = data;
        parent = null;
        left = null;
        right = null;
    }
    public Node(E data, Node<E> parent, Node<E> left, Node<E> right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
    public E get() {
        return data;
    }
    public Node<E> getParent() {
        return parent;
    }
    public Node<E> getLeft() {
        return left;
    }
    public Node<E> getRight() {
        return right;
    }
    public void setParent(Node<E> parent) {
        this.parent = parent;
    }
    public void setLeft(Node<E> left)  {
        this.left = left;
    }
    public void setRight(Node<E> right) {
        this.right = right;
    }
}
public class BinaryTree<E extends Comparable<E>> {
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
                current.setLeft(new Node<E>(data, current, null, null));
            }
            else {
                add(data, current.getLeft());
            }
        }
        else if(data.compareTo(current.get()) > 0) {
            if(current.getRight() == null) {
                current.setRight(new Node<E>(data, current, null, null));
            }
            else {
                add(data, current.getRight());
            }
        }
    }
    public String toString() {
        return InOrderString(root);
    }
    public String InOrderString() {
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
    public String toStringPreOrder() {
        return toStringPreOrder(root);
    }
    public String toStringPreOrder(Node<E> current) {
        if(current != null) {
            String out = " "+current.get();
            out += toStringPreOrder(current.getLeft());
            out += toStringPreOrder(current.getRight());
            return out;
        }
        return "";
    }
    public boolean contains(E data) {
        return contains(data, root);
    }
    public boolean contains(E data, Node<E> current) {
        if(current != null) {
            if(data.compareTo(current.get()) == 0) {
                return true;
            }
            if(data.compareTo(current.get()) < 0) {
                return contains(data, current.getLeft());
            }
            else {
                return contains(data, current.getRight());
            }
        }
        return false;
    }
    public void remove(E data) {
        remove(data, root);    
    }
    public void remove(E data, Node<E> current) {
        if(current == null) {}
        else if(data.compareTo(current.get()) == 0) {
            // no children
            if(current.getLeft() == null && current.getRight() == null) {
                // root
                if(current == root) {
                    root = null;
                }
                // not root
                else {
                    if(current.getParent().getLeft() == current) {
                        current.getParent().setLeft(null);
                    }
                    else {
                        current.getParent().setRight(null);
                    }
                }
            }
            // one left child
            else if(current.getLeft() != null && current.getRight() == null) {
                // root
                if(current == root) {
                    root = root.getLeft();
                }
                // not root
                else {
                    if(current.getParent().getLeft() == current) {
                        current.getParent().setLeft(current.getLeft());
                    }
                    else {
                        current.getParent().setRight(current.getLeft());
                    }
                }
            }
            // one right child
            else if(current.getLeft() == null && current.getRight() != null) {
                // root
                if(current == root) {
                    root = root.getRight();
                }
                // not root
                else {
                    if(current.getParent().getLeft() == current) {
                        current.getParent().setLeft(current.getRight());
                    }
                    else {
                        current.getParent().setRight(current.getRight());
                    }
                }
            }
            // two children
            else {
                // not root
                if(current == root) {
                    Node<E> lowestLeft = getAndRemoveLowest(current.getRight());
                    lowestLeft.setLeft(root.getLeft());
                    lowestLeft.setRight(root.getRight());
                    root = lowestLeft;
                }
                // root
                else {
                    // get and remove the lowest node to the right of the current
                    Node<E> lowestLeft = getAndRemoveLowest(current.getRight());
                    // put in references from current into lowestLeft
                    lowestLeft.setLeft(current.getLeft());
                    lowestLeft.setRight(current.getRight());
                    //System.out.println(lowestLeft+" "+lowestLeft.getLeft()+" "+lowestLeft.getRight());
                    // remove the current node
                    if(current.getParent().getLeft() == current)
                        current.getParent().setLeft(lowestLeft);
                    else
                        current.getParent().setRight(lowestLeft);
                }
            }
        }
        else {
            //System.out.println(data+" "+data.compareTo(current.get())+" "+current.get()+" "+current.getLeft()+" "+current.getRight());
            if(data.compareTo(current.get()) < 0)
                remove(data, current.getLeft());
            else
                remove(data, current.getRight());
        }
    }
    public Node<E> getAndRemoveLowest(Node<E> current) {
        if(current.getLeft() == null) {
            if(current.getParent().getRight() == current)
                current.getParent().setRight(null);
            else
                current.getParent().setLeft(null);
            return current;
        }
        else {
            return getAndRemoveLowest(current.getLeft());
        }
    }
}