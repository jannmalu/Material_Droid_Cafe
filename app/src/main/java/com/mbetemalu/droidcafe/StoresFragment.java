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
public class StoresFragment extends Fragment {

    //Declare private member variables
    private RecyclerView storeRecyclerView;
    private ArrayList<Store> storeData;
    private StoreAdapter storeAdapter;

    public StoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stores, container, false);

        //Initialize recycler view
        storeRecyclerView = rootView.findViewById(R.id.recycler_stores);

        //Set layout for the recycler view
        storeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Initialize the array list that will contain data
        storeData = new ArrayList<>();

        //Initialize the recipe adapter
        storeAdapter = new StoreAdapter(storeData,getContext());

        //Set the adapter
        storeRecyclerView.setAdapter(storeAdapter);

        //Get data
        initializeData();

        //Implement Swiping and moving of card items
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.DOWN|ItemTouchHelper.UP, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(storeData,from,to);
                storeAdapter.notifyItemMoved(from,to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                storeData.remove(viewHolder.getAdapterPosition());
                storeAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(storeRecyclerView);

        return rootView;
    }

    private void initializeData() {
        //Get the data you created in the resource file:strings.xml
        String[] storeTitles = getResources().getStringArray(R.array.store_titles);
        String[] storeDescriptions = getResources().getStringArray(R.array.store_descriptions);
        TypedArray storeImages = getResources().obtainTypedArray(R.array.stores_images);

        //Clear existing data to avoid duplication
        storeData.clear();

        //Create an array list of dessert recipes with title, description and images
        for(int i = 0; i < storeTitles.length; i++){
            storeData.add(new Store(storeImages.getResourceId(i,0), storeTitles[i],storeDescriptions[i]));
        }

        //Clear up data in the typed array
        storeImages.recycle();

        //Notify the adapter of the change in the data set
        storeAdapter.notifyDataSetChanged();
    }
}
