/*
 * 

http://image.slidesharecdn.com/javascript-object-orientedprogrammingremotescripting-150919001353-lva1-app6891/95/javascript-objectoriented-programming-remote-scripting-28-638.jpg?cb=1447839450

1. polling
   request and response
2. Comet
   request and later response 
3. Streaming
   request and response HEADER
   later message and message and message
4. WebSocket
   request HEader and response HEader 
   later message 
   message req and message resp      

http://www.codeproject.com/KB/aspnet/CometGrid/NonCometPicture0.jpg
1. Ajax time-polling based update
	(1)[Req] Client0: request time expired, get me an update
	(2) Thread0: client o aksing for an update
	(3) But no update Avaiable right now
    (4)[Resp] But no update Avaiable right now
    
    (2)[Req] Client1:request time expired, get me an update
    (3) Thead1: clien1 ask for an update
    (4)  But no update Avaiable right now
    (5)[Resp]  But no update Avaiable right now

    (4) [req] client 100 :request time expired, get me an update
    (5) SERVER BUSY CANNOT SERVE ANY MORE
http://systemdesigns.blogspot.com/2015/12/facebook-chat-messeger.html?view=sidebar
http://stackoverflow.com/questions/6272904/database-design-private-chat-group-chat-and-emails

http://stackoverflow.com/questions/11655486/transmiting-receiving-compressed-data-with-sockets-how-to-properly-receive-the#tfbml-data%7B%22close_iframe%22%3Atrue%2C%22location_url%22%3A%22http%3A%2F%2Fstackoverflow.com%2Fquestions%2F11655486%2Ftransmiting-receiving-compressed-data-with-sockets-how-to-properly-receive-the%22%7D

 $$ Compress Message wit hheader and tail
 
https://github.com/NetEase/pomelo/wiki/Distributed-Chat
  chat architecture
http://stackoverflow.com/questions/32898729/socket-io-scaling-is-it-more-efficient-to-send-messages-to-specific-socket-ids
   
 * 
 * WEB MESSAGE
 * http://www.9lessons.info/2013/05/message-conversation-database-design.html
 * WEB CHAT 
 * http://www.cnblogs.com/hoojo/p/longPolling_comet_jquery_iframe_ajax.html
 * 
 * MOBILE MESSAGE
 * 
 * MOBILE CHAT
 * 
 * 
 * Core Concept: Data Structure
 * MySQL/NoSQL		
 * file:///Users/weihung/Desktop/%E4%B9%9D%E7%AB%A0Nine%20Chapter%20SystemDesign/SystemDesign_6_20151025.pdf
 * http://www.jiuzhang.com/qa/127/
 * http://wenku.baidu.com/view/b1d78a7614791711cd791720
 * http://darkhouse.com.cn/blog/4
 * http://yanmingming.sinaapp.com/?p=852
 * http://www.linuxde.net/2013/08/15150.html
 * */
