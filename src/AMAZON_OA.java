/*
https://github.com/ankitgangal/CodingProblems/blob/master/HackerRank%20Questions/November%20hack101%202013/ServiceLane.java
https://github.com/ankitgangal/CodingProblems/blob/master/HackerRank%20Questions/StockMaximize.java
https://github.com/ankitgangal/CodingProblems/blob/master/HackerRank%20Questions/October%20Hack%202013/ChocolateFeast.java
https://github.com/ankitgangal/CodingProblems/blob/master/HackerRank%20Questions/October%20Hack%202013/AngryChildren/AngryChildren.java
==================   http://203.88.163.207/thread/138834
找人内推的Amazon, 因为毕业久了。。就走社招流程，昨天刚做了发的Hackerrank的题，90min,3道，leetcode原题。。紧张了好久才做掉（感谢V）： 
1.在一个String中找到第一个重复出现的单词。eg: He had had a ..., return had. 输入的String中单词可以被space，tab, colon, simicolon, dash, dot,隔开，并且是多个！！多个！！！开始没有仔细看题。。 
2.merge 2 sortted array... 
3.leetcode的Candy，友情提醒这个题亚麻把Scanner已经写好了，input file 第一行是告诉有几个人。。下面的几行是每个人的score， 答题部分的输入参数int[] score，是已经去掉input file第一行的值。。所以直接照着leetcode答案搬就可以了。。。。开始没有读他写好的input部分。。index折腾半天都不对。。。小伙伴们仔细审题哇。。 

最后：攒人品。。求过。。求电面。求onsite。。求各种过。。大家新年快乐。。{:4_107:} 
http://instant.1point3acres.com/thread/81338
http://www.1point3acres.com/bbs/thread-169516-1-1.html
==================   http://cv.qiaobutang.com/post/55c2d7570cf242d2e7fd2a5f
题1:二维square数组spirally shift一位，顺时针。如果输入的不是square数组，输出error，否则输出处理后的二维数组。

输入：

2 ／／接下来的输入行数
1 2 ／／ 每个元素之间以space隔开
3 4 ／／ 同上. from: 1point3acres.com/bbs 

输出：

3 1
4 2

输入：

3
1 2 3 
4 5 6
7 8 9

输出：

4 1 2
7 5 3 
8 9 6

输入：

2
1 2 3
4 5 6

输出：

ERROR


题2:二维数组，不一定是square的。写一个函数，检查每个元素的K个邻近元素（within k indices)中是不是有重复元素。输出为，YES或者NO。

输入：

4 ／／ 接下来的行数个数，即二维数组的行数
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16. 
3 ／／ K的数值

输出：
NO. 

输入：
3
1 2 3 
4 5 6
5 8 9
2

输出：
YES

输入：
4
1 2 3 
4 5 6
7 8 9
10 11 12
2

输出：
NO.
==================   http://cv.qiaobutang.com/post/55c2fe270cf242d2e7fd9f60
就在刚刚结束了amazon的hackerrank test。时间为75分钟2道编程题目。

1. List Manipulation(Coding)

输入：    L1->L2->L3->R1->R2->R3
返回：    L1->R1->L2->R2->L3->R3
输出：
10
30
20
40
50

Sample Input #1:
4      //length of list(but you do not know it in your function.)

linkedlist:
1. 
2.
3
4

Sample Output: 
1
3
2
4

Sample Input #2:

5//length of list(but you do not know it in your function.)

LinkedList:
10
20
30
40
50

2. Validate statement

括号匹配。


Input Format:
A string where characters could be either a number or ()[]

Output Format:

A boolean, true if the input is balanced, false otherwise

Sample Input#1:
([ 5 2 ] 12 )

Sample output #1:
true

Sample Input #2:
40[12 23[4 5 (12 ) ) ]

Sample Output #2:
false

Sample Input #3:
13 56 89 103

Sample Output#3:
true;
==================$$$$$$$$$$$   http://www.meetqun.com/forum.php?mod=viewthread&tid=3387&extra=page%3D11%26filter%3Dtypeid%26typeid%3D49%26orderby%3Dlastpost
题目是：
Find the most common "3 page path" on a website given a a log. 2 R6 S5 S- H; F* C# u4 H  P

www.glassdoor.com/Interview/Find ... a-log-QTN_51746.htm. O0 j3 }9 |  a+ o
+ _+ x3 u) j4 D2 v

网站自带test case, 要在上面写code编译通过和case. 要分析时间复杂度，还有写注释，写assumption, document.  据说写code过程会被全程记录。% p, Q0 M) h8 {8 A! U  R- [
; ?. l2 b( r7 ?9 O  F% P" ^
0 v* k6 ]0 T3 X7 \: R
先说题目：用两个hashtable来做。第一个hashtable给customer id的page归类，第二个hashtable来给three page type记录出现次数。做出来了之后貌似不难。但是在90分钟内，要先理解题意，document, 写出bug free code and make sure designed algorithm is right, 加上会紧张因为限时，还是有一定挑战的。（至少对我，对大神一切无视）

做题时开了视频和男票一起商量，先把题目发给他，然后一起讨论，他反应很快，很快就理解了题意，然后告诉了思路，我们稍加讨论，我自己写code,  算法思路是对的，但是因为使用hashmap container不是特别熟悉，因为hashmap里面还要用vector, nested container, 导致查了一些referrence浪费时间，最后debug 到compiler通过，但是test case没过，时间不够提交了。

事后自己再调试了一会儿，是hashmap的一个自带函数用错了，把结果弄出来了，思路是对的，再发给了recruiter. 也不知道会不会看。现在还在等消息，求bless了。面的是Front End SDE.% e% _- F: `# B
/ |7 Z+ D% x. y% v( K
希望对大家有用。对于这种code test, 要编译通过的，对于编程熟练度要求比较高。
==================   http://www.1point3acres.com/bbs/thread-110152-1-1.html
给个time O（logN）的解法
int FindSortedArrayRotation(int array[], int length)
{
        if (array == NULL || length == 0)
                return -1;
        if (array[0] < array[1] && array[0] < array[length - 1])
                return 0;
        else if (array[length - 1] < array[length - 2] && array[length - 1] < array[0])
                return length - 1;
        else
        {
                int left = 1, right = length - 2; 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
                while (left <= right)
                {
                        int mid = left + (right - left) / 2;
                        if (array[mid] < array[mid - 1] && array[mid] < array[mid + 1])
                                return mid;
                        else if (array[mid] > array[left])
                        {. 1point3acres.com/bbs
                                left = mid + 1;
                        }
                        else. visit 1point3acres.com for more.
                                right = mid;
                }
        }
        return -1;
}.1point3acres缃�
==================   $$$$$$$$$$$ http://instant.1point3acres.com/thread/140913
看图片 

求onsite 

题不难 基本就是建一个bst 然后level order search 
要注意的是 最后的输出 

https://github.com/piczmar/try-it-out/tree/master/com.tryitout.recruitment/src/test/java/com/tryitout/rectruitment/mobeelizer
https://github.com/UWFlow/flow-android/tree/master/src/com/uwflow/flow_android/network

http://www.1point3acres.com/bbs/thread-169516-1-1.html
==================    http://www.jiuzhang.com/interview/61/
题目1
Find first repeating letter in a string.
比如输入“abcba”, 返回“a”

题目2
Merge 2 arrays in 1 array.
static void mergeArray(int[] a, int[] b, int M)
两个sorted array都有M个元素，但是a的capacity是M， b的capacity是2M，最后是把a中的元素加入到b中，保持sorted。

题目3
Stock Maximize
题目在这里：https://www.hackerrank.com/challenges/stockmax
答案可以google，有很多人pose出来了。当时一紧张，光顾着调自己的代码，没有去网上看别人的solution。结果最后自己的代码还几个test case没过。
http://instant.1point3acres.com/thread/157178

http://instant.1point3acres.com/thread/81338
http://203.88.163.207/thread/138834


Amazon full time OA面经
经 经 http://wdxtub.com/interview/14520850399861.html
http://cv.qiaobutang.com/post/55c1c1b80cf298cd1425dc8a
http://cv.qiaobutang.com/post/55c2e94c0cf242d2e7fd5fd2


http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=148073&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311
http://www.1point3acres.com/bbs/thread-167097-1-1.html
http://www.1point3acres.com/bbs/thread-121706-1-1.html
http://cv.qiaobutang.com/post/55c3038e0cf242d2e7fdb191
	http://cv.qiaobutang.com/post/55c306600cf242d2e7fdbb7f
	http://www.1point3acres.com/bbs/thread-121902-1-1.html
I.
一是7道改bug, 
的基本就是一个符号,或者一个变量名,都是很小的地方,筒子们不要想多了

1) ArraySum: 计算一个整数数组的合。【错在sum = arr[i]，应为sum += arr[i]】
2) CountOccurence: 统计某整数在整数数组中出现次数。【记不太清了】
3) CheckGrade: 根据百分制分数计算GPA。【错在判断条件使用了"||"，应为"&&"】
4) RemoveElement: 删除数组中特定位置元素，超出范围则返回原数组。【错在迭代时下标多加了一次】
5) DigitCount: 将一个整数除以它的位数取余数。【错在最后返回的除法时】
6) PrintPattern: 输入一个整数n，若为奇数则输出从1开始的n个奇数，否则输出从0开始的n个偶数。【错在对待输出的数的加法没有在循环语句中】
7) Manchester: 输入一个01数组，输出manchester编码。【记不太清了】

http://cv.qiaobutang.com/post/55c2edb70cf242d2e7fd6bf4
1）计算array和
2）统计某数出现次数，
3）计算letter grade，
4）去除数组中在index的数，
5）将一个整数除以位数然后再取余数，
6）输入一个整数n，若为奇数则输出从1开始的n个奇数，否则输出从0开始的n个偶数。
7）manchester code， 两个元素相同输出 0，不同输出 1。


http://cv.qiaobutang.com/post/55c3057f0cf242d2e7fdb87f
1.计算array 和， sum = arr[i] 改成 sum += arr[i]
2. Count Occurenece: while 循环里加i++
3. Grading: ||改成&&
4. 去除给定index。 第七行arr[i++]改成arr[i+1]
5. Digit count：保留原数就行。.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
6. 降序排列：排序时符号写错了，改成大于
7. print pattern： 忘了哪错了。原来没看到。自己改的。后来发现面经历都有。。

1. 把所以除了偶数以外的数字变为1其余变为0。
2. selection sort对数组排序，不记得改了啥。。
3. insertion sort对数组排序，记得是改了index。
4. 然后是输入3， 输出1，11，111。
5. 数组中找出重复值并删除。
6. 忘了题目背景，for循环调整花括号。
7. 还有一题暂时想不起。。容我再想想。。。。


我这次考了
收邮费的那题，
6角桌，
向西走向北走什么的最后问方位的题，
印度广播公司的那个题也考了，还有就是定义新运算和录取新员工的题。
都挺简单的。不过还是要看一下面经，有的题很长，不看的话阅读时间很长，有可能不够用。

1） matrix顺时针旋转一个数字。
比如：
1 2 3
4 5 6
7 8 9

要变成
4 1 2
7 5 3
8 9 6

2）找matrix中重复数字的距离，如果小余等于给定的值，输出YES，否则输出NO。

比如：
1 2 3
4 5 2
7 8 9

问距离是否在2之内，  输出YES， 因为第一个2向下移动1，在向右移动1，就到了另一个2上面了。

II.
二是二十几道逻辑题, 
八仙桌啊, 字母题啊,数字规律呀啥的. 看看就没问题了,万变不离其宗.

1) 985:874 => 763:_?_【652】
2) FASTER:HCUVGT => SLOWER:_?_【UNQYGT】. From 1point 3acres bbs
3) BAK, DCM, HGQ, MNW 挑选不同【MNW】
4) AE5, DF6, HN14, KP18 挑选不同【KP18】
5) I: Birthday is before October and after Feburary. II: Birthday is after April and before August. 问根据I，II或者两者组合是否可以推出生日在哪月。

// 其余题目记录不全。还有如Candidates问题、六角桌问题、定义运算符让判断结论是否成立问题。


III.
三是编程题
right rotate, 
valid gray code和 
remove vowel 
给一个LinkedList, 把后半段reverse 1,2,3,4,5,6 -> 1,2,3,6,5,4
给出两棵树root1与root2，判断后者是否为前者的子树
z的coding是two sum变形？返回的不是index是出现target的次数？
http://cv.qiaobutang.com/post/55c1c1b80cf298cd1425dc8a
two pair sum有duplicate吗	
	
	9、2015Amazon码农全职due的OA经验分享
	10、2015Amazon OA第二轮经验分享
	11、2015Amazon fulltime oa 经验分享
	12、2015Amazon OA debug 和 reasoning
	13、2015亚马逊Amazon OA 分享
	14、2015Amazon SDE/Test Fulltime OA
	15、2015 Amazon OA due 经验分享
	16、2015 amazon OA 笔经
	17、2015亚马逊oa笔经分享
	18、2015Amazon的SDE测试方向的OA
	19、2015亚马逊90min test
	20、2015Amazon intern oa due 笔经
	21、2014亚马逊OA笔经
	22、2015亚马逊OA经验分享
	23、2014亚马逊OA经验分享
	24、2015亚马逊OA经验分享

	
	5、2015Amazon Full-time的SDE OA
	6、一道Amazon OA逻辑题
	7、2015 Amazon 技术类 面经 含源程序
	8、2015amazon hackerrank test 真题
	9、2015 Amazon OA 两道题 120mins

http://www.jiuzhang.com/interview/64/
	2015
今天晚上刚做完 amazon 的OA

最后一道编程题是 twopair sum
input 是一个int array 和 int targetvalue
outut是 targetvalue 出现了几次 和lintcode上的题基本一样
debugging 和 reasoning 的题没怎么变
有好几道题是有关于 六个人怎么安排座位
   
http://www.jiuzhang.com/interview/61/
2015
刚做完amazon hackerrank online, 社招职位。总共三道题目，难度都不大，但是第三题，我有几个test cases没过，后来发现题目意思理解有误，不能同一天买卖。
题目1
Find first repeating letter in a string.
比如输入“abcba”, 返回“a”
题目2
Merge 2 arrays in 1 array.
static void mergeArray(int[] a, int[] b, int M)
两个sorted array都有M个元素，但是a的capacity是M， b的capacity是2M，最后是把a中的元素加入到b中，保持sorted。
题目3
Stock Maximize
题目在这里：https://www.hackerrank.com/challenges/stockmax
答案可以google，有很多人pose出来了。当时一紧张，光顾着调自己的代码，没有去网上看别人的solution。结果最后自己的代码还几个test case没过。
http://www.meetqun.com/thread-2987-1-1.html
	2014
	RHCAI   OEST   HNDA   ADEH
	找出不同的一个。% u$ c, f: h+ L; Z, r" ~
	有说选A的，因为就它5个字母；/ M: h) K' g& e% k" }
	有说选B的，因为就它没有A、H.. m& y4 T3 o: w  B0 _
	求高人指点。
public class AMAZON_OA {

}
*/