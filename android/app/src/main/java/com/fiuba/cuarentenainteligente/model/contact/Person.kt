package com.fiuba.cuarentenainteligente.model.contact

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val dni: String,
    val name: String? = null,
    val lastName: String? = null,
    val gender: String? = null,
    val birthdate: String? = null
) : Parcelable