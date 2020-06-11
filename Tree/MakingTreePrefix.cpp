#include<iostream>

using namespace std;

typedef struct Node {
	int data;
	Node *left_child;
	Node *right_child;
} Node;

void insert_data(Node *node);

void print_tree(Node* node);

int main() {
	Node *root=new Node;
	cout << "Enter the root node: " << endl;
	cin >> root->data;
	insert_data(root);
	cout << "\n\nThe pre-order traversal of the tree formed: " << endl;
	print_tree(root);
}


void insert_data(Node* node) {
	int temp;
	cout << "Press \'1\' to enter the left child of " << node->data << endl;
	cin >> temp;
	if(temp==1) {
		Node* x=new Node;
		cout << "Enter the left child: " << endl;
		cin >> x->data;
		node->left_child=x;
		insert_data(x);
	}
	cout << "Press \'1\' to enter the right child of " << node->data << endl;
	cin >> temp;
	if(temp==1) {
		Node* x=new Node;
		cout << "Enter the right child: " << endl;
		cin >> x->data;
		node->right_child=x;
		insert_data(x);
	}
}

void print_tree(Node* node) {
	if(node!=NULL) {
		cout << node->data << "\t";
		print_tree(node->left_child);
		print_tree(node->right_child);
	}
}


