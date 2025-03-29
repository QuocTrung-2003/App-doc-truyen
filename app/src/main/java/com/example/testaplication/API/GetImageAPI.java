package com.example.testaplication.API;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetImageAPI {
    @GET("v3/b/67e74d2b8561e97a50f4e8a3")
    Call<APIBlackClover> getBlackCloverImages();
    @GET("v/08041532")
    Call<ApiAttackOnTitan> getImageAttackOnTitan();
}
