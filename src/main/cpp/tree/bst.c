#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

struct node {
  int data;
  struct node *left;
  struct node *right;
};

struct node *insertElement(struct node *tree, int element);
void preorderTraversal(struct node *tree);
void inorderTraversal(struct node *tree);
void postorderTraversal(struct node *tree);
struct node *findSmallestElement(struct node *tree);
struct node *findLargestElement(struct node *tree);
struct node *deleteElement(struct node *tree, int element);
struct node *mirrorImage(struct node *tree);
int totalNodes(struct node *tree);
int totalExternalNodes(struct node *tree);
int totalInternalNodes(struct node *tree);
int Height(struct node *tree);
struct node *deleteTree(struct node *tree);
struct node *inorderSuccessor(struct node *root, struct node *current,
                              struct node *successor);

int main() {
  struct node *tree = NULL;
  int option, val, data;
  struct node *ptr;
  do {
    printf("\n\n ******MAIN MENU******* \n");
    printf("\n 1. Insert Element");
    printf("\n 2. Preorder Traversal");
    printf("\n 3. Inorder Traversal");
    printf("\n 4. Postorder Traversal");
    printf("\n 5. Find the smallest element");
    printf("\n 6. Find the largest element");
    printf("\n 7. Delete an element");
    printf("\n 8. Count the total number of nodes");
    printf("\n 9. Count the total number of external nodes");
    printf("\n 10. Count the total number of internal nodes");
    printf("\n 11. Determine the height of the tree");
    printf("\n 12. Find the mirror image of the tree");
    printf("\n 13. Delete the tree");
    printf("\n 14. Exit");
    printf("\n\n Enter your option : ");
    scanf("%d", &option);
    switch (option) {
    case 1:
      printf("\nEnter the value of the new node : ");
      scanf("%d", &val);
      tree = insertElement(tree, val);
      break;
    case 2:
      printf("\nThe elements of the tree are : \n");
      preorderTraversal(tree);
      break;
    case 3:
      printf("\nThe elements of the tree are : \n");
      inorderTraversal(tree);
      break;
    case 4:
      printf("\nThe elements of the tree are : \n");
      postorderTraversal(tree);
      break;
    case 5:
      ptr = findSmallestElement(tree);
      printf("\nSmallest element is :%d", ptr->data);
      break;
    case 6:
      ptr = findLargestElement(tree);
      printf("\nLargest element is : %d", ptr->data);
      break;
    case 7:
      printf("\nEnter the element to be deleted : ");
      scanf("%d", &val);
      tree = deleteElement(tree, val);
      break;
    case 8:
      printf("\nTotal no. of nodes = %d", totalNodes(tree));
      break;
    case 9:
      printf("\nTotal no. of external nodes = %d", totalExternalNodes(tree));
      break;
    case 10:
      printf("\nTotal no. of internal nodes = %d", totalInternalNodes(tree));
      break;
    case 11:
      printf("\nThe height of the tree = %d", Height(tree));
      break;
    case 12:
      tree = mirrorImage(tree);
      printf("\nTree Mirrored! Please print the tree to view it.\n");
      break;
    case 13:
      tree = deleteTree(tree);
      break;
    }
  } while (option != 14);
  return 0;
}

struct node *insertElement(struct node *tree, int element) {
  if (tree == NULL) {
    tree = (struct node *)malloc(sizeof(struct node));
    tree->data = element;
    return tree;
  }
  if (element >= tree->data) {
    if (tree->right == NULL) {
      struct node *new_node = (struct node *)malloc(sizeof(struct node));
      new_node->data = element;
      tree->right = new_node;
      return tree;
    } else {
      insertElement(tree->right, element);
      return tree;
    }
  } else {
    if (tree->left == NULL) {
      struct node *new_node = (struct node *)malloc(sizeof(struct node));
      new_node->data = element;
      tree->left = new_node;
      return tree;
    } else {
      insertElement(tree->left, element);
      return tree;
    }
  }
}

