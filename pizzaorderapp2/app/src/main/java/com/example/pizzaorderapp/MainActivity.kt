package com.example.pizzaorderapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvMainDish: TextView
    private lateinit var tvSideDish: TextView

    // 接收主餐選擇結果
    private val mainDishLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val result = it.data?.getStringExtra("mainDish")
        tvMainDish.text = "主餐：${result ?: "尚未選擇"}"
    }

    // 接收副餐選擇結果
    private val sideDishLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val result = it.data?.getStringExtra("sideDish")
        tvSideDish.text = "副餐：${result ?: "尚未選擇"}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化畫面元件
        tvMainDish = findViewById(R.id.tv_main_dish)
        tvSideDish = findViewById(R.id.tv_side_dish)
        val btnMain = findViewById<Button>(R.id.btn_choose_main_dish)
        val btnSide = findViewById<Button>(R.id.btn_choose_side_dish)
        val btnStoreInfo = findViewById<Button>(R.id.btn_store_info)

        // 主餐按鈕
        btnMain.setOnClickListener {
            val intent = Intent(this, MainDishActivity::class.java)
            mainDishLauncher.launch(intent)
        }

        // 副餐按鈕
        btnSide.setOnClickListener {
            val intent = Intent(this, SideDishActivity::class.java)
            sideDishLauncher.launch(intent)
        }

        // 店家資訊按鈕
        btnStoreInfo.setOnClickListener {
            val intent = Intent(this, StoreInfoActivity::class.java)
            startActivity(intent)
        }
    }
}