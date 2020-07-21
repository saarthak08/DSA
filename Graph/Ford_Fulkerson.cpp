#include <iostream>

using namespace std;

// Max-Flow Finding Algorithm.
/*
    
    Approach: 
    Apply DFS, get a path. In the path select the edge with minimum flow.             
    Subtract the flow from all the edges in the path.
    Add this flow to max flow variable.
    Repeat this process until there is no path found via DFS.

    Time Complexity: O(E*f);
    f=Maximum Flow
*/

//Graph
int graph[100][100];
//Graph Capacity
int graph_capacity[100][100];
//Min Flow
int min_f = 0;
//Flag to find that min flow is found.
bool result = false;
int ford_fulkerson(int vertices);
int dfs_route(int source, bool visited[], int vertices, int destination, int min_flow);

int main()
{
    int n;
    cout << "Enter the number of vertices ((0-n) => (n+1) vertices):" << endl;
    cin >> n;
    if (n <= 1)
    {
        cout << "Error! Invalid Input!" << endl;
        exit(0);
    }
    //Initialising the adjacency matrix
    for (int i = 0; i < 100; i++)
    {
        for (int j = 0; j < 100; j++)
        {
            graph[i][j] = 0;
            graph_capacity[i][j] = 0;
        }
    }
    cout << "\n\nEnter the edges of the directed graph along with their maximum capacity: (First source vertex of the edge and then destination edge of the vertex.) " << endl;
    while (1)
    {
        int u, v, cap;
        cout << "\nPress \'Enter key\' to enter the edge or enter any other input to stop entering the edges: " << endl;
        cin.clear();
        getchar();
        if (cin.get() != '\n')
        {
            break;
        }
        cout << "Enter the first vertex of the edge:" << endl;
        cin >> u;
        if (u < 0 || u > n - 1)
        {
            cout << "Error! Incorrect Input! Please try again." << endl;
            continue;
        }
        cout << "Enter the second vertex of the edge:" << endl;
        cin >> v;
        if (v < 0 || v > n - 1)
        {
            cout << "Error! Incorrect Input! Please try again." << endl;
            continue;
        }
        cout << "Enter the maximum flow of the edge: " << endl;
        cin >> cap;
        if (cap < 0)
        {
            cout << "Error! Incorrect Input! Please try again." << endl;
            continue;
        }
        graph_capacity[u][v] = cap;
        graph[u][v] = 1;
    }

    int max_flow = ford_fulkerson(n);
    cout << "\n\nMax Flow: " << max_flow << endl;
}

int ford_fulkerson(int vertices)
{
    int max_flow = 0, temp = 1;

    //If there is a route between source & sink.
    while (1)
    {
        //Visited boolean array for DFS.
        bool visited[vertices];
        cout << "\nRoute: " << endl;
        //Result marked to false for every dfs.
        result = false;
        //Min Flow set to 0.
        min_f = 0;
        //Temp is -1 if path doesn't exist & 0 if path exists between source and destination.
        temp = dfs_route(0, visited, vertices, 5, INT_MAX);
        if (temp == 0)
        {
            max_flow = max_flow + min_f;
        }
        else
        {
            break;
        }
    }
    return max_flow;
}

int dfs_route(int source, bool visited[], int vertices, int destination, int min_flow)
{
    cout << source << "\t";
    //Marked current index as true.
    visited[source] = true;
    if (source == destination)
    {
        //if destination found, then mark result=true so that min_flow can be added.
        result = true;
        //Min flow marked
        min_f = min_flow;
        cout << "Min Flow:" << min_f << "\t";
        // returned 0 as destination is found.
        return 0;
    }
    else
    {
        for (int i = 0; i < vertices; i++)
        {
            // If edge exists and vertex is not already visited.
            if (!visited[i] && graph[source][i] == 1 && graph_capacity[source][i] > 0)
            {
                // Marking min flow.
                if (graph_capacity[source][i] < min_flow)
                {
                    min_flow = graph_capacity[source][i];
                }
                dfs_route(i, visited, vertices, destination, min_flow);
                // If result is found, then break the loop.
                // reduce the min-flow from the current flow of all the edges && add it to the reverse flow.
                if (result)
                {
                    graph_capacity[source][i] = graph_capacity[source][i] - min_f;
                    graph_capacity[i][source] = graph_capacity[i][source] + min_f;
                    graph[i][source] = 1;
                    break;
                }
            }
        }
    }
    //If result not found, return -1 else return 0.
    if (!result)
    {
        return -1;
    }
    else
    {
        return 0;
    }
}