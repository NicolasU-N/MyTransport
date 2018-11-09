package com.demo.nicolas.mytransport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //Código del fragment
                    setTitle("Pagina Uno");
                    PaginaUno fragment = new PaginaUno();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container,fragment,"FragmentName");
                    fragmentTransaction.commit();
                    return true;

                case R.id.navigation_dashboard:
                    //Código del fragment
                    setTitle("Pagina Dos");
                    PaginaDos fragment2 = new PaginaDos();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.container,fragment2,"FragmentName");
                    fragmentTransaction2.commit();
                    return true;

                case R.id.navigation_notifications:
                    //Código del fragment
                    setTitle("Pagina Tres");
                    PaginaTres fragment3 = new PaginaTres();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.container,fragment3,"FragmentName");
                    fragmentTransaction3.commit();
                    return true;

                case R.id.mi_perfil:
                    //Código del fragment
                    setTitle("Pagina cuatro");
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


        //3:06 Fragment

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
