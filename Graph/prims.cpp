#include<iostream>
#include<limits.h>

using namespace std;


void print_graph(int** graph, int v);
int** prims(int** graph, int v);
bool detect_cycle(int** graph, int v, int index, bool *visited, int **visited_edges);


int main() {
	int n=0,e;
	cout << "Enter the number of vertices: ";
	cin >> n;
	int** graph;
	graph=new int*[n];
	for(int i=0;i<n;i++) {
		graph[i]=new int[n];
	}
	for(int i=0;i<n;i++) {
		for(int j=0;j<n;j++) {
			graph[i][j]=0;
		}
	}
	cout << "Enter the edges: " <<endl;
	int x;
	int k=0;
	while(k!=e) {
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
			cout << "Enter the weight of the edge: ";
			cin >> w;
			if(u<0||v<0||u>=n||v>=n) {
				cout << "Invalid Input. Try Again!" << endl;
				continue;
			}
			graph[u][v]=w;
			graph[v][u]=w;
			k++;
		}
		else {
			cout << "Invalid Input. Try Again!" <<endl;
			continue;
		}
	}
	print_graph(graph,n);
	int** result_tree=prims(graph,n);
	cout << "\n\nResulting Spanning Tree: " << endl;
	print_graph(result_tree,n);
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


int** prims(int** graph, int v) {

	int **tree=new int*[v];
	for(int i=0;i<v;i++) {
		tree[i]=new int[v];
	}
	for(int i=0;i<v;i++) {
		for(int j=0;j<v;j++) {
			tree[i][j]=0;
		}
	}
	for(int i=0;i<v;i++) {
		int min_index=0;
		int min=INT_MAX;
		for(int j=0;j<v;j++) {
			if(graph[i][j]!=0) {
				tree[i][j]=graph[i][j];
				tree[j][i]=graph[i][j];
				bool *visited=new bool[v];
				int** visited_edges=new int*[v];
				for(int x=0;x<v;x++) {
					visited[x]=false;
					visited_edges[x]=new int[v];
				}
				for(int x=0;x<v;x++) {
					for(int y=0;y<v;y++) {
						visited_edges[x][y]=0;
					}
				}
				if(!detect_cycle(tree,v,i,visited,visited_edges)) {
					if(min>graph[i][j]) {
						min=graph[i][j];
						min_index=j; 
					}
				}
				tree[i][j]=0;
				tree[j][i]=0;
			}
		}
		tree[i][min_index]=min;
		tree[min_index][i]=min;
	}
	return tree;
}

bool detect_cycle(int** graph, int v, int index, bool *visited, int **visited_edges) {
	visited[index]=true;
	for(int i=0;i<v;i++) {
		if(graph[index][i]!=0&&!visited[i]) {
			visited_edges[index][i]=1;
			visited_edges[i][index]=1;
			if(detect_cycle(graph,v,i,visited,visited_edges)) {
				return true; 
			}
		}
	}
	for(int i=0;i<v;i++) {
		if(graph[index][i]!=0&&visited[i]&&visited_edges[i][index]!=1&&visited_edges[index][i]!=1) {
			cout << "\nCycle Detected: "<< index << "\t" << i << endl;
			return true;
		}
	}
	return false;
}
