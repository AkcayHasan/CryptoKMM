package com.akcay.cryptokmm.network.entities.response

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CoinListModel(
    @SerialName("")
    val coinImageUrl: String,

): Parcelable
