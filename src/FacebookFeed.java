/*
 * 
 * Core Concept: Architecture 		
 * Pull and Push Model
 * 
 * 
 * file:///Users/weihung/Desktop/%E4%B9%9D%E7%AB%A0Nine%20Chapter%20SystemDesign/SystemDesign_6_20151025.pdf
 * https://www.quora.com/Software-Engineering-Best-Practices/What-are-the-best-practices-for-building-something-like-a-News-Feed
 * http://www.slideshare.net/XiaoJunHong/feed-26666858
 * https://github.com/tschellenbach/Stream-Framework
 * */
public class FacebookFeed {
/*
===  1. Scenario
	• 1. three Feed Lists
	• 2. one Timeline List 
	• 3. Social Graph (Unidirection)
 	Friend
 	Following/Followers
 	Edge
 	Object/Data - list of message
 	• Web->MySQL
 	table content feed_id content
 	table timeline user_id feed_id   
===  2. Necessities
 	• Read Timeline QPS = 300K
 	• Write tweet QPS = 5K
 	• Notify 1 million followers within 1s
 	• Concurrent users = 15m within 1s
 	• Daily tweets = 400m
===  3. Action
	PUSH MODEL < 100K
		NOTE: latency, **storage, edit cost(delete feed/unfollow->check $user_id $feed_author_id)
		TABLE: $user_id $feed_author_id $feed_id
 		• 1. write a tweet with push 
 			O(n) every follower timeline list
 			• select $user_id from [[[followers]]]
 			• insert into [[[feeds]]] ($user_id, $author_id(my own), $feed_id) values ()
 		• 2. read a timeline with push 
 			O(1) its own timeline list
 			• select *from [[[feeds]]] where $user_id 
 			= $user_id(its own id)
 		• 3. the storm of lady gaga 
 			O(49.7m) user[only notify online user]
	PULL MODEL >=100K 
		NOTE: computation(time ~ # of following), Bandwidth
		NOTE: 	Need a Timeline service(~= TIMELINE BUILDER) 
		NOTE: 	>=90K to avoid Vacillation
		TABLE: $user_id  $feed_id
 		• 1. write a tweet with pull 
 			O(1) its own feed list
 			• insert into [[[feeds]]] ($user_id, $feed_id) values ()
 		• 2. read a timeline with pull 
 			O(n) every following feed list
 			• select *from [[[feeds]]] where user_id in
 			( select $user_id from [[[following]]] )
 		• 3. the architect of matrix(the storm of crazy stalkers) 
 			O(1m) following
	CACHE MISS (need DISK seek)
	  	• Active user list
	  	• Notify Timeline builder
	  	• Load/Build from FeedTable
	  	• Append into Memory
	TIMELINE BUILDER (need DISK seek)
		• Get(	userIDList,	k,	endTime,	beginTime), k how many tweets we need
	SEARCH
		• Indexer
				keyword	=	{hello,	 world,	fennec}	
		• Search	index
				Dictionary            Keyword	Index
				…
				hello	 		->		39
				…
				world		->		46
				…
				fennec	->	99		
				Keyword	Index
				(KeywordIndex, number of tweets has this keyword)
				(39,12), (99,1),(46,5)
		• Blender
===  4. KiloByte 
 PUSH MODEL
 PULL MODEL
  	• Size	of	timeline	lists	=	1billion     *	10^3 *200	=	200T
	• Size	of	feed	lists	    =	1billion	 *	50	 *200	=	10T
	• Size	of	social	graph	    =	1billion	 *	30	 *2*8	=	480G
 Save TweetListTable
 	• Load/Build Timeline lists
 	• Each	chunk	is	a	64MB	of	time	capsule/chunk
 		• TweetListTable
 			TweetID /UserID /Time /Content /State
  		• TweetListTable
  			TweetID /UserID	
 SQL/NoSQL
===  5. Evolve
 DB system
 Speed
 Space
 1.speed up PUSH/PULL with memory (PULL可让最常访问的数据靠近计算)(PULL网络部署结构优化)(PULL按时间归档热数据)(PULL 并行获取 •  高效聚合算法 •  )
 	Before memory
    • Web->MySQL 
 	After memory
    • (Scalability) Web->memcached->MySQL (集中式缓存)	
    	- Mysql read overload(cache miss, memcached size limit)
    	- Memcached slow query (read overload, CPU limit, Bandwidth limit)
 	• Size	of	timeline	lists	=	1billion     *	10^3 *200	=	200T
	• Size	of	feed	lists	    =	1billion	 *	50	 *200	=	10T
	• Size	of	social	graph	    =	1billion	 *	30	 *2*8	=	480G
 2.save space PUSH/PULL memory with active user list
 	• Only	cache	for	the	users	who	is	active	within	1	week
	• Only	cache	the	latest	80	tweets
 	Before with active user list
 	• Size	of	timeline	lists	=	100million   *	10^3 *200	=	..
	• Size	of	feed	lists	    =	100million	 *	50	 *200	=	...
	• Size	of	social	graph	    =	100million	 *	30	 *2*8	=	..
	After with active user list	
 	• Size	of	timeline	lists	=	100million   *	500  *200	=	10T
	• Size	of	feed	lists	    =	100million	 *	80	 *200	=	1.6T
	• Size	of	social	graph	    =	100million	 *	20	 *2*8	=	32G	 
 3.save space PUSH/PULL memory by separating tweet content
 	NOTE: Tweet	size:	20B =	userID(8)+tweetID(8)	+	indicators(4)
  	• Size	of	timeline	lists	=	100million   *	500  *20	=	1T
	• Size	of	feed	lists	    =	100million	 *	80	 *20	=	160G
	• Size	of	social	graph	    =	100million	 *	20	 *2*8	=	32G	 
 3** (Scalability) (availability)memcached server Failover not work well or SPF 
 	• Solution : consistent Hash (分布式缓存) - add/delete/virtual node
 3** (availability) memcached server Failover Hot Point(1 million user access immediately/seckilling)
 	• Solution : Master/ Slave(same datacenter[Master/Salve] or different data center[cheap SSD])
 3**(Scalability) memcached server Slow Query since active user rise
 	• Solution : Line cache
 3** (availability)
 	• Solution :SLA Service Level Agreement (SLA)
 3** Memcached Key
 	• hit(cache miss low) why 3**
 	• size why step2 and 3
 	• core functionality operate on memcached for a while
 4.speed up TWEETLISTTABLE 
 	• Reduce Disk Seek
 	• Index 
 		(UserId) 	
 	Before Speed Up - No Index
 	• Get(	{2,	12,	22}	), Time	complexity:	O(n) Search all tweets for find UseID is 2,12 and 22
  	After Speed Up - Index
 	• Get(	{2,	12,	22}	), Time	complexity:	O(q*logn+k) Search part of tweets(userId 22 tweets group together) for find UseID is 2,12 and 22	
 5.save space  TWEETLISTTABLE
  		Before save space
  		• TweetListTable 
  			TweetID, UserID 
  		After save space
  		• TweetListTable 
  			TweetID 
  		• Index 
  		    (UserId, number of tweets for this UserId) = (22, 3) or (2,2)	
http://www.slideshare.net/XiaoJunHong/feed-26666858  		    
  6. • Web->MySQL --> slow query, latency, memcached scale but still
 	table content feed_id content
 	table timeline user_id feed_id 
 	 • Solution:  optimize SQL no join, no subquery, index, BASE
 	 • Solution: SQL only store no business logic 
 	 - select feed_id from timeline where uder_id =?
 	 - select feed_id, content from content where feed_id in?
  7. • Web->MySQL + optimize SQL + SQL only store no business logic  --> slow query, latency, memcached scale but still	 
 	 • Solution:(scale up 垂直拆分 strong binding)   vertical split business to different server
  8. • Web->MySQL + optimize SQL + SQL only store no business logic + vertical split ->read limit
     • Solution:(scale out 读写分离 sync,consistency) one master/salve cluseter   read(slave)/write(master) split  and replication between master and salve 	 
  9. • Web->MySQL + optimize SQL + SQL only store no business logic + vertical split + read/write split ->read limit  
 	 • Solution:(scale up)  add read(salve)
  9. • Web->MySQL + optimize SQL + SQL only store no business logic + vertical split + read/write split + add read(slave) ->write limit  
 	 • Solution:(scale up 水平拆分 plan splitSQL/table) multiple master/salve cluseters add write(master) 	
 	 • Solution: different SQL(physical and logic) different table(hash and time)
 10. mysql slow query => large history feed
 	 • Solution: index Date( monthly)
 	 table timeline_month user_id feed_id stat_date count
 	 • Solution: timeline_month get date, timeline get feed (date and count)
 11. 10. 水平拆分 "year cluster" == 冷热数据分离
 */
}
