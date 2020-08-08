#include<iostream>

using namespace std;

int max(int a, int b);

int main() {
	int C,n;
	cout << "Enter capacity (Max Weight): ";
	cin >> C;
	cout << "Enter the number of elements: ";
	cin >> n;
	int w[n];
	int v[n];
	cout << "Enter the weights of the elements: " << endl;
	for(int i=0;i<n;i++) {
		cin >> w[i];
	}
	cout << "Enter the values of the elements: " << endl;
	for(int i=0;i<n;i++) {
		cin >> v[i];
	}
	sort(v+0,v+n-1);
	int memo[n][C+1];
	for(int j=0;j<C+1;j++) {
		memo[0][j]=v[0];
	}
	for(int i=0;i<n;i++) {
		memo[i][0]=0;
	}
	for(int i=1;i<n;i++) {
		for(int j=1;j<C+1;j++) {
			if(v[i]>j) {
				memo[i][j]=memo[i-1][j];
			} else {
				memo[i][j]=max(v[i]+memo[i][j-w[i]],memo[i-1][j]);
			}
		}
	}
	for(int i=0;i<n;i++) {
		for(int j=0;j<C+1;j++) {
			cout << memo[i][j] << "\t";
		}
		cout << endl;
	}
	cout << "Maximum Value Possible: " << memo[n-1][C];
	return 0;
}


int max(int a, int b) {
	return a>b?a:b;
}