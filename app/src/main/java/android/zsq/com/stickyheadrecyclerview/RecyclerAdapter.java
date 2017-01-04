package android.zsq.com.stickyheadrecyclerview;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */
public   class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder>{
    private List< RecyclerViewHolderData> data  = new ArrayList<>();
    private  Activity activity ;
    public RecyclerAdapter( List< RecyclerViewHolderData> data ,Activity activity ) {
        this.data=data;
        this.activity=activity;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_sticky, parent, false);
        //View itemView =  View.inflate(activity,R.layout.item_sticky,null);
        RecyclerHolder holder = new  RecyclerHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
       int currentPosition= position % 4;
        getContentResId(currentPosition);

        holder.setData(data.get(currentPosition),position);
    }
    private void getContentResId(int position) {
        switch (position) {
            case 0:
                data.get(0).uri=getResUri(R.mipmap.image1);
            case 1:
                data.get(1).uri=getResUri(R.mipmap.image2);
            case 2:
                data.get(2).uri=getResUri(R.mipmap.image3);
            case 3:
                data.get(3).uri=getResUri(R.mipmap.image4);
        }
    }
    @Override
    public int getItemCount() {
        return 1000;
    }
    public Uri getResUri(int resId){
        return Uri.parse("res://xxyy/" + resId);
    }
}