#include<iostream>

using namespace std;

int min(int a, int b);

int main() {
	int C,n;
	cout << "Enter the total value to be changed: ";
	cin >> C;
	cout << "Enter the number of denominatons: ";
	cin >> n;
	int w[n+1];
	cout << "Enter the values of denominations: " << endl;
	for(int i=1;i<=n;i++) {
		cin >> w[i];
	}
	int memo[C+1];
	memset(memo,0,C+1);	
	for(int i=1;i<C+1;i++) {
		int min_no=C+1;
		bool flag=false;
		for(int j=1;j<=n;j++) {
			if(w[j]<=i) {
				flag=true;
				min_no = min(min_no,1+memo[i-w[j]]);

			}
		}
		if(flag) {
			memo[i]=min_no;
		}
	}
	cout << "Memo:" <<endl;
	for(int j=0;j<C+1;j++) {
		cout << memo[j] <<"\t";
	} 
	cout << "\nMin: " << memo[C];
	return 0;

}



int min(int a, int b) {
	return a>b?b:a;
}