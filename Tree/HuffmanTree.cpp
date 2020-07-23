#include<iostream>


/*

Push all the characters in ch[] mapped to corresponding frequncy freq[] in priority queue.
To create Huffman Tree, pop two nodes from priority queue.
Assign two popped node from priority queue as left and right child of new node.
Push the new node formed in priority queue.
Repeat all above steps untill size of priority queue becomes 1.
Traverse the only node in the priority queue for each character in ch[] and store the Huffman Code.
To print all the stored Huffman Code for every character in ch[].


*/

using namespace std;

typedef struct node {
	int data;
	node *left_child;
	node *right_child;
} node;


class queue {
	private:
		int capacity;
		node **arr;
		int rear;
		int front;
		int size;
	public:
		queue(int capacity) {
			this->capacity=capacity;
			this->arr=new node*[capacity];
			this->rear=-1;
			this->front=0;
			this->size=0;
		}

		int get_size() {
			return this->size;
		}

		void enqueue(node* x) {
			if(size==capacity) {
				cout << "Overflow!" << endl;
				return;
			}
			rear=(rear+1)%capacity;
			int index=size;
			if(size==0) {	
				index=rear;
			}
			for(int i=0;i<size;i++) {
				if(x->data<=arr[(front+i)%capacity]->data) {
					index=i;
					break;
				}
			}
			if(index!=size) {
				for(int i=size-1;i>=index;i--) {
					node* temp=arr[(front+i)%capacity];
					arr[(front+i+1)%capacity]=temp;
				}
			}
			arr[(front+index)%capacity]=x;
			size++;
		}

		node* dequeue() {
			if(size==0) {
				cout << "Underflow" << endl;
				exit(0);
			}
			node* temp=arr[front];
			size--;
			front=(front+1)%capacity;
			return temp;
		}

		void print() {
			cout << "\nCurrent Queue: " << endl;			
			for(int i=0;i<size;i++) {
				cout << arr[(front+i)%capacity]->data << endl;
			}
		}
		bool is_empty() {
			return this->size==0?true:false;
		}

};

void print_tree(node* root);

void print_huffman_tree(node* root, int path[], int size);

int main() {
	queue x(26);
	string s;
	cout << "Enter a string with lower case alphabets only: " <<endl;
	cin >> s;
	int freq[26];
	for(int i=0;i<26;i++) {
		freq[i]=0;
	}
	for(int i=0;i<s.length();i++) {
		freq[s[i]-'a']+=1;
	}	
	for(int i=0;i<s.length();i++) {
		if(freq[i]>0) {
			node* k=new node;
			k->data=freq[i];
			x.enqueue(k);
		}
	}
	x.print();
	int size=x.get_size();
	node *root;
	while(!x.is_empty()) {
		node* first=x.dequeue();
		node* second=x.dequeue();
	    node* new_node=new node;
		new_node->data=(first->data)+(second->data);
		new_node->right_child=first;
		new_node->left_child=second;
		if(!x.is_empty()) {
			x.enqueue(new_node);
		} else {
			root=new_node;
		}
	}
	int path[size];
	cout << "\n\nHuffman Tree: (Pre-Order Traversal)" <<endl;
	print_tree(root);
	cout << "\nHuffman Codes: " << endl;
	print_huffman_tree(root,path,0);

}


void print_tree(node* root) {
	if(root!=NULL) {
		cout << root->data << endl;
		print_tree(root->left_child);
		print_tree(root->right_child);
	}
}

void print_huffman_tree(node* root, int path[], int size) {
	if(root->left_child==NULL&&root->right_child==NULL) {
		cout << root->data << ": ";
		for(int i=0;i<size;i++) {
			cout << path[i]; 
		}
		cout << endl;
	}
	if(root->left_child!=NULL) {
			path[size]=0;
			size++;
			print_huffman_tree(root->left_child,path,size);
			size--;
	}
	if(root->right_child!=NULL) {
			path[size]=1;
			size++;
			print_huffman_tree(root->right_child,path,size);
			size--;
	}
	
}
