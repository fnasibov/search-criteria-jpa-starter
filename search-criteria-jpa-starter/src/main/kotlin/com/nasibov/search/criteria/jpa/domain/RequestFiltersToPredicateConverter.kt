package com.nasibov.search.criteria.jpa.domain

import com.nasibov.search.criteria.jpa.domain.FilterOperation.*
import com.nasibov.search.criteria.jpa.domain.converter.Converter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import javax.persistence.criteria.*

@Component
class RequestFiltersToPredicateConverter(@Qualifier("converters") private val converters: ConverterList) {

    @Suppress("UNCHECKED_CAST")
    fun <EntityType> convert(filters: List<Filter>, root: Root<EntityType>, criteriaBuilder: CriteriaBuilder): Predicate? {
        val predicates: MutableList<Predicate> = ArrayList(filters.size)
        for (filter in filters) {
            val field: Path<out Comparable<*>> = root.get(filter.fieldName)
            val values: List<String> = filter.values
            require(values.isNotEmpty()) { "Values can not be empty" }
            when (filter.operation) {
                EQ -> predicates.add(criteriaBuilder.equal(field, converters.get(field.javaType.name)?.convert(values[0])))
                AL -> predicates.add(criteriaBuilder.like(field as Expression<String>, "%" + values[0] + "%"))
                BL -> predicates.add(criteriaBuilder.like(field as Expression<String>, values[0] + "%"))
                EL -> predicates.add(criteriaBuilder.like(field as Expression<String>, "%" + values[0]))
                NL -> predicates.add(criteriaBuilder.notLike(field as Expression<String>, "%" + values[0] + "%"))
                NBL -> predicates.add(criteriaBuilder.notLike(field as Expression<String>, values[0] + "%"))
                NEL -> predicates.add(criteriaBuilder.notLike(field as Expression<String>, "%" + values[0]))
                LT -> throw OperationNotSupported("Operation  \"Lower than\" not supported")
                GT -> throw OperationNotSupported("Operation  \"Greater than\" not supported")
                LE -> throw OperationNotSupported("Operation  \"Lower or equals\" not supported")
                GE -> throw OperationNotSupported("Operation  \"Greater or equals\" not supported")
                IN -> {
                    val inPredicate: CriteriaBuilder.In<Any> = criteriaBuilder.`in`(field)
                    converters.get(field.javaType.name)?.convertList(values)?.forEach { inPredicate.value(it) }
                    predicates.add(inPredicate)
                }
            }
        }
        if (predicates.isEmpty()) {
            return null
        }

        return criteriaBuilder.and(*predicates.toTypedArray())
    }


}