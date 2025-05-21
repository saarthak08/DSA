class Solution {
    public int[] twoSum(int[] nums, int target) {
    HashMap<Integer,Integer> hashMap=new HashMap<>();
        int i,result[]=new int[2];
        for(i=0;i<nums.length;i++){
            hashMap.put(nums[i],i);
        }
        for(i=0;i<nums.length;i++){
            int temp;
            temp=target-nums[i];
            if(hashMap.containsKey(temp))
            {
                if(hashMap.get(temp)==i){
                    continue;
                }
                result[0]=i;
                result[1]=hashMap.get(temp);
                 break;
                }   
            }
            return result;
    }
}