#include<iostream>
#define ALPHABETS 26

using namespace std;


/*

Trie is an efficient information re-trieval data structure. 
Using Trie, search complexities can be brought to optimal limit (key length). 
If we store keys in binary search tree, a well balanced BST will need time proportional to M * log N, where M is maximum string length and N is number of keys in tree.
Using Trie, we can search the key in O(M) time. 
However the penalty is on Trie storage requirements. 
So, trie enables string searching in O(M) time where M is the string length.

*/


//Structure of a trie.
typedef struct trie_node {
	trie_node* children[ALPHABETS];
	bool isEndofWord;
} trie_node;

trie_node* insert_key(trie_node* root, string key);
void print_trie(trie_node* root, string word);
bool search_in_trie(trie_node* root,string search);
trie_node* delete_key(trie_node* root, string toDelete, int index);
bool isEmpty(trie_node* root);
void delete_nodes(trie_node* node,int index, string toDelete);


int main() {
	trie_node* root=new trie_node;
	cout << "Enter the number of keys you want to insert in trie: ";
	int n;
	cin >> n;
	//Building Trie.
	cout << "Enter the keys: " << endl;
	for(int i=0;i<n;i++) {
		string temp;
		cin >> temp;
		root=insert_key(root,temp);
	}
	cout << "\n\nCurrent Trie: " <<endl;
	print_trie(root,"");
	string search;
	cout << "\nEnter the string you want to search in trie: " <<endl;
	cin >> search;
	if(search_in_trie(root,search)) {
		cout << "String found!" << endl;
	}
	else {
		cout << "String not found!" << endl;
	}
	cout << "\nEnter the string you want to delete in trie: " <<endl;
	cin >> search;
	root=delete_key(root, search,0);
	cout << "\n\nTrie After Deletion: " << endl;
	print_trie(root,"");

}

//Insertion: Time Complexity: O(M).
trie_node* insert_key(trie_node* root, string key) {
	trie_node* node=root;
	int length=key.length();
	for(int i=0;i<length;i++) {
		//Index will denote the children's index in children array.
		int index=key[i]-'a';
		if(node->children[index]==NULL) {
			node->children[index]=new trie_node;
		}
		node=node->children[index];
	}
	//Mark end of word true.
	node->isEndofWord=true;
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


//Search in Trie: Time Complexity: O(M)
bool search_in_trie(trie_node* root,string search) {
	//Search each character at each node.
	for(int i=0;i<search.length();i++) {
		//Character is converted into character.
		int index=search[i]-'a';
		//If it is null, then returned false.
		if(root->children[index]==NULL) {
			return false;
		}
		//Continued.
		root=root->children[index];
	}
	//If all index are not NULL, then at last it is checked whether the last index is marked as end of word or not, if yes then string found, else not found.
	if(root->isEndofWord) {
		return true;
	}
	else {
		return false;
	}
}

//Node is checked whether it is empty or not i.e. its all children are NULL or not.
bool isEmpty(trie_node* root) {
	for(int i=0;i<26;i++) {
		if(root->children[i]!=NULL) {
			return false;
		}
	}
	return true;
}


//Delete: Time Complexity: O(M)
trie_node* delete_key(trie_node* root, string toDelete,int index) {
	if(root==NULL) {
		cout << "Trie already empty!" <<endl;
		return root;
	}
	//last index is checked if its endOfWord, it is unmarked. // Recursion base case.
	if(index==toDelete.length()) {
		if(root->isEndofWord) {
			root->isEndofWord=false;
		}
		//it is checked whether, it is empty or not. If its empty, then only it is deleted.
		if(isEmpty(root)) {
			delete root;
			root=NULL;
		}
		return root;
	}
	//Character is converted into index.
	int x=toDelete[index]-'a';
	//Recursive calls.
	root->children[x]=delete_key(root->children[x],toDelete,index+1);
	//If it is not end of word and it is empty, then only the node is deleted.
	if(!root->isEndofWord&&isEmpty(root)) {
		delete root;
		root=NULL;
	}
	return root;
}





