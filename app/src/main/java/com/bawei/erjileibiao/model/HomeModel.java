package com.bawei.erjileibiao.model;

import com.bawei.erjileibiao.api.ProductService;
import com.bawei.erjileibiao.contract.HomeContract;
import com.bawei.erjileibiao.entity.LeftEntity;
import com.bawei.erjileibiao.entity.RightEntity;
import com.bawei.erjileibiao.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeModel  implements HomeContract.IModel {
    @Override
    public void getLeft(ImodelCallBack imodelCallBack) {

        RetrofitUtils.getInstance().createServer(ProductService.class)
                .getLeft()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LeftEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LeftEntity leftEntity) {
                        imodelCallBack.success(leftEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        imodelCallBack.failure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getRight(HashMap<String, String> params, ImodelCallBack imodelCallBack) {


        RetrofitUtils.getInstance().createServer(ProductService.class)
                .getRight(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RightEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RightEntity rightEntity) {
                        imodelCallBack.success(rightEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
