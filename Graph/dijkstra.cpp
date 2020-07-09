#include<iostream>
#include<limits.h>

using namespace std;


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

	int *distance=new int[n];
	int *path=new int[n];
	for(int i=0;i<n;i++) {
		distance[i]=-1;
		path[i]=0;
	}
	distance[source]=0;
	bool res=dijkstra(graph,n,source,destination,distance,path);
	if(!res) {
		cout << "\n\nNo path between source and destination." <<endl;
		exit(0);
	}
	else {
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
	q.enqueue(source);
	while(!q.is_empty()) {
		int x=q.dequeue();
		if(x==destination) {
			return true;
		}
		int min=INT_MAX;
		int min_index=0;
		bool flag=false;
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
		if(flag) {
			q.enqueue(min_index);
		}
	}
	return false;
}