#include<iostream>
#include<math.h>
#include<vector>
#include<limits>

using namespace std;



void power_set(int input[], int n, vector<int> curr, int **ps, int *i);

int main() {
	int n;
	cout << "Enter the number of numbers you want in array: ";
	cin >> n;
	int arr[n];
	cout << "Enter the numbers: " << endl;
	for(int i=0;i<n;i++) {
		cin >> arr[i];
	}
	vector<int> curr;
	int x=0;
	int *k=&x;
	int **ps=(int **)malloc(((int)pow(2,n))*sizeof(int *));
	for(int i=0;i<(int)pow(2,n);i++) {
		*(ps+i)=(int *) malloc((n+1)*sizeof(int));
	}
	power_set(arr,n,curr,ps,k);
	cout << "The Power Set: "<<endl;
	cout << "0" <<endl;
	for(int i=0;i<(int)pow(2,n);i++) {
		for(int j=0;j<n;j++) {
			if(ps[i][j]==INT_MAX) {
				break;
			}
			cout << ps[i][j] << "\t";
		}
		cout << endl;
	}
}

void power_set(int input[], int n, vector<int> curr, int **ps, int *i) {
	if(n==0) {
		vector<int>::iterator x;
		int j=0;
		for(j=0;j<curr.size();j++) {
			ps[*i][j]=curr[j];
		}
		ps[*i][j]=INT_MAX;
		(*i)+=1;
		return;
	}

	curr.push_back(input[n-1]);


	power_set(input,n-1,curr,ps,i);


	curr.pop_back();

	power_set(input,n-1,curr,ps,i);


}