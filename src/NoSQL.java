
public class NoSQL {

/*
 *   http://blog.csdn.net/siyanyanyanyai/article/details/21383891
 *   
 *   Core1. no relationship
     Core2. many insert operation
     core3. not so stable, some error tolerance
     Core4. big table
     Core5. key, value pair
     Core6. document
     Core7. serial and entity(serial state). e.g., log, twitter.etc
    
     I.http://darkhouse.com.cn/blog/4
     II.http://blog.nosqlfan.com/html/4156.html
 
I.http://darkhouse.com.cn/blog/4
 
 File -> Disk
 1.Pointer->offset
 2. Block
 	- 4KB
 	- Disk#, Track#, Sector#(part of track, umbrella )
 	- file system format
 	- Random Access
 	- Sequential Access
 	- 
 
 
 Pointer to higher level representation---> Normalization 
 Offset ---> Aggregation
 1.Aggregation:
 	associate all relevant data in one tbale and locate using offset
 2.Normalization:
 	reduce redundancy of data, complex data type using multiple table,
 	distribute data to reduce reduancy and improve accurancy
 3.Atomic normalization:
  	only unique id and one attribute
  	easy to scale like adding one more attribute
  	easy to edit
  	Bad: join
 4. Denormalization
  	combine data together
 5. high QPS
    storage
    cross region 	
 6. (Why NoSQL)Huge Concurrent visit quantity [ CPU or memory doesn;t help]
  	- normal machine 100 QPS
  	- connection session-> same data -> lock(atomicity and consistency)
  	- one process even edit data in Disk, that will make 
 7. (Why NoSQL) Maximum storage [ adding server doesn;t help]
 	- billions of data (rows/tuples)
 	- Partition
 	- BAckup take time
 8. (Why NoSQL) Cross region
    - standard 3 architecture - application server, database server
    - high latency (Taipei to NY)
    - SOlution: a database server in Taipei 
    - two database sync and consistency doesn;t get help from relational database   
 9. Distribute Architecture(Distribute database) can solve the above three problems
    - replication->latency, inconsistency(later sync ) 
10. Distribute database     
    - C consistency
    - A availability
    - P partition tolerance
11. NoSQL = SQL - some part of functionality
    - some part of functionality removed from SQL need to be implemented in Application end
12. #1.Key-Value  Aggregation No.1 Normalization No.Last e.g, Redis
	- KEY: path/file name, VALUE:content (BLOB)
	- 键值数据库的优势在于获取单个实体数据时是最快的
	- unique key
	- key,value pair independent k
	- http://darkhouse.com.cn/images/Key_Value_Model.png
	- Rule**key->hash->022->02means partition 2(or server 2) and 2 means lcoation2 in server 2(partition number , location number)
	- Temporary data like CDN(images, javascript to fast load web page)->in-memory->can't fetch from other SQL(so temporary)
	- Rule**range query for key => BTree-> challenge for partition 
	- SQL to Key-Value
13. #2.Column-Family (Big Table - Google Map/Google Analytics), e.g, HBase, BigTable
    - KEY: row key, VALUE: customer info/address info
    - Rule**group relevant column as a unit e.g., customer col1: name, title col2:address, city,zip code
    - row key(customer Id), column family(Customer info | Address info)
    - Trade off between Aggregation and Normalization
    - Strong relation->col2:address, city,zip code , Weak relation -->normalization(Customer info | Address info)
    - No format e.g., Address info US:state, UK: no state
    - Physical (Customer info | Address info) two different documents
    - parallel process two different document ->better for MapReduce
    - SQL to Column-Family
14. #3.Document e.g, MongoDB
	- KEY: row key, VALUE: document info
	- Two Features: 
	- 1. Data Structure flexibility(Aggregation, normlaization, fat index(primary keys))
	- 2. Scale-Out: Replication and Sharding
	- accept proper redundancy, avoid too many small document
	- sale info: all info
	- relational DB sale info: title and item SQL/JOIN/Denormalize 
	- relational DB TopN: fast(item table). Document: slow(all documnet)
	- relational DB Visit:  slow. Document: fast
	- Rule**limited aggregation some normalization e.g, document of sale info only keep custoemrID and productID.
	- 1. Instead of whole customer info and whole product info since then when customer info or product info change we don't need to update one by one
	- This normalization is Better for: Update a customer info or a product info
	- This normalization is Better for: Total Sale in LA city-> whole customer info to get all the customers belong to LA city and find Whole sale info inlcudes those customers and sum them all
	- The above logic all implemented in application or client side
	- 1. Fat Index at some attribute e.g., customer ID,Row Key, Order Total
	- this is Better for: Find out customer total sale fast, don;t even go to main table
	- When update a customer sale info , also update this Fat Index table by using Transaction
	- 2. Scale-Out=> support high concurrent visits and Big data
	- SQL/NoSQL give up some Consistency criterion to support high concurrent visits and Big data
	- 文档数据库以Denormalized的形式保存数据的特点使得它在一个特殊的应用领域具有良好的适用性，这便是数据归档
	- **migrate some old data from realtional DB to Document, relieve pressure on Relational DB and satisfy the need of operation and storage on those History/Old data
15. #4. Graph  Normalization No.1 Aggregation No.Last
    - key:node,edge, value: real data
    - Node Table, Edge Table, Property Table
    - 图形数据库更关注实体与实体间的关系（Relationship）， 因此它在各个实体和关系间的遍历是最有效率的
 	- like ER-Model, E-R-E by using JOIN but Graph using INDEX
 	- node -> bidirection
 	 This is better for: who works in manufactoring. starting node:manufacturing, index with Employees
 	 - Employment date info is in "works in" Edge instead of node
 	 - > R-DB in Relation
 	 - don't care about size of database
 	 - R-SQL to mimic Graph 3 table
 	 - Node Table:node ID(Big Data, using tag to partition)
 	 - Edge Table: start Node ID, End Node ID, Date From, Date End(Big Data, using Edge Name to partition)
 	 - Property Table: Property pointer, Name,Value 	 
 	 - 同样主索引的排序能保证同一个属性指向下的所有属性（Name/Value Pairs）能在物理位置上连续
 	 - 默认情况下基于这两列的主索引是排序的；这样的设定能保证根据一个Start Node ID快速获取所有属于该节点的关系， 因为相同的Start Node ID在物理位置是连续的，可以避免过多的随机读取
 	 - INDEX : Node ID or Edge ID + <Node Tag>_<Property Name> application program know how to use index to query and filter
 
II.http://blog.nosqlfan.com/html/4156.html
 
 16. many to many relationship - a book to many authors, a authors to many books
     books (many) to authors(many)
 17.    
 
 */
}
