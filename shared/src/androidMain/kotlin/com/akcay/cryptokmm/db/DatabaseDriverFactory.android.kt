package com.akcay.cryptokmm.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.akcay.cryptokmm.database.CryptoDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(schema = CryptoDatabase.Schema, context = context, name = "crypto.db")
    }
}