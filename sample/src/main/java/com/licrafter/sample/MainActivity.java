package com.licrafter.sample;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.licrafter.exp.ExpandableHeader;
import com.licrafter.exp.ExpandableLayout;
import com.licrafter.sample.repo.ImageModel;
import com.licrafter.sample.repo.Repo;
import com.licrafter.sample.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableHeader mExpHeader;
    private ExpandableLayout mExpLayout;
    private ViewPager mHeaderPager;
    private RecyclerView mRecyclerview;
    private Toolbar mToolbar;

    private List<ImageModel> banners = new ArrayList<>();
    private List<Integer> fullHeights = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repo.initRepo();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mExpHeader = (ExpandableHeader) findViewById(R.id.exp_header);
        mExpLayout = (ExpandableLayout) findViewById(R.id.exp_layout);
        mHeaderPager = (ViewPager) findViewById(R.id.viewPager);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        setSupportActionBar(mToolbar);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(new SampleAdapter());
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                mExpLayout.setMinMargin(mToolbar.getHeight());
                mExpLayout.setThreshold(mToolbar.getHeight());
                mExpHeader.setThreshold(mToolbar.getHeight());
            }
        });

        banners.addAll(Repo.data);
        for (ImageModel model : banners) {
            fullHeights.add(Utils.getDisplayWidth(MainActivity.this) * model.getHeight() / model.getWidth());
        }
        mHeaderPager.setAdapter(new BannerPagerAdapter(getApplicationContext(), banners));
        mExpHeader.setHeight(fullHeights.get(0));
        mExpLayout.setUpWithHeader(mExpHeader);
        Utils.hideSystemUI(this);
        mHeaderPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == fullHeights.size() - 1) {
                    return;
                }

                //计算ViewPager现在应该的高度,heights[]表示页面高度的数组。
                int height = (int) ((fullHeights.get(position) == 0 ? fullHeights.get(0) : fullHeights.get(position))
                        * (1 - positionOffset) +
                        (fullHeights.get(position + 1) == 0 ? fullHeights.get(0) : fullHeights.get(position + 1))
                                * positionOffset);

                mExpHeader.setHeight(height);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Utils.hideSystemUI(this);
        }
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

    class BannerPagerAdapter extends PagerAdapter {

        private List<ImageModel> imageModels;
        private Context context;
        private SparseArray<ImageView> mImageArray;
        private LayoutInflater inflater;

        public BannerPagerAdapter(Context context, List<ImageModel> imageModels) {
            this.imageModels = imageModels;
            this.context = context;
            this.inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = (ImageView) inflater.inflate(R.layout.layout_banner_image, container, false);
            Picasso.with(container.getContext()).load(banners.get(position).getUrl()).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
