#include <algorithm> // For std::swap
#include <iostream>
#include <stdexcept> // For std::out_of_range
#include <vector>

using namespace std;

/**
 * @brief A Max-Heap implementation using std::vector.
 */
class MaxHeap {
private:
  vector<int> heap;

  // Helper functions to get parent and child indices
  int parent(int i) { return (i - 1) / 2; }
  int leftChild(int i) { return 2 * i + 1; }
  int rightChild(int i) { return 2 * i + 2; }

  /**
   * @brief Maintains the max-heap property by moving an element down.
   * @param i The index to start percolating down from.
   */
  void percolateDown(int i) {
    int left = leftChild(i);
    int right = rightChild(i);
    int largest = i;

    if (left < heap.size() && heap[left] > heap[largest]) {
      largest = left;
    }
    if (right < heap.size() && heap[right] > heap[largest]) {
      largest = right;
    }

    if (largest != i) {
      swap(heap[i], heap[largest]);
      percolateDown(largest);
    }
  }

  /**
   * @brief Maintains the max-heap property by moving an element up.
   * @param i The index to start percolating up from.
   */
  void percolateUp(int i) {
    while (i > 0 && heap[i] > heap[parent(i)]) {
      swap(heap[i], heap[parent(i)]);
      i = parent(i);
    }
  }

public:
  MaxHeap() {}

  /**
   * @brief Constructs a heap from an existing vector of elements.
   * @param elements The vector of elements.
   */
  MaxHeap(const vector<int> &elements) {
    heap = elements;
    // Build heap by percolating down from the last non-leaf node
    for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
      percolateDown(i);
    }
  }

  /**
   * @brief Inserts a new element into the heap.
   * @param key The element to insert.
   */
  void insert(int key) {
    heap.push_back(key);
    percolateUp(heap.size() - 1);
  }

  /**
   * @brief Deletes the element at a specific index.
   * @param index The index of the element to delete.
   */
  void deleteAtIndex(int index) {
    if (index >= heap.size()) {
      throw out_of_range("Index out of range.");
    }
    // Replace the element with the last element and percolate down
    heap[index] = heap.back();
    heap.pop_back();
    if (index <
        heap.size()) { // Check if the deleted element was not the last one
      percolateDown(index);
    }
  }

  /**
   * @brief Prints the elements of the heap.
   */
  void print() const {
    if (heap.empty()) {
      cout << "Heap is empty." << endl;
      return;
    }
    for (int val : heap) {
      cout << val << " ";
    }
    cout << endl;
  }
};

int main() {
  MaxHeap heap;
  int option;

  do {
    cout << "\n****** MAIN MENU *******" << endl;
    cout << "1. Build heap from a list" << endl;
    cout << "2. Insert element" << endl;
    cout << "3. Delete element (by index)" << endl;
    cout << "4. Print heap" << endl;
    cout << "5. Exit" << endl;
    cout << "Enter your option: ";
    cin >> option;

    switch (option) {
    case 1: {
      int n;
      cout << "Enter the number of elements: ";
      cin >> n;
      vector<int> elements(n);
      cout << "Enter the elements:" << endl;
      for (int i = 0; i < n; ++i) {
        cin >> elements[i];
      }
      heap = MaxHeap(elements);
      cout << "Heap built." << endl;
      break;
    }
    case 2: {
      int element;
      cout << "Enter the element to insert: ";
      cin >> element;
      heap.insert(element);
      cout << "Element inserted." << endl;
      break;
    }
    case 3: {
      int index;
      cout << "Enter the index of the element to delete: ";
      cin >> index;
      try {
        heap.deleteAtIndex(index);
        cout << "Element at index " << index << " deleted." << endl;
      } catch (const out_of_range &e) {
        cout << "Error: " << e.what() << endl;
      }
      break;
    }
    case 4: {
      heap.print();
      break;
    }
    case 5:
      break;
    default:
      cout << "Invalid option. Please try again." << endl;
      break;
    }
  } while (option != 5);

  return 0;
}