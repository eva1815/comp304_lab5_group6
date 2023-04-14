package com.example.group6.alexeva_comp304sec304_lab5_group6

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

//Singleton
object EmployeeDAO {
    private val database = Firebase.database
    private val myRef = database.getReference("Employee")

    private val TAG = "EmployeeDAO"
    var liveEmployeeList = MutableLiveData<ArrayList<Employee>>()

    init{
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var tempList = arrayListOf<Employee>()
                if(snapshot.exists()){
                    for(EmployeeSnap in snapshot.children){
                        val s = EmployeeSnap.getValue<Employee>()
                        Log.d(TAG, "EmployeeSnap Value is: $s")
                        tempList.add(s!!)
                    }
                }
                liveEmployeeList.value = tempList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }


    fun insert(employee: Employee){
        val key = myRef.push().key
        employee.employeeId = key
        myRef.child(key!!).setValue(employee)
    }

    fun update(employee: Employee){
        myRef.child(employee.employeeId!!).setValue(employee)
    }

    fun delete(employee: Employee){
        myRef.child(employee.employeeId!!).removeValue()
    }

}