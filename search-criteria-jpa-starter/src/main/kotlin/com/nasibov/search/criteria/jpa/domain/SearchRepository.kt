package com.nasibov.search.criteria.jpa.domain

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class SearchRepository(private val requestFiltersToPredicateConverter: RequestFiltersToPredicateConverter) {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    fun <EntityType> search(entityClass: Class<EntityType>, request: SearchRequest): List<EntityType> {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(entityClass)
        val root = criteriaQuery.from(entityClass)
        val filter = requestFiltersToPredicateConverter.convert(request.filters, root, criteriaBuilder)
        criteriaQuery.where(filter)
        val order = RequestSortingToOrderConverter.convert(request.sorting, root, criteriaBuilder)
        criteriaQuery.orderBy(order)
        val query = entityManager.createQuery(criteriaQuery)
        if (request.page > 1) {
            query.firstResult = (request.page - 1) * request.pageSize
        }
        query.maxResults = request.pageSize

        return query.resultList
    }

    fun <EntityType> count(entityClass: Class<EntityType>, request: SearchRequest): Long {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Long::class.java)
        val root = criteriaQuery.from(entityClass)
        val filter = requestFiltersToPredicateConverter.convert(request.filters, root, criteriaBuilder)
        criteriaQuery.select(criteriaBuilder.count(root))
        criteriaQuery.where(filter)
        val query = entityManager.createQuery(criteriaQuery)

        return query.singleResult
    }

}