#include <algorithm> // For std::swap
#include <iostream>
#include <vector>

using namespace std;

/**
 * @brief To heapify a subtree rooted with node i.
 * @param arr The vector to be heapified.
 * @param n The size of the heap.
 * @param i The index of the root of the subtree.
 */
void heapify(vector<int> &arr, int n, int i) {
  int largest = i;       // Initialize largest as root
  int left = 2 * i + 1;  // left child
  int right = 2 * i + 2; // right child

  // If left child is larger than root
  if (left < n && arr[left] > arr[largest]) {
    largest = left;
  }

  // If right child is larger than largest so far
  if (right < n && arr[right] > arr[largest]) {
    largest = right;
  }

  // If largest is not root
  if (largest != i) {
    swap(arr[i], arr[largest]);

    // Recursively heapify the affected sub-tree
    heapify(arr, n, largest);
  }
}

/**
 * @brief The main function to perform heap sort on a vector.
 * @param arr The vector to be sorted.
 */
void heapSort(vector<int> &arr) {
  int n = arr.size();

  // Build a max-heap from the array.
  // The last non-leaf node is at index (n / 2) - 1.
  for (int i = n / 2 - 1; i >= 0; i--) {
    heapify(arr, n, i);
  }

  // One by one extract an element from heap
  for (int i = n - 1; i > 0; i--) {
    // Move current root to end
    swap(arr[0], arr[i]);

    // call max heapify on the reduced heap
    heapify(arr, i, 0);
  }
}

/**
 * @brief A utility function to print a vector.
 * @param arr The vector to print.
 */
void printArray(const vector<int> &arr) {
  for (int val : arr) {
    cout << val << " ";
  }
  cout << endl;
}

int main() {
  int n;
  cout << "Enter the number of elements: ";
  cin >> n;

  vector<int> arr(n);
  cout << "Enter the elements:" << endl;
  for (int i = 0; i < n; i++) {
    cin >> arr[i];
  }

  cout << "\nOriginal array is: \n";
  printArray(arr);

  heapSort(arr);

  cout << "\nSorted array is: \n";
  printArray(arr);

  return 0;
}