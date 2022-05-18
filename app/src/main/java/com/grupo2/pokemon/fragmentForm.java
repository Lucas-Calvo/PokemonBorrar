package com.grupo2.pokemon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.grupo2.pokemon.databinding.FragmentFormBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentForm extends Fragment {


    private FragmentFormBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragmentForm() {
        // Required empty public constructor
    }

    public static fragmentForm newInstance(String param1, String param2) {
        fragmentForm fragment = new fragmentForm();
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnañadir=view.findViewById(R.id.btañadir);
        EditText etnombre=view.findViewById(R.id.nombrePokemon);


        btnañadir.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                PokemonBD db= new PokemonBD(getContext());
                String nombre=etnombre.getText().toString();
                db.añadirPokemon(nombre);
           }
        });
    }
}