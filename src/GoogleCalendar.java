
//	N: 假设有 1 billion user
//  user = 10^9

//	每个user平均每天new一个event
//	平均每天读10次
//	那么大概每秒10k的写和100k的读
//    Write = 10 k  = Read/10
//    Read  = 100 k = 10^5 = daily active user


//	K: 如果每个user可以使用1M的存储空间，那么total就是1PB，属于大数据了
//    Storage = 1M*10^9 = 10^15 = 1PB


//	当然实际使用的情况感觉应该没有这个大，但是potentilly还是可能的, 我感觉实际情
//	况100T应该是够了 （90%的user不怎么使用calendar）


//	从这个分析来说， Cassandra handle起来应该没什么问题，是一个不错的选择, 一般
//	的SQL就不适合处理这么大量了
//	http://massivetechinterview.blogspot.com/2015/10/google-calendar-architecture.html
	

/*http://www.jiuzhang.com/qa/13/
 * */
public class GoogleCalendar {
/*	
	-----
	scheduler
	event
	notifier
	----
	老三样 kafka cassandra spark
	---
	我能想到的功能
	new
	invite
	display
	modify
	notify
	delete
	-------
	show and resolve conflict among multiples users' calendar

	scheduling periodic event

	alarm before event occurrence
	-------

	create event
	2, invite user
	notifer users at specific time or periodically
	Follow up:
	4. if you have a lot of users with the same calendar, 
	how to implment 
    1. create event, 
    2. invite user and 
    3. notify users	
    
    
    
1. 如何设计calendar的视图呢？（参考如何设计excel）
	Q: create event
	// http://www.jiuzhang.com/qa/45/
		First: Excel hashMap, 
		Second: dependency, 
		Third: append,
	  	Fourth:  delete, 
	  	Fifth: edit
	
	
2.3. 如何订阅多个feed呢？（参考如何设计subscribe系统）
    Q: invite user ? 
        First: Email service 
	Q: notifer users at specific time or periodically ?
	    First: Java Quartz (cronjob)/Email service
    
	// https://cnodejs.org/topic/56485dd8b68278b07f982804
	// http://stackoverflow.com/questions/23507200/good-practices-for-designing-monthly-subscription-system-in-database
	Clients/User
	-------
	Client ID
	Name
	
	Plan
	-------
	Paln ID
	Name
	Credit_for_month
	Price_per_month
	
	Subsciprtions/Events
	---------
	Subscritption ID
	Client ID
	Plan ID
	Subscription_start_timeStamp
	Subscriprion_end_time_stamp
	
	## Use Case1 : When a client subscribes to an offer like "Premium with 1st month free!", your database would look like this
	Clinet
	---------
	ID:1; LstName:foo;,,,,
	
	Plan
	---------
	ID:1, Name:Premium;Credits:-1(unlimited);Price_per_month:30
	ID:2, Name:Premium 1st month offer; Credits:-1, Price_per_month:0
	
	Subscritpion
	---------
	ID:1, CLient ID:1, Plan ID:2, Start:2014-05-07 08:00, End:2014-12-06 07:59
	ID:2,Client ID:1, Plan ID:1, Start:2014-06-07 08:00, End:9999-12-06 07:59
	
	## Use Case2: When a client unsubscribe the 1st July, update the column end in your subscription table with the month and the year only
	
	Subscritpion
	---------
	ID:1, CLient ID:1, Plan ID:2, Start:2014-05-07 08:00, End:2014-12-06 07:59
	ID:2,Client ID:1, Plan ID:1, Start:2014-06-07 08:00, End:2014-07-06 07:59
		
	## Use Case 3 :to know if a client is not unsubscribe, you should use this
	SELECT Count(Client.*) 
	FROM Client client
	INNER JOIN Subsciprtion subscibtion 
	ON subscribtion.client_is = client.id
	WHERE DATE_TODAY between sub.start AND sub.end
		
	
	// https://www.quora.com/How-are-push-notifications-technically-designed
	http://stackoverflow.com/questions/3594297/notification-scheduler-and-designer-in-java-implementation-recommendations 
	https://gist.github.com/BrandonSmith/6679223
	$$$$ http://www.javacodegeeks.com/2011/06/spring-quartz-javamail-tutorial.html			



4. 如何解决多平台的修改冲突呢？（参考如何合并购物车）
	// http://www.jiuzhang.com/qa/13/
		First : Queue: store all the operation 
		and push change to user after operation finish(Step Second and Third)
			[可以把所有用户对同一个calendar的操作序列化，之后给大家push同步。]
		Second : Local Update
		Third : Remote Sync
			1. no conflict=> merge
			2. conflict=>update and merge
			[当然，为了用户友好，可以现在本地先update用户的操作，]
			[同时和服务器同步，如果数据一致，则ok；如果期间有人修改了calendar造成不同步，则update为最新的]
*/
}
