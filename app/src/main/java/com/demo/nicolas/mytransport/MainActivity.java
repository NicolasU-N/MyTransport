package com.demo.nicolas.mytransport;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    Log.d("ITEM_SELECCIONADO", " === Inico ===");
                    //Código del fragment
                    setTitle("Inicio");
                    PaginaUno fragment = new PaginaUno();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container,fragment,"FragmentName");
                    fragmentTransaction.commit();
                    return true;

                case R.id.navigation_valoraciones:
                    Log.d("ITEM_SELECCIONADO", " === Mis Valoraciones ===");
                    //Código del fragment
                    setTitle("Mis Valoraciones");
                    PaginaDos fragment2 = new PaginaDos();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.container,fragment2,"FragmentName");
                    fragmentTransaction2.commit();
                    return true;

                case R.id.navigation_mapa:
                    Log.d("ITEM_SELECCIONADO", " === Mapa ===");
                    //Código del fragment
                    setTitle("Mapa");
                    PaginaTres fragment3 = new PaginaTres();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.container,fragment3,"FragmentName");
                    fragmentTransaction3.commit();
                    return true;

                case R.id.navigation_perfil:
                    Log.d("ITEM_SELECCIONADO", " === Mi perfil ===");
                    //Código del fragment
                    setTitle("Mi perfil");
                    PaginaCuatro fragment4 = new PaginaCuatro();
                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction4.replace(R.id.container,fragment4,"FragmentName");
                    fragmentTransaction4.commit();
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
        setTitle("Pagina Uno");
        PaginaUno fragment = new PaginaUno();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment,"FragmentName");
        fragmentTransaction.commit();
    }

}

