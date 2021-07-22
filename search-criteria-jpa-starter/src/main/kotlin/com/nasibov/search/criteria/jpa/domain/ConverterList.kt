package com.nasibov.search.criteria.jpa.domain

import com.nasibov.search.criteria.jpa.domain.converter.Converter

data class ConverterList(val elements: Map<String, Converter<*>>) {
    fun get(name: String): Converter<*>? {
        return elements[name]
    }
}
