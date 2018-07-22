import java.util.HashMap;

/*
 * https://www.geeksforgeeks.org/find-distance-between-two-nodes-of-a-binary-tree/
 * Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca) 
 * 
 * */


public class Amazon_DistanceBetweenTwoKey {
	
	/* A Java Program to find distance between n1 and n2
	 using one traversal */
	
	private int findDistanceofNode(NodeDIST root, int n, int dist){
		
		// Base case
		if (root == null) {
			return -1;
		}
		
		// Recursive case
		if (root.val == n) {
			return dist;
		}
		
		int levelLeft = findDistanceofNode(root.left, n, dist +1);
		int distResult =  (levelLeft != -1) ? levelLeft: findDistanceofNode(root.right, n, dist +1);
		//System.out.println("Distance of Node " + n + " is " + distResult);
		return distResult;
	}
	
	private  NodeDIST findLCA(NodeDIST root, int n1, int n2) {
		
		// Base case
		if (root == null) {
			return null;
		}
		// Recursive case
		if (root.val == n1 || root.val == n2) {
			return root;
		}
		
		NodeDIST lcaLeft = findLCA(root.left, n1, n2);
		NodeDIST lcaRight = findLCA(root.right, n1, n2);
	    
		// If both of the above calls return Non-NULL, then one val is present in oce subtree and the other is present in other.
        // So this node is the LCA
		if (lcaLeft != null && lcaRight != null) {
			return root;
		}
		
		// Otherwise check if left subtree or right subtree is LCA
		return (lcaLeft != null)? lcaLeft: lcaRight;	
	}
	
	public int findDistance(NodeDIST root, int n1, int n2) {
		
		int d1 = findDistanceofNode(root, n1, 0);
		int d2 = findDistanceofNode(root, n2, 0);
		NodeDIST lca = findLCA(root, n1, n2);
		
		// Case1: With LCA. LCA is neither n1 nor n2.
		if (lca.val != n1 && lca.val != n2) {
			return d1 + d2 -2*findDistanceofNode(root, lca.val, 0);
		}
		// Case2: With LCA. LCA is n1.
		if (lca.val == n1){
			return findDistanceofNode(lca, n2, 0);
		}
		
		// Case3: With LCA. LCA is n2.
		if (lca.val == n2){
			return findDistanceofNode(lca, n1, 0);
		}
		
		// Case4: No LCA. One of the nodes is not present in the binary tree.
		return -1;
		
	}
	 
	// Driver program to test above functions
	public static void main(String[] args) {
	        
		Amazon_DistanceBetweenTwoKey sol = new Amazon_DistanceBetweenTwoKey();
		
	    // Let us create binary tree given in the above example
		NodeDIST  root = new NodeDIST(1);
	    root.left = new NodeDIST(2);
	    root.right = new NodeDIST(3);
	    root.left.left = new NodeDIST(4);
	    root.left.right = new NodeDIST(5);
	    root.right.left = new NodeDIST(6);
	    root.right.right = new NodeDIST(7);
	    root.right.left.right = new NodeDIST(8);
	    //       1 
	    //    2     3
	    //  4  5   6   7
	    //           8
	        
	    System.out.println("Dist(4, 5) = 2: "+ (sol.findDistance(root, 4, 5) == 2) );
	    System.out.println("Dist(4, 6) = 4: "+ (sol.findDistance(root, 4, 6) == 4) );
	    System.out.println("Dist(3, 4) = 3: "+ (sol.findDistance(root, 3, 4) == 3) );
	    System.out.println("Dist(2, 4) = 1: "+ (sol.findDistance(root, 2, 4) == 1) );
	    System.out.println("Dist(8, 5) = 5: " + (sol.findDistance(root, 8, 5) == 5) );
	         
	}
}

class NodeDIST {
	NodeDIST left, right;
	int val;
	int dist; 
	
	NodeDIST (int val) {
		this.val = val;
		this.dist = -1;
		this.left = null;
		this.right = null;
	}
	
	public String toString(){
		//return "("+val+","+left.val+","+right.val+")";
		return "("+val+")";
	}
}



