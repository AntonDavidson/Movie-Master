package com.example.moviemaster.domain

interface DomainMapper<I,O> {
    fun mapToDataBase(input:I):O
}
