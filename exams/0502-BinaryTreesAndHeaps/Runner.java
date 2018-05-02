public class Runner {
    public static void main(String[] args) {
        BinaryTree<Profile> bt = new BinaryTree<Profile>();
        bt.add(new Profile("John", 1234));
        bt.add(new Profile("Jose", 4321));
        bt.add(new Profile("Jennifer", 1111));
        bt.add(new Profile("Juan", 5555));
        System.out.println(bt);
        
        MaxHeap<Profile> mh = new MaxHeap<Profile>();
        mh.add(new Profile("John", 1234));
        mh.add(new Profile("Jose", 4321));
        mh.add(new Profile("Jennifer", 1111));
        mh.add(new Profile("Juan", 5555));
        System.out.println(mh);
    }
}