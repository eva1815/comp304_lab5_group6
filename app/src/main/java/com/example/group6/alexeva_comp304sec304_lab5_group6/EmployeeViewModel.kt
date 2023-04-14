package com.example.group6.alexeva_comp304sec304_lab5_group6

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeViewModel(private val repository: EmployeeRepository):ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    var employeeList: LiveData<ArrayList<Employee>> = repository.allEmployees

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
//    fun insert(employee: Employee) = viewModelScope.launch {
//        repository.insert(employee)
//    }
    fun insert(employee: Employee) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.insert(employee)
        }
    }

    fun update(input: String, newInputName: String, newInputDepartment:String,newInputTime:String,newInputSalary:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                for (employee in employeeList.value!!) {
                    if (employee.name == input) {
                        employee.name = newInputName
                        employee.department = newInputDepartment
                        employee.time = newInputTime
                        employee.salary = newInputSalary.toInt()
                        repository.update(employee)
                        break
                    }
                }
            }
        }
    }
    fun delete(input: String, inputDepartment: String, inputTime: String, inputSalary: String){
        viewModelScope.launch {
            for(p in employeeList.value!!){
                if(p.name == input){
                    repository.delete(p)
                }
            }
        }
    }

}