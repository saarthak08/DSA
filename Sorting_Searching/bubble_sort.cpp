#include<iostream>

using namespace std;


int main() {
	int n;
	cout << "Enter the number of elements in the array: ";
	cin >> n;
	int a[n],d[n];
	cout << "Enter the elements: " << endl;
	for(int i=0;i<n;i++) {
		cin >> a[i];
		d[i]=a[i];
	}
	cout << "\n\nBubble Sort: (Ascending) " << endl;
	for(int i=0;i<n-1;i++) {
		for(int j=0;j<n-i-1;j++) {
			if(a[j]>a[j+1]) {
				int temp=a[j];
				a[j]=a[j+1];
				a[j+1]=temp;
			}
		}
		cout << "\nIteration " << i+1 << endl;
		for(int j=0;j<n;j++) {
			cout << a[j] << "\t";
		}
	}

	cout << "\n\nBubble Sort: (Descending) " << endl;
	for(int i=0;i<n-1;i++) {
		for(int j=n-1;j>i;j--) {
			if(a[j-1]<a[j]) {
				int temp=a[j-1];
				a[j-1]=a[j];
				a[j]=temp;
			}
		}
		cout << "\nIteration " << i+1 << endl;
		for(int j=0;j<n;j++) {
			cout << a[j] << "\t";
		}
	}

}