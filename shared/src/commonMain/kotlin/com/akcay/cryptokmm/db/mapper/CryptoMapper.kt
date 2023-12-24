package com.akcay.cryptokmm.db.mapper

import com.akcay.cryptokmm.network.entities.response.CoinInfo
import database.CryptoEntity

fun CryptoEntity.toCrypto() = CoinInfo(
    id = id,
    name = name,
    fullName = fullName,
    internal = internal_,
    imageUrl = imageUrl,
    url = url
)

fun CoinInfo.toCryptoEntity() = CryptoEntity(
    id = id,
    name = name,
    fullName,
    internal,
    imageUrl,
    url
)