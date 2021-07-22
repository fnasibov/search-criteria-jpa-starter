package com.nasibov.search.criteria.jpa.domain

data class Sorting(
        var fieldName: String,
        var order: SortOrder
)