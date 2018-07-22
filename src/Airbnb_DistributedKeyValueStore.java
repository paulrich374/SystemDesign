
public class Airbnb_DistributedKeyValueStore {
/*

有人问key - value的设计题，这是我的一些理解，欢迎大家讨论指正

这是一个很有意思的题目，主要是考高并发下的key value存储系统，
我一开始从
distibute hash入手，讲了讲分布式存储系统，类似 Dynamo. 

后来面试官让我设计单
服务器上
put, 
get, 
delete, 
update。
可以借鉴GFS，
比如以64K为存储块(block), 存储块大小可以和面试官讨论，
如果存储的value比较大，就用大的存储块(GFS是64M)，
在内存中维护一个Index(Key -> Block), 每次读写操作以存储块为单位，
1. Put(): 
	在""内存""中写，写满64M，写入""硬盘""
2. Get(): 
	根据""Index""找到对应存储块，如果存储块不在""内存""，
	从""硬盘""中读出，按""LRU""更新
	内存中存储块，然后块内顺序查找
3. Delete():  
	直接从""index""上删除key，后台运行一个垃圾回收的程序，专门负责清理，合并存储块
4. Update()： 
	"Copy on Write", 先将原来的值copy出来存入新的块，update完成后
	update index，这样可以避免读写冲突的问题。原来的内容会被垃圾回收处理


1. Key Value Storage
   Q: store 1PBs (key,vlaue) tuples
2. Simple API
	put(key, value)	// insert/write(C/U) the value associated with key
	get(key)		//	get/read(R) the data associated with key
3. Use case:
   - Amazon 
     key: 	customerID
     value: customer Profile
     		(e.g., 
     			buying history, 
     			credit card,..
     		)
   - Facebook, Twitter
     key:	UserID
     value: user profile
     		(e.g., 
     			posting history, 
     			photos, 
     			friends, ..
     		)
   - iCloud/iTunes
   	key:	Movie/Song name
   	value:  Movie, Song file
4. Solution:
   	- Amazon					: DynamoDB: internal key value store used to power Amazon.com(shopping cart)
   								: Simple Storage System(s3)  
   	- BigTable/HBase/HyperTable	: distributed, scalable data storage
   	- Cassandra					: ditributed data management system(developed by facebook)
   	- Memcached					: in-memory key-value store for small chunks of arbitrary data(Strings, objects)
   	- eDonkey/eMule				: peer-to-peer sharing system
5. Solution Development:
	- called Distributed Hash Tables(DHT)
	- Main idea: PARTITION set of key-values across many machines
6. Challenge1:
	Fault Tolerance
	[handle machine failures without losing data and without degradation in performance]
7. Challenge2:
	Scalability
	[Need to scale to thousands of mahcines]
	[Need to allow easy addition of new mahchiens]
8. Challenge3:
	Consistency
	[maintain data consistency in face of node failures and mesage losses]
9. Proposed Solution: Directory-Based Architecture
   [Master-Slave Architecture]
   	1. Have a node(MASTER) maintain the MAPPING between keys and the machines (nodes)
   	   that store the values associated with the keys
   	   E.g., 
   	   		Put(K14,V14)->Put(k14,Machine3)
   	2. Recursive Query
   		Ad: 
   			- Faster, as typically MASTER/DIRECTORY close to nodes
   			- Easier to maintain consisteny, as MASTER/DIRECTORY can serialize puts()/getS()
   		Disad:
   			- scalability bottleneck, as all "Values" go through MASTER/DIRECTORY
   	3. Iterative Query
   	   	Ad:
   	   		- more scalable
   	   	Disad:
   	   		- slower
   	   		- harder to enforce data consistency
   	   		
   	   		
10.Directory-Based Architecture for Challenge1[Fault Tolerance]   



 		Sol1: Replicate value on several Nodes
   	   	E.g., 
   	   		Put(K14,V14)->Put(k14,Machine3, Machine1)
   	   		different racks in a datacenter to guard against RACK FAILURES 
   	   	1-1: Recursive Replication: 
   	   			Client put to 1st machien and 1st machine replicate since. (master return replicted machone)
   	   	1-2: Iterative Replication: client replicate by itself
   	   	 		Client put to 1st and the other machine all by itself. (master return replicted machone)
   	   	 		MAster directly put them all (master DON't return replicted machone)
   	   	 		
   	   	 				
11.Directory-Based Architecture for Challenge2[Scalability]



    	Sol1: Use MORE nodes
    	1-1 Number of requests:
    			-????Can serve requests from all nodes on which a value is sotred in parallel
    			- Master can replicate a popular value on more nodes
    	1-2 MAster/Directory Scalability:
    		 	- Replicate it
    		 	- PARTITION IT, so different keys are served by different MASTER/DIRECTORIES
    		 	- HOW to partition it?
    	1-3 Load Balancing:
    		 	- MASTER/DIRECTORY keep track of storage avaliability at each node
    		 		- Preferentially insert new values on nodes with more storage available
    		 	- What happens when a new node is added
    		 		- ??cannot isnert only new values on new node. why?
    		 		- Move values from the heavy loaded nodes to the new node
    		 	- What happen when a node fails?
    		 		- Need to replicate values from fail node to other nodes
    		 		
    		 		
12.Directory-Based Architecture for Challenge3[Consistency Tolerance]    



		Sol1: need to make sure that a value is replicarted correctly
		[All same data]Sol2: How do you know a value has been replicated on every node.
				- wait for acknowledgements from every node
		Sol3: what happens if a node fails during replication?
				- pick another node and try again
		sol4: What happens if a node is slow?
				- slow down the entire put()? pick up anotehr node?
		sol5: In general, with multiple replicas
				- slow puts and fast gets
		Problem1:CONCURRENT update(put to same key)
				- neeed to make sure that updates happen in the smae order
		Problem2:LArge variety of consistency models:
			  1. ATOMIC consistency(linearizability)
			  		- reads/writes (gets/puts) to replicas apeear as if there was 
			  		- a single underlying replica (Single system Image)
			  		- "One updated at a time"
			  		- transactions
			  2. EVENTUAL consistency:
			  		- given enough time all updates will propagate through the sustem
			  		- One of the weakest form of consistency: used by many systems in picture
			  3. CAUSAL consistency:
			  4. SEQUENTIAL consistency:
			  5. STRONG consistency:
			  
			  
13. Quorum Consensus -Imrpove put() and get() operation performance



		- replica set of size N
		- put() waits for acknowledgements from at least W replicas
		- get() waits for repsonses from at least R replicas
		- W + R > N
		- why does it work? there is at least one node that contains update
		- Why might you use W + R > N +1 ?
		- N =3, W =2, R =2
		- Replica set for K14:{N1,N3,N4}, N= 3
		- Assume put() on N3 fails, W =2
		- Now, issing get() to any two nodes out of three will
		  return the answer, R =2
		  
		  
14. Scaling up MASTER/DIRECTORY



		  CAHllenge: (mapping table)can be tens or hundrends of billions of entries in the system
		  	MASTER/DIRECTORY contains a number of entries equal to nubmer of 
		  	(key,vlaue ) tuples in the system
		  SOLUTIon: CONSISTENT HASHING
// http://www.lintcode.com/en/ladder/8/		  
		    0. No need for a MASTER/DRIECOTRY mapping table (key,machine ID)
		  	1. Associate to each node unique id in an uni-dimenstional space 0-2^M-1
		  	2. Partition this space across M machines
		  	3. Each (Key, Value) is stored at the node with the smallest ID larger than(">") Key
		  	E.g., 
		  		M = 6-> ID: spce0 -63
		  		         smallest id    largest id
		  		Node 8 maps [5,             8]
		  		Node 15 maps [9,           15]
		  		Node 20 maps [16,          20]
		  		Node .. maps [..,..]
		  		Node 5 maps [59,            4]
15. Lookup in CHORD-like system (with Leaf Set)
			// https://gist.github.com/danprince/5423d164d688420863e3
			// http://image.slidesharecdn.com/distributedhashtable-090305101758-phpapp01/95/distributed-hash-table-4-728.jpg?cb=1236248488
			// http://image.slidesharecdn.com/lectureset5-150425103623-conversion-gate01/95/lecture-set-5-84-638.jpg?cb=1429976339
			// http://image.slidesharecdn.com/distributedhashtable-090305101758-phpapp01/95/distributed-hash-table-7-728.jpg?cb=1236248488
			Supports just one operation: given a key, 
			   it maps the key onto a node
			1. Assign ID to nodes 
				map hash values to node with closeset ID
				
			2. Leaf Set is successors and predecessors
			    successor(key=1) =1(node1)
			    successor(key =2) = 3(node3)
			    Recursive Lookup
				all that's needed for correctness
			3. Routing table matches successively longer prefixes
				allow efficient loopup
				Route("d46"a1c) -> "d46"7c4
				""IPv4 routing table""entries for both 192.168.20.16/28 and 192.168.0.0/16
				Route(192.168.20.19) =  192.168.20.16/28	
			4. Data Replication
				On leaf set
16. DynamoDB Example: Service Level Aggrement(SLA)
		  	1. Application can deliver its functionality in a boudned time
		  		every dependency in the platform needs to deliver its 
		  		functionality with even tighter bounds
		  	2. Example: service guaranteeing that it will provide a response 
		  		within 300ms for 99.99% of its requests for a peak client load
		  		of 500 requests per second
		  	3. Contrast to services which focus on MEAN response time
		  	4. Service-Oriented Architecture of Amazon's platform
		  	   1. client requests
		  	      page rending components
		  	   2. Request Routing
		  	   	  Aggregator services
		  	   3. Request Routing
		  	   	  services
		  	   	  1. dynamo instances
		  	   	  2. s3
		  	   	  3. other datasotres
		  	   	  
For example, consider the ""IPv4 routing table"" with entries for both 192.168.20.16/28 and 192.168.0.0/16. 
When the address 192.168.20.19 needs to be looked up, 
both entries in the routing table "match". That is,both entries contain the looed up address.
In this case, the longest prefix of the candidate routes is 192.168.20.16/28, 
since its subnet mask (/28) is higher than the other entry's mask (/16), 
making the route more specific.
 The next hop will therefore be chosen based on the routing table entry for 192.168.20.16/28		  	   	  
192.168.1.0/24 is the prefix of the Internet Protocol Version 4 network 
starting at the given address,

 https://en.wikipedia.org/wiki/Subnetwork
having 24 bits allocated for the network prefix, 
and the remaining 8 bits reserved for host addressing. 
For example, 255.255.255.0 is the network mask 
for the 192.168.1.0/24 prefix.
 Classless Inter-Domain Routing (CIDR)
  (192.168.5.130) and its associated /24 network mask (255.255.255.0)
  Network prefix 192.168.5.0
  Host part      0.0.0.130
  (192.168.5.130) and its associated /26 network mask (255.255.255.192)
  Network prefix 192.168.5.128
  Host part      0.0.0.2
 * */
}
