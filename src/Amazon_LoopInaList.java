
public class Amazon_LoopInaList {
	
		/*	http://www.careercup.com/question?id=5968086721101824
		 * Merge two sorted list http://40.careercup.appspot.com/question?id=6284997057052672   Assuming the LinkedList head starts at the least significant digit for both a and b:

		 * Assumption: 
A singly linked list 
		 * Approach: 
Use two pointers to iterate through the list until two pointers meet 
If the two poitners meet, there is a cycle
If the two pointers never meet, there is no cycle   

注億注億注億注億注億
Maintain two pointers fast and slow. 
fast change as follows fast = fast.next.next 
slow change as follows slow= slow.next 
If they meet at some point then there is a loop. 
If fast reach the end of the list then there is no loop.        
		      
		 * Time Complexity: 
 O(n)
		 * Space Complexity: 
 O(1)
		  
		 * Test Case: 
 1. Test case:(structure) cycle
 2. Test case:(structure) no cycle
 3. Test case:(structure) null
		 
	 */	
	/*
(1) Given a linked list of integers, write a function to determine whether the given list has a loop or cycle anywhere in the list. The integer values may not be relied upon to be distinct. You may use the JDK or the standard template library. Your solution will be evaluated on correctness, runtime complexity (big-O), and adherence to coding best practices. A complete answer will include the following:
Document your assumptions
Explain your approach and how you intend to solve the problem
Provide code comments where applicable
Explain the big-O run time complexity of your solution. Justify your answer.
Identify any additional data structures you used and justify why you used them.
Only provide your best answer to each part of the question.

Cycle detection,老生常谈……
	 * */
	
		/* @Paramter: a list
		 * @Return: a boolean value to indicate a cycle or not
		 * */
		public boolean hasLoops( ListNodeLoop head ) {
			    // Validate the input
				if (head == null){
					return false;
				}
				// Iterate two pointers to check if they meet at some point
			    ListNodeLoop runner = head;
			    ListNodeLoop walker = head;
			    while (runner.next != null && runner.next.next != null){
			    	walker = walker.next;
			    	runner = runner.next.next;
			    	if (walker == runner){
			    		return true;
			    	}
			    }
				return false;
		} 
		public static void main(String[] args){
			Amazon_LoopInaList sol = new Amazon_LoopInaList();
			ListNodeLoop head = new ListNodeLoop(1);
			ListNodeLoop cycle =new ListNodeLoop(4);
			head.next = new ListNodeLoop(2);
			head.next.next = new ListNodeLoop(3);
			head.next.next.next = cycle;
			head.next.next.next.next = new ListNodeLoop(5);
			head.next.next.next.next.next = new ListNodeLoop(6);
			head.next.next.next.next.next.next = new ListNodeLoop(7);
			head.next.next.next.next.next.next.next = new ListNodeLoop(8);
			head.next.next.next.next.next.next.next.next = cycle;
			System.out.println("Cycle:"+sol.hasLoops(head));
			ListNodeLoop head2 = new ListNodeLoop(10);
			head2.next = new ListNodeLoop(11);
			head2.next.next = new ListNodeLoop(12);
			head2.next.next.next = new ListNodeLoop(13);;
			head2.next.next.next.next = new ListNodeLoop(5);
			head2.next.next.next.next.next = new ListNodeLoop(6);
			head2.next.next.next.next.next.next = new ListNodeLoop(7);
			head2.next.next.next.next.next.next.next = new ListNodeLoop(8);	
			System.out.println("Cycle:"+sol.hasLoops(head2));
			ListNodeLoop head3 = null;
			System.out.println("Cycle:"+sol.hasLoops(head3));
		}

		

}
 class ListNodeLoop { 
	 public int value; 
	 public ListNodeLoop next;
	 ListNodeLoop(int val){
		 value = val;
		 next = null;
	 }
} 
