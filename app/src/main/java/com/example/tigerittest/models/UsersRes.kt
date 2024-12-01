package com.example.tigerittest.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UsersRes(
    @SerializedName("limit") val limit: Int = 0,
    @SerializedName("skip") val skip: Int = 0,
    @SerializedName("total") val total: Int = 0,
    @SerializedName("users") val users: List<User> = listOf()
)


@Parcelize
@Entity(tableName = "users", primaryKeys = ["id"])
data class User(
    @field:SerializedName("age") var age: Int = 0,
    @field:SerializedName("birthDate") var birthDate: String = "",
    @field:SerializedName("bloodGroup") var bloodGroup: String = "",
    @field:SerializedName("ein") var ein: String = "",
    @field:SerializedName("email") var email: String = "",
    @field:SerializedName("eyeColor") var eyeColor: String = "",
    @field:SerializedName("firstName") var firstName: String = "",
    @field:SerializedName("gender") var gender: String = "",
    @field:SerializedName("height") var height: Double = 0.0,
    @field:SerializedName("id") var id: Int = 0,
    @field:SerializedName("image") var image: String = "",
    @field:SerializedName("ip") var ip: String = "",
    @field:SerializedName("lastName") var lastName: String = "",
    @field:SerializedName("macAddress") var macAddress: String = "",
    @field:SerializedName("maidenName") var maidenName: String = "",
    @field:SerializedName("password") var password: String = "",
    @field:SerializedName("phone") var phone: String = "",
    @field:SerializedName("role") var role: String = "",
    @field:SerializedName("ssn") var ssn: String = "",
    @field:SerializedName("university") var university: String = "",
    @field:SerializedName("userAgent") var userAgent: String = "",
    @field:SerializedName("username") var username: String = "",
    @field:SerializedName("weight") var weight: Double = 0.0,
    @Ignore @field:SerializedName("hair") var hair: Hair = Hair(),
    @Ignore @field:SerializedName("address") var address: Address = Address(),
    @Ignore @field:SerializedName("bank") var bank: Bank = Bank(),
    @Ignore @field:SerializedName("company") var company: Company = Company(),
    @Ignore @field:SerializedName("crypto") var crypto: Crypto = Crypto(),
) : Parcelable

@Parcelize
data class Hair(
    @SerializedName("color") val color: String = "", @SerializedName("type") val type: String = ""
) : Parcelable

@Parcelize
data class Address(
    @SerializedName("address") val address: String = "",
    @SerializedName("city") val city: String = "",
    @SerializedName("coordinates") val coordinates: Coordinates = Coordinates(),
    @SerializedName("country") val country: String = "",
    @SerializedName("postalCode") val postalCode: String = "",
    @SerializedName("state") val state: String = "",
    @SerializedName("stateCode") val stateCode: String = ""
) : Parcelable

@Parcelize
data class Bank(
    @SerializedName("cardExpire") val cardExpire: String = "",
    @SerializedName("cardNumber") val cardNumber: String = "",
    @SerializedName("cardType") val cardType: String = "",
    @SerializedName("currency") val currency: String = "",
    @SerializedName("iban") val iban: String = ""
) : Parcelable


@Parcelize
data class Company(
    @SerializedName("address") val address: Address = Address(),
    @SerializedName("department") val department: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("title") val title: String = ""
) : Parcelable

@Parcelize
data class Crypto(
    @SerializedName("coin") val coin: String = "",
    @SerializedName("network") val network: String = "",
    @SerializedName("wallet") val wallet: String = ""
) : Parcelable

@Parcelize
data class Coordinates(
    @SerializedName("lat") val lat: Double = 0.0, @SerializedName("lng") val lng: Double = 0.0
) : Parcelable
