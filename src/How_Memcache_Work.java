
public class How_Memcache_Work {
/*
http://www.slideshare.net/greenido/memcache-basics-on-google-app-engine
What
1. Memcache is an im-memory Key-Value Pairs data store
2. Key or Value ca nbe anything that is serializable
3. Memcached is a shared service accessed via App Engine APIs
Why
1. Caching in front of datastore
	- caching entities for low latency read
 	- integrated into most ORM frameworks
2. Caching for Read heavy operations
	- User authentication token and session data
	- API calls or other computation resutls
3. Semi-durable shared States cross APP isntances
	- Sessions
	- Coutners/Metrics
	- Application Configutrations
4. Improve Application Performance
5. Reduce Application cost
How 
1. Java GAE low-level memcache APis and Objectify 
2. Python api.memecache module and ndb	
3. Coordinate data read with Datastore
	- check if Memecache vlaue exist
	- YES => use and display
	- NO => detch from datastore and write into Memecache
?????4. Coordinate data write with Datastore
	- Update Memcache value
		- handle race condition
		-leverage put if untouched/compare and set to detect race condition
	- Write the value to Datastore
		- orinigally leverage the """""task queue"""""""" for background wrtites
ADVANTAGES
4. Batch operations
    - getALL(), putALL(), deleteALL()
    - further improve memcache perdormance by reducing NETWORK traffics
    - batch size < 32 MB
5. Atomic operation 
 	- provide atomic increment of numeric value
 	- A memcahism t oupdate a value consistently by concurrent request
6. Asynchronous call
 	- provide a mechanism to make a non-blocking call for memcache operations
7. NAmeSpace
	- Logically separate data layers for different application purposes (like
	multi tenancy) across GAE srvies such as Datastore, memcache, Task Queues
DISADVANTAGES	
8. Memcache is volative (wrtie-through logic by backing)
9. Memcache is not transactional (ise getIdentifiable() putInuntouched() for optimistic locking)
10.Memcache is limited resource (cache useful and necessary, Dedicated memcache from application)  		
11. KEY TAKEAWAYS
  - MEmcache is supported natively in GAE
  - Memcache support open standard, Batch,Atomic, Asynchrounous
  - Seamless integration with GAE datastore
  - Read-frequesntly and wrtie-rarely data is most sutable for combing memcache
  - HAndle memcache;s colatility in your application
  - USe memcache wisely, it is not a unlimited reosurce
  
https://www.quora.com/How-does-a-Memcache-cluster-work

https://www.adayinthelifeof.nl/2011/02/06/memcache-internals/
1. Big-O
2. LRU cache
3. consistent hashing
4. Consistent hashing



1. Big-O
Most of memcache functionality (add, get, set, flush etc) are o(1).
An iterator would probably be a O(N) operation, which means the amount of time doubles when the amount of items in the cache doubles. 

2. LRU Cache
Question1: how much memory it should use ?
Question2:  memcache will delete old data to make room for your new data


3. Memory allocation
When memcache starts, it partitions its allocated memory into smaller parts called pages. Each page is 1Mb large


4. Consistent Hashing
Question1: talk to multiple memcache-servers at the smae time?
$server_id = hashfunc($key) % $servercount;
Question2: add and delete server ?
Consistent hashing uses a counter that acts like a clock
uppose this counter is 16 bits
Question3: server delete?
move to next!
Question3: hot spot?
The more servers you add, the better the consistent hashing will perform.
the number 0 and 65535 would be on the “12”, 32200 would be around 6’o clock, 48000 on 9 o’clock and so on. 

 * */
}
