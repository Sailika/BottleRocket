package com.app.bottlerocket.mvvm;

/**
 * Created by saili on 3/22/2018.
 */

import android.app.Application;
import android.content.Context;

import com.app.bottlerocket.mvvm.data.StoreFactory;
import com.app.bottlerocket.mvvm.data.StoreService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class StoresApplication extends Application {

    private StoreService storeService;
    private Scheduler scheduler;

    private static StoresApplication get(Context context) {
        return (StoresApplication) context.getApplicationContext();
    }

    public static StoresApplication create(Context context) {
        return StoresApplication.get(context);
    }

    public StoreService getStoreService() {
        if (storeService == null) {
            storeService = StoreFactory.create();
        }

        return storeService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
