package com.pujianto131.catalogmovieuiuxpakeko.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.pujianto131.catalogmovieuiuxpakeko.BuildConfig;
import com.pujianto131.catalogmovieuiuxpakeko.DetailActivity;
import com.pujianto131.catalogmovieuiuxpakeko.R;
import com.pujianto131.catalogmovieuiuxpakeko.model.ResultItem;
import com.pujianto131.catalogmovieuiuxpakeko.utils.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.img_poster)
    ImageView imgPoster;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_overView)
    TextView tvOverview;

    @BindView(R.id.tv_release_date)
    TextView tvRelease_date;

    @BindView(R.id.lv_detailItem)
    LinearLayout lvDetailItem;
    @BindView(R.id.btn_detail)
    Button btnDetail;



    public MovieHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final ResultItem item){
        tvTitle.setText(item.getTitle());
        tvOverview.setText(item.getOverview());
        tvRelease_date.setText(DateTime.getLongDate(item.getReleaseDate()));

        Glide.with(itemView.getContext())
                .load(BuildConfig.BASE_URL_IMG+"w154"+item.getPosterPath())
                .apply(new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                ).into(imgPoster);
        lvDetailItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.MOVIE_ITEM, new Gson().toJson(item));
                itemView.getContext().startActivity(intent);
            }
        });
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.MOVIE_ITEM, new Gson().toJson(item));
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
