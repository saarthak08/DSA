package main;

import (
	"fmt"
)

func main() {
	var s string;
	fmt.Print("Enter a string: ");
	fmt.Scanln(&s);
	fmt.Println(countSubstrings(s));
} 


func countSubstrings(s string) int {
	var count=0;
	for i:=0;i<len(s);i++ {
		count+=1;
		count+=checkPalindrome(s, i-1, i+1);
		count+=checkPalindrome(s,i,i+1);
	}
	return count;
    
}

func checkPalindrome(s string, indexI int, indexJ int) int {
	count:=0;
	for i,j:=indexI,indexJ;i>=0&&j<len(s)&&s[i]==s[j]; {
			i--;
			j++;
			count++;
	}
	return count;
}