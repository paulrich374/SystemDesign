import java.util.ArrayList;
import java.util.HashMap;

// 1. intensive
// 2. virtual node
// 3. clockwise to determine which server
// 4. hash(id)%n = specific server
// 		n: number of servers
// 5. hash(id)%(2^bits) = integer belong to some range( and that range belong to a specific server) 
//      bits: normally 16 bits range 0~65536, how many servers will make range divide to how many parts
// 6. hash(id)%(2^bits) = integer belong to some point( and that point move clockwise to find the first server) 
//      bits: normally 16 bits range 0~65536, how many servers will make range divide to how many parts
// 1. Datastructure
//		Map<Integer, ArrayList<Integer>>
//           machine/server ID corresponding with its 3 virtual server ID
/*
在 Consistent Hashing I 中我们介绍了一个比较简单的一致性哈希算法，

这个简单的版本有两个缺陷：

1. 增加一台机器之后，数据全部从其中一台机器过来，

	这一台机器的读负载过大，对正常的服务会造成影响。

2. 当增加到3台机器的时候，每台服务器的负载量不均衡，为1:1:2[server1: 0-89, server2:90-179, server3:180-359]
for n = 3, return

[
  [0,89,1]
  [90,179,3],
  [180,359,2]
]

为了解决这个问题，引入了 micro-shards 的概念，

一个更好的算法是这样：

1.	将 360° 的区间分得更细。从 0~359 变为一个 0 ~ n-1 的区间，将这个区间首尾相接，连成一个圆。

2.	当加入一台新的机器的时候，""随机""选择在圆周中撒 k 个点，代表这台机器的 k 个 micro-shards。

3.	每个数据在圆周上也对应一个点，这个点通过一个 hash function 来计算。

4.	一个数据该属于那台机器负责管理，是按照该数据对应的圆周上的点在圆上顺时针碰到的第一个 micro-shard 点所属的机器来决定。

n 和 k在真实的 NoSQL 数据库中一般是 2^64 和 1000。

请实现这种引入了 micro-shard 的 consistent hashing 的方法。主要实现如下的三个函数：

create(int n, int k)
addMachine(int machine_id) // add a new machine, return a list of shard ids.
getMachineIdByHashCode(int hashcode) // return machine id


Notice

当 n 为 2^64 时，在这个区间内随机基本不会出现重复。

但是为了方便测试您程序的正确性，n 在数据中可能会比较小，

所以你必须保证你生成的 k 个随机数不会出现重复。

LintCode并不会判断你addMachine的返回结果的正确性（因为是随机数），

只会根据您返回的addMachine的结果判断你getMachineIdByHashCode结果的正确性。



Example
create(100, 3): create a range 0-100 with each server has 3 virtual servers
addMachine(1): add a machine and return 3 random server ID
>> [3, 41, 90]  => 三个随机数

getMachineIdByHashCode(4): get hashcode 4 correponding machine (clockwise to 41 machine1)
>> 1

addMachine(2): add a machine and return 3 random server ID
>> [11, 55, 83]

getMachineIdByHashCode(61):get hashcode 61 correponding machine  (clockwise to 83 machine2)
>> 2

getMachineIdByHashCode(91):get hashcode 91 correponding machine  (clockwise to 100 to 3 machine1)
>> 1

 * */
public class Airbnb_ConsistentHashingII {
	public int n,k;
	public ArrayList<Integer> ids = null;
	public HashMap<Integer, ArrayList<Integer>> machines = null;
	/* create a range 0-n with each server has k virtual servers
	 * @param n a positive integer
	 * @param k a postitive integer
	 * @return a Airbnb_ConsistentHashingII object
	 * */
	public static Airbnb_ConsistentHashingII create(int n, int k){
		// a range and 
	}
	/* add a machine and return 3 random server ID among 0-n
	 * @param machine_id an integer
	 * @return a list of shard ids
	 * */
	public ArrayList<Integer> addMachine(int machine_id){
		// a random generate to generate most equal probability random id(hashcode)
	}
	/* get machine id associated with hashcode which is nearest to hashcode (clockwise nearest)
	 * @param hashcode an integer
	 * @return a mahchine id
	 * */
	public int getMachineIdByHashCode(int hashcode){
		// loop thruough all the existing hashcode
	}
}
