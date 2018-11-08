package com.demo.nicolas.mytransport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater LayoutInflater;

    //Obtenemos el contexto, en qué actividad estoy
    public SliderAdapter (Context context){
        this.context = context;
    }

    //Titulo
    public String [] slide_titulos = {
            "¡Bienvenido!",
            "Rutas",
            "Valoraciones"
    };

    //Descripcion
    public String [] slide_desc = {
            "A continuación conocerás los destinos regionales que ofrecemos. ",
            "Encontrarás una sección donde podrás ver todas las rutas de una forma fácil y sencilla.",
            "Cada vez que viajes puedes valorar el servicio dejando un comentario o una puntuación.\n\n Empieza ahora mismo!"
    };

    @Override
    public int getCount() {
        return slide_titulos.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout)o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.inflate(R.layout.slide_layout, container, false);


        TextView txttitulo = (TextView) view.findViewById(R.id.slide_titulo);
        TextView txtdescripcion = (TextView) view.findViewById(R.id.slide_desc);


        txttitulo.setText(slide_titulos[position]);
        txtdescripcion.setText(slide_desc[position]);

        // Button btnempezar = (Button) view.findViewById(R.id.btnEmpezar);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
