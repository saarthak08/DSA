#include <stdio.h>
#include <stdlib.h> // For malloc, free

// Node structure for the singly linked list
struct node {
  int data;
  struct node *next;
};

// Function prototypes
struct node *create_ll(struct node *start);
struct node *display(struct node *start);
struct node *insert_beg(struct node *start);
struct node *insert_end(struct node *start);
struct node *insert_before_value(struct node *start); // Renamed for clarity
struct node *insert_after_value(struct node *start);  // Renamed for clarity
struct node *delete_beg(struct node *start);
struct node *delete_end(struct node *start);
struct node *delete_specific_node(struct node *start); // Renamed for clarity
struct node *delete_after_value(struct node *start);   // Renamed for clarity
struct node *delete_list(struct node *start);
struct node *sort_list(struct node *start);

int main() {
  struct node *start = NULL; // Local start pointer for better practice
  int option;

  do {
    printf("\n\n *****MAIN MENU *****");
    printf("\n 1: Create a list");
    printf("\n 2: Display the list");
    printf("\n 3: Add a node at the beginning");
    printf("\n 4: Add a node at the end");
    printf("\n 5: Add a node before a given value");
    printf("\n 6: Add a node after a given value");
    printf("\n 7: Delete a node from the beginning");
    printf("\n 8: Delete a node from the end");
    printf("\n 9: Delete a specific node by value");
    printf("\n 10: Delete a node after a given value");
    printf("\n 11: Delete the entire list");
    printf("\n 12: Sort the list");
    printf("\n 13: EXIT");
    printf("\n\n Enter your option : ");
    scanf("%d", &option);

    switch (option) {
    case 1:
      start = create_ll(start);
      printf("\n LINKED LIST CREATED");
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
      start = insert_before_value(start);
      break;
    case 6:
      start = insert_after_value(start);
      break;
    case 7:
      start = delete_beg(start);
      break;
    case 8:
      start = delete_end(start);
      break;
    case 9:
      start = delete_specific_node(start);
      break;
    case 10:
      start = delete_after_value(start);
      break;
    case 11:
      start = delete_list(start);
      printf("\n LINKED LIST DELETED");
      break;
    case 12:
      start = sort_list(start);
      printf("\n List sorted.");
      break;
    }
  } while (option != 13);

  return 0;
}

/**
 * @brief Creates a singly linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *create_ll(struct node *start) {
  struct node *new_node, *ptr;
  int num;

  printf("\n Enter -1 to end");
  printf("\n Enter the data : ");
  scanf("%d", &num);

  while (num != -1) {
    new_node = (struct node *)malloc(sizeof(struct node));
    if (new_node == NULL) {
      fprintf(stderr, "Memory allocation failed!\n");
      return start; // Return current list state on failure
    }
    new_node->data = num;
    new_node->next = NULL;

    if (start == NULL) {
      start = new_node;
    } else {
      ptr = start;
      while (ptr->next != NULL) {
        ptr = ptr->next;
      }
      ptr->next = new_node;
    }
    printf("\n Enter the data : ");
    scanf("%d", &num);
  }
  return start;
}

/**
 * @brief Displays the elements of the singly linked list.
 * @param start The head of the list.
 * @return The head of the list.
 */
struct node *display(const struct node *start) {
  const struct node *ptr;
  if (start == NULL) {
    printf("\n List is empty.");
    return (struct node *)start;
  }
  ptr = start;
  printf("\n List elements: ");
  while (ptr != NULL) {
    printf("\t %d", ptr->data);
    ptr = ptr->next;
  }
  return (struct node *)start;
}

