package com.licrafter.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.licrafter.exp.ExpandableHeader;
import com.licrafter.exp.ExpandableLayout;
import com.licrafter.sample.view.CusViewPager;

public class MainActivity extends AppCompatActivity {

    private ExpandableHeader mExpHeader;
    private ExpandableLayout mExpLayout;
    private CusViewPager mHeaderPager;
    private RecyclerView mRecyclerview;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mExpHeader = (ExpandableHeader) findViewById(R.id.exp_header);
        mExpLayout = (ExpandableLayout) findViewById(R.id.exp_layout);
        mHeaderPager = (CusViewPager) findViewById(R.id.viewPager);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mHeaderPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        setSupportActionBar(mToolbar);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(new SampleAdapter());
        mExpLayout.setUpWithHeader(mExpHeader);
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                mExpLayout.setMinMargin(mToolbar.getHeight());
                mExpLayout.setThreshold(mToolbar.getHeight());
                mExpHeader.setThreshold(mToolbar.getHeight());
            }
        });
    }

    public class SampleAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final SampleHolder holder = new SampleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false));
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "点击 position " + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof SampleHolder) {
                ((SampleHolder) holder).title.setText("item-" + position);
            }
        }

        @Override
        public int getItemCount() {
            return 40;
        }
    }

    class SampleHolder extends RecyclerView.ViewHolder {

        TextView title;

        public SampleHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return PagerFragment.getInstance("http://pic24.nipic.com/20121008/3822951_094451200000_2.jpg");
            } else {
                return PagerFragment.getInstance("http://pic24.nipic.com/20121008/3822951_094451200000_2.jpg");
                // return PagerFragment.getInstance("http://imgsrc.baidu.com/baike/pic/item/7aec54e736d12f2ee289bffe4cc2d5628435689b.jpg");
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
