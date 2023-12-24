package com.akcay.cryptokmm.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.akcay.cryptokmm.database.CryptoDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(schema = CryptoDatabase.Schema, name = "crypto.db")
    }

}