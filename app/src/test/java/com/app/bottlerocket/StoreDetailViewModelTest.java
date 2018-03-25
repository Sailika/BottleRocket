package com.app.bottlerocket;

import android.view.View;

import com.app.bottlerocket.data.FakeRandomStoresGeneratorAPI;
import com.app.bottlerocket.mvvm.model.Store;
import com.app.bottlerocket.mvvm.viewmodel.StoreDetailViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StoreDetailViewModelTest {

    private StoreDetailViewModel storeDetailViewModel;
    private Store store;

    @Before
    public void setUpDetailViewModelTest() {
        store = FakeRandomStoresGeneratorAPI.getStores();
        storeDetailViewModel = new StoreDetailViewModel(store);
    }

    @Test
    public void shouldGetStoreID() throws Exception {
        assertEquals(store.getStoreID(), storeDetailViewModel.getStoreID());
    }

    @Test
    public void shouldGetName() throws Exception {
        assertEquals(store.getName(), storeDetailViewModel.getStoreName());
    }

    @Test
    public void shouldGetPhone() throws Exception {
        assertEquals(store.getPhone(), storeDetailViewModel.getPhoneNumber());
    }


    @Test
    public void shouldGetAddress() throws Exception {
        String fakeAddress = store.getAddress() + " " + store.getCity() + " " + store.getState();
        assertEquals(fakeAddress, storeDetailViewModel.getAddress());
    }

    @Test
    public void givenTheIDVisibilityAsVisible() throws Exception {
        assertEquals(View.VISIBLE, storeDetailViewModel.getStoreID());
    }

}
