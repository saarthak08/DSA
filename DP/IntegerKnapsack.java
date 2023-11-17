import java.util.*;

public class IntegerKnapsack {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfWeights = sc.nextInt();
        int[] wt = new int[numberOfWeights];
        for (int i = 0; i < numberOfWeights; i++) {
            wt[i] = sc.nextInt();
        }
        int[] v = new int[numberOfWeights];
        for (int i = 0; i < numberOfWeights; i++) {
            v[i] = sc.nextInt();
        }
        int maxWeight = sc.nextInt();
        int[][] dp = new int[numberOfWeights][maxWeight + 1];
        for (int i = 0; i < numberOfWeights; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < numberOfWeights; i++) {
            for (int j = 1; i < maxWeight + 1; j++) {
                if(i==0) {
                    
                }
            }
        }
    }
}
