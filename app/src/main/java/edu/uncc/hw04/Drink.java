/*
 * Group 22 Homework 03
 * Drink.java
 * Ken Stanley & Stephanie Karp
 */
package edu.uncc.hw04;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Comparator;

public class Drink implements Parcelable, Comparable<Drink>{
    public static final double SIZE_SMALL = 1.0;
    public static final double SIZE_MEDIUM = 5.0;
    public static final double SIZE_LARGE = 12.0;

    public final double drinkSize;
    public final double drinkAlcoholPercent;
    public final long dateTime;

    public Drink(double size, double alcohol) {
        this.drinkSize = size;
        this.drinkAlcoholPercent = alcohol;
        this.dateTime = Calendar.getInstance().getTimeInMillis();
    }

    protected Drink(Parcel in) {
        drinkSize = in.readDouble();
        drinkAlcoholPercent = in.readDouble();
        dateTime = in.readLong();
    }

    public static final Creator<Drink> CREATOR = new Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel in) {
            return new Drink(in);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };

    public String getDrinkAlcoholPercent() {
        String alcPercent = String.valueOf(drinkAlcoholPercent);
        return (alcPercent);
    }

    public String getDrinkDateAdded() {
        String dateAdded = String.valueOf(dateTime);
        return (dateAdded);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(drinkSize);
        parcel.writeDouble(drinkAlcoholPercent);
        parcel.writeLong(dateTime);
    }

    @Override
    public int compareTo(Drink o) {
        return 0;
    }

    public static class AlcoholPercentComparator implements Comparator<Drink> {
        @Override
        public int compare(Drink o1, Drink o2) {
            return o1.getDrinkAlcoholPercent().compareTo(o2.getDrinkAlcoholPercent());
        }
    }

    public static class DateAddedComparator implements Comparator<Drink> {
        @Override
        public int compare(Drink o1, Drink o2) {
            return o1.getDrinkDateAdded().compareTo(o2.getDrinkDateAdded());
        }
    }
}
