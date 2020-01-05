package com.bawei.erjileibiao;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.erjileibiao.adapter.LeftAdapter;
import com.bawei.erjileibiao.adapter.RightAdapter;
import com.bawei.erjileibiao.base.BaseActivity;
import com.bawei.erjileibiao.contract.HomeContract;
import com.bawei.erjileibiao.entity.GreenEntity;
import com.bawei.erjileibiao.entity.LeftEntity;
import com.bawei.erjileibiao.entity.RightEntity;
import com.bawei.erjileibiao.presenter.HomePresenter;
import com.bawei.erjileibiao.utils.RetrofitUtils;
import com.bwei.week2.DaoMaster;
import com.bwei.week2.DaoSession;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.IView {

    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    private DaoSession daoSession;

    @Override
    protected void initData() {

    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {


        EventBus.getDefault().register(this);

        rvLeft.setLayoutManager(new LinearLayoutManager(this));
        rvRight.setLayoutManager(new LinearLayoutManager(this));

        //创建数据库
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"dhx.db");
        SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(sqLiteDatabase);

        daoSession = daoMaster.newSession();

        if (RetrofitUtils.getInstance().hasNet(this)){
            presenter.getLeft();
        }else {
            List<GreenEntity> greenEntities=daoSession.getGreenEntityDao().loadAll();
            if (greenEntities!=null&&greenEntities.size()>0){
                GreenEntity greenEntity=greenEntities.get(0);
                String json=greenEntity.getJson();

                LeftEntity leftEntity= new Gson().fromJson(json,LeftEntity.class);

                LeftAdapter leftAdapter=new LeftAdapter(this,leftEntity.getResult().get(0).getSecondCategoryVo());

                rvLeft.setAdapter(leftAdapter);


                RightEntity rightEntity= new Gson().fromJson(json,RightEntity.class);

                final RightAdapter rightAdapter = new RightAdapter(this, rightEntity.getResult());

                rvRight.setAdapter(rightAdapter);
            }



        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(Object data) {

        if (data instanceof LeftEntity){

            LeftEntity leftEntity= (LeftEntity) data;



            final LeftAdapter leftAdapter = new LeftAdapter(this, leftEntity.getResult().get(0).getSecondCategoryVo());

            rvLeft.setAdapter(leftAdapter);

            leftAdapter.setRevLeftOnClick(new LeftAdapter.RevLeftOnClick() {
                @Override
                public void revOnclick(String id) {
                    EventBus.getDefault().post(id);
                }
            });

            //缓存

            final GreenEntity greenEntity = new GreenEntity();
            final String s = new Gson().toJson(leftEntity);
            greenEntity.setJson(s);
            greenEntity.setTag("分类");
            daoSession.getGreenEntityDao().insert(greenEntity);

        }else if (data instanceof RightEntity){

            RightEntity rightEntity= (RightEntity) data;

            final RightAdapter rightAdapter = new RightAdapter(this, rightEntity.getResult());

            rvRight.setAdapter(rightAdapter);


            final GreenEntity greenEntity = new GreenEntity();
            final String s = new Gson().toJson(rightEntity);
            greenEntity.setJson(s);
            greenEntity.setTag("分类");
            daoSession.getGreenEntityDao().insert(greenEntity);

        }
    }

    @Override
    public void failure(Throwable throwable) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getId(String id){

        HashMap<String,String> params=new HashMap<>();

        params.put("categoryId",id);
        params.put("page","1");
        params.put("count","10");

        presenter.getRight(params);
    }
}
