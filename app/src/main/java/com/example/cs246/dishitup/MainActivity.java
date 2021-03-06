package com.example.cs246.dishitup;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.List;

/**
 * allows you to navigate the app, creates the database
 *
 * @author Trenton Kevin Jason
 */
public class MainActivity extends ActionBarActivity {

    DatabaseControl recipeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FilterData.initializeFilter(getApplicationContext());
        FilterData.clearFilter();

        recipeDatabase = new DatabaseControl(getApplicationContext());  //this gets or database of recipeCards
        if (recipeDatabase.getRecipeCount() < 1){
            DefaultRecipes defaults = new DefaultRecipes();
            List<RecipeCard> defaultCards = defaults.getDefaultCards();
            for(RecipeCard recipe : defaultCards) {
                recipeDatabase.createRecipe(recipe);
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchSearch(View view) {
        Intent intent = new Intent(MainActivity.this, Search.class);
        startActivity(intent);
    }

    /**
     * Passes a blank recipe card to the AddNewRecipe activity.
     * @param view the current view
     */
    public void launchAddNewRecipe(View view) {
        Intent intent = new Intent(MainActivity.this, AddNewRecipe.class);

        intent.putExtra("RecipeCard", new RecipeCard());

        // start the new activity
        startActivity(intent);
    }

    public void goToShoppingCart(View view) {
        Intent intent = new Intent(MainActivity.this, ShoppingCart.class);
        startActivity(intent);
    }
}
