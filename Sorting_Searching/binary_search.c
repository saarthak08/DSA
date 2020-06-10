#include <stdio.h>
#include <conio.h>
#define size 100

int binary_search_iterative(int a[], int l, int r, int x);
int binary_search_recursive(int a[], int l, int r, int x);


int main()
{
	int arr[size], i, n, data, index;
	printf("\n Enter the number of elements in the array : ");
	scanf("%d", &n);
	printf("\n Enter the elements of the array in sorted form: ");
	for(i=0;i<n;i++)
	{
		scanf("%d", &arr[i]);
	}
	
	printf("\n The  array is: \n");
	for(i=0;i<n;i++)
		printf(" %d\t", arr[i]);
	
	
	printf("\n Enter the number to be searched in the array : ");
	scanf("%d", &data);
	//index=binary_search_iterative(arr, 0, n-1, data);
	printf("%p\n",binary_search_recursive);
	index = binary_search_recursive(arr, 0, n-1, data);
	if(index !=-1)
	printf("\n The number in the array is at the %d  position", index+1);
	else
	printf("\n The number does not exist in the array : ");
	return 0;
}

int binary_search_iterative(int arr[], int l, int r, int x)
{
	while(l<=r){
		int m = (l + r)/2;
		if(arr[m] == x)
		return m;
		if(arr[m] < x)
		l = m+1;
		else
		r=m-1;
	}
	return -1;
}


int binary_search_recursive(int arr[], int l, int r, int x)
{
	if(l<=r){
		int m = (l + r)/2;
		if(arr[m] == x)
		return m;
		printf("%p\n",binary_search_recursive);
		if(arr[m] < x)
		return binary_search_recursive(arr, m+1, r, x);
		else return binary_search_recursive(arr, l, m-1, x);
	
	}
	return -1;
}

