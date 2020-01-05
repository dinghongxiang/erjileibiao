package com.bawei.erjileibiao.api;

import com.bawei.erjileibiao.entity.LeftEntity;
import com.bawei.erjileibiao.entity.RightEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ProductService {

    @GET("small/commodity/v1/findCategory")
    Observable<LeftEntity> getLeft();

    @GET("small/commodity/v1/findCommodityByCategory")
    Observable<RightEntity> getRight(@QueryMap HashMap<String,String> params);
}
