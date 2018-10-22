package com.pujianto131.catalogmovieuiuxpakeko.fitur;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import retrofit2.http.PUT;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.ViewHolder> {
    private Cursor list;

    public FavoritAdapter(Cursor item){
        replaceAll(item);
    }

    public void replaceAll(Cursor item) {
        list=item;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.activity_detail_item, parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    @Override
    public int getItemCount() {
        if (list == null)return 0;
        return list.getCount();
    }
    private ResultItem getItem(int position){
        if (!list.moveToPosition(position)){
            throw new IllegalStateException("Position Invalid!");
        }

        return new ResultItem(list);

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_poster)
        ImageView imgPoster;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_overView)
        TextView tvOverview;

        @BindView(R.id.tv_release_date)
        TextView tvReleaseDate;

        @BindView(R.id.btn_detail)
        Button btnDetail;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void bind(final ResultItem item){
            tvTitle.setText(item.getTitle());
            tvOverview.setText(item.getOverview());
            tvReleaseDate.setText(DateTime.getLongDate(item.getReleaseDate()));
            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_URL_IMG+"w154"+item.getPosterPath())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.placeholder)
                            .centerCrop()
                    )
                    .into(imgPoster);
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
}
