package com.example.cs246.dishitup;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class displays and temporarily stores the data for a recipe.
 * Created by Kevin on 2/25/2015.
 */
public class RecipeCard implements Parcelable {
    private String name;
    private int rating;
    private int id;
    private String comment;
    private String pictureRef;
    private int cookTime; // note: this is time in minutes
    private List<String> amounts;
    private List<String> ingredients;
    private String directions;
    private Set<String> categories;

    private static final String RECIPE_CARD_TAG = "RecipeCard";

    RecipeCard() {
        name = null;
        rating = -1;
        id = -1;
        comment = null;
        pictureRef = "@drawable/placeholder_image";
        cookTime = -1;
        ingredients = new ArrayList<>();
        amounts = new ArrayList<>();
        directions = null;
        categories = new TreeSet<>();

        Log.i(RECIPE_CARD_TAG, "Created a blank RecipeCard.");
    }



    public String getName() {return name;}
    public int getRating()  {return rating;}
    public int getId() {return id;}
    public String getComment() {return comment;}
    public String getPictureRef() {return pictureRef;}
    public int getCookTime() { return cookTime;} // gets it in minutes
    public List<String> getIngredients() {return ingredients;}
    public List<String> getAmounts() {return amounts;}
    public String getDirections() {return directions;}
    public Set<String> getCategories()  {return categories;}

    public boolean hasCategory(String category) {return categories.contains(category);}

    public void setName(String name) {this.name = name;}
    public void setRating(int rating) {this.rating = rating;}
    public void setId(int id) {this.id = id;}
    public void setComment(String comment) {this.comment = comment;}
    public void setPictureRef(String pictureRef) {this.pictureRef = pictureRef;}
    public void setCookTime(int time) { cookTime = time;}
    public void addIngredient(String amount, String ingredient) {
        amounts.add(amount);
        ingredients.add(ingredient);
        if (amounts.size() != ingredients.size())
            Log.wtf(RECIPE_CARD_TAG, "Ingredients and Amounts are different lengths!");
    }

    /**
     * Removes all instances of the ingredient (and its associated amount) from the RecipeCard
     * In cases where multiple instances of the same ingredient exist, all instances will be removed.
     * @param ingredient The ingredient to remove
     */
    public void removeIngredient(String ingredient) {
        if (ingredients.contains(ingredient)) {
            boolean done = false;

            while (!done) {
                done = true;
                for (int i = 0; (i < ingredients.size()) && (done); i++) {
                    if (ingredients.get(i).equals(ingredient)) {
                        ingredients.remove(i);
                        amounts.remove(i);
                        done = false;
                    }
                }

            }
        }
    }

    public void setDirections(String directions) {this.directions = directions;}
    public void addCategory(String category)  {categories.add(category);}
    public void removeCategory(String category) {
        if (hasCategory(category)) {
            categories.remove(category);
        } else {
            Log.i(RECIPE_CARD_TAG, "Category " + category + " wasn't in the list.");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes the contents of the RecipeCard to the destination Parcel
     * @param dest Destination Parcel
     * @param flags ???
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.rating);
        dest.writeInt(this.cookTime);
        dest.writeString(this.pictureRef);
        dest.writeString(this.comment);
        dest.writeString(this.directions);
        dest.writeList((List) this.categories);
        dest.writeList(this.amounts);
        dest.writeList(this.ingredients);

    }

    /**
     * Non-default constructor to create a RecipeCard
     * @param parcel Source Parcel
     */
    public RecipeCard(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.rating = parcel.readInt();
        this.cookTime = parcel.readInt();
        this.pictureRef = parcel.readString();
        this.comment = parcel.readString();
        this.directions = parcel.readString();
        this.categories = (Set<String>) parcel.readArrayList(null);
        this.amounts = parcel.readArrayList(null);
        this.ingredients = parcel.readArrayList(null);
    }

    // Method to recreate a Question from a Parcel
    public static Creator<RecipeCard> CREATOR = new Creator<RecipeCard>() {

        @Override
        public RecipeCard createFromParcel(Parcel source) {
            return new RecipeCard(source);
        }

        @Override
        public RecipeCard[] newArray(int size) {
            return new RecipeCard[size];
        }

    };




}
