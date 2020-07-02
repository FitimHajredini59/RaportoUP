package com.FIEK.raportoup.fragmentet;

import android.content.Intent;
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
import com.FIEK.raportoup.adapter.CustomAdapter;
import com.FIEK.raportoup.aktivitetet.FaqjaPare;
import com.FIEK.raportoup.databaza.Databaza;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RaportetMiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RaportetMiaFragment extends Fragment {

    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;
    FloatingActionButton add_button;

    Databaza db;
    ArrayList<String> username;
    ArrayList<String> koment;
    ArrayList<String> kategorite;
    ArrayList<byte[]> selectedImage;
    ArrayList<String> koha;
    ArrayList<String> adresa;

    CustomAdapter customAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RaportetMiaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RaportetMiaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RaportetMiaFragment newInstance(String param1, String param2) {
        RaportetMiaFragment fragment = new RaportetMiaFragment();
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

        View viewGroup = inflater.inflate(R.layout.fragment_raportet_mia, container, false);

        recyclerView = viewGroup.findViewById(R.id.recyclerView);
        add_button = viewGroup.findViewById(R.id.add_button);
        empty_imageview = viewGroup.findViewById(R.id.empty_imageview);
        no_data = viewGroup.findViewById(R.id.no_data);

        FloatingActionButton fab = (FloatingActionButton) viewGroup.findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Krijo një raport të ri", Snackbar.LENGTH_LONG)
                        .setAction("Krijo", null).show();
                Intent intent = new Intent(getContext(), FaqjaPare.class);
                startActivity(intent);
            }
        });

        db = new Databaza(getContext());
        username = new ArrayList<>();
        koment = new ArrayList<>();
        kategorite = new ArrayList<>();
        selectedImage = new ArrayList<byte[]>();
        koha = new ArrayList<>();
        adresa = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(getActivity(), getContext(), username, koment, kategorite, selectedImage, koha, adresa);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return viewGroup;
    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                username.add(cursor.getString(1));
                koment.add(cursor.getString(2));
                kategorite.add(cursor.getString(3));
                selectedImage.add(cursor.getBlob(4));
                koha.add(cursor.getString(5));
                adresa.add(cursor.getString(6));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}