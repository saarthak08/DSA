import java.util.*;

class Solution {

    private static Scanner sc=new Scanner(System.in);
    private static HashMap<Long,Long> map;
    private static HashMap<Long,Long> resultMap;

    public static void main(String[] args) {
        int n=0;
        n=sc.nextInt();
        map=new HashMap<Long,Long>();
        resultMap=new HashMap<Long,Long>();
        int arr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=sc.nextInt();
        }
        double res=(double)(calculate((long)n))/(double)factorial(n);
        System.out.format("%.6f", res);
    }

    private static long factorial(long n) {
        map.put((long)0,(long)1);
        map.put((long)1,(long)1);
        if(map.containsKey(n)) {
            return map.get(n);
        }
        for(long i=1;i<=n;i++) {
            if(!map.containsKey(i)) {
                map.put((long)i,i*map.get((long)i-1));
            }
        }
        return map.get(n);
    }
    
    private static long calculate(long n) {
        if(n==1) {
            resultMap.put((long)1,(long)1);
            return 1;
        } else if(n==2) {
            resultMap.put((long)2,(long)3);
            return 3;
        }
        long result=0;
        for(long i=1;i<n;i++) {
            long firstFactor=(factorial(n-1)/(factorial((n-1)-i)*factorial(i))), secondFactor=1, thirdFactor, fourthFactor=0;
            thirdFactor=factorial(i);            
            secondFactor=factorial(n-(i+1));
            if(resultMap.containsKey(i)) {
                fourthFactor=resultMap.get(i);
                result+=firstFactor*secondFactor*(thirdFactor+fourthFactor);
            } else {
                fourthFactor=calculate(i);
                result+=firstFactor*secondFactor*(thirdFactor+fourthFactor);
            }
        }
        resultMap.put(n,result+factorial(n-1));
        return result+factorial(n-1);
    }
  

}