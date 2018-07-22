
public class Amazon_LastKelements {
/*http://www.careercup.com/question?id=6210476388122624
(17) A museum is selling tickets to a fundraiser. 
As people place orders, their customer IDs are appended to a linked list. 
Due to an error, too many tickets have been sold and the unfortunate customers must now be notified that they cannot purchase a ticket after all. 
Given the list and the number of customers to notify (k), return the node in the list for the first customer that needs to be notified. 
An optimal solution will not use any additional data structures. 
You may assume k is less than the number of nodes in the list. 
You may use the JDK or the standard template library. Your solution will be evaluated on correctness,
 runtime complexity (big-O), and adherence to coding best practices. A complete answer will include the following:
Document your assumptions
Explain your approach and how you intend to solve the problem
Provide code comments where applicable
Explain the big-O run time complexity of your solution. Justify your answer.
Identify any additional data structures you used and justify why you used them.
Only provide your best answer to each part of the question.
Example:
Input: myList: 1 -> 8 -> 4 -> 2 -> 7 -> 13 -> 3 k: 2
Output: 13 -> 3

Last K elements, 老生常谈……
 * */
/*
 Assumption:
 1. a singly linked list
 Approach:
 1. maintain two pointers say, walker and runner
 2. Initially, let both of them point to the start of list
 3. Now, move runner ahead by k elements. So runner and walker are k distance apart
 4. Now, move both pointers one step ahead each time until runner.next becomes null
    i.e., runner points to the last element of the list
 Xiterate two poitners until runner hit the end of list
 5. Since walker and runner are k distance apart, the part of the list startign from the element pointed by
    walker will be the desired output
 X, walker.next is the last K element
 
 
 Complexity: O(n)
 
 Complexity:
 time = O(n), space = O(1)
 Test Case:
 1. (size) 0
 2. (size) 1
 3. (size) normal
 4. (size) large
 5. (int) k<=0
 6. (int)k > len X
 * */	
	
	/*
	 * */
	public Node lastK( Node head,int k){
		// Vlaidate the input
		if (head ==null && k == 0){
			return null;
		}
		// Mantain two pointers and runner move k steps first
		Node walker = head;
		Node runner = head;
		//for (int i = 0 ; i < k; i++){
		int i = 0;
		while (runner!= null && i <k)	{
			runner = runner.next;
			i++;
		}
		// Iterate two pointers until runner hit the end
		while(runner!= null){
			walker= walker.next;
			runner = runner.next;
		}
			
		return walker;	
	}
	public static void main(String[] args){
		Amazon_LastKelements sol =new Amazon_LastKelements();
		// 1 -> 8 -> 4 -> 2 -> 7 -> 13 -> 3 k: 2
		Node head = new Node(1);
		head.next = new Node(8);
		head.next.next = new Node(4);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(7);
		head.next.next.next.next.next = new Node(13);
		head.next.next.next.next.next.next = new Node(3);
		System.out.println(sol.lastK(head,2));
		head = new Node(1);
		System.out.println(sol.lastK(head,1));
		head = null;
		System.out.println(sol.lastK(head,0));	
		head = new Node(1);
		System.out.println(sol.lastK(head,-1));		
	}
}
/*
 *  The solution is same as finding kth element from the end of list. 
Use two pointers, say ptr1 and ptr2. 
Initially, let both of them point to the start of the list. 
Now, move ptr2 ahead by k elements. So ptr1 and ptr2 are k distance apart. 
Now move both the pointers one step ahead each time until ptr2->next becomes null, i.e ptr2 points to last element of the list. 
Since ptr1 and ptr2 are k distance apart, the part of the list starting from the element pointed by ptr1 will be the desired output.*/
