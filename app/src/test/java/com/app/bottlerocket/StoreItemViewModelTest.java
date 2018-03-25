package com.app.bottlerocket;

import android.content.Intent;
import android.databinding.Observable;
import android.test.mock.MockContext;

import com.app.bottlerocket.data.MockView;
import com.app.bottlerocket.mvvm.model.Store;
import com.app.bottlerocket.mvvm.viewmodel.StoreItemViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StoreItemViewModelTest {

    private static final String STORE_PHONE_TEST = "813-926-7302";
    private static final String STORE_NAME_TEST = "Macy's";

    @Mock
    private MockContext mockContext;

    @Test
    public void shouldGetStoreName() throws Exception {
        Store store = new Store();
        store.setName(STORE_NAME_TEST);
        StoreItemViewModel storeItemViewModel = new StoreItemViewModel(store, mockContext);
        assertEquals(store.getName(), storeItemViewModel.getStoreName());
    }

    @Test
    public void shouldGetStorePhone() throws Exception {
        Store store = new Store();
        store.setPhone(STORE_PHONE_TEST);
        StoreItemViewModel storeItemViewModel = new StoreItemViewModel(store, mockContext);
        assertEquals(store.getPhone(), storeItemViewModel.getPhoneNumber());
    }


    @Test
    public void shouldStartStoreDetailActivityOnItemClick() throws Exception {
        Store store = new Store();
        StoreItemViewModel storeItemViewModel = new StoreItemViewModel(store, mockContext);
        storeItemViewModel.onItemClick(new MockView(mockContext));
        verify(mockContext).startActivity(any(Intent.class));
    }

    @Test
    public void shouldNotifyPropertyChangeWhenSetStore() throws Exception {
        Store store = new Store();
        StoreItemViewModel storeItemViewModel = new StoreItemViewModel(store, mockContext);
        Observable.OnPropertyChangedCallback mockCallback = mock(Observable.OnPropertyChangedCallback.class);
        storeItemViewModel.addOnPropertyChangedCallback(mockCallback);
        storeItemViewModel.setStore(store);
        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }
}
