#include<iostream>
#include<limits.h>

using namespace std;


void print_graph(int** graph, int v);
bool bellman_ford(int** graph,int n,int source,int* distance);

int main() {
	int n=0;
	cout << "Enter the number of vertices: ";
	cin >> n;
	int** graph;
	graph=new int*[n];
	for(int i=0;i<n;i++) {
		graph[i]=new int[n];
	}
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
			int u,v,w;
			cout << "Enter the first vertex of the edge: ";
			cin >> u;
			cout << "Enter the second vertex of the edge: ";
			cin >>v;
			cout << "Enter the weight of the edge: " << endl;
			cin >> w;
			if(u<0||v<0||u>=n||v>=n) {
				cout << "Invalid Input. Try Again!" << endl;
				continue;
			}
			graph[u][v]=w;
		}
		else {
			cout << "Invalid Input. Try Again!" <<endl;
			continue;
		}
	}
	print_graph(graph,n);
	int source;
	while(true) {
		cout << "Enter source node: " <<endl;
		cin >> source;
		if(source<0||source>=n) {
			cout << "Invalid Input. Try Again!" <<endl;
			continue;
		}
		else {
			break;
		}
	}

	int *distance=new int[n];
	for(int i=0;i<n;i++) {
		distance[i]=INT_MAX;
	}
	distance[source]=0;
	bool res=bellman_ford(graph,n,source,distance);
	if(!res) {
		cout << "\n\nGraph contains negative cycle!" <<endl;
	} 
	else {
		cout << "\n\nVertex distance from source: " << endl;
		for(int i=0;i<n;i++) {
			cout << i <<"\t"<<distance[i] << endl;;
		}
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



bool bellman_ford(int** graph,int n,int source,int* distance) {
	cout << "\nDistance Arrays in each iteration: " << endl;
	for(int i=0;i<n;i++) {
		for(int j=0;j<n;j++) {
			if(graph[i][j]!=0) {
				if(distance[j]>graph[i][j]+distance[i]) {
					distance[j]=graph[i][j]+distance[i];
					for(int k=0;k<n;k++) {
						cout << distance[k] << "\t";
					}
					cout << endl;
				}
			}
		}
	}
	for(int i=0;i<n;i++) {
		for(int j=0;j<n;j++) {
			if(graph[i][j]!=0) {
				if(distance[j]>graph[i][j]+distance[i]) {
					return false;
				}
			}
		}
	}
	return true;
}