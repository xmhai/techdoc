## Concept
- When the node startup, it will either join a cluster or create its own cluster.
- Documents are JSON objects. They are stored  in **_source** field along with the meta data which used internally by ES.
- Use Kibana "Dev Tools" to send REST API, the url consists of api and command in the format of /*api*/*command*
- Sharding to divide index into mulitple pieces called "shard".
  - An index defaults to having *one* shard.
  - There are split and shrink api to manage shards.
  - If there are millions of documents, can choose *five* shards.

## Commands
/_cluster/health  
/_cat/indices?v
/_cat/nodes?v
/_cat/shards?v

## Installation
- Standalone  
#add "vm.max_map_count=262144" to sysctl.conf  
sudo nano /etc/sysctl.conf  
https://tecadmin.net/how-to-install-elasticsearch-on-centosl-8/  
**NOTE:** use "bind: 0.0.0.0" and add "discovery.type: single-node"

- Docker  
https://www.elastic.co/guide/en/elastic-stack-get-started/current/get-started-docker.html

http://localhost:9200  

## Mapping to SQL  
https://www.elastic.co/guide/en/elasticsearch/reference/current/_mapping_concepts_across_sql_and_elasticsearch.html  
Index -> Table  
Document -> Row  
Field -> Column  
Cluster -> Database  
Shard  
Replica  

## SQL DML Statement and DSL Mapping
- SELECT -> /_search  
LIMIT number -> "size": number (default is 10)  
OFFSET number -> "from": number (default is 0)  
columns -> "_source" : ["field", "field"...]  
FROM -> /index  
WHERE column="value" ->  
    **term query** for keyword fields -> "query" : {"match" : {"field" : "value"}}  
    **full text query** for text fields -> "query" : {"match" : {"field" : "value"}}  
    **filter query** for numeric/date fields -> "query" : {"filter" : [ range_query, range_query... ]}  
    match query count the relevance, while filter query only return yes/no  
AND -> "query" : { "bool" : { "must" : [ "match" : { "field" : "value" } ] }}  
OR -> "query" : { "bool" : { "should" : [ "match" : { "field" : "value" } ] }}  
BETWEEN -> "range" : { "field" : { "lte" : value,  "gte" : value } }  
ORDER BY columns ->
    "sort":["field", "field"...] (Cannot sort on TextField)
    "sort" : [ { "field" : "desc|asc"}, { "field" : "desc|asc"}... ]  
- UPDATE -> _update OR replace using same ID
- DELETE -> DELETE verb  
- AGGREGATION  
  - SUM|AVG|MIN|MAX -> "aggs" : {"agg_name" :  {"sum|avg|min|max|stats" : { "field" : "field_name" }}}  
  - DISTINCT COUNT -> "aggs" : {"agg_name" :  {"cardinality" : { "field" : "field_name" }}}  
  - COUNT OF RECORDS USED IN AGGREGATION-> "aggs" : {"agg_name" :  {"value_count" : { "field" : "field_name" }}}  
  - GROUP BY  -> Bucket aggregation  "aggs" : {"agg_name" :  {"terms":{"field" : "field_name"}}, {"filter":{"field" : "field_name"}}}  

Nested aggregation can get stats on each bucket (group).   
Range can get bucket (group) based on range  
Histogram  
Nested aggregation combine with nested query  

Pagination (Calculated at Application level)  
- total_pages = ceil(total_hits/page_size)
- size = page_size
- from = page_size * (page_number - 1)

**Index**
- Create index  
PUT /*index*

- Query Indexes  
GET /_cat/indices  
GET /_cat/indices?v&s=index (return header)  
GET /_cat/indices/*index*

- Get Mapping  
GET /*index*/_mapping

- Delete Index  
DELETE /*index*

**Document**
- Insert Document  
POST /*index*/_doc  
{"firstName":"John", "lastName":"Doe"}

- Select Document  
GET /*index*/_doc/*ID*  
GET /*index*/_search  
{ "match": { "lastName": "lin", "lastName": "liu" } }  
{ "sort": [ { "account_number": "asc" } ] }  
Cannot sort on TextField

- Update Document  
POST /*index*/_doc/

- Delete Document

https://www.elastic.co/guide/en/elasticsearch/reference/current/fielddata.html

