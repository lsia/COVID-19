package com.fiuba.cuarentenainteligente.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.view.activities.contact.ContactActivity
import com.fiuba.cuarentenainteligente.view.activities.qrgenerator.QrGeneratorActivity
import kotlinx.android.synthetic.main.activity_main.*

// Es temporal hasta que tengamos el flujo, la idea es ir volcando features
class SelectorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dni_button.setOnClickListener {
            startActivity(Intent(this, DniActivity::class.java))
        }

        contact_selector_button.setOnClickListener {
            startActivity(Intent(this, ContactActivity::class.java))
        }

        qr_generator_button.setOnClickListener {
            startActivity(Intent(this, QrGeneratorActivity::class.java))
        }

    }
}
