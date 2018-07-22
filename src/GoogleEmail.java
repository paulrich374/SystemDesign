// http://www.jiuzhang.com/qa/638/
public class GoogleEmail {
/*

1. Scenario(like message)
	register/login
	*email CRUD
	*email Delivery
	email contact management
	email search
	


2. Necessary
	User assume 			1 billion user = 1000 million
	average concurrent 		1000million*(1/(24*60*60))*2hr*60min/hr*60sec/min=80million
	max peak 				80million*2 = 160 million
2-1. Traffic
	1 Mps(for attchment upload)/user
	max peak (assume 1/3 of the concurrent user is uploading attchment) 160 million*1*1/3 = 53 Tbps(Huge!)
2-2. Memory
	5KB / user
	max daily 1000million users*5KB/user = 5TB
2-3. Storage(HardDisk)
	1 GB/user
	total 1000million *1GB = 1MTB(Huge!)
3. Application
	user contact service
	email management service
		- email CRUD
		- email delivery business logic
	file service
		- email rea/write to DB
		- file attachement read/write 
	search service
		

4. KiloByte
	user contact service->MySQL
	email-> NoSQL
	file service-> File ssytem, chunk server
5. Evolve
	1. Performance:
		Push model for email inbox notification
		Pull for getting emails inbox
		Cache recent contacts/emails in memory
	2. Space:
		text file and attachment compress
	3. Search:
		Build search index on emails
		Trie for frequent email contacts


 */
}
