/*
 * Group 22 Homework 03
 * ViewDrinksFragment.java
 * Ken Stanley & Stephanie Karp
 */
package edu.uncc.hw04.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.uncc.hw04.Drink;
import edu.uncc.hw04.R;
import edu.uncc.hw04.ViewDrinksRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ViewDrinksFragment extends Fragment {
    int currentDrinkNumber = 0;
    ArrayList<Drink> drinks;
    LinearLayoutManager layoutManager;
    ViewDrinksRecyclerAdapter adapter;

    public ViewDrinksFragment(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewdrinks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView viewDrinksRecyclerView = view.findViewById(R.id.viewDrinksRecyclerView);
        viewDrinksRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        viewDrinksRecyclerView.setLayoutManager(layoutManager);
        adapter = new ViewDrinksRecyclerAdapter(getActivity(), drinks);
        viewDrinksRecyclerView.setAdapter(adapter);

        updateView(drinks);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof iListener) {
            listener = (iListener) context;
        } else {
            throw new RuntimeException(context + getString(R.string.listener_throw_message));
        }
    }

    public void updateView(ArrayList<Drink> drinks) {
        Drink currentDrink = drinks.get(currentDrinkNumber);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);

        Date drinkDate = Calendar.getInstance().getTime();
        drinkDate.setTime(currentDrink.dateTime);
    }

    iListener listener;

    public interface iListener {
        void viewDrinksButtonCloseClicked(ArrayList<Drink> drinks);
    }
}
