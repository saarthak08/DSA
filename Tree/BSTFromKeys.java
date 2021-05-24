import java.util.*;

public class BSTFromKeys {

	public static Scanner sc=new Scanner(System.in);


	public static void main(String[] args) {
		int n;
		n=sc.nextInt();
		ArrayList[][] dp=new ArrayList[n][n];

		List<Node> trees= getAllTrees(n,dp,0,n-1);

		System.out.println("All Possible Binary Search Trees: ");
		for(int i=0;i<trees.size();i++) {
			preorderTraversal(trees.get(i));
			System.out.println();
		}
	}

	static List<Node> getAllTrees(int n, List<Node>[][] dp, int start,int end) {
		
		List<Node> trees=new ArrayList<>();

		if(start>end) {
			trees.add(null);
			return trees;
		}

		if(dp[start][end]!=null) {
			return dp[start][end];
		}

		for(int i=start;i<=end;i++) {
			List<Node> leftTree=getAllTrees(n,dp,start,i-1);
			List<Node> rightTree=getAllTrees(n,dp,i+1,end);

			for(int x=0;x<leftTree.size();x++) {
				for(int y=0;y<rightTree.size();y++) {
					Node root=new Node(i+1);
					root.left=leftTree.get(x);
					root.right=rightTree.get(y);
					trees.add(root);
				}
			}
		}
		dp[start][end]=trees;
		return trees;
	}


	static void preorderTraversal(Node root) {
		if(root!=null) {
			System.out.print(root.data+" ");
			preorderTraversal(root.left);
			preorderTraversal(root.right);
		}
	}

}


class Node {
	int data;
	Node right,left;
	public Node() {}
	public Node(int data) {
		this.data=data;
	}
}
