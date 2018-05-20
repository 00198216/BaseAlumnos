package com.example.charl.basealumnos.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.charl.basealumnos.DBHelper.DBHelper;
import com.example.charl.basealumnos.Data.Alumno;
import com.example.charl.basealumnos.R;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Modify.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Modify#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Modify extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText id,nombre,nota;
    private Button btnBuscar,btnEliminar,btnActualizar,btnLimpiar;
    private boolean flag=false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Modify() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Modify.
     */
    // TODO: Rename and change types and number of parameters
    public static Modify newInstance(String param1, String param2) {
        Modify fragment = new Modify();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_modify, container, false);


        id = (EditText) vista.findViewById(R.id.txtIdM);
        nombre = (EditText) vista.findViewById(R.id.txtNombreM);
        nota = (EditText)vista.findViewById(R.id.txtNotaM);
        btnBuscar = (Button)vista.findViewById(R.id.btnBuscarM);
        btnEliminar = (Button)vista.findViewById(R.id.btnEliminarM);
        btnActualizar = (Button)vista.findViewById(R.id.btnActualizarM);
        btnLimpiar = (Button) vista.findViewById(R.id.btnLimpiarM);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumno P = DBHelper.myDB.findUser(id.getText().toString());
                if(P==null){
                    Toast.makeText(getContext(),"El usuario no fue encontrado", Toast.LENGTH_SHORT).show();
                    limpiar();
                }
                else{
                    nombre.setText(P.getNombre());
                    nota.setText(P.getNota());
                    flag=true;
                }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    Toast.makeText(getContext(), "No hay usuario para actualizar", Toast.LENGTH_SHORT).show();
                } else {
                    if (parseFloat(nota.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Nota erronea", Toast.LENGTH_SHORT).show();
                        limpiar();
                        flag = false;
                    } else {
                        DBHelper.myDB.editUser(new Alumno(id.getText().toString(), nombre.getText().toString(), nota.getText().toString()));
                        flag=false;
                    }
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.myDB.deleteUser(id.getText().toString());
                limpiar();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });


        return vista;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    public void limpiar(){
        nombre.setText("");
        id.setText("");
        nota.setText("");
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
