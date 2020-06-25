import java.util.*;

public class DFSGraphViaList{

    private static Scanner sc=new Scanner(System.in);
    private static boolean[] visited;
    private static boolean[] isConnected;
    private static Node[] graph;
    public static void main(String[] args){
        int vertices;
        System.out.println("Enter the number of vertices:");
        vertices=sc.nextInt();
        visited=new boolean[vertices+1];
        isConnected=new boolean[vertices+1];
        initializeGraph(vertices);
        printGraph(vertices);
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
        graph=new Node[vertices+1];
        for(i=1;i<=vertices;i++){
            graph[i]=new Node();
            graph[i].setData(i);
        }
        System.out.println("\n\nEnter the edges (Vertices of the corresponding edges):");
        while(true){
            System.out.println("\n\nPress \'-1\' to finish entering the edges.");
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
            addToList(u,v);
        }
    }

    private static void printGraph(int vertices) throws NullPointerException{
        System.out.println("\n\nGraph:");
        for(int i=1;i<=vertices;i++){
            Node iterator=graph[i];
           while(iterator!=null){
               System.out.print(iterator.getData());
               if(iterator.getNext()!=null){
                   System.out.print(" --> ");
               }
               iterator=iterator.getNext();
            }
            System.out.println();
        }
    }

    private static void addToList(int u,int v){
        Node newNodeFirst=new Node();
        Node newNodeSecond=new Node();
        newNodeFirst.setData(u);
        newNodeSecond.setData(v);
        Node iterator=graph[u];
        while(iterator.getNext()!=null){
            iterator=iterator.getNext();
        }
        iterator.setNext(newNodeSecond);
        iterator=graph[v];
        while(iterator.getNext()!=null){
            iterator=iterator.getNext();
        }
        iterator.setNext(newNodeFirst);
    }

  private static void dfs(int vertices,int vertex) throws NullPointerException{
        System.out.print(vertex+"\t");
        visited[vertex]=true;
        Node iterator=graph[vertex];
        while(iterator!=null){
            if(!visited[iterator.getData()]){
                dfs(vertices,iterator.getData());
            }
            iterator=iterator.getNext();
    }
    }
}

class Node{
    private int data;
    private Node next;

    public Node(){
    }

    public int getData(){
        return data;
    }

    public void setData(int data){
        this.data=data;
    }

    public void setNext(Node next){
        this.next=next;
    }

    public Node getNext(){
        return next;
    }
}