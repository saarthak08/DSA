#include <stdio.h>
#include <conio.h>
#define MAX 10

int stack[MAX],topA=-1,topB=MAX;

void pushA(int val)
{
	if(topA==topB-1)
		printf("\n OVERFLOW");
	else
	{
		topA+= 1;
		stack[topA] = val;
	}
}

int popA()
{
	int val;
	if(topA==-1)
	{
		printf("\n UNDERFLOW");
		val = -999;
	}
	else
	{
		val = stack[topA];
		topA--;
	}
	return val;
}


void pushB(int val)
{
	if(topB-1==topA)
		printf("\n OVERFLOW");
	else
	{
		topB -= 1;
		stack[topB] = val;
	}
}

int popB()
{
	int val;
	if(topB==MAX)
	{
		printf("\n UNDERFLOW");
		val = -999;
	}
	else
	{
		val = stack[topB];
		topB++;
	}
	return val;
}


int main()
{
	int option, val;
	do
	{
		printf("\n *****MENU*****");
		printf("\n 1. PUSH IN STACK A");
		printf("\n 2. PUSH IN STACK B");
		printf("\n 3. POP FROM STACK A");
		printf("\n 4. POP FROM STACK B");
		printf("\n 5. EXIT");
		printf("\n Enter your choice");
		scanf("%d",&option);
		switch(option)
		{
			case 1: printf("\n Enter the value to push on Stack A : ");
				scanf("%d",&val);
				pushA(val);
				break;
			case 2: printf("\n Enter the value to push on Stack B : ");
				scanf("%d",&val);
				pushB(val);
				break;
			case 3: val=popA();
				if(val!=-999)
				printf("\n The value popped from Stack A = %d",val);
				break;
			case 4: val=popB();
				if(val!=-999)
				printf("\n The value popped from Stack B = %d",val);
				break;
		}
	}while(option!=5);
	return 0;
}
