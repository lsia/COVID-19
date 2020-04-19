package com.fiuba.cuarentenainteligente.view.activities.qrgenerator

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.model.qrgenerator.QrGenerated
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_qr_generator.*
import java.util.*

//TODO hacer que se pueda descargar/imprimir el codigo
class QrGeneratorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_generator)
        qr_contact_button.setOnClickListener {
            qr_generator_site.editText?.text?.let { site ->
                qr_generator_room?.editText?.text?.let { room ->
                    if (site.isNotEmpty() && room.isNotEmpty()) {
                        try {
                            val qrGenerated =
                                QrGenerated(
                                    site = site.toString(),
                                    room = room.toString(),
                                    timestamp = Date().time
                                )
                            qrGenerated.createId()
                            val barcodeEncoder = BarcodeEncoder()
                            val bitmap = barcodeEncoder.encodeBitmap(
                                qrGenerated.toString(),
                                BarcodeFormat.QR_CODE,
                                600,
                                600
                            )
                            val imageViewQrCode =
                                findViewById<ImageView>(R.id.qr_generator_code_image)
                            imageViewQrCode.setImageBitmap(bitmap)
                        } catch (e: Exception) {
                        }
                    } else {
                        Toast.makeText(this, R.string.qr_generator_invalid, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
            val imm: InputMethodManager =
                this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(qr_contact_button.windowToken, 0)
        }
    }
}