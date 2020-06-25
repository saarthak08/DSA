import java.util.*;

public class DFSGraph{

    private static Scanner sc=new Scanner(System.in);
    private static boolean[] visited;
    private static int[][] adjacencyMatrix;
    private static boolean[] isConnected;
    public static void main(String[] args){
        int vertices;
        System.out.println("Enter the number of vertices:");
        vertices=sc.nextInt();
        adjacencyMatrix=new int[vertices+1][vertices+1];
        visited=new boolean[vertices+1];
        isConnected=new boolean[vertices+1];
        initializeGraph(vertices);
        printAdjacencyMatrix(vertices);
        System.out.println("\n\nDepth First Search:");
        for(int i=1;i<=vertices;i++){
            if(isConnected[i]){
                dfs(vertices, i);
                break;
            }
        }
        System.out.println();
        
    }

    private static void initializeGraph(int vertices){
        int u,v,i,j;
        System.out.println("\n\nEnter the edges (Vertices of the corresponding edges):");
        while(true){
            System.out.println("\n\nPress \'1\' to finish entering the edges.");
            System.out.println("\nEnter the first vertex of the edge:");
            u=sc.nextInt();
            if(u==-1){
                break;
            }
            System.out.println("Enter the second vertex of the edge:");
            v=sc.nextInt();
            if(v==-1){
                break;
            }
            if(u<1||u>vertices||v<1||v>vertices){
                System.out.println("\n\nError! Invalid Input! Try Again!");
                continue;
            }
            isConnected[u]=true;
            isConnected[v]=true;
            adjacencyMatrix[u][v]=1;
            adjacencyMatrix[v][u]=1;
        }
    }

    private static void printAdjacencyMatrix(int vertices){
        System.out.println("\n\nAdjacency Matrix:");
        for(int i=1;i<=vertices;i++){
            for(int j=1;j<=vertices;j++){
                System.out.print(adjacencyMatrix[i][j]+"\t");
            }
            System.out.println();
        }
    }

    private static void dfs(int vertices,int vertex){
        System.out.print(vertex+"\t");
        visited[vertex]=true;
        for(int i=1;i<=vertices;i++){
            if(!visited[i]&&adjacencyMatrix[vertex][i]==1){
                dfs(vertices, i);
            }
        }
    }
}