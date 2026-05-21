package com.demo.wiprodemo.domain.repository.user

import com.demo.wiprodemo.data.entity.UserEntity

val dummyUserEntity = UserEntity(
    id = 1,
    name = "Rahul",
    username = "rahul123",
    email = "rahul@test.com",
    phone = "9999999999",
    website = "rahul.dev",

    address = UserEntity.Address(
        street = "MG Road",
        suite = "A-101",
        city = "Hyderabad",
        zipcode = "400001",

        geo = UserEntity.Address.Geo(
            lat = "19.0760",
            lng = "72.8777"
        )
    ),

    company = UserEntity.Company(
        name = "Tech Solutions",
        catchPhrase = "Innovation for Future",
        bs = "digital transformation"
    )
)