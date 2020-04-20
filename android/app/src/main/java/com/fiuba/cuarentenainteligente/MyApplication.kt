package com.fiuba.cuarentenainteligente

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //TODO ver si queremos datos offline, yo estimo que si, esto deja preparados los archivos
        // actualizados, la encuesta y deja los write en una cola esperando conexion
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}