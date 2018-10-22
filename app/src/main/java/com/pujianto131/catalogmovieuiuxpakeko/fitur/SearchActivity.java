package com.pujianto131.catalogmovieuiuxpakeko.fitur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.pujianto131.catalogmovieuiuxpakeko.API.APIClient;
import com.pujianto131.catalogmovieuiuxpakeko.R;
import com.pujianto131.catalogmovieuiuxpakeko.adapter.MovieAdapter;
import com.pujianto131.catalogmovieuiuxpakeko.model.SearchModel;
import com.pujianto131.catalogmovieuiuxpakeko.utils.Language;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    public static final String MOVIE_TITLE = "movie_title";

    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    private MovieAdapter adapter;

    private Call<SearchModel>apiCall;
    private APIClient apiClient = new APIClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        setUpList();

        String movieTitle = getIntent().getStringExtra(MOVIE_TITLE);
        loadData(movieTitle);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (apiCall != null)apiCall.cancel();
    }

    private void setUpList(){
        adapter= new MovieAdapter();
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        rvSearch.setAdapter(adapter);
    }

    private void loadData(String movieTitle){
        apiCall = apiClient.getService().getSearchMovie(movieTitle, Language.getCountry());
        apiCall.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()){
                    adapter.replaceAll(response.body().getResults());
                }else {
                    loadFailed();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                loadFailed();

            }
        });
    }

    private void loadFailed(){
        Toast.makeText(this, R.string.error_load_data, Toast.LENGTH_SHORT).show();
    }
}
