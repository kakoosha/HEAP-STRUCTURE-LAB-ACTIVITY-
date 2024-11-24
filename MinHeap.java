import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MinHeap {
    private List<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    // Insert an element into the heap
    public void insert(int value) {
        heap.add(value);
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) >= heap.get(parentIndex)) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    // Extract the minimum element from the heap
    public Integer extractMin() {
        if (heap.isEmpty()) {
            return null;
        }
        int min = heap.get(0);
        int lastIndex = heap.size() - 1;
        heap.set(0, heap.get(lastIndex));
        heap.remove(lastIndex);
        minHeapify(0);
        return min;
    }

    // Maintain the min-heap property
    private void minHeapify(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex) < heap.get(smallest)) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex) < heap.get(smallest)) {
            smallest = rightChildIndex;
        }
        if (smallest != index) {
            swap(index, smallest);
            minHeapify(smallest);
        }
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Display the heap
    public void display() {
        System.out.println("Heap: " + heap);
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Min-Heap Console Application");
        System.out.println("Commands: insert <value>, extract, display, exit");

        while (true) {
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            if (command.startsWith("insert")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    try {
                        int value = Integer.parseInt(parts[1]);
                        minHeap.insert(value);
                        System.out.println("Inserted " + value);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format.");
                    }
                } else {
                    System.out.println("Usage: insert <value>");
                }
            } else if (command.equals("extract")) {
                Integer min = minHeap.extractMin();
                if (min != null) {
                    System.out.println("Extracted min: " + min);
                } else {
                    System.out.println("Heap is empty.");
                }
            } else if (command.equals("display")) {
                minHeap.display();
            } else if (command.equals("exit")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Unknown command.");
            }
        }

        scanner.close();
    }
}