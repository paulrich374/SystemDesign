import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Amazon_5HighestScore {
		/*	http://www.careercup.com/question?id=6210203154382848
Java Priority Queue: https://jindongpu.wordpress.com/2015/10/20/implement-max-heap-and-min-heap-using-priorityqueue-in-java/

		 * Assumption: 
		 
注億注億注億注億注億
INTEGER -> min~max
    	-> postiive
    	-> negative
    	-> consecutive ?
HASHMAP -> constant insertion time
        -> capacity    
        
1. The student id are in the range [0-max role number for students]
   without any gaps??
2. Test scores are in the range [0-100]
3. To achieve constant insetion time to the map 
   we use studentId as the key
   so we assume that we have a HASHMAP with initial capacity  = max role number
   for students
   
		 * Approach: 
		 
1. Let say there is an object called TestResult which encapsulates testdate, studentId, testscore
   These test results are stored in a hashmap with studentid as the key
   And we only store top 5 test result with highest score by using a min-heap with size 5
   Map<Integer, List<TestResult> perStudentResults = new HashMap<Integer,List<TestResult> 
   
   HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();

2. Once we  finish all the test results insertion, we perform average of the score and display 
   it as the score with studehId as the key in the other map
   
   HashMap<Integer, Double> res = new HashMap<Integer, Double>();
	
		 * Time Complexity: 
1.
If there are n student p results for each student
Insertsion to the hashmap for n student records O(n*p)
Heapify operation for 5 elements can be done in O(log5)
The heapify operation for n student is again n*O(log5)
2.
For each student
for i =1 to 5
retrieve all 5 elemnts 
compute the average so far
Complexity here will be O(n*5logp) = O(n*logp) 
finally complexity is O(n*p)

		 * Space Complexity: O(n)

		 * Test Cases:
1. Test case :(size) at least 5
2. Test case :(size) at least 5 and large dataset
3. TEst case: ()
*/	
	
	/*
	Given a list of test results (each with a test date, Student ID, and the student’s Score), return the Final Score for each student. A student’s Final Score is calculated as the average of his/her 5 highest test scores. You can assume each student has at least 5 test scores. 

			You may use the JDK or the standard template library. The solution will be evaluated on correctness, runtime complexity (big-O), and adherence to coding best practices. A complete answer will include the following: 

			Document your assumptions 
			Explain your approach and how you intend to solve the problem 
			Provide code comments where applicable 
			Explain the big-O run time complexity of your solution. Justify your answer.
			Identify any additional data structures you used and justify why you used them. 

			class TestResult{ 
			int studentId; 
			Date testDate; 
			int testScore; 
			} 

			public Map<Integer, Double> getFinalScores(List<TestResult> resultList){ 
			return null; 
			}
	*/	
	
	/*   * @Paramter: a list of TestResult
		 * @Return: a map with userId as key and user's score average as value
	 * */
	public Map<Integer, Double> getFinalScores(ArrayList<TestResult> resultList){ 
	
		// A map to store top5 score list in the value part,T:O(nlog5), S:O(n)
		// NOTE: whatever add first if later size >5, we poll one out
		HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();
		for (TestResult t: resultList){//O(n)
			PriorityQueue pq = null;
			if (map.containsKey(t.studentId)){
				pq = map.get(t.studentId);
			}else {
				pq = new PriorityQueue<Double>();
				
			}
			pq.add(t.testScore);//O(log5)
			if (pq.size() > 5){
				pq.poll();
			}
			map.put(t.studentId, pq);
		}
		System.out.println("user and score map:" + map);
		
		// A map to store userId and calculated average, T:O(n), S:O(n)
		HashMap<Integer, Double> res = new HashMap<Integer, Double>();
		for (Entry<Integer,PriorityQueue<Integer>> e:map.entrySet()){//O(n)
			int avg = 0;
			PriorityQueue temppg = e.getValue();
			while (!temppg.isEmpty()){ // O(5)
				avg+= (int )temppg.poll();
			}
			res.put(e.getKey(), avg*0.2);
		}
		
		return res; 
		
	}
	public static void main(String[] args){
		Amazon_5HighestScore sol = new Amazon_5HighestScore();
		int[][] scores = { { 2, 78 }, { 2, 89 }, { 2, 97 }, { 2, 92 },
				{ 2, 99 }, { 2, 66 }, { 2, 23 }, { 3, 78 }, { 3, 89 },
				{ 3, 87 }, { 3, 92 }, { 3, 98 }, { 3, 66 }, { 3, 23 } };

		ArrayList<TestResult> resultList = new ArrayList<>();
		for (int[] score : scores) {
			resultList.add(new TestResult(score[0], score[1]));
		}
		System.out.println("Trascript detail: "+Arrays.toString(scores));
		System.out.println("Trascript: "+sol.getFinalScores(resultList));

	}
	
}
class TestResult{ 
	int studentId; 
	Date testDate; 
	int testScore; 
	TestResult(int id, int score) {
		studentId = id;
		testScore = score;
		testDate = null;
	}
	public String toString(){
		return studentId+","+testScore;
	}
} 
/*
Assumptions: 
1) The student id are in the range (0-max role number for students) with out any gaps. 
2) To achieve constant insertion time to the map we use studentId as the key. So we assume that we have a hashmap with initial capacity = max role number for students 
3) test scores are in the range of [0-100] 
Approach: 
Lets say there is an object called TestResult which encapusulates testdate, studentId, testscore. 
These test results are stored in a hashmap with student id as the key 
Map<Integer, List<TestResult> perStudentResults = new HashMap<Integer,List<TestResult> 
Before we declare the score per student,
heapify the results per student as a max heap. 
Once made as a max heap,  
get the max element 5 times and perform the average of the score and display it as the score. 
Complexity: 
If there are n students p results for each students, 
* Insertion to the hashmap for n student records -> O(n*p) 
* Heapify operation for p elements can be done in O(p).
* The heapify operation for n student is again n* O(p) = O(n*p)
* For each student 
for i = 1 to 5 
* retrieve the max element and rearrange the heap = O(logp) 
* Compute the average so far 
end 
end 
Complexity here will be O(n*5logp) = O(nlogp) 
finally the complexity is O(n*p) 
// A map to store  user and on-calculating score
//HashMap<Integer, Double> map  = new HashMap<Integer, Double>();
for (TestResult t: resultList){
	if (map.containsKey(t.studentId)){
		double avg = map.get(t.studentId);
		avg+=t.testScore;
		map.put(t.studentId, avg/2);
	} else {
		map.put(t.studentId, t.testScore/1.0);
	}
}
*/
/* Moving average
int sum = 5;
for (int i =1;i< 5;i++){
sum+=5;
sum/=2;
}
System.out.println(sum);
*/