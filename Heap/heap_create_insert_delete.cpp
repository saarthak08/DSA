#include<iostream>


using namespace std;

typedef struct heap {
	int *array;
	int capacity;
	int size;
	int heap_type;  // Min-Heap=0 Max-Heap=1

} heap;

heap* create_heap(heap* h, int capacity, int heap_type);
heap* build_heap(heap* h, int a[], int n);
heap* percolate_down(heap* h, int i);
heap* percolate_up(heap* h, int i);
int left_child_index(heap* h, int i);
int right_child_index(heap* h, int i);
heap* insert_element(heap* h, int element);
heap* delete_element(heap* h, int element);
void print_heap(heap* h);


int main() {
	heap* h;
	int option;
	h=create_heap(h,10,1);
	do
	{
		cout << "\n\n ******MAIN MENU******* " << endl << endl;
		cout << "1. Build heap." << endl;
		cout << "2. Insert element into heap." << endl;
		cout << "3. Delete element from heap." << endl;
		cout << "4. Print heap." << endl;
		cout << "5. Exit" << endl << endl;
		cout << "Enter the option: ";
		cin >> option;
		switch(option) {
			case 1: {
				int n;
				cout << "Enter the number of elements in the heap: ";
				cin >> n;
				int a[n];
				cout << "Enter the elements of the heap: " << endl;
				for(int i=0; i<n; i++) {
					cin >> a[i];
				}
				h=build_heap(h,a,n);
				cout << "Heap Build!" << endl;
				break;
			}
			case 2: {
				int element;
				cout << "Enter the element you want to insert: " << endl;
				cin >> element;
				h=insert_element(h,element);
				break;
			}
			case 3: {
				int element;
				cout << "Enter the index of the element you want to delete: " << endl;
				cin >> element;
				h=delete_element(h, element);
				break;
			}
			case 4: {
				print_heap(h);
				break;
			}
			case 5: {
				break;
			}
			default: {
				cout << "Error! Invalid Input. Please try again!" << endl;
				break;
			}


		}
	}
	while(option!=5);
}

heap* create_heap(heap* h, int capacity, int heap_type) {
	h=new heap;
	h->array=new int[capacity];
	h->heap_type=heap_type;
	h->capacity=capacity;
	return h;
}

heap* build_heap(heap* h, int a[], int n) {
	if(h->capacity<n) {
		h->array=new int[n];
		h->capacity=n;
	}
	for(int i=0;i<n;i++) {
		h->array[i]=a[i];
	}
	h->size=n;
	for(int i=(n-1)/2;i>=0;i--) {
		h=percolate_down(h,i);
	}
	return h;
}

heap* percolate_down(heap* h, int i) {
	int left_child,right_child,index=-1;
	left_child=left_child_index(h,i);
	right_child=right_child_index(h,i);
	if(right_child!=-1&&left_child!=-1) {
		if(h->array[right_child]>h->array[left_child]) {
			index=right_child;
		}
		else {
			index=left_child;
		}
	}
	else {
		if(right_child==-1&&left_child!=-1) {
			index=left_child;
		}
		else if(right_child!=-1&&left_child==-1) {
			index=right_child;
		}
		else {
			index=-1;
		}
	}	
	if(index!=-1) {
		if(h->array[index]>h->array[i]) {
			int temp=h->array[index];
			h->array[index]=h->array[i];
			h->array[i]=temp;
		}
		h=percolate_down(h,index);
	}
	return h;
}

int left_child_index(heap* h, int i) {
	if(((2*i)+1)<=((h->size)-1)) {
		return ((2*i)+1);
	}
	else {
		return -1;
	}
}

int right_child_index(heap* h, int i) {
	if(((2*i)+2)<=((h->size)-1)) {
		return ((2*i)+2);
	}
	else {
		return -1;
	}
}

void print_heap(heap* h) {
	if(h!=NULL&&h->array!=NULL&&h->size>0) {
		for(int i=0;i<h->size;i++) {
			cout << h->array[i] << "\t";
		}
	}
	else {
		cout << "Heap is empty." << endl;
	}
}

heap* delete_element(heap* h, int element) {
	int index=-1,left_child,right_child;
	if(element>=h->size||element<0) {
		cout << "Error! Invalid Index!" << endl;
		return h;
	}
	int temp=h->array[h->size-1];
	h->array[h->size-1]=h->array[element];
	h->array[element]=temp;
	h->size--;
	percolate_down(h,element);
	cout << "Element Deleted" << endl;
	return h;
}

heap* insert_element(heap* h, int element) {
	if(h->size==h->capacity) {
		h->capacity++;
		int* temp=new int[h->capacity];
		for(int i=0;i<h->size;i++) {
			temp[i]=h->array[i];
		}
		h->array=temp;
	}
	h->array[h->size]=element;
	h->size++;
 	h=percolate_up(h,h->size-1);
 	cout << "Element Inserted" << endl;
	return h;
}


heap* percolate_up(heap* h, int i) {
	int parent_index=-1;
	parent_index=(i-1)/2;
	if(parent_index>=0) {
		if(h->array[i]>h->array[parent_index]) {
			int temp=h->array[i];
			h->array[i]=h->array[parent_index];
			h->array[parent_index]=temp;
			percolate_up(h,parent_index);
		}
	}
	return h;
}


