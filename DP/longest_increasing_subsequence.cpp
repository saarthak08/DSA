#include<iostream>

using namespace std;

int min(int a, int b);


int main() {
	int n;
	cout << "Enter the number of elements: ";
	cin >> n;
	int a[n];
	cout << "Enter the numbers: " << endl;
	for(int i=0;i<n;i++) {
		cin >> a[i];
	}
	int m[n];
	for(int i=0;i<n;i++) {
		m[i]=1;
	}
	for(int i=0;i<n;i++) {
		for(int j=0;j<i;j++) {
			if(a[i]>a[j]) {
				m[i]=max(m[i],m[j]+1);
			}
		}
	}
	int maxx=0;
	cout << "Array: "<<endl;
	for(int i=0;i<n;i++) {
		if(maxx<m[i]) {
			maxx=m[i];
		}
		cout << m[i] <<" ";
	}
	cout << "LIS: " << maxx << endl;
}

int max(int a, int b) {
	return a>b?a:b;
}