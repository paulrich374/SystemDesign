import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Amazon_treedependencyarray {
	/* https://github.com/RubenAgudo/SWERC/blob/master/src/org/amazon/ReconstructTree.java
	 * http://www.careercup.com/question?id=6233087981649920
	 * @Paramter: a 2D integer array
	 * @Return: a string with serialized tree node
	 * Assumption: a non-empty zero-indexed positive integer array as input
	 * Approach: 
	 *           Reconstruct the tree using a HashMap, in which the key is the parent and the vlaue is a list of hcildren.
	 *           Whenever you read a new pair, add it to the HashMap and save which one is a parent and a child.
	 *           When you finish adding pairs, Iterate through isParent, and when you found a parent that is not child, that is root
	 *           
	 *           
	 * Time Complexity: 
	 *           Worst case scenario O(2n)
	 *           Adding N paris has cost N because a HashMap can add elements in constant time. 
	 *           Then you search for the last element in the isParent list.
	 * Space Complexity: O(n)
	 * 
	 * HashMap: used because it provides efficient data retrieval O(1)
	 * HashSet: perfect for storing "appeared" values and searching for a value is also constant O(1)
	 * LinkedList : a priori we don;t know how amnt pairs will be, so we don't know how many parents
	 *              will be. 
	 *         		This structure it does not have a limit like the classic array or arrayList and we can iterate through it using the JAva Iterator<T>
	 *              Downsides of HashSet and HashMap: to function properly and avoid collisions, they
	 *              need to be large, so in space complexity they are not
	 *              very different, but this exercise asked for time efiifcientyyc
	 * 
	 * Test Case: 
	 * 1. Test case:(structure) normal
	 * 2. Test case:(structure) unbalanced
	 * 3. Test case:(structure) cycle
	 * 4. Test case:(size) empty
	 * 5. Test case:(size) 1
	 * 6. Text case:(size) large
	 * 1. content: a non-empty zero-indexed positive integer array  with duplicate
	 * 2. content: a non-empty zero-indexed positive integer array  with all equal elements
	 * 3. content: null
	 * 4. content: negative
	 * 5. size = 0
	 * 6. size = 1
	 * 7. size = large
	 * */
	public TreeNodeScore reconstructTree(int[][] A){
		// Validate the input
		if (A == null || A.length == 0){
			return null;
		}
		// Constrcut a map to store paretn and child
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		for (int i = 0 ; i < A.length; i++){
			ArrayList<Integer> list = null;
			if (map.containsKey(A[i][0])){
				list = map.get(A[i][0]);
			} else {
				list  = new ArrayList<Integer>();
			}
			list.add(A[i][1]);
			map.put(A[i][0], list);
		}
		System.out.println("TreeMap: "+ map);
		
		// Construct a tree root, root.left and root.right
		int key = 0;
		ArrayList list = new ArrayList(map.entrySet());
		//for (Entry<Integer, ArrayList<Integer>> e:map.entrySet()){
		Entry e = (Entry) list.get(0);	
		key = (int) e.getKey();
		
		//}
		HashSet<Integer> visited = new HashSet<Integer>();
		return constructBianryTreeDFSLike(map, key, visited);
		//return null;
	}
	private TreeNodeScore constructBianryTreeDFSLike(TreeMap<Integer, ArrayList<Integer>> map, int key,HashSet<Integer> visited ){
		
		// Base Case
		if ((Integer)key == null){
			return null;
		}
		if (visited.contains(key)){
			System.out.println("Loop Detected!!");
			return null;
		}
		/*
		// Recursive Case
		TreeNodeScore root = new TreeNodeScore(key);
		visited.add(root.val);
		ArrayList<Integer> list = map.get(key);
		//System.out.println("key:"+key+". List:"+list);
		if (list == null){
			return root;
		}
		int size = list.size();
		if (size == 2){
			root.left = constructBianryTreeDFSLike(map, list.get(0), visited);
			root.right = constructBianryTreeDFSLike(map, list.get(1), visited);
		}else if (size == 1){
			root.left = constructBianryTreeDFSLike(map, list.get(0), visited);
		}
		return root;
		*/
		TreeNodeScore root = new TreeNodeScore(key);
		visited.add(root.val);
		// Size ==0
		if (map.get(key) == null || map.get(key).isEmpty()) {
			return root;
		}
		ArrayList<Integer> list = map.get(key);
		// Size ==1
		root.left = constructBianryTreeDFSLike(map, list.get(0),visited);
		// Size ==2
		if (map.get(key).size() == 2) {
			root.right = constructBianryTreeDFSLike(map, list.get(1), visited);
		}
		return root;
	}
	public static void main(String[] args){
		Amazon_treedependencyarray sol = new Amazon_treedependencyarray();
		// Test case:(structure) normal
		int[][] A = new int[][] {{2,4},{1,2},{3,6},{1,3},{2,5}};
		System.out.println("Amazon_treedependencyarray:" + sol.reconstructTree(A));
		// Test case:(structure) unbalanced
		A = new int[][] {{1,2},{2,3},{3,4},{4,5},{5,6}};
		System.out.println("Amazon_treedependencyarray:" + sol.reconstructTree(A));
		// Test case:(structure) cycle
		A = new int[][] {{1,2},{2,3},{3,1}};
		System.out.println("Amazon_treedependencyarray:" + sol.reconstructTree(A));
		// Test case:(size) 0
		A = new int[][] {};
		System.out.println("Amazon_treedependencyarray:" + sol.reconstructTree(A));
		// Test case: size =1
		A = new int[][] {{1,2}};
		System.out.println("Amazon_treedependencyarray:" + sol.reconstructTree(A));		
		A = new int[][] {{1,2},{2,3},{3,4},{4,3}};
		System.out.println("Amazon_treedependencyarray:" + sol.reconstructTree(A));		
	}
}
