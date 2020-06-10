import java.util.*;

public class StockMaximizeDP{
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args)
    {
        int testcases;
        testcases=sc.nextInt();
        long[] results=new long[testcases];
        for(int i=0;i<testcases;i++)
        {
            int num=sc.nextInt();
            long[] input=new long[num];
            long[] max=new long[num-1];
            if(num==1)
            {
                results[i]=0;
                continue;
            }
            for(int j=0;j<num;j++)
            {
                input[j]=sc.nextLong();
            }

            max[num-2]=input[num-1];
            for(int j=num-3;j>=0;j--)
            {
                if(input[j+1]>max[j+1])
                {
                    max[j]=input[j+1];
                }
                else
                {
                    max[j]=max[j+1];
                }
            }
            results[i]=0;
            for(int j=0;j<num-1;j++)
            {
                if(max[j]>input[j])
                results[i]+=(max[j]-input[j]);
            }
        
        }
        for(int i=0;i<testcases;i++)
        {
            System.out.println(results[i]);
        }
    }


}