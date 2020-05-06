package com.fiuba.cuarentenainteligente.view.ui.contact

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.model.contact.Person
import com.fiuba.cuarentenainteligente.model.location.LocationModel
import com.fiuba.cuarentenainteligente.utils.GpsUtils
import com.fiuba.cuarentenainteligente.view.ui.scanner.QrReaderFragment
import com.fiuba.cuarentenainteligente.viewmodel.location.LocationViewModel


class ContactFragment : Fragment() {

    private lateinit var locationViewModel: LocationViewModel
    private var isGPSEnabled = false
    private var latestLocation: LocationModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        activity?.let {
            GpsUtils(it).turnGPSOn(object : GpsUtils.OnGpsListener {

                override fun gpsStatus(isGPSEnable: Boolean) {
                    this@ContactFragment.isGPSEnabled = isGPSEnable

                }
            })
        }

        if (savedInstanceState == null) {
            activity?.supportFragmentManager?.commit {
                add(R.id.contact_container, ContactSelectorFragment.newInstance())
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == GpsUtils.GPS_REQUEST) {
                isGPSEnabled = true
                invokeLocationAction()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            GpsUtils.LOCATION_REQUEST -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    invokeLocationAction()
                }
                return
            }

        }
    }

    private fun invokeLocationAction() {
        if (checkPermissions()) {
            startLocationUpdate()
        }
    }

    private fun startLocationUpdate() {
        locationViewModel.getLocationData().observe(this, Observer {
            it?.let {
                latestLocation = it
            }
        })

    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            builder.setTitle(R.string.location_permission_request_title)
            builder.setMessage(R.string.location_permission_request_message)

            builder.setPositiveButton(R.string.acept) { _, _ ->
                requestPermissions()
            }

            builder.setOnDismissListener {
                requestPermissions()
            }
            // create and show the alert dialog
            val dialog: AlertDialog = builder.create()
            dialog.show()
            return false
        } else {
            return true
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), GpsUtils.LOCATION_REQUEST
        )
    }

    fun replaceToManualContactFragment() {
        activity?.supportFragmentManager?.commit {
            addToBackStack(ManualContactFragment.TAG)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            replace(R.id.contact_container, ManualContactFragment.newInstance())
        }
    }

    fun replaceToContactCongratsFragment(person: Person) {
        activity?.supportFragmentManager?.commit {
            Toast.makeText(activity, "HOLA", Toast.LENGTH_LONG).show()
            addToBackStack(QrReaderFragment.TAG)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            locationViewModel.getLocationData().removeObservers(requireActivity())
            replace(R.id.contact_container, ContactCongrats.newInstance(person, latestLocation))
        }
    }



}


