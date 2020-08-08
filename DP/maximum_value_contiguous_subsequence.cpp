#include<iostream>

using namespace std;

int main() {
	int n;
	cout << "Enter the number of elements: ";
	cin >> n;
	int a[n];
	cout << "Enter the elements: " << endl;
	for(int i=0;i<n;i++) {
		cin >> a[i];
	}
	int m[n];
	memset(m,0,n);
	if(a[0]<0) {
		m[0]=0;
	} else {
		m[0]=a[0];
	}
	for(int i=1;i<n;i++) {
		if(m[i-1]+a[i]<0) {
			m[i]=0;
		} else {
			m[i]=m[i-1]+a[i];
		}
	}

	int max=0;
	for(int i=0;i<n;i++) {
		if(max<=m[i]) {
			max=m[i];
		}
	}
	cout << "Maximum Contagious Sum: " << max;
	
}