#include <iostream>

using namespace std;

// Heaps are complete binary tree which is of two types: Max Heap and Min Heap.
/*
Max-Heap: In a Max-Heap the key present at the root node must be greatest among the keys present at all of it’s children. The same property must be recursively true for all sub-trees in that Binary Tree.
Min-Heap: In a Min-Heap the key present at the root node must be minimum among the keys present at all of it’s children. The same property must be recursively true for all sub-trees in that Binary Tree.

Heapify means percolateDown or percolateUp.

Percolate Down: Time Complexity: O(logN)
Approach: For current index, find left child and right child, then compare which child is bigger, if the bigger child is bigger with parent, then swap child and parent values.
		  Repeat the same process for all the index upto the down by updating index recursively. The children of an index i will be 2i+1 and 2i+2.
Percolate Up is just opposite of it. That is, for current index, we find the parent of the index. i.e. (i-1)/2. Time complexity: O(logn).
Whenever we precolateDown an element, we have to be sure that elements below the current element are already heapified.
Only then, precolatingDown will heapify that element. Same for percolateUp. If we are percolatingUp, we have to be sure that
the elements above are already heapified.

Build Heap: Time Complexity: O(n)
Approach: Take input in an array & then, heapify (percolateDown) all elements by moving from bottom to top except leaf nodes.

DeleteElement: Time Complexity: O(logN)
Approach: Replace the element to be deleted with the last element and then reduce the size of array & heapify (percolateDown) from the index where the element was deleted/replaced.

Insert Element: Time Complexity: O(logN)
Approach: Insert the element at the last and then heapify (percolate up).

Note: Why does buildHeap takes O(n) time complexity why not O(nlogn) if we are taking n elements and heapifying every element?
Ans: The number of operations required for percolateDown and percolateUp is proportional to the distance the node may have to move.
For percolateDown, it is the distance to the bottom of the tree, so percolateDown is expensive for nodes at the top of the tree.
With percolateUp, the work is proportional to the distance to the top of the tree, so percolateUp is expensive for nodes at the bottom of the tree.
Although both operations are O(log n) in the worst case, in a heap, only one node is at the top whereas half the nodes lie in the bottom layer.
So it shouldn't be too surprising that if we have to apply an operation to every node, we would prefer percolateDown over percolateUp.
The buildHeap function takes an array of unsorted items and moves them until they all satisfy the heap property, thereby producing a valid heap.
There are two approaches one might take for buildHeap using the percolateUp and percolateDown operations we've described.
Start at the top of the heap (the beginning of the array) and call percolateUp on each item.
At each step, the previously percolated items (the items before the current item in the array) form a valid heap,
and percolating the next item up places it into a valid position in the heap.
After percolating up each node, all items satisfy the heap property.
Or, go in the opposite direction: start at the end of the array and move backwards towards the front.
At each iteration, you percolate an item down until it is in the correct location.
Now, second option is better here since all the leaf nodes (which are approximately half of the tree nodes) are already heapified.
So, we can percolate down from a level above leaf nodes and heapify them down.

Heap Sort: Time Complexity: O(nLogn)
Approach: Build a heap and then, remove the first element of the heap by placing it at the last of the array and then reduce the size of heap & heapify (percolateDown).
*/

typedef struct heap
{
	int *array;
	int capacity;
	int size;
	int heap_type; // Min-Heap=0 Max-Heap=1

} heap;

heap *create_heap(heap *h, int capacity, int heap_type);
heap *build_heap(heap *h, int a[], int n);
heap *percolate_down(heap *h, int i);
heap *percolate_up(heap *h, int i);
int left_child_index(heap *h, int i);
int right_child_index(heap *h, int i);
heap *insert_element(heap *h, int element);
heap *delete_element(heap *h, int element);
void print_heap(heap *h);

