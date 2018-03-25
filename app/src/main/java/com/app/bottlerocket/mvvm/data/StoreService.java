package com.app.bottlerocket.mvvm.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface StoreService {

  @GET
  Observable<StoreResponse> fetchStores(@Url String url);

}
