/*
 * 
https://hellosmallworld123.wordpress.com/2015/10/02/%E4%BC%98%E6%AD%A5%E7%B3%BB%E7%BB%9F%E8%AE%BE%E8%AE%A1/
3.如何设计一个Uber。

	I. 首先登陆系统也是一样的设计。

	II. 而作为rider，我们需要找到当前所在的地方的附近所有的drivers，假设drivers的location都是已经存好的放在数据库里并且是不变的，我们要做一个这样的查找也是非常麻烦的。
	
	   因为即使driver_id, X, Y 都是index过的，我们要查找X在某个limit之内和Y在某个Limit之内，也需要遍历所有的X和Y才能找到我们需要的driver。

		有两种解决这个问题的办法，
		一种叫做GeoHash
		http://www.cnblogs.com/LBSer/p/3310455.html
		只要把整个地图分成小块，然后给每个Point of Interest（也就是Driver）assign一个GeoHash，那么相同的GeoHash值就是在附近的。
		要处理边界问题，我们除了当前location的GeoHash，也处理周围的8个GeoHash的所有POI，这样就cover了边界情况了，
		
		另外这些所有的找出来的点我们再重新计算到当前location的距离，然后去掉一些离开比较远的。这样就找到了结果

		一种叫做R Tree，其实是一种B Tree在二维上的拓展，
		我们把每个地图分成多个小方块，然后每个小方块里又有更多的小方块，
		这种RTree用来处理面或者线的问题更好，处理点的问题用GeoHash更好。
		http://blog.csdn.net/v_july_v/article/details/6530142
		
		作为Driver，我们可能需要push 一些Event（比如说有大型的音乐会啊之类的，我们同样也只想push到event附近的driver那里。

    III. How to design a real time system: 
    
        Driver每4秒update一次位置和速度方向信息，怎么样保证他们都被收到并且存下来呢。
		
		[Producer]我们可以用一个distrubuted message Queue，类似kafka，producer把内容写到messageQueue来，
		
		[Worker]我们的server有很多worker从message Queue里面拿到数据并且分析数据，然后记录下来，供rider来使用。
		
		[Shard]有一个问题是如果我们把所有的这些都放在同一个地方，就会很拥挤，而大部分的Uber的ride都是在同一个城市里的，
		
		       而且我们要求实时计算，所以delay可能会很严重，
		
		       所以我们可以把这些数据都分成不同的shard，相同的城市可以用一个shard，
		       
		       这样的好处就是大部分的Uber Ride应该是同城市的，
		
		       而down side是如果是cross city的话就会比较expensive，还有一点就是小城市和大城市的traffic差距很多，
		       
		       所以我们可以把很多小城市组合在一起。 
 */
public class Uber {

}
