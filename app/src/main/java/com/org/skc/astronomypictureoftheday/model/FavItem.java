package com.org.skc.astronomypictureoftheday.model;

public class FavItem {
    private String mImageUrl;
    private String mTitle;
    private String mDate;

    public FavItem(String mImageUrl, String mTitle, String mDate) {
        this.mImageUrl = mImageUrl;
        this.mTitle = mTitle;
        this.mDate = mDate;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }
}
