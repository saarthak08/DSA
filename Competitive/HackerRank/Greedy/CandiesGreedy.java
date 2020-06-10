import java.util.*;

public class CandiesGreedy{
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args)
    {
        int n,i;
        n=sc.nextInt();
        long[] rating= new long[n];
        for(i=0;i<n;i++)
        {
            rating[i]=sc.nextLong();
        }
        System.out.print(minPurchase(rating));
    }
    public static long minPurchase(long c[])
    {
        long result=0;
        int i;
        long candie[]=new long[c.length];
        candie[0]=1;
        for(i=1;i<c.length;i++)
        {
            candie[i]=1;
            if(c[i]>c[i-1])
            {
                candie[i]=candie[i-1]+1;
            }
        }
        result=candie[c.length-1];
        for(i=c.length-2;i>=0;i--)
        {
            if(c[i]>c[i+1])
            {
                candie[i]=Math.max(candie[i],candie[i+1]+1);
            }
            result+=candie[i];
        }

        return result;
    }
}