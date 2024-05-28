import java.util.ArrayList;

class Node {
    int key;
    Node(int key) {
        this.key = key;
    }
}

class Heap {
    private ArrayList<Node> heap;

    public Heap() {
        this.heap = new ArrayList<>();
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(3);
        heap.insert(2);
        heap.insert(15);
        heap.insert(5);
        heap.insert(4);
        heap.insert(45);
        heap.print();
        System.out.println(heap.extractMin());
        heap.print();
        System.out.println(heap.search(5));
        System.out.println(heap.search(15));
        int[] sortedArray = heap.heapSort();
        for (int i : sortedArray) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Helper
    private int parent(int i) {
        return (i - 1) / 2;
    }
    private int leftChild(int i) {
        return 2 * i + 1;
    }
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        Node temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void heapifyUp(int i) {
        while (i > 0 && heap.get(parent(i)).key > heap.get(i).key) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < heap.size() && heap.get(left).key < heap.get(smallest).key) {
            smallest = left;
        }

        if (right < heap.size() && heap.get(right).key < heap.get(smallest).key) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    // Insert a new key
    public void insert(int key) {
        heap.add(new Node(key));
        heapifyUp(heap.size() - 1);
    }

    // Remove and return the minimum element
    public int extractMin() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        Node root = heap.get(0);
        Node lastNode = heap.get(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastNode);
            heapifyDown(0);
        }

        return root.key;
    }

    // Search for a key
    public boolean search(int key) {
        for (Node node : heap) {
            if (node.key == key) {
                return true;
            }
        }
        return false;
    }

    // Sort the heap
    public int[] heapSort() {
        ArrayList<Node> tempHeap = new ArrayList<>(heap);
        int[] sortedArray = new int[heap.size()];
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = extractMin();
        }
        heap = tempHeap; // Restore the heap
        return sortedArray;
    }

    // Print the heap
    public void print() {
        for (Node node : heap) {
            System.out.print(node.key + " ");
        }
        System.out.println();
    }
}