package com.mbetemalu.droidcafe;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastriesRecipes extends Fragment {

    private RecyclerView pastriesRecyclerView;
    private ArrayList<Recipe> pastriesRecipeData;
    private RecipeAdapter pastriesAdapter;

    public PastriesRecipes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pastries_recipes, container, false);

        //Initialize recycler view
        pastriesRecyclerView = rootView.findViewById(R.id.recycler_pastries);

        //Set layout for the recycler view
        pastriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Initialize the array list that will contain data
        pastriesRecipeData = new ArrayList<>();

        //Initialize the recipe adapter
        pastriesAdapter = new RecipeAdapter(pastriesRecipeData,getContext());

        //Set the adapter
        pastriesRecyclerView.setAdapter(pastriesAdapter);

        //Get data
        initializeData();

        //Implement Swiping and moving of card items
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.DOWN|ItemTouchHelper.UP, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(pastriesRecipeData,from,to);
                pastriesAdapter.notifyItemMoved(from,to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                pastriesRecipeData.remove(viewHolder.getAdapterPosition());
                pastriesAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(pastriesRecyclerView);

        return rootView;


    }

    private void initializeData() {

        //Get the data you created in the resource file:strings.xml
        String[] pastriesTitles = getResources().getStringArray(R.array.pastries_title);
        String[] pastriesDescription= getResources().getStringArray(R.array.pastries_descriptions);
        TypedArray pastriesImages = getResources().obtainTypedArray(R.array.pastries_images);
        String[] pastriesIngredients = getResources().getStringArray(R.array.pastries_ingredients);
        String[] pastriesSteps = getResources().getStringArray(R.array.pastries_steps);

        //Clear existing data to avoid duplication
        pastriesRecipeData.clear();

        //Create an array list of dessert recipes with title, description and images
        for(int i = 0; i < pastriesIngredients.length; i++){
            pastriesRecipeData.add(new Recipe(pastriesImages.getResourceId(i,0), pastriesTitles[i],pastriesDescription[i],pastriesIngredients[i], pastriesSteps[i]));
        }

        //Clear up data in the typed array
        pastriesImages.recycle();

        //Notify the adapter of the change in the data set
        pastriesAdapter.notifyDataSetChanged();
    }
}
