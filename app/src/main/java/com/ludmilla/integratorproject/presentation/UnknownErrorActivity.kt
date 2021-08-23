package com.ludmilla.integratorproject.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ludmilla.integratorproject.R

class UnknownErrorActivity :AppCompatActivity() {

    private lateinit var tryAgainBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        tryAgainBtn = findViewById(R.id.txtInformation2)
        tryAgainBtn.setOnClickListener { finish() }

    }
}