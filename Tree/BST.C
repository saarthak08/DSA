#include<stdio.h>
#include<stdlib.h>


struct node
{
	int data;
	struct node *left;
	struct node *right;
};


struct node* insertElement(struct node *tree, int element);
void preorderTraversal(struct node *tree);
void inorderTraversal(struct node *tree);
void postorderTraversal(struct node *tree);
struct node *findSmallestElement(struct node *tree);
struct node *findLargestElement(struct node *tree);
struct node *deleteElement(struct node *tree, int element);
void mirrorImage(struct node *tree);
int totalNodes(struct node *tree);
int totalExternalNodes(struct node *tree);
int totalInternalNodes(struct node *tree);
int Height(struct node *tree);
void deleteTree(struct node *tree);


int main()
{
	struct node *tree=NULL;
	int option, val, data;
	struct node *ptr;
	do
	{
		printf("\n ******MAIN MENU******* \n");
		printf("\n 1. Insert Element");
		printf("\n 2. Preorder Traversal");
		printf("\n 3. Inorder Traversal");
		printf("\n 4. Postorder Traversal");
		printf("\n 5. Find the smallest element");
		printf("\n 6. Find the largest element");
		printf("\n 7. Delete an element");
		printf("\n 8. Count the total number of nodes");
		printf("\n 9. Count the total number of external nodes");
		printf("\n 10. Count the total number of internal nodes");
		printf("\n 11. Determine the height of the tree");
		printf("\n 12. Find the mirror image of the tree");
		printf("\n 13. Delete the tree");
		printf("\n 14. Exit");
		printf("\n\n Enter your option : ");
		scanf("%d", &option);
		switch(option)
		{
			case 1:
			printf("\n Enter the value of the new node : ");
			scanf("%d", &val);
			tree=insertElement(tree, val);
			break;
			case 2:
			printf("\n The elements of the tree are : \n");
			preorderTraversal(tree);
			break;
			case 3:
			printf("\n The elements of the tree are : \n");
			inorderTraversal(tree);
			break;
			case 4:
			printf("\n The elements of the tree are : \n");
			postorderTraversal(tree);
			break;
			case 5:
			ptr = findSmallestElement(tree);
			printf("\n Smallest element is :%d",ptr->data);
			break;
			case 6:
			ptr = findLargestElement(tree);
			printf("\n Largest element is : %d", ptr->data);
			break;
			case 7:
			printf("\n Enter the element to be deleted : ");
			scanf("%d", &val);
			//tree = deleteElement(tree, val);
			break;
			case 8:
			printf("\n Total no. of nodes = %d", totalNodes(tree));
			break;
			case 9:
			printf("\n Total no. of external nodes = %d",
			totalExternalNodes(tree));
			break;
			case 10:
			printf("\n Total no. of internal nodes = %d",
			totalInternalNodes(tree));
			break;
			case 11:
			printf("\n The height of the tree = %d",Height(tree));
			break;
			case 12:
			mirrorImage(tree);
			printf("\nTree Mirrored! Please print the tree to view it.\n");
			break;
			case 13:
			deleteTree(tree);
			break;
		}
	} while(option!=14);
	return 0;
}


struct node* insertElement(struct node *tree, int element) {
	if(tree==NULL) {
		printf("Hello");
		tree=(struct node*)malloc(sizeof(struct node));
		tree->data=element;
		return tree;
	}
	if(element>=tree->data) {
		if(tree->right==NULL) {
			struct node* new_node=(struct node*)malloc(sizeof(struct node));
			new_node->data=element;
			tree->right=new_node;
			return tree;
		}
		else {
			tree=insertElement(tree->right,element);
			return tree;
		}
	}
	else {
		if(tree->left==NULL) {
			struct node* new_node=(struct node*)malloc(sizeof(struct node));
			new_node->data=element;
			tree->left=new_node;
			return tree;
		}
		else {
			tree=insertElement(tree->left,element);
			return tree;
		}
	}
}




void preorderTraversal(struct node *tree)
{
	if(tree != NULL)
	{
		printf("%d\t", tree->data);
		preorderTraversal(tree->left);
		preorderTraversal(tree->right);
	}
}


void inorderTraversal(struct node *tree)
{
	if(tree != NULL)
	{
		inorderTraversal(tree->left);
		printf("%d\t", tree->data);
		inorderTraversal(tree->right);
	}
}


void postorderTraversal(struct node *tree)
{
	if(tree != NULL)
	{
		postorderTraversal(tree->left);
		postorderTraversal(tree->right);
		printf("%d\t", tree->data);
	}
}


struct node *findSmallestElement(struct node *tree)
{
	if((tree == NULL) || (tree->left == NULL)) {
		return tree;
	}
	else {
		return findSmallestElement(tree ->left);
	}
}


struct node *findLargestElement(struct node *tree)
{
	if((tree == NULL) || (tree->right == NULL)) {
		return tree;
	}
	else {
		return findLargestElement(tree->right);
	}
}


int totalNodes(struct node *tree)
{
	if(tree==NULL) {
		return 0;
	}
	else {
		return(totalNodes(tree->left) + totalNodes(tree->right) + 1);
	}
}


int totalExternalNodes(struct node *tree)
{
	if(tree==NULL) {
		return 0;
	}
	else if((tree->left==NULL) && (tree->right==NULL)) {
		return 1;
	}
	else {
		return (totalExternalNodes(tree->left) + totalExternalNodes(tree->right));
	}
}


int totalInternalNodes(struct node *tree)
{
	if((tree==NULL) || ((tree->left==NULL) && (tree->right==NULL))) {
		return 0;
	}
	else {
		return (totalInternalNodes(tree->left)+ totalInternalNodes(tree->right) + 1);
	}
}


int Height(struct node *tree)
{
	int leftheight, rightheight;
	if(tree==NULL) {
		return 0;
	}
	else
	{
		leftheight = Height(tree->left);
		rightheight = Height(tree->right);
		if(leftheight > rightheight) {
			return (leftheight + 1);
		}
		else {
			return (rightheight + 1);
		}
	}
}


void mirrorImage(struct node *tree)
{
	struct node *ptr;
	if(tree!=NULL)
	{
		mirrorImage(tree->left);
		mirrorImage(tree->right);
		ptr=tree->left;
		ptr->left = ptr->right;
		tree->right = ptr;
	}
}


void deleteTree(struct node *tree)
{
	if(tree!=NULL)
	{
		deleteTree(tree->left);
		deleteTree(tree->right);
		free(tree);
	}
}
