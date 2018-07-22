import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/*http://www.careercup.com/question?id=9981709
 * 
 * 
 * http://cv.qiaobutang.com/post/55c306c00cf242d2e7fdbcbc
 * 
 * 	// 	Calling remove(index)
		numbers.remove(1);
	//	Calling remove(object)
		numbers.remove(new Integer(3));
 * 
 * TEST Case 
 * 
 * input -> Collection- >same ,duplicate
 * output -> set - > same, duplicate

 * sort -> duplicate
 * hash -> duplicate
 * 
 * 
 * 
 * ==================== TEST CASE THINKING====================
 * 
I. Sort algorithm that could fail in the following boundary cases:

1. size = 0				, Empty input
2. size = 1				, 1 element input
3. size = HUGE			, Very long input (maybe of length max(data type used for index))
4. size = %2==0,%2==1	, Odd/even length input

5. content = not all same type	, Garbage inside the collection that will be sorted
6. content = null				, Null input
7. content = duplicate			, Duplicate elements
8. content = all the same		, Collection with all elements equal

II. Integer with known special cases:

0
Min/MaxInt
Negative/Positive

III. String with some known special cases:

Empty string
Long string
Unicode string (special characters)
If limited to a specific set of characters, what happens when some are not in the range
Odd/even length string
Null (as argument)
Non-null terminated


 * ==================== TEST CASE THINKING====================
 * 
 * 
 * 
 * 
 * 
 * http://www.meetqun.com/forum.php?mod=viewthread&tid=3387&extra=page%3D11%26filter%3Dtypeid%26typeid%3D49%26orderby%3Dlastpost
 * http://stackoverflow.com/questions/2991480/most-frequent-3-page-sequence-in-a-weblog
 * 
网站自带test case, 要在上面写code编译通过和case. 要分析时间复杂度，还有写注释，写assumption, document.  据说写code过程会被全程记录。

先说题目：用两个hashtable来做。第一个hashtable给customer id的page归类，第二个hashtable来给three page type记录出现次数。做出来了之后貌似不难。但是在90分钟内，要先理解题意，document, 写出bug free code and make sure designed algorithm is right, 加上会紧张因为限时，还是有一定挑战的。（至少对我，对大神一切无视）

做题时开了视频和男票一起商量，先把题目发给他，然后一起讨论，他反应很快，很快就理解了题意，然后告诉了思路，我们稍加讨论，我自己写code,  算法思路是对的，但是因为使用hashmap container不是特别熟悉，因为hashmap里面还要用vector, nested container, 导致查了一些referrence浪费时间，最后debug 到compiler通过，但是test case没过，时间不够提交了。

事后自己再调试了一会儿，是hashmap的一个自带函数用错了，把结果弄出来了，思路是对的，再发给了recruiter. 也不知道会不会看。现在还在等消息，求bless了。面的是Front End SDE.

希望对大家有用。对于这种code test, 要编译通过的，对于编程熟练度要求比较高。 

 
 Assumption: log is sorted by timestamp order 
 1. create a hashtable user_visits, where key is user ID  and vlaue is the last two pages user visit
 	HashMap<Integer, List<String>> 
 2.  
 S:O(n)
 T:O(n), number of rows in log 
 
 */
/*

 Assumption: 
 		The log is sorted by timestamp
 		3 different page sequence
 Approach: 
 		
 Complexity: 
 		Time :O(nlogn),
 		Space : O(n)
 Test Case: 
 		1. content: a non-empty zero-indexed positive integer array  with duplicate
 		2. content: a non-empty zero-indexed positive integer array  with all equal elements
 		3. content: null
 		4. content: negative
 		5. size = 0
 		6. size = 1
 		7. size = large
 * */	
public class Amazon_3pagepath {

