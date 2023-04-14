package com.example.group6.alexeva_comp304sec304_lab5_group6

data class Employee (
    var employeeId:String?,
    var name: String,
    var department: String,
    var time: String,
    var salary: Int
){
    // Create Default Constructor in Kotlin Class
    constructor() : this(null,"Alex", "DEFAULT", "10", 10000)
}