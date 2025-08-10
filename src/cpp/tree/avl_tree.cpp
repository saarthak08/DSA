#include <iostream>

using namespace std;

typedef struct node {
  int data;
  node *right_child;
  node *left_child;
  int height;
} node;

int height(node *root);
void balance_tree(node *n, node **root, int val);
void preorder_traversal(node *root);
void right_rotation(node *pre, node *curr, node **root);
void left_rotation(node *pre, node *curr, node **root);
void insert_node(node **root, int val);

int main() {
  node **root;
  int x = 0;
  while (x != 3) {
    cout << "\n\n******* MENU *******" << endl;
    cout << "Press \'1\' to insert a node." << endl;
    cout << "Press \'2\' to print pre-order traversal." << endl;
    cout << "Press \'3\' to exit." << endl;
    cin >> x;
    if (x == 3) {
      break;
    } else if (x == 2) {
      cout << "\nPre-Order Traversal of the Tree: " << endl;
      preorder_traversal(*root);
    } else if (x == 1) {
      int n;
      cout << "Enter the node value to be inserted: ";
      cin >> n;
      insert_node(root, n);
    } else {
      cout << "Invalid Input. Try Again!" << endl;
      continue;
    }
  }
}

void insert_node(node **root, int val) {
  node *ptr = NULL;
  node *curr = NULL;
  curr = *root;
  if (curr == NULL) {
    *root = new node;
    (*root)->data = val;
    (*root)->height = 0;
    return;
  }
  int k = 0;
  while (curr != NULL) {
    ptr = curr;
    k++;
    if (val > curr->data) {
      curr = curr->right_child;
    } else {
      curr = curr->left_child;
    }
  }
  curr = *root;
  while (curr != NULL) {
    int w = k;
    if (k > curr->height) {
      curr->height = k;
    }
    k--;
    if (val > curr->data) {
      curr = curr->right_child;
    } else {
      curr = curr->left_child;
    }
  }
  if (val > ptr->data) {
    node *x = new node;
    x->data = val;
    x->height = 0;
    ptr->right_child = x;
  } else {
    node *x = new node;
    x->data = val;
    x->height = 0;
    ptr->left_child = x;
  }
  balance_tree(*root, root, val);
}

void preorder_traversal(node *root) {
  if (root != NULL) {
    cout << "Data: " << root->data << "\t" << "Height: " << root->height
         << endl;
    preorder_traversal(root->left_child);
    preorder_traversal(root->right_child);
  }
}

int height(node *root) {
  if (root == NULL) {
    return -1;
  }
  int right_height = height(root->right_child);
  int left_height = height(root->left_child);
  if (right_height > left_height) {
    return right_height + 1;
  } else {
    return left_height + 1;
  }
}

void balance_tree(node *n, node **root, int val) {
  if (n != NULL) {
    balance_tree(n->left_child, root, val);
    balance_tree(n->right_child, root, val);
    int right_height = -1, left_height = -1;
    if (n->height > 1) {
      if (n->right_child != NULL) {
        right_height = n->right_child->height;
      }
      if (n->left_child != NULL) {
        left_height = n->left_child->height;
      }
      node *curr = *root;
      node *ptr = NULL;
      while (curr != NULL) {
        if (n->data > curr->data) {
          ptr = curr;
          cout << ptr->data;
          curr = curr->right_child;
        } else if (n->data < curr->data) {
          ptr = curr;
          cout << ptr->data;
          curr = curr->left_child;
        } else if (n->data == curr->data) {
          break;
        }
      }
      int bf = right_height - left_height;
      if (bf > 1) {
        if (val > n->right_child->data) {
          left_rotation(ptr, curr, root);
        } else {
          right_rotation(curr, curr->right_child, root);
          left_rotation(ptr, curr, root);
        }
      } else if (bf < 1) {
        if (val < n->left_child->data) {
          right_rotation(ptr, curr, root);
        } else {
          left_rotation(curr, curr->left_child, root);
          right_rotation(ptr, curr, root);
        }
      }
    }
  }
}

void left_rotation(node *pre, node *curr, node **root) {
  node *temp = curr;
  curr = curr->right_child;
  node *x = curr->left_child;
  curr->left_child = temp;
  temp->right_child = x;
  if (pre == NULL) {
    *root = curr;
  } else {
    if (pre->data > curr->data) {
      pre->left_child = curr;
    } else {
      pre->right_child = curr;
    }
  }
}

void right_rotation(node *pre, node *curr, node **root) {
  node *temp = curr;
  curr = curr->left_child;
  node *x = curr->right_child;
  curr->right_child = temp;
  temp->left_child = x;
  if (pre == NULL) {
    *root = curr;
  } else {
    if (pre->data > curr->data) {
      pre->left_child = curr;
    } else {
      pre->right_child = curr;
    }
  }
}
