package com.bawei.erjileibiao.contract;

import com.bawei.erjileibiao.mvp.IBaseModel;
import com.bawei.erjileibiao.mvp.IBaseView;

import java.util.HashMap;

public interface HomeContract {

    interface IModel extends IBaseModel{
        void getLeft(ImodelCallBack imodelCallBack);
        void getRight(HashMap<String,String> params,ImodelCallBack imodelCallBack);
        interface ImodelCallBack{
            void success(Object data);
            void failure(Throwable throwable);
        }
    }


    interface IView extends IBaseView {
        void success(Object data);
        void failure(Throwable throwable);
    }

    interface IPresenter{
        void getLeft();
        void getRight(HashMap<String,String> params);
    }
}
