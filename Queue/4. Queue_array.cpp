#include <stdio.h>
#include <conio.h>
#define MAX 10 // Changing this value will change length of array

int queue[MAX];
int front = -1, rear = -1;
void insert();
int delete_element(void);
int first_element(void);
int last_element(void);


int main()
{
	int option, val;
	do
	{
		printf("\n\n ***** MAIN MENU *****");
		printf("\n 1. Insert an element");
		printf("\n 2. Delete an element");
		printf("\n 3. First element");
		printf("\n 4. Last Element");
		printf("\n 5. EXIT");
		printf("\n Enter your option : ");
		scanf("%d", &option);
		switch(option)
		{
		case 1:
			insert();
			break;
		case 2:
			val = delete_element();
			if (val != -1)
			printf("\n The number deleted is : %d", val);
			break;
		case 3:
			val = first_element();
			if (val != -1)
			printf("\n The first value in queue is : %d", val);
			break;
		case 4:
			val = last_element();
			if (val != -1)
			printf("\n The last value in queue is : %d", val);
			break;
		
		}
	}while(option != 5);
	getch();
	return 0;
}


void insert()
{
	int num;
	printf("\n Enter the number to be inserted in the queue : ");
	scanf("%d", &num);
	if(rear == MAX-1)
		printf("\n OVERFLOW");
	else if(front == -1 && rear == -1)
		front = rear = 0;
	else
		rear++;
	queue[rear] = num;
}


int delete_element()
{
	int val;
	if(front == -1 || front>rear)
	{
		printf("\n UNDERFLOW");
		return -1;
	}
	else
	{
		val = queue[front];
		front++;
		if(front > rear)
		front = rear = -1;
	return val;
	}
}


int first_element()
{
	if(front==-1 || front>rear)
	{
		printf("\n QUEUE IS EMPTY");
		return -1;
	}
	else
	{
		return queue[front];
	}
}


int last_element()
{
	if(rear==-1 || front>rear)
	{
		printf("\n QUEUE IS EMPTY");
		return -1;
	}
	else
	{
		return queue[rear];
	}
}
