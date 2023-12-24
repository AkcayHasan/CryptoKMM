package com.akcay.cryptokmm.network.entities.response

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class RawUSD(
    @SerialName("PRICE") var price: Double? = null,
) : Parcelable