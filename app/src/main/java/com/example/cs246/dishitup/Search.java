package com.example.cs246.dishitup;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.Arrays;

/**
 * allows the user to rearrange the list of recipes
 *
 * @author Trenton
 */
public class Search extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    Spinner SortS;
    String[] items =  {"select the sort","Rating", "A-Z", "Z-A", "Time Short to Long", "Time Long to Short"};

    
    private static final String TAG_SEARCH = "search mode";

    /**
     * this runs a test on the TAG_SEARCH
     * @exception this tells you that i == 2
     *
     */
    public void testLog(){
        int i  = 2;
        Log.i(TAG_SEARCH, "i =" + i);

        Log.e(TAG_SEARCH, " i =" + i);
    }

    /**
     * calls database and sorts by rating
     */
     private void sortByRateing(){
       //  RecipeCard [] Filter = Roledex.getFilteredList();

    }

    /**
     * calls database, sorts alphabetically
     */
    private void sortByAZ(){
       // RecipeCard [] Filter = Roledex.getFilteredList();
       // Arrays.sort(Filter.getName());

    }

    /**
     * calls database, sorts reverse alphabetically
     */
    private void sortByZA(){
       // RecipeCard [] Filter = Roledex.getFilteredList();


    }

    /**
     * calls database, sort time short to long
     */
    private void sortTimeShortLong(){
       // RecipeCard [] Filter = Roledex.getFilteredList();

    }

    /**
     * calls database, long to short
     */
    private void sortTimeLongSort(){
        //RecipeCard [] Filter = Roledex.getFilteredList();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SortS = (Spinner) findViewById(R.id.SortS);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.sort, android.R.layout.simple_spinner_item);
        ArrayAdapter <String> adapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, items);
        SortS.setAdapter(adapter);
        SortS.setOnItemSelectedListener(this);

        //testLog();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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

    public void goToMenu(View view) {
        finish();
    }


    public void goToDummyRecipe(View view) {
        // first, just go to the recipe and see what it looks like
        Intent intent = new Intent(Search.this, Recipe.class);
        // set the global recipe
        GlobalRecipe.getInstance().recipeCard.setName("Dummy");
        GlobalRecipe.getInstance().recipeCard.setCookTime(60);
        GlobalRecipe.getInstance().recipeCard.addCategory("Dummy");
        GlobalRecipe.getInstance().recipeCard.addIngredient("12 cups", "dummies");
        GlobalRecipe.getInstance().recipeCard.setRating(3);
        GlobalRecipe.getInstance().recipeCard.setDirections("Dummy\nDummy\nDummy");
        GlobalRecipe.getInstance().recipeCard.setPictureRef("@drawable/placeholder_image");
        GlobalRecipe.getInstance().recipeCard.setId(0);
        GlobalRecipe.getInstance().recipeCard.setComment("Dummy comment");

        // start the new activity
        startActivity(intent);
    }

    //these are for what happens when you use the spinner must be overloaded

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        Toast.makeText(this, "you selected " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void spinnerSelect(){

        String spinVar = String.valueOf(SortS.getSelectedItem());
        //select the sort","Rating", "A-Z", "Z-A", "Time Short to Long", "Time Long to Short
        if(spinVar.equals("select the sort")){

        }else if(spinVar.equals("Rating")){
            sortByRateing();

        }else if(spinVar.equals("Rating")){
            sortByAZ();

        }else if(spinVar.equals("Rating")){
            sortByZA();

        }else if(spinVar.equals("Rating")){

        }else if(spinVar.equals("Rating")){

        }else
        {
            System.out.println("panic and cry this is never suposed to happen");
        }

    }
}
