package com.app.bottlerocket;

import android.test.mock.MockContext;
import android.view.View;

import com.app.bottlerocket.data.FakeRandomStoresGeneratorAPI;
import com.app.bottlerocket.mvvm.data.StoreService;
import com.app.bottlerocket.mvvm.model.Store;
import com.app.bottlerocket.mvvm.viewmodel.StoresViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StoresViewModelTest {

    private static final String URL_TEST = "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/stores.json";

    @Mock
    private StoreService storeService;
    @Mock
    private MockContext mockContext;

    private StoresViewModel storesViewModel;

    @Before
    public void setUpMainViewModelTest() {
        storesViewModel = new StoresViewModel(mockContext);
    }

    @Test
    public void simulateStoresListFromApi() throws Exception {
        List<Store> stores = FakeRandomStoresGeneratorAPI.getStoresList();
        doReturn(Observable.just(stores)).when(storeService).fetchStores(URL_TEST);
    }

    @Test
    public void ensureTheViewsAreInitializedCorrectly() throws Exception {
        storesViewModel.initializeViews();
        assertEquals(View.GONE, storesViewModel.mLabel.get());
        assertEquals(View.GONE, storesViewModel.mRecycler.get());
        assertEquals(View.VISIBLE, storesViewModel.mProgress.get());
    }
}
