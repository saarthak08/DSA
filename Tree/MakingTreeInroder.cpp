#include<iostream>
#define SIZE 100

using namespace std;

typedef struct Node {
	int data;
	Node* left_child;
	Node* right_child;
} Node;


Node* queue[SIZE];

int front=0;
int rear=-1;
int length=0;


void enqueue(Node* x) {
	if(length==SIZE-1) {
		cout << "\nError! Queue is full" <<endl;
		return;
	}
	rear=(rear+1)%SIZE;
	length++;
	queue[rear]=x;
}

Node* dequeue() {
	if(length==0) {
		cout << "\nError! Queue is already empty" <<endl;
		exit(0);
	}
	length--;
	Node* temp=queue[front];
	front=(front+1)%SIZE;
	return temp;
}

void print_queue() {
	int x=front;
	cout << "\n\n\nCurrent Queue: " <<endl;
	for(;x<=rear;x++) {
		cout << queue[x]->data << "\t";
	}
}

void print_tree(Node* node) {
	if(node!=NULL) {
		cout << node->data << "\t";
		print_tree(node->right_child);
		print_tree(node->left_child);
	}
}


int main() {
	Node *root;
	root=new Node;
	cout << "Enter the root node:" <<endl;
	cin >> root->data;
	enqueue(root);
	while(length!=0) {
		int tempFlag;
		Node* tempNode=dequeue();
		cout << "\n\nPress \'1\' to enter the left child of "<<tempNode->data<<endl;
		cin >> tempFlag;
		if(tempFlag==1) {
			Node* left_node;
			left_node=new Node;
			cout << "\nEnter the left child of "<< tempNode->data << endl;
			cin >> left_node->data;
			tempNode->left_child=left_node;
			enqueue(left_node);
		}
		cout << "\n\nPress \'1\' to enter the right child of "<<tempNode->data << endl;
		cin >> tempFlag;
		if(tempFlag==1) {
			Node* right_node;
			right_node=new Node;
			cout << "\nEnter the right child of "<< tempNode->data << endl;
			cin >> right_node->data;
			tempNode->right_child=right_node;
			enqueue(right_node);
		}
	}
	cout << "\n\nInitial Tree (Pre-Order Traversal): " <<endl;
	print_tree(root);
	return 0;
}


