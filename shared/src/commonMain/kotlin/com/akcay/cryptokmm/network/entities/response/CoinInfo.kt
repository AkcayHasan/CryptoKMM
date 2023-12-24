package com.akcay.cryptokmm.network.entities.response

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CoinInfo(
    @SerialName("Id") var id: Int = 0,
    @SerialName("Name") var name: String? = null,
    @SerialName("FullName") var fullName: String? = null,
    @SerialName("Internal") var internal: String? = null,
    @SerialName("ImageUrl") var imageUrl: String? = null,
    @SerialName("Url") var url: String? = null,
) : Parcelable