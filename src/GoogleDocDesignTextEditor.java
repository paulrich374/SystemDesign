/*
// http://www.isnowfy.com/collaborative-editor/
// Scale issue(Consistency):  http://fex.baidu.com/blog/2014/04/realtime-collaboration/
第三个面试官进来，又是一个很nice的亚裔面试官。

貌似Snapchat里面很多Stanford CS asian graduates, 

也算是一个比较有意思的现象。


这一轮是面系统设计，题目是如何设计google doc这类实时保持同步的online editing tool，


要求面试者设计的细节越多越好。


系统设计的问题其实是很考较工作经验以及对分布式系统设计的理解，以及模糊需求下良好沟通的能力。

对于这类问题，以下几点需要点出：

Client-Server架构。

	Client（在此指用户浏览器）自然是必须的，Server端在这个设计问题中也是需要的，
	因为即使所有Client都下线之后，Server端依然应该有数据备份。
Consistency. 
	这是本问题的关键：如何在多用户输入的情况下保持data consistency，
	这是依靠Server端synchronize change list来实现的（同理，any conflict is handled here）。
Scalability. 
	当用户量大量增长的时候，如何scale设计架构来满足需求。
	
	
[[[[[//	N: 假设有 1 billion user
//  user = 10^9

//	每个user平均每天new一个event
//	平均每天读10次
//	那么大概每秒10k的写和100k的读
//    Write = 10 k  = Read/10
//    Read  = 100 k = 10^5 = daily active user


//	K: 如果每个user可以使用1M的存储空间，那么total就是1PB，属于大数据了
//    Storage = 1M*10^9 = 10^15 = 1PB


//	当然实际使用的情况感觉应该没有这个大，但是potentilly还是可能的, 我感觉实际情
//	况100T应该是够了 （90%的user不怎么使用calendar）


//	从这个分析来说， Cassandra handle起来应该没什么问题，是一个不错的选择, 一般
//	的SQL就不适合处理这么大量了
//	http://massivetechinterview.blogspot.com/2015/10/google-calendar-architecture.html
]]]]



 * 
 * 面试题先是oo desing，要求设计一个 text editor，
 * insert，delete各种command，
 * 可以 redo/undo。
 * 然后follow up 如何扩展成google docs，如何防止conflict, 
 * 直接答不会，最后跪了。 
 * 
 * google docs是保存各种action然后本地render的时候复原成文档的，
 * 所以处理conflict就很简单了 

https://www.quora.com/What-is-a-good-data-structure-for-a-text-editor-like-Word-Google-Docs
http://www.themianjing.com/2015/06/%E6%96%B0%E9%B2%9Ctwo-sigma-onsite-%E5%B7%B2%E8%B7%AA/
2. text editor..设计题..要实现..insert(position), delete(p1, p2), highlight(p1, p2)， redo ，undo….等..



1.==== Data Structure ========================================== 
$$$$$$$$ http://www.sanfoundry.com/java-program-implement-rope/
https://www.quora.com/How-does-one-develop-a-simple-text-editor

APPROACH#1 问你用什么数据机构之类的。
我一开始说要用char[][]。 

APPROACH#2 但是給批评了好久…最后用了arraylist 
貌似…勉强通过了….
http://www.codeproject.com/Articles/237508/How-To-Build-A-Simple-Text-Editor-A-Tutorial


APPROACH#3 
$$$$$$$$ https://en.wikipedia.org/wiki/Rope_(data_structure)
One data structure I'm aware of for implementing a text editor is a  rope, 

which is a binary tree with strings stored at the leafs. 

Inner  nodes contain information to allow you to access characters by position.  

This allows you to edit text more efficiently than just changing a long  string. 

This is enough for a basic text editor like notepad, 

but word  processors like Word require more data to be stored.

1.5===insert(position), delete(p1, p2) ========================================== 


2.==== Highloght ========================================== 

然后…用什么储存..hightlight的…
APPROACH#1 我一开始用hashset了..但是有个问题就是.
delete的东西里面.刚好是hightlight的..那你怎么知道..你delete 的东西..

APPROACH#2 也在hashset里面…所以.后来我用interval tree了…..他说…有道理多了….
$$$$$$$$ http://www.geeksforgeeks.org/interval-tree/
Consider a situation where we have a set of intervals and we need following operations to be implemented efficiently. 
1) Add an interval
2) Remove an interval
3) Given an interval x, find if x overlaps with any of the existing intervals.
Interval Tree: The idea is to augment a self-balancing Binary Search Tree (BST) like Red Black Tree, AVL Tree, etc to maintain set of intervals 
so that all operations can be done in O(Logn) time.

APPROACH#3 Highlighting and comments could be stored by using special characters,
 
strings to  denote what text to highlight, comment. 
 
A formatter would read these  special characters and remove them to format the rest of the text for  displaying, 
 
printing.


3.==== Redo/Undo ========================================== 
$$$$$$$$  https://www.quora.com/How-is-undo-and-redo-functionality-typically-implemented
然后最后问了redo 和undo 怎么做…我用两个stack 储存operation….

https://www.quora.com/How-is-undo-and-redo-functionality-typically-implemented
APPROACH#1 There are two stacks, an undo stack and a redo stack. 

Whenever a user action is performed, 

a pair of action structures are pushed onto the undo stack - 

the pair has the action itself and its inverse.

When Undo is invoked, we pop the pair from the undo stack and put it on the redo stack. 

We invoke the second action (the inverse)

When Redo is invoked, we pop the pair from the redo stack and push it on the undo stack, 

we invoke the first action in the pair.

Undoability and Redoability are defined by whether either stack is empty.

When the user performs any action, the redo stack is cleared.

This also serves as an easy way to serialize the entire stack of user actions as a project. 

This way, the user can be right back where they were when they load a previous project.
  
  
http://stackoverflow.com/questions/3583901/how-to-design-undo-redo-in-text-editor 
APPROACH#2 You can model your actions as commands, that you keep in two stacks. One for undo, another for redo. 

You can compose your commands to create more high-level commands, like when you want to undo the actions of a macro, 

for example; or if you want to group individual keystrokes of a single word, or phrase, in one action.

Each action in your editor (or a redo action) generates a new undo command that goes into the undo stack

 (and also clears the redo stack). 
 
 Each undo action generates the corresponding redo command that goes into the redo stack.

You can also, as mentioned in the comments by derekerdmann, combine both undo and redo commands into one type of command, 

that knows how to undo and redo its action.
  

4.==== Concurrent Edit ========================================== 


APPROACH#1 I'm  not sure about how concurrent edits in Google docs are handled, 

but I  assume that they queue each user's edit by time and do them one by one,

remembering how an edit changes the position of future edits.
$$$$ https://confluence.atlassian.com/doc/concurrent-editing-and-merging-changes-144719.html  
If someone else has saved the page before you, when you click Save, 
Confluence will check if there are any conflicts between your changes and theirs. 
==> If there are no conflicting changes, Confluence will merge the changes.
==> If there are conflicts, Confluence will display them for you and give you the option to:
Continue editing - Continue to edit the page; useful if you want to manually merge the changes.  
  
  
APPROACH#2 OT
$$$$$$$$ 

APPROACH#3 Mutex/Semaphore  

*/
public class GoogleDocDesignTextEditor {
/*协同编辑主要来说需要这么几个步骤

1、计算出当前用户的对文档作出的修改发送到服务器
	http://www.jiuzhang.com/solutions/longest-common-subsequence/
	@http://www.jiuzhang.com/solutions/longest-common-substring/
	对于第一个步骤而言，就是类似于linux中的diff操作，去比较当前的文档内容和之前的文档内容，来得到用户对于文档的修改。
	可以对两篇文档，用动态规划的方法，找到最长公共子序列，然后除去公共部分，剩下的就可以得到差异部分了，用动态规划的方法的时间复杂度为O(n^2)，


2、对于所有用户对文档进行的修改进行合并以及冲突处理
	得到差异发送到服务器我们就需要进行冲突的合并了，这个步骤叫做operational transformation。
	比如用户A在文档字符数3的位置插入了test，用户B删掉了文档字符数5-7的位置，
	合并之后就变成了用户A在3的位置插入了test，用户B删掉了文档9-11的位置，
	这样对于所有用户的修改进行合并，



3、将合并之后结果返回到前段
	再把所有修改告知所有的用户，来确保所有用户显示的内容始终是一样的。
	
	
4、将光标移动到需要的位置
	最后就是移动光标的位置到相应的位置上，其实就是判断光标之前的操作都进行了什么，相应的去把光标前后移动就可以了。



基本上按照这个思路，利用comet技术等，多注意一些细节问题，
就可以去自己实现一个类似google doc的在线协同编辑器了，
我按照这个想法，实现放到了sae上，源代码在这里，有兴趣的可以看看。



 * 
 *
 * 
 * */
}






