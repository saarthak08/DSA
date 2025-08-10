#include <stdio.h>
#include <stdlib.h> // For malloc, free

// Node structure for the circular linked list
struct node {
  int data;
  struct node *next;
};

// Function prototypes
struct node *create_cll(struct node *start);
struct node *display(struct node *start);
struct node *insert_beg(struct node *start);
struct node *insert_end(struct node *start);
struct node *delete_beg(struct node *start);
struct node *delete_end(struct node *start);
struct node *delete_after_value(struct node *start); // Renamed for clarity
struct node *delete_list(struct node *start);

int main() {
  struct node *start = NULL; // Local start pointer for better practice
  int option;
  do {
    printf("\n\n *****MAIN MENU *****");
    printf("\n 1: Create a list");
    printf("\n 2: Display the list");
    printf("\n 3: Add a node at the beginning");
    printf("\n 4: Add a node at the end");
    printf("\n 5: Delete a node from the beginning");
    printf("\n 6: Delete a node from the end");
    printf("\n 7: Delete a node after a given value");
    printf("\n 8: Delete the entire list");
    printf("\n 9: EXIT");
    printf("\n\n Enter your option : ");
    scanf("%d", &option);
    switch (option) {
    case 1:
      start = create_cll(start);
      printf("\n CIRCULAR LINKED LIST CREATED");
      break;
    case 2:
      start = display(start);
      break;
    case 3:
      start = insert_beg(start);
      break;
    case 4:
      start = insert_end(start);
      break;
    case 5:
      start = delete_beg(start);
      break;
    case 6:
      start = delete_end(start);
      break;
    case 7:
      start = delete_after_value(start);
      break;
    case 8:
      start = delete_list(start);
      printf("\n CIRCULAR LINKED LIST DELETED");
      break;
    }
  } while (option != 9);
  return 0;
}

/**
 * @brief Creates a circular singly linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *create_cll(struct node *start) {
  struct node *new_node, *ptr;
  int num;
  printf("\n Enter -1 to end");
  printf("\n Enter the data : ");
  scanf("%d", &num);
  while (num != -1) {
    new_node = (struct node *)malloc(sizeof(struct node));
    if (new_node == NULL) {
      printf("Memory allocation failed!\n");
      exit(1);
    }
    new_node->data = num;
    if (start == NULL) {
      new_node->next = new_node; // Points to itself for a single node
      start = new_node;
    } else {
      ptr = start;
      while (ptr->next != start)
        ptr = ptr->next;
      ptr->next = new_node;
      new_node->next = start;
    }
    printf("\n Enter the data : ");
    scanf("%d", &num);
  }
  return start;
}

/**
 * @brief Displays the elements of the circular linked list.
 * @param start The head of the list.
 * @return The head of the list.
 */
struct node *display(struct node *start) {
  struct node *ptr;
  if (start == NULL) {
    printf("\n List is empty.");
    return start;
  }
  ptr = start;
  printf("\n List elements: ");
  do {
    printf("\t %d", ptr->data);
    ptr = ptr->next;
  } while (ptr != start);
  return start;
}

/**
 * @brief Inserts a new node at the beginning of the circular linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *insert_beg(struct node *start) {
  struct node *new_node, *ptr;
  int num;
  printf("\n Enter the data : ");
  scanf("%d", &num);
  new_node = (struct node *)malloc(sizeof(struct node));
  if (new_node == NULL) {
    printf("Memory allocation failed!\n");
    exit(1);
  }
  new_node->data = num;
  if (start == NULL) {
    new_node->next = new_node;
    start = new_node;
  } else {
    ptr = start;
    while (ptr->next != start)
      ptr = ptr->next;
    ptr->next = new_node;
    new_node->next = start;
    start = new_node;
  }
  return start;
}

/**
 * @brief Inserts a new node at the end of the circular linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *insert_end(struct node *start) {
  struct node *new_node, *ptr;
  int num;
  printf("\n Enter the data : ");
  scanf("%d", &num);
  new_node = (struct node *)malloc(sizeof(struct node));
  if (new_node == NULL) {
    printf("Memory allocation failed!\n");
    exit(1);
  }
  new_node->data = num;
  if (start == NULL) {
    new_node->next = new_node;
    start = new_node;
  } else {
    ptr = start;
    while (ptr->next != start)
      ptr = ptr->next;
    ptr->next = new_node;
    new_node->next = start;
  }
  return start;
}

/**
 * @brief Deletes the node from the beginning of the circular linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *delete_beg(struct node *start) {
  struct node *ptr;
  if (start == NULL) {
    printf("\n List is empty. Cannot delete.");
    return start;
  }
  if (start->next == start) { // Only one node
    free(start);
    start = NULL;
    return start;
  }
  ptr = start;
  while (ptr->next != start)
    ptr = ptr->next;
  ptr->next = start->next;
  free(start);
  start = ptr->next;
  return start;
}

/**
 * @brief Deletes the node from the end of the circular linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *delete_end(struct node *start) {
  struct node *ptr, *preptr;
  if (start == NULL) {
    printf("\n List is empty. Cannot delete.");
    return start;
  }
  if (start->next == start) { // Only one node
    free(start);
    start = NULL;
    return start;
  }
  ptr = start;
  preptr = NULL;
  while (ptr->next != start) {
    preptr = ptr;
    ptr = ptr->next;
  }
  preptr->next = ptr->next; // preptr->next = start
  free(ptr);
  return start;
}

/**
 * @brief Deletes the node after a given value in the circular linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *delete_after_value(struct node *start) {
  struct node *ptr, *preptr;
  int val;
  printf("\n Enter the value after which the node has to be deleted : ");
  scanf("%d", &val);

  if (start == NULL) {
    printf("\n List is empty. Cannot delete.");
    return start;
  }

  ptr = start;
  preptr = NULL;
  int found = 0;
  // Find the node with the given value
  do {
    if (ptr->data == val) {
      found = 1;
      break;
    }
    preptr = ptr;
    ptr = ptr->next;
  } while (ptr != start);

  if (!found) {
    printf("\n Value %d not found in the list.", val);
    return start;
  }

  // If the found node is the last node, there's no node after it to delete
  if (ptr->next == start && ptr == start) { // Single node list
    printf("\n Cannot delete after the only node in the list.");
    return start;
  }
  if (ptr->next == start) { // Found node is the last node in a multi-node list
    printf("\n No node after value %d to delete.");
    return start;
  }

  // Node to be deleted is ptr->next
  struct node *node_to_delete = ptr->next;
  ptr->next = node_to_delete->next;
  free(node_to_delete);

  return start;
}

/**
 * @brief Deletes the entire circular linked list.
 * @param start The current head of the list.
 * @return NULL after deletion.
 */
struct node *delete_list(struct node *start) {
  struct node *ptr;
  if (start == NULL) {
    printf("\n List is already empty.");
    return start;
  }
  ptr = start->next; // Start from the second node
  while (ptr != start) {
    struct node *temp = ptr;
    ptr = ptr->next;
    free(temp);
  }
  free(start); // Free the last remaining node (original start)
  start = NULL;
  return start;
}