
// https://en.wikipedia.org/wiki/Rope_(data_structure)

/**
 * Java Program to Implement Rope
 * 
Index[edit]     public char indexAt(int ind)


Figure 2.1: Example of index lookup on a rope.
Definition: Index(i): return the character at position i
Time complexity: O(log N)

****** Concat[edit]       public void concat(String str)


Figure 2.2: Concatenating two child ropes into a single rope.
Definition: Concat(S1, S2): concatenate two ropes, S1 and S2, into a single rope.
Time complexity: O(1) (or O(log N) time to compute the root weight)

****** Split[edit]

Figure 2.3: Splitting a rope in half.
Definition: Split (i, S): split the string S into two new strings S1 and S2, S1 = C1, …, Ci and S2 = Ci + 1, …, Cm.
Time complexity: O(log N)

Insert[edit] = a Split() and two Concat()

Definition: Insert(i, S’): insert the string S’ beginning at position i in the string s, to form a new string C1, …, Ci, S’, Ci + 1, …, Cm.
Time complexity: O(log N).

Delete[edit] =  two Split() and one Concat()

Definition: Delete(i, j): delete the substring Ci, …, Ci + j − 1, from s to form a new string C1, …, Ci − 1, Ci + j, …, Cm.
Time complexity: O(log N).

****** Report[edit]
Definition: Report(i, j): output the string Ci, …, Ci + j − 1.
Time complexity: O(j + log N)
 **/
 
import java.util.Scanner;
 
/** Class RopeNode **/
class RopeNode
{
    RopeNode left, right;
    String data;
    int weight;    
    /** Constructor **/
    public RopeNode(String data)
    {
        this.data = data;
        left = null;
        right = null;
        weight = data.length();
    }
    /** Constructor **/
    public RopeNode()
    {
        data = null;
        left = null;
        right = null;
        weight = 0;
    }
}
 
/** Class Rope **/
class RopeSimple
{
    RopeNode root;
 
    /** Constructor **/
    public RopeSimple()
    {
        root = new RopeNode("");
    }
    /** Function to clear rope **/
    public void makeEmpty()
    {
        root = new RopeNode("");
    }
    /** Function to concat an element 
     * 
     * Concat(S1, S2): concatenate two ropes, S1 and S2, into a single rope
     * 
     * A concatenation can be performed simply by creating a new root node with left = S1 and right = S2, 
     * which is constant time. 
     * The weight of the parent node is set to the length of the left child S1, 
     * which would take O(log N) time, if the tree is balanced.
     * 
     * **/
    public void concat(String str)
    {
    	
        // Creating a new node 
        RopeNode newRoot = new RopeNode();    	
        RopeNode nptr = new RopeNode(str);
        // Left is S1(old root)
        newRoot.left = root;
        // Right is S2(new appended strign node)
        newRoot.right = nptr;
        // The weight is length of the left child
        newRoot.weight = newRoot.left.weight ;
        if (newRoot.left.right != null)
            newRoot.weight += newRoot.left.right.weight;
        root = newRoot;
    }
    /** Function get character at a paricular index 
     *  
     *  Index(i): return the character at position i
     *  
     *  Time complexity: O(log N)
     *  To retrieve the i-th character, we begin a recursive search from the root node:
     * 
     * 
     * **/
    public char indexAt(int ind)
    {
    	//  start at the root node
        RopeNode tmp = root;
        // greater than
        if (ind > tmp.weight)
        {
            ind -= tmp.weight;
            return tmp.right.data.charAt(ind);
        }
        // less than
        while (ind < tmp.weight)
            tmp = tmp.left;
        ind -= tmp.weight;
        return tmp.right.data.charAt(ind);            
    }
    /** Function get substring between two indices
     * 
     *  Definition: Report(i, j): output the string Ci, …, Ci + j − 1.
     *  
     *  Time complexity: O(j + log N)
     *  To report the string Ci, …, Ci + j − 1, find the node u that contains Ci and weight(u) >= j, and then traverse T starting at node u. Output Ci, …, Ci + j − 1 by doing an in-order traversal of T starting at node u.
     *  
     *  **/
    public String substring(int start, int end)
    {
        String str = "";
        boolean found = false;
        RopeNode tmp = root;
        if (end > tmp.weight)
        {
            found = true;
            end -= tmp.weight;
            if (start > tmp.weight)
            {
                start -= tmp.weight;
                str = tmp.right.data.substring(start, end);
                return str;
            }
            else
                str = tmp.right.data.substring(0, end);            
        }        
        if (!found)
        {
            while (end <= tmp.weight)
                tmp = tmp.left;
            end -= tmp.weight;
            if (start >= tmp.weight)
            {
                start -= tmp.weight;
                str = tmp.right.data.substring(start, end) + str;
                return str;
            }
            str = tmp.right.data.substring(0, end);            
        }    
        tmp = tmp.left;
        while (start < tmp.weight)
        {
            str = tmp.right.data + str;
            tmp = tmp.left;
        }
        start -= tmp.weight;
        str = tmp.right.data.substring(start) + str;    
 
        return str;        
    }
    
    

    /** Function to print Rope **/
    public void print()
    {
        print(root);
        System.out.println();
    }
    private void print(RopeNode r)
    {
        if (r != null)
        {
            print(r.left);
            if (r.data != null)
                System.out.print(r.data);
            print(r.right);
        }
    }    
}
 
/** Class RopeTest **/
public class GoogleRopeDataStructure
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        /** Creating object of Rope **/
        RopeSimple r = new RopeSimple(); 
        System.out.println("Rope Test\n");          
        char ch;
        /**  Perform rope operations  **/
        do    
        {    
            System.out.println("\nRope Operations\n");
            System.out.println("1. concat ");
            System.out.println("2. get character at index");
            System.out.println("3. substring");
            System.out.println("4. clear");
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter string to concat");
                r.concat( scan.next() );                     
                break;                          
            case 2 : 
                System.out.println("Enter index");
                System.out.println("Character at index = "+ r.indexAt(scan.nextInt()));
                break;                         
            case 3 : 
                System.out.println("Enter integer start and end limit");
                System.out.println("Substring : "+ r.substring( scan.nextInt(), scan.nextInt() ));
                break;                                          
            case 4 :  
                System.out.println("\nRope Cleared\n");
                r.makeEmpty();
                break;            
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }
            /**  Display rope  **/ 
            System.out.print("\nRope : ");
            r.print();
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y'); 
    }
}