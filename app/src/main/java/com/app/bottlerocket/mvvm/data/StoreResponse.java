package com.app.bottlerocket.mvvm.data;


import com.app.bottlerocket.mvvm.model.Store;
import java.util.ArrayList;

/**
 * Created by saili on 3/22/2018.
 */

public class StoreResponse {
    private ArrayList<Store> stores;

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "ClassPojo [stores = " + stores + "]";
    }
}
