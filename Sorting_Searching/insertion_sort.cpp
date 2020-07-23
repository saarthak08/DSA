#include<iostream>

using namespace std;

/*
Time Complexity: Worst Case: O(n^2), Best Case: O(n).
Approach: Each element is compared to all the elements previous to it in the array starting from 2nd element &
then the element is inserted at right position among the elements.
*/


void insertion_sort(int a[], int n);




int main() {
	int n;
	cout << "Enter the number of elements in the array: ";
	cin >> n;
	int a[n];
	cout << "Enter the elements: " << endl;
	for(int i=0;i<n;i++) {
		cin >> a[i];
	}
	insertion_sort(a,n);
}

void insertion_sort(int a[], int n) {
	for(int i=1;i<n;i++) {
		//Key choosen
		int key=a[i];
		int j=i-1;
		//Key compared and index also.
		while(j>=0&&a[j]>key) {
			//If key is lesser than array is shifted to right.
			a[j+1]=a[j];
			j--;
		}
		//Finally key is placed at right position among the elements.
		a[j+1]=key;
	}

	cout << "\n\nSorted Array: " << endl;
	for(int i=0;i<n;i++) {
		cout << a[i] << "\t";
	}
}