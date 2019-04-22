import java.util.*;

public class BreadthFirstSearchShortestReach{
    public static Scanner sc=new Scanner(System.in);

    public static void main(String[] args)
    {
        int queries;
        queries=sc.nextInt();
        while(queries>0)
        {
            int nodes,edges,i,j;
            nodes=sc.nextInt();
            edges=sc.nextInt();
        ArrayList<ArrayList<Integer>> arr=new ArrayList<ArrayList<Integer>>(nodes);
        for(i=0;i<nodes;i++)
        {
            arr.add(new ArrayList<Integer>());
        }
        for(i=0;i<edges;i++)
        {
            int u=sc.nextInt()-1;
            int v=sc.nextInt()-1;
            ArrayList<Integer> temp=arr.get(u);
            temp.add(v);
            arr.set(u,temp);
            ArrayList<Integer> temp2=arr.get(v);
            temp2.add(u);
            arr.set(v,temp2);
        }
        int s=sc.nextInt();
        s=s-1;

        ArrayList<Integer> result =foo(arr, s);

            for(i=0;i<nodes;i++) {
                if (i!=s) {
                    if(result.get(i)==0) {
                        System.out.print("-1 ");
                    } else {
                        System.out.print(result.get(i) + " ");
                    }
                }
            }
            System.out.print("\n");
    
        queries--;
        }
    }

    public static ArrayList<Integer> foo(ArrayList<ArrayList<Integer>> arr, int s)
    {
        Queue<Integer> queue=new LinkedList<Integer>();
        ArrayList<Integer> result=new ArrayList<Integer>(arr.size());
        for(int i=0;i<arr.size();i++)
        {
            result.add(0);
        }
        queue.add(s);
        while (queue.size()>0){
            int current=queue.remove();
            ArrayList<Integer> tmp=arr.get(current);
            for(int i=0;i<tmp.size();i++) {
                int v=tmp.get(i);
                if(result.get(v)==0) {
                    queue.add(v);
                    result.set(v,result.get(current)+6);
                }
            }
        }        
        

        return result;
    }


}


     