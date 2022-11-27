package com.org.skc.astronomypictureoftheday.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.org.skc.astronomypictureoftheday.model.FavItem;
import com.org.skc.astronomypictureoftheday.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashSet;
import static com.org.skc.astronomypictureoftheday.view.MainActivity.mFavoriteList;

public class FavItemsListActivity extends AppCompatActivity implements FavItemAdaptor.OnItemClickListener {
    private String TAG = "FavItemsListActivity";

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DATE = "date";

    private TextView mTextViewEmptyListUpdate;

    private RecyclerView mRecyclerView;
    private FavItemAdaptor mFavItemAdaptor;
    private ArrayList<FavItem> mFavItemList;
    private RequestQueue mRequestQueue;
    protected static String mUrlRequestForJsonRemoveFavorites = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_recycler_view);
        mTextViewEmptyListUpdate = findViewById(R.id.emptyListUpdate);


        mRecyclerView = findViewById(R.id.recyclerView);
        // Don't change width and height and load all images in fixed view
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFavItemList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        if (mFavItemList.size() == 0){
            mTextViewEmptyListUpdate.setText(getString(R.string.fav_def_text));
        }

        mFavoriteList = PrefConfig.readListFromPref(getApplicationContext());
        if (mFavoriteList == null){
            mFavoriteList = new HashSet<>();
        }

        parseJSON();
    }

    private void parseJSON() {
        for (String requestJSON : mFavoriteList) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requestJSON, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String date = response.getString("date");
                                String title = response.getString("title");
                                String imageUrl = response.getString("url");
                                String[] values = date.split("-");
                                int day = Integer.parseInt(values[0]);
                                int month = Integer.parseInt(values[1]);
                                int year = Integer.parseInt(values[2]);
                                mUrlRequestForJsonRemoveFavorites = requestJSON;
                                Log.d(TAG, "requestJSON: " + requestJSON);
                                mFavItemList.add(new FavItem(imageUrl, title, date));
                                // fill adaptor with the data
                                mFavItemAdaptor = new FavItemAdaptor(FavItemsListActivity.this, mFavItemList);
                                mRecyclerView.setAdapter(mFavItemAdaptor);
                                mFavItemAdaptor.setOnItemClickListener(FavItemsListActivity.this);
                                mTextViewEmptyListUpdate.setText("");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(FavItemsListActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    });
            mRequestQueue.add(request);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        FavItem clickedItem = mFavItemList.get(position);
        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getTitle());
        detailIntent.putExtra(EXTRA_DATE, clickedItem.getDate());
        startActivity(detailIntent);
    }

    @Override
    public void onDeleteItem(int position) {
        removeItem(position);
        mFavoriteList.remove(mUrlRequestForJsonRemoveFavorites);
        PrefConfig.updateData(getApplicationContext());
    }

    private void removeItem(int position) {
        mFavItemList.remove(position);
        mFavItemAdaptor.notifyItemRemoved(position);
        if (mFavItemList.size() == 0){
            mTextViewEmptyListUpdate.setText("Seems empty, add your favorites here!");
        }

    }
}