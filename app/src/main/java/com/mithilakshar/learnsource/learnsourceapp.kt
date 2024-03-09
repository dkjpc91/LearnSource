package com.mithilakshar.learnsource

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class learnsourceapp():Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}