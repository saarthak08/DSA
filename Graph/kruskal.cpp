#include<iostream>
#include<algorithm>

using namespace std;


void print_graph(int** graph, int v);
int** kruskal(int** graph, int v, int** edges, int e);
bool detect_cycle(int** graph, int v, int index, bool *visited, int **visited_edges);

int main() {
	int n=0,e;
	cout << "Enter the number of vertices: ";
	cin >> n;
	int** graph;
	int** edges;
	graph=new int*[n];
	for(int i=0;i<n;i++) {
		graph[i]=new int[n];
	}
	for(int i=0;i<n;i++) {
		for(int j=0;j<n;j++) {
			graph[i][j]=0;
		}
	}
	cout << "Enter the number of edges: ";
	cin >> e;
	edges=new int*[e];
	for(int i=0;i<e;i++) {
		edges[i]=new int[3];
		edges[i][0]=0;
		edges[i][1]=0;
		edges[i][2]=0;

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
			edges[k][0]=w;
			edges[k][1]=u;
			edges[k][2]=v;
			k++;
		}
		else {
			cout << "Invalid Input. Try Again!" <<endl;
			continue;
		}
	}
	print_graph(graph,n);
	int** result_tree=kruskal(graph,n,edges,e);
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

int** kruskal(int** graph, int v, int** edges, int e) {
	int **tree;
	tree=new int*[v];
	for(int i=0;i<v;i++) {
		tree[i]=new int[v];
	}
	for(int i=0;i<v;i++) {
		for(int j=0;j<v;j++) {
			tree[i][j]=0;
		}
	}
	int edge[e];
	for(int i=0;i<e;i++) {
		edge[i]=edges[i][0];
	}
	sort(edge,edge+e);
	cout << "\n\nEdges: " << endl;
	for(int i=0;i<e;i++) {
		cout << edge[i] << "\t";
	}
	bool visited_edge[e];
	for(int i=0;i<e;i++) {
		visited_edge[i]=false;
	}
	cout << endl << endl;
	for(int i=0;i<e;i++) {
		int p,q,r;
		bool flag=false;
		for(int j=0;j<e;j++) {
			if(!visited_edge[j]&&edge[i]==edges[j][0]) {
				flag=true;
				visited_edge[j]=true;
				p=edges[j][1];
				q=edges[j][2];
				r=edges[j][0];
				break;
			}
		}
		if(flag) {
			bool* visited=new bool[v];
			for(int x=0;x<v;x++) {
				visited[x]=false;
			}
			tree[p][q]=r;
			tree[q][p]=r;
			int **visited_edges=new int*[v];
			for(int x=0;x<v;x++) {
				visited_edges[x]=new int[v];
			}
			for(int x=0;x<v;x++) {
				for(int y=0;y<v;y++) {
					visited_edges[x][y]=0;
				}
			} 
			if(detect_cycle(tree,v,p,visited,visited_edges)) {
				cout << "Cycle detected for edge from vertices " << p << " to " << q << ". Weight: " << r << endl; 
				tree[p][q]=0;
				tree[q][p]=0;
			} else {
				cout << "Edge included from vertices " << p << " to " << q << endl;
			}

		}
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
