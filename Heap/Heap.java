import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Heap {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Enter the number of elements in the heap: ");
    int n = sc.nextInt();
    ArrayList<Integer> heap = new ArrayList<Integer>();
    for (int i = 0; i < n; i++) {
      heap.add(sc.nextInt());
    }
    buildHeap(heap);
    while (n != -1) {
      System.out.println(
          "\nEnter 1 to print heap\nEnter 2 insert element into heap.\nEnter 3 to remove element from heap\nEnter -1 to exit");
      n = sc.nextInt();
      switch (n) {
        case 1:
          printHeap(heap);
          break;
        case 2:
          insertElementIntoHeap(heap);
          break;
        case 3:
          deleteElementFromHeap(heap);
          break;
        case -1:
          return;
        default:
          System.out.println("Error, invalid input! Please try again.");
      }
    }
  }

  private static void deleteElementFromHeap(ArrayList<Integer> heap) {
    System.out.println("Enter the element to be deleted from heap: ");
    int element = sc.nextInt();
    Iterator<Integer> iterator = heap.iterator();
    int index = -1;
    int i = 0;
    while (iterator.hasNext()) {
      if (iterator.next() == element) {
        index = i;
        break;
      }
      i++;
    }
    if (index == -1) {
      System.out.println("Given element is not present in the heap");
    } else {
      heap.set(index, heap.get(heap.size() - 1));
      int result = heap.remove(heap.size() - 1);
      percolateDown(heap, index);
      System.out.println("Element removed from the heap");
    }
  }

  private static void insertElementIntoHeap(ArrayList<Integer> heap) {
    System.out.println("Enter the element to be inserted in heap: ");
    heap.add(sc.nextInt());
    percolateUp(heap, heap.size() - 1);
    System.out.println("Element inserted into heap");
  }

  private static void printHeap(ArrayList<Integer> heap) {
    System.out.println("\nHeap: ");
    Iterator<Integer> i = heap.iterator();
    while (i.hasNext()) {
      System.out.print(i.next() + "\t");
    }
    System.out.println();
  }

  private static void buildHeap(ArrayList<Integer> heap) {
    for (int i = (heap.size() - 1) / 2; i >= 0; i--) {
      percolateDown(heap, i);
    }
  }

  private static void percolateUp(ArrayList<Integer> heap, int index) {
    int parent = (index - 1) / 2 >= 0 ? (index - 1) / 2 : -1;
    if (parent != -1 && heap.get(parent) < heap.get(index)) {
      int temp = heap.get(index);
      heap.set(index, heap.get(parent));
      heap.set(parent, temp);
      percolateUp(heap, parent);
    }
  }

  private static void percolateDown(ArrayList<Integer> heap, int index) {
    int firstChild = heap.size() > 2 * index + 1 ? 2 * index + 1 : -1;
    int secondChild = heap.size() > 2 * index + 2 ? 2 * index + 2 : -1;
    int replacableIndex = -1;
    if (firstChild != -1 && heap.get(firstChild) > heap.get(index)) {
      replacableIndex = firstChild;
      if (secondChild != -1 && heap.get(secondChild) > heap.get(firstChild)) {
        replacableIndex = secondChild;
      }
    } else if (secondChild != -1 && heap.get(secondChild) > heap.get(index)) {
      replacableIndex = secondChild;
    }
    if (replacableIndex != -1) {
      int temp = heap.get(replacableIndex);
      heap.set(replacableIndex, heap.get(index));
      heap.set(index, temp);
      percolateDown(heap, replacableIndex);
    }
  }
}