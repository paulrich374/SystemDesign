import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Amazon_KclosestPoints {
/*http://instant.1point3acres.com/thread/85971
 * http://www.careercup.com/question?id=15974664
 * http://www.careercup.com/question?id=4751976126480384
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
	/*

		 * Assumption: 
		 
注億注億注億注億注億
INTEGER   -> min~max
    	  -> postiive
    	  -> negative
    	  -> consecutive ?
HASHMAP   -> constant insertion time
          -> capacity  
Data Type -> all the same          
        
1. The point corrdeinate x and y are in the range [min-max ]
3. To achieve better run time to sort the distance
   we use a max-heap to sort the distance
   
		 * Approach: 

1. Create a size K max-heap with first K points in the point list
2. Inserting the rest of points into the max-heap, once size > k, we poll one out
3. When finish inserting, all K points left in the max-heap are the K closest points to the origin
        
         * Complexity:

           there are n umber of points to insert into the max-heap
           For each point, we need to perform heapify O(logK) when inseting a new point
           O(n*logK)
           Space:O(K) for max-heap size
           
         * Test cases:

1. Test case : (size) 1
2. Test case : (size) 0
3. Test case : (size) normal <k
3. Test case : (size) normal >k
4. Test case : (size) large
5. Test case : (content) same point
6. Test case : (content) duplicate point
	 * */
	public class newPQPointComparator implements Comparator<PointAmazon>{
		@Override
		public int compare(PointAmazon p1, PointAmazon p2){
			//return p1.compareTo(p2);
			return (int) (p2.distance - p1.distance);
		}
	}
	/* @Parameter: a list of points and a integer k to indicate the target return size
	 * @Return: a list of closest points with size k
	 * */
	public ArrayList<PointAmazon> findKclosest(ArrayList<PointAmazon> points, int k){
		ArrayList<PointAmazon> res = new ArrayList<PointAmazon>();
		
		// Validate the input
		if (points == null || points.size() ==0 || k <=0 || points.size() < k)
			return res;
		
		// Create a size k max-heap and iterating over whole points list
		// to insert point in the max-heap
		PriorityQueue<PointAmazon> pq = new PriorityQueue<PointAmazon>(k+1, new newPQPointComparator());
		for (int i = 0 ; i < points.size();i++){
			pq.add(points.get(i));
			if (pq.size() > k){
				pq.poll();
			}
		}
		
		// Convert priority queue into list 
		while(!pq.isEmpty()){
			res.add(pq.poll());
		}
		return res;
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
        Amazon_KclosestPoints sol = new Amazon_KclosestPoints();
        System.out.println("K closest points are: "+sol.findKclosest(points,5));
	}
	
}
class PointAmazon{
	int x,y;
	double distance;
	PointAmazon(int x, int y, PointAmazon origin){
		this.x = x;
		this.y = y;
		this.distance = Math.hypot(x-origin.x, y-origin.y);
	}
	PointAmazon(int x, int y){
		this.x = x;
		this.y = y;
	}	
	public int compareTo(PointAmazon that){
		return Double.valueOf(that.distance).compareTo(distance);// descending order
	}
	@Override
	public String toString(){
		return "x,y,dist="+x+","+y+",@"+distance+"\n";
	}
}
