package com.licrafter.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.licrafter.exp.ExpandableHeader;
import com.licrafter.exp.ExpandableLayout;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ExpandableHeader mExpHeader;
    private ExpandableLayout mExpLayout;
    private ImageView mHeaderImage;
    private RecyclerView mRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExpHeader = (ExpandableHeader) findViewById(R.id.exp_header);
        mExpLayout = (ExpandableLayout) findViewById(R.id.exp_layout);
        mHeaderImage = (ImageView) findViewById(R.id.headerImage);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(new SampleAdapter());
        mExpLayout.setUpWithHeader(mExpHeader);
        mExpLayout.setMinMargin(200);
        mExpLayout.setThreshold(200);
        Picasso.with(this).load("http://pic24.nipic.com/20121008/3822951_094451200000_2.jpg").into(mHeaderImage);
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
}
