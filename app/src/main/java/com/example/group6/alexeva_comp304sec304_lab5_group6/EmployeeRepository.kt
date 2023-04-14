package com.example.group6.alexeva_comp304sec304_lab5_group6

import androidx.lifecycle.MutableLiveData

class EmployeeRepository(private val employeeDao:EmployeeDAO) {
    val allEmployees: MutableLiveData<ArrayList<Employee>> = employeeDao.liveEmployeeList

    fun insert(employee: Employee) {
        employeeDao.insert(employee)
    }

    fun update(employee: Employee) {
        employeeDao.update(employee)
    }

    fun delete(employee: Employee) {
        employeeDao.delete(employee)
    }
}