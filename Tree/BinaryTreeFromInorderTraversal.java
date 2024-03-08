import java.util.*;

class BinaryTreeFromInorderTraversal {

	public static Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		int n;
		n=sc.nextInt();
		int inorder[]=new int[n];
		for(int i=0;i<n;i++) {
			inorder[i]=sc.nextInt();
		}
		List<Node> trees=getTrees(inorder,n,0,n-1);
		System.out.println("PreOrder Traversals:");
		for(int i=0;i<trees.size();i++) {
			preOrderTraversal(trees.get(i));
			System.out.println();
		}

	}

	public static void preOrderTraversal(Node root) {
		if(root!=null) {
			System.out.print(root.data+" ");
			preOrderTraversal(root.left);
			preOrderTraversal(root.right);
		}
	}


	public static List<Node> getTrees(int[] inorder,int n,int start,int end) {

		List<Node> trees= new ArrayList<>();

		if(start>end) {
			trees.add(null);
			return trees;
		}

		for(int i=start;i<=end;i++) {
			List<Node> leftTrees=getTrees(inorder,n,start,i-1);
			List<Node> rightTrees=getTrees(inorder,n,i+1,end);
			for(int x=0;x<leftTrees.size();x++) {
				for(int y=0;y<rightTrees.size();y++) {
					Node root=new Node(inorder[i]);
					root.left=leftTrees.get(x);
					root.right=rightTrees.get(y);
					trees.add(root);
				}
			}

		}
		return trees;

	}
}


class Node {
	int data;
	Node left, right;

	public Node() {}

	public Node(int data) {
		this.data=data;
	}
}

