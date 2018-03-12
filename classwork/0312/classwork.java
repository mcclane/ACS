public class classwork {
    public static void main(String[] args) {
        BinaryTree<Integer> bt = new BinaryTree<Integer>();
        bt.add(90);
        bt.add(80);
        bt.add(100);
        bt.add(70);
        bt.add(85);
        bt.add(98);
        bt.add(120);
        System.out.println("In-order = "+bt.InOrderString());
        System.out.println("Pre-order = "+bt.toStringPreOrder());
        System.out.println("Height: "+bt.getHeight());
        System.out.println("Levels: "+bt.getLevel());
        System.out.println(bt.getLevel(90));
        System.out.println(bt.getLevel(120));
        
        bt = new BinaryTree<Integer>();
        bt.add(90);
        bt.add(91);
        bt.add(92);
        bt.add(93);
        System.out.println("In-order = "+bt.InOrderString());
        System.out.println("Pre-order = "+bt.toStringPreOrder());
        System.out.println("Height: "+bt.getHeight());
        System.out.println("Levels: "+bt.getLevel());
        System.out.println(bt.getLevel(90));
        System.out.println(bt.getLevel(120));
        
        bt = new BinaryTree<Integer>();
        bt.add(90);
        bt.add(150);
        bt.add(170);
        bt.add(160);
        bt.add(171);
        bt.add(151);
        System.out.println("In-order = "+bt.InOrderString());
        System.out.println("Pre-order = "+bt.toStringPreOrder());
        System.out.println("Height: "+bt.getHeight());
        System.out.println("Levels: "+bt.getLevel());
        System.out.println(bt.getLevel(150));
        System.out.println(bt.getLevel(151));
    }
}

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
class BinaryTree<E extends Comparable<E>> {
    private Node<E> root;
    public int passes = 0;
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
    public E search(E data) {
        passes = 0;
        Node<E> res = search(data, root);
        if(res == null) {
            return null;
        }
        return res.get();
    }
    public Node<E> search(E data, Node<E> current) {
        if(current != null) {
            passes++;
            if(data.compareTo(current.get()) == 0) {
                return current;
            }
            if(data.compareTo(current.get()) < 0) {
                return search(data, current.getLeft());
            }
            else {
                return search(data, current.getRight());
            }
        }
        return null;
    }
    public int getHeight() {
        return getHeight(root);
    }
    private int getHeight(Node<E> current) {
        if(current == null) {
            return -1;
        }
        if(getHeight(current.getRight()) > getHeight(current.getLeft())) {
            return 1 + getHeight(current.getRight());
        }
        else {
            return 1 + getHeight(current.getLeft());
        }
    }
    public int getLevel() {
        return getHeight() + 1;
    }
    public int getLevel(E data) {
        return getLevel(data, root, 1);
    }
    private int getLevel(E data, Node<E> current, int level) {
        if(current == null) {
            return -1;
        }
        if(data.compareTo(current.get()) == 0) {
            return level;
        }
        else {
            int leftLevel = getLevel(data, current.getLeft(), level+1);
            int rightLevel = getLevel(data, current.getRight(), level+1);
            if(leftLevel != -1) {
                return leftLevel;
            }
            else if(rightLevel != -1) {
                return rightLevel;
            }
            return -1;
            
        }
    }
    
}