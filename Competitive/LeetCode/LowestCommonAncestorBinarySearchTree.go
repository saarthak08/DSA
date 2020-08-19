package main;


type TreeNode struct {
	Val int;
	Left *TreeNode;
	Right *TreeNode;
}

func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
    pNodes:=make(map[int]*TreeNode);
    qNodes:=make(map[int]*TreeNode);
    if (root==p) {
    	return p;
    } else if (root==q) {
    	return q;
    }
    temp:=root;
    var min *TreeNode;
    min=&TreeNode{};
    for temp!=p {
    	pNodes[(*temp).Val]=temp;
    	if (temp==q) {
    		return q;
    	}
       	if (*temp).Val>(*p).Val {
    		temp=(*temp).Left;
    	} else {
    		temp=(*temp).Right;
    	}
    }
    temp=root;
    for temp!=q {
    	qNodes[(*temp).Val]=temp;
    	if val,ok:=pNodes[(*temp).Val];ok {
    		min=val;
    	}
    	if (temp==p) {
    		return p;
    	}   	
    	if (*temp).Val>(*q).Val {
    		temp=(*temp).Left;
    	} else {
    		temp=(*temp).Right;
    	}
    }
    return min;
    
}