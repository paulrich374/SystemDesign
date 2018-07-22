
public class How_Nginx_Apache_Lightttpd {
/*
 
 http://detechter.com/the-battle-of-the-web-servers-apache-vs-nginx-vs-lighttpd-2/
 
 
 apache                					nginx            			lighttpd
  				 						asynchronous            	asynchronous
 hundreds of megabytes      			few megabytes of RAM 
 
   										 one master process          single process with a single thread and 
   										 but delegates its  		 non-blocking I/O 
   										 work unto worker processes.							 
  
simultaneous connection 
requires a thread which 
incurs significant overhead.  			Nginx is faster and more reliable than Lighttpd
 
  
 * */
}
