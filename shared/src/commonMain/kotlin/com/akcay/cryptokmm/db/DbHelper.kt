package com.akcay.cryptokmm.db

import app.cash.sqldelight.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

fun <T: Any> Flow<Query<T>>.mapToList(
    context: CoroutineContext = Dispatchers.Default
) : Flow<List<T>> = map {
    withContext(context) {
        it.executeAsList()
    }
}