package com.nasibov.search.criteria.jpa.configuration

import com.nasibov.search.criteria.jpa.domain.ConverterList
import com.nasibov.search.criteria.jpa.domain.converter.Converter
import org.springframework.beans.factory.getBeansOfType
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.lang.reflect.ParameterizedType
import javax.persistence.EntityManager
import javax.persistence.criteria.Predicate

@Configuration
@ConditionalOnClass(Predicate::class, EntityManager::class)
@ComponentScan("com.nasibov.search.criteria.jpa")
class SearchCriteriaJpaAutoConfiguration(private val context: ApplicationContext) {

    @Bean
    @ConditionalOnMissingBean
    fun converters(): ConverterList {
        val converters: MutableMap<String, Converter<*>> = mutableMapOf()
        val beansOfType = context.getBeansOfType<Converter<*>>()
        beansOfType.forEach { (_, converter) ->
            val type: String = (converter.javaClass.genericInterfaces.first() as ParameterizedType)
                .actualTypeArguments.first().typeName
            converters[type] = converter
        }

        return ConverterList(converters)
    }
}