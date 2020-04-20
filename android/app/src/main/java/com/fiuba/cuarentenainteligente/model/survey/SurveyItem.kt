package com.fiuba.cuarentenainteligente.model.survey

data class SurveyItem(var id: String, var type: String, var text: String) {
    constructor() : this(
        "", "", ""
    )
}
