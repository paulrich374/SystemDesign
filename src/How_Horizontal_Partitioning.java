
public class How_Horizontal_Partitioning {
/*
REST-ful framework for easier caching

How_Platform
1. Programming language and frameoworks
	- Dynamic languages are slower than static languages
	- Compiled code runs faster than interpreted code
		-> use accelerators or pre-compilers
	- Frameworks that provide 
		Dependency Injections
		Reflection
		Annotations
		has a marginal performance impact
	- ORMs hide DB querying which can in some cases result in poor
		query performance due to non-optimized querying
2. RDBMS
	- MySQL, MSSQL and Oracle support native replication
	- Postgres uspports replication through 3rd party software (Slony)
	- Oracle supports Real Application Clustering
	- MySQL uses locking and arbitration, while Postgres/Oracle use MVCC 
		(MSSQL just recently introduced MVCC)
3. Cache
	- teracotta vs memcached vs coherence
	
How_Cool
1. CDNs
2. IP Anycasting
3. Async Nonblocking IO for disk
4. Incorporate multi-layer caching strategy where required
 	- L1 cache - in-process with App server
 	-L2 cache - across network boundary
 	-L3 cache - On disk
5. Grid Computing
	- JAva -GridGain
	- Erlang - natively built in
 	
How_HTTP_Accelerator
1. Web app ->Http Acceletator or Reverse Proxy 
2. Reason :
	- Redirect static content requests to a lighter HTTP server(lighttpd)
	- Cache Content based on rules(with granular invalidation support)
	- Use Ascyns NIO on the user side
	- Intelligent load balancing
3. Solutions
	- Nginx(HTTP / IMAP)
	- Perlbal
	-Hardware accelerators plus Load Balancers
	
	
	
How_Caching
1. Add caches within App servers 
	- Object Cache
	- Session Cache(Central Session Cache)
    - API Cache
    - Page Cache
2. Software
    - Memcached
    -TeraCotta(Java Only)
    - Coherence(commercial expensive data grid by Oracle)
    
How_Horizontal_Partitioning

a gloabl redirector
direct to different set

a single set consists of 
1. 1 Load Balanced App servers cluster
2. Lookup map
2. 2 DB cluster, each cluster with 3 DB servers
3. SAN with 6 servers 
a single set serve 10 million users

sets can even be deployed in separate datacenters

Positives: 
 	Infinite Scalability
Negatives:
 	Aggregation of data across SETS is complex
 	Users may need to be moved across SETS if sizing is improper
 	Glocal App settings ans preferences need to replicated across SETS


1. increase the number of clusters by dividing the data

2. Vertical Partition: Dividing tables / Columns
	E.g., users table and friends table can be on separate DB clusters
	E.g., each DB cluster has different tables
	E.g., moving a set of columns into a separate table 

3. Vertical Partition Negatives:
Cannot SQL join
Cannot Referential Integrity

Referential integrity: 
The PK I refered has been deleted and I DON'T know
Referential integrity is a property of data which, when satisfied, requires every value of one attribute (column) of a relation (table) to exist as a value of another attribute (column) in a different (or the same) relation (table).
in other words there is a foreign key value with no corresponding primary key value in the referenced table.
What happened here was that which was deleted from the artist table
With referential integrity enforced, this would not have been possible

4. Horizontal Partition: Dividing by rows (value) Look up map
	E.g., DB cluster1 has table1 1~1000 rows table2 has 1~1000 rows
	E.g., DB cluster2 has table1 1000~2000 rows table2 has 1000~2000 rows
Techniques:
1.FCFS
2.Round robin
	e.g., row1%10->cluster1
	e.g., row11%10->cluster1
	e.g., row4%10->cluster4
3.Least used
	e.g., row1->cluster1 (fewer rows)
4.HAsh based
	e.g., hash(row1,row10,row100) -> cluster1
	e.g., hash(row2,row20,row2000) -> cluster2
5.Value Based partition 
	e.g., A-M cluster1 
	e.g., 1~10^6 cluster1
6.Except hash and value, all other require independent look up map
map user to Databse Cluster
This map itself will be stored on a separate DB(and may further replicated) 

5. Horizontal Partition Negatives:
SQL unions for search type queries must be performed within code
Arregate the search results in code


 * */
}
