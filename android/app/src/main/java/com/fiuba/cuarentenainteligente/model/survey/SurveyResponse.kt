package com.fiuba.cuarentenainteligente.model.survey

data class SurveyResponse(var minSymptom: Long, var survey: List<SurveyItem>) {
    constructor() : this(
        -1, emptyList()
    )
}