package com.akcay.cryptokmm.network.entities.response

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CoinNewsModelItem(
    @SerialName("key") var key  : String? = null,
    @SerialName("name" ) var name : String? = null,
    @SerialName("img"  ) var img  : String? = null,
    @SerialName("lang" ) var lang : String? = null
): Parcelable