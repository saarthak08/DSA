import java.math.BigInteger;
import java.util.*;

public class FibonacciModifiedDP{
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args)
    {
        int firstn, secondn, n,i;
        firstn=sc.nextInt();
        secondn=sc.nextInt();
        n=sc.nextInt();
        BigInteger[] arr= new BigInteger[n];
        arr[0]=BigInteger.valueOf(firstn);
        arr[1]=BigInteger.valueOf(secondn);
        for(i=2;i<n;i++)
        {
            arr[i]=(arr[i-1].multiply(arr[i-1])).add(arr[i-2]);
        }
        System.out.println(arr[n-1]);
    }


}