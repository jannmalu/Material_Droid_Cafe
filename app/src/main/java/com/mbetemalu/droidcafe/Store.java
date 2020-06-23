package com.mbetemalu.droidcafe;

public class Store {

    //Declare private member variables
    private final int store_image;
    private String store_title;
    private String store_description;

    //Creating a recipe constructor and pass parameters
    Store(int store_image, String store_title, String store_description){
        this.store_image = store_image;
        this.store_title = store_title;
        this.store_description = store_description;
    }

    //Getters and return the objects
    public int getStore_image(){
        return store_image;
    }

    public String getStore_title(){
        return store_title;
    }

    public String getStore_description(){
        return store_description;
    }
}
