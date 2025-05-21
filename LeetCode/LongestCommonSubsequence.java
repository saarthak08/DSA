class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1=text1.length();
        int n2=text2.length();
        int[][] memo=new int[n1+1][n2+1];
        for(int i=0;i<n1+1;i++) {
            for(int j=0;j<n2+1;j++) {
                if(i==0||j==0) {
                    memo[i][j]=0;
                }
                else if(text1.charAt(i-1)==text2.charAt(j-1)) {
                    memo[i][j]=memo[i-1][j-1]+1;
                } else {
                    memo[i][j]=max(memo[i-1][j],memo[i][j-1]);
                }
            }
        }
        return memo[n1][n2];
    }
    
    public int max(int a,int b) {
        return a>b?a:b;
    }
}