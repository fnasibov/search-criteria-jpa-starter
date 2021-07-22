package com.nasibov.search.criteria.jpa.domain

enum class FilterOperation {
    /**
     * Equals
     */
    EQ,
    /**
     * Any like
     */
    AL,
    /**
     * Begin like
     */
    BL,
    /**
     * End like
     */
    EL,
    /**
     * Not like
     */
    NL,
    /**
     * Not begin like
     */
    NBL,
    /**
     * Not end like
     */
    NEL,
    /**
     * Lower than
     */
    LT,
    /**
     * Greater than
     */
    GT,
    /**
     * Lower or equals
     */
    LE,
    /**
     * Greater or equals
     */
    GE,
    /**
     * IN
     */
    IN
}