#include <iostream>
#include <limits.h>

using namespace std;

/*

	Dijkstra's Algorithm gives the shortest path in a weighted graph.
	For unweighted graph, simple BFS works but for unweigted graph Dijkstra is the way to go.

	Approach:
	- We will create a visited array to mark the node is visited or not. If a node is visited, it's shortest path is already determined.
	- We will create a distance array for all vertices currently having distance as Integer.MAX_VALUE for all vertices.
	- This distance array denotes the shortest distance from source node to all the other nodes in the graph.
	- In starting all the distances are MAX_VALUE i.e. infinite.
	- We will start from source node and mark it's value in distance array as 0 and enqueue it to a queue.
	- Now, this queue should be a priority queue. (Or without priority queue, it can also be done but it won't be an optimised approach).
	- The priority in queue depends on the distance of the node from the source node i.e. the value of distance array.
	- Now, we will execute a loop until queue is empty.
		- We will dequeue a node from the queue. Let's say the dequeued node is q.
		- We will check all the non visited neighbours of the node. We will find distance to each non visited neighbour. The distance will be value of distance[q] + weight[neighbour-q edge].
		- If the distance is less than the distance mentioned in the distance array. We have found a shorter path. Hence, we will update the distance array value of that neighbour. This is also called relaxing.
		- We will enqueue the neighbours for which we found the shorter path and if the neighbour is already in queue, we will update the distance there with shorter distance.
	- Now, the distance array will contain the shortest paths from source nodes to all the nodes.

   Dijkstra doesn't work with negative edges.

   The difference in Dijkstra and Bellman Ford is that Bellman ford relaxes each edge for each vertex & that's why its complexity goes to O(EV)
   but in Dijkstra, we choose the min weight vertex and enqeue it to the queue & then relax all its neighbours.

	Time Complexity:
		When we use adjaceny matrix, the time complexity will be O(V^2).

		When we use adjacency list, the time complexity will be O((V+E)*log(V)). O(V+E) for traversing and on each traverse, we are dequeuing from the queue.
		The queue is priority queue. So, it will take O(logV) time for heapify.
*/
class queue
{
private:
	int *arr;
	int front;
	int rear;

public:
	int size;
	int capacity;
	queue(int capacity)
	{
		this->capacity = capacity;
		this->front = 0;
		this->rear = -1;
		this->size = 0;
		this->arr = new int[capacity];
	}
	void enqueue(int x)
	{
		if (size == capacity)
		{
			cout << "Overflow" << endl;
			exit(0);
		}
		size++;
		rear = (rear + 1) % capacity;
		arr[rear] = x;
		return;
	}

	int dequeue()
	{
		if (size == 0)
		{
			cout << "Underflow" << endl;
			exit(0);
		}
		size--;
		int temp = arr[front];
		front = (front + 1) % capacity;
		return temp;
	}
	bool is_empty()
	{
		return size == 0 ? true : false;
	}
	void print_queue()
	{
		if (size == 0)
		{
			cout << "\nQueue Empty!" << endl;
			return;
		}
		cout << "\nCurrent Queue: " << endl;
		int j = front;
		for (int i = 0; i < size; i++, j++)
		{
			cout << arr[j] << "\t";
		}
	}
};

void print_graph(int **graph, int v);
bool dijkstra(int **graph, int n, int source, int destination, int *distance, int *path);

int main()
{
	int n = 0;
	cout << "Enter the number of vertices: ";
	cin >> n;
	int **graph;
	graph = new int *[n];
	for (int i = 0; i < n; i++)
	{
		graph[i] = new int[n];
	}
	cout << "Enter the edges: " << endl;
	int x;
	// Graph Initialisation
	while (true)
	{
		cout << "\n\n******* MENU *******" << endl;
		cout << "Press \'1\' to enter edge." << endl;
		cout << "Press \'2\' to print the current graph." << endl;
		cout << "Press \'3\' to exit entering edge." << endl;
		cin >> x;
		if (x == 3)
		{
			break;
		}
		else if (x == 2)
		{
			print_graph(graph, n);
		}
		else if (x == 1)
		{
			int u, v, w;
			cout << "Enter the first vertex of the edge: ";
			cin >> u;
			cout << "Enter the second vertex of the edge: ";
			cin >> v;
			cout << "Enter the weight of the edge: " << endl;
			cin >> w;
			if (u < 0 || v < 0 || u >= n || v >= n || w < 0)
			{
				cout << "Invalid Input. Try Again!" << endl;
				continue;
			}
			graph[u][v] = w;
			graph[v][u] = w;
		}
		else
		{
			cout << "Invalid Input. Try Again!" << endl;
			continue;
		}
	}
	print_graph(graph, n);
	int source, destination;
	while (true)
	{
		cout << "Enter source node: " << endl;
		cin >> source;
		cout << "Enter destination node: " << endl;
		cin >> destination;
		if (source < 0 || destination < 0 || source >= n || destination >= n)
		{
			cout << "Invalid Input. Try Again!" << endl;
			continue;
		}
		else
		{
			break;
		}
	}

	// Distance and path arrays initialisation.
	int *distance = new int[n];
	int *path = new int[n];
	for (int i = 0; i < n; i++)
	{
		distance[i] = INT_MAX;
		path[i] = 0;
	}

	// Distance of source set to 0.
	distance[source] = 0;

	// Dijkstra
	bool res = dijkstra(graph, n, source, destination, distance, path);
	if (!res)
	{
		cout << "\n\nNo path between source and destination." << endl;
		exit(0);
	}
	else
	{
		// Print Paths.
		cout << "\n\nDistance from source to destination: " << distance[destination] << endl;
		cout << "Path from source to destination: " << endl;
		for (int i = destination; path[i] != source; i = path[i])
		{
			if (i == destination)
			{
				cout << i << "\t";
			}
			cout << path[i] << "\t";
		}
		cout << source << "\t";
	}
}

void print_graph(int **graph, int v)
{
	cout << "\n\nCurrent Graph: " << endl;
	for (int i = 0; i < v; i++)
	{
		for (int j = 0; j < v; j++)
		{
			cout << graph[i][j] << "\t";
		}
		cout << endl;
	}
}

bool dijkstra(int **graph, int n, int source, int destination, int *distance, int *path)
{
	queue q(n);
	bool visited[n];
	// Enqueue source node.
	q.enqueue(source);
	while (!q.is_empty())
	{
		// Dequeue a node.
		int x = q.dequeue();
		// If its destination return true.
		if (x == destination)
		{
			return true;
		}
		int min = INT_MAX;
		int min_index = -1;
		bool flag = false;

		// Relax the edges & vertices and choose the vertex with minimum distance value.
		for (int i = 0; i < n; i++)
		{
			if (graph[x][i] != 0 && !visited[i])
			{
				if (distance[i] > distance[x] + graph[x][i])
				{
					distance[i] = distance[x] + graph[x][i];
					path[i] = x;
				}
			}
		}

		for (int i = 0; i < n; i++)
		{
			if (min > distance[i] && !visited[i])
			{
				min_index = i;
				min = distance[i];
			}
		}

		// Enqueue the node if its found.
		if (min_index != -1)
		{
			visited[min_index] = true;
			q.enqueue(min_index);
		}
	}
	// Return false if path not found.
	return false;
}