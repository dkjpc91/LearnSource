package com.mithilakshar.learnsource.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mithilakshar.learnsource.homedata
import com.mithilakshar.learnsource.utils.myResponses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepo(val context: Context) {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseRef = firebaseDatabase.getReference("AppData").child("Home")
    private val homeLD = MutableLiveData<myResponses<ArrayList<homedata>>>()

    val homeLiveData get() = homeLD

    suspend fun getHomeData() {
        homeLiveData.postValue(myResponses.Loading())
        val TAG = "MainActivity"
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshots: DataSnapshot) {
                Log.i(TAG, "onDataChange: Value Changed")
                if (!snapshots.exists()) {
                    homeLiveData.postValue(myResponses.Error("Data snapshot not exists"))
                    return
                }
                val tempList = ArrayList<homedata>()
                for (snapshot in snapshots.children) {
                    val homeModel = snapshot.getValue(homedata::class.java)
                    homeModel?.let {
                        tempList.add(homeModel)
                    }
                }

                    homeLiveData.postValue(myResponses.Success(tempList))




            }

            override fun onCancelled(error: DatabaseError) {
                homeLiveData.postValue(myResponses.Error("Something Went Wrong with Database."))
            }

        })

    }

}