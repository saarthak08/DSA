#include<iostream>

using namespace std;

int *stack;
int capacity=0;
int size=0;
void print_graph(int** graph, int v);
void dfs(int** graph, int v, int x, bool* visited);

int pop() {
	if(size==0) {
		cout << "Underflow!" << endl;
		return 0;
	}
	int temp= stack[size-1];
	stack[size-1]=0;
	size--;
	return temp;
}

void push(int n) {
	if(size==capacity) {
		cout << "Overflow!" <<endl;
		return;
	}
	size+=1;
	stack[size-1]=n;
}

int main() {
	int n=0;
	cout << "Enter the number of vertices: ";
	cin >> n;
	int** graph;
	graph=new int*[n];
	for(int i=0;i<n;i++) {
		graph[i]=new int[n];
	}
	stack=new int[n];
	capacity=n;
	cout << "Enter the edges: " <<endl;
	int x;
	while(true) {
		cout << "\n\n******* MENU *******" <<endl;
		cout <<"Press \'1\' to enter edge." <<endl;
		cout <<"Press \'2\' to print the current graph." <<endl;
		cout <<"Press \'3\' to exit entering edge." <<endl;
		cin >> x;
		if(x==3) {
			break;
		}
		else if(x==2) {
			print_graph(graph,n);
		}
		else if(x==1) {
			int u,v;
			cout << "Enter the first vertex of the edge: ";
			cin >> u;
			cout << "Enter the second vertex of the edge: ";
			cin >>v;
			if(u<0||v<0||u>=n||v>=n) {
				cout << "Invalid Input. Try Again!" << endl;
				continue;
			}
			graph[u][v]=1;
		}
		else {
			cout << "Invalid Input. Try Again!" <<endl;
			continue;
		}
	}
	print_graph(graph,n);
	bool* visited=new bool[n];
	for(int i=0;i<n;i++) {
		if(!visited[i]) {
			dfs(graph,n,i,visited);
		}
	}
	cout << "\n\nTopological Sort: " << endl;
	while(size>0) {
		cout << pop() << "\t";
	}
}


void print_graph(int** graph, int v) {
	cout <<"\n\nCurrent Graph: " <<endl;
	for(int i=0;i<v;i++) {
		for(int j=0;j<v;j++) {
			cout << graph[i][j] << "\t";
		}
		cout << endl;
	}

}


void dfs(int** graph, int v, int x, bool* visited) {
	visited[x]=true;
	for(int i=0;i<v;i++) {
		if(!visited[i]&&graph[x][i]==1) {
			dfs(graph,v,i,visited);
		}
	}
	push(x);
}