#include<iostream>

using namespace std;

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
		int key=a[i];
		int j=i-1;
		while(j>=0&&a[j]>key) {
			a[j+1]=a[j];
			j--;
		}
		a[j+1]=key;
	}

	cout << "\n\nSorted Array: " << endl;
	for(int i=0;i<n;i++) {
		cout << a[i] << "\t";
	}
}