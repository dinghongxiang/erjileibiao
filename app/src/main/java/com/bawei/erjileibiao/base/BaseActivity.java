package com.bawei.erjileibiao.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.erjileibiao.mvp.BasePresenter;
import com.bawei.erjileibiao.mvp.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    public P presenter;
    public Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layoutId());

        unbinder=ButterKnife.bind(this);

        presenter=initPresenter();

        if (presenter!=null){
            presenter.attch(this);
        }

        initView();

        initData();
    }

    protected abstract void initData();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (presenter!=null){
            unbinder.unbind();
        }

        if (presenter!=null){
            presenter.death();
        }
    }
}
