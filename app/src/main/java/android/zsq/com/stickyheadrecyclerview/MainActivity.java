package android.zsq.com.stickyheadrecyclerview;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvContent;
    private TextView normalHead;
    private int headHeight;
    private LinearLayoutManager linearLayoutManager;
    private int mCurrentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        normalHead = (TextView) findViewById(R.id.tv_head);
        RecyclerViewHolderData holder1 = new RecyclerViewHolderData();
        holder1.text = "head";
        holder1.uri = getResUri(R.mipmap.image1);
        RecyclerViewHolderData holder2 = new RecyclerViewHolderData();
        holder2.text = "head";
        holder2.uri = getResUri(R.mipmap.image1);
        RecyclerViewHolderData holder3 = new RecyclerViewHolderData();
        holder3.text = "head";
        holder3.uri = getResUri(R.mipmap.image1);
        RecyclerViewHolderData holder4 = new RecyclerViewHolderData();
        holder4.text = "head";
        holder4.uri = getResUri(R.mipmap.image1);
        final List<RecyclerViewHolderData> data = new ArrayList<>();
        data.add(holder1);
        data.add(holder2);
        data.add(holder3);
        data.add(holder4);
        RecyclerAdapter adapter = new RecyclerAdapter(data, this);
        rvContent.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(linearLayoutManager);
        rvContent.setHasFixedSize(true);
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 当recyclerView的滑动改变改变的时候 实时拿到它的高度
                headHeight = normalHead.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 根据当前的position的下一个拿到view
                View itemView = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (itemView != null) {
                    // 通过itemView 顶部y 坐标  判断是否小于或者等于head的底部y坐标,如果小于的话,说明itemViewHead 已经滑到了在recyclerView上的head了,
                    // 就开始对NormalHead 进行操作
                    Log.d("---",itemView.getTop()+"getTop");
                    Log.d("---",headHeight+"headHeight");
                    if (itemView.getTop() <= headHeight) {
                        // 设置normalHead不断的向-y的方向滑动(可以说是隐藏)
                        normalHead.setY(-(headHeight - itemView.getTop()));
                    } else {
                        // 如果当itemView 顶部y 坐标已经占据了normalHead的全部了,那么,就说明上一个的item的head已经在屏幕外了.,把当前的item的head 显示在normalHead之上,并且将Y的值设定成0
                        normalHead.setY(0);
                        Log.d("---","----");
                    }
                }
                //拿到当前显示的item的position
                int currentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (mCurrentPosition != currentPosition) {
                    mCurrentPosition = currentPosition;
                    normalHead.setY(0);
                    // 拿到当前item的head值,显示在normalHead上
                    normalHead.setText(data.get(mCurrentPosition % 4).text + (mCurrentPosition + 1));
                }
            }
        });
    }

    public Uri getResUri(int resId) {
        return Uri.parse("res://xxyy/" + resId);
    }
}
