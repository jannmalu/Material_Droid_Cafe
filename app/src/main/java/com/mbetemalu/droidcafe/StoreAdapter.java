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

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    //Declare the private member variables store data and the context
    private ArrayList<Store> store_data;
    private Context myContext;

    //Create constructor to pass in the recipe data and context

     StoreAdapter(ArrayList<Store> mStoreData, Context context){
        this.myContext = context;
        this.store_data = mStoreData;
    }

    //Implement the method onCreateViewHolder for creating view holder objects
    //@param parent the view group of which the view object will be added after it is bound to the adapter position
    //@param viewType of the new view
    //@return the newly created view holder


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create new view holder
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.stores_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the current view object using its position and populate it with data
        Store currentStore = store_data.get(position);

        //Populate the current view wih data
        holder.bindTo(currentStore);
    }

    @Override
    public int getItemCount() {
        return store_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         //create a constructor for the viewHolder used in onCreateViewHolder() method
        //@param itemView rootView of the recipe list item layout

        //Declare the private member variables that the viewHolder will hold
        private ImageView myStoreImage;
        private TextView myStoreTitle;
        private TextView myStoreDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myStoreImage = itemView.findViewById(R.id.store_image);
            myStoreTitle = itemView.findViewById(R.id.store_title);
            myStoreDescription = itemView.findViewById(R.id.store_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int storePosition = getAdapterPosition();
                    Store currentStore = store_data.get(storePosition);
                    if(storePosition == 0){
                        Intent galleriaIntent = new Intent(myContext,Galleria.class);
                        galleriaIntent.putExtra("gTitle", currentStore.getStore_title());
                        galleriaIntent.putExtra("gImage", currentStore.getStore_image());
                        galleriaIntent.putExtra("gDescription", currentStore.getStore_description());
                        myContext.startActivity(galleriaIntent);
                    }else{
                        Toast.makeText(myContext, "Create an activity for the store", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        public void bindTo(Store currentStore) {
            //Populate view with data
            //for loading the image use the glide library so as to prevent problems of app crashing as a result of loading many images of different resolutions

            Glide.with(myContext).load(currentStore.getStore_image()).into(myStoreImage);
            myStoreTitle.setText(currentStore.getStore_title());
            myStoreDescription.setText(currentStore.getStore_description());
        }
    }
}