int main()
{
	heap *h;
	int option;
	h = create_heap(h, 10, 1);
	do
	{
		cout << "\n\n ******MAIN MENU******* " << endl
			 << endl;
		cout << "1. Build heap." << endl;
		cout << "2. Insert element into heap." << endl;
		cout << "3. Delete element from heap." << endl;
		cout << "4. Print heap." << endl;
		cout << "5. Exit" << endl
			 << endl;
		cout << "Enter the option: ";
		cin >> option;
		switch (option)
		{
		case 1:
		{
			int n;
			cout << "Enter the number of elements in the heap: ";
			cin >> n;
			int a[n];
			cout << "Enter the elements of the heap: " << endl;
			for (int i = 0; i < n; i++)
			{
				cin >> a[i];
			}
			h = build_heap(h, a, n);
			cout << "Heap Build!" << endl;
			break;
		}
		case 2:
		{
			int element;
			cout << "Enter the element you want to insert: " << endl;
			cin >> element;
			h = insert_element(h, element);
			break;
		}
		case 3:
		{
			int element;
			cout << "Enter the index of the element you want to delete: " << endl;
			cin >> element;
			h = delete_element(h, element);
			break;
		}
		case 4:
		{
			print_heap(h);
			break;
		}
		case 5:
		{
			break;
		}
		default:
		{
			cout << "Error! Invalid Input. Please try again!" << endl;
			break;
		}
		}
	} while (option != 5);
}

heap *create_heap(heap *h, int capacity, int heap_type)
{
	h = new heap;
	h->array = new int[capacity];
	h->heap_type = heap_type;
	h->capacity = capacity;
	return h;
}

heap *build_heap(heap *h, int a[], int n)
{
	if (h->capacity < n)
	{
		h->array = new int[n];
		h->capacity = n;
	}
	for (int i = 0; i < n; i++)
	{
		h->array[i] = a[i];
	}
	h->size = n;
	for (int i = (n - 1) / 2; i >= 0; i--)
	{
		h = percolate_down(h, i);
	}
	return h;
}

heap *percolate_down(heap *h, int i)
{
	int left_child, right_child, index = -1;
	left_child = left_child_index(h, i);
	right_child = right_child_index(h, i);
	if (right_child != -1 && left_child != -1)
	{
		if (h->array[right_child] > h->array[left_child])
		{
			index = right_child;
		}
		else
		{
			index = left_child;
		}
	}
	else
	{
		if (right_child == -1 && left_child != -1)
		{
			index = left_child;
		}
		else if (right_child != -1 && left_child == -1)
		{
			index = right_child;
		}
		else
		{
			index = -1;
		}
	}
	if (index != -1)
	{
		if (h->array[index] > h->array[i])
		{
			int temp = h->array[index];
			h->array[index] = h->array[i];
			h->array[i] = temp;
		}
		h = percolate_down(h, index);
	}
	return h;
}

int left_child_index(heap *h, int i)
{
	if (((2 * i) + 1) <= ((h->size) - 1))
	{
		return ((2 * i) + 1);
	}
	else
	{
		return -1;
	}
}

int right_child_index(heap *h, int i)
{
	if (((2 * i) + 2) <= ((h->size) - 1))
	{
		return ((2 * i) + 2);
	}
	else
	{
		return -1;
	}
}

void print_heap(heap *h)
{
	if (h != NULL && h->array != NULL && h->size > 0)
	{
		for (int i = 0; i < h->size; i++)
		{
			cout << h->array[i] << "\t";
		}
	}
	else
	{
		cout << "Heap is empty." << endl;
	}
}

heap *delete_element(heap *h, int element)
{
	int index = -1, left_child, right_child;
	if (element >= h->size || element < 0)
	{
		cout << "Error! Invalid Index!" << endl;
		return h;
	}
	int temp = h->array[h->size - 1];
	h->array[h->size - 1] = h->array[element];
	h->array[element] = temp;
	h->size--;
	percolate_down(h, element);
	cout << "Element Deleted" << endl;
	return h;
}

heap *insert_element(heap *h, int element)
{
	if (h->size == h->capacity)
	{
		h->capacity++;
		int *temp = new int[h->capacity];
		for (int i = 0; i < h->size; i++)
		{
			temp[i] = h->array[i];
		}
		h->array = temp;
	}
	h->array[h->size] = element;
	h->size++;
	h = percolate_up(h, h->size - 1);
	cout << "Element Inserted" << endl;
	return h;
}

heap *percolate_up(heap *h, int i)
{
	int parent_index = -1;
	parent_index = (i - 1) / 2;
	if (parent_index >= 0)
	{
		if (h->array[i] > h->array[parent_index])
		{
			int temp = h->array[i];
			h->array[i] = h->array[parent_index];
			h->array[parent_index] = temp;
			percolate_up(h, parent_index);
		}
	}
	return h;
}
