#include <iostream>

using namespace std;

/*
Time Complexity: O(N^2) - All cases.
But we can improve it by placing a flag inside the swapping condition & setting
it inside it. If the flag is set, then complexity can be reduced to O(n) if
array is already sorted.

Approach: Each value is compared to its neighbour & if value before is greater
than value after, then they are swapped. This step is repeated n times and after
each iteration, the greater values get placed at the last of the array.
*/

int main() {
  int n;
  cout << "Enter the number of elements in the array: ";
  cin >> n;
  int a[n], d[n];
  cout << "Enter the elements: " << endl;
  for (int i = 0; i < n; i++) {
    cin >> a[i];
    d[i] = a[i];
  }
  cout << "\n\nBubble Sort: (Ascending) " << endl;
  for (int i = 0; i < n - 1; i++) {
    for (int j = 0; j < n - i - 1; j++) {
      if (a[j] > a[j + 1]) {
        int temp = a[j];
        a[j] = a[j + 1];
        a[j + 1] = temp;
      }
    }
    cout << "\nIteration " << i + 1 << endl;
    for (int j = 0; j < n; j++) {
      cout << a[j] << "\t";
    }
  }

  cout << "\n\nBubble Sort: (Descending) " << endl;
  for (int i = 0; i < n - 1; i++) {
    for (int j = n - 1; j > i; j--) {
      if (a[j - 1] < a[j]) {
        int temp = a[j - 1];
        a[j - 1] = a[j];
        a[j] = temp;
      }
    }
    cout << "\nIteration " << i + 1 << endl;
    for (int j = 0; j < n; j++) {
      cout << a[j] << "\t";
    }
  }
}
