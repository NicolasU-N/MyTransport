package com.demo.nicolas.mytransport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inicializamos nuestro archivo de preferencias, llamado creedenciales con un modo de visualización Privado
        SharedPreferences preferences = getSharedPreferences("creedenciales", Context.MODE_PRIVATE);

        // Obtenemos el valor de la variable flag del archivo preferences
        boolean primerIngreso = preferences.getBoolean("flag",true);

        if(primerIngreso == true){ // issue - #2

            //Ejecutamos Activity SliderMain
            Intent intent = new Intent(this, SliderMain.class);
            startActivity(intent);
            finish();

            //Reescribimos la bandera

            //Inicializamos el objeto editor para modificar preferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("flag",false); // Reescribimos flag

            editor.commit();

        }else {

            //Nos dirigimos a la actividad principal
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
