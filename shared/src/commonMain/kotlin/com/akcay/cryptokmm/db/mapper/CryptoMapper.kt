package com.akcay.cryptokmm.db.mapper

import com.akcay.cryptokmm.network.entities.response.CoinInfo
import com.akcay.cryptokmm.network.entities.response.DISPLAY
import com.akcay.cryptokmm.network.entities.response.Data
import com.akcay.cryptokmm.network.entities.response.RAW
import com.akcay.cryptokmm.network.entities.response.USD
import database.CryptoEntity

fun CryptoEntity.toCrypto() = Data(
    CoinInfo(
        id = id,
        name = name,
        fullName = fullName,
        internal = internal_,
        imageUrl = imageUrl,
        url = url
    ),
    RAW(),
    DISPLAY(
        USD(
            highHour, price, changePercentageHour
        )
    )
)

fun Data.toCryptoEntity() = CryptoEntity(
    id = this.coinInfo?.id ?: 0,
    name = this.coinInfo?.name,
    fullName = this.coinInfo?.fullName,
    internal_ = this.coinInfo?.internal,
    imageUrl = this.coinInfo?.imageUrl,
    url = this.coinInfo?.url,
    price = this.display?.usd?.price,
    highHour = this.display?.usd?.highHour,
    changePercentageHour = this.display?.usd?.changePercentageHour
)