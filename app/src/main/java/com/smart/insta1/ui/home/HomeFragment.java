package com.smart.insta1.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.ButtonBarLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.smart.insta1.R;


import android.app.AlertDialog.Builder;
import android.widget.Toast;

import 	java.util.Calendar;
import java.util.TimeZone;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;

    TextView textDate;
    TextView SelectAddress;
    TextView countPeople;


    Button addPeople, mPeople, addChildren, mChildren;
    int countPerson = 1;
    int countChilden = 0;
    private static final String TAG = "HomeFragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        countPeople =  root.findViewById(R.id.count_people);
        countPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View veiw = getLayoutInflater().inflate(R.layout.layout_count_people,null);
                BottomSheetDialog dialog =  new BottomSheetDialog(getContext());
                dialog.setContentView(veiw);
                addPeople =veiw.findViewById(R.id.btn_people_p);
                mPeople = veiw.findViewById(R.id.btn_people_m);
                TextView textViewCountPerson = veiw.findViewById(R.id.textViewCountPeople);
                TextView textViewCountChildren = veiw.findViewById(R.id.textViewCountChildren);

                addChildren = veiw.findViewById(R.id.btn_children_p);
                mChildren = veiw.findViewById(R.id.btn_children_m);





                addChildren.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countChilden ++;
                        textViewCountChildren.setText(String.valueOf(countChilden));

                        if (countPerson < 1 ){
                            mPeople.setEnabled(false);
                        }
                        else {
                            mPeople.setEnabled(true);
                        }
                    }
                });

                mChildren.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countChilden--;
                        textViewCountChildren.setText(String.valueOf(countChilden));
                        if (countPerson < 1 ){
                            mPeople.setEnabled(false);
                        }
                        else {
                            mPeople.setEnabled(true);
                        }
                    }
                });

                addPeople.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countPerson++;
                        textViewCountPerson.setText(String.valueOf(countPerson));

                        if(countChilden<1){
                            mChildren.setEnabled(false);
                        }
                        else {
                            mChildren.setEnabled(true);
                        }
                    }
                });

                mPeople.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        countPerson--;
                        textViewCountPerson.setText(String.valueOf(countPerson));
                        if(countChilden<1){
                            mChildren.setEnabled(false);
                        }
                        else {
                            mChildren.setEnabled(true);
                        }
                    }
                });


                dialog.show();
            }

        });

        SelectAddress = root.findViewById(R.id.textAddress);
        SelectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogRegion();
            }
        });

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();

        long today = MaterialDatePicker.todayInUtcMilliseconds();
        calendar.setTimeInMillis(today);



        calendar.set(Calendar.MONTH, Calendar.APRIL);
        long april =  calendar.getTimeInMillis();


        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        long december = calendar.getTimeInMillis();

        CalendarConstraints.Builder constraints = new CalendarConstraints.Builder();
        constraints.setValidator(DateValidatorPointForward.now());

        Pair<Long, Long> selection =  Pair.create(today, december);
        MaterialDatePicker.Builder<Pair<Long, Long>> builder =  MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select a Date");
        builder.setSelection(selection);
        builder.setCalendarConstraints(constraints.build());


        final MaterialDatePicker materialDatePicker = builder.build();
        textDate = root.findViewById(R.id.textDate);
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                materialDatePicker.show(getFragmentManager() , "Date_picker");

            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                textDate.setText( materialDatePicker.getHeaderText()+" 2020 г");
                Log.d(TAG, materialDatePicker.getHeaderText());



            }
        });
        return root;
    }

    private void showDialogRegion(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose an animal");

// add a list
        String[] regions = {"horse", "cow", "camel", "sheep", "goat"};
        builder.setItems(regions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: SelectAddress.setText(regions[0]);
                        break;
                    case 1: SelectAddress.setText(regions[1]);
                        break;
                    case 2: SelectAddress.setText(regions[2]);
                        break;
                    case 3: SelectAddress.setText(regions[3]);
                        break;
                    case 4: SelectAddress.setText(regions[4]);
                        break;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


//    private void CountAlertDialog(){
//
//        dialog = new Dialog(getContext());
//        dialog.setContentView(R.layout.layout_count_people);
//        dialog.setTitle("Укажите количесто людей");
//        addPeople =
//
//    }
}