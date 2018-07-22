/*
 * 
TopK heap
Inverted Index
// https://hellosmallworld123.wordpress.com/2015/10/02/%E4%BC%98%E6%AD%A5%E7%B3%BB%E7%BB%9F%E8%AE%BE%E8%AE%A1/

https://hellosmallworld123.wordpress.com/2015/10/02/%E4%BC%98%E6%AD%A5%E7%B3%BB%E7%BB%9F%E8%AE%BE%E8%AE%A1/

设计题2： 让我设计一个 Netflix/Spotify， 

follow up 很多 比如

如何限制同一个用户多处登录不能同时看一个资源，

如何实现根据用户的网速调整清晰度，

怎么热门推荐等等。

	I. 对于设计登录系统，
	  
	   首先我们先要设计基本的login系统，首先支持Register/login，就需要一个基本的用户表，
	  
	   如果要支持更复杂一点的登录系统，包括verification，ban，inactive，removed，就需要一个status来记录每个user的状态。

       I-I. 如果还要支持用户可以从多个设备登录，不同的设备会有不同的session id， 但是却有相同的user——id，
       
       I-II. 如果不想要同一个用户可以在多个设备上播放同样的资源，我们就要记录每个用户每个session正在play的资源，然后保证每个session不能play相同的song_ID或者video_id。
       
    II. 如果要根据网络速度调整清晰度，
    
        我们就需要先测量网络的情况，让client端ping一下服务器，根据收到的ping再向服务器请求不同清晰度的资源。
        
        资源本身不应该存在服务器上，可以存在离客户端很近的CDN上。

        然后要看播放器的类型，
        
        II-I. 如果是app的话，这个app会需要向DNS server请求我们服务器的地址，
        
              然后我们服务器向clientload网页
        
        II-II.（如果是从浏览器的话，如果是app的话，就不需要load网页，直接去请求源地址就可以了）。
        
              然后网页再加载播放器（flash或者HTML5的）， 最后再请求源地址，源地址应该是放在附近的CDN上面的，所以很快。

    III. 接下来是推荐系统，假设问怎么样设计一个推荐系统，
    
         推荐给用户Top10 most frequent played。
         
         最简单的用MinHeap，把所有的play过的video或者audio都记录一个frequency，这个记录可以放在内存里（可以是cluster的这种比如redis）这样便于快速的更新和存取。
         
         我们希望这个工作放在worker里做，这样可以不影响整个系统的through put。
         
         当worker完成了工作以后，就可以Update所有的client，这个工作也可以由一个worker来做，
         
         这里我们可以用push也可以用pull，也可以两者相结合，push的话只需要push给在线用户，pull的话对于刚上线的用户来更新最新结果。

         III-I. 外排序就是假设内存不够用，那么我们就将所有的数据分成小块，然后每个小块都可以放进内存里排序，排好了序的这些小块再进行merge sort。
         
         就得到了所有数据的排序结果。

     IV. 还有一个follow up就是因为有很多用户在同时播放视频文件，
     
         有可能同时有很多人在看同一个视频，那么这个视频的freq就会有很多+1，怎么样保证所有的+1都记录下来呢？
         
         ???????  我们可以用一个aggregator，专门记录这种update，等update到了一定的数量或者一定时间做一次batch的update。 
 
http://codeinterviews.com/Uber-Design-Netflix/
     
V. Architecture
	1. Database layer 
	2 .service layer
	3. front end layer
4. Netflix partener with AWS
	Database alyer
			5. data store using S3 associated with RelationalDB nd No SQL
	Service layer
			6. user authentication
			7. session management
			8. data streaming and other business logic
	FrontEnd
			9. UI
	OPTIMIZE
			10. cache
			11. CDN network to replicate data close to users
			12. STATIC file few update
			13. moive/show files can get multiple copies and be replicated to edge server  
	FOLLOW UP
			14. limit same user with different platform cannot watch the same moive
			15. auto adjust quality of definiiton according to newwork bandwidth
			16. recommendation system 
 */
public class UberNetflix {

}
