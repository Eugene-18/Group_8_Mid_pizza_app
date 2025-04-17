package com.example.pizzaorderapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class SideDishActivity : AppCompatActivity() {

    private lateinit var cbFries: CheckBox
    private lateinit var cbNuggets: CheckBox
    private lateinit var cbSalad: CheckBox
    private lateinit var cbDrink: CheckBox
    private lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_dish)

        cbFries = findViewById(R.id.cb_fries)
        cbNuggets = findViewById(R.id.cb_nuggets)
        cbSalad = findViewById(R.id.cb_salad)
        cbDrink = findViewById(R.id.cb_drink)
        btnConfirm = findViewById(R.id.btn_confirm_side)

        btnConfirm.setOnClickListener {
            val selected = mutableListOf<String>()
            if (cbFries.isChecked) selected.add("薯條")
            if (cbNuggets.isChecked) selected.add("雞塊")
            if (cbSalad.isChecked) selected.add("沙拉")
            if (cbDrink.isChecked) selected.add("飲料")

            val resultIntent = Intent().apply {
                putExtra("sideDish", selected.joinToString(", "))
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}