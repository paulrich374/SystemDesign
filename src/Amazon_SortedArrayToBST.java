
public class Amazon_SortedArrayToBST {

	
	/*Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

上传题解
精选 1
其他 0
java 1
0
	http://www.jiuzhang.com/solutions/convert-sorted-array-to-binary-search-tree/

*/
	   private TreeNode buildTree(int[] num, int start, int end) {
	        if (start > end) {
	            return null;
	        }

	        TreeNode node = new TreeNode(num[(start + end) / 2]);
	        node.left = buildTree(num, start, (start + end) / 2 - 1);
	        node.right = buildTree(num, (start + end) / 2 + 1, end);
	        return node;
	    }

	    
	    public static void main(String[] args) {
	    	Amazon_SortedArrayToBST sol = new Amazon_SortedArrayToBST();
	    	int[] arr = {1, 2, 3, 4, 5, 6, 7};
	    	System.out.println(sol.buildTree(arr, 0, arr.length - 1));
	    }
}
