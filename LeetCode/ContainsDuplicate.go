package main;

func containsDuplicate(nums []int) bool {
    arr:=make(map[int]bool);
    for i:=0;i<len(nums);i++ {
        if (arr[nums[i]]) {
            return true;
        }
        arr[nums[i]]=true;
    }
    return false;
}