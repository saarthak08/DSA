#include<iostream>
#include<queue>
#include<set>
#include<iterator>
#include<algorithm>

using namespace std;

int main() {


	queue<int> a;
	a.push(1);
	a.push(2);
	a.push(3);
	a.push(4);
	queue<int> temp=a;
	cout << "\n\nQueue: \t" ;
	while(!temp.empty()) {
		cout << temp.front() << "\t";
		temp.pop();
	}


	int b[]={2,5,3,1,4};
	sort(b,b+5);
	cout << "\n\nSorted Array:" << "\t";
	for(int i=0;i<5;i++) {
		cout << b[i] << "\t";
	}


	set<int,less<int> > x;
	x.insert(5);
	x.insert(6);
	x.insert(7);
	x.insert(8);
	x.insert(9);
	set<int,less<int> >::iterator p;
	cout << "\n\nThe set elements are : ";
	for (p = x.begin(); p != x.end(); p++)
	cout << *p << "\t";
	cout << "\nElement found: \t";
	p=find(x.begin(),x.end(),7);
	cout << *p << "\t";


	vector<int> ar;
	ar.push_back(10);
	ar.push_back(11);
	ar.push_back(12);
	vector<int>::iterator ptr;
	cout << "\n\nThe vector elements are : ";
	for (ptr = ar.begin(); ptr != ar.end(); ptr++)
	cout << *ptr << "\t";

	return 0;


}