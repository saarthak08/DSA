#include <stdio.h>
#include <conio.h>
#include <malloc.h>


struct node
{
	int data;
	struct node *next;
};


struct queue
{
	struct node *front;
	struct node *rear;
};


struct queue *q = (struct queue*)malloc(sizeof(struct queue));
void create_queue(struct queue *);
struct queue *insert(struct queue *,int);
struct queue *delete_element(struct queue *);
int Last_element(struct queue *);
int First_element(struct queue *);

int main()
{
	int val, option;
	create_queue(q);
	do
	{
		printf("\n *****MAIN MENU*****");
		printf("\n 1. INSERT");
		printf("\n 2. DELETE");
		printf("\n 3. FIRST ELEMENT");
		printf("\n 4. LAST ELEMENT");
		printf("\n 5. EXIT");
		printf("\n Enter your option : ");
		scanf("%d", &option);
		switch(option)
		{
			case 1:
			printf("\n Enter the number to insert in the queue:");
			scanf("%d", &val);
			q = insert(q,val);
			break;
		case 2:
			q = delete_element(q);
			break;
		case 3:
			val = First_element(q);
			if(val != -1)
			printf("\n The value at front of queue is : %d", val);
			break;
		case 4:
			val = Last_element(q);
			if(val != -1)
			printf("\n The value at rear of queue is : %d", val);
			break;	
		}
	}while(option != 5);
	getch();
	return 0;
}


void create_queue(struct queue *q)
{
	q -> rear = NULL;
	q -> front = NULL;
}


struct queue *insert(struct queue *q,int val)
{
	struct node *ptr;
	ptr = (struct node*)malloc(sizeof(struct node));
	ptr -> data = val;
	if(q -> front == NULL)
	{
		q -> front = ptr;
		q -> rear = ptr;
		q -> front -> next = q -> rear -> next = NULL;
	}
	else
	{
		q -> rear -> next = ptr;
		q -> rear = ptr;
		q -> rear -> next = NULL;
	}
	return q;
}


struct queue *delete_element(struct queue *q)
{
	struct node *ptr;
	ptr = q -> front;
	if(q -> front == NULL)
		printf("\n UNDERFLOW");
	else
	{
		q -> front = q -> front -> next;
		printf("\n The value being deleted is : %d", ptr -> data);
		free(ptr);
		if(q->front == NULL)
			q->rear=NULL;
	}
	return q;
}


int First_element(struct queue *q)
{
	if(q->front==NULL)
	{
		printf("\n QUEUE IS EMPTY");
		return -1;
	}
	else
		return q->front->data;
}


int Last_element(struct queue *q)
{
	if(q->rear==NULL)
	{
		printf("\n QUEUE IS EMPTY");
		return -1;
	}
	else
		return q->rear->data;
}

