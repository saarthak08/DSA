class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int memo[]=new int[n];
        for(int i=0;i<n;i++) {
            memo[i]=1;
        }
        int maxx=0;
        for(int i=0;i<n;i++) {
            for(int j=i;j>=0;j--) {
                if(nums[i]>nums[j]) {
                    memo[i]=max(memo[j]+1,memo[i]);
                }
            }
            if(maxx<memo[i]) {
                maxx=memo[i];
            }
        }
        return maxx;
    }
    
    
    public int max(int a,int b) {
        return a>b?a:b;
    }
}