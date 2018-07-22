
public class How_Primary_Secondary_Index {
/*
GOLDEN:http://massivetechinterview.blogspot.tw/2015/08/dynamodb-internal.html

http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/SecondaryIndexes.html

Question: to allow efficient access to data with attributes other than the primary key

A table can have multiple secondary indexes, which gives your applications access to many different query patterns.


When you create a secondary index, you define the alternate key for the index, along with any other attributes that you want to be projected in the index. DynamoDB copies these attributes into the index, along with the primary key attributes from the table. You can then query or scan the index just as you would query or scan a table.

Every secondary index is automatically maintained by DynamoDB. When you add, modify, or delete items in the table, any indexes on the table are also updated to reflect these changes.


http://stackoverflow.com/questions/20824686/what-is-difference-between-primary-index-and-secondary-index-exactly

Primary index:

A primary index is an index on a set of fields that includes the unique primary key for the field and is guaranteed not to contain duplicates. 
Also Called a Clustered index. eg. Employee ID can be Example of it.

Secondary index:

A Secondary index is an index that is not a primary index and may have duplicates. 
eg. Employee name can be example of it. Because Employee name can have similar values.
The primary index contains the key fields of the table. 
The primary index is automatically created in the database when the table is activated. 
$$$$$$$$$$$ If a large table is frequently accessed such that it is not possible to apply primary index sorting, 
you should create secondary indexes for the table.
The indexes on a table have a three-character index ID. 
'0' is reserved for the primary index. 
Customers can create their own indexes on SAP tables; 
their IDs must begin with Y or Z.

Local Secondary Index:
Hash and range keys/Query scope:   Query is performed on single partition only so that it share the hash key as table has but has its own range key.
Consistency:                       Provides option to select either eventual or strong consistency.
Size:                              Have size limits. The size of all items together for a single index should be less than 10 GB per hash key.
Provisioned throughput:            R/W capacity limits is the same as table R/W capacity.


Global Secondary Index:
Hash and range keys/Query scope:   Query is performed on all table partitions so that it require its own hash key and range key.
Consistency:                       Supports only eventual consistency.
Size:                              R/W capacity limits is separate from table R/W capacity.
Provisioned throughput:            Does not have size limits.


 */
}
