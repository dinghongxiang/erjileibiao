package com.bawei.erjileibiao.presenter;

import com.bawei.erjileibiao.contract.HomeContract;
import com.bawei.erjileibiao.model.HomeModel;
import com.bawei.erjileibiao.mvp.BasePresenter;

import java.util.HashMap;

public class HomePresenter extends BasePresenter<HomeModel, HomeContract.IView> implements HomeContract.IPresenter {
    @Override
    public void getLeft() {
        model.getLeft(new HomeContract.IModel.ImodelCallBack() {
            @Override
            public void success(Object data) {
                getView().success(data);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }

    @Override
    public void getRight(HashMap<String, String> params) {

        model.getRight(params, new HomeContract.IModel.ImodelCallBack() {
            @Override
            public void success(Object data) {
                getView().success(data);
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
    }

    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }
}
