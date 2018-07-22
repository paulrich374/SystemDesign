import java.util.ArrayList;
import java.util.Comparator;

public class Amazon_KclosestPointQuickSelect {
/*http://instant.1point3acres.com/thread/85971
 * http://www.careercup.com/question?id=15974664
 * http://www.careercup.com/question?id=4751976126480384
 * http://www.jiuzhang.com/solutions/kth-largest-element/
 * (4) Find the K closest points to the origin in 2D plane, 
 * given an array containing N points. 
 * You can assume K is much smaller than N and N is very large. 
 * You need only use standard math operators (addition, subtraction, multiplication, and division). 
 * You may use the JDK or the standard template library. 
 * Your solution will be evaluated on correctness, runtime complexity (big-O), and adherence to coding best practices.
A complete answer will include the following:
Document your assumptions
Explain your approach and how you intend to solve the problem
Provide code comments where applicable
Explain the big-O run time complexity of your solution. Justify your answer.
Identify any additional data structures you used and justify why you used them.
Only provide your best answer to each part of the question.
 * */
	
	/*

		 * Assumption: 
		 
注億注億注億注億注億
INTEGER -> min~max
    	-> postiive
    	-> negative
    	-> consecutive ?
HASHMAP -> constant insertion time
        -> capacity    
        
1. The point corrdeinate x and y are in the range [min-max ]
   
   
		 * Approach: 


First, find the distance of each point and store it as an array "dist" Time=O(n)
Secondly, find the kth smallest element in 'dist' using select algorithm and store it a new variable say 'num' Time=O(n)
Then traverse the array dist and print out all the corresponding points which have distace less than or equal to num Time=O(n) 
Overall time complexity= O(n)      
NOTE: 
If we are allowed to change the original points array then this solution is O(n) run-time complexity and O(1) space complexity. 
If not then the space complexity is O(n).
         * Complexity:


           Time:O(n)
           Space:O(1) inplace O(n) not in-place
           
         * Test cases:

1. Test case : (size) 1
2. Test case : (size) 0
3. Test case : (size) normal <k
3. Test case : (size) normal >k
4. Test case : (size) large
5. Test case : (content) same point
6. Test case : (content) duplicate point
7. Test case : k <= 0

First, find the distance of each point from the origin and store it in an array 'dist'. Time = O(n). 

Secondly, find kth smallest element in 'dist' using select algorithm and store it a new variable say 'num'. Time= O(n). 

Then traverse the array dist and print out all the corresponding points which have distance less than or equal to num. Time= O(n). 

overall time complexity= O(n)




	 * */

	/* @Parameter: a list of points and a integer k to indicate the target return size
	 * @Return: a list of closest points with size k
	 * */
	public ArrayList<PointAmazon> findKclosestQuickSelect(ArrayList<PointAmazon> points, int k){
		ArrayList<PointAmazon> res = new ArrayList<PointAmazon>();
		
		// Validate the input
		if (points == null || points.size() ==0 || k <=0 || points.size() < k)
			return res;
		
		// Perform quick select algorithm to get k closest points among a list of points
		// 0~ len-1 to find k
		quickSelect(points, 0, points.size()-1, k);
		
		// take first k  points from points list
		for (int i=0; i<k;i++){
			res.add(points.get(i));
		}
		return res;
	}
	/* quickSelect
	 * 
	 * @Parameters: a list of points, a integer left bound, a integer right bound , a integer k to indicate the number want to select
	 * @Return: void
	 * pivot
	 * how many you got so far currentK = pivot-l+1
	 * k < currentK
	 * 	how many left k - currentK
	 * k > currentK
	 *   K
	 * */
	private void quickSelect( ArrayList<PointAmazon> points, int l , int r, int k){
		// Base Case
		if (l >r){
			return;
		}
		// Recursive Case:
		// k closese = k smallest = 0 ~k-1(index)
		// Get the index whcih sepearte two part
		int pivot = partition(points, l , r);
		int currentK = pivot - l +1;
		// Check the index position and decide to proceed or not
		//if (pivot > k-1){
		if (currentK > k)
			// l~ pivot-1 to find k
			quickSelect( points, l, pivot-1, k);
		//} else if (pivot < k-1){
		else if (currentK < k){
			// pivot+1~r to find extra k-pivot-1(we already have 0~pivot)
			//quickSelect(points, pivot+1, r, k-pivot-1);// k=5, pivot =3=>4
			quickSelect(points, pivot+1, r, k-currentK);
		} 
		// find k
		else {
			System.out.println("Index equal:"+l +" vs. "+r);
		}
		return;
		
	}
	/*partition
	 *
	 * @Parameter:a list of points, a integer left bound, a integer right bound 
	 * @Return: a integer pivot index to separate a list into two part, left part < pivot value and right part > pivot value
	 * a pivot using last element
	 * a newindex start with l
	 * for loop to swap any element less than pivot to newindex and newindex++
	 * set newindex as pivot and pivot as newindex
	 * return newindex(current final sorted position)
	 * */
	private int partition(ArrayList<PointAmazon> points, int l, int r){
		// take last point as pivot
		PointAmazon pivot = points.get(r);
		// two pointer collide
		//while (){	
		//}
		//while (){
		//}
		int newindex = l;
		for (int i = l ; i < r; i++){
			// trade current(smaller) with newindex
			if (points.get(i).distance < pivot.distance){
				PointAmazon temp =  points.get(i);
				points.set(i, points.get(newindex));
				points.set(newindex, temp);
				newindex++;
			}
		}
		points.set(r, points.get(newindex));
		points.set(newindex, pivot);
		
		// swap
		return newindex;
	}
	
	public static void main(String[] args){
		ArrayList<PointAmazon> points = new ArrayList<PointAmazon>();
		PointAmazon origin = new PointAmazon(0, 0);
        points.add(new PointAmazon(1, 1, origin));
        points.add(new PointAmazon(1, 3, origin));
        points.add(new PointAmazon(-1, 1, origin));
        points.add(new PointAmazon(-1, 3, origin));
        points.add(new PointAmazon(1, -1, origin));
        points.add(new PointAmazon(3, -1, origin));
        points.add(new PointAmazon(-1, -1, origin));
        points.add(new PointAmazon(-1, 3, origin));
        points.add(new PointAmazon(2, 2, origin));
        System.out.println(points);
        Amazon_KclosestPointQuickSelect sol = new Amazon_KclosestPointQuickSelect();
        System.out.println("K closest points are: "+sol.findKclosestQuickSelect(points,5));
	}
	
}

/* assume x and y [min-max]
 * Approach#1 
 * calculate distance x^2+y^2 with long O(n)
 * sort the distance O(nlogn)
 * take k smallest distance k 
 * 
 * Approach#2 
 * calculate distance x^2+y^2 with long O(n)
 * max-heap with k elements O(nlogk) since k << n
 * space O(k)
 * 
 *  Approach#3
 *  First, find the distance of each point from the origin and store it in an array 'dist'. Time = O(n). 

Secondly, find kth smallest element in 'dist' using select algorithm and store it a new variable say 'num'. Time= O(n). 

Then traverse the array dist and print out all the corresponding points which have distance less than or equal to num. Time= O(n). 

overall time complexity= O(n)
 * If we are allowed to change the original points array then this solution is O(n) run-time complexity and O(1) space complexity. If not then the space complexity is O(n).

   Approach#4
   Another Approach, 

NxN bit Matrix Representation of the points, Traverse the matrix from the given point. 

O(n) Time Complexity and O(Max(x) *Max(y)/8) => Bit matrix representation
 * 
 * */	
