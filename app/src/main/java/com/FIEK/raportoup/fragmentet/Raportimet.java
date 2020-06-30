package com.FIEK.raportoup.fragmentet;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.FIEK.raportoup.R;
import com.FIEK.raportoup.adapter.AdminAdapter;
import com.FIEK.raportoup.databaza.Databaza;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Raportimet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Raportimet extends Fragment {

    RecyclerView recyclerViewAdmin;
    ImageView empty_imageviewAdmin;
    TextView no_dataAdmin;

    Databaza db;
    ArrayList<String> id;
    ArrayList<String> username;
    ArrayList<String> koment;
    ArrayList<String> kategorite;
    ArrayList<byte[]> selectedImage;
    ArrayList<String> koha;
    ArrayList<String> adresa;

    AdminAdapter adminAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Raportimet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Raportimet.
     */
    // TODO: Rename and change types and number of parameters
    public static Raportimet newInstance(String param1, String param2) {
        Raportimet fragment = new Raportimet();
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
        View viewGroup = inflater.inflate(R.layout.fragment_raportimet, container, false);

        recyclerViewAdmin = viewGroup.findViewById(R.id.recyclerViewAdmin);
        empty_imageviewAdmin = viewGroup.findViewById(R.id.empty_imageviewAdmin);
        no_dataAdmin = viewGroup.findViewById(R.id.no_dataAdmin);

        db = new Databaza(getContext());
//        id = new ArrayList<>();
        username = new ArrayList<>();
        koment = new ArrayList<>();
        kategorite = new ArrayList<>();
        selectedImage = new ArrayList<byte[]>();
        koha = new ArrayList<>();
        adresa = new ArrayList<>();

        storeDataInArrays();

        adminAdapter = new AdminAdapter(getActivity(), getContext(), username, koment, kategorite, selectedImage, koha, adresa);
        recyclerViewAdmin.setAdapter(adminAdapter);
        recyclerViewAdmin.setLayoutManager(new LinearLayoutManager(getActivity()));

        return viewGroup;
    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllDataAdmin();
        if (cursor.getCount() == 0) {
            empty_imageviewAdmin.setVisibility(View.VISIBLE);
            no_dataAdmin.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
//                id.add(cursor.getString(0));
                username.add(cursor.getString(1));
                koment.add(cursor.getString(2));
                kategorite.add(cursor.getString(3));
                selectedImage.add(cursor.getBlob(4));
                koha.add(cursor.getString(5));
                adresa.add(cursor.getString(6));
            }
            empty_imageviewAdmin.setVisibility(View.GONE);
            no_dataAdmin.setVisibility(View.GONE);
        }
    }
}