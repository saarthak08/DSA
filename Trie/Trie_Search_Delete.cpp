#include<iostream>
#define ALPHABETS 26

using namespace std;


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

trie_node* insert_key(trie_node* root, string key) {
	trie_node* node=root;
	int length=key.length();
	for(int i=0;i<length;i++) {
		int index=key[i]-'a';
		if(node->children[index]==NULL) {
			node->children[index]=new trie_node;
		}
		node=node->children[index];
	}
	node->isEndofWord=true;
	return root;
}

void print_trie(trie_node* root, string word) {
	if(root!=NULL) {
		if(root->isEndofWord) {
			cout << word << endl;
		}
		for(int i=0;i<26;i++) {
			if(root->children[i]!=NULL) {
				char result=i+'a';
				string x=word;
				x+=result;
				print_trie(root->children[i],x);
			}
		}
	}
}

bool search_in_trie(trie_node* root,string search) {
	for(int i=0;i<search.length();i++) {
		int index=search[i]-'a';
		if(root->children[index]==NULL) {
			return false;
		}
		root=root->children[index];
	}
	if(root->isEndofWord) {
		return true;
	}
	else {
		return false;
	}
}

bool isEmpty(trie_node* root) {
	for(int i=0;i<26;i++) {
		if(root->children[i]!=NULL) {
			return false;
		}
	}
	return true;
}

trie_node* delete_key(trie_node* root, string toDelete,int index) {
	if(root==NULL) {
		cout << "Trie already empty!" <<endl;
		return root;
	}
	if(index==toDelete.length()) {
		if(root->isEndofWord) {
			root->isEndofWord=false;
		}
		if(isEmpty(root)) {
			delete root;
			root=NULL;
		}
		return root;
	}
	int x=toDelete[index]-'a';
	root->children[x]=delete_key(root->children[x],toDelete,index+1);
	if(!root->isEndofWord&&isEmpty(root)) {
		delete root;
		root=NULL;
	}
	return root;
}





