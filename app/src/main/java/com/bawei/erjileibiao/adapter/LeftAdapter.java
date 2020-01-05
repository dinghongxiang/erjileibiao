package com.bawei.erjileibiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.erjileibiao.R;
import com.bawei.erjileibiao.entity.LeftEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.VH> {



    private Context context;
    private List<LeftEntity.ResultBean.SecondCategoryVoBean> list;

    public LeftAdapter(Context context, List<LeftEntity.ResultBean.SecondCategoryVoBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View inflate = View.inflate(context, R.layout.rev_left, null);

        final VH vh = new VH(inflate);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.name.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revLeftOnClick.revOnclick(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        public VH(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }


    private RevLeftOnClick revLeftOnClick;

    public void setRevLeftOnClick(RevLeftOnClick revLeftOnClick) {
        this.revLeftOnClick = revLeftOnClick;
    }

    public interface RevLeftOnClick{
        void revOnclick(String id);
    }
}
