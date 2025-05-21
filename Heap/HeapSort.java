import java.util.Scanner;

// Time Complexity: O(n*log(n)).
/* Approach: 
Build a heap and then, remove the first element of the heap by placing it at the last of the array and then reduce the size of heap & heapify (percolateDown).
*/

public class HeapSort {

  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    Heap h = new Heap(10, 1);
    System.out.print("Enter the number of elements of the heap: ");
    h.setSize(sc.nextInt());
    System.out.println("\nEnter the elements of the heap: ");
    int[] temp = new int[h.getSize()];
    for (int i = 0; i < h.getSize(); i++) {
      temp[i] = sc.nextInt();
    }
    h.buildHeap(temp);
    System.out.println("\nCurrent Heap: ");
    h.printHeap();
    int[] sortedArray = heapSort(h);
    System.out.println("\nSorted Array: ");
    for (int i = 0; i < sortedArray.length; i++) {
      System.out.print(sortedArray[i] + "\t");
    }
  }

  private static int[] heapSort(Heap h) {
    int old_size = h.getSize();
    while (h.getSize() > 0) {
      int temp = h.getArray()[0];
      h.getArray()[0] = h.getArray()[h.getSize() - 1];
      h.getArray()[h.getSize() - 1] = temp;
      int size = h.getSize();
      size = size - 1;
      h.setSize(size);
      h.heapifyDown(0);
    }
    h.setSize(old_size);
    return h.getArray();
  }
}

class Heap {

  private final int heapType;
  private int capacity;
  private int size;
  private int[] array;

  public Heap(int capacity, int heapType) {
    this.array = new int[capacity];
    this.heapType = heapType;
  }

  public int getCapacity() {
    return this.capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public int getSize() {
    return this.size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int[] getArray() {
    return this.array;
  }

  public void setArray(int[] array) {
    this.array = array;
  }

  public void heapifyDown(int i) {
    int leftChild = -1, rightChild = -1, index = -1;
    if (2 * i + 1 < this.size) {
      leftChild = 2 * i + 1;
    }
    if (2 * i + 2 < this.size) {
      rightChild = 2 * i + 2;
    }
    if (leftChild != -1 && rightChild != -1) {
      if (this.array[leftChild] > this.array[rightChild]) {
        index = leftChild;
      } else {
        index = rightChild;
      }
    } else if (leftChild == -1 && rightChild != -1) {
      index = rightChild;
    } else if (leftChild != -1 && rightChild == -1) {
      index = leftChild;
    }
    if (index != -1) {
      if (this.array[index] > this.array[i]) {
        int temp = this.array[index];
        this.array[index] = this.array[i];
        this.array[i] = temp;
        heapifyDown(index);
      }
    }
  }

  public void buildHeap(int[] a) {
    if (a.length > this.capacity) {
      this.capacity = a.length;
      this.array = new int[this.capacity];
    }
    this.size = a.length;
    System.arraycopy(a, 0, this.array, 0, this.size);
    for (int i = (this.size - 1) / 2; i >= 0; i--) {
      heapifyDown(i);
    }
  }

  public void printHeap() {
    for (int i = 0; i < this.size; i++) {
      System.out.print(this.array[i] + "\t");
    }
  }
}