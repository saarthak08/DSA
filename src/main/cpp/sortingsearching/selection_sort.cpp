#include <iostream>

using namespace std;

void selection_sort(int a[], int n);

/*
Time Complexity: O(n^2)
Approach: Minimum element is selected from the array each time & placed at the
right index.
*/

int main() {
  int n;
  cout << "Enter the number of elements in the array: ";
  cin >> n;
  int a[n], d[n];
  cout << "Enter the elements: " << endl;
  for (int i = 0; i < n; i++) {
    cin >> a[i];
  }
  selection_sort(a, n);
}

void selection_sort(int a[], int n) {
  for (int i = 0; i < n; i++) {
    int min = a[i];
    int min_index = i;
    for (int j = i + 1; j < n; j++) {
      if (a[j] < min) {
        min_index = j;
        min = a[j];
      }
    }
    int temp = a[i];
    a[i] = a[min_index];
    a[min_index] = temp;
  }

  cout << "\n\nSorted Array: " << endl;
  for (int i = 0; i < n; i++) {
    cout << a[i] << "\t";
  }
}
