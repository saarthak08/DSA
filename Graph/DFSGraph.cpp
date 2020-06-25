#include<iostream>
#include<stdlib.h>

using namespace std;

int** initializeGraph(int **Graph,int vertices,bool *visited);
void printGraph(int **Graph,int vertices);
void dfs(int** Graph,int vertices,int vertex, bool *visited);

int main(){
	int vertices,i;
    cout << "Enter the number of vertices:"<< endl;
    cin >> vertices;
    bool* visited=new bool[vertices+1];
	int **Graph=(int **)malloc((vertices+1)*sizeof(int *));
	for(i=1;i<=vertices;i++){
	    visited[i]=false;
		*(Graph+i)=(int *)malloc((vertices+1)*sizeof(int));
	}
	for(int i=1;i<=vertices;i++){
		for(int j=1;j<=vertices;j++){
			*(*(Graph+i)+j)=0;
		}
	}
	Graph=initializeGraph(Graph,vertices,visited);
    printGraph(Graph,vertices);
    cout<< "\n\nDepth First Search:"<<endl;
    dfs(Graph,vertices,1,visited);
}

int** initializeGraph(int **Graph,int vertices, bool *visited){
	int u,v;
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
        *(*(Graph+u)+v)=1;
    	*(*(Graph+v)+u)=1;
	}
    return Graph;
}


void printGraph(int** Graph, int vertices){
	int i,j;
	for(i=1;i<=vertices;i++){
		for(j=1;j<=vertices;j++){
			cout << *(*(Graph+i)+j) << "\t" ;
		}
		cout<<endl;
	}
}
	
void dfs(int **Graph,int vertices,int vertex,bool* visited){
	cout<< vertex <<"\t";
	visited[vertex]=true;
    for(int i=1;i<=vertices;i++){
        if(!visited[i]&&Graph[vertex][i]==1){
            dfs(Graph,vertices, i,visited);
        }
    }	
}
	
