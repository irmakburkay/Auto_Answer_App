package com.example.autoanswerapp;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment3() {

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment3, container, false);


        SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
        Date date1 = new Date();
        Date date2 = new Date();
        TextView name = (TextView) view.findViewById(R.id.editTextTextPersonName);
        TextView tel = (TextView) view.findViewById(R.id.editTextPhone);
        TextView time1 = (TextView) view.findViewById(R.id.textViewTime1);
        TextView time2 = (TextView) view.findViewById(R.id.textViewTime2);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton3);
        FloatingActionButton fab2 = (FloatingActionButton) view.findViewById(R.id.floatingActionButton4);

        time1.setOnClickListener(v -> {
            TimePickerDialog tpd = new TimePickerDialog(getContext(),
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
                            date1.setHours(hourOfDay);
                            date1.setMinutes(minuteOfDay);
                            time1.setText(dt.format(date1));
                        }
                    }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);
            tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Set", tpd);
            tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Cancel", tpd);
            tpd.show();
        });

        time2.setOnClickListener(v -> {
            TimePickerDialog tpd = new TimePickerDialog(getContext(),
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
                            date2.setHours(hourOfDay);
                            date2.setMinutes(minuteOfDay);
                            time2.setText(dt.format(date2));
                        }
                    }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);
            tpd.setButton(TimePickerDialog.BUTTON_POSITIVE, "Set", tpd);
            tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Cancel", tpd);
            tpd.show();
        });

        fab.setOnClickListener(v -> {
            MainActivity.floatingActionButton.performClick();
            Fragment2.addItem(0, name.getText().toString(), tel.getText().toString(), date1, date2);
            if(!MainActivity.tabLayout.getTabAt(1).isSelected()){
                MainActivity.tabLayout.getTabAt(1).select();
            }
        });
        fab2.setOnClickListener(v -> {

        });
        //saat aralığı seçip parametreye yolla

        return view;
    }

}