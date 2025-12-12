package com.phani.tddsample.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.phani.tddsample.domain.model.Address
import com.phani.tddsample.domain.model.Company
import com.phani.tddsample.domain.model.User

data class UserDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val website: String,
    @SerializedName("company") val company: CompanyDto,
    @SerializedName("address") val address: AddressDto
)

data class CompanyDto(
    @SerializedName("name") val name: String,
    @SerializedName("catchPhrase") val catchPhrase: String
)

data class AddressDto(
    @SerializedName("street") val street: String,
    @SerializedName("city") val city: String,
    @SerializedName("zipcode") val zipcode: String
)

// Mapper functions
fun UserDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email,
        phone = phone,
        website = website,
        company = company.toDomain(),
        address = address.toDomain()
    )
}

fun CompanyDto.toDomain(): Company {
    return Company(
        name = name,
        catchPhrase = catchPhrase
    )
}

fun AddressDto.toDomain(): Address {
    return Address(
        street = street,
        city = city,
        zipcode = zipcode
    )
}
