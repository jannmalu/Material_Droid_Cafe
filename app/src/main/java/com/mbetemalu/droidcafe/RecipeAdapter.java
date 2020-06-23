package com.mbetemalu.droidcafe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{
    //Declare the private member variables recipe data and the context
    private ArrayList<Recipe> recipe_data;
    private Context myContext;

    //Create constructor to pass in the recipe data and context

    RecipeAdapter(ArrayList<Recipe> mRecipeData, Context context){
        this.myContext = context;
        this.recipe_data = mRecipeData;
    }
    //Implement the method onCreateViewHolder for creating view holder objects
    //@param parent the view group of which the view object will be added after it is bound to the adapter position
    //@param viewType of the new view
    //@return the newly created view holder

    @NonNull
    @Override

    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create new view holder
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.recipe_list_item,parent,false));
    }

    //Required for binding the view to the data
    //@Holder viewHolder which the data should be put
    //@position which is the adapter position

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {

        //Get the current view object using its position and populate it with data
        Recipe currentRecipe = recipe_data.get(position);

        //Populate the current view wih data
        holder.bindTo(currentRecipe);
    }

    //Return the size of the data set

    @Override
    public int getItemCount() {
        return recipe_data.size();
    }

    //create the viewHolder class that represents each and every row in te recycler view
    public class ViewHolder extends RecyclerView.ViewHolder {

        //create a constructor for the viewHolder used in onCreateViewHolder() method
        //@param itemView rootView of the recipe list item layout

        //Declare the private member variables that the viewHolder will hold
        private ImageView myRecipeImage;
        private TextView myRecipeTitle;
        private TextView myRecipeDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myRecipeImage = itemView.findViewById(R.id.recipe_image);
            myRecipeTitle = itemView.findViewById(R.id.recipe_title);
            myRecipeDescription = itemView.findViewById(R.id.recipe_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Recipe currentDessert = recipe_data.get(position);
                    Intent recipeIntent = new Intent(myContext,RecipeActivity.class);
                    recipeIntent.putExtra("recipeId", position);
                    recipeIntent.putExtra("recipeTitle", currentDessert.getRecipe_title());
                    recipeIntent.putExtra("recipeImage", currentDessert.getRecipe_image());
                    recipeIntent.putExtra("recipeDescription", currentDessert.getRecipe_description());
                    recipeIntent.putExtra("recipeIngredients", currentDessert.getRecipe_ingredients());
                    recipeIntent.putExtra("recipeSteps", currentDessert.getRecipe_steps());
                    myContext.startActivity(recipeIntent);
                }
            });
        }

        //Create a method to bind current view with data
        public void bindTo(Recipe currentRecipe) {
            //Populate view with data
            //for loading the image use the glide library so as to prevent problems of app crashing as a result of loading many images of different resolutions

            Glide.with(myContext).load(currentRecipe.getRecipe_image()).into(myRecipeImage);
            myRecipeTitle.setText(currentRecipe.getRecipe_title());
            myRecipeDescription.setText(currentRecipe.getRecipe_description());
        }
    }
}
