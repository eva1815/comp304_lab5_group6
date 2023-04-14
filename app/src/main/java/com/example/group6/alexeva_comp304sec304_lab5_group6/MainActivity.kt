package com.example.group6.alexeva_comp304sec304_lab5_group6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var textInput: EditText
    private lateinit var textDepartment: EditText
    private lateinit var textTime: EditText
    private lateinit var textSalary: EditText
    private lateinit var textInputUpdateName: EditText
    private lateinit var textInputUpdateDepartment: EditText
    private lateinit var textInputUpdateTime: EditText
    private lateinit var textInputUpdateSalary: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var buttonDelete: Button
    private lateinit var buttonUpdate: Button
    private lateinit var btnSignOut: Button

    private val employeeViewModel: EmployeeViewModel by viewModels {
        ViewModelFactory((application as EmployeeApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,getString(R.string.msg_welcome_home_screen), Toast.LENGTH_SHORT).show()
        //Employee (name, department, time, salary)

      textView = findViewById(R.id.tv_list)
      textInput = findViewById(R.id.et_input)
      textDepartment = findViewById(R.id.et_department)
      textTime = findViewById(R.id.et_time)
      textSalary = findViewById(R.id.et_salary)
      textInputUpdateName = findViewById(R.id.et_input_update)
        textInputUpdateDepartment = findViewById(R.id.et_input_department)
        textInputUpdateTime = findViewById(R.id.et_input_time)
        textInputUpdateSalary = findViewById(R.id.et_input_salary)
      buttonUpdate = findViewById(R.id.btn_update)
      buttonSubmit = findViewById(R.id.btn_insert)
      buttonDelete = findViewById(R.id.btn_delete)
        btnSignOut = findViewById(R.id.btn_sign_out)

      employeeViewModel.employeeList.observe(this) { employees ->
          Log.i("Main","-------------------------------")
          textView.text=""
          for(employee in employees){
              textView.append("${employee.name} - ${employee.department} - ${employee.time} -${employee.salary}\n")
              Log.i("Main", employee.name)
          }
      }

      buttonSubmit.setOnClickListener {
          val input = textInput.text.toString().trim()
          val inputDepartment = textDepartment.text.toString().trim()
          val inputTime = textTime.text.toString().trim()
          val inputSalary = textSalary.text.toString().trim()
          Toast.makeText(this, getString(R.string.msg_insert_successfully), Toast.LENGTH_SHORT).show()
          employeeViewModel.insert(Employee(null,input,inputDepartment,inputTime,inputSalary.toInt()))
      }
      buttonDelete.setOnClickListener {
          val input = textInput.text.toString().trim()
          val inputDepartment = textDepartment.text.toString().trim()
          val inputTime = textTime.text.toString().trim()
          val inputSalary = textSalary.text.toString().trim()
          Toast.makeText(this, getString(R.string.msg_delete_successfully), Toast.LENGTH_SHORT).show()
          employeeViewModel.delete(input,inputDepartment,inputTime,inputSalary)
      }
      buttonUpdate.setOnClickListener {
          val input = textInput.text.toString().trim()
          val newInputName = textInputUpdateName.text.toString().trim()
          val newInputDepartment = textInputUpdateDepartment.text.toString().trim()
          val newInputTime = textInputUpdateTime.text.toString().trim()
          val newInputSalary = textInputUpdateSalary.text.toString().trim()

          if (input.isNotEmpty() && newInputName.isNotEmpty() && newInputDepartment.isNotEmpty() && newInputTime.isNotEmpty() && newInputSalary.isNotEmpty()) {
              println("$input $newInputName $newInputDepartment $newInputTime $newInputSalary")
              Toast.makeText(this, getString(R.string.msg_update_successfully), Toast.LENGTH_SHORT).show()
              employeeViewModel.update(input, newInputName, newInputDepartment, newInputTime, newInputSalary)
          } else {
              Toast.makeText(this, getString(R.string.msg_fill_updated_info), Toast.LENGTH_SHORT).show()
          }
      }
        btnSignOut.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}