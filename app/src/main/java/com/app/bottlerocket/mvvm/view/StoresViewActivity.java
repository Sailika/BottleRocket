package com.app.bottlerocket.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.app.bottlerocket.R;
import com.app.bottlerocket.databinding.StoresViewActivityBinding;
import com.app.bottlerocket.mvvm.viewmodel.StoresViewModel;

import java.util.Observable;
import java.util.Observer;

public class StoresViewActivity extends AppCompatActivity implements Observer {

    private StoresViewActivityBinding storesViewActivityBinding;
    private StoresViewModel storesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setSupportActionBar(storesViewActivityBinding.toolbar);
        setupStoreListView(storesViewActivityBinding.listStores);
        setupObserver(storesViewModel);
    }

    //initialize the view model and the layout ...
    private void initDataBinding() {
        storesViewActivityBinding = DataBindingUtil.setContentView(this, R.layout.stores_view_activity);
        storesViewModel = new StoresViewModel(this);
        storesViewActivityBinding.setMainViewModel(storesViewModel);
    }

    //set Adapter to the List View...
    private void setupStoreListView(RecyclerView storeList) {
        StoresAdapter adapter = new StoresAdapter();
        storeList.setAdapter(adapter);
        storeList.setLayoutManager(new LinearLayoutManager(this));
    }

    //setup observer to get updates for chnaging the ui on service complete....
    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        storesViewModel.reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //on Refresh Icon selected, send ViewModel request to fetch the data...
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh) {
            storesViewModel.initializeViews();
            storesViewModel.fetchStoresList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //After Service is completed, Update the UI...
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof StoresViewModel) {
            StoresAdapter storesAdapter = (StoresAdapter) storesViewActivityBinding.listStores.getAdapter();
            StoresViewModel storesViewModel = (StoresViewModel) observable;
            storesAdapter.setStoresList(storesViewModel.getStoresList());
        }
    }
}
