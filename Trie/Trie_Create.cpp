#include<iostream>
#define ALPHABETS 26

using namespace std;

typedef struct trie_node {
	trie_node* children[ALPHABETS];
	bool isEndOfWord;
} trie_node;


trie_node* insert_key(trie_node* root, string key);
void print_trie(trie_node* root,string word);

int main() {
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


trie_node* insert_key(trie_node* root, string key) {
	trie_node* node=root;
	int length=key.length();
	int index;
	for(int i=0;i<length;i++) {
		index=key[i]-'a';
		if(node->children[index]==NULL) {
			node->children[index]=new trie_node;
		}
		node=node->children[index];
	}
	node->isEndOfWord=true;
	return root;
}

void print_trie(trie_node* root, string word) {
	if(root!=NULL) {
		if(root->isEndOfWord) {
			cout << word << endl;
		}
		for(int i=0;i<ALPHABETS;i++) {
			if(root->children[i]!=NULL) {
				int index=i+'a';
				char result=(char)index;
				string x=word;
				x+=result;
				print_trie(root->children[i],x);
			}
		}
	}
} 


