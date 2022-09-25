/*
 * Group 22 Homework 03
 * MainActivity.java
 * Ken Stanley & Stephanie Karp
 */
package edu.uncc.hw04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import edu.uncc.hw04.fragments.AddDrinkFragment;
import edu.uncc.hw04.fragments.BACCalculatorFragment;
import edu.uncc.hw04.fragments.SetProfileFragment;
import edu.uncc.hw04.fragments.ViewDrinksFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.uncc.hw04.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements BACCalculatorFragment.iListener, AddDrinkFragment.iListener, SetProfileFragment.iListener, ViewDrinksFragment.iListener {
    ActivityMainBinding binding;
    private ArrayList<edu.uncc.hw04.Drink> drinks;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new BACCalculatorFragment(), "calculator")
                .commit();

        drinks = new ArrayList<>();
    }

    @Override
    public void bacCalculatorButtonResetClicked() {
        BACCalculatorFragment fragment = (BACCalculatorFragment)getSupportFragmentManager().findFragmentByTag("calculator");

        if (fragment != null) {
            profile = null;
            drinks.clear();
            fragment.resetCalculator();
        }
    }

    @Override
    public void bacCalculatorButtonSetClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new SetProfileFragment(), "set profile")
                .addToBackStack("set profile")
                .commit();
    }

    @Override
    public void bacCalculatorButtonAddDrinkClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new AddDrinkFragment(), "add drink")
                .addToBackStack("add drink")
                .commit();
    }

    @Override
    public void bacCalculatorButtonViewDrinksClicked(ArrayList<Drink> drinks) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new ViewDrinksFragment(drinks), "view drinks")
                .addToBackStack("view drinks")
                .commit();
    }

    @Override
    public void addDrinkButtonSetClicked(Drink drink) {
        BACCalculatorFragment fragment = (BACCalculatorFragment)getSupportFragmentManager().findFragmentByTag("calculator");

        getSupportFragmentManager()
                .popBackStack("add drink", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (fragment != null) {
            drinks.add(drink);
            fragment.updateDrinks(drinks, profile);
        }
    }

    @Override
    public void addDrinkButtonCancelClicked() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void setProfileButtonCancelClicked() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void profileSet(Profile profile) {
        BACCalculatorFragment fragment = (BACCalculatorFragment)getSupportFragmentManager().findFragmentByTag("calculator");

        getSupportFragmentManager()
                .popBackStack("set profile", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (fragment != null) {
            this.profile = profile;
            drinks.clear();

            fragment.updateDrinks(drinks, profile);
        }
    }


    @Override
    public void sortByAlcoholPercentAsc(ArrayList<Drink> drinks) {
        Collections.sort(drinks, new Comparator<Drink>() {
            @Override
            public int compare(Drink o1, Drink o2) {
                return o1.getDrinkAlcoholPercent().compareTo(o2.getDrinkAlcoholPercent());
            }
        });
    }

    @Override
    public void sortByDateAddedAsc(ArrayList<Drink> drinks) {
        Collections.sort(drinks, new Comparator<Drink>() {
            @Override
            public int compare(Drink o1, Drink o2) {
                return o1.getDrinkDateAdded().compareTo(o2.getDrinkDateAdded());
            }
        });
    }

    @Override
    public void viewDrinksButtonCloseClicked(ArrayList<Drink> drinks) {
        BACCalculatorFragment fragment = (BACCalculatorFragment)getSupportFragmentManager().findFragmentByTag("calculator");

        getSupportFragmentManager()
                .popBackStack("view drinks", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (fragment != null) {
            this.drinks = drinks;
            fragment.updateDrinks(drinks, profile);
        }
    }

}