import java.util.*;

class Solution {

    private static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        int t=0;
        t=sc.nextInt();
        while(t>0) {
            int n=0;
            n=sc.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=sc.nextInt();
            }
            int countDivisible=0,countNonDivisible=0;
            for(int i=0;i<n;i++) {
                if(arr[i]%3==0) {
                    countDivisible+=1;
                } else {
                    countNonDivisible+=1;
                }
            }
            if(countDivisible-countNonDivisible==1||countDivisible-countNonDivisible==0||countDivisible-countNonDivisible==-1) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
            t--;
        }
    }
}