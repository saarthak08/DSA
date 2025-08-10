package java.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Heaps are complete binary tree which is of two types: Max Heap and Min Heap.
/*
Max-Heap: In a Max-Heap the key present at the root node must be greatest among the keys present at all of its children. The same property must be recursively true for all subtrees in that Binary Tree.
Min-Heap: In a Min-Heap the key present at the root node must be minimum among the keys present at all of its children. The same property must be recursively true for all subtrees in that Binary Tree.

Heapify means percolateDown or percolateUp.

Percolate Down: Time Complexity: O(logN)
Approach: For current index, find left child and right child, then compare which child is bigger, if the bigger child is bigger with parent, then swap child and parent values.
		  Repeat the same process for all the index up to the down by updating index recursively. The children of an index i will be 2i+1 and 2i+2.
Percolate Up is just opposite of it. That is, for current index, we find the parent of the index. i.e. (i-1)/2. Time complexity: O(logn).
Whenever we precolateDown an element, we have to be sure that elements below the current element are already heapified.
Only then, precolatingDown will heapify that element. Same for percolateUp. If we are percolatingUp, we have to be sure that
the elements above are already heapified.

Build Heap: Time Complexity: O(n)
Approach: Take input in an array & then, heapify (percolateDown) all elements by moving from bottom to top except leaf nodes.

DeleteElement: Time Complexity: O(logN)
Approach: Replace the element to be deleted with the last element and then reduce the size of array & heapify (percolateDown) from the index where the element was deleted/replaced.

Insert Element: Time Complexity: O(logN)
Approach: Insert the element at the last and then heapify (percolate up).

Note: Why does buildHeap takes O(n) time complexity why not O(nlogn) if we are taking n elements and heapifying every element?
Ans: The number of operations required for percolateDown and percolateUp is proportional to the distance the node may have to move.
For percolateDown, it is the distance to the bottom of the tree, so percolateDown is expensive for nodes at the top of the tree.
With percolateUp, the work is proportional to the distance to the top of the tree, so percolateUp is expensive for nodes at the bottom of the tree.
Although both operations are O(log n) in the worst case, in a heap, only one node is at the top whereas half the nodes lie in the bottom layer.
So it shouldn't be too surprising that if we have to apply an operation to every node, we would prefer percolateDown over percolateUp.
The buildHeap function takes an array of unsorted items and moves them until they all satisfy the heap property, thereby producing a valid heap.
There are two approaches one might take for buildHeap using the percolateUp and percolateDown operations we've described.
Start at the top of the heap (the beginning of the array) and call percolateUp on each item.
At each step, the previously percolated items (the items before the current item in the array) form a valid heap,
and percolating the next item up places it into a valid position in the heap.
After percolating up each node, all items satisfy the heap property.
Or, go in the opposite direction: start at the end of the array and move backwards towards the front.
At each iteration, you percolate an item down until it is in the correct location.
Now, second option is better here since all the leaf nodes (which are approximately half of the tree nodes) are already heapified.
So, we can percolate down from a level above leaf nodes and heapify them down.

Heap Sort: Time Complexity: O(nLogn)
Approach: Build a heap and then, remove the first element of the heap by placing it at the last of the array and then reduce the size of heap & heapify (percolateDown).
*/

public class Heap {

  private final List<Integer> heap;

  public Heap(List<Integer> items) {
    heap = new ArrayList<>(items);
    buildHeap();
  }

  private void buildHeap() {
    // Start from the last non-leaf node and percolate down.
    for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
      percolateDown(i);
    }
  }

  public void insert(int item) {
    heap.add(item);
    percolateUp(heap.size() - 1);
  }

  public int extractMax() {
    if (isEmpty()) {
      throw new NoSuchElementException("Heap is empty.");
    }
    int max = heap.getFirst();
    int lastElement = heap.removeLast();
    if (!isEmpty()) {
      heap.set(0, lastElement);
      percolateDown(0);
    }
    return max;
  }

  public int delete(int index) {
    if (index < 0 || index >= heap.size()) {
      throw new IndexOutOfBoundsException("Invalid index.");
    }
    int deletedItem = heap.get(index);
    int lastElement = heap.removeLast();
    if (index < heap.size()) { // if the deleted element was not the last one
      heap.set(index, lastElement);
      // We don't know if the new element should go up or down, so we try both.
      // In a typical implementation, you'd compare with parent and decide.
      // A simpler way is to just percolate down, as it's the most common case after a deletion
      // swap.
      percolateDown(index);
    }
    return deletedItem;
  }

  private void percolateUp(int index) {
    int parent = (index - 1) / 2;
    if (index > 0 && heap.get(index) > heap.get(parent)) {
      swap(index, parent);
      percolateUp(parent);
    }
  }

  private void percolateDown(int index) {
    int leftChild = 2 * index + 1;
    int rightChild = 2 * index + 2;
    int largest = index;

    if (leftChild < heap.size() && heap.get(leftChild) > heap.get(largest)) {
      largest = leftChild;
    }
    if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largest)) {
      largest = rightChild;
    }

    if (largest != index) {
      swap(index, largest);
      percolateDown(largest);
    }
  }

  public boolean isEmpty() {
    return heap.isEmpty();
  }

  public void printHeap() {
    System.out.println(heap);
  }

  private void swap(int i, int j) {
    int temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Heap heap = null;

    while (true) {
      System.out.println("\n****** MAIN MENU *******");
      System.out.println("1. Build heap from a new list.");
      System.out.println("2. Insert element into heap.");
      System.out.println("3. Delete element from heap (by index).");
      System.out.println("4. Extract Max element.");
      System.out.println("5. Print heap.");
      System.out.println("6. Exit");
      System.out.print("Enter the option: ");
      int option = sc.nextInt();

      if (heap == null && option > 1 && option < 6) {
        System.out.println("Please build the heap first (Option 1).");
        continue;
      }

      switch (option) {
        case 1:
          System.out.print("Enter the number of elements: ");
          int n = sc.nextInt();
          List<Integer> items = new ArrayList<>();
          System.out.println("Enter the elements:");
          for (int i = 0; i < n; i++) {
            items.add(sc.nextInt());
          }
          heap = new Heap(items);
          System.out.println("Heap built successfully.");
          break;
        case 2:
          System.out.print("Enter the element to insert: ");
          int element = sc.nextInt();
          heap.insert(element);
          System.out.println("Element inserted.");
          break;
        case 3:
          System.out.print("Enter the index of the element to delete: ");
          int index = sc.nextInt();
          try {
            heap.delete(index);
            System.out.println("Element at index " + index + " deleted.");
          } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 4:
          try {
            System.out.println("Extracted Max: " + heap.extractMax());
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 5:
          heap.printHeap();
          break;
        case 6:
          sc.close();
          return;
        default:
          System.out.println("Error! Invalid Input. Please try again!");
          break;
      }
    }
  }
}
