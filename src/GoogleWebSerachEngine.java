
public class GoogleWebSerachEngine {

}
/*

1. Crawl
BFS

2. Index
Inverted Index

3. Query
Sort
TopK heap
• Frequency of the search words
Word Frequency
• Distance of the search words
Shortest Word Distance I,II(cache),III(equal words),IV(many words distance=>total distance between adjacent words like dist(0,1)+dist(1,2)+dist(2,3))
Minimum Window Substring

• Did you mean: google from search word:oogle
Anagram
Palindrome

1. Crawl
2. Index
3. Query


1. Crawl
	• What kind of coumnets(only html-like documents, remove .doc, .ppt,.pdf,.img..etc)
	• how often (stale and refresh 1 week )
	• what sites (application sites remove, robot.txt)
	• how to
	• seed list (BFS to update seed lists)
	• Parser (take body of the codument, remove non-text portion of the documnets, Meta data or Not )
	• Tokenizer (space delimiter)
	• Stemmer (to produce the word stems)
	• DataMapper (add the words(stems) and its location into the index)

2. Index
	• Inverted Index
		word / document lists
	• Forward index
		documnet / word lists

3. Query
	•  apple => apple mac ? or apple fruit ?

Query Phase 1
	• Tokenizing
	• Stemming
	•  SORTING the results by relevance (ranking algorithm)
	• PageRank (a score to each page depends on the number and importance of pages that links to it and uses that to rank the page)
	• Feedback(the more time users a user clicks on a search result, the higher it is ranked)
		• Frequency of the search words 
			• the page has more if the search words is assumed to be relevant)
		• Location of the search words 
			• the search word is near to the top of the document is more relevant)
		• Distance of the search words
			• the closer(search word between each other) the words are to each other on a page, the higher that page will be ranked
			• e.g., "brown fox"
		  		document 1: the quick brown fox jumped over the lazy dog
		  		document 2:  the brown dog chased after the fox
		  		document 1 is more relevant
		• aggregator that calls a number of tanking methods in sequence and
		• merges the returned rank lists together
			• [wordID, rank score] = [1123, 0.452]
Query Phase 2
	• Relevant results
	• SQL select count ORDER BY DESC
        • return pageID, count of serached words
	•  Normalize, all deivide by highest counts
	• SQL select pos ORDER BY ASC
	• SQL select dis ORDER BY ASC
	
	  select loc0.page_id, abs(loc0.pos - loc1.pos)+abs(loc1.pos - loc2.pos) as dist
	  where loc0.pageId == loc1.pageID ADN
	   loc1.pageId == loc2.pageID AND
	   loc0.wordId = AND
	   loc1.wordID = AND
	   loc2.wordID = 
	GROUP BY loc0.page_id ORDER BY dist ASC
		• 1 search item, no distance
		• 2 search item,  distance
		• 3 search item,  distance, word 1 and 2 and then word 2 and 3
		• 2 search item,  distance, word 1 and 2 and then word 2 and 3 and then word 3 and 4





 */