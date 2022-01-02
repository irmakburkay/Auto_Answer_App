package com.example.autoanswerapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
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

    public View view;

    public static ArrayList<Class2> items;

    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment2, container, false);

        items = new ArrayList<Class2>();
        items.add(new Class2("Ahmet","0000000002",new Date(),new Date()));
        items.add(new Class2("Mehmet","0000000001",new Date(),new Date()));
        items.add(new Class2("Unknown","0000000000",new Date(),new Date()));
        items.add(new Class2("Ahmet","0000000002",new Date(),new Date()));
        items.add(new Class2("Mehmet","0000000001",new Date(),new Date()));
        items.add(new Class2("Unknown","0000000000",new Date(),new Date()));
        items.add(new Class2("Ahmet","0000000002",new Date(),new Date()));
        items.add(new Class2("Mehmet","0000000001",new Date(),new Date()));
        items.add(new Class2("Unknown","0000000000",new Date(),new Date()));
        items.add(new Class2("Ahmet","0000000002",new Date(),new Date()));
        items.add(new Class2("Mehmet","0000000001",new Date(),new Date()));
        items.add(new Class2("Unknown","0000000000",new Date(),new Date()));

        recyclerView = view.findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new RvAdapter(items);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);




        return view;
    }
    public static void addItem(int position, String name, String tel, Date date1, Date date2)
    {

        items.add(position, new Class2(name, tel, date1, date2));
        recyclerView.smoothScrollToPosition(0);
        adapter.notifyItemInserted(position);

    }
}