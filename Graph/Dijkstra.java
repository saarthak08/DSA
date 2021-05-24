import java.util.*;

class Dijkstra {
	
	public static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		int V;
		System.out.println("Enter the number of vertices: ");
		V=sc.nextInt();
		Deque<Integer> graph[] = new ArrayDeque[V+1];
		for(int i=0;i<V+1;i++) {
			graph[i]=new ArrayDeque<Integer>();
		}
		int weight[][]=new int[V+1][V+1];
		System.out.println("Enter the number of edges: ");
		int E=sc.nextInt();
		for(int i=0;i<E;i++) {
			System.out.println("Enter the first node:");
			int u=sc.nextInt();
			System.out.println("Enter the second node:");
			int v=sc.nextInt();
			System.out.println("Enter the weight of the edge:");
			int w=sc.nextInt();
			graph[u].offerLast(v);
			graph[v].offerLast(u);
			weight[u][v]=w;
			weight[v][u]=w;
		}
		dijkstra(graph,V,E,weight);
		
	}

	static void dijkstra(Deque<Integer> graph[], int V, int E,int weight[][]) {
		int distance[]=new int[V+1];
		boolean visited[]=new boolean[V+1];
		PriorityQueue<Pair> notVisited=new PriorityQueue<>(V+1,new PairComparator());
		for(int i=1;i<=V;i++) {
			distance[i]=Integer.MAX_VALUE;
		}
		System.out.println("Enter the source node:");
		int source=sc.nextInt();
		Pair p=new Pair(source,0);
		distance[source]=0;
		notVisited.offer(p);
		while(!notVisited.isEmpty()) {
			Pair x=notVisited.poll();
			visited[x.v1]=true;
			int u=x.v1;
			Iterator<Integer> itr=graph[u].iterator();
			while(itr.hasNext()) {
				int v=itr.next();
				if(!visited[v]&&weight[u][v]+distance[u]<distance[v]) {
					distance[v]=weight[u][v]+distance[u];
					Pair temp=new Pair(v,distance[v]);				
					notVisited.offer(temp);
				}
			}

		}
		for(int i=1;i<V+1;i++) {
			System.out.println(distance[i]);
		}

	}


}

class Pair {
	int v1;
	int v2;
	public Pair(int v1,int v2) {
		this.v1=v1;
		this.v2=v2;
	}
}


class PairComparator implements Comparator<Pair> {
	@Override
	public int compare(Pair p1, Pair p2) {
		return p1.v2-p2.v2;
	}
}
