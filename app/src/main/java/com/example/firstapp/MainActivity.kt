package com.example.firstapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate: TextView? =null
    private var tvAgeinmin : TextView? =null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btn : Button = findViewById(R.id.btn)
        selectedDate = findViewById(R.id.selectedDate)
        tvAgeinmin = findViewById(R.id.tvAgeinmin)
        btn.setOnClickListener{
            clickDatepicker()
        }
    }

    private fun clickDatepicker(){


        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day= myCalender.get(Calendar.DAY_OF_MONTH)
        val dp =  DatePickerDialog(this,
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                Toast.makeText( this,
                    "year is $selectedYear, " +
                            "month was ${selectedMonth+1}"
                            + ", day was $selectedDayOfMonth",
                    Toast.LENGTH_LONG).show()

                val tvselectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                selectedDate?.text = tvselectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(tvselectedDate)

                theDate?.let{
                    val selectedDateinMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time/ 60000

                        val diffrence = currentDateInMinutes - selectedDateinMinutes

                        tvAgeinmin?.text = diffrence.toString()
                    }

                }

            },
            year,
            month,
            day
        )

        dp.datePicker.maxDate = System.currentTimeMillis() - 86400000

        dp.show()

    }
}