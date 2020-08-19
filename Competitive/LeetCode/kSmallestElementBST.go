package main;

import (
	"fmt"
);

type TreeNode struct {
	Val int;
	Right *TreeNode;
	Left *TreeNode;
}

func kthSmallest(root *TreeNode, k int) int {
	list:=make([]int,k);
    list=inorder(root,list);
    return list[k-1];
}

func inorder(root *TreeNode, list []int) []int {
	if root!=nil {
		list=inorder((*root).Right,list);
		list=append(list,(*root).Val);
		list=inorder((*root).Left,list);
	}
	return list;
}


