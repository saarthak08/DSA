#include <iostream>
#include <algorithm>

using namespace std;

// Time Complexity: O(E*log(E)) This-> algorithm: O(V^2)
/*
An undirected graph is a graph in which the edges do not point in any direction (ie. the edges are bidirectional).
A connected graph is a graph in which there is always a path from a vertex to any other vertex.

A spanning tree is a sub-graph of an undirected connected graph, which includes all the vertices of the graph with a minimum possible number of edges. If a vertex is missed, then it is not a spanning tree.
The edges may or may not have weights assigned to them.
A spanning tree will never contain a cycle. And it will always contain V-1 edges  where V is the number of vertices.

A minimum spanning tree is a spanning tree in which the sum of the weight of the edges is as minimum as possible.

Kruskal's Algorithm is used to make Minimum Spanning Tree from a graph.

Approach: Sort all the edges according to their weights.
Then, do the following for all the edges in ascending weight order.
Find if including the edge makes a cycle in the tree or not.
If the edge makes a cycle, discard it and if not, include it to the tree.
*/

void print_graph(int **graph, int v);
int **kruskal(int **graph, int v, int **edges, int e);
bool detect_cycle(int **graph, int v, int index, bool *visited, int **visited_edges);

int main()
{
	int n = 0, e;
	cout << "Enter the number of vertices: ";
	cin >> n;
	int **graph;
	int **edges;
	graph = new int *[n];
	for (int i = 0; i < n; i++)
	{
		graph[i] = new int[n];
	}
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			graph[i][j] = 0;
		}
	}
	cout << "Enter the number of edges: ";
	cin >> e;
	edges = new int *[e];
	// Edges Initialisation. 0->edge weight. 1-> From Vertex. 2-> To Vertex.
	for (int i = 0; i < e; i++)
	{
		edges[i] = new int[3];
		edges[i][0] = 0;
		edges[i][1] = 0;
		edges[i][2] = 0;
	}
	cout << "Enter the edges: " << endl;
	int x;
	int k = 0;
	// Graph Initialisation
	while (k != e)
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
			cout << "Enter the weight of the edge: ";
			cin >> w;
			if (u < 0 || v < 0 || u >= n || v >= n)
			{
				cout << "Invalid Input. Try Again!" << endl;
				continue;
			}
			graph[u][v] = w;
			graph[v][u] = w;
			edges[k][0] = w;
			edges[k][1] = u;
			edges[k][2] = v;
			k++;
		}
		else
		{
			cout << "Invalid Input. Try Again!" << endl;
			continue;
		}
	}
	print_graph(graph, n);
	int **result_tree = kruskal(graph, n, edges, e);
	cout << "\n\nResulting Spanning Tree: " << endl;
	print_graph(result_tree, n);
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

int **kruskal(int **graph, int v, int **edges, int e)
{
	int **tree;
	// Initialised Tree
	tree = new int *[v];
	for (int i = 0; i < v; i++)
	{
		tree[i] = new int[v];
	}
	for (int i = 0; i < v; i++)
	{
		for (int j = 0; j < v; j++)
		{
			tree[i][j] = 0;
		}
	}
	int edge[e];
	for (int i = 0; i < e; i++)
	{
		edge[i] = edges[i][0];
	}
	// Sorted edges array
	sort(edge, edge + e);
	cout << "\n\nEdges: " << endl;
	for (int i = 0; i < e; i++)
	{
		cout << edge[i] << "\t";
	}
	// Initialised visited edge array.
	bool visited_edge[e];
	for (int i = 0; i < e; i++)
	{
		visited_edge[i] = false;
	}
	cout << endl
		 << endl;
	// For each edge
	for (int i = 0; i < e; i++)
	{
		int p, q, r;
		bool flag = false;
		for (int j = 0; j < e; j++)
		{
			// if edge matches and isn't visited
			if (!visited_edge[j] && edge[i] == edges[j][0])
			{
				// Mark flag as true and edge as visited & note the vertices & weight.
				flag = true;
				visited_edge[j] = true;
				p = edges[j][1];
				q = edges[j][2];
				r = edges[j][0];
				break;
			}
		}
		if (flag)
		{
			// New boolean visited array of vertices
			bool *visited = new bool[v];
			for (int x = 0; x < v; x++)
			{
				visited[x] = false;
			}
			// Including the edge in the tree.
			tree[p][q] = r;
			tree[q][p] = r;
			// visited edges array tracking
			int **visited_edges = new int *[v];
			for (int x = 0; x < v; x++)
			{
				visited_edges[x] = new int[v];
			}
			for (int x = 0; x < v; x++)
			{
				for (int y = 0; y < v; y++)
				{
					visited_edges[x][y] = 0;
				}
			}
			// Detect Cycle
			if (detect_cycle(tree, v, p, visited, visited_edges))
			{
				// If cycle is detected, the edge is discarded.
				cout << "Cycle detected for edge from vertices " << p << " to " << q << ". Weight: " << r << endl;
				tree[p][q] = 0;
				tree[q][p] = 0;
			}
			else
			{
				// Else edge is included which is already done.
				cout << "Edge included from vertices " << p << " to " << q << endl;
			}
		}
	}
	return tree;
}

// Modified DFS
bool detect_cycle(int **graph, int v, int index, bool *visited, int **visited_edges)
{
	// mark vertex as visited.
	visited[index] = true;
	// DFS
	for (int i = 0; i < v; i++)
	{
		if (graph[index][i] != 0 && !visited[i])
		{
			visited_edges[index][i] = 1;
			visited_edges[i][index] = 1;
			// If cycle is detected, return true immediately.
			if (detect_cycle(graph, v, i, visited, visited_edges))
			{
				return true;
			}
		}
	}
	// check whetether edge is not in visited edges array but the index between the edges are already visited or not.
	for (int i = 0; i < v; i++)
	{
		if (graph[index][i] != 0 && visited[i] && visited_edges[i][index] != 1 && visited_edges[index][i] != 1)
		{
			cout << "\nCycle Detected: " << index << "\t" << i << endl;
			// If yes, return true else return false.
			return true;
		}
	}
	return false;
}
