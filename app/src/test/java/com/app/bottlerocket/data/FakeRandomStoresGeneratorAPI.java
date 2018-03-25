package com.app.bottlerocket.data;

import com.app.bottlerocket.mvvm.model.Store;

import java.util.ArrayList;

public class FakeRandomStoresGeneratorAPI {
    private static final String STREET_TEST = "7803 Citrus Park Town Center Mall";
    private static final String CITY_TEST = "Tampa";
    private static final String NAME_TEST = "Macy's";
    private static final String LATITUDE = "28.078152";
    private static final String ZIPCODE_TEST = "33625";
    private static final String PICTURE_TEST = "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/images/macys.jpeg";
    private static final String PHONE_TEST = "813-926-7302";
    private static final String STATE_TEST = "FL";
    private static final String ID = "1238";
    private static final String LONGITUDE = "-82.583401";

    public static ArrayList<Store> getStoresList() {
        ArrayList<Store> stores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stores.add(getStores());
        }
        return stores;
    }

    public static Store getStores() {
        Store stores = new Store();
        stores.setAddress(STREET_TEST);
        stores.setCity(CITY_TEST);
        stores.setLatitude(LATITUDE);
        stores.setLongitude(LONGITUDE);
        stores.setName(NAME_TEST);
        stores.setPhone(PHONE_TEST);
        stores.setState(STATE_TEST);
        stores.setZipcode(ZIPCODE_TEST);
        stores.setStoreLogoURL(PICTURE_TEST);
        stores.setStoreID(ID);

        return stores;
    }
}
