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
	int memo[C+1];
	memo[0]=0;
	for(int i=1;i<C+1;i++) {
		int max_no=0;
		for(int j=0;j<n;j++) {
			if(w[j]<=i) {
				max_no = max(max_no,v[j]+memo[i-w[j]]);
			}
		}
		memo[i]=max_no;
	}
	
	cout << "Max: " << memo[C];
	return 0;

}



int max(int a, int b) {
	return a>b?a:b;
}