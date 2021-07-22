package com.nasibov.search.criteria.jpa.service

import com.nasibov.search.criteria.jpa.domain.SearchRepository
import com.nasibov.search.criteria.jpa.domain.SearchRequest
import com.nasibov.search.criteria.jpa.domain.SearchResponse
import org.springframework.stereotype.Service

@Service
class SearchService(private val searchRepository: SearchRepository) {

    fun <ResultModelType> search(
        request: SearchRequest,
        entityClass: Class<ResultModelType>
    ): SearchResponse<ResultModelType> {
        val search: List<ResultModelType> = searchRepository.search(entityClass, request)
        val count: Long = searchRepository.count(entityClass, request)

        return SearchResponse(search, request.page, request.pageSize, count)
    }


}