package com.org.skc.astronomypictureoftheday.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.skc.astronomypictureoftheday.R;
import com.org.skc.astronomypictureoftheday.model.FavItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavItemAdaptor extends RecyclerView.Adapter<FavItemAdaptor.FavItemViewHolder> {
    private Context mContext;
    private ArrayList<FavItem> mFavItemsList;
    private OnItemClickListener mListener;

    // Create an interface to open an image to Detail Activity
    public interface OnItemClickListener {
        void onItemClick(int position); // Call this after adapter has been created, setAdapter()
        void onDeleteItem(int position); // Call this after adapter has been created, setAdapter()
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public FavItemAdaptor(Context context, ArrayList<FavItem> exampleList) {
        mContext = context;
        mFavItemsList = exampleList;
    }

    @NonNull
    @Override
    public FavItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new FavItemViewHolder(v);
    }

    // Get list of current items here
    @Override
    public void onBindViewHolder(@NonNull FavItemViewHolder holder, int position) {
        FavItem currentItem = mFavItemsList.get(position);
        String imageUrl = currentItem.getImageUrl();
        String title = currentItem.getTitle();
        String dateTaken = currentItem.getDate();

        // Set each view here
        holder.mTextViewTitle.setText(title);
        holder.mTextViewDate.setText("Taken on: " + dateTaken);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mFavItemsList.size();
    }

    public class FavItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewTitle;
        public TextView mTextViewDate;
        public ImageView mDeleteItem;

        public FavItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageRecyclerView);
            mTextViewTitle = itemView.findViewById(R.id.textRecyclerViewTitle);
            mTextViewDate = itemView.findViewById(R.id.textRecyclerViewDate);
            mDeleteItem = itemView.findViewById(R.id.deleteItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        // Get the position in Recycler View List
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

            mDeleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        // Get the position in Recycler View List
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onDeleteItem(position);
                        }
                    }
                }
            });
        }
    }
}
