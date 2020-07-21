#include<iostream>
#include<limits.h>

using namespace std;

//Bellman Ford works for negative cycles too.
// Time Complexity: O(EV)

/*


The difference in Dijkstra and Bellman Ford is that Bellman ford relaxes each edge for each vertex & that's why its complexity goes to O(EV)
but in Dijkstra, we choose the min weight vertex and enqeue it to the queue & then relax all its neighbours.

1) This step initializes distances from source to all vertices as infinite and distance to source itself as 0. Create an array dist[] of size |V| with all values as infinite except dist[src] where src is source vertex.

2) This step calculates shortest distances. Do following |V|-1 times where |V| is the number of vertices in given graph.
Do following for each edge u-v
If dist[v] > dist[u] + weight of edge uv, then update dist[v]
dist[v] = dist[u] + weight of edge uv

3) This step reports if there is a negative weight cycle in graph. Do following for each edge u-v.
If dist[v] > dist[u] + weight of edge uv, then “Graph contains negative weight cycle”
The idea of step 3 is, step 2 guarantees shortest distances if graph doesn’t contain negative weight cycle. If we iterate through all edges one more time and get a shorter path for any vertex, then there is a negative weight cycle

*/


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
	// Initialising Graph
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
	//Initialising all distance to MAX.
	int *distance=new int[n];
	for(int i=0;i<n;i++) {
		distance[i]=INT_MAX;
	}
	//Initialising distance of source to 0.
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
	//Relaxation of Graph.
	for(int i=0;i<n;i++) {
		//For each edge, check if current distance of the neighbouring node is greater than sum of weight of edge & distance of current node.
		for(int j=0;j<n;j++) {
			if(graph[i][j]!=0) {
				if(distance[j]>graph[i][j]+distance[i]) {
					//If yes, then update the distance of neighbouring node.
					distance[j]=graph[i][j]+distance[i];
					for(int k=0;k<n;k++) {
						cout << distance[k] << "\t";
					}
					cout << endl;
				}
			}
		}
	}
	//After relaxation, distance array contains shortest paths from source to respective nodes.
	//If after relaxation, still there is any node such that its neighbouring node's distance is greater than sum of weight of edge & distance of current node,
	// then graph contains negative cycle.
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