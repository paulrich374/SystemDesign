
public class NoSQLDataModel {
/*
 * 
 * https://highlyscalable.wordpress.com/2012/03/01/nosql-data-modeling-techniques/
 * http://blog.nosqlfan.com/html/4156.html
 * 
 * http://stackoverflow.com/questions/11476066/friends-table-datastore-design-app-engine-python
 * http://stackoverflow.com/questions/20557651/implementing-news-feed-on-gae-shoud-i-use-prospective-search
 * http://stackoverflow.com/questions/16806297/how-to-create-a-reliable-chat-using-google-app-engine-data-store-hre
 * http://stackoverflow.com/questions/1630087/how-would-you-design-an-appengine-datastore-for-a-social-site-like-twitter
 * 
 * 
http://blog.nosqlfan.com/html/4156.html
存在描述多对多的关系表	
处处引用客户端Join	
滥用内联后患无穷	
在线计算	
把内联Map对象的Key当作ID用	
不合理的ID	

 1. ===========http://stackoverflow.com/questions/1630087/how-would-you-design-an-appengine-datastore-for-a-social-site-like-twitter
找所有 訊息
class Message(db.Model)
 	sender = db.StringProperty()
 	receivers = db.StringListProperty()
 	body = db.TextProperty()

$$$$$ messages = Message.all().filter('receivers=', user_id) 
100messages with 1000 users in each receivers list, you have to deserialize 1000*100 list property.
Way too expensive in datastore latency and cpu
NOTE: need to unpack receiver list to check if they contain certain reveiver user_id

只找特定ＩＮＤＥＸ相對映 訊息
class MessageIndex(db.Model)
	#parent = a message
	receivers = db.StringListProperty()
class Message(db.Model)
 	sender = db.StringProperty()
 	body = db.TextProperty()
 	
indexes = MessageIndex.all(keys_only=True).filter('receivers=', user_id)
This key only query finds the message indices with a receiver equal to the one you specified 
without deserializing and serializing the list of receivers  
keys = [k.parent() for k in indexes]
$$$$$ messages = db.get(keys)
Then you use these indices(as model Message's key) to only grab the messages that you want.  
 */
}
