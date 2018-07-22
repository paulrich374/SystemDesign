import java.util.ArrayList;
import java.util.LinkedList;

public class Amazon_treescoregathering {
	/*
	 * @Paramter: a integer array
	 * @Return: a string with serialized tree node
	 * Assumption: a non-empty zero-indexed positive integer array as input
	 * Approach: construct a tree with binary search tree feature and print out the tree for each level using BFS
	 * Time Complexity: O(nlogn), construct a tree, 
	 * Space Complexity: O(n)
	 * Test Case: 
	 * 1. content: a non-empty zero-indexed positive integer array  with duplicate
	 * 2. content: a non-empty zero-indexed positive integer array  with all equal elements
	 * 3. content: null
	 * 4. content: negative
	 * 5. size = 0
	 * 6. size = 1
	 * 7. size = large
	 * */
	public String scoregathering (int [] A){
		
		// Validate the input
		if (A == null || A.length == 0)
			return "";
		
		// Construct a tree , T:O(nlogn), S:O(n)
		TreeNodeScore root = new TreeNodeScore(A[0]);
		root.count = 1;
		for (int i =1; i < A.length; i++){// O(n)
			// traverse from the root
			root = constructBinaryTreeBSTLike(root,A[i]); // insert a node => O(logn)
		}
		
		// Level order traversal T:O(n) traverse all nodes, S:O(n) queue
		LinkedList<TreeNodeScore> queue = new LinkedList<TreeNodeScore>();
		ArrayList<ArrayList<Integer>> levelorderprint = new ArrayList<ArrayList<Integer>>(); 
		String res = new String();
		queue.add(root);
		res += root.val +":"+root.count+",";
		while (!queue.isEmpty()){
			int size = queue.size();// levelorderprint
			ArrayList<Integer> list= new ArrayList<Integer>();//levelorderprint
			for (int i = 0 ; i < size; i++){//levelorderprint
				TreeNodeScore cur = queue.poll();
				list.add(cur.val);//levelorderprint
				if (cur.left != null){
					queue.add(cur.left);
					res += cur.left.val +":"+cur.left.count+",";
				}else {
					res += ",";
				}
				if (cur.right != null){
					queue.add(cur.right);
					res += cur.right.val +":"+cur.right.count + ",";				
				}else {
					res += ",";
				}
			}	
			levelorderprint.add(list);// levelorderprint
		}
		System.out.println(levelorderprint);//levelorderprint
		
		// Clean the leaf's children
		int i = res.length()-1;
		while (i>=0 && res.charAt(i) == ','){
			i--;
		}
		res = res.substring(0,i+1);
		
		
		
		return res;
		
	}
	// Contruct a binary tree, like {..,2,5,5,6,1,4} T:O(logn), S:O(logn)
	private  TreeNodeScore constructBinaryTreeBSTLike(TreeNodeScore root, int val){
		// Base Case, cannot fins a node create one
		if (root == null){
			TreeNodeScore node = new TreeNodeScore(val);
			node.count = 1;
			return node;
		}
		// Recursive case
		if (root.val > val){
			 root.left = constructBinaryTreeBSTLike(root.left,val);
		}else if (root.val < val){
			 root.right = constructBinaryTreeBSTLike(root.right,val);
		}else {
			root.count+=1;
		}
		return root;
	}
	public static void main(String[] args){
		// Test case: (content) duplicate
		int[] A = new int[]{4,2,5,5,6,1,4};
		Amazon_treescoregathering sol =new Amazon_treescoregathering();
		System.out.println("Tree: " + sol.scoregathering(A));
		// Test case: (content) all equal
		A = new int[]{4,4,4,4,4,4,4};
		System.out.println("Tree: " + sol.scoregathering(A));
		// Test case: (content) null
		A = null;
		System.out.println("Tree: " + sol.scoregathering(A));		
		// Test case: (content) negative
		A = new int[]{4,-2,-2,4,-1,4,5};;
		System.out.println("Tree: " + sol.scoregathering(A));
		// Test case: (size) 0
		A = new int[]{};;
		System.out.println("Tree: " + sol.scoregathering(A));	
		// Test case: (size) 1
		A = new int[]{4};;
		System.out.println("Tree: " + sol.scoregathering(A));	
		// Test case: (size) large size
		A = new int[]{4,2,5,5,6,1,4,7,8,9,0,1,1,2,34,6,7,8,8,3,4,6,7,8,9,9,3,5,7,23,4,5,7};
		System.out.println("Tree: " + sol.scoregathering(A));				
	}
}
class TreeNodeScore{
	int val;
	int count;
	TreeNodeScore left ,right;
	TreeNodeScore(int val){
		this.val = val;
		this.count = 0;
		this.left = null;
		this.right = null;
	}
	@Override
	public String toString(){
		LinkedList<TreeNodeScore> queue = new LinkedList<TreeNodeScore>();
		ArrayList<ArrayList<Integer>> levelorderprint = new ArrayList<ArrayList<Integer>>(); 
		String res = new String();
		queue.add(this);
		res += this.val +":"+this.count+",";
		while (!queue.isEmpty()){
			int size = queue.size();// levelorderprint
			ArrayList<Integer> list= new ArrayList<Integer>();//levelorderprint
			for (int i = 0 ; i < size; i++){//levelorderprint
				TreeNodeScore cur = queue.poll();
				list.add(cur.val);//levelorderprint
				if (cur.left != null){
					queue.add(cur.left);
					res += cur.left.val +":"+cur.left.count+",";
				}else {
					res += ",";
				}
				if (cur.right != null){
					queue.add(cur.right);
					res += cur.right.val +":"+cur.right.count + ",";				
				}else {
					res += ",";
				}
			}	
			levelorderprint.add(list);// levelorderprint
		}
		System.out.println(levelorderprint);//levelorderprint
		return res;
	}
	
}