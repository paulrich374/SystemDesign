
public class NEW_CallManyTimes {
/*
 	Leetcode: Read N Characters Given Read4 – Call multiple times (stateful -> memoize perivous state)
 		1. One time
 			read(1) ["abcde"]
 				read4=>"abcd"
 				buf =>"a" and read4 left "bcde" DROP!
 		2.Multiple Times
 			read(1) ["abcde"]
 				read4=>"abcd"
 				buf =>"a" and read4 left "bcde" => offset=offset+bytes = (0+1)%4=1 and bufsize = size(from this time)-bytes= 4-1=3
 			read(2) 
 			    read4<="bcd"
 			    buf =>"bc" and read4 left "d"=> offset = offset+bytes =(1+2)%4=3 and bufsize = size(from last time)-bytes = 3-2=1	
 	
 	
 	17.2 Design an algorithm to figure out if someone has won a game oftic-tac-toe.(save time and waste space)
 			Clarify1: once or multiple time
 			Clarify2: 3X3 or NXN
 			Calrify3:
 			Ans1 Called many times:
 				3X3 bord for each cell has 3 possibility, so 3*3*..# = 3^9 board possibilities
 				// Hash Key - int board, and Hash Value - win or not
 				public int hasWon(int board){
 					return winnerHashtable[board]
 				}
 				// Rolling Hash - Base3 conversion, 3^0 * cell(0,0) + 3^1 * cell(0,1) + 3^2 * cell(0,2) +
 				 * 					+ 3^3 * cell(1,0)+... +3^8 * cell(2,2)
 				public static int convertBoardToInt(char[][] board){
 					int factor =1;
 					int sum = 0;
 					for (int i =0 ;i < b.length;i++){
 						for (int j = 0 ;j < b[0].length;i++){
 							int v = 0;
 							if (board[i][j] == 'X'){
 								v = 1;
 							} else if (board[i][j] == 'O') {
 							    v = 2;
 							}
 							sum+= v*factor;
 							factor*=3;
 						}
 					}	
 				}
 			Ans2 Called Once:
 			  	Piece hasWon1 (Piece[][] board){
 					// Check rows
 					// Check Columns
 					// Check diagonal
 					for (int i=0 ; i< 3;i++){
 				    	// Check rows
 						if (board[i][0] != Piece.Empty && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
 							return board[i][0];
 						}
 						// Check Columns
  						if (board[0][i] != Piece.Empty && board[0][i] == board[1][i] && board[1][i] == board[2][i]){
 							return board[0][i];
 						}
 						// Check diagonal		
 						if (board[0][0] != Piece.Empty  && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
 							return board[0][0];
 						}			
 						// Check reverse diagonal
 						if (board[2][0] != Piece.Empty && board[2][0] == board[1][1] && board[1][1] == board[0][2])
 							return board[2][0]
 					}
 					return Piece.Empty;
 				}
	18.5 You have a large text file containing words. Given any two words, find the shortest
		distance (in terms of number of words) between them in the file. If the operation
		will be repeated many times for the same file (but different pairs of words), can you
		optimize your solution? 
		  		Ans: Call many times, operation will be repeated many times
		  		https://segmentfault.com/a/1190000003906667
		  		[LeetCode] Shortet Word distance I, II, III
		  		ASSUME: 
		  			Appearing order doesn't matter	
		  		APPROACH:
		  		    Traverse the file just once
		  		    last seen word1 and word2, storing the location in 
		  		    lastPosWord1 and lastPosWord2
		  		    When we come across word1, we compare it to lastPosWord2
		  		    ans update min as necessaey, and then update lastPosWord1
		  		    We do the equivalence operation on word2
		  		    At the end of traversal, we will have the minimum distance
		  		 Complexity:
		  		 	T:O(n),
		  		 	S:O(1)
		  		 Test Case:
		  		    same word
		  		    null word
		  		    large
		  		    small
		  		  
		  		    	
		  		  Ans: Call once
		  		  		public int shortest(String[] words, String word1, Stirng word2){
		  		  			if (words.length == 0 || words == null || word1 == null || word1.length() == 0 || word2 == null || word2.length() ==0)
		  		  				return 0;
		  		  			int min = Integer.MIN_VALUE;
		  		  			int lastPosWord1 = -1;
		  		  			int lastPosWord2 = -1;
		  		  			
		  		  			for (int i = 0; i < words.length;i++){
		  		  				String currentWord = words[i];
		  		  				if (words[i].equals(word1)){
		  		  					// Not the initial pos, we calculate
		  		  					if (lastPosWord2 > 0){
		  		  						min = Math.min(min, abs(i-lastPosWord2));
		  		  					}	
		  		  					lastPosword1 = i;
		  		  				} else if (words[i].equals(word2)){
		  		  					// Not the initial pos, we calculate
		  		  					if (lastPosWord1 > 0){
		  		  						min = Math.min(min, abs(i-lastPosWord1));
		  		  					}	
		  		  					lastPosWord2 = i;
		  		  				}
		  		  			}
		  		  			return min;
		  		  		}
		  		    	
		  		    	
		  		  Ans: Call many times
		  		  		hash table with each word and the locations where it occurs
		  		  		We then just need to find the minimum difference berwteeen a value is listA and a value in listB
		  		  		[2Poitner problem] find the smallest difference
		  		  		listA:{1,2,9,15,25}
		  		  		listB:{4,10,19}
		  		  		
		  		  		Sol#1 : merge two sorted list O(n+m)
		  		  			list: {la, 2a, 4b, 9a, I0b, 15a, I9b, 25a} 
				  			Find the minimum distance is now just a matter of traversing the merged lsit to find 
				  			the minimum distance between two consecutive numbers which have different list tags.
				  			In this case, the slution would be a distance of 1 (between 91 and 10b)
				  			After the initial indexing of the file, this takes O(p + k) time, where p and k are the number of occurences of each word
				  		
				  		Sol#2: O(n+m) no back check
				  			for ()
				  				for () 
	
	
	18.6 Describe an algorithm to find the smallest one million numbers in one billion
numbers. Assume that the computer memory can hold all one billion numbers
		  		Solution 1: Sorting 
		  			We can sort the elements in ascending order and then take the first million numbers from that
		  			The time complexity is 0(n log(n)).
		  		
		  		Solution 2: Min Heap
				  	We can use a min heap to solve this problem
				  	We first create a max heap (largest element ) for the first million numbers.
				  	Then, we traverse through the lsit. On each element, we insert it into the list and delete teh alrgest elemnt
				  	At the end of traversal, we will have a heap containing the smallest one million element
				  	This algorithm is 0(n log(m)), where m is the number of values we are
					looking for.
					
				Solution 3: Selection Rank Algorithm (if you can modify the original array)
					find the ith smallest (or largest) elemtn in an array in linear time
					If the elements are unique, you can find the ith smallest element in expected 0(n) time
					The basic algorithm oeprates like this
					step 1.
						Pick a random eleemtn in the array and use it as a 'pivot'.
						Partition elements around the pivot, keeping track of the number of elements on the left side of the partiion
						
					step2.
						If there are exactly i elements on the left, then you just return the biggest element on the left
					step3.
						If the left side is bigger than i, repeat the algorthm just the left part of the array
					step4. 
						If the left side is smaller than i, repeat the algorithm on the right, but look for the element with rank i - leftSize
 * */
}
