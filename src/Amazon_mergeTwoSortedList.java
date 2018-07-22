
public class Amazon_mergeTwoSortedList {
/*
Given two sorted singly linked lists, implement a function to merge the two lists into a single sorted list and return its head. You may destroy the original lists if you want. 

You may use the JDK or the standard template library. The solution will be evaluated on correctness, runtime complexity (big-O), and adherence to coding best practices. A complete answer will include the following: 

Document your assumptions 
Explain your approach and how you intend to solve the problem 
Provide code comments where applicable 
Explain the big-O run time complexity of your solution. Justify your answer.
Identify any additional data structures you used and justify why you used them. 
Only provide your best answer to each part of the question. 

Example: 

Input: 

List 1: 1->2->3->4 

List 2: 1->3->5->7 

Output: 

1->1->2->3->3->4->5->7 

Use one of the following skeletons for your solutions. 
Java: 
Use one of the following skeletons for your solutions. 
Java: 

public class Node { 
public int value; 
public Node next; 
public Node() { 
value = 0; 
next = null; 
} 
public Node(int value, Node next) { 
this.value = value; 
this.next = next; 
} 
} 

public class MergeListProblem { 
public static Node mergeLists(Node head1, Node head2) { 
// your code goes here 
} 
} 
 * */
/*
 Assumption:
 
 
 Approach:
    maintain two extra pointer point to the head of two lsits
    iterate two list if not hit the end
    Compare node val and appned smaller to the result list 
    By adjusting each node's pointer to next, we can have 
    
    Create a dummy head with value 0 and point next to the l1
    When comparing, 
    if l1 value < l2 value
		move l1 one step ahead
	otehrwise
	    12 point to l1
	    l1 move one step ahead
	    l2 point to temp(l2.next)
	move dummpy list one step ahead
	when l2 has left
	just append l2 to dummy
	return dummy.next
 Compleixty: 
    Time:O(n+m)
    Space:O(n+m)
    (1)O(len1+len2) time complexity in worst case, and O(min(len1,len2)) in best 
(2)O(1) space complexity
 Test CasE:
   	1.(size) one is empty
   	2.(size) two is empty
   	3.(size) l1 len > l2 len
   	4.(size) l1 len < l2 len
   	5. (content) duplicate
   	6. (content) all eaual
   	7. (content) 
   	   	
 **/
	
	/*
	 * @Parameter: two lsit l1 and l2
	 * @Return:a merged list 
	 * */
	public Node mergeLists(Node l1, Node l2) { 
		// Validate the input
		if (l1 == null && l2 == null){
			return null;
		}
		if (l1 == null){
			return l2;
		}
		if (l2 == null ){
			return l1;
		}
		
		// 
		Node fakeHead = new Node(0);
		fakeHead.next = l1;
		Node p1 = fakeHead;
		//Node p2 = l2;
		//while (p1.next != null && p2 != null){
			//if (p1.next.value < p2.value){
			//	p1 = p1.next;
			//} else {
			//	Node temp1 = p1.next;
			//	Node temp2 = p2.next;
			//	p1.next = p2;
			//	p2.next = temp1;
			//	p2 = temp2;
			//	p1 = p1.next;
			//}
		//}
		while (l1 != null && l2 != null){
			// l1 move one step ahead	
			if (l1.value < l2.value){
				l1 = l1.next;
			}else{
			// l2 move one step ahead	
				Node temp = l2.next;
				l2.next = p1.next;
				p1.next = l2;
				l2 = temp;
			}
			// result list  move one step ahead	
			p1 = p1.next;
		}
		
		//NOTE: while (p2 != null){
		//if (p2 != null){
		//	p1.next = p2;
		//}
		if (l2 != null)
			p1.next = l2;
		return fakeHead.next;
	} 
	public static void main(String[] args){
		Amazon_mergeTwoSortedList sol = new Amazon_mergeTwoSortedList();
		Node l1 = new Node(1);
		
		l1.next = new Node(2);
		l1.next.next = new Node(3);
		l1.next.next.next = new Node(4);

		Node l2 = new Node(1);
		l2.next = new Node(3);
		l2.next.next = new Node(5);
		l2.next.next.next = new Node(7);
		
		//List 1: 1->2->3->4 

		//List 2: 1->3->5->7 

		//Output:  1->1->2->3->3->4->5->7 
		
		System.out.println("Merged list: "+sol.mergeLists(l1,l2));
		l1 = new Node(1);
		l1.next = new Node(2);
		l1.next.next = new Node(3);
		l1.next.next.next = new Node(4);		
		System.out.println("Merged list: "+sol.mergeLists(l1,null));
		l2 = new Node(1);
		l2.next = new Node(3);
		l2.next.next = new Node(5);
		l2.next.next.next = new Node(7);		
		System.out.println("Merged list: "+sol.mergeLists(null,l2));
		System.out.println("Merged list: "+sol.mergeLists(null,null));
	}
}
 class Node { 
	public int value; 
	public Node next; 
	public Node(int val) { 
		value = val; 
		next = null; 
	} 
	public Node(int value, Node next) { 
		this.value = value; 
		this.next = next; 
	} 
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Node node = this;
		while(node!= null){
			sb.append(node.value);
			if (node.next != null)
				sb.append("->");
			node = node.next;
		}
		return sb.toString();
	}
} 
