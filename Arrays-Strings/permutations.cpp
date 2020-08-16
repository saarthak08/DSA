#include<iostream>
#include<string>

using namespace std;

// Permutations of a string are calculated by removing a character from the main string & adding it to prefix string & repeating this step again & again.

// Time Complexity of this algorithm is O(n*n!);

/*
		Input String: "abc";
		Recurssion Tree: 	

									 "abc"
					___________________|____________________
				   |     		       |					|
			  ("bc","a")     	  ("ac","b")			("ab","c")	
		   _____|_____            ____|_____			____|_______
		  |			  |          |          |		   |			|
	 ("ab","c")   ("b","ac")  ("c","ba")  ("a","bc") ("b","ca")    ("a","cb")
		  |			  |			 |			|            |             |
	 ("","abc")	  ("","acb")  ("","bac")   ("","bca") ("","cab")	("","cba")



substr(index,length);  
string x="abc";
x.substr(0,0)="";
x.substr(1,x.length())="bc;
*/


	

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
			//rem+=str.substr(i+1,str.length()-(i+1)); //BOTH ARE SAME;
			rem+=str.substr(i+1,str.length());
			string pre=prefix;
			pre+=str[i];
			perm(rem,pre);
		}
	}

}