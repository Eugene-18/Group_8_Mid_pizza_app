package com.example.pizzaorderapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainDishActivity : AppCompatActivity() {

    private lateinit var rgMainDish: RadioGroup
    private lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dish)

        rgMainDish = findViewById(R.id.rg_main_dish)
        btnConfirm = findViewById(R.id.btn_confirm_main)

        btnConfirm.setOnClickListener {
            val selectedId = rgMainDish.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "請選擇一項主餐", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadioButton = findViewById<RadioButton>(selectedId)
            val selectedDish = selectedRadioButton.text.toString()

            val resultIntent = Intent().apply {
                putExtra("mainDish", selectedDish)
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
