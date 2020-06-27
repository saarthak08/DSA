#include<iostream>
#include<string>

using namespace std;

void perm(string str, string prefix);

int main() {
	string input_string;
	cout << "Enter a string: ";
	cin >> input_string;
	perm(input_string,"");
	return 0;
}


void perm(string str, string prefix) {
	if(str.length()==0) {
		cout << prefix << endl;
	} else {
		for(int i=0; i<str.length();i++) {
			string rem=str.substr(0,i);
			rem+=str.substr(i+1,str.length());
			string pre=prefix;
			pre+=str[i];
			perm(rem,pre);
		}
	}

}