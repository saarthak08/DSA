package main;

import  (
	"fmt"
)

func main() {
	var n int;
	fmt.Print("Enter the number of elements: ");
	fmt.Scan(&n);
	var arr=make([]int,n);
	fmt.Println("Enter the elements: ");
	for i:=0;i<n;i++ {
		fmt.Scan(&arr[i]);
	}
	fmt.Println(longestConsecutive(arr));
	
}

func longestConsecutive(nums []int) int {
	visited:=make(map[int]int);
	for i:=0;i<len(nums);i++ {
		visited[nums[i]]=0;
	}
	max:=0;
	for i:=0;i<len(nums);i++ {	
		if visited[nums[i]]==0 {
			temp:=1;
			num:=nums[i];
			visited[nums[i]]=1;
			for val,ok:=visited[num-1];ok&&val!=1; {
				visited[num-1]=1;
				temp+=1;
				num=num-1;
				val,ok=visited[num-1];
			}
			num=nums[i];
			for val,ok:=visited[num+1];ok&&val!=1; {
				visited[num+1]=1;
				temp+=1;
				num=num+1;
				val,ok=visited[num+1];
			}	
			if max<temp {
				max=temp;
			}	
		}
	}
	return max;
}