/*
I was preparing for technical interviews and would like to know how could I go about briefly explaining an interviewer about the approach to designing the following programs without going into unnecessary details
1. Program that lets people play tic tac toe with each other over the internet?               
2. A suitable data structure for a photo editor or text editor? Why?
3. Implementing code to operate the elevators?
4. Implementing the rendering engine of a web browser?
I could come up with the following:
There are primarily 2 parts to the program. One deals with the rendering of the board display. The other is the actual engine containing methods to find the winning move, AI, game completion, etc. Then, there will be the code related to the network.
A linked list of strings seems appropriate for the text editor, since the primary element is text and related manipulations. Does a linked list of objects seem right for the photo editor?
This again involves a display rendering of the elevators along with resource synchronization.
Don't have much clue about how to approach this one.
Are the sufficient enough? Am I missing some important details in approaching these problems.

 
 
The photo editor is an interesting one.
You need to consider the requirements of a photo editor:
rapid selection, decode and display of part of a multi-megabyte data structure (16M?+)
responsive display of user operation of paint tools - the brush should not lag, even if brushing over the whole image
can you efficiently operate paint tools when zoomed out?
save to common formats efficiently
consider impact of all this data on CPU cache and bandwidth - note that accessing main memory can take 100s of times longer than accessing cache. How do you make most operations happen in the cache?
what common features do existing editors have - undo, layers, effects, real-time blend modes, gamma conversion at the final display pass
I suggest holding multiple zoom levels with lower resolution and bit-depth (like mipmaps) in memory at once to allow efficient operation at different zoom levels, encoding changes as patches to the base image, not directly editing the base (i.e. layer every change separately). The patches could be encoded as a grid with only painted squares having memory allocated to them. Edits can be encoded as actions (e.g. tool, mouse down, movement, mouse up) before they are rendered into pixels - this allows a cheap paint to be done as patches at the current mipmap level and quickly displayed, while a background thread creates patches at the other mipmap levels.
 */
