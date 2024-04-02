#include<iostream>
#include<limits.h>

using namespace std;


// Time Complexity is E*(logV);

/*
Check kruskal.cpp for spanning tree definitions.

Prim's algorithm is a minimum spanning tree algorithm that takes a graph as input
and finds the subset of the edges of that graph which form a tree that includes every vertex and
has the minimum sum of weights among all the trees that can be formed from the graph

Approach:
Take any vertex as starting vertex.
At each vertex, all the edges are considered and the one which has minimum weight and doesn't form a cycle is choosen.
*/

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
	//Graph Initialisation
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
	//Initialised MST
	int **tree=new int*[v];
	for(int i=0;i<v;i++) {
		tree[i]=new int[v];
	}
	for(int i=0;i<v;i++) {
		for(int j=0;j<v;j++) {
			tree[i][j]=0;
		}
	}
	//For each vertex
	for(int i=0;i<v;i++) {
		//Initialised MIN INDEX & MIN.
		int min_index=0;
		int min=INT_MAX;
		//For each edge from vertex i.
		for(int j=0;j<v;j++) {
			if(graph[i][j]!=0) {
				//Edge is included in MST.
				tree[i][j]=graph[i][j];
				tree[j][i]=graph[i][j];
				//Initialised visited vertex array && visited edges array.
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
				//Detect Cycle
				if(!detect_cycle(tree,v,i,visited,visited_edges)) {
					//If there is no cycle, compare with min-weight edge.
					if(min>graph[i][j]) {
						min=graph[i][j];
						min_index=j; 
					}
				}
				//Edge is discarded since minimum weight edge has to been included.
				tree[i][j]=0;
				tree[j][i]=0;
			}
		}
		//Minimum weight edge included.
		tree[i][min_index]=min;
		tree[min_index][i]=min;
	}
	return tree;
}

//Modified DFS
bool detect_cycle(int** graph, int v, int index, bool *visited, int **visited_edges) {
	//mark vertex as visited.
	visited[index]=true;
	//DFS
	for(int i=0;i<v;i++) {
		if(graph[index][i]!=0&&!visited[i]) {
			visited_edges[index][i]=1;
			visited_edges[i][index]=1;
			//If cycle is detected, return true immediately.
			if(detect_cycle(graph,v,i,visited,visited_edges)) {
				return true; 
			}
		}
	}
	//check whetether edge is not in visited edges array but the index between the edges are already visited or not.
	for(int i=0;i<v;i++) {
		if(graph[index][i]!=0&&visited[i]&&visited_edges[i][index]!=1&&visited_edges[index][i]!=1) {
			cout << "\nCycle Detected: "<< index << "\t" << i << endl;
			//If yes, return true else return false.
			return true;
		}
	}
	return false;
}