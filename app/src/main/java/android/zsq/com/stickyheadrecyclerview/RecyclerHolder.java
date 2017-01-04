package android.zsq.com.stickyheadrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public class RecyclerHolder extends RecyclerView.ViewHolder {

    private final SimpleDraweeView ivImage;
    private final TextView tvHead;

    public RecyclerHolder(View rootView) {
        super(rootView);
        tvHead = (TextView) rootView.findViewById(R.id.tv_head);
        ivImage = (SimpleDraweeView) rootView.findViewById(R.id.iv_image);

    }
    public void setData(RecyclerViewHolderData data, int position){
        tvHead.setText(data.text +(position+1));
        ivImage.setImageURI(data.uri);
    }
}
