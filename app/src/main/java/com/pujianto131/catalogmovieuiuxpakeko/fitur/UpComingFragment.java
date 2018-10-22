package com.pujianto131.catalogmovieuiuxpakeko.fitur;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pujianto131.catalogmovieuiuxpakeko.API.APICall;
import com.pujianto131.catalogmovieuiuxpakeko.API.APIClient;
import com.pujianto131.catalogmovieuiuxpakeko.R;
import com.pujianto131.catalogmovieuiuxpakeko.adapter.MovieAdapter;
import com.pujianto131.catalogmovieuiuxpakeko.model.UpComingModel;
import com.pujianto131.catalogmovieuiuxpakeko.utils.Language;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {

    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.rv_upComing)
    RecyclerView rvUpComing;
    private MovieAdapter adapter;
    private Call<UpComingModel> apiCall;
    private APIClient apiClient = new APIClient();

    public UpComingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_up_coming, container, false);
        context = view.getContext();
        unbinder = ButterKnife.bind(this, view);
        setUpList();
        loadData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (apiCall != null)apiCall.cancel();
    }
    private void setUpList(){
        adapter = new MovieAdapter();
        rvUpComing.setLayoutManager(new LinearLayoutManager(context));
        rvUpComing.setAdapter(adapter);
    }

    private void loadData(){
        apiCall = apiClient.getService().getUpComingMovie(Language.getCountry());
        apiCall.enqueue(new Callback<UpComingModel>() {
            @Override
            public void onResponse(Call<UpComingModel> call, Response<UpComingModel> response) {
                if (response.isSuccessful()){
                    adapter.replaceAll(response.body().getResults());
                }else loadFailed();
            }

            @Override
            public void onFailure(Call<UpComingModel> call, Throwable t) {
                loadFailed();
            }
        });
    }

    private void loadFailed(){
        Toast.makeText(context, R.string.error_load_data, Toast.LENGTH_SHORT).show();
    }

}
