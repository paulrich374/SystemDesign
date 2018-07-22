/*
enefits有很多个部门，是层级结构，
比如R&D这个部门下有 software department 部门，
而 software department 部门下又有 backend 和 frontend 等等。
其中每个部门里有主管，有员工。
 * */
public class DatabaseTreeStructure {
/*
          				R&D
       			/         |        \   \ 
  		  software    hardware    hr  marketing 
   		/ 	      \
	frontend 	backend  
(Boss,Employees) 
======== Me
Table :user organization boss or employee
--------....

======== NineChapter
Table1: Organization
Organization ID
Name
Level
Father

Table2: Staff
Staff ID
Name
Organization ID(the bottom layer)
Title
==> Organization level(1,2,3) Fast to track

10k ppl

====== MongoDB
Table1: Organization
Organization ID
Name
Level
1. Child
2. FatherX 
3. list of ancestors => array 
4. list of ancestors= >String (root to here path)
5. (organization+left or right=> convenitent for inorder traversal


Query:
2. direct father FAST
1. direct child FAST
1.2. = >all children SLOW
3. (solve 1,2 all children slow issue )all children of "Programming" => use list of ansestor to find out  any ancestor list has "Programming"
3. Drawback: add or delete need to update all list of ancestor
4. Use String to support MySQL
5. 优点是查询一个部门的所有子节点会非常快，缺点也是增删会比较复杂。
   All children fast but add/delete node slow
 * */
}
