package com.fiuba.cuarentenainteligente.view.activities.survey

import android.content.Context
import com.fiuba.cuarentenainteligente.model.survey.SurveyItem

class SurveyComponentFactory {
    fun getView(surveyItem: SurveyItem, context: Context): ISurveyComponent? {
        when (surveyItem.type) {
            "boolean" -> {
                return SurveyBooleanComponent(surveyItem, context)
            }
        }
        return null
    }
}