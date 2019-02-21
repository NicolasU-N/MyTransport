package com.demo.nicolas.mytransport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccesoActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    //Autenticación con google
    private GoogleApiClient googleApiClient;

    private SignInButton signInButton;

    public static final int SIGN_IN_CODE = 777;

    private Button btnLogin;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser usuarioActual = firebaseAuth.getCurrentUser();
        actualizarUsuario(usuarioActual);

        //GoogleLogin
        OptionalPendingResult<GoogleSignInResult>opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            startActivity(new Intent(AccesoActivity.this, MainActivity.class));
            finish();
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    if(result.isSuccess()){
                        GoogleSignInAccount account = result.getSignInAccount();
                    }else {
                        //startActivity(new Intent(AccesoActivity.this, AccesoActivity.class)); //i dont know
                        //finish();
                    }
                }
            });
        }

    }

    private void actualizarUsuario(FirebaseUser usuarioActual) {
        if (usuarioActual != null) {
            //if(usuarioActual.isEmailVerified()){
                startActivity(new Intent(AccesoActivity.this, MainActivity.class));
                finish();

            //}else {
              //  Toast.makeText(AccesoActivity.this, "Debe Verificar su email", Toast.LENGTH_SHORT).show();
            //}
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        Button sButton = (Button)findViewById(R.id.btnCorreoSingup);

        firebaseAuth = FirebaseAuth.getInstance();

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccesoActivity.this,SingUpActivity.class);
                startActivity(intent);
            }
        });



        // -------- GOOGLE
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this) //Donde estoy y  quién me recive el mensaje de error
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent , SIGN_IN_CODE); // Esperamos resultado , Codigo identificador
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result= Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSingInResult(result);
        }
    }

    private void handleSingInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            goMainScreen();//Activity Donde mostraremos los datos MainActivity->Mi perfil
        }else {
            Toast.makeText(this, "No se pudo iniciar sesión",Toast.LENGTH_SHORT).show();
        }

    }

    private void goMainScreen() {
        Intent intent = new Intent(AccesoActivity.this, MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //No se puede quedar una como anterior de la otra
        startActivity(intent);
    }
}
