package com.huseyinerenguler.ceng427_hw2.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huseyinerenguler.ceng427_hw2.Manager.ManagerFetchFoodList;
import com.huseyinerenguler.ceng427_hw2.R;

import java.util.ArrayList;

public class FragmentFoodList extends Fragment {

    private View inf;
    private TextView textView_date;
    private TextView textView_meal1;
    private TextView textView_meal2;
    private TextView textView_meal3;
    private TextView textView_meal4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inf = inflater.inflate(R.layout.fragment_food_list, container, false);

        initViews();
        fetchFoodList();

        return inf;
    }

    private void initViews() {

        textView_date = inf.findViewById(R.id.textView_date);
        textView_meal1 = inf.findViewById(R.id.textView_meal1);
        textView_meal2 = inf.findViewById(R.id.textView_meal2);
        textView_meal3 = inf.findViewById(R.id.textView_meal3);
        textView_meal4 = inf.findViewById(R.id.textView_meal4);
    }

    private void fetchFoodList() {

        new ManagerFetchFoodList().fetchFoodList(new ManagerFetchFoodList.AsyncResponse() {
            @Override
            public void processFinish(ArrayList<String> result) {

                if (result.size() > 4) {
                    textView_date.setText(result.get(0));
                    textView_meal1.setText(result.get(1));
                    textView_meal2.setText(result.get(2));
                    textView_meal3.setText(result.get(3));
                    textView_meal4.setText(result.get(4));
                }
            }
        });
    }

}