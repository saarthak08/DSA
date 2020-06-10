#include <stdio.h>
#include <malloc.h>
#include <conio.h>

//Min Priority Queue

struct node
{
int data;
int priority;
struct node *next;
};

struct node *start=NULL;
struct node *insert(struct node *);
struct node *delete(struct node *);
void Min_element(struct node *);


int main()
{
	int option;
	do
	{
		printf("\n *****MAIN MENU*****");
		printf("\n 1. INSERT");
		printf("\n 2. DELETE");
		printf("\n 3. MIN Element");
		printf("\n 4. EXIT");
		printf("\n Enter your option : ");
		scanf( "%d", &option);
		switch(option)
		{
			case 1:
				start=insert(start);
				break;
			case 2:
				start = delete(start);
				break;
			case 3:
				Min_element(start);
				break;
		}
	}while(option!=4);
}


struct node *insert(struct node *start)
{
	int val, pri;
	struct node *ptr, *p;
	ptr = (struct node *)malloc(sizeof(struct node));
	printf("\n Enter the value and its priority : " );
	scanf( "%d %d", &val, &pri);
	ptr->data = val;
	ptr->priority = pri;
	if(start==NULL || pri < start->priority )
	{
		ptr->next = start;
		start = ptr;
	}
	else
	{
		p = start;
		while(p->next != NULL && p->next->priority <= pri)
		p = p->next;
		ptr->next = p->next;
		p->next = ptr;
	}
	return start;
}


struct node *delete(struct node *start)
{
	struct node *ptr;
	if(start == NULL)
	{
		printf("\n UNDERFLOW" );
		return;
	}
	else
	{
		ptr = start;
		printf("\n Deleted item is: %d", ptr->data);
		start = start->next;
		free(ptr);
	}
	return start;
}


void Min_element(struct node *start)
{
	struct node *ptr;
	ptr = start;
	if(start == NULL)
		printf("\nQUEUE IS EMPTY" );
	else{
		printf("\n MIN ELEMENT IS : " );
		printf( "\t%d[priority=%d]", ptr->data, ptr->priority );
	}
}
