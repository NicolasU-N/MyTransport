package com.demo.nicolas.mytransport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SliderMain extends AppCompatActivity {

    private ViewPager myslideViewPager;

    private LinearLayout dotLayout;
    private TextView [] mDots;

    private SliderAdapter SliderAdapter;


    private Button btnAtras;
    private  Button btnSiguiente;
    private Button btnEmpezar;

    private int pagina_actual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider_main_layout);

        myslideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        dotLayout = (LinearLayout) findViewById(R.id.dotLayout);

        btnAtras = (Button) findViewById(R.id.btnAtras);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnEmpezar = (Button) findViewById(R.id.btnEmpezar);


        SliderAdapter = new SliderAdapter(this);
        myslideViewPager.setAdapter(SliderAdapter);

        añadirPuntos(0);

        myslideViewPager.addOnPageChangeListener(viewListener);

        //OnClickListener

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myslideViewPager.setCurrentItem(pagina_actual + 1);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myslideViewPager.setCurrentItem(pagina_actual - 1);
            }
        });
    }


    public void añadirPuntos(int posicion){
        mDots = new TextView[3];
        dotLayout.removeAllViews();


        for (int i = 0 ; i < mDots.length;i++ ){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(60);
            mDots[i].setTextColor(getResources().getColor(R.color.colorBlancoTransparente));

            dotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0){

            mDots[posicion].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            añadirPuntos(i);

            pagina_actual = i;

            if(i==0){
                btnSiguiente.setEnabled(true);

                btnAtras.setEnabled(false);
                btnAtras.setVisibility(View.INVISIBLE);

                btnEmpezar.setEnabled(false);
                btnEmpezar.setVisibility(View.INVISIBLE);

                btnSiguiente.setText("Siguiente");
                btnAtras.setText("");

            }else if(i == mDots.length - 1 ){

                btnSiguiente.setEnabled(false);
                btnSiguiente.setVisibility(View.INVISIBLE);

                btnAtras.setEnabled(true);
                btnAtras.setVisibility(View.VISIBLE);

                btnEmpezar.setEnabled(true);
                btnEmpezar.setVisibility(View.VISIBLE);

                btnAtras.setText("Atrás");

                //btnSiguiente.setText("Empezar");

                btnEmpezar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SliderMain.this, AccesoActivity.class);
                        startActivity(intent);
                        finish(); // Finalizamos para no devolvernos
                    }
                });

            }else{
                btnSiguiente.setEnabled(true);
                btnAtras.setEnabled(true);
                btnAtras.setVisibility(View.VISIBLE);

                btnAtras.setText("Atrás");
                btnSiguiente.setText("Siguiente");

                // Sobreescribimos método de btnSiguiente - issue #1
                btnSiguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myslideViewPager.setCurrentItem(pagina_actual + 1);
                    }
                });
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


}
