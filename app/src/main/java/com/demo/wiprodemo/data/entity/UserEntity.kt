package com.demo.wiprodemo.data.entity

import com.demo.wiprodemo.domain.module.User

data class UserEntity(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) {
    data class Address(
        val city: String,
        val geo: Geo,
        val street: String,
        val suite: String,
        val zipcode: String
    ) {
        data class Geo(
            val lat: String,
            val lng: String
        )
    }

    data class Company(
        val bs: String,
        val catchPhrase: String,
        val name: String
    )
}

fun UserEntity.toUser() = User(
    email = email,
    id = id,
    name = name,
    phone = phone,
    username = username,
)