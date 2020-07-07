#include<iostream>

using namespace std;


class queue {
	private:
		int* arr;
	public:
		int size;
		int capacity;
		int front;
		int rear;
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
void topological_sort(int **graph, int v);


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
	topological_sort(graph,n);
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

void topological_sort(int **graph, int v) {
	queue x(v);
	queue result(v);
	int indegree[v];
	for(int i=0;i<v;i++) {
		indegree[i]=0;
	}
	for(int i=0;i<v;i++) {
		for(int j=0;j<v;j++) {
			if(graph[i][j]==1) {
				indegree[j]+=1;
			}
		}
	}
	for(int i=0;i<v;i++) {
		if(indegree[i]==0) {
			x.enqueue(i);
			cout << "X Enqueue: "<< i << endl;
		}
	}
	int count=0;
	while(!x.is_empty()) {
		int k=x.dequeue();
		result.enqueue(k);
		for(int i=0;i<v;i++) {
			if(graph[k][i]==1) {
				indegree[i]-=1;
				if(indegree[i]==0) {
					x.enqueue(i);
					cout << "X Enqueue: "<< i << endl;
				}
			}
		}
		count++;
	}
	if(count!=v) {
		cout << "Graph isn't a DAG!" <<endl;
	}
	result.print_queue();
}