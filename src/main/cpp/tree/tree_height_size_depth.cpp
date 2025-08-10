#include <iostream>

using namespace std;

typedef struct Node {
  int data;
  Node *left_child;
  Node *right_child;
} Node;

void build_tree(Node *root);
void print_tree(Node *root);
void prefill_tree(Node *root);
int height_of_tree(Node *root);
int depth_of_a_node(Node *root, Node *node);
int size_of_tree(Node *root);

int main() {
  Node *root;
  root = new Node;
  cout << "Enter the root node: " << endl;
  cin >> root->data;
  prefill_tree(root);
  cout << "\n\n\nTree (Pre-order Traversal) :" << endl;
  print_tree(root);
  cout << "\n\nHeight of Tree: " << endl << height_of_tree(root) << endl;
  cout << "\n\nSize of Tree: " << endl << size_of_tree(root) << endl;
  cout << "Depth of Node 3 :" << endl
       << depth_of_a_node(root, root->right_child->right_child) << endl;
}

void build_tree(Node *root) {
  int temp;
  Node *temp_node;
  if (root != NULL) {
    cout << "Press \'1\' to enter the left child of " << root->data << ": "
         << endl;
    cin >> temp;
    if (temp == 1) {
      temp_node = new Node;
      cout << "Enter the left child of " << root->data << ": " << endl;
      cin >> temp_node->data;
      root->left_child = temp_node;
      build_tree(root);
    }
    cout << "Press \'1\' to enter the right child of " << root->data << ": "
         << endl;
    cin >> temp;
    if (temp == 1) {
      temp_node = new Node;
      cout << "Enter the right child of " << root->data << ": " << endl;
      cin >> temp_node->data;
      root->right_child = temp_node;
      build_tree(root);
    }
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

int height_of_tree(Node *root) {
  int left_height, right_height;
  if (root == NULL) {
    return -1;
  }
  left_height = height_of_tree(root->left_child);
  right_height = height_of_tree(root->right_child);
  if (left_height > right_height) {
    return left_height + 1;
  } else {
    return right_height + 1;
  }
}

int size_of_tree(Node *root) {
  if (root == NULL) {
    return 0;
  }
  return size_of_tree(root->left_child) + size_of_tree(root->right_child) + 1;
}

int depth_of_a_node(Node *root, Node *node) {
  int x, y;
  if (root == node) {
    return 0;
  }
  if (root == NULL || node == NULL) {
    return -1;
  }
  x = depth_of_a_node(root->left_child, node);
  y = depth_of_a_node(root->right_child, node);
  if (x != -1 || y != -1) {
    if (x != -1) {
      return x + 1;
    } else {
      return y + 1;
    }
  } else {
    return -1;
  }
}
