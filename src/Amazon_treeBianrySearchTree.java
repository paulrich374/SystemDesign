
public class Amazon_treeBianrySearchTree {
/*
 *Q2：
Given a binary tree, 
write a function that returns true if and only if it is a binary search tree.
 Your solution will be evaluated on correctness, runtime complexity (big-O), 
 and adherence to coding best practices
 
 * */
	/*
Assumption:

Approach:

Complexity:

Test Case:
1. (size) 0/ (content) null
2. (size) 1
3. (size) normal
4. (size) large
5. (content) is BST
6. (content) is not BST
7.
8. (content) duplciate

	 * */
	
	public boolean isBST(TreeNodeScore root){
		// Validate the input
		//if (root == null ){
		//	return true;
		//}
		// DFS to check each node is satisfied or not
		return helper(root);
	}
	private boolean helper(TreeNodeScore root){
		// Base Case
		if (root == null){
			return true;
		}
		if (root.left != null && root.val < root.left.val){
			return false;
		}
		if (root.right != null && root.val > root.right.val){
			return false;
		}
		// Recursive Case
		return helper(root.left) && helper(root.right);	
	}
	public static void main(String[] args){
		Amazon_treeBianrySearchTree sol = new Amazon_treeBianrySearchTree();
		TreeNodeScore root = new TreeNodeScore(10);
		root.left = new TreeNodeScore(6);
		root.right =new TreeNodeScore(18);
		root.left.left = new TreeNodeScore(4);
		root.left.right = new TreeNodeScore(8);
		root.left.right.left = new TreeNodeScore(5);
		System.out.println("isBST:"+sol.isBST(root));
		root = new TreeNodeScore(10);
		root.left = new TreeNodeScore(6);
		root.right =new TreeNodeScore(18);
		root.left.left = new TreeNodeScore(4);
		root.left.right = new TreeNodeScore(8);
		root.left.right.left = new TreeNodeScore(9);
		System.out.println("isBST:"+sol.isBST(root));
		root= null;
		System.out.println("isBST:"+sol.isBST(root));
		root= new TreeNodeScore(10);
		System.out.println("isBST:"+sol.isBST(root));		
	}
}
