package com.demo.nicolas.mytransport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    Log.d("ITEM_SELECCIONADO", " === Inico ===");
                    //Código del fragment
                    setTitle("Inicio"); //Título AppBar
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new PaginaUno(),"FragmentName")
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                    return true;

                case R.id.navigation_valoraciones:
                    Log.d("ITEM_SELECCIONADO", " === Mis Valoraciones ===");
                    //Código del fragment
                    setTitle("Mis Valoraciones");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new PaginaDos(),"FragmentName")
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                    return true;

                case R.id.navigation_mapa:
                    Log.d("ITEM_SELECCIONADO", " === Mapa ===");
                    //Código del fragment
                    setTitle("Mapa");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new PaginaTres(),"FragmentName")
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                    return true;

                case R.id.navigation_perfil:
                    Log.d("ITEM_SELECCIONADO", " === Mi perfil ===");
                    //Código del fragment
                    setTitle("Mi perfil");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new PaginaCuatro(),"FragmentName")
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Código del fragment
        setTitle("Inicio"); //Título AppBar
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new PaginaUno(),"FragmentName")
                .commit();
    }

}

