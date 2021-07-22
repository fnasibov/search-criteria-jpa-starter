package com.nasibov.search.criteria.jpa.domain

data class SearchRequest(
        val filters: List<Filter>,
        val sorting: List<Sorting>,
        val page: Int,
        val pageSize: Int
)