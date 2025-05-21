package main;

import (
	"fmt"
)

type TreeNode struct {
	Val int;
	Right *TreeNode;
	Left *TreeNode;
}


func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
	qNodes:=make([]*TreeNode,0);
	pNodes:=make([]*TreeNode,0);
	_,pNodes=preOrder(root,p,pNodes);
	_,qNodes=preOrder(root,q,qNodes);
	var min *TreeNode;
	pMap:=make(map[int]*TreeNode);
	for _,val:=range pNodes {
        pMap[(*val).Val]=val;
	}
    for _,val:=range qNodes {
        fmt.Println((*val).Val);
        if v,ok:=pMap[(*val).Val];ok {
			min=v;
            break;
		}
	}
	return min;
}


func preOrder(root *TreeNode, node *TreeNode,nodes []*TreeNode) (bool,[]*TreeNode) {
	if(root!=nil) {
		if(root==node) {
			nodes=append(nodes,root);
			return true,nodes;
		}
		right,nodes:=preOrder((*root).Right,node,nodes);
		if(right) {
			nodes=append(nodes,root);
			return true,nodes;
		}
		left,nodes:=preOrder((*root).Left,node,nodes);
		if(left) {
			nodes=append(nodes,root);
			return true,nodes;
		}
	}
	return false,nodes;
}