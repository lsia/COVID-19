package com.fiuba.cuarentenainteligente.view.fragments.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.model.contact.Person
import com.fiuba.cuarentenainteligente.view.activities.contact.ContactActivity
import kotlinx.android.synthetic.main.fragment_manual_contact.*

class ManualContactFragment : Fragment() {
    companion object {
        val TAG = ManualContactFragment::class.java.simpleName

        fun newInstance(): ManualContactFragment {
            return ManualContactFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_manual_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contact_registration_button.setOnClickListener {
            if (contact_registration_editText.text.isNotEmpty()) {
                (activity as ContactActivity).replaceToContactCongratsFragment(Person(dni = contact_registration_editText.text.toString()))
            } else {
                Toast.makeText(context, R.string.manual_contact_empty_menssage, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}