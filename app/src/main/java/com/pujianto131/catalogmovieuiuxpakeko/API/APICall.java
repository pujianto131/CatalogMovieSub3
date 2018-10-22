package com.pujianto131.catalogmovieuiuxpakeko.API;

import com.pujianto131.catalogmovieuiuxpakeko.model.DetailModel;
import com.pujianto131.catalogmovieuiuxpakeko.model.NowPlayingModel;
import com.pujianto131.catalogmovieuiuxpakeko.model.ResultItem;
import com.pujianto131.catalogmovieuiuxpakeko.model.SearchModel;
import com.pujianto131.catalogmovieuiuxpakeko.model.UpComingModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APICall {
    @GET("movie/now_playing")
    Call<NowPlayingModel> getNowPlayingMovie(@Query("language")String language);

    @GET("movie/{movie_id}")
    Call<DetailModel> getDetailMovie(@Path("movie_id") String movie_id, @Query("language") String language);

    @GET("movie/upcoming")
    Call<UpComingModel>getUpComingMovie(@Query("language") String language);

    @GET("search/movie")
    Call<SearchModel>getSearchMovie(@Query("query") String query, @Query("language") String Language);
}
