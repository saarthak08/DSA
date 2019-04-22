import java.util.*;

public class GreedyFloristsGreedy{
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args)
    {
        int n, k,i;
        n=sc.nextInt();
        k=sc.nextInt();
        int[] arr=new int[n];
        for(i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }
        System.out.print(minpurchase(arr,k));
        
    }
    public static int minpurchase(int c[],int k)
    {
        int previousPurchase=0;
        int result=0;
        int customer=0,i;
        Arrays.sort(c);
        for(i=(c.length-1);i>=0;i--){
            result+=(c[i]*(previousPurchase+1));
            customer++;
            if(customer==k){
                previousPurchase++;
                customer=0;
            }
        }
        return result;
    }

}