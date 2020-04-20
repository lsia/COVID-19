package com.fiuba.cuarentenainteligente.view.activities.survey

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fiuba.cuarentenainteligente.R
import com.fiuba.cuarentenainteligente.model.survey.SurveyItem
import kotlinx.android.synthetic.main.survey_boolean_component.view.*

class SurveyBooleanComponent(
    private val surveyItem: SurveyItem,
    context: Context
) : LinearLayout(context), ISurveyComponent {

    init {
        LayoutInflater.from(context).inflate(R.layout.survey_boolean_component, this, true)
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            topMargin = resources.getDimensionPixelSize(R.dimen.reduced_margin)
            bottomMargin = resources.getDimensionPixelSize(R.dimen.reduced_margin)
            leftMargin = resources.getDimensionPixelSize(R.dimen.default_margin)
            rightMargin = resources.getDimensionPixelSize(R.dimen.default_margin)
        }

        survey_boolean_text.text = surveyItem.text
    }

    override fun getResult(): Pair<String, String> {
        return Pair(surveyItem.id, survey_boolean_check.isChecked.toString())
    }
}