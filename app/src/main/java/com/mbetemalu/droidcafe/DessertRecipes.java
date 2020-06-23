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
public class DessertRecipes extends Fragment {

    //Declare private member variables
    private RecyclerView dessertRecyclerView;
    private ArrayList<Recipe> dessertRecipeData;
    private RecipeAdapter dessertAdapter;

    public DessertRecipes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dessert_recipes, container, false);

        //Initialize recycler view
        dessertRecyclerView = rootView.findViewById(R.id.recycler_dessert);

        //Set layout for the recycler view
        dessertRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Initialize the array list that will contain data
        dessertRecipeData = new ArrayList<>();

        //Initialize the recipe adapter
        dessertAdapter = new RecipeAdapter(dessertRecipeData,getContext());

        //Set the adapter
        dessertRecyclerView.setAdapter(dessertAdapter);

        //Get data
        initializeData();

        //Implement Swiping and moving of card items
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.DOWN|ItemTouchHelper.UP, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(dessertRecipeData,from,to);
                dessertAdapter.notifyItemMoved(from,to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                dessertRecipeData.remove(viewHolder.getAdapterPosition());
                dessertAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(dessertRecyclerView);

        return rootView;
    }

    private void initializeData() {
        //Get the data you created in the resource file:strings.xml
        TypedArray dessertImages = getResources().obtainTypedArray(R.array.dessert_images);
        String[] dessertTitles = getResources().getStringArray(R.array.dessert_title);
        String[] dessertDescription = getResources().getStringArray(R.array.dessert_descriptions);
        String[] dessertIngredients = getResources().getStringArray(R.array.dessert_ingredients);
        String[] dessertSteps = getResources().getStringArray(R.array.dessert_steps);

        //Clear existing data to avoid duplication
        dessertRecipeData.clear();

        //Create an array list of dessert recipes with title, description and images
        for(int i = 0; i < dessertIngredients.length; i++){
            dessertRecipeData.add(new Recipe(dessertImages.getResourceId(i,-1), dessertTitles[i],dessertDescription[i],dessertIngredients[i], dessertSteps[i]));
        }

        //Clear up data in the typed array
        dessertImages.recycle();

        //Notify the adapter of the change in the data set
        dessertAdapter.notifyDataSetChanged();
    }
}
