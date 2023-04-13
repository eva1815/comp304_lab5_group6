package com.example.group6.alexeva_comp304sec304_lab5_group6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"Welcome to Home Screen", Toast.LENGTH_SHORT).show()
        //Employee (name, department, time, salary)
    }
}