package com.fiuba.cuarentenainteligente.view.activities.survey

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.model.survey.SurveyResponse
import com.fiuba.cuarentenainteligente.viewmodel.location.LocationViewModel
import com.fiuba.cuarentenainteligente.viewmodel.survey.SurveyViewModel
import kotlinx.android.synthetic.main.activity_survey.*

class SurveyActivity : AppCompatActivity() {

    private val components = mutableListOf<ISurveyComponent>()
    private lateinit var surveyViewModel: SurveyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        ViewModelProvider(this).get(LocationViewModel::class.java)
        surveyViewModel = ViewModelProvider(this).get(SurveyViewModel::class.java)
        surveyViewModel.getSurvey().observe(this, Observer {
            drawSurvey(it)
        })
    }

    private fun drawSurvey(surveyResponse: SurveyResponse?) {
        val surveyComponentFactory = SurveyComponentFactory()
        surveyResponse?.survey?.forEach {
            val component = surveyComponentFactory.getView(
                it,
                this@SurveyActivity
            )
            survey_container.addView(component as View)
            components.add(component)
        }
        val confirmButton = Button(this)
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
            Toast.makeText(this@SurveyActivity, results.toString(), Toast.LENGTH_LONG).show()
            surveyViewModel.saveSurvey(results)
        }
    }
}