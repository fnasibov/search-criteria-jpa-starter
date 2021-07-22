# Library that introduce functionality for searching entities in projects that uses spring data jpa.
Use case:
+ Create instance of the [SearchRequest](search-criteria-jpa-starter/src/main/kotlin/com/nasibov/search/criteria/jpa/domain/SearchRequest.kt)
+ Pass [SearchRequest](search-criteria-jpa-starter/src/main/kotlin/com/nasibov/search/criteria/jpa/domain/SearchRequest.kt) to the [SearchService](search-criteria-jpa-starter/src/main/kotlin/com/nasibov/search/criteria/jpa/service/SearchService.kt)
+ Enjoy to [SearchResponse](search-criteria-jpa-starter/src/main/kotlin/com/nasibov/search/criteria/jpa/domain/SearchResponse.kt)

[SearchRequest](search-criteria-jpa-starter/src/main/kotlin/com/nasibov/search/criteria/jpa/domain/SearchRequest.kt) contains:
+ filters - list of predicates that in native sql we can pass in where clause
+ sorting - sorting rules
+ page - page number that we need
+ pageSize - amount of the elements on page that we want

[SearchResponse](search-criteria-jpa-starter/src/main/kotlin/com/nasibov/search/criteria/jpa/domain/SearchResponse.kt) contains:
+ result - list of entities from db 
+ page - current page of results
+ pageSize - element amount on page
+ count - count of elements in db
