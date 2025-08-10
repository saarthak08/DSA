#include <iostream>
#define SIZE 100

using namespace std;

typedef struct Node {
  int data;
  Node *left_child;
  Node *right_child;
  int level;
} Node;

Node *queue[SIZE];
int front = 0;
int rear = -1;
int length = 0;

void enqueue(Node *node) {
  if (length == SIZE - 1) {
    cout << "\nQueue overflow!" << endl;
    return;
  }
  length++;
  rear = (rear + 1) % SIZE;
  queue[rear] = node;
}

Node *dequeue() {
  if (length == 0) {
    cout << "\nQueue already empty!" << endl;
    exit(0);
  }
  Node *node = queue[front];
  front = (front + 1) % SIZE;
  length--;
  return node;
}

void print_queue() {
  if (length == 0) {
    cout << "\nQueue empty!" << endl;
    return;
  }
  cout << "\nCurrent Queue: " << endl;
  for (int i = front; i <= rear; i++) {
    cout << queue[i] << "\t";
  }
}

void prefill_tree(Node *root) {
  Node *temp = new Node;
  temp->data = 1;
  root->right_child = temp;
  temp = new Node;
  temp->data = 2;
  root->left_child = temp;
  temp = new Node;
  temp->data = 3;
  root->right_child->right_child = temp;
  temp = new Node;
  temp->data = 7;
  root->right_child->left_child = temp;
  temp = new Node;
  temp->data = 4;
  root->right_child->right_child->left_child = temp;
  temp = new Node;
  temp->data = 2;
  root->left_child->right_child = temp;
  temp = new Node;
  temp->data = 6;
  root->left_child->left_child = temp;
}

void print_tree(Node *root) {
  if (root != NULL) {
    cout << root->data << "\t";
    print_tree(root->left_child);
    print_tree(root->right_child);
  }
}

void levels_diameter(Node *root) {
  enqueue(root);
  int highest_level = 0;
  int levels_count[100];
  for (int i = 0; i < 100; i++) {
    levels_count[i] = 0;
  }
  while (length != 0) {
    Node *temp = dequeue();
    levels_count[temp->level]++;
    if (temp->left_child != NULL) {
      temp->left_child->level = temp->level + 1;
      highest_level = temp->level + 1;
      enqueue(temp->left_child);
    }
    if (temp->right_child != NULL) {
      temp->right_child->level = temp->level + 1;
      highest_level = temp->level + 1;
      enqueue(temp->right_child);
    }
  }
  cout << "\n\n\nNumber of levels in the Tree: " << highest_level << endl;
  cout << "\nNumber of nodes in each level: " << endl;
  int diameter = levels_count[0];
  for (int i = 0; i <= highest_level; i++) {
    cout << "Level " << i << ": " << levels_count[i] << endl;
    if (levels_count[i] > diameter) {
      diameter = levels_count[i];
    }
  }
  cout << "Diameter of the Tree: " << diameter << endl;
}

int main() {
  Node *root;
  root = new Node;
  cout << "Enter the root node: " << endl;
  cin >> root->data;
  prefill_tree(root);
  cout << "\n\n\nTree (Pre-order Traversal) :" << endl;
  print_tree(root);
  levels_diameter(root);
}