/*

	//public class DistanceBetweenTwoKey 
	//{
	    // (To the moderator) in c++ solution this variable
	    // are declared as pointers hence changes made to them 
	    // reflects in the whole program
	     
	    // Global static variable
	    static int d1 = -1;
	    static int d2 = -1;
	    static int dist = 0;
	     
	    // A Binary Tree Node
	    static class Node{
	        Node left, right;
	        int key;
	         
	        // constructor
	        Node(int key){
	            this.key = key;
	            left = null;
	            right = null;
	        }
	    }
	     
	    // Returns level of key k if it is present in tree,
	     // otherwise returns -1
	    static int findLevel(Node root, int k, int level)
	    {
	        // Base Case
	        if (root == null)
	            return -1;
	         
	        // If key is present at root, or in left subtree or right subtree,
	        // return true;
	        if (root.key == k)
	            return level;
	             
	        int l = findLevel(root.left, k, level + 1);
	        return (l != -1)? l : findLevel(root.right, k, level + 1);
	    }
	     
	    // This function returns pointer to LCA of two given values n1 and n2. 
	    // It also sets d1, d2 and dist if one key is not ancestor of other
	    // d1 --> To store distance of n1 from root
	    // d2 --> To store distance of n2 from root
	    // lvl --> Level (or distance from root) of current node
	    // dist --> To store distance between n1 and n2
	    static Node findLCA(Node root, int n1, int n2, int lvl){
	         
	        // Base case
	        if (root == null)
	            return null;
	         
	        // If either n1 or n2 matches with root's key, report
	        // the presence by returning root (Note that if a key is
	        // ancestor of other, then the ancestor key becomes LCA
	        if (root.key == n1){
	            d1 = lvl;
	            return root;
	        }
	        if (root.key == n2)
	        {
	            d2 = lvl;
	            return root;
	        }
	         
	        // Look for n1 and n2 in left and right subtrees
	        Node left_lca = findLCA(root.left, n1, n2,  lvl + 1);
	        Node right_lca = findLCA(root.right, n1, n2,  lvl + 1);
	         
	        // If both of the above calls return Non-NULL, then one key
	        // is present in once subtree and other is present in other,
	        // So this node is the LCA
	        if (left_lca != null && right_lca != null)
	        {
	            dist = (d1 + d2) - 2*lvl;
	            return root;
	        }
	         
	        // Otherwise check if left subtree or right subtree is LCA
	        return (left_lca != null)? left_lca : right_lca;    
	    }
	     
	    // The main function that returns distance between n1 and n2
	    // This function returns -1 if either n1 or n2 is not present in
	    // Binary Tree.
	    static int findDistance(Node root, int n1, int n2){
	         d1 = -1;
	         d2 = -1;
	         dist = 0;
	        Node lca = findLCA(root, n1, n2, 1);
	         
	        // Case1: Have a LCA. 
	        // If both n1 and n2 were present in Binary Tree, return dist
	        if (d1 != -1 && d2 != -1)
	            return dist;
	       
	        // Case2: LCA is in either (n1) or n2. 
	        // If n1 is ancestor of n2, consider n1 as root and find level 
	        // of n2 in subtree rooted with n1
	        if (d1 != -1) {
	            dist = findLevel(lca, n2, 0);
	            return dist;
	        }
	         
	        // Case3: LCA is in either n1 or (n2). 
	        // If n2 is ancestor of n1, consider n2 as root and find level 
	        // of n1 in subtree rooted with n2
	        if (d2 != -1) {
	            dist = findLevel(lca, n1, 0);
	            return dist;
	        }
	         
	        // Case4: No LCA
	        return -1;
	    }
	// This code is contributed by Sumit Ghosh	
	*/




	/*
	public int bsfDistance(int[] arr, int n1, int n2) {
		// Edge case
		if (arr == null || arr.length == 0) {
			return -1;
		}
		// Construct the tree
		HashMap<Integer, TreeNodeParent> map = new HashMap<Integer, TreeNodeParent>();
		TreeNodeParent tree = constructTree(map, arr);
		// Calculate the distance
		int dis = 0;
        
		return dis;
	}
	
	// A recursive method to construct the tree
    private TreeNodeParent constructTree(HashMap<Integer, TreeNodeParent> map, int[] arr) {
    	int val = arr[arr.length-1];
    	TreeNodeParent cur = null;
    	for (int curVal:arr) {
    		if (cur == null) {
    			cur = new TreeNodeParent(val, null, null, null);
    			continue;
    		}
    		if (curVal < cur.val) {
    			
    		}
    		cur = 
        	map.put(val, root);
    	}
    	return cur;
    }
    
    private int calculateDistance() {
    	
    }
    
	public static void main(String[] args) {
		Amazon_CovnertUnsortedArraytoBST sol = new Amazon_CovnertUnsortedArraytoBST();
		
		int[] arr = {5,6,3,1,2,4};
		int n1 = 2;
		int n2 = 4;
		int dis = sol.bsfDistance(arr, n1, n2);
		System.out.println("Distance between" + n1 + "," + n2 + ":" + dis);	
	}
	*/
/*
class TreeNodeParent {
	int val;
	TreeNodeParent left, right, parent;
	
	TreeNodeParent(int val, TreeNodeParent left, TreeNodeParent right, TreeNodeParent parent) {
		this.val = val;
		this.left = left;
		this.right =right;
		this.parent = parent;
	}
}
*/