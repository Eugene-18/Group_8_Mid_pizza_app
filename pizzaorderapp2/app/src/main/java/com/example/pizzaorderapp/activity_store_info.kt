package com.example.pizzaorderapp
import android.app.Activity


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class StoreInfoActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var storeList: ArrayList<Store>
    private lateinit var adapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_info)

        listView = findViewById(R.id.lv_stores)
        val btnAdd = findViewById<Button>(R.id.btn_add_store)

        storeList = arrayListOf(
            Store("披薩小屋", "0223456789"),
            Store("義式風味", "0212345678")
        )

        adapter = StoreAdapter(this, storeList)
        listView.adapter = adapter

        // 點擊：打電話
        listView.setOnItemClickListener { _, _, position, _ ->
            val phone = storeList[position].phone
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            startActivity(intent)
        }

        // 長按：編輯店家資訊
        listView.setOnItemLongClickListener { _, _, position, _ ->
            showEditStoreDialog(position)
            true
        }

        // 新增店家
        btnAdd.setOnClickListener {
            showAddStoreDialog()
        }
    }

    private fun showAddStoreDialog() {
        val view = LayoutInflater.from(this).inflate(R.layout.activity_dialog_add_store, null)
        val edtName = view.findViewById<EditText>(R.id.edt_store_name)
        val edtPhone = view.findViewById<EditText>(R.id.edt_store_phone)

        AlertDialog.Builder(this)
            .setTitle("新增店家")
            .setView(view)
            .setPositiveButton("新增") { _, _ ->
                val name = edtName.text.toString()
                val phone = edtPhone.text.toString()
                if (name.isNotBlank() && phone.isNotBlank()) {
                    storeList.add(Store(name, phone))
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun showEditStoreDialog(position: Int) {
        val view = LayoutInflater.from(this).inflate(R.layout.activity_dialog_add_store, null)
        val edtName = view.findViewById<EditText>(R.id.edt_store_name)
        val edtPhone = view.findViewById<EditText>(R.id.edt_store_phone)

        val store = storeList[position]
        edtName.setText(store.name)
        edtPhone.setText(store.phone)

        AlertDialog.Builder(this)
            .setTitle("更新店家")
            .setView(view)
            .setPositiveButton("更新") { _, _ ->
                val newName = edtName.text.toString()
                val newPhone = edtPhone.text.toString()
                if (newName.isNotBlank() && newPhone.isNotBlank()) {
                    storeList[position] = Store(newName, newPhone)
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }
}

data class Store(val name: String, val phone: String)

class StoreAdapter(private val context: Activity, private val data: List<Store>) :
    ArrayAdapter<Store>(context, R.layout.activity_store_item, data) {
    override fun getView(position: Int, convertView: View?, parent: android.view.ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_store_item, parent, false)
        val tvName = view.findViewById<TextView>(R.id.tv_store_name)
        val tvPhone = view.findViewById<TextView>(R.id.tv_store_phone)

        val store = data[position]
        tvName.text = store.name
        tvPhone.text = store.phone

        return view
    }
}