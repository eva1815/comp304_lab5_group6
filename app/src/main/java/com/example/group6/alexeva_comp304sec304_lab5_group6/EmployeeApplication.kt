package com.example.group6.alexeva_comp304sec304_lab5_group6

import android.app.Application

class EmployeeApplication:Application() {
    val repository by lazy { EmployeeRepository(EmployeeDAO) }
}