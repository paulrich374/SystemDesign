/*
 * 设计RESTful API
 * http://www.zhihu.com/question/28557115
 * 
 *    Base   /  Controller / Resource / Paramter  / Format
 *  index.php/ example_api / user     / id/1      / format/json
 * 
 * 
 * 
 * {{{{{{{{{{  {{{{{{{{{{{{ BLOOM FILTER how many hash fucntion how many bit and  {{{{{{{{{{  {{{{{{{{{{{{
 *  {{{{{{{{{{  {{{{{{{{{{{{Codeigniter REST structure  {{{{{{{{{{  {{{{{{{{{{{{
 * 
 * 
 * 
 * */
public class LinkedinRestfulAPI {
	
/* I. REST
 * Resource: data, e.g., newsfeed, friends 
 * REpresentational: representation format e.g.,JSON, XML, JPEG
 * State Transfer: state change. e.g., POST, GET, PUT, DELETE
 * 
 * II. REST SOURCE
 * Roy Fielding
 * 
 * III.RESTful API
 * III-I. REST Architecture
 * Traditional:PC PHP, JSP
 * Nowadays:Phone,Tablet,PC 
 * Need an unified interface
 * 
 * III-II. Design RESTful API
 * 
 * 0. controller/versioning/resource/parameter/format

 
 * 1. URL(Uniform Resource Locator) root
 * 		/api/


 * 2. API versioning
 * 		/api/v1/


 * 3. URI(Uniform Resource Identifier) noun plural
 * 		BAD
 *      • /getProducts
 * 		• /listOrders
 * 		• /retrieveClientByOrder?orderId=1
 *      GOOD
 *      • GET /products:will return the list of all products 
 *      • POST /products: will add a product to the collection
 *      • GET /products/4: will retrieve product #4 
 *      • PATCH/PUT /product/4: will update product#4

    
 * 4. Make sure HEAD and GET are secure
 *      • GET /deleteProduct?id=1 XXX Never happen

    
 * 5. Resource URL use Nested structure
 *      • GET /friends/10375923/profile
 *      • UPDATE /profile/primaryAddress/city

    
 * 6. Warn the size of the result
 * 		• if too much, pagination or limit
 *      •  HTTP support Pagination by using Link in Header 

      
 * 7. HTTP status code
 *      200 OK
 *      201 Created
 *      500 Internal Server Error
 *      404 Not Found

      
 * 8. clear response with easy and readable test


 * 9. secure
 *    	1.https + hash(key)
 *      2. application level encrypt all HTTP request 
 *      3.OAuth2

  
 * 10.Server : RESTful API.e.g., Spring MVC or Jersey or Play Framework
 *             Web e.g., AngularJS or BackboneJS+jQuery
 *             +
 *             iOS e.g., RestKit
 *             +
 *             Android e.g., RetroFit or Volley 




 #
 # ================ USER(/user,/brand)
 #
create_user 201 Created
 	• POST /user/
e.g., result /api/v1/user/3001/
get_user 200 OK
 	• GET /user/{user_id}/
update_user  202 Accepted
 	• PUT /user/{user_id}/
list_feed('List photos in user\'s feed')
 	• GET /user/feed/
list_looks('List user\'s uploaded looks')
 	• GET /user/{user_id}/looks/
user_search('Search for users by query string or by comma-separated facebook id, twitter id, handle, or email address')
 	• POST /user/search/
list_notifications('List notifications for authenticated user')
 	• GET /user/notification/
list_suggested('List suggested users')
 	• GET /user/suggested/




    #
    # ================  PHOTOS(/photo)
    #
upload_photo(latitude and longitude are doubles. tags is a list of dictionaries containing the fields brand, item_type, xcoord, ycoord.',)
 	• POST /photo/
list_tag('List photos for given tag')
 	• GET /photo/tag/
list_brand_photos('List photos for given brand')
 	• GET /photo/brand/
get_photo
 	• GET /photo/{photo_id}/
remove_photo(204 No Content)
 	• DELETE /photo/{photo_id}/
discover('List top photos')
 	• GET /photo/discover/
list_brands
 	• GET /brand/



    #
    # ================  FOLLOWING(/user)
    #
follow('Follow another user')(required_param=['user_id', 'friend_id'])
 	• POST /user/{user_id}/following/
unfollow('Unfollow a user')(required_param=['user_id', 'friend_id'])
 	• DELETE /user/{user_id}/following/{friend_id}/
list_followers('List followers')
 	• GET /user/{user_id}/follower/
list_following('List following')
 	• GET /user/{user_id}/following/




    #
    # ================  LIKING(many to one)(/photo, /pin)
    #
like ('Like a photo')
 	• POST /photo/{photo_id}/like/
unlike ('Unlike a photo')
 	• DELETE /photo/{photo_id}/like/{pin_id}/
list_user_likes
 	• GET /user/{user_id}/like/
list_photo_likes 
 	• GET /photo/{photo_id}/like/
list_pins 'List of available pins'
 	• GET /pin/



    #
    # ================  COMMENTS(many to one)(/photo)
    #
comment
 	• POST /photo/{photo_id}/comment/
remove_comment
 	• DELETE /photo/{photo_id}/comment/{comment_id}/
list_comments
 	• GET /photo/{photo_id}/comment/
 	
 	
 	
 	
    #
    # ================   SAVING(many to one)(/user)
    #
save_photo
 	• POST /user/{user_id}/saved/
remove_saved_photo
 	• DELETE /user/{user_id}/saved/{photo_id}/
list_saved_photo 'List a user\'s saved photos - results are same as all photo feeds'
 	• GET /user/{user_id}/saved/
 	
 	
 	
    #
    # ================  FLAGGING(/photo)
    #
flag
 	• POST /photo/{photo_id}/flag/
unflag
 	• DELETE /photo/{photo_id}/flag/
 	
 	
 	
 	
    #
    # ================  VERSUS(/versus)
    #
get_versus 
 	• GET /versus/{versus_id}/
list_versus 'List open versus battles'
 	• GET /versus/
create_versus  required_param=['my_photo_id', 'their_photo_id']
 	• POST /versus/
vote
 	• POST /versus/{versus_id}/vote/
 	
 	
 	
    #
    # ================  SPONSORSHIPS(/sponsoredlogo)
    #
sponsored_logo 'Get sponsored logo for section'
 	• GET /sponsoredlogo/{section}/
 
 	• USER(user.py) 
 	• PHOTO(photo.py) 
 	• FOLLOWING(relation.py) 
 	• LIKING 
 	• COMMENT(comment.py) 
 	• SAVING 
 	• FLAGGING(flag.py) 
 	• VERSUS(versus.py)  
 	• SPONSORHSIPS

 	
/jobs/brand	update brand list	
every tuesday 04:00
(GMT)	
2016-01-25 (20:00:00)
On time	
Success
Run now
/jobs/closeout	versus close out job	
every 2 hours synchronized
(GMT)	
2016-02-01 (16:00:00)
On time	
Success
Run now
/jobs/decay	decay popular photos	
every 4 hours synchronized
(GMT)	
2016-02-01 (16:00:00)
On time	
Success
Run now
/jobs/update_discover	update discover photos	
every 30 minutes synchronized
(GMT)	
2016-02-01 (17:30:00)
On time	
Success
Run now
 	
 	
 	
http://mysql.rjweb.org/doc.php/latlng


*/
}
