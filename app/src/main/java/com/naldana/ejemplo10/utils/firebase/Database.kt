package com.naldana.ejemplo10.utils.firebase

import android.support.annotation.NonNull
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.naldana.ejemplo10.CurrencyAdder
import com.naldana.ejemplo10.R
import com.naldana.ejemplo10.pojo.Coin

class Database {

    private val TAG = "Firebase"

    private val database = FirebaseDatabase.getInstance().reference.apply {
        addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(Coin::class.java)
                    Log.d(TAG, "Value is: ${value?.name}")
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })
    }

    fun addCurrency(dataCoin: Coin) {
        database.child(dataCoin.name).setValue(dataCoin)
            .addOnSuccessListener {
                fun CurrencyAdder.toast(message: CharSequence) =
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            }
    }


}