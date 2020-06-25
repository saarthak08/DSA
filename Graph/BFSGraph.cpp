#include<iostream>
#include<stdlib.h>
#define MAX 50

using namespace std;

int queue[MAX];
int front = 2, rear = 2;

struct Node{
    struct Node* next;
    int data;
};

Node** initializeGraph(int vertices);
bool checkAdjacencyList(Node** Graph,int u,int v);
void printGraph(Node **Graph, int vertices);
Node** addToList(Node** Graph, int u, int v);
void breadth_first_search(Node** Graph, int vertices, int i, bool visited[]);
int lastElementInQueue();
void pushQueue(int num);
int popQueue();





int main(){
    int vertices,i;
    cout << "Enter the number of vertices:"<< endl;
    cin >> vertices;
    bool* visited=new bool[vertices+1];
    Node** Graph=initializeGraph(vertices);
    printGraph(Graph,vertices);
    for(i=1;i<=vertices;i++){
        visited[i]=false;
    }
    cout<< "\n\nBreadth First Search:"<<endl;
    breadth_first_search(Graph,vertices,1,visited);
    cout<<endl;
}

Node** initializeGraph(int vertices){
    int i,u,v;
    Node** Graph;
    Graph=(Node**)malloc((vertices+1)*sizeof(Node* ));
    for(i=0;i<=vertices;i++){
        *(Graph+i)=(Node *)malloc(sizeof(Node));
        (*(Graph+i))->data=i;
        (*(Graph+i))->next=NULL;
    }
    cout <<"Enter the edges(Vertices of the corresponding edges): "<<endl;
    while(1){
        cout<<"\n\nEnter '-1' to finish entering the edges."<<endl;
        cout<<"\nEnter the first vertex of the edge:"<<endl;
        cin>>u;
        if(u==-1){
            break;
        }
        cout<<"Enter the second vertex of the edge:"<<endl;
        cin>>v;
        if(v==-1){
            break;
        }
        if(u<1||u>vertices||v<1||v>vertices){
            cout<< "\n\nError! Invalid Input! Try Again!"<<endl;;
            continue;
        }
        if(checkAdjacencyList(Graph,u,v)){
            Graph=addToList(Graph, u, v);
        }
        else{
            cout<<"\n\nEdge already present. Try something different!"<<endl;
            continue;
        }
    }
    return Graph;
}

bool checkAdjacencyList(Node** Graph,int u, int v){
    bool result=true;
    Node* iterator=*(Graph+u);
    while(iterator!=NULL){
        if(iterator->data==v){
            result=false;
            break;
        }
        iterator=iterator->next;
    }
    iterator=NULL;
    return result;
}

void printGraph(Node** Graph, int vertices){
    cout << "\n\nAdjacency List: "<<endl<<endl;
    int i;
    Node *iterator;
    for(i=1;i<=vertices;i++){
        iterator=*(Graph+i);
        while(iterator!=NULL){
            cout<< iterator->data;
            if(iterator->next!=NULL){
                cout <<" --> ";
            }
            iterator=iterator->next;
        }
        cout<<endl;
    }
    iterator=NULL;
}

Node** addToList(Node** Graph, int u, int v){
    Node* firstNode=(Node*)malloc(sizeof(Node));
    Node* secondNode=(Node*)malloc(sizeof(Node));
    firstNode->data=u;
    secondNode->data=v;
    firstNode->next=NULL;
    secondNode->next=NULL;
    Node *iterator=*(Graph+u);
    while(iterator->next!=NULL){
        iterator=iterator->next;
    }
    iterator->next=secondNode;
    iterator=*(Graph+v);
    while(iterator->next!=NULL){
        iterator=iterator->next;
    }
    iterator->next=firstNode;
    firstNode=NULL;
    secondNode=NULL;
    return Graph;
}


void pushQueue(int num)
{
	if(front == (rear+1)%MAX){
		cout<< "\n Overflow"<<endl;
    }
	else
	{
		rear=(rear+1)%MAX;
		queue[rear] = num;
	}
}


int popQueue()
{
	int val;
	if(front == rear)
	{
		return -1;
	}
	else
	{
		front=(front+1)%MAX;
		val = queue[front];
		return val;
	}
}


int lastElementInQueue()
{
	if(rear==front)
	{
		return -1;
	}
	else
	{
		return queue[(front+1)%MAX];
	}
}


void breadth_first_search(Node** Graph, int vertices, int i, bool visited[]){
    visited[i]=true;
    pushQueue(i);    
    while(lastElementInQueue()!=-1) 
    { 
        i=lastElementInQueue();
        cout << i << "\t"; 
        popQueue();
        Node *iterator=*(Graph+i); 
        while(iterator!=NULL)
        {
            if (!visited[iterator->data]) 
            { 
                visited[iterator->data] = true; 
                pushQueue(iterator->data);
            }
            iterator=iterator->next;
        }
    }
}


