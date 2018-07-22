//http://slideplayer.com/slide/5789915/
//http://2011azure.srad.jp/story/11/06/16/1320232/
public class AMUSTdatastorevsMySQL {

}
/*
-----                        -----
----- Choosing a Public Cloud----- 
-----                        -----

Language use
• 
Alaways consider using Iaas Clouds
• and builds your platform on top of them

-----                        -----
-----Microsoft Windows Azure -----
-----                        -----

• 概要 Iaas(Windows VM) / 概要 Paas(.NET/ Java/PHP and Node.js APIs)(IDE:visiual studio or Eclipse)
• 概要 (.NET/ Java/PHP/Ruby/Python and Node.js APIs)(IDE:visiual studio or Eclipse)

• Fast growing public cloud
• Operated By Microsoft
• No free version, only 3 month trial 
   
      Instances /Compute
      
•    Computing instances run Windows OS and applications(CPU+RAM+HDD)
Web Role  
	• internet information services (IIS) mahcine for hosting Web applciations and WCF services  
Woker Role
	•  Long-running computations
VM Role
	•  Window Virtual machine(non-persistent)

	
	儲存	       		Datastore
	   
	   
Azure Table Storage	   
	•   Distributed highly-scalable cloud database (stores entities with porperties)
Azure Queue Storage
	•   Message queue service
Azure Blobs/Drives
	•  Blob/ File Storage
	• NTFS volumes  
SQL Azure	
	•  SQL server in the cloud
	•   highly-scalable and scalable relational DB
Azure Business Analytics	
	•  create reports with tables, chats, maps, etc
Azure CDN
	•  content delivery network
Azure Caching
	•  Distributed, in-memory, application cache
	• 

実行worker

料金
CPU時間

-----                        -----
----- Google App Engine      -----
-----                        -----

• 概要 Paas public cloud
• 概要  Leading JAva/Python   (Eclipse) 


• Infrastructure similar to the one Driving GMAIL and GoogleDoc operated by Google 
• GOOGLE PAGE VIEW - Provides CPU/bandwidth/storage capable to serve 5 million page views / month
• GOOGLE GMAIL - Instant itnegration with SMS confimration
• 
		App Engine Instances
		
•  Computing units that holds the application
•  Fully managed sandboxes (NOT VM)
	• provide CPU+RAM+storage+Language runtime
	
   		App engine Backend
   		
•  Like the App engine instances
	• But provides higher compuritng resources  
•  Used for background processing


	儲存	   		App engine Datastores
				
•  Provide NoSQL schameless object database
•  Supports transacts and a query-engine(GQL)
•  Hig-replication datastore (HRD)
??? •  MAster-salve datastore(faster but less reliable)

Cloud SQL
•  Managed MySQL in the App Engine Cloud

Blobsotre/Cloud Storage
??? blobs? • Store files/blobs, has with ACL and REST API		
	     
		Extra
		
MapReduce API
	• highly-scalable parallel computing API for heavy computing tasks(based on Hadoop) 

Channel API
	• Push notificationsfor Javascript applications
実行Cron

実行Task Queues	
	• Sevices for execution of background work

Memcache
	• Distributed in-memory data cache
		
Google App Engine for Java
	• Java 6 in a sandbox environment
	• Java Web apps (WARs)/Servlets/JSPs+GWT
	• Persistence with JPA and JDO in the datastore
	• java.net, JavaMail and JCache + Eclipse plugin

Google App Engnine for Python
	• Python 2.5 in a sandbox environment
	• Supports rich framework like Django
	• Persistence API for the datastore service
	
-----                        -----	
----- AMAZON                 -----
-----                        -----


•  pinonner
•  概要  Iaas and Paas
•  概要 (ANY LANGUAGE)(any IDE) installed by yourself
• VM (windowns.Linux/Other OS)

			Instances
•  Ec2
	• VM (windowns.Linux/Other OS)
	•  US,EU,Japan,Brazil
	
	
	        BackEnd
        
儲存	       		Datastore
    
 DynamoDB/SimpleDB
    • Managed NoSQL cloud database
    •  highly scalable, fault-tolerant
    •  DynamoDB is faster and newer than SimpleDB
 RDS (relational database system)
	• Managed MySQL and Oracle databases
	•  Scalability, automated backup, replication

    
•  EBS(elastic block store)
•  HDD
•  OS file system
•  S3 (simple storage)
• Binary data (files, image, videos, etc)
•  accessible through web (w or w/o authnetication)
	
	        Extra
	        
	        
• SQS (message queue)
•  CloudFront (content dilivery network)
•  Elastic Cache(caching)
•  Route53(Cloud DNS)
•  SES (email)

•  Pricing 
•  On-demand pricing (per hour/per GB)
•  has 1-year free trial (credit card required)


•  Java+Spring+hibernate +PostgreSQL/MySQL/MongoDB + memecache + tomcat/Jetty
•  Ruby+Rails +mongoDB/MySQL+memcache
•  Python + Django +mamcache+PostgreSQL
•  JavaScipt + node.js +mySQL/MongoDB
•  
•  
•  
•  
•  
•  
•  
•  
•  
•  
• 

*/