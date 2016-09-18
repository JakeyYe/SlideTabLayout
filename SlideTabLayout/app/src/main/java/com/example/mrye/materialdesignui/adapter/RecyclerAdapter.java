package com.example.mrye.materialdesignui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrye.materialdesignui.ModelBean;
import com.example.mrye.materialdesignui.R;

import java.util.List;

/**
 * Created by Mr.Ye on 2016/8/15.
 */
                              //RecyclerView适配器，RecyclerView已经封装好了ViewHolder
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<ModelBean> list;
    private Resources res;
    private OnItemClickListener listener;

    public RecyclerAdapter(Context context,List<ModelBean> list){
        this.context=context;
        this.list=list;
        res=context.getResources();
    }
    @Override                 //将CardView封装在RecyclerView中，作为ListView中的一个Item
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将布局转化为View并传递给RecyclerView封装好的ViewHolder
        View view= LayoutInflater.from(context).inflate(R.layout.item_card_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
           //封装ViewHolder
        private ImageView imageView;
        private TextView title;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.pic);
            title = (TextView) view.findViewById(R.id.title);
        }

    }


    @Override
    public void onBindViewHolder(final RecyclerAdapter.MyViewHolder holder, final int position) {
         //建立起ViewHolder中视图与数据的关联
        final ModelBean bean=list.get(position);
        holder.imageView.setImageResource(bean.getResId());
        holder.title.setText(bean.getTitle());
        Bitmap bitmap= BitmapFactory.decodeResource(res,bean.getResId());//获取Bitmap
        //异步获得Bitmap图片颜色值
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
//                Palette.Swatch color = palette.getLightVibrantSwatch();//有活力 亮色
                  Palette.Swatch color =palette.getVibrantSwatch();//有活力
                if(color!=null){
                    holder.title.setTextColor(color.getTitleTextColor());
                    holder.title.setBackgroundColor(color.getRgb());
                }
            }
        });

        /**
         * 调用接口回调
         */
        //RecyclerView中itemView的事件监听器
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener)
                    listener.onItemClick(position, bean);
            }
        });

    }

    /**
     * 内部接口回调方法
     */
    public interface OnItemClickListener {
        void onItemClick(int position, Object object);
    }

    /**
     * 设置监听方法
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
