package com.fiuba.cuarentenainteligente.repositories.survey

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepository {
    private val database = Firebase.database

    fun getSurvey(): DatabaseReference {
        return database.getReference("survey")
    }
}