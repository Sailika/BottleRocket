package com.app.bottlerocket.mvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.app.bottlerocket.R;
import com.app.bottlerocket.mvvm.Constants;
import com.app.bottlerocket.mvvm.StoresApplication;
import com.app.bottlerocket.mvvm.data.StoreResponse;
import com.app.bottlerocket.mvvm.data.StoreService;
import com.app.bottlerocket.mvvm.model.Store;

import java.util.ArrayList;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class StoresViewModel extends Observable {

    public ObservableInt mProgress;
    public ObservableInt mRecycler;
    public ObservableInt mLabel;
    public ObservableField<String> messageLabel;

    private ArrayList<Store> storesList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public StoresViewModel(@NonNull Context context) {

        this.context = context;
        this.storesList = new ArrayList<>();
        mProgress = new ObservableInt(View.GONE);
        mRecycler = new ObservableInt(View.GONE);
        mLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.loading_stores));
    }

    public void onClickFabLoad(View view) {
        initializeViews();
        fetchStoresList();
    }

    public void initializeViews() {
        mLabel.set(View.GONE);
        mRecycler.set(View.GONE);
        mProgress.set(View.VISIBLE);
    }

    public void fetchStoresList() {
        StoresApplication storesApplication = StoresApplication.create(context);
        StoreService storeService = storesApplication.getStoreService();

        Disposable disposable = storeService.fetchStores(Constants.STORES_URL)
                .subscribeOn(storesApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<StoreResponse>() {
                    @Override
                    public void accept(StoreResponse storeResponse) throws Exception {
                        changeStoresDataSet(storeResponse.getStores());
                        mProgress.set(View.GONE);
                        mLabel.set(View.GONE);
                        mRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_loading_stores));
                        mProgress.set(View.GONE);
                        mLabel.set(View.VISIBLE);
                        mRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void changeStoresDataSet(ArrayList<Store> stores) {
        storesList.addAll(stores);
        setChanged();
        notifyObservers();
    }

    public ArrayList<Store> getStoresList() {
        return storesList;
    }


    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
