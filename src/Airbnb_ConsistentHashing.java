import java.util.ArrayList;

// http://www.lintcode.com/en/problem/consistent-hashing/#
// http://www.jiuzhang.com/solutions/consistent-hashing/
// 1. Horizontal shard
// 2. hash(id)%n = specific server
// 		n: number of servers
// 3. hash(id)%(2^bits) = integer belong to some range( and that range belong to a specific server) 
//      bits: normally 16 bits range 0~65536, how many servers will make range divide to how many parts
// 4. 
/*

一般的""""数据库""""进行""""horizontal shard"""""的方法是指，

把 id 对 数据库服务器总数 n 取模，然后来得到他在哪台机器上。

这种方法的缺点是，当数据继续增加，我们需要增加数据库服务器，

将 n 变为 n+1 时，几乎所有的数据都要移动，

这就造成了不 consistent。

为了减少这种 naive 的 hash方法(%n) 带来的缺陷，

出现了一种新的hash算法：

一致性哈希的算法——Consistent Hashing。

这种算法有很多种实现方式，这里我们来实现一种简单的 Consistent Hashing。

将 id 对 360 取模，

假如一开始有3台机器，

那么让3台机器分别负责0~119, 120~239, 240~359 的三个部分。

那么模出来是多少，查一下在哪个区间，就去哪台机器。

当机器从 n 台变为 n+1 台了以后，

我们从n个区间中，找到最大的一个区间，

然后一分为二，把一半给第n+1台机器。

比如从3台变4台的时候，我们找到了第3个区间0~119是当前最大的一个区间，

那么我们把0~119分为0~59和60~119两个部分。

0~59仍然给第1台机器，60~119给第4台机器。

然后接着从4台变5台，

我们找到最大的区间是第3个区间120~239，

一分为二之后，变为 120~179, 180~239。

假设一开始所有的数据都在一台机器上，

请问加到第 n 台机器的时候，区间的分布情况和对应的机器编号分别是多少？



Notice you can assume that n <= 360.

Clarification
If the maximal interval is [x, y], 

and it belongs to machine id z, 

when you add a new machine with id n, 

you should divide [x, y, z] into two intervals:

[x, (x + y) / 2, z] and [(x + y) / 2 + 1, y, n]

Example
for n = 1, return

[
  [0,359,1]
]
represent 0~359 belongs to machine 1.

for n = 2, return

[
  [0,179,1],
  [180,359,2]
]
for n = 3, return

[
  [0,89,1]
  [90,179,3],
  [180,359,2]
]
for n = 4, return

[
  [0,89,1],
  [90,179,3],
  [180,269,2],
  [270,359,4]
]
for n = 5, return

[
  [0,44,1],
  [45,89,1],
  [90,179,3],
  [180,269,2],
  [270,359,4]
]

 * */
public class Airbnb_ConsistentHashing {
	/*
	 * @param n a positive integer
	 * @return nX3 matrix
	 * */
	public ArrayList<ArrayList<Integer>> consistentHashing(int n){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		// [x, y, z], x interval_start, y interval_end, z server_index
		ArrayList<Integer> machine = new ArrayList<Integer>();
		// Add machine 1
		machine.add(0);
		machine.add(359);
		machine.add(1);
		res.add(machine);
		for (int i = 1; i < n; i++){
			ArrayList<Integer> new_machine = new ArrayList<Integer>();
			int index = 0;
			// Get the largest range and get it divided by two
			for(int j = 1; j < i; j++){
				int rangeNow = res.get(j).get(1) - res.get(j).get(0) + 1;
				int rangeLast = res.get(index).get(1) - res.get(index).get(0) + 1;
				if (rangeNow > rangeLast){
					index = j;
				}
			}
			// Get the largest range index and split it to half 
			int x = res.get(index).get(0);
			int y = res.get(index).get(1);
			// update largest range machine interval_end
			res.get(index).set(1, (x + y) / 2);
			// Add machine New
			new_machine.add((x + y)/2 + 1);
			new_machine.add(y);
			new_machine.add(i + 1);
			res.add(new_machine);
		}
		return res;
	}
	
	public static void main(String[] args){
		Airbnb_ConsistentHashing sol = new Airbnb_ConsistentHashing();
		int n = 5;
		ArrayList<ArrayList<Integer>> res = sol.consistentHashing(n);
		for (ArrayList<Integer> a: res){
			System.out.println(a);
		}
	}
}
