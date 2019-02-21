package com.demo.nicolas.mytransport;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.demo.nicolas.mytransport.models.Users;
import com.demo.nicolas.mytransport.utils.Constants;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResolvingResultCallbacks;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ConocenosFragment.OnFragmentInteractionListener,Inico.OnFragmentInteractionListener,PerfilSesionOnFragment.OnFragmentInteractionListener, GoogleApiClient.OnConnectionFailedListener { //Implementamos los onclick de los fragments que vamos a mostrar

    public static final String user="names";

    //Login con Firebase
    private FirebaseAuth firebaseAuth;

    //Login Silencioso Google
    private GoogleApiClient googleApiClient;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    Log.d("ITEM_SELECCIONADO", " === Inico ===");
                    //Código del fragment
                    setTitle("                            Inicio"); //Título AppBar
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Inico(),"FragmentName")
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                    return true;



                case R.id.navigation_mapa:
                    Log.d("ITEM_SELECCIONADO", " === Mapa ===");
                    //Código del fragment
                    setTitle("                            Mapa");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Mapa(),"FragmentName")
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                    return true;

                case R.id.navigation_perfil:

                    Log.d("ITEM_SELECCIONADO", " === Mi perfil ===");
                    //Código del fragment
                    setTitle("                          Mi perfil");

                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new PerfilSesionOnFragment(),"FragmentName")
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
                    /**
                    OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
                    if(opr.isDone()){//Accedemos al resultado del login
                        GoogleSignInResult result = opr.get();
                        handleSignInResult(result);
                    }else {
                        opr.setResultCallback(new ResultCallback<GoogleSignInResult>(){
                            @Override
                            public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                                handleSignInResult(googleSignInResult);
                            }
                        });
                    }**/
                    return true;

                case R.id.logut_item:

                    Log.d("ITEM_SELECCIONADO", " === CERRAR SESIÓN ===");
                     //Código del fragment
                     //setTitle("Mis Valoraciones");
                     //getSupportFragmentManager().beginTransaction().replace(R.id.container,new MisValoraciones(),"FragmentName")
                     //.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();

                    firebaseAuth.signOut();
                    startActivity(new Intent(MainActivity.this, AccesoActivity.class));
                    finish();

                    Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()){
                                Intent intent = new Intent(MainActivity.this, AccesoActivity.class);
                                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //No se puede quedar una como anterior de la otra
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "No se pudo cerrar Sesión",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    /**if(user != null) {
                        firebaseAuth.signOut();
                        startActivity(new Intent(MainActivity.this, AccesoActivity.class));
                        finish();
                    }else {
                        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                if (status.isSuccess()){
                                    Intent intent = new Intent(MainActivity.this, AccesoActivity.class);
                                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //No se puede quedar una como anterior de la otra
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(), "No se pudo cerrar Sesión",Toast.LENGTH_LONG).show();
                                }
                            }
                        });**/
                    //}
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

        //Código del fragment de inicio por defecto
        setTitle("                            Inicio"); //Título AppBar
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Inico(),"FragmentName")
                .commit();


        //Login con firebase
        firebaseAuth = FirebaseAuth.getInstance();


        //Login silencioso
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

    }

    private void handleSignInResult(GoogleSignInResult result) {

        GoogleSignInAccount account = result.getSignInAccount();

        String photoUrl = account.getPhotoUrl().toString();
        PerfilSesionOnFragment fragment;
        fragment = PerfilSesionOnFragment.newInstance(account.getDisplayName(),account.getEmail(),account.getId(),photoUrl);

        if(result.isSuccess()){

             //Iniciamos fragment



            getSupportFragmentManager().beginTransaction().replace(R.id.container,new PerfilSesionOnFragment(),"FragmentName")
                   .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();




        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new PerfilSesionOnFragment(),"FragmentName")
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}