public class FacebookChat {
/*
===  1.Scenario
===  2.Necessities
===  3.Action
===  4.Kilobyte
===  5.Evolve 

http://wenku.baidu.com/view/b1d78a7614791711cd791720
===  1.Scenario
      daily request: billion
      database: tens of millions
      daily feed:million
      concurrent user:100K
      machine: <30
===  2.Necessities
===  3.Action
       Architecture Difficuilty -
       chat arch VS search arch
       char arch VS ecommerce arch
       SMC principle
       XMPresentP
       Protocol Design -
       TCP
       Security layer
       PB
       
                <-------------------
                |                  |
     client -> access -> logic --> memory
                               |   |
                               --> SSD-> mongoDB
                               
                               
                         dispatcher
            |      |      |        |        |
           login  user   friend    msg    group  
                         dispatcher
            |      |      |        |        |
           loginHand  userHand   friendHand    msgHand    groupHand
            |             |           |           |            | 
            db            db          db          db          db             
===  4.Kilobyte
===  5.Evolve 


===  http://www.slideshare.net/FabioTiriticco/websocket-wiith-scala-and-play-framework
     • Browser(Client) JavaScript
     
     • Android(Client) Java
      

===  http://www.ibm.com/developerworks/library/x-ioschat/
===  Timers
	Timers. A timer task is used to periodically poll messages.php to see when new chat items arrive.
===  http://www.zhihu.com/question/20215561
===  WebSocket vs HTML5 vs HTTP vs TCP
	一、WebSocket是HTML5出的东西（协议），
   		也就是说HTTP协议没有变化，或者说没关系，
   		但HTTP是不支持持久连接的（长连接，循环连接的不算）
   		HTTP 1.0 and 1.1: keep-alive, merge multiple request to one. Has NOTHING to do with HTML, HTTP can transport nonHTML
   		Websocket: (a big patch for Http)a new protocol, intersect with HTTP. compatible with hanshake protocol
   		HTML5: a new API, new rule, new technology support websocket(upgrade) 
	二、Websocket是什么样的协议，具体有什么优点
   		Websocket: a persistent protocol (HTTP: non-persistent protocol)
   	    Http 1.0: one request and one response and finish
   	    Http 1.1: keep-alive, allow multiple http requests and multiple responses (response is passive and not allowed to be active)
   	    WebSocket: only share Http handshake
					GET /chat HTTP/1.1
					Host: server.example.com
					Upgrade: websocket
					Connection: Upgrade
					Sec-WebSocket-Key: x3JJHMbDL1EzLkh9GBhXDw==
					Sec-WebSocket-Protocol: chat, superchat
					Sec-WebSocket-Version: 13
					Origin: http://example.com
	二、教练，你BB了这么多，跟Websocket有什么关系呢？
					Upgrade: websocket
					Connection: Upgrade
					Tell Apache or Nginx I want new fashion Websocket not old time Http
					Sec-WebSocket-Key: x3JJHMbDL1EzLkh9GBhXDw==
					Sec-WebSocket-Protocol: chat, superchat
					Sec-WebSocket-Version: 13
					Key is base64 encoding and generated by browser to verify server if websocket servlet
					protocol to tell same URL but different service
					version is draft version

					HTTP/1.1 101 Switching Protocols
					Upgrade: websocket
					Connection: Upgrade
					Sec-WebSocket-Accept: HSmrc0sMlYUkAGmm5OPpG2HaGWk=
					Sec-WebSocket-Protocol: chat
					Tell clinet what protocol server upgraded Websocket协议 instead of mozillasocket，lurnarsocket或者shitsocket
					服务器：好啦好啦，知道啦，给你看我的ID CARD来证明行了吧
					Protocol 则是表示最终使用的协议
	三、Websocket的作用
					傳統ＩＭ方法 http long poll[keep waiting]
					傳統ＩＭ方法 ajax polling [pulse]
					HTTP 非常消耗资源的
					ajax polling need server fast response 需要服务器有很快的处理速度和资源。（速度）
					long poll need a big resource to handle all clients 需要有很高的并发，也就是说同时接待客户的能力。（场地大小）
					Solve Http issue 1. passive
					 				HTTP->Websocket），服务端就可以主动推送信息给客户端啦。
					 				只需要经过一次HTTP请求，就可以做到源源不断的信息传送了
					 				回调，即：你有信息了再来通知我，而不是我傻乎乎的每次跑来问你
					 WHY RESOURCE HAVE BEEN WASTING?
					 				Two Layer
					 				Problem1: handler slow
					 				接线员 Nginx decode HttP 
					 				客服 PHP Python handler
					 				本身接线员基本上速度是足够的
					 				每次都卡在客服（Handler）了，老有客服处理速度太慢。，导致客服不够
					 				SOLUTION:可以直接跟接线员建立持久连接  
									         有信息的时候客服想办法通知接线员，然后接线员在统一转交给客户。
									         这样就可以解决客服处理速度过慢的问题了。
									Problem2: too many bandwith and time
									在传统的方式上，要不断的建立，关闭HTTP协议，由于HTTP是非状态性的，
									每次都要重新传输identity info（鉴别信息），来告诉服务端你是谁
									SOLUTION:HTTP握手，所以说整个通讯过程是建立在一次连接/状态中，
									         也就避免了HTTP的非状态性，服务端会一直知道你的信息，直到你关闭请求，
									         这样就解决了接线员要反复解析HTTP协议，还要查看identity info的信息
									         著作权归作者所有。
												没有信息的时候就交给接线员（Nginx），
												不需要占用本身速度就慢的客服（Handler）了
===  http://yanmingming.sinaapp.com/?p=852
===  現代ＩＭ方法 -- Websocket 探究
	1.HTTP 协议 与 TCP 协议区别：
		HTTP  
		SUMMARY application layer protocol, browser and server, request and response                 
		1. application layer protocol  (application layer protocols: HTTP, TELNET, WebSocket)   
		2. BETWEEN browser and server
		2. HTTP stateless, multiple HTTP requests has no relation between each other
		3. HTTP request/response format- one request corresponding one response
		TCP
		SUMMARY transportation protocol, between machines to make connection
		1. transportation layer protocol (transportation layer protocols: TCP, UDP)
		2. BETWEEN machines
	2.傳統ＩＭ方法 -- 长轮询(long polling) 、短轮询(short polling) 、长连接区别(long connection):
		Long Polling => HOLD
		SUMMARY Server side hold Request
		1. != long connection
		2. use HTTP short connection but Server side hold Request
		3.NOTE: TIMER + CACHE
		Short Polling
		SUMMARY HTTP request and response
		1. server side give response whatever response having data or not
		2. browser side  send HTTP request again to check if response having data
		3.NOTE: TIMER + CACHE
		Long Connection=>SHARE
		SUMMARY multiple HTTP request one TCP connection 
		1. multiple HTTP request use one TCP connection to save time wasting 
		   in TCP connection open/close between multiple HTTP requests
		2.NOTE: SERVER CANNOT ACTIVELY SEND MESSAGE TO CLIENT   
	3.傳統ＩＭ方法 ---- Http协议 与 webSocket协议关系：
		http://www.9tut.com/images/ccna_self_study/OSI/OSI_Model_headers_added.jpg	
		http://www.radio-electronics.com/info/telecommunications_networks/ims-ip-multimedia-subsystem/ims-layers-stack.php
	 	HTTP--WebSocket Application  
	 	                Presentation
	 	                Session/Connection Establish/termination
	 	TCP             Transport 
	 	 IP             Network    .router, layer3 switch
	 	               Data Link  .MAC address
	 	                Physical   .hub, repeater
	 	傳統IM apporach #1 Polling
	 		1.Idea:         timer, browser send periodic HTTP request to server 
	 		2.Disadvantage: twice HTTP requests mimic bi-direction and increase complexity and waste banwidth and time
	 		3.Advantage: Easy to code
	 		4. Example:	 small application	
	 	傳統IM apporach #2 Long Polling
	 		1.Idea:         server hold request if no data is available
	 		                request expired and server no longer hold it. browser send request again
	 		2.Disadvantage:	increase server loading, and returned data is not guaranteed and hard to manage
	 		3.Advantage: waste less resource
	 		4. Example:	 Facebook IM, WebQQ, Hi Web		
	 	傳統IM apporach #3 Long Connection/Streaming
	 		1.Idea:         a implicit window on browser to send long connection request 
	 		2.Disadvantage:	depress scalability , increase server expenses to maintain long connection 
	 		3.Advantage: real time, no invlaid request and easy to manage
	 		4. Example: Gmail
	 	傳統IM apporach #4 Flash Socket
	 		1.Idea: Flash socket program with JavaScript
	 		2.Disadvantage: Real real time
	 		3.Advantage: Need Flash, not Http protocol and thus cannot pass through FireWall
	 		4. Example: RS Game
	4.現代ＩＭ方法 ---- WebSocket原理：
Js連接請求握手
先連 TCP
在連 Websocket		
		原理1.[真.长连接]WebSockerClient   TCPClient    TCPServer     WebSocketServer
		
		              ->connecting->sync
		              <-connected<-sync ACK
		                         -> ACK
		                         
		              ->handshake->send->------------------
		              <-handshake<-receive<-------------------
WebSocket 解决的第一个问题是，通过第一个 HTTP request 建立了 TCP 连接之后，之后的交换数据都不需要再发 HTTP request了，使得这个长连接变成了一个真.长连接。但是不需要发送 HTTP header就能交换数据显然和原有的 HTTP 协议是有区别的，所以它需要对服务器和客户端都进行升级才能实现		              
		原理2.[] 浏览器与服务器之间进行双向数据传输
在此基础上 WebSocket 还是一个双通道的连接，在同一个 TCP 连接上既可以发也可以收信息		
		原理3. Multiplexing
此外还有 multiplexing 功能，几个不同的 URI 可以复用同一个 WebSocket 连接。这些都是原来的 HTTP 不能做到的。		                   
  **5.Java中使用方法：
		1. ===Server=== jetty package
			1<dependency>
			2<groupId>org.eclipse.jetty.aggregate</groupId>
			3<artifactId>jetty-all-server</artifactId>
			4<version>8.1.9.v20130131</version>
			5</dependency>		
		2. ===Server=== 
		   IdleWebSocketServlet inherit abstract WebSocketServlet
		   to implement doWebsocketConnect() to establish websocket port
		   
		   Define WebSocket cache - new ConcurrentHashMap<String, WebSocket>();
		   to handle all websocket connections and every connection will be put into map
		   e.g., map.put("", new WebSocket()) or map.remove(""); 
連完 TCP	
看是ＨＴＴＰ還是ＷEBSOCKET	      
		   Browser       WebSocketservlet         WebSocketFactory        ""MyWebSocketServlet""       HttpServlet
		            HTTP WebSocket Request?                  make WebSocket connection(doWebsocketConnect)
		       --------------->Accept---------------------->------------------------->
		                                                    return websocket object
		                                              Upgrade<-------------------------- 
		                           
		                       Decline (go to Http request)--------------------------------------------->        
		                                                    return Http response
		        <---------------------------------------------------------------------------------------
		                                             After Upgrading request or response to Websocket Connection
		                                                 0. Establish WebSocketServletConnection and put into list
		                     response.sendError(101)
		                means server will change to Websocket protocol
		                as client (browser) request
		        <----------------------------------------1. Handshake		                                                 2. WebSocket.onOpen
		                                                 3. reset new protocol connection

JavaScript
websocket  
onopen
onclose
onmessage
onerror

Js連接請求握手 		1. Browser            HTTP
Request Method:GET
asks for an upgrade
		   			*2. WebSocketservlet   WebSocket Request? YES go to 3. NO go to 10.
		   			3. WebSocketFactory   make WebSocket connection
		   			4. MyWebSocketServlet return Websocket object
Status Code:101
"Switching Protocol"
UpgradeWebSocket	5. MyWebSocketServlet Upgrade  
		   			*6. MyWebSocketServlet 0. Establish WebSocketServletConnection and put into list
		   			7. MyWebSocketServlet 1. Handshake and server will change to Websocket protocol
		   			8. MyWebSocketServlet 2. WebSocket.onOpen
		   			9. MyWebSocketServlet 3. reset new protocol connection  
双向数据传输  			10. HttpServlet        HTTP request
		    		11. HttpServlet        HTTP response                          


HTTP WebSocket 解决的第一个问题是，通过第一个 HTTP request 建立了 TCP 连接之后，
MYWEBSOCKET SERVLET 之后的交换数据都不需要再发 HTTP request了，使得这个长连接变成了一个真.长连接。
MYWEBSOCKET SERVLET 但是不需要发送 HTTP header就能交换数据显然和原有的 HTTP 协议是有区别的，
UPGRADE 所以它需要对服务器和客户端都进行升级才能实现                                          
		3. ===Client=== Define webSocket and implement WebSocket.OnTextMessage port
		   e.g., SessionWebSocket: implement onOpen, onClose, onMessage methods
		   when more event happens, define list of event, onMessage will execute corresponding methods in accordance 
		4. ===Client=== JavaScript
		   1. support websocket or not
		   2. create a websocket
		   3. open a websocket connection
		   4. change http: to ws:
		   4. open a message receiving connection
		   5. close websocket connection
		   SEE BELOW JAVASCRIPT CODE
	6.WebSocket优势：	
		1. 最主要功能 ***@@@$$$$$ sever actively send message to client(no need extra request)
		2. more efficient than HTTP protocol
	7.WebSocket劣势：
		1. browser compability issues only support IE10
		2. You need to build your own protocol, even for the simplest thing! 
		3. You cannot use any of the friendly HTTP statuses, body etc.
		4. http://www.slideshare.net/FabioTiriticco/websocket-wiith-scala-and-play-framework 
Problem: Waking Dead Websocket
比如说，这些坑爹的中间节点可能会认为一份连接在一段时间内没有数据发送就等于失效，
它们会自作主张的切断这些连接。在这种情况下，不论服务器还是客户端都不会收到任何提示，
它们只会一厢情愿的以为彼此间的红线还在，徒劳地一边又一边地发送抵达不了彼岸的信息。
而计算机网络协议栈的实现中又会有一层套一层的缓存，除非填满这些缓存，
你的程序根本不会发现任何错误。这样，本来一个美好的 WebSocket 长连接，
就可能在毫不知情的情况下进入了半死不活状态。	
Solution: Ping/Pong Frame
而解决方案，WebSocket 的设计者们也早已想过。
就是让服务器和客户端能够发送 Ping/Pong Frame（RFC 6455 - The WebSocket Protocol）。
这种 Frame 是一种特殊的数据包，它只包含一些元数据而不需要真正的 Data Payload，
可以在不影响 Application 的情况下维持住中间网络的连接状态。	
		
	8.Why message has frame? 
	 	To know the length of the message before transmitting data
		
http://www.cnblogs.com/hoojo/p/longPolling_comet_jquery_iframe_ajax.html
===  Web 通信 之 长连接、长轮询（long polling）


1.普通轮询 Ajax方式
2、普通轮询 iframe方式
3、长连接iframe方式
4、ajax实现长连接
上面这段代码就是才有Ajax的方式完成长连接，主要优点就是和服务器始终保持一个连接。
如果当前连接请求成功后，将更新数据并且继续创建一个新的连接和服务器保持联系。
如果连接超时或发生异常，这个时候程序也会创建一个新连接继续请求。
这样就大大节省了服务器和网络资源，提高了程序的性能，从而也保证了程序的顺序。






===  http://www.linuxde.net/2013/08/15150.html
===  淘宝技术分享 HTTP长连接200万尝试及调优
	For server, we normally foucs on the loading of QPS
	However, there is an application which focus more on Connection e.g., Chat
	对于一个server，我们一般考虑他所能支撑的qps，但有那么一种应用， 我们需要关注的是它能支撑的连接数个数，而并非qps
	这种应用常见于消息推送系统，也称为comet应用，比 如聊天室或即时消息推送系统等
	For long polling, there is no data being transmitted, only network connection.
	这样的长连 接，往往我们是没有数据发送的，所以也可以看作为非活动连接。
	对于系统来说，这种非活动连接，并不占用cpu与网络资源，而仅仅占用系统的内存而已
	
	1. 服务端的准备
		我们需要一台大内存的服务器，用于部署Nginx的comet应用
	2. 客户端的准备
		在一台系统上，连接到一个服务时的本地端口是有限的。由于端口是16位整数，也就只能是0到 65535，
		而0到1023是预留端口，所以能分配的只是1024到65534，也就是64511个。
		也就是说，一台机器只能创建六万多个长连接。要达到 我们的两百万连接，需要大概34台客户端。
		我们可以采用虚拟ip的方式来实现这么多客户端，如果是虚拟ip，
		则每个ip可以绑定六万多个端口，34个虚拟ip就可以搞定

 */
/*
http://yanmingming.sinaapp.com/?p=852
function connect() {
02
           var target = document.getElementById('target').value;
03
           if (target == '') {
04
               alert('Please select server side connection implementation.');
05
               return;
06
           }
07
           if ('WebSocket' in window) {
08
               ws = new WebSocket(target);
09
           } else if ('MozWebSocket' in window) {
10
               ws = new MozWebSocket(target);
11
           } else {
12
               alert('WebSocket is not supported by this browser.');
13
               return;
14
           }
15
           ws.onopen = function () {
16
               setConnected(true);
17
               log('Info: WebSocket connection opened.');
18
           };
19
           ws.onmessage = function (event) {
20
               log('Received: ' + event.data);
21
           };
22
           ws.onclose = function () {
23
               setConnected(false);
24
               log('Info: WebSocket connection closed.');
25
           };
26
       }
27
 
28
       function updateTarget(target) {
29
           if (window.location.protocol == 'http:') {
30
               document.getElementById('target').value = 'ws://' + window.location.host + target;
31
           } else {
32
               document.getElementById('target').value = 'wss://' + window.location.host + target;
33
           }
34
       }



 * */	
}
