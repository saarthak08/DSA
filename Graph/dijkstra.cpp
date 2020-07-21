#include<iostream>
#include<limits.h>

using namespace std;


/* 
   Dijkstra doesn't work with negative edges.
	
   The difference in Dijkstra and Bellman Ford is that Bellman ford relaxes each edge for each vertex & that's why its complexity goes to O(EV)
   but in Dijkstra, we choose the min weight vertex and enqeue it to the queue & then relax all its neighbours.


	Time Complexity: This algorithm: O(V^2) using matrix. O(E*log(V)) using Adjacency List & Binary Heap.

	Approach (Matrix) :
	1) Make a distance array & assign infinite values to all nodes and 0 to source node.
	2) Enqueue the source node to the queue.
	3) Dequeue a node from the queue, then check all its edges and relax them and then choose the vertex with minimum distance value.
	4) Enqueue the node with minimum distance to the queue.
	5) Repeat 2-4 until queue is empty.




	Approach (Adjacency List & Heap) :
	The idea is to traverse all vertices of graph using BFS and use a Min Heap to store the vertices not yet included in SPT (or the vertices for which shortest distance is not finalized yet).  Min Heap is used as a priority queue to get the minimum distance vertex from set of not yet included vertices. Time complexity of operations like extract-min and decrease-key value is O(LogV) for Min Heap.

	Following are the detailed steps.
	1) Create a Min Heap of size V where V is the number of vertices in the given graph. Every node of min heap contains vertex number and distance value of the vertex.
	2) Initialize Min Heap with source vertex as root (the distance value assigned to source vertex is 0). The distance value assigned to all other vertices is INF (infinite).
	3) While Min Heap is not empty, do following
		…..a) Extract the vertex with minimum distance value node from Min Heap. Let the extracted vertex be u.
		…..b) For every adjacent vertex v of u, check if v is in Min Heap. If v is in Min Heap and distance value is more than weight of u-v plus distance value of u, then update the distance value of v.
*/
class queue {
	private:
		int* arr;
		int front;
		int rear;
	public:
		int size;
		int capacity;
		queue(int capacity) {
			this->capacity=capacity;
			this->front=0;
			this->rear=-1;
			this->size=0;
			this->arr=new int[capacity];
		}	
		void enqueue(int x) {
			if(size==capacity) {
				cout << "Overflow" << endl;
				exit(0);
			}
			size++;
			rear=(rear+1)%capacity;
			arr[rear]=x;
			return;
		}

		int dequeue() {
			if(size==0) {
				cout << "Underflow" <<endl;
				exit(0);
			}
			size--;
			int temp=arr[front];
			front=(front+1)%capacity;
			return temp;
		}
		bool is_empty() {
			return size==0?true:false;
		}
		void print_queue() {
			if(size==0) {
				cout << "\nQueue Empty!" <<endl;
				return;
			}
			cout << "\nCurrent Queue: " <<endl;
			int j=front;
			for(int i=0;i<size;i++,j++) {
				cout << arr[j] << "\t";
			}
		}

};


void print_graph(int** graph, int v);
bool dijkstra(int** graph,int n,int source,int destination, int* distance, int* path);

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
	//Graph Initialisation
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
			if(u<0||v<0||u>=n||v>=n||w<0) {
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
	int source,destination;
	while(true) {
		cout << "Enter source node: " <<endl;
		cin >> source;
		cout << "Enter destination node: " << endl;
		cin >> destination;
		if(source<0||destination<0||source>=n||destination>=n) {
			cout << "Invalid Input. Try Again!" <<endl;
			continue;
		}
		else {
			break;
		}
	}

	//Distance and path arrays initialisation.
	int *distance=new int[n];
	int *path=new int[n];
	for(int i=0;i<n;i++) {
		distance[i]=-1;
		path[i]=0;
	}

	//Distance of source set to 0.
	distance[source]=0;

	//Dijkstra
	bool res=dijkstra(graph,n,source,destination,distance,path);
	if(!res) {
		cout << "\n\nNo path between source and destination." <<endl;
		exit(0);
	}
	else {
		//Print Paths.
		cout << "\n\nDistance from source to destination: " << distance[destination] << endl;
		cout << "Path from source to destination: " << endl;
		for(int i=destination;path[i]!=source;i=path[i]) {
			if(i==destination) {
				cout << i << "\t";
			}
			cout << path[i] << "\t";
		}
		cout << source << "\t";
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



bool dijkstra(int** graph,int n,int source,int destination, int* distance, int* path) {
	queue q(n);
	// Enqueue source node.
	q.enqueue(source);
	while(!q.is_empty()) {
		//Dequeue a node.
		int x=q.dequeue();
		//If its destination return true.
		if(x==destination) {
			return true;
		}
		int min=INT_MAX;
		int min_index=0;
		bool flag=false;

		//Relax the edges & vertices and choose the vertex with minimum distance value.
		for(int i=0;i<n;i++) {	
			if(graph[x][i]!=0&&distance[i]==-1) {
				flag=true;
				distance[i]=distance[x]+graph[x][i];
				path[i]=x;
				if(graph[x][i]<min) {
					min=graph[x][i];
					min_index=i;
				}
			}
		}
		//Enqueue the node if its found.
		if(flag) {
			q.enqueue(min_index);
		}
	}
	//Return false if path not found.
	return false;
}