package com.nasibov.search.criteria.jpa.domain.converter

interface Converter<ResultType : Comparable<*>> {
    fun convertList(values: List<String>): List<ResultType> {
        return values.map { convert(it) }
    }

    fun convert(value: String): ResultType
}