/*

1. 150M world wide active users, 
2. handles 300K QPS to generate timelines, 
3. and a firehose that churns out 22 MB/sec. 
4. 400 million tweets a day flow through the system and 
5. it can take up to 5 minutes for a tweet to flow from Lady Gaga’s fingers to her 31 million followers. 
 
 
300K QPS are spent reading timelines and 
only 6000 requests per second are spent on writes. 
 
 
==== write

Let’s say you tweet and you have 20K followers.

What the fanout daemon will do is look up the location of all 20K users inside the Redis cluster. 

Then it will start inserting the Tweet ID of the tweet into all those lists throughout the Redis cluster. 

So for every write of a tweet as many as 20K inserts are occurring across the Redis cluster. 
 
==== write stored information
 
What is being stored is the tweet ID of the generated tweet, 

the user ID of the originator of the tweet, 

and 4 bytes of bits used to mark 

if it’s a retweet or a reply or something else.

==== active user

Every active user is stored in RAM to keep latencies down.

Active user is someone who has logged into Twitter within 30 days,

which can change depending on cache capacity or Twitter’s usage. 
 
If you are not an active user then the tweet does not go into the cache.

==== home timelines
 
Only your home timelines hit disk. 

If you fall out of the Redis cluster then you go through a process called reconstruction.
	Query against the social graph service. Figure out who you follow. Hit disk for every single one of them and then shove them back into Redis.
	It’s MySQL handling disk storage via Gizzard, which abstracts away SQL transactions and provides global replication.

When you query for your home timeline the Timeline Service is queried. The Timeline Service then only has to find one machine that has your home timeline on it 

	Effectively running 3 different hash rings because your timeline is in 3 different places.
	They find the first one they can get to fastest and return it as fast as they can.
	The tradeoff is fanout takes a little longer, but the read process is fast. About 2 seconds from a cold cache to the browser. For an API call it’s about 400 msec. 
	
The user cache is a memcache cluster that has the entire user base in cache. 

Tweetypie has about the last month and half of tweets stored in its memcache cluster. 

These are exposed to internal customers.	

Some read time filtering happens at the edge.

For example, filtering out Nazi content in France, 

so there’s read time stripping of the content before it is sent out.

 * */
public class ScaleAtTwitter {

}
