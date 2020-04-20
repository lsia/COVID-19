package com.fiuba.cuarentenainteligente.viewmodel.survey

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiuba.cuarentenainteligente.model.survey.SurveyResponse
import com.fiuba.cuarentenainteligente.repositories.survey.FirebaseRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SurveyViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository()
    private val savedSurvey: MutableLiveData<SurveyResponse> = MutableLiveData()

    private val surveyEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            savedSurvey.value = dataSnapshot.getValue(SurveyResponse::class.java)
        }

        override fun onCancelled(p0: DatabaseError) {
        }

    }

    fun getSurvey(): MutableLiveData<SurveyResponse> {
        firebaseRepository.getSurvey().addValueEventListener(surveyEventListener)
        return savedSurvey
    }

    fun saveSurvey(results: List<Pair<String, String>>) {
        //TODO guardar cuando definamos la BD
    }

    override fun onCleared() {
        super.onCleared()
        firebaseRepository.getSurvey().removeEventListener(surveyEventListener)
    }
}