package com.nasibov.search.criteria.jpa.domain.converter

import org.springframework.stereotype.Component
import java.util.*

@Component
class StringToUUIDConverter : Converter<UUID> {

    override fun convert(value: String): UUID {
        return UUID.fromString(value)
    }

}