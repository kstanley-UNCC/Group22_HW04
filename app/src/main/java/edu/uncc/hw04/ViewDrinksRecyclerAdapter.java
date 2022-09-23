package edu.uncc.hw04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ViewDrinksRecyclerAdapter extends RecyclerView.Adapter<ViewDrinksRecyclerAdapter.DrinkViewHolder> {
    ArrayList<Drink> drinks;

    public ViewDrinksRecyclerAdapter(ArrayList<Drink> data) {
        this.drinks = data;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_viewdrinks_list_row, parent, false);

        DrinkViewHolder drinkViewHolder = new DrinkViewHolder(view);

        return drinkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drink drink = drinks.get(position);
        holder.viewDrinksListRowAlcoholPercent.setText((int) drink.drinkAlcoholPercent);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
        Date drinkDate = Calendar.getInstance().getTime();
        drinkDate.setTime(drink.dateTime);

        holder.viewDrinksListRowDateAdded.setText((CharSequence) drinkDate);
    }

    @Override
    public int getItemCount() {
        return this.drinks.size();
    }

    public static class DrinkViewHolder extends RecyclerView.ViewHolder {

        TextView viewDrinksListRowAlcoholPercent;
        TextView viewDrinksListRowDateAdded;
        TextView viewDrinksListRowDrinkSize;
        View rootView;
        Drink drink;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            viewDrinksListRowAlcoholPercent = itemView.findViewById(R.id.viewDrinksListRowAlcoholPercent);
            viewDrinksListRowDateAdded = itemView.findViewById(R.id.viewDrinksListRowDateAdded);
            viewDrinksListRowDrinkSize = itemView.findViewById(R.id.viewDrinksListRowDrinkSize);
        }
    }
}
