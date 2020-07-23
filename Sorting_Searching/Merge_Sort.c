#include <stdio.h>
#define size 100

int n;
void merge(int a[], int, int, int);
void merge_sort(int a[],int, int);


/*

Time Complexity: O(nLogn) - All cases.
Space Complexity: O(n)

MergeSort(arr[], l,  r)
If r > l
     1. Find the middle point to divide the array into two halves:  
             middle m = (l+r)/2
     2. Call mergeSort for first half:   
             Call mergeSort(arr, l, m)
     3. Call mergeSort for second half:
             Call mergeSort(arr, m+1, r)
     4. Merge the two halves sorted in step 2 and 3:
             Call merge(arr, l, m, r)

*/


int main()
{
	int arr[size], i;
	printf("\n Enter the number of elements in the array : ");
	scanf("%d", &n);
	printf("\n Enter the elements of the array: ");
	for(i=0;i<n;i++)
	{
		scanf("%d", &arr[i]);
	}
	
	printf("\n The initial array is: \n");
	for(i=0;i<n;i++)
		printf(" %d\t", arr[i]);
	
	merge_sort(arr, 0, n-1);
	printf("\n The sorted array is: \n");
	for(i=0;i<n;i++)
		printf(" %d\t", arr[i]);
	return 0;
}


void merge(int arr[], int beg, int mid, int end)
{
	int i=beg, j=mid+1, index=beg, temp[size], k;
	while((i<=mid) && (j<=end))
	{
		if(arr[i] < arr[j])
		{
			temp[index] = arr[i];
			i++;
		}
		else
		{
		temp[index] = arr[j];
		j++;
		}
		index++;
	}
	if(i>mid)
	{
		while(j<=end)
		{
			temp[index] = arr[j];
			j++;
			index++;
		}
	}
	else
	{
		while(i<=mid)
		{
			temp[index] = arr[i];
			i++;
			index++;
		}
	}
	for(k=beg;k<index;k++)
	arr[k] = temp[k];
}


void merge_sort(int arr[], int beg, int end)
{
	int mid,i,j;
	if(beg<end)
	{
		mid = (beg+end)/2;
		merge_sort(arr, beg, mid);
		merge_sort(arr, mid+1, end);
		merge(arr, beg, mid, end);	
		printf("\n\n\n The sub-array after each iteration is : \n");
		for(i=beg;i<=end;i++)
		printf(" %d\t", arr[i]);
		
		
		printf("\n The array after each iteration is : \n");
		for(j=0;j<n;j++)
		printf(" %d\t", arr[j]);
		
	}
}