/**
 * @brief Inserts a new node at the beginning of the singly linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *insert_beg(struct node *start) {
  struct node *new_node;
  int num;

  printf("\n Enter the data : ");
  scanf("%d", &num);

  new_node = (struct node *)malloc(sizeof(struct node));
  if (new_node == NULL) {
    fprintf(stderr, "Memory allocation failed!\n");
    return start; // Return current list state on failure
  }
  new_node->data = num;
  new_node->next = start;
  start = new_node;
  return start;
}

/**
 * @brief Inserts a new node at the end of the singly linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *insert_end(struct node *start) {
  struct node *ptr, *new_node;
  int num;

  printf("\n Enter the data : ");
  scanf("%d", &num);

  new_node = (struct node *)malloc(sizeof(struct node));
  if (new_node == NULL) {
    fprintf(stderr, "Memory allocation failed!\n");
    return start; // Return current list state on failure
  }
  new_node->data = num;
  new_node->next = NULL;

  if (start == NULL) {
    start = new_node;
  } else {
    ptr = start;
    while (ptr->next != NULL) {
      ptr = ptr->next;
    }
    ptr->next = new_node;
  }
  return start;
}

/**
 * @brief Inserts a new node before a given value in the singly linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *insert_before_value(struct node *start) {
  struct node *new_node, *ptr, *preptr;
  int num, val;

  printf("\n Enter the data : ");
  scanf("%d", &num);
  printf("\n Enter the value before which the data has to be inserted : ");
  scanf("%d", &val);

  if (start == NULL) {
    printf("\n List is empty. Cannot insert before %d.\n", val);
    return start;
  }

  new_node = (struct node *)malloc(sizeof(struct node));
  if (new_node == NULL) {
    fprintf(stderr, "Memory allocation failed!\n");
    return start; // Return current list state on failure
  }
  new_node->data = num;

  // If inserting before the first node
  if (start->data == val) {
    new_node->next = start;
    start = new_node;
    return start;
  }

  ptr = start;
  preptr = NULL;
  while (ptr != NULL && ptr->data != val) {
    preptr = ptr;
    ptr = ptr->next;
  }

  if (ptr == NULL) {
    printf("\n Value %d not found in the list.\n", val);
  } else {
    preptr->next = new_node;
    new_node->next = ptr;
  }
  return start;
}

/**
 * @brief Inserts a new node after a given value in the singly linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *insert_after_value(struct node *start) {
  struct node *new_node, *ptr;
  int num, val;

  printf("\n Enter the data : ");
  scanf("%d", &num);
  printf("\n Enter the value after which the data has to be inserted : ");
  scanf("%d", &val);

  if (start == NULL) {
    printf("\n List is empty. Cannot insert after %d.\n", val);
    return start;
  }

  new_node = (struct node *)malloc(sizeof(struct node));
  if (new_node == NULL) {
    fprintf(stderr, "Memory allocation failed!\n");
    return start; // Return current list state on failure
  }
  new_node->data = num;

  ptr = start;
  while (ptr != NULL && ptr->data != val) {
    ptr = ptr->next;
  }

  if (ptr == NULL) {
    printf("\n Value %d not found in the list.\n", val);
  } else {
    new_node->next = ptr->next;
    ptr->next = new_node;
  }
  return start;
}

/**
 * @brief Deletes the node from the beginning of the singly linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *delete_beg(struct node *start) {
  struct node *ptr;
  if (start == NULL) {
    printf("\n List is empty. Cannot delete.\n");
    return start;
  }
  ptr = start;
  start = start->next;
  free(ptr);
  return start;
}

/**
 * @brief Deletes the node from the end of the singly linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *delete_end(struct node *start) {
  struct node *ptr, *preptr;
  if (start == NULL) {
    printf("\n List is empty. Cannot delete.\n");
    return start;
  }
  if (start->next == NULL) { // Only one node
    free(start);
    start = NULL;
    return start;
  }
  ptr = start;
  preptr = NULL;
  while (ptr->next != NULL) {
    preptr = ptr;
    ptr = ptr->next;
  }
  preptr->next = NULL;
  free(ptr);
  return start;
}

/**
 * @brief Deletes a specific node by value from the singly linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *delete_specific_node(struct node *start) {
  struct node *ptr, *preptr;
  int val;

  printf("\n Enter the value of the node which has to be deleted : ");
  scanf("%d", &val);

  if (start == NULL) {
    printf("\n List is empty. Cannot delete.\n");
    return start;
  }

  // If deleting the first node
  if (start->data == val) {
    ptr = start;
    start = start->next;
    free(ptr);
    return start;
  }

  ptr = start;
  preptr = NULL;
  while (ptr != NULL && ptr->data != val) {
    preptr = ptr;
    ptr = ptr->next;
  }

  if (ptr == NULL) {
    printf("\n Value %d not found in the list.\n", val);
  } else {
    preptr->next = ptr->next;
    free(ptr);
  }
  return start;
}

/**
 * @brief Deletes the node after a given value in the singly linked list.
 * @param start The current head of the list.
 * @return The new head of the list.
 */
struct node *delete_after_value(struct node *start) {
  struct node *ptr, *temp;
  int val;

  printf("\n Enter the value after which the node has to be deleted : ");
  scanf("%d", &val);

  if (start == NULL) {
    printf("\n List is empty. Cannot delete.\n");
    return start;
  }

  ptr = start;
  while (ptr != NULL && ptr->data != val) {
    ptr = ptr->next;
  }

  if (ptr == NULL) {
    printf("\n Value %d not found in the list.\n", val);
  } else if (ptr->next == NULL) {
    printf("\n No node after value %d to delete.\n", val);
  } else {
    temp = ptr->next; // Node to be deleted
    ptr->next = temp->next;
    free(temp);
  }
  return start;
}

/**
 * @brief Deletes the entire singly linked list.
 * @param start The current head of the list.
 * @return NULL after deletion.
 */
struct node *delete_list(struct node *start) {
  struct node *ptr = start;
  while (ptr != NULL) {
    struct node *temp = ptr;
    ptr = ptr->next;
    free(temp);
  }
  start = NULL;
  return start;
}

/**
 * @brief Sorts the singly linked list using a bubble sort algorithm.
 * @param start The current head of the list.
 * @return The sorted head of the list.
 */
struct node *sort_list(struct node *start) {
  struct node *ptr1, *ptr2;
  int temp_data;
  if (start == NULL || start->next == NULL) {
    return start; // List is empty or has only one node, already sorted
  }
  ptr1 = start;
  while (ptr1->next != NULL) {
    ptr2 = ptr1->next;
    while (ptr2 != NULL) {
      if (ptr1->data > ptr2->data) {
        temp_data = ptr1->data;
        ptr1->data = ptr2->data;
        ptr2->data = temp_data;
      }
      ptr2 = ptr2->next;
    }
    ptr1 = ptr1->next;
  }
  return start;
}