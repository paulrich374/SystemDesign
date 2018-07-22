import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.TreeMap;

/*
 * http://instant.1point3acres.com/thread/81338
 * input
 * output
 * sort
 * 
 * return −1 if no adjacent indices exist.
 * */
public class Amazon_minumAjacentdistance {
	
	/*
	 * @Paramter:  an non-empty zero-indexed array 
	 * @Return: the minimum distance, int 
	 * Assumption: 
	 * Approach: 
	 * Time Complexity: O(nlogn), sort map by values
	 * Space Complexity: O(n)
	 * Test Case: 
	 * 0. Dependency : loop (duplicate)
	 * 1. content: duplicate
	 * 2. content: all equal 
	 * 3. content: null
	 * 4. content: 
	 * 5. size = 0
	 * 6. size = 1
	 * 7. size = large
	 * // Test case: dependency -> loop
	   // Test case: same course for all user
	   // Test case: duplicate user
	   // Test case: duplicate course (course with the same largest occurences) 
	 * */	
	public int minimumDisBtwAdjacentPair(int[] A){
		int min = Integer.MAX_VALUE;
		// test case : (size) non-empty
		// test case : (size) 1 element
		if (A.length == 1)
			return -1;
		
		// INDEX will change, need a tuple object to store index and its value
		//Arrays.sort(A);
		//System.out.println("Sorted Array:" + Arrays.toString(A) );
		
		// Store value and its list of poistion
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		for (int i= 0; i <  A.length;i++){
			ArrayList<Integer> list = null;
			if (map.containsKey(A[i])){
				list = map.get(A[i]);
			} else {
				list= new ArrayList<Integer>();
			}
			list.add(i);
			map.put(A[i], list);
		}
		System.out.println(map);
		
		// Check adjacent pair and its minimum distance
		ArrayList list = new ArrayList(map.entrySet());
		for (int i=1; i < list.size(); i++){   // O(n),if distnct; O(contant), if duplicate serious; O(1) all the same
			Entry e1 = (Entry) list.get(i);
			Entry e2 = (Entry) list.get(i-1);
			if (((int)e1.getKey()) - ((int)e2.getKey())== 1){
				ArrayList<Integer> list1 = (ArrayList<Integer>) e1.getValue();
				ArrayList<Integer> list2 = (ArrayList<Integer>) e2.getValue();
				min = Math.min( min, findsmallest(list1, list2) );
			}
		}
		return min == Integer.MAX_VALUE ? -1: min;
	}
	 /*
	  * Find the smallest difference between two lists
     * @param list1, list2: Two integer lists.
     * @return: Their smallest difference.
     */
	private int findsmallest(ArrayList<Integer> list1, ArrayList<Integer> list2){// O(1), if distinct; O(n) two list; O(0) all the same no difference return -1
		int min  = Integer.MAX_VALUE ;
		Collections.sort(list1);
		Collections.sort(list2);
		int id1 = 0;
		int id2 = 0;
		for (id1 = 0; id1 < list1.size() && id2 < list2.size() ;id1++){
			// id2+1
			while (id2+1 < list2.size()){
				if ( list2.get(id2+1) > list1.get(id1) ) 
					break;
				id2++;
			}
			//
			if (id2 < list2.size()){
				min = Math.min(min, Math.abs(list1.get(id1)-list2.get(id2)));
			}
			if (id2+1 < list2.size()){
				min = Math.min(min, Math.abs(list1.get(id1)-list2.get(id2+1)));
			}			
		}
		
		return min;
	}
	
	
	public static void main(String[] args){
		//  Test Case: Dup
		int[] A = new int[6];
		A[0] = 1; 
		A[1] = 4;
		A[2] = 7;
		A[3] = 3;
		A[4] = 3;
		A[5] = 5;
				
		Amazon_minumAjacentdistance sol = new Amazon_minumAjacentdistance();
		System.out.println("Array:" + Arrays.toString(A));
		//int[] B = Arrays.copyOf(A, A.length);
		//Arrays.sort(B);	
		System.out.println("Minimum distance:"+sol.minimumDisBtwAdjacentPair(A));
		
		// Test Case: (content) All equal elements
		int[] C = new int[]{3,3,3,3,3,3};	
		
		
		// Test Case:  (size)NON-empty as stated in the question, so need to qorry about
		
		
		// Test Case:  (size)one element
		int[] D = new int[]{3};
		
		// Test Case:  (content)negative
		
		// Test case : (content)min Int and max Int
		
		
	}
}
