package com.fiuba.cuarentenainteligente.view.ui.contact

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.model.qrgenerator.QrGenerated
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_bottom_nav.*
import kotlinx.android.synthetic.main.activity_qr_generator.*
import java.util.*


class QrGeneratorFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_qr_generator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                            val imageViewQrCode = view?.findViewById<ImageView>(R.id.qr_generator_code_image_fragment)
                            imageViewQrCode!!.setImageBitmap(bitmap)
                        } catch (e: Exception) {
                        }
                    } else {
                        Toast.makeText(requireActivity(), R.string.qr_generator_invalid, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
            val imm: InputMethodManager =
                requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(qr_contact_button.windowToken, 0)
        }

    }


}
