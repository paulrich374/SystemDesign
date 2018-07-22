import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Amazon_BFSsocialFriendCourse {
/*https://github.com/wangchj/leetcode-cases
 * http://instant.1point3acres.com/thread/138834
 *  dependency -> loop, cycle
 *  sort
 *
 *  要求把用户社交网络内所有好友的课程进行统计并排序（按选课的好友数），
 *  最后将拍好序的课程(从高到低)装在一个vector中输出
 *  2. how do you test your code or give some test case
 *  这种test case就是要想到一些边界的条件吧。
 *  比如说3个人相互好友，然后选了同一门课之类

	 Assumption: 
	 	
	 Approach: 
	 	XXXXXX Use a queue to perform a BFS ( WRONG!!! MORE ONE TWO LEVEL)
	 	Use 
		XXXXXX Use a hashset to store all friends when performing BFS
		Use a hashset to store all friends when performing two loop iteration
	 	The resaon of using set is to avoid repeat friend 
	 	Use a hashmap to map course and its occurences
	 	Sort the hashmap by its occurences(sort map by value)
	 Complexity:
	 	Time Complexity: O(nlogn), sort map by values
	 	Space Complexity: O(n)
	 Test Case: 
	 	0. Dependency : loop (duplicate)
	 	1. content: duplicate
	 	2. content: all equal 
	 	3. content: null
	 	4. content: 
	 	5. size = 0
	 	6. size = 1
	 	7. size = large
	 	8. Test case: dependency -> loop
	   9. Test case: same course for all user
	   10.Test case: duplicate user
	   11. Test case: duplicate course (course with the same largest occurences) 
 * */
	static HashMap<String, ArrayList<String>> friendsList = new HashMap<String, ArrayList<String>>();
	static HashMap<String, ArrayList<Integer>> courseList = new HashMap<String, ArrayList<Integer>>();
	/*
	 * @Paramter: a string which represents a user name
	 * @Return: a list of integer which represents a ordered course list
	 * */	
	public ArrayList<Integer> friendsCourseStatistics(String user){
		
		ArrayList<Integer> res = new ArrayList<Integer>();	
    	
		// Validate the input
		if (user == null || user.length() == 0 )
    		return res;
    	
    	
		// Two level friends statistics from friend graph, get all "social" friends = >O(k*m)
		HashSet<String> twolevelfriends = new HashSet<String>();
		for (String s:getUserFriends(user)){
			twolevelfriends.add(s);
			for (String s2: getUserFriends(s)){
				twolevelfriends.add(s2);
			}
		}
		// loop and exclude yourself
		if (twolevelfriends.contains(user))
			twolevelfriends.remove(user);
		System.out.println("Final twolevelfriends:"+twolevelfriends);
		
		/* WRONG!!! MORE ONE TWO LEVEL
		HashSet<String> twolevelfriends2 = new HashSet<String>();
    	LinkedList<String> queue = new LinkedList<String>();
    	queue.add(user);
    	twolevelfriends2.add(user);
    	while(!queue.isEmpty()){
    		String cur = queue.poll();
    		for (String m: getUserFriends(cur)){
    			if (!twolevelfriends2.contains(m)){
    				queue.add(m);
    				twolevelfriends2.add(m);
    			}
    		}
    	}	
		System.out.println("Final twolevelfriends2:"+twolevelfriends2);
    	*/
    	
		
		
		// Course statistics from all of your "social" friends = >O(k*m*o)
		HashMap<Integer, Integer> courseCount = new HashMap<Integer,Integer>();
		for (String friend: twolevelfriends){
			for (Integer id: getCourse(friend)){
				if (courseCount.containsKey(id)){
					courseCount.put(id,courseCount.get(id)+1 );
				} else {
					courseCount.put(id,1 );
				}
			}
			System.out.println("course count:"+courseCount+". friend:"+friend);
		}
		
		
		
		// Sort the map by value = >O(nlogn)
		ArrayList list = new ArrayList(courseCount.entrySet());
		Collections.sort(list, new Comparator(){
			@Override
			public int compare (Object o1, Object o2){
				Entry e1 = (Entry) o1;
				Entry e2 = (Entry) o2;
				return (int)e2.getValue() - (int)e1.getValue();
			}
		});
		res = list;
		return res;
	}
	
	/* Given funciton
	 * @Parameter:
	 * @Return:
	 * */
	private ArrayList<String> getUserFriends(String user){
		ArrayList<String> res = new ArrayList<String>();
		if (user == null || user.length() == 0)
				return res;
		if (friendsList.containsKey(user)){
			res = friendsList.get(user);
		}
		return res;
	}
	/* Given funciton
	 * @Parameter:
	 * @Return:
	 * */
	private ArrayList<Integer> getCourse(String user){
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (user == null || user.length() == 0)
				return res;
		if (courseList.containsKey(user)){
			res = courseList.get(user);
		}
		return res;		
	}
	
	
	
	public static void main (String[] args){
		Amazon_BFSsocialFriendCourse sol = new Amazon_BFSsocialFriendCourse();
		String[] list = {"b","c"};
		friendsList.put("a", new ArrayList<String> (Arrays.asList(list)));
		list = new String[]{"c","d"};
		friendsList.put("b", new ArrayList<String> (Arrays.asList(list)));
		list = new String[]{"e","a"};
		friendsList.put("c", new ArrayList<String> (Arrays.asList(list)));
		list = new String[]{"f","g"};
		friendsList.put("e", new ArrayList<String> (Arrays.asList(list)));
        System.out.println(friendsList);
		// a-> b,c,
		// b-> c,d
		// c-> e,f
		Integer[] list2 = {1,2};
		courseList.put("a", new ArrayList<Integer> (Arrays.asList(list2)));
		 list2 = new Integer[]{3,4};
			courseList.put("b", new ArrayList<Integer> (Arrays.asList(list2)));

		 list2 = new Integer[]{1,3,4};
			courseList.put("c", new ArrayList<Integer> (Arrays.asList(list2)));

		 list2 = new Integer[]{2,4};
			courseList.put("d", new ArrayList<Integer> (Arrays.asList(list2)));

		 list2 = new Integer[]{3,4,5};
			courseList.put("e", new ArrayList<Integer> (Arrays.asList(list2)));

		 list2 = new Integer[]{9,14,16};
			courseList.put("f", new ArrayList<Integer> (Arrays.asList(list2)));
			 list2 = new Integer[]{19,14,16};
				courseList.put("g", new ArrayList<Integer> (Arrays.asList(list2)));		
	        System.out.println(courseList);
	        
	     System.out.println("Course ranking: "+ sol.friendsCourseStatistics("a"));   
       // Test case: dependency -> loop
	   // Test case: same course for all user
	   // Test case: duplicate user
	   // Test case: duplicate course (course with the same largest occurences) 
	     
	}
}
/*
for (String s:getUserFriends(user)){
	//System.out.println("FirstLevel:" +s);
	twolevelfriends.add(s);
	for (String s2: getUserFriends(s)){
		//System.out.println("SecondLevel:" +s2);
		twolevelfriends.add(s2);
	}
	//System.out.println(twolevelfriends);
}
// loop and exclude yourself
if (twolevelfriends.contains(user))
	twolevelfriends.remove(user);
System.out.println("Final:"+twolevelfriends);
		// Collections.sort => list, array 
		//                     map ==> list or array
*/