	/* 
	 * @Paramter: one list of string with user info and one list of string with page number. Both are all ordered in descending time
	 * @Return: a object contains 3 page sequence
	 */
	public ThreePageSequence find3pageSeq(ArrayList<String>log1, ArrayList<String>log2){
		
		ThreePageSequence res= new ThreePageSequence("","","");
		
		
		// A hashmap to record user and their visit history(in chronological order)
		HashMap<String, ArrayList<String>> uservisitHistory = new HashMap<String, ArrayList<String>>();
		// Perform user visit history statistics ,T: O(n), S:O(n)
		for (int i = 0 ; i < log1.size(); i++){//O(n)
			//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
			ArrayList<String> list = null;
			String user_id = log1.get(i);
			if (uservisitHistory.containsKey(user_id)){//O(1)
				list = uservisitHistory.get(user_id);
			    // to make timestamp descending become ascending
			} else {
				list = new ArrayList<String>();
			}
			//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
			list.add(0,log2.get(i));
			uservisitHistory.put(user_id, list);
		}
		System.out.println("User visiting history map: " + uservisitHistory);
		
		
		/*
		// Perform ThreePageSequence statistics, T:O(n^2),S:O(n)
		HashMap<ThreePageSequence, Integer> threepageseqCount = new HashMap<ThreePageSequence, Integer>();
		//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		for (Entry<String, ArrayList<String>> e: uservisitHistory.entrySet()){//O(n)
			//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
			ArrayList<String> pagelist = e.getValue();
			int size = pagelist.size();
			if (size <3){
				continue;
			}
			for (int i= 0 ; i < size-2;i++) {//O(n-3)
				// p1->p1->p1
				// p1->p1->p2
				// p1->p2->p1
				// p3->p1->p1->p2
				// CHECK COLLECTION ALL EQUAL
				//if (pagelist.get(i) == pagelist.get(i+1) || pagelist.get(i) == pagelist.get(i+2))
				// CHECK COLLECTION DUPLICATE
				if (pagelist.get(i) == pagelist.get(i+1) || pagelist.get(i) == pagelist.get(i+2))
					continue;
					
				ThreePageSequence newseq = new ThreePageSequence(pagelist.get(i),pagelist.get(i+1),pagelist.get(i+2));
				//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
				if (!threepageseqCount.containsKey(newseq))//O(1)
					threepageseqCount.put(newseq, 1);
				else
					threepageseqCount.put(newseq, threepageseqCount.get(newseq)+1);
			}
		}
		System.out.println(threepageseqCount);
		
		
		
		// Sort map by value, Collecitons.sort(list, new Compatator(){}), T:O(nlogn), S:O(n)
		ArrayList list = new ArrayList(threepageseqCount.entrySet());
		Collections.sort(list, new Comparator(){     //O(nlogn)
			@Override
			public int compare (Object o1, Object o2){
				//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
				Entry e1 = (Entry) o1;
				Entry e2 = (Entry) o2;
						//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
				return (int)e2.getValue() - (int)e1.getValue();
			}
		});
		System.out.println(list.get(0));
		
		
		// Get most common from sorted list
		if (list.size() != 0){
			//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
			Entry e = (Entry) list.get(0);
			res = (ThreePageSequence) e.getKey();
		}
		*/
		// Get the occurrence of each three page sequence and find the one with the maximum occurrence
		HashMap<ArrayList<String>, Integer> threepageseqCount = new HashMap<ArrayList<String>, Integer>();
		int max = 0;
		ArrayList<String> maxPattern = null;
		for (ArrayList<String> pages:uservisitHistory.values()){
			for (int i = 0 ; i <= pages.size() - 3; i++){
				// Skip size <3
				if (pages.size() <3){
					continue;
				}
				// Skip sequence with duplicate or all equal case since assume they are all different
				if (pages.get(i) == pages.get(i+1) || pages.get(i) == pages.get(i+2))
					continue;				
				ArrayList<String> pattern = new ArrayList<String>( pages.subList(i, i+3) );
				Integer count = threepageseqCount.get(pattern);
				if (count == null){
					threepageseqCount.put(pattern, count=0);
				}
				threepageseqCount.put(pattern, count+1);
				if (count +1 > max){
					maxPattern = pattern;
					max = count+1;
				}
			}
		}
		System.out.println("Occurence for each three page squence: " + threepageseqCount);
		System.out.println("Three page squence with the maximum occurence: " + maxPattern);
		//res = maxPattern;
		res= new ThreePageSequence(maxPattern.get(0),maxPattern.get(1),maxPattern.get(2));
		return res;
	}

	public static void main (String[] args){
		Amazon_3pagepath sol = new Amazon_3pagepath();
		LinkedHashMap<String,String> log = new LinkedHashMap<String, String>();
		//String[][] log = new String[][];
		//ArrayList<ArrayList<String>> log = new ArrayList<>
		ArrayList<String>log1 = new ArrayList<String>();
		ArrayList<String>log2 = new ArrayList<String>();
		
		log1.add("user1");
		log2.add("page3");
		
		log1.add("user2");
		log2.add("page2");
		
		log1.add("user1");
		log2.add("page2");	
		
		log1.add("user2");
		log2.add("page1");		
		
		log1.add("user1");
		log2.add("page1");	
		
		log1.add("user3");
		log2.add("page2");		
		
		log1.add("user3");
		log2.add("page5");
		
		log1.add("user3");
		log2.add("page2");	
		
		log1.add("user3");
		log2.add("page1");			
		
		log1.add("user4");
		log2.add("page3");	
		
		log1.add("user4");
		log2.add("page2");	
		
		log1.add("user4");
		log2.add("page1");			
		
		log1.add("user5");
		log2.add("page3");	
		
		log1.add("user5");
		log2.add("page1");
		
		log1.add("user5");
		log2.add("page2");
		
		log1.add("user5");
		log2.add("page2");
		
		log1.add("user6");
		log2.add("page7");
		
		log1.add("user6");
		log2.add("page7");
		
		log1.add("user6");
		log2.add("page7");		
		
		log1.add("user7");
		log2.add("page1");
		
		log1.add("user7");
		log2.add("page2");
		
		log1.add("user7");
		log2.add("page3");			
		System.out.println("User list:              " + log1);
		System.out.println("Page vistiting history: " + log2);
		System.out.println("Most common 3page sequence: " +  sol.find3pageSeq(log1, log2));
		// Test case: all equal
		// page1->page1->page1
		
		// Test case: denpendency=>loop
		// A->B->A
		
		// Test case: not continous
		// page1->page2->page1
		// page2->page1->page1
		// page1->page1->page2
		
	}
	
	class ThreePageSequence{
		String p1;
		String p2; 
		String p3;
		ThreePageSequence(String s1, String s2, String s3){
			this.p1 = s1;
			this.p2 = s2; 
			this.p3 = s3;
		}
		// http://www.javamadesoeasy.com/2015/02/hashmap-custom-implementation-put-get.html
		@Override
		public boolean equals(Object o){
			//1. check object is not null and equal class
			if (o == null)
				return false;
			if (this.getClass()!=o.getClass())
				return false;
			//2. cast object type and check what it is supposed to be equal
			ThreePageSequence e = (ThreePageSequence) o;
			return e.p1.equals(this.p1) && e.p2.equals(this.p2) && e.p3.equals(this.p3);
		}
		@Override
		public int hashCode(){
			return p1.hashCode()+p2.hashCode()+p3.hashCode();
		}
		@Override
		public String toString() {
			return p1+"-"+p2+"-"+p3;
		}
	}
	
}
//private void statistics (HashMap<String, ArrayList<String>> user_visit, ArrayList<String>log1, ArrayList<String>log2){
//}
