package com.app.bottlerocket.mvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.app.bottlerocket.mvvm.BitmapTransform;
import com.app.bottlerocket.mvvm.model.Store;
import com.app.bottlerocket.mvvm.view.StoreDetailActivity;
import com.squareup.picasso.Picasso;

import static com.app.bottlerocket.mvvm.Constants.IMG_SIZE;
import static com.app.bottlerocket.mvvm.Constants.MAX_HEIGHT;
import static com.app.bottlerocket.mvvm.Constants.MAX_WIDTH;


public class StoreItemViewModel extends BaseObservable {

    private Store store;
    private Context context;

    public StoreItemViewModel(Store store, Context context) {
        this.store = store;
        this.context = context;
    }

    public String getStoreName() {
        return store.getName();
    }

    public String getPhoneNumber() {
        return store.getPhone();
    }

    public String getAddress() {
        return store.getAddress();
    }


    public String getPictureProfile() {
        return store.getStoreLogoURL();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {

        Picasso.with(imageView.getContext())
                .load(url)
                .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                .skipMemoryCache()
                .resize(IMG_SIZE, IMG_SIZE)
                .centerInside()
                .into(imageView);
    }

    public void onItemClick(View view) {
        context.startActivity(StoreDetailActivity.launchDetail(view.getContext(), store));
    }

    public void setStore(Store store) {
        this.store = store;
        notifyChange();
    }
}
