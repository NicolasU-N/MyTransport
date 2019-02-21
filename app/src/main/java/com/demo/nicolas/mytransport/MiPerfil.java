package com.demo.nicolas.mytransport;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;






/**
 * A simple {@link Fragment} subclass.
 */

// TODO: 19/02/2019: 1. Agregar Listners btns 2.Agregar animación


public class MiPerfil extends Fragment {


     // Instanciamos fragment
    Button btnIniciarSesion;

    Button btnConocenos;


    public MiPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mi_perfil, container, false);


        //Inicializamos buttons
        btnConocenos = (Button) view.findViewById(R.id.btnConocenos);
        btnIniciarSesion = (Button) view.findViewById(R.id.btnIniciarSesion);

        btnConocenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager().beginTransaction();

                trans.replace(R.id.container, new ConocenosFragment()); // Llamamos al frame layout (Contenedor de la clase main)

                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });

        /**btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccesoActivity.class);
                //intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //No se puede quedar una como anterior de la otra
                startActivity(intent);
            }
        });**/


        return view;
    }



}