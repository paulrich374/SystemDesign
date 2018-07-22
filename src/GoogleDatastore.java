
public class GoogleDatastore {
/*
 * 
 * id=3001	Properties Apr 27, 2012, 3:04:01 AM	Properties Key('swaagit_user', 3001)	
Namespace:[default]
Kind:swaagit_followinguser
ID:swaagit_user id:278 > swaagit_followinguser id:3001
Key literal:Key('swaagit_user', 278, 'swaagit_followinguser', 3001)
Properties

// GET ALL FOLLOWERS
following_ids = FollowingUser.objects.filter(pk=as_ancestor(user)).order_by('-created_date').values_list('following_id', flat=True)
// LIMITER
following_ids = User.objects.get_following_ids(self, consistent=False, batch_size=2000)
//  
photo_list = self.photo_list()
return photo_list.fetch_merge([make_key(User, pk) for pk in list(following_ids) + [self.pk]])
 
 

=============== swaagit_photofollowerlist =============
------ PHOTO 106002 is followed by 104003 ----------
 
swaagit_photo id:106002 > swaagit_photofollowerlist id:1
Key('swaagit_photo', 106002, 'swaagit_photofollowerlist', 1) 
followers ["Key('swaagit_user', 10001)","Key('swaagit_user', 104003)"]


=============== swaagit_photo ============= 
------ USER 1001 OWN PHOTO 106002 ----------

swaagit_photo id:106002
Key('swaagit_photo', 106002)
user_id Key('swaagit_user', 1001)


=============== swaagit_followinguser ============= 
------ USER 104003 FOLLOWING USER 10001 ----------

swaagit_user id:104003 > swaagit_followinguser id:1001
Key('swaagit_user', 104003, 'swaagit_followinguser', 1001)


 * 
 * 
 * 
 * 
 * http://stackoverflow.com/questions/1630087/how-would-you-design-an-appengine-datastore-for-a-social-site-like-twitter
 * 
 *  GOOD
 *  
class Message(db.Model):
    sender = db.StringProperty()
    body = db.TextProperty()

class MessageIndex(db.Model):
    #parent = a message
    receivers = db.StringListProperty()    
     
indexes = MessageIndex.all(keys_only = True).filter('receivers = ', user_id)
keys = [i.parent() for i in indexes)
messages = db.get(keys)

This key only query
inds the message indices with a receiver equal to the one you specified 
without deserializing and serializing the list of receivers.

 * 
 *  WRONG
 *  
class Message(db.Model):
    sender = db.StringProperty()
    receivers = db.StringListProperty()
    body = db.TextProperty()
messages = Message.all().filter('receivers =', user_id)

e.g. receivers ={0003, 0034, 0056, 0076,..} 100 users in the receivers
Look up receiver 0056, unpack receivers and find 0056 O(n) if 0056 is in the last id
Suppose we have 100 messages(row) to scan and unpack each message receiver list 
O(100*1000)

 * 
 * http://stackoverflow.com/questions/20557651/implementing-news-feed-on-gae-shoud-i-use-prospective-search
 * 
 * 
 * http://blog.crowdint.com/2014/11/21/understanding-google-cloud-datastore-keys-using-go.html
 * 
----- Complete Key - with unique string id or integer id
 
 foodKey := datastore.NewKey(context, "Catalog", "Food", 0, nil)
clothesKey := datastore.NewKey(context, "Catalog", "Clothes", 0, nil)

 foodKey := datastore.NewKey(context, "Catalog", "", 1, nil)
clothesKey := datastore.NewKey(context, "Catalog", "", 2, nil)
 
------ Incomplete Key - depends on Google datstroe to gengerate 16 bit integer id for you
 
 
 type Product struct{
    name string
    price float64
 }
newProduct := Product{"ham", 9.99}
key := datastore.NewKey(context, "Product", "", 0, nil)
datastore.Put(context, key, newProduct)
 
incompleteKey := datastore.NewKey(context, "Product", "", 0, nil)
//is equivalent to
incompleteKey = datastore.NewIncompleteKey(context, "Product", nil) 
 
------  Parent Key (Group)
 
 //this will return all food products
datastore.NewQuery("Product").Ancestor(foodKey)


http://stackoverflow.com/questions/13532319/google-app-engine-datastore-nosql-example-with-ancestor-queries
return Child_model().all().ancestor(parent)

https://gist.github.com/douglasstarnes/2362078



 */
}
/*
 * Namespace:
Kind:
ID:
Key literal:
 
[default]
swaagit_photo
swaagit_photo id:127001
Key('swaagit_photo', 127001)
 Properties
Name		Type	Value	Indexed	

flagged
=	Boolean	False	



pins
=	BLOB	
gAJ9Lg==



comment_count
=	Integer	
0



has_tags
=	Boolean	False	



description
=	String	
#cat



flag_count
=	Integer	
0



status
=	String	
ready



created_date
=	Date and time	4/20/12, 12:06 PM PDT	



last_decay
=	Null	
null



battle_count
=	Integer	
0



latlong_from_ip
=	Null	
null



user_id
=	Key	
Key('swaagit_user', 1001)



image_url
=	String	
http://lh4.ggpht.com/Sqb8C_TRf76aM-2_IbYHB_daD39yrCEtjt1ZIkN_6c2tSaLhMcuLI55375zLs1bD8NcBfEcUmehr1vBU8X0



public
=	Boolean	True	



score
=	Floating point number	
0



longitude
=	Null	
null



like_count
=	Integer	
0



is_popular
=	Boolean	False	



latitude
=	Null	
null



hashtags
=	Array	
["cat"]



image
=	String	
AMIfv96JjmvDsBH2W8XDQ9ZZ5HhberSuUS5jdq1LQ0o-5VxFGe_BacHbpvYoilIcoZWe0_eM5FCKgJlGlYfcQvX9qqwuSYZK2dBk5pIDqDzWCxkXZ4Yehn8lubvXxKl6woUa1akZzFPe2HSxw3cYJOk00sYO6et8rg/uploads/image.jpg
*/
