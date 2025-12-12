package com.phani.tddsample.domain.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val website: String,
    val company: Company,
    val address: Address
)

data class Company(
    val name: String,
    val catchPhrase: String
)

data class Address(
    val street: String,
    val city: String,
    val zipcode: String
)
