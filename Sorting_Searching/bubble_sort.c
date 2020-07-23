#include <stdio.h>
#define size 100

void bubble_sort(int a[],int);


/*
Time Complexity: O(N^2) - All cases.
But we can improve it by placing a flag inside the swapping condition & setting it inside it.
If the flag is set, then complexity can be reduced to O(n) if array is already sorted.
*/

int main()
{
	int arr[size], i, n;
	printf("\n Enter the number of elements in the array : ");
	scanf("%d", &n);
	printf("\n Enter the elements of the array: ");
	for(i=0;i<n;i++)
	{
		scanf("%d", &arr[i]);
	}
	bubble_sort(arr, n);
	printf("\n The sorted array is: \n");
	for(i=0;i<n;i++)
		printf(" %d\t", arr[i]);
	return 0;
}

void bubble_sort(int data[], int N)
{
	int i,j,k,temp;
	for(i=0; i<N ;i++){
		for(j=0;j<N-i-1;j++){
			if(data[j]>data[j+1]){
				temp=data[j];
				data[j]=data[j+1];
				data[j+1]=temp;
			}
		
		}
		printf("\n\n\n The elements of the array after %d iteration are \n", i+1);
		for(k=0;k<N;k++)
		{
			printf("%d\t ", data[k]);
		}
	}
	
}
