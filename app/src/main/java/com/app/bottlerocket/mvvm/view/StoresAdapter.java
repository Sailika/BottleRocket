package com.app.bottlerocket.mvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.bottlerocket.R;
import com.app.bottlerocket.databinding.StoreItemBinding;
import com.app.bottlerocket.mvvm.model.Store;
import com.app.bottlerocket.mvvm.viewmodel.StoreItemViewModel;

import java.util.ArrayList;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.StoresAdapterViewHolder> {

    private ArrayList<Store> storesList;

    public StoresAdapter() {
        this.storesList = null;
    }

    @Override
    public StoresAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StoreItemBinding storeItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.store_item,
                        parent, false);
        return new StoresAdapterViewHolder(storeItemBinding);
    }

    @Override
    public void onBindViewHolder(StoresAdapterViewHolder holder, int position) {
        holder.bindPeople(storesList.get(position));
    }

    @Override
    public int getItemCount() {
        return storesList.size();
    }

    public void setStoresList(ArrayList<Store> storesList) {
        this.storesList = storesList;
        notifyDataSetChanged();
    }

    public static class StoresAdapterViewHolder extends RecyclerView.ViewHolder {
        StoreItemBinding mStoreItemBinding;

        //Constructor
        public StoresAdapterViewHolder(StoreItemBinding storeItemBinding) {
            super(storeItemBinding.storeItem);
            this.mStoreItemBinding = storeItemBinding;
        }

        //method to bind the list items
        void bindPeople(Store store) {
            if (mStoreItemBinding.getStoresViewModel() == null) {
                mStoreItemBinding.setStoresViewModel(new StoreItemViewModel(store, itemView.getContext()));
            } else {
                mStoreItemBinding.getStoresViewModel().setStore(store);
            }
        }
    }
}
