package com.pujianto131.catalogmovieuiuxpakeko.fitur;


import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pujianto131.catalogmovieuiuxpakeko.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.pujianto131.catalogmovieuiuxpakeko.database.DatabaseContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritFragment extends Fragment {

    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.rv_favorit)
    RecyclerView rvFavorit;

    private Cursor list;
    private FavoritAdapter adapter;


    public FavoritFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorit, container, false);
        context = view.getContext();

        unbinder = ButterKnife.bind(this, view);
        setUpList();
        new loadDataAsync().execute();
        return  view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        new loadDataAsync().execute();
    }


    private void  setUpList(){
        adapter = new FavoritAdapter(list);
        rvFavorit.setLayoutManager(new LinearLayoutManager(context));
        rvFavorit.setAdapter(adapter);

    }
    private class loadDataAsync extends AsyncTask<Void, Void, Cursor>{
        @Override
        protected Cursor doInBackground(Void... voids) {
            return context.getContentResolver().query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
        }
        @Override
        protected void onPostExecute(Cursor cursor){
            super.onPostExecute(cursor);
            list = cursor;
            adapter.replaceAll(list);
        }
    }

}
