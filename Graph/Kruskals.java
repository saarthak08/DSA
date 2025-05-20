/*
 * This code implements Kruskal's algorithm to find the Minimum Spanning Tree (MST) of a weighted, undirected graph.
 *
 * Algorithm Steps:
 * 1. Sort all the edges of the graph in non-decreasing order of their weights.
 * 2. Initialize a disjoint-set data structure where each vertex is in its own set.
 * 3. Iterate through the sorted edges. For each edge (u, v) with weight w:
 * a. Find the sets that u and v belong to using the 'findParent' operation.
 * b. If u and v belong to different sets, it means adding this edge will not create a cycle.
 * c. Add the edge (u, v) to the MST.
 * d. Merge the sets containing u and v using the 'union' operation.
 * 4. Continue until V-1 edges have been added to the MST, where V is the number of vertices in the graph.
 *
 * Time Complexity:
 * 1. Sorting the edges takes O(E log E) time, where E is the number of edges.
 * 2. The while loop iterates at most E times.
 * 3. Inside the loop, the 'findParent' and 'union' operations using path compression and union by rank take almost constant time on average, effectively O(alpha(V)), where alpha is the inverse Ackermann function (a very slowly growing function, practically constant).
 * 4. Adding edges to the MST list takes O(1) on average.
 * Therefore, the overall time complexity is dominated by the sorting step, which is O(E log E). In a connected graph, E is at least V-1 and at most V(V-1)/2, so O(E log E) is equivalent to O(E log V).
 *
 * Space Complexity:
 * 1. The 'parent' array of size V is used for the disjoint-set data structure, taking O(V) space.
 * 2. The 'rank' array of size V is also used for the disjoint-set data structure, taking O(V) space.
 * 3. The 'PriorityQueue' to store the edges can hold up to E edges, taking O(E) space.
 * 4. The 'mst' list to store the edges of the MST can hold up to V-1 edges, taking O(V) space.
 * Therefore, the overall space complexity is O(V + E).
 */

public class Kruskals {
	private static Scanner sc = new Scanner(System.in);
	private static int[] parent;
	private static int[] rank;

	private static void union(GraphEdge edge) {
		int v1 = edge.v1-1;
		int v2 = edge.v2-1;
		int parentV1 = findParent(v1);
		int parentV2 = findParent(v2);
		if(rank[parentV1]>rank[parentV2]) {
			parent[parentV2] = parentV1;
		} else if(rank[parentV1]<rank[parentV2]) {
			parent[parentV1] = parentV2;
		} else {
			parent[parentV2] = parentV1;
			rank[parentV1]+=1;
		}
	}

	private static int findParent(int node) {
		if(parent[node]==node) {
			return node;
		}
		int parentNode = findParent(parent[node]);
		parent[node] = parentNode;
		return parentNode;
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of vertices: ");
		int v = sc.nextInt();
		System.out.println("Enter the number of edges: ");
		int e = sc.nextInt();
		parent = new int[v];
		for(int i=0; i<v; i++) {
			parent[i] = i;
		}
		rank = new int[v];

		PriorityQueue<GraphEdge> queue = new PriorityQueue<>();

		for (int i = 0; i < e; i++) {
			System.out.println("Enter details of the edge " + (i + 1));
			System.out.println("Enter the first vertex of the edge: ");
			GraphEdge edge = new GraphEdge();
			edge.v1 = sc.nextInt();
			System.out.println("Enter the second vertex of the edge: ");
			edge.v2 = sc.nextInt();
			System.out.println("Enter the weight of the edge: ");
			edge.w = sc.nextInt();
			queue.offer(edge);
		}

		ArrayList<GraphEdge> mst = new ArrayList<>();

		while (mst.size() != v - 1 && !queue.isEmpty()) {
			GraphEdge edge = queue.poll();
			int parentV1 = findParent(edge.v1-1);
			int parentV2 = findParent(edge.v2-1);
			if(parentV1!=parentV2) {
				union(edge);
				mst.add(edge);
			}
		}

		if (mst.size() != v - 1) {
			System.out.println("Graph is not connected. MST cannot be formed.");
		} else {
			System.out.println("MST: ");
			for (GraphEdge edge : mst) {
				System.out.println(edge.v1 + " " + edge.v2 + " " + edge.w);
			}
		}
	}
}

class GraphEdge implements Comparable<GraphEdge> {
	int v1;
	int v2;
	int w;

	public GraphEdge(int v1, int v2, int w) {
		this.v1 = v1;
		this.v2 = v2;
		this.w = w;
	}

	public GraphEdge() {

	}

	public int compareTo(GraphEdge that) {
		return this.w - that.w;
	}
}