package com.pujianto131.catalogmovieuiuxpakeko;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.pujianto131.catalogmovieuiuxpakeko.API.APIClient;
import com.pujianto131.catalogmovieuiuxpakeko.model.DetailModel;
import com.pujianto131.catalogmovieuiuxpakeko.model.ResultItem;
import com.pujianto131.catalogmovieuiuxpakeko.utils.DateTime;


import com.pujianto131.catalogmovieuiuxpakeko.utils.Language;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    public static final String MOVIE_ITEM="movie_item";

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_overView)
    TextView tvOverView;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_voteAverage)
    TextView tvVoteAverage;

    @BindView(R.id.rt_ratingBar)
    RatingBar rtRatingBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.iv_backdropPath)
    ImageView ivBackdropPath;
    @BindView(R.id.iv_poster)
    ImageView ivPosterPath;


    private Call<DetailModel> apiCall;
    private APIClient apiClient = new APIClient();
    private Gson gson = new Gson();

    private ResultItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));




        String json = getIntent().getStringExtra(MOVIE_ITEM);
        item = gson.fromJson(json, ResultItem.class);


        getSupportActionBar().setTitle(item.getTitle());
        tvTitle.setText(item.getTitle());

        Glide.with(DetailActivity.this)
                .load(BuildConfig.BASE_URL_IMG+"w342"+item.getBackdropPath())
                .into(ivBackdropPath);
        Glide.with(DetailActivity.this)
                .load(BuildConfig.BASE_URL_IMG+"w154"+item.getPosterPath())
                .into(ivPosterPath);
        tvReleaseDate.setText(DateTime.getLongDate((item.getReleaseDate())));
        tvOverView.setText(item.getOverview());
        String vote = new String(String.valueOf(item.getVoteAverage()));
        tvVoteAverage.setText("Rating: "+vote);
        Float star =new Float(item.getVoteAverage());
        rtRatingBar.setRating(star/2);
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (apiCall != null)apiCall.cancel();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }
    private void loadFailed() {
        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
    }
}
