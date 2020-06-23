package com.mbetemalu.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecipeActivity extends AppCompatActivity {

    private ImageView recipeImage;
    private TextView recipeTitle;
    private TextView recipeIngredients;
    private TextView recipeStep;

    private int recipeId;
    private String title;
    private String ingredients;
    private String steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        recipeImage = findViewById(R.id.recipe_view_image);
        recipeTitle = findViewById(R.id.recipe_view_title);
        recipeIngredients = findViewById(R.id.recipe_view_ingredients);
        recipeStep = findViewById(R.id.recipe_view_steps);

        this.recipeId = getIntent().getIntExtra("recipeImage", -1);
        this.title = getIntent().getStringExtra("recipeTitle");
        this.ingredients = getIntent().getStringExtra("recipeIngredients");
        this.steps = getIntent().getStringExtra("recipeSteps");

        setData();
    }

    private void setData() {
        Glide.with(getApplicationContext()).load(this.recipeId).into(recipeImage);
        this.recipeTitle.setText(this.title);
        this.recipeIngredients.setText(this.ingredients);
        this.recipeStep.setText(this.steps);
    }
}
