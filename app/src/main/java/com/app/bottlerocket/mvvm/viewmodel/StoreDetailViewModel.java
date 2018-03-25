package com.app.bottlerocket.mvvm.viewmodel;

import com.app.bottlerocket.mvvm.model.Store;

public class StoreDetailViewModel {

    private Store store;

    //Constructor..
    public StoreDetailViewModel(Store store) {
        this.store = store;

    }

    public String getStoreName() {
        return store.getName();
    }

    public String getStoreID() {
        return store.getStoreID();
    }


    public String getPhoneNumber() {
        return store.getPhone();
    }


    public String getAddress() {
        return store.getAddress()
                + " "
                + store.getCity()
                + " "
                + store.getState();
    }

//    public String getStoreLogo() {
//        return store.getStoreLogoURL();
//    }
//    @BindingAdapter({"imageUrl"})
//    public static void loadImage(ImageView view, String imageUrl) {
//        // Loads given image
//        Picasso.with(view.getContext())
//                .load(imageUrl)
//                .transform(new BitmapTransform(Constants.MAX_WIDTH, Constants.MAX_HEIGHT))
//                .skipMemoryCache()
//                .resize(Constants.IMG_SIZE, Constants.IMG_SIZE)
//                .centerInside()
//                .into(view);
//
//    }
}
