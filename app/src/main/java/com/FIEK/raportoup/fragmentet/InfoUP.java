package com.FIEK.raportoup.fragmentet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.FIEK.raportoup.R;
import com.FIEK.raportoup.utilities.FetchDataUP;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoUP#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoUP extends Fragment {

    public static TextView data1, data2, data3, data4;
//    public static RequestQueue requestQueue;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoUP() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NdihmaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoUP newInstance(String param1, String param2) {
        InfoUP fragment = new InfoUP();
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

        View viewGroup = inflater.inflate(R.layout.fragment_infoup, container, false);

        data1 = viewGroup.findViewById(R.id.data1);
        data2 = viewGroup.findViewById(R.id.data2);
        data3 = viewGroup.findViewById(R.id.data3);
        data4 = viewGroup.findViewById(R.id.data4);

        FetchDataUP process = new FetchDataUP();
        process.execute();

        return viewGroup;
    }
}