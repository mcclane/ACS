public class v2 {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(90);
        bst.add(80);
        bst.add(100);
        bst.add(70);
        bst.add(85);
        bst.add(98);
        bst.add(120);
        System.out.println(bst.InOrderString());
        System.out.println(bst.PreOrderString());
        System.out.println("Contains 85: "+bst.contains(85));
        System.out.println("Contains 86: "+bst.contains(86));
        System.out.println();

        bst = new BinarySearchTree<Integer>();
        bst.add(90);
        bst.add(80);
        bst.add(100);
        bst.add(70);
        bst.add(85);
        bst.add(98);
        bst.add(120);
        bst.remove(70);
        bst.remove(120);
        System.out.println("70 and 120 removed");
        System.out.println(bst.InOrderString());
        System.out.println(bst.PreOrderString());
        System.out.println();

        bst = new BinarySearchTree<Integer>();
        bst.add(90);
        bst.add(80);
        bst.add(100);
        bst.add(70);
        bst.add(85);
        bst.add(98);
        bst.add(120);
        bst.remove(100);
        System.out.println("100 removed");
        System.out.println(bst.InOrderString());
        System.out.println(bst.PreOrderString());
        System.out.println();

        bst = new BinarySearchTree<Integer>();
        bst.add(90);
        bst.add(80);
        bst.add(100);
        bst.add(98);
        bst.add(91);
        bst.add(99);
        bst.remove(100);
        System.out.println("100 removed");
        System.out.println(bst.InOrderString());
        System.out.println(bst.PreOrderString());
        System.out.println();

        bst = new BinarySearchTree<Integer>();
        bst.add(90);
        bst.add(100);
        bst.add(98);
        bst.add(110);
        bst.remove(90);
        System.out.println("90 removed");
        System.out.println(bst.InOrderString());
        System.out.println(bst.PreOrderString());
        System.out.println();

        bst = new BinarySearchTree<Integer>();
        bst.add(90);
        bst.add(80);
        bst.add(100);
        bst.add(98);
        bst.add(91);
        bst.add(99);
        bst.remove(90);
        System.out.println("90 removed");
        System.out.println(bst.InOrderString());
        System.out.println(bst.PreOrderString());
        System.out.println();


    }
}
class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;
    public BinarySearchTree() {
        root = null;
    }
    public void remove(E data) {
        remove(data, root, null);    
    }
    public void remove(E data, Node<E> current, Node<E> parent) {
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
                    if(parent.getLeft() == current) {
                        parent.setLeft(null);
                    }
                    else {
                        parent.setRight(null);
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
                    if(parent.getLeft() == current) {
                        parent.setLeft(current.getLeft());
                    }
                    else {
                        parent.setRight(current.getLeft());
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
                    if(parent.getLeft() == current) {
                        parent.setLeft(current.getRight());
                    }
                    else {
                        parent.setRight(current.getRight());
                    }
                }
            }
            // two children
            else {
                // not root
                if(current == root) {
                    Node<E> lowestLeft = getAndRemoveLowest(current.getRight(), current);
                    lowestLeft.setLeft(root.getLeft());
                    lowestLeft.setRight(root.getRight());
                    root = lowestLeft;
                }
                // root
                else {
                    // get and remove the lowest node to the right of the current
                    Node<E> lowestLeft = getAndRemoveLowest(current.getRight(), current);
                    // put in references from current into lowestLeft
                    lowestLeft.setLeft(current.getLeft());
                    lowestLeft.setRight(current.getRight());
                    //System.out.println(lowestLeft+" "+lowestLeft.getLeft()+" "+lowestLeft.getRight());
                    // remove the current node
                    if(parent.getLeft() == current)
                        parent.setLeft(lowestLeft);
                    else
                        parent.setRight(lowestLeft);
                }
            }
        }
        else {
            //System.out.println(data+" "+data.compareTo(current.get())+" "+current.get()+" "+current.getLeft()+" "+current.getRight());
            if(data.compareTo(current.get()) < 0)
                remove(data, current.getLeft(), current);
            else
                remove(data, current.getRight(), current);
        }
    }
    public Node<E> getAndRemoveLowest(Node<E> current, Node<E> parent) {
        if(current.getLeft() == null) {
            if(parent.getRight() == current)
                parent.setRight(null);
            else
                parent.setLeft(null);
            return current;
        }
        else {
            return getAndRemoveLowest(current.getLeft(), current);
        }
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
    public String PreOrderString() {
        return PreOrderString(root);
    }
    public String PreOrderString(Node<E> current) {
        if(current != null) {
            String out = " "+current.get();
            out += PreOrderString(current.getLeft());
            out += PreOrderString(current.getRight());
            return out;
        }
        return "";
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
