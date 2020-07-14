#include<iostream>

using namespace std;


int* merge(int l, int m, int r, int *arr);
int* merge_sort(int l, int r, int *arr);

int main() {
	int n;
	cout << "Enter the number of elements in the array: ";
	cin >> n;
	int *arr=new int[n];
	cout << "Enter the elements: " << endl;
	for(int i=0;i<n;i++) {
		cin >> arr[i];
	}
	arr=merge_sort(0,n-1,arr);
	cout << "\n\nSorted Array: " << endl;
	for(int i=0;i<n;i++) {
		cout << arr[i] << "\t";
	}
}



int* merge_sort(int l, int r, int* arr) {
	if(l<r) {
		int m=(r+l)/2;
		arr=merge_sort(l,m,arr);
		arr=merge_sort(m+1,r,arr);
		arr=merge(l,m,r,arr);
	}
	return arr;
}

int* merge(int l, int m, int r, int *arr) {
	int x[r-l+1];
	int i=l,j=m+1,k=0;
	while(i<=m||j<=r) {
		if(i<=m&&j<=r) {
			if(arr[i]<=arr[j]) {
				x[k++]=arr[i++];
			}
			else {
				x[k++]=arr[j++];
			}
		}
		else if(i<=m&&j>r) {
			x[k++]=arr[i++];
		}
		else if(j<=r&&i>m) {
			x[k++]=arr[j++];
		}
	}
	for(i=0;i<=(r-l);i++) {
		arr[i+l]=x[i];
	}
	return arr;
}