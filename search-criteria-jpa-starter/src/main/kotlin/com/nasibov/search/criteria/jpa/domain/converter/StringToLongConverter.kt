package com.nasibov.search.criteria.jpa.domain.converter

import org.springframework.stereotype.Component

@Component
class StringToLongConverter : Converter<Long> {

    override fun convert(value: String): Long {
        return value.toLong()
    }
}