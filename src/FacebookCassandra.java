import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

// http://www.lintcode.com/en/problem/mini-cassandra/
// 1. Data Structure
// 		range query    NavigatableMap
// 		search row_key HashMap
// 		sorted column  TreeMap
// 		Column column_key and column_value
// 		HashMap<String, NavigableMap<Integer, String>>
// 2. Insert 
//		row_key, column_key, column_value
// 3. Query  
//		row_key, column_key start, column_key end
/*

Cassandra is a NoSQL storage. The structure has two-level keys.

Level 1: raw_key. The same as hash_key or shard_key.
Level 2: column_key.
Level 3: column_value
raw_key 		is used to hash and can not support range query. let's simplify this to a string.
column_key 		is sorted and support range query. let's simplify this to integer.
column_value 	is a string. you can serialize any data into a string and store it in column value.

implement the following methods:

insert(raw_key, column_key, column_value)
query(raw_key, column_start, column_end) // return a list of entries


Example
insert("google", 1, "haha")
query("google", 0, 1)

Table
                       Column
               column_key column_value
   Row            
  row_key            0
 "google"		     1      "haha"
 
 
>> [（1, "haha")]

 * */
public class FacebookCassandra {
	private HashMap<String, NavigableMap<Integer, String>> hash;
	public FacebookCassandra(){
		// support range query
		this.hash = new HashMap<String, NavigableMap<Integer, String>>();
	}
	
	
	/*
	 * @param row_key a string
	 * @param column_key an integer
	 * @param column_value a string
	 * @return void
	 * */
	public void insert(String row_key, int column_key, String column_value){
		// insert
		if (!hash.containsKey(row_key)){
			// sorted 
			hash.put(row_key, new TreeMap<Integer, String>());
		}
		// update
		hash.get(row_key).put(column_key, column_value);
	}
	
	/*
	 * @param
	 * @param
	 * @param column_end
	 * @return a list of Columns
	 * */
	public ArrayList<Column> query(String row_key, int column_start, int column_end){
		ArrayList<Column> res = new ArrayList<Column>();
		// boolean find
		if (!hash.containsKey(row_key)){
			return res;
		}
		// loop through range 
		// NavigableMap.subMap(fromKey, boolean, toKey, boolean)
		for (Map.Entry<Integer, String> entry: hash.get(row_key).subMap(column_start, true, column_end, true).entrySet()){
			res.add(new Column(entry.getKey(), entry.getValue()));
		}
		return res;	
	}
	public static void main(String[] args){
		FacebookCassandra sol = new FacebookCassandra();
		
		sol.insert("google", 1, "haha");
		ArrayList<Column> res = sol.query("google", 0, 1);
		System.out.println(res);
	}
}
class Column{
	public int key;
	public String value;
	public Column(int key, String value){
		this.key = key;
		this.value = value;
	}
	public String toString(){
		return key+", "+value;
	}
}