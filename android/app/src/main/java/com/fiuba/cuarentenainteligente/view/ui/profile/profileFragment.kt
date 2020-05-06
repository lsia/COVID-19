package com.fiuba.cuarentenainteligente.view.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.fiuba.cuarentenainteligente.R
import kotlinx.android.synthetic.main.fragment_profile.*


class profileFragment : Fragment() {

    private lateinit var profViewModel: profileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View? {
        profViewModel =
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
