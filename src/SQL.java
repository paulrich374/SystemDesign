
public class SQL {
	/*
     Core1. relationship
     Core2. many edit operation
     Core3. stable 
     Core4. Bank
     Core5. MySQL: a service/product to implement SQL
     Core6. SQL: a rule, language, standard
     Core7. normalization and entity(combinational state). e.g., user has username, which class, date of birth.etc
     

     I. http://blog.codinglabs.org/articles/theory-of-mysql-index.html
 
I. http://blog.codinglabs.org/articles/theory-of-mysql-index.html
   B-Tree/B+Tree
   Index: core1->get data fast/search data fast, a special algorithm to search data on column/attribute
   Search: linear search O(n)
           binary search O(logn) sorted
           binary tree search O(logn)
   BST: at most two children        
   B-Tree: generalization of BST with more than two children and self balancing 
           search:O()
           15       56         77          3 keys     n-1 keys
           data     data       data
         |       |         |          |    4 poitners n pointers
            20      49 
            data    data
          |      |
         null    null  
      d:=> d<=n<=2d, n pointers
      h:height <= logd((N+1)/2), binary O(log2((N+1)/2)) 
      search: O(logdN), binary O(log2N)
      insert/delete: split, merge, shift
      node/pair: key,value = id, data
      search: from root and do BS
      BTree_Search(node, key){
      	if (node == null )
      		reutrn null;
      	foreach(node.key){
      	
      		if (node.key[i] == key)
      		 	return node.data[i];
      		 if (node.key[i] > key)// e.g., 12, 50, 70
      		    return BTree_Search(point[i]->node, key);
      	}
      	return BTree_Search(point[i+1]->node, key);//e,g., 90
      }
      data = BTree_Search(root, my_key);
     https://www.quora.com/What-is-the-difference-between-a-binary-tree-a-binary-search-tree-a-B-tree-and-a-B+-tree
     http://www.geeksforgeeks.org/b-tree-set-1-introduction-2/      
   B+Tree: the keys and data are stored in leaves, leaf may incude a pointer to next leaf to speed sequential access.
           15       56         77          3 keys     n keys
                       
                |         |          |    3 poitners n pointers
   15          20          49 
            |          |        |
          15   18  ->  20  30
          data data    data data
              
      2d pointers instead of 2d+1
   RAM:
      data
      address
      matrix row*column = address
      matrix content: data
      read: input: address, output:data
      write: input:address and data
      主存存取的时间仅与存取次数呈线性关系，因为不存在机械操作
      两次存取的数据的“距离”不会对时间有任何影响，
      例如，先取A0再取A1和先取A0再取D3的时间消耗是一样的。A-D:column, 0-3:row
   Disk I/O:         
      NOTE: index is so big that it is stored as a document on the Disk
      Step1: which disk(disk plater)
      Step2: which track(same center circle)=> Track seek Time
      Step3: which sector=> rotate Time
      Pre-fetech can reduce I/O seek
      Pre-fetech size = 4K*X=page*X
II. MyISAM and InnoDB 
   MyISAM: B+Tree
   InnoDB: B+Tree    
III. Indexes
   Scheme oprtimization**
   Query optimization
     */
}
