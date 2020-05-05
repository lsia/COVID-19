package com.fiuba.cuarentenainteligente.view.ui.survey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.model.survey.SurveyResponse
import com.fiuba.cuarentenainteligente.view.activities.survey.ISurveyComponent
import com.fiuba.cuarentenainteligente.view.activities.survey.SurveyComponentFactory
import com.fiuba.cuarentenainteligente.viewmodel.location.LocationViewModel
import com.fiuba.cuarentenainteligente.viewmodel.survey.SurveyViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_survey.*


class surveyFragment : Fragment() {

    private val components = mutableListOf<ISurveyComponent>()
    private lateinit var surveyViewModel: SurveyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_survey, container, false)
        ViewModelProvider(this).get(LocationViewModel::class.java)
        surveyViewModel = ViewModelProvider(this).get(SurveyViewModel::class.java)
        surveyViewModel.getSurvey().observe(viewLifecycleOwner, Observer {
            drawSurvey(it)
        })
        return root
    }

    private fun drawSurvey(surveyResponse: SurveyResponse?) {
        val surveyComponentFactory = SurveyComponentFactory()
        surveyResponse?.survey?.forEach {
            val component = activity?.let { activity ->
                surveyComponentFactory.getView(
                    it,
                    activity
                )
            }
            survey_container.addView(component as View)
            components.add(component)
        }
        val confirmButton = Button(activity)
        confirmButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            topMargin = resources.getDimensionPixelSize(R.dimen.reduced_margin)
            bottomMargin = resources.getDimensionPixelSize(R.dimen.reduced_margin)
            leftMargin = resources.getDimensionPixelSize(R.dimen.default_margin)
            rightMargin = resources.getDimensionPixelSize(R.dimen.default_margin)
        }
        confirmButton.setText(R.string.survey_confirm)
        survey_container.addView(confirmButton)
        confirmButton.setOnClickListener {
            val results = mutableListOf<Pair<String, String>>()
            components.forEach {
                results.add(it.getResult())
            }
            Toast.makeText(activity, results.toString(), Toast.LENGTH_LONG).show()
            surveyViewModel.saveSurvey(results)
        }
    }



}
