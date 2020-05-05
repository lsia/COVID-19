package com.fiuba.cuarentenainteligente.view.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.view.activities.DniActivity
import com.fiuba.cuarentenainteligente.view.activities.contact.ContactActivity
import com.fiuba.cuarentenainteligente.view.activities.qrgenerator.QrGeneratorActivity
import com.fiuba.cuarentenainteligente.view.activities.survey.SurveyActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*


class profileFragment : Fragment() {

    private lateinit var dashboardViewModel: profileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(profileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_contact_selector.setOnClickListener {
            val bundle = Bundle()
            findNavController().navigate(R.id.contactActivity,bundle)
        }

        btn_qr_generator.setOnClickListener {
            val bundle = Bundle()
            findNavController().navigate(R.id.nav_gr_gen,bundle)
        }




    }
}
