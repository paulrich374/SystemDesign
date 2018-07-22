
public class Amazon_listIntersection {
/*Q1：https://github.com/wangchj/leetcode-cases
 * http://www.1point3acres.com/bbs/thread-38652-1-1.html
 * http://www.chegg.com/homework-help/questions-and-answers/given-two-lists-integers-write-function-returns-list-contains-intersection-elements-occur--q4260184
Given two lists of integers, 
write a function that returns a list that contains only the intersection (elements that occur in both lists) of the two lists. 
The returned list should only contain unique integers, no duplicates.
For example, [4, 2, 73, 11, -5] and [-5, 73, -1, 9, 9, 4, 7] would return the list [-5, 4, 73] in no particular order.
 
 * 
 * 
第一题先sort，然后用merge sort里面merge的思想做。
第二题有好几种方法，中序遍历，打印出来应该是从小到大。或者用递归的方法做：如果是BST，那么左右都是BST。同事root大于左边最大值，右边最小值。
第三题，典型的divide and conquer，google一下closest pairs

第一题用hashmap找重复就可以，
第二题老题，最好办法是min max 范围，只要O(n), 
第三题，只要用maxheap做就行，建立heap不要指定大小，为空就可以。然后是O(nlg(k)) 其实就是 O(n)
最难也就是深度优先写递归
 * */
}
