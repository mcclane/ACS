public class classwork {
    public static void main(String[] args) {
        int[] h1 = new int[]{9, 18, 23, 20, 21, 25, 26, 22, 23};
        printHeapByLevel(h1);
        System.out.println();
        printHeapPreOrder(h1, 0);
        System.out.println();
        printHeapInOrder(h1, 0);
        System.out.println("\n");
        h1 = new int[]{90, 70, 80, 50, 60, 55, 44};
        printHeapByLevel(h1);
        System.out.println();
        printHeapPreOrder(h1, 0);
        System.out.println();
        printHeapInOrder(h1, 0);
        
        int[] minh = new int[]{9,18, 23, 20, 21, 25};
        System.out.println("\n");
        printHeapByLevel(minh);
        
        int[] maxh = new int[]{90, 45, 43, 30, 39, 38};
        System.out.println("\n");
        printHeapByLevel(maxh);
    }
    public static void printHeapByLevel(int[] myheap) {
        int exp = 0;
        for(int i = 0;i < myheap.length;i++) {
            System.out.print(myheap[i]+" ");
            if(i + 1 == Math.pow(2, exp)) {
                System.out.println();
                exp++;
            }
        }
    }
    public static void printHeapPreOrder(int[] myheap, int index) {
        if(index >= myheap.length) {
            return;
        }
        System.out.print(myheap[index]+" ");
        printHeapPreOrder(myheap, index*2+1);
        printHeapPreOrder(myheap, index*2+2);
    }
    public static void printHeapInOrder(int[] myheap, int index) {
        if(index >= myheap.length) {
            return;
        }
        printHeapInOrder(myheap, index*2+1);
        System.out.print(myheap[index]+" ");
        printHeapInOrder(myheap, index*2+2);
    }
}