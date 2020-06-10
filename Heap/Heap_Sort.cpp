// C++ program for implementation of Heap Sort

#include<iostream>

using namespace std;
 
int count=0; 
// To heapify a subtree rooted with node i which is
// an index in arr[]. n is size of heap
void heapify(int arr[], int n, int i)
{
    int largest = i;  // Initialize largest as root
    int l = 2*i ;  // left = 2*i
    int r = 2*i + 1;  // right = 2*i + 1
 
    // If left child is larger than root
    if (l < n && arr[l] > arr[largest])
        largest = l;
 
    // If right child is larger than largest so far
    if (r < n && arr[r] > arr[largest])
        largest = r;
 
    // If largest is not root
    if (largest != i)
    {
        swap(arr[i], arr[largest]);
 		count++;
        // Recursively heapify the affected sub-tree
        heapify(arr, n, largest);
    }
}
 
// main function to do heap sort
void heapSort(int arr[], int n)
{
    // Build heap (rearrange array)
    for (int i = n/2 ; i >= 1; i--)
        heapify(arr, n, i);
 
 	cout<<"Content of heap is:\n";
    for (int i = 1; i<=n ; i++)
        cout<<arr[i]<<"  ";
 
    // One by one extract an element from heap
    for (int i=n; i>=1; i--)
    {
        // Move current root to end
        swap(arr[1], arr[i]);
 
        // call max heapify on the reduced heap
        heapify(arr, i, 1);
    }
}
 
/* A utility function to print array of size n */
void printArray(int arr[], int n)
{
    for (int i=1; i<=n; ++i)
        cout << arr[i] << "  ";
    cout << "\n";
}
 
// Driver program
int main()
{
    int arr[] = {0, 20, 12, 35, 15, 10, 80, 30, 17, 2, 1};
    int n = sizeof(arr)/sizeof(arr[0])-1;
 
    heapSort(arr, n);
 
    cout <<"\n\nSorted array is \n";
    printArray(arr, n);
    
	cout <<"\n\n Count is \n";
    cout<<count;
    
    return 0;
}
