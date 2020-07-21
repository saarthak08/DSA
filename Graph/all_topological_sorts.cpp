#include<iostream>
#include<vector>

using namespace std;


void print_graph(int** graph, int v);
void all_topological_sorts(int **graph, int v, bool* visited, vector<int>& result, int *indegree);


/*
Initialize all vertices as unvisited.
Now choose vertex which is unvisited and has zero indegree and decrease indegree of all those vertices by 1 (corresponding to removing edges) now add this vertex to result and call the recursive function again and backtrack.
After returning from function reset values of visited, result and indegree for enumeration of other possibilities.
*/

int main() {
	int n=0;
	cout << "Enter the number of vertices: ";
	cin >> n;
	int** graph;
	graph=new int*[n];
	for(int i=0;i<n;i++) {
		graph[i]=new int[n];
	}
	//Graph Initialization
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
	// Visited Boolean Array
	bool* visited=new bool[n];
	// Indegree Array
	int* indegree=new int[n];
	//Calculating indegree
	for(int i=0;i<n;i++) {
		indegree[i]=0;
	}
	for(int i=0;i<n;i++) {
		for(int j=0;j<n;j++) {
			if(graph[i][j]==1) {
				indegree[j]+=1;
			}
		}
	}
	vector<int> result;
	cout << "\n\nAll Topological Sorts: " <<endl;
	all_topological_sorts(graph,n,visited,result, indegree);
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

void all_topological_sorts(int **graph, int v, bool* visited, vector<int>& result, int *indegree) {
	bool flag=false;
	//For each vertex of the graph
	for(int i=0;i<v;i++) {
		//If the vertex is not visited and has 0 indegree.
		if(!visited[i]&&indegree[i]==0) {
			//Pushed to result.
			result.push_back(i);
			//Lowering the indegree of neighbouring nodes of current node by 1.
			for(int j=0;j<v;j++) {
				if(graph[i][j]==1) {
					indegree[j]-=1;
				}
			}
			//Marking current node as visited.
			visited[i]=true;
			//Recursive call
			all_topological_sorts(graph,v,visited,result,indegree);
			//Removed from result.
			result.erase(result.end() - 1); 
			//Marking current node as unvisited.
			visited[i]=false;
			//Increasing the indegree of neighbouring nodes of current node by 1.
			for(int j=0;j<v;j++) {
				if(graph[i][j]==1) {
					indegree[j]+=1;
				}
			}
			//Marking the flag true to print the order.
			flag=true;
		}
	}
	if (!flag) { 
        for (int i = 0; i < result.size(); i++) {
            cout << result[i] << " "; 
        }
        cout << endl; 
    }	
}