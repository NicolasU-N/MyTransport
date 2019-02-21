package com.demo.nicolas.mytransport;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.nicolas.mytransport.models.Users;
import com.demo.nicolas.mytransport.utils.Constants;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilSesionOnFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilSesionOnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilSesionOnFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "argName";
    private static final String ARG_PARAM2 = "argEmail";
    private static final String ARG_PARAM3 = "argId";
    private static final String ARG_PARAM4 = "argPhotoUrl";

    // TODO: Rename and change types of parameters
    private String argName;
    private String argEmail;
    private String argId;
    private String argPhotoUrl;

    Button btnConocenos2;

    private ConocenosFragment.OnFragmentInteractionListener mListener;




    public PerfilSesionOnFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilSesionOnFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilSesionOnFragment newInstance(String param1, String param2,String param3,String param4) {
        PerfilSesionOnFragment fragment = new PerfilSesionOnFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { // Obtenemos los Strings de la activity main por medio de la key
            argName = getArguments().getString(ARG_PARAM1);
            argEmail = getArguments().getString(ARG_PARAM2);
            argId = getArguments().getString(ARG_PARAM3);
            argPhotoUrl = getArguments().getString(ARG_PARAM4);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil_sesion_on, container, false);
        // Inflate the layout for this fragment

        ImageView mImg_photo = (ImageView) view.findViewById(R.id.img_photo);
        TextView mTv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView mTv_email = (TextView) view.findViewById(R.id.tv_email);
        //TextView mTv_id = (TextView) view.findViewById(R.id.tv_id);
      //  Button btn_logout = (Button) view.findViewById(R.id.btn_logout);
        //Button btn_revoke = (Button) view.findViewById(R.id.btn_revoke);


        btnConocenos2 = (Button) view.findViewById(R.id.btnConocenos2);

        btnConocenos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager().beginTransaction();

                trans.replace(R.id.container, new ConocenosFragment()); // Llamamos al frame layout (Contenedor de la clase main)

                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });

        //Glide.with(this).load(argPhotoUrl).into(mImg_photo); // Descargamos la foto de perfil

        //mTv_name.setText(argName);
        //mTv_name.setVisibility(View.VISIBLE);
        //mTv_email.setText(argEmail);
        //mTv_id.setText(argId);


            return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ConocenosFragment.OnFragmentInteractionListener) {
            mListener = (ConocenosFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

