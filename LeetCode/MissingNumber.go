package main;

func missingNumber(nums []int) int {
    max:=len(nums);
    product:=(max*(max+1))/2
    for i:=0;i<len(nums);i++ {
        product=product-nums[i];
    }
    return product;
}