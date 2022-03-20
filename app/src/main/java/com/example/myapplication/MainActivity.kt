package com.example.myapplication


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText


class MainActivity: AppCompatActivity(R.layout.activity_main) {

    private lateinit var btnConsult: AppCompatButton
    private lateinit var etConsult: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        initViews()

        initListeners()
    }

    private fun initViews(){
        btnConsult = findViewById(R.id.btnConsult)
        etConsult = findViewById(R.id.etConsult)
    }

    private fun initListeners(){
        btnConsult.setOnClickListener{view -> listeners(view)}
    }

    private fun listeners(view: View) {
        when(view.id) {
            R.id.btnConsult -> verifyPlate(etConsult.text.toString())

        }
    }

    private fun verifyPlate(plate:String){
        println(plate)
        println("apenas")
        supportFragmentManager.beginTransaction().add(R.id.activity_main, CountDownFragment.newInstance(), "countDown").commit()

    }
}

