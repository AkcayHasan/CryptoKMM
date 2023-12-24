package com.akcay.cryptokmm.network.entities.response

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class USD(
    @SerialName("HIGHHOUR") var highHour: String? = null,
    @SerialName("PRICE") var price: String? = null,
    @SerialName("CHANGEPCTHOUR") var changePercentageHour: String? = null,
) : Parcelable