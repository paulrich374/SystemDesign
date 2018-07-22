import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Amazon_BFSMovieRating {
/*http://40.careercup.appspot.com/page?pid=amazon-interview-questions&PageSpeed=noscript&sort=comments&n=59

Java LinkedList: https://stackoverflow.com/questions/19050211/why-linkedlist-doesnt-have-initialcapacity-in-java

	 * Assumption: 
	  		    a graph with no cycle
	 * Approach: 
                XX Use a Queue to perform BFS to traverse "all" similar movies
                [Use BFS to traverse the graph and get each node]
                Use a set to record all traversed similar movies to avoid cycle and revisited
                XX Use a PriorityQueue to perform top k functionality by inserting all similar movies into priority queue with fixed size K 
	            [Then push the node in the set into min-heap and output the top k from the heap]
         
	 * Complexity: 
	  			Time O(nlogK)	
	 			Space : O(n)
	
	 * Test Case: 
	  			1. Test case:(structure) normal
	  			2. Test case:(structure) unbalanced
	  			3. Test case:(structure) cycle
	  			4. Test case:(size/content) empty/null
	  			5. Test case:(size) 1
	  			6. Test case:(size) large
	  			7. Test case:(content) negative

 */
	    /* 
	     * @Parameter:
	     * @Return:
	     */
	    // Custom priority queue comparator for implement min-heap with Type Movie
    	public static class PQComparator implements Comparator<Movie>{
    		@Override
    		public int compare(Movie m1, Movie m2){
    			return (int)(m1.getRating() - m2.getRating());
    		}
    	}
	 
	     /*  
	     *     @Param movie
	     *     @Param numTopRatedSimilarMovies 
	     *            number of movies we want to return
	     *     @Return List of top rated similar movies
	     */
	    public static ArrayList<Movie> getMovieRecommendations(Movie movie, int numTopRatedSimilarMovies) {
	    	// Validate the input
	    	ArrayList<Movie> res= new ArrayList<Movie>();
	    	if (movie == null ||numTopRatedSimilarMovies <=0 ){
	    		return res;
	    	}
	    	
	    	// BFS 
	    	// To get all simiar movies T: O(n) all nodes, S: O(n) all nodes
	    	// a set to store all similar movies wihtout duplciate and also to avoid revisit 
	    	HashSet<Movie> set = new HashSet<Movie>();
	    	LinkedList<Movie> queue = new LinkedList<Movie>();
	    	queue.add(movie);
	    	set.add(movie);
	    	while(!queue.isEmpty()){
	    		Movie cur = queue.poll();
	    		for (Movie m: cur.getSimilarMovie()){
	    			if (!set.contains(m)){
	    				queue.add(m);
	    				set.add(m);
	    			}
	    		}
	    	}
	    	System.out.println("Movie "+movie.getId()+". all similar moives:"+set);
	    	
	    	
	    	// min-heap to get top K recommendations T:O(nlogK), S:O(K)
	    	PriorityQueue<Movie> pq = new PriorityQueue<Movie>(numTopRatedSimilarMovies+1, new PQComparator());
	    	for (Movie m : set){
	    		//if (pq.size() >=  numTopRatedSimilarMovies){
	    		//	if (m.getRating() > pq.peek().getRating()){
	    		//		pq.poll();
	    		//		pq.add(m);
	    		//	}
	    		//} else {
	    		//	pq.add(m);
	    		//}
	    		pq.add(m);
	    		if (pq.size() >  numTopRatedSimilarMovies){
	    			pq.poll();
	    		}
	    	}
	    	
	    	
	    	// Convert pq to list
	    	//Iterator<Movie> iter = pq.iterator();
	    	//for (Movie m: iter){
	    	//	res.add(m);
	    	//}
	    	while (!pq.isEmpty()) {
	    		res.add(pq.poll());
	    	}
	    	
	    	
	    	return res;
	    	
	    }
	    
	    
	    public static void main(String[] args){
	    	Movie movieA = new Movie(1,1.2);
	    	Movie movieB = new Movie(2,2.4);
	    	Movie movieC = new Movie(3,3.6);
	    	Movie movieD = new Movie(4,4.8);
	    	/*
	    	 * Implement a function to return top rated movies in the network of movies reachable from the current movie.
* eg.            A(Rating 1.2)
     *               /   \
     *            B(2.4)  C(3.6)
     *              \     /
     *                D(4.8)
	    	 * */
	    	// Test case1: normal without cycle
	    	movieA.addSimilarMovie(movieB);
	        movieA.addSimilarMovie(movieC);
	        
	        movieB.addSimilarMovie(movieA);
	        movieB.addSimilarMovie(movieD);
	        
	        movieC.addSimilarMovie(movieA);
	        movieC.addSimilarMovie(movieD);
	        
	        movieD.addSimilarMovie(movieB);
	        movieD.addSimilarMovie(movieC);
	        System.out.println("Top 2 Recommendations of movieA :"+getMovieRecommendations(movieA,2));
	        System.out.println();
	        System.out.println("Top 4 Recommendations of movieA :"+getMovieRecommendations(movieA,4));
	        System.out.println();
	        System.out.println("Top 1 Recommendations of movieA :"+getMovieRecommendations(movieA,1));
	        System.out.println();

	        System.out.println();
	        System.out.println();
	        
	        
	        // Test case2: cycle
	        movieD.addSimilarMovie(movieA);
	        System.out.println("Top 2 Recommendations of movieA :"+getMovieRecommendations(movieA,2));
	        System.out.println();
	        
	        // Test case3: null
	        System.out.println("Top 2 Recommendations of null :"+getMovieRecommendations(null,2));
	        System.out.println();
	        
	        // Test case4: negative
	        System.out.println("Top -2 Recommendations of movieA :"+getMovieRecommendations(movieA,-2));
	        System.out.println();
	    }

}

class Movie {
	private final int movieId;
	private final double rating;
	private ArrayList<Movie> similarMovies; // similarity id bidirectional
	public Movie(int movieId, double rating){
		this.movieId = movieId;
		this.rating = rating;
		this.similarMovies = new ArrayList<Movie>();
	}
	public int getId(){
		return movieId;
	}
	public double getRating(){
		return rating;
	}
	public ArrayList<Movie> getSimilarMovie( ){
		return similarMovies;
	}
	public void addSimilarMovie(Movie movie){
		// bidirectional
		similarMovies.add(movie);
		movie.similarMovies.add(this);
	}
	public String toString(){
		return movieId+"("+rating+")";
	}
}
/*use bfs to traverse the map and get each node, 
then push the node into a max-heap and output the peak each time

	 
 */