struct node *deleteElement(struct node *tree, int element) {
  struct node *pre_ptr, *curr, *ptr, *succ;
  curr = tree;
  if (curr == NULL) {
    printf("\nTree Empty!\n");
    return tree;
  }
  while (curr != NULL) {
    if (element == curr->data) {
      break;
    } else if (element > curr->data) {
      curr = curr->right;
    } else {
      curr = curr->left;
    }
  }
  if (curr == NULL) {
    printf("\nNode not found in the tree.\n");
    return tree;
  }
  ptr = curr;
  curr = tree;
  while (curr != NULL) {
    if (curr->left == ptr || curr->right == ptr) {
      pre_ptr = curr;
      break;
    } else if (element >= curr->data) {
      curr = curr->right;
    } else {
      curr = curr->left;
    }
  }
  if (ptr->left == NULL && ptr->right == NULL) {
    if (ptr == tree) {
      tree = NULL;
    } else {
      if (pre_ptr->left == ptr) {
        pre_ptr->left = NULL;
      } else {
        pre_ptr->right = NULL;
      }
    }
    free(ptr);
    return tree;
  } else if (ptr->left == NULL && ptr->right != NULL) {
    if (pre_ptr->left == ptr) {
      pre_ptr->left = ptr->right;
    } else {
      pre_ptr->right = ptr->right;
    }
    free(ptr);
  } else if (ptr->left != NULL && ptr->right == NULL) {
    if (pre_ptr->left == ptr) {
      pre_ptr->left = ptr->left;
    } else {
      pre_ptr->right = ptr->left;
    }
    free(ptr);
  } else {
    struct node *temp = (struct node *)malloc(sizeof(struct node));
    temp->data = INT_MAX;
    temp = inorderSuccessor(ptr, ptr, temp);
    ptr->data = temp->data;
    curr = tree;
    while (curr != NULL) {
      if (curr->left == temp) {
        curr->left = NULL;
        break;
      } else if (curr->right == temp) {
        curr->right = NULL;
      }
    }
    free(temp);
  }
  printf("\nNode deleted!");
  return tree;
}

void preorderTraversal(struct node *tree) {
  if (tree != NULL) {
    printf("%d\t", tree->data);
    preorderTraversal(tree->left);
    preorderTraversal(tree->right);
  }
}

void inorderTraversal(struct node *tree) {
  if (tree != NULL) {
    inorderTraversal(tree->left);
    printf("%d\t", tree->data);
    inorderTraversal(tree->right);
  }
}

void postorderTraversal(struct node *tree) {
  if (tree != NULL) {
    postorderTraversal(tree->left);
    postorderTraversal(tree->right);
    printf("%d\t", tree->data);
  }
}

struct node *findSmallestElement(struct node *tree) {
  if ((tree == NULL) || (tree->left == NULL)) {
    return tree;
  } else {
    return findSmallestElement(tree->left);
  }
}

struct node *findLargestElement(struct node *tree) {
  if ((tree == NULL) || (tree->right == NULL)) {
    return tree;
  } else {
    return findLargestElement(tree->right);
  }
}

int totalNodes(struct node *tree) {
  if (tree == NULL) {
    return 0;
  } else {
    return (totalNodes(tree->left) + totalNodes(tree->right) + 1);
  }
}

int totalExternalNodes(struct node *tree) {
  if (tree == NULL) {
    return 0;
  } else if ((tree->left == NULL) && (tree->right == NULL)) {
    return 1;
  } else {
    return (totalExternalNodes(tree->left) + totalExternalNodes(tree->right));
  }
}

int totalInternalNodes(struct node *tree) {
  if ((tree == NULL) || ((tree->left == NULL) && (tree->right == NULL))) {
    return 0;
  } else {
    return (totalInternalNodes(tree->left) + totalInternalNodes(tree->right) +
            1);
  }
}

int Height(struct node *tree) {
  int leftheight, rightheight;
  if (tree == NULL) {
    return 0;
  } else {
    leftheight = Height(tree->left);
    rightheight = Height(tree->right);
    if (leftheight > rightheight) {
      return (leftheight + 1);
    } else {
      return (rightheight + 1);
    }
  }
}

struct node *mirrorImage(struct node *tree) {
  struct node *ptr;
  if (tree != NULL) {
    mirrorImage(tree->left);
    mirrorImage(tree->right);
    ptr = tree->left;
    ptr->left = ptr->right;
    tree->right = ptr;
  }
  return tree;
}

struct node *deleteTree(struct node *tree) {
  if (tree != NULL) {
    deleteTree(tree->left);
    deleteTree(tree->right);
    printf("\nDeleting Node: %d", tree->data);
    free(tree);
    tree = NULL;
  }
  return tree;
}

struct node *inorderSuccessor(struct node *root, struct node *current,
                              struct node *successor) {
  if (current != NULL) {
    successor = inorderSuccessor(root, current->left, successor);
    if (successor->data > current->data && current->data > root->data) {
      successor = current;
    }
    successor = inorderSuccessor(root, current->right, successor);
    return successor;
  } else {
    return successor;
  }
}
