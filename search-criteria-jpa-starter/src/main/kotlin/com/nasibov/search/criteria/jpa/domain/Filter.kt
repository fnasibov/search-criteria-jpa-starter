package com.nasibov.search.criteria.jpa.domain

data class Filter(
    var fieldName: String,
    var operation: FilterOperation,
    var values: List<String>
    )
