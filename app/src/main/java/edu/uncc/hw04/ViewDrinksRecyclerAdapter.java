package edu.uncc.hw04;

import android.content.Context;
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
    LayoutInflater mInflater;
    iViewDrinks iListener;
    Context layout;

    public ViewDrinksRecyclerAdapter(Context layout, ArrayList<Drink> data, iViewDrinks iListener) {
        this.drinks = data;
        this.mInflater = LayoutInflater.from(layout);
        this.iListener = iListener;
        this.layout = layout;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_viewdrinks_list_row, parent, false);

        return new DrinkViewHolder(view, iListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drink drink = drinks.get(position);

        holder.viewDrinksListRowAlcoholPercent.setText(layout.getString(R.string.view_drink_list_row_alcohol_percent, drink.drinkAlcoholPercent));

        holder.viewDrinksListRowDrinkSize.setText(layout.getString(R.string.view_drink_list_row_drink_size, drink.drinkSize));

        SimpleDateFormat dateFormat = new SimpleDateFormat(layout.getString(R.string.view_drink_list_row_date_format), Locale.US);
        Date drinkDate = Calendar.getInstance().getTime();
        drinkDate.setTime(drink.dateTime);

        holder.viewDrinksListRowDateAdded.setText(layout.getString(R.string.view_drink_list_row_date_added, dateFormat.format(drinkDate)));
    }

    @Override
    public int getItemCount() {
        return this.drinks.size();
    }

    public static class DrinkViewHolder extends RecyclerView.ViewHolder {
        TextView viewDrinksListRowAlcoholPercent;
        TextView viewDrinksListRowDateAdded;
        TextView viewDrinksListRowDrinkSize;
        iViewDrinks iListener;

        public DrinkViewHolder(@NonNull View itemView, iViewDrinks iListener) {
            super(itemView);
            this.iListener = iListener;

            viewDrinksListRowAlcoholPercent = itemView.findViewById(R.id.viewDrinksListRowAlcoholPercent);
            viewDrinksListRowDateAdded = itemView.findViewById(R.id.viewDrinksListRowDateAdded);
            viewDrinksListRowDrinkSize = itemView.findViewById(R.id.viewDrinksListRowDrinkSize);

            itemView.findViewById(R.id.viewDrinksListRowTrashButton).setOnClickListener(v -> {
                int currentDrinkNumber = getLayoutPosition();
                iListener.trashButtonClicked(currentDrinkNumber);
            });
        }
    }

    public interface iViewDrinks {
        void trashButtonClicked(int currentDrinkNumber);
    }
}
