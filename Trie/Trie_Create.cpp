#include<iostream>
#define ALPHABETS 26

/*
Trie is an efficient information re-trieval data structure. 
Using Trie, search complexities can be brought to optimal limit (key length). 
If we store keys in binary search tree, a well balanced BST will need time proportional to M * log N, where M is maximum string length and N is number of keys in tree.
Using Trie, we can search the key in O(M) time. 
However the penalty is on Trie storage requirements. 
So, trie enables string searching in O(M) time where M is the string length.
*/

using namespace std;

// Trie Structure.
typedef struct trie_node {
	//All children
	trie_node* children[ALPHABETS];
	//for marking the end of word.
	bool isEndOfWord;
} trie_node;


trie_node* insert_key(trie_node* root, string key);
void print_trie(trie_node* root,string word);

int main() {
	//Initialised root node.
	trie_node *root=new trie_node;
	int n;
	cout << "Enter the number of keys you want to insert to the trie: ";
	cin >> n;
	string key[n];
	cout << "Enter the keys: " <<endl;
	for(int i=0;i<n;i++) {
		cin >> key[i];
		root=insert_key(root,key[i]);
	}
	cout << "\n\nCurrent Trie: " <<endl;
	print_trie(root,"");
}

//Insertion: Time Complexity: O(M).
trie_node* insert_key(trie_node* root, string key) {
	trie_node* node=root;
	int length=key.length();
	int index;
	for(int i=0;i<length;i++) {
		//Index will denote the children's index in children array.
		index=key[i]-'a';
		if(node->children[index]==NULL) {
			node->children[index]=new trie_node;
		}
		node=node->children[index];
	}
	//Mark end of word true.
	node->isEndOfWord=true;
	return root;
}


//Print Trie
void print_trie(trie_node* root, string word) {
	if(root!=NULL) {
		//Print if end of word is achieved.
		if(root->isEndOfWord) {
			cout << word << endl;
		}
		for(int i=0;i<ALPHABETS;i++) {
			if(root->children[i]!=NULL) {
				int index=i+'a';
				//Index is converted into alphabet.
				char result=(char)index;
				//Added to string
				string x=word;
				x+=result;
				//Recursive call.
				print_trie(root->children[i],x);
			}
		}
	}
} 


