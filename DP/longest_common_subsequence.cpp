#include<iostream>
#include<string>
#include<algorithm>

using namespace std;

void lcs(string a,string b);

int main() {
	string a,b;
	cout << "Enter first string: ";
	cin >> a;
	cout << "Enter second string: ";
	cin >> b;
	lcs(a,b);
}


void lcs(string a,string b) {
	int i=0,j=0;
	int m=a.length();
	int n=b.length();
	int memo[m+1][n+1];
	for(int i=0;i<=m;i++) {
		for(int j=0;j<=n;j++) {
			memo[i][j]=0;
		}
	}
	for(i=0;i<=m;i++) {
		for(j=0;j<=n;j++) {
			if(i==0||j==0) {
				memo[i][j]=0;
			}
			else if(a[i-1]==b[j-1]) {
				memo[i][j]=memo[i-1][j-1]+1;
			}
			else {
				if(memo[i-1][j]>memo[i][j-1]) {
					memo[i][j]=memo[i-1][j];
				} else {
					memo[i][j]=memo[i][j-1];
				}
			}
		}
	}
	for(int i=0;i<=m;i++) {
		for(int j=0;j<=n;j++) {
			cout<<memo[i][j]<<"\t";
		}
		cout << endl;
	}
	cout << "LCS: " << memo[m][n]<<endl;
	cout << "Sequence: ";
	string c="";
	while(i!=0||j!=0) {
		if(a[i-1]==b[j-1]) {
			c+=a[i-1];
			i=i-1;
			j=j-1;
		} else {
			if(memo[i-1][j]>memo[i][j-1]) {
				i=i-1;
			} else {
				j=j-1;
			}
		}
	}
	reverse(c.begin(),c.end());
	cout << c <<endl;
}

