package com.corydominguez.gator.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

/**
 * Created by coryd on 13/02/2014.
 */
public class Tweet extends Model implements Parcelable {
    @Column(name = "TweetId", index = true, unique = true)
    private Long tweetId;
    @Column(name = "Text")
    private String text;
    @Column(name = "TweetUserId")
    private String tweetUserId;
    @Column(name = "ProfileImageUrl")
    private String profileImageUrl;
    @Column(name = "ScreenName")
    private String screenName;
    @Column(name = "Link")
    private Link link;

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTweetUserId() {
        return tweetUserId;
    }

    public void setTweetUserId(String tweetUserId) {
        this.tweetUserId = tweetUserId;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", text='" + text + '\'' +
                ", tweetUserId='" + tweetUserId + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", screenName='" + screenName + '\'' +
                ", link=" + link +
                '}';
    }
}
