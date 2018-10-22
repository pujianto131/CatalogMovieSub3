package com.pujianto131.catalogmovieuiuxpakeko.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pujianto131.catalogmovieuiuxpakeko.database.DatabaseContract;
import com.pujianto131.catalogmovieuiuxpakeko.database.DatabaseHelper;
import com.pujianto131.catalogmovieuiuxpakeko.database.FavoritHelper;

import static com.pujianto131.catalogmovieuiuxpakeko.database.DatabaseContract.CONTENT_URI;

public class FavoritProvider extends ContentProvider {

    private static final int FAVORIT = 100;
    private static final int FAVORIT_ID = 101;

    private static final UriMatcher mUriMathcer = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMathcer.addURI(DatabaseContract.AUTHORITY, FavoritColums.TABLE_NAME, FAVORIT);
        mUriMathcer.addURI(DatabaseContract.AUTHORITY, FavoritColums.TABLE_NAME + "#", FAVORIT_ID);
    }



    private FavoritHelper favoritHelper;
    @Override
    public boolean onCreate() {
        favoritHelper = new FavoritHelper(getContext());
        favoritHelper.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch (mUriMathcer.match(uri)){
            case FAVORIT:
                cursor = favoritHelper.queryProvider();
                break;
            case FAVORIT_ID:
                cursor =favoritHelper.queryByIdProvider(uri.getLastPathSegment());
                break;

                default:
                    cursor = null;
                    break;
        }
        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {


        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long added;
        switch (mUriMathcer.match(uri)){
            case FAVORIT:
                added = favoritHelper.insertProvider(contentValues);
                break;

            default:
                added = 0;
                break;
        }
        if (added > 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int delete;
        switch (mUriMathcer.match(uri)){
            case FAVORIT_ID :
                delete = favoritHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                delete = 0;
                break;
        }
        if (delete > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return delete;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int update;
        switch (mUriMathcer.match(uri)){
            case FAVORIT_ID:
                update = favoritHelper.updateProvider(uri.getLastPathSegment(), contentValues);
                break;
            default:
                update = 0;
                break;
        }
        if (update > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return update;
    }
}
