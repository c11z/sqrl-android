package com.corydominguez.gator.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by coryd on 13/02/2014.
 */

//@Table(name = "Tweet")
public class Tweet implements Parcelable {
//    @Column(name = "TweetId", index = true, unique = true)
    private Long tweetId;
//    @Column(name = "Text")
    private String text;
//    @Column(name = "TweetUserId")
    private Long tweetUserId;
//    @Column(name = "ProfileImageUrl")
    private String profileImageUrl;
//    @Column(name = "ScreenName")
    private String screenName;
//    @Column(name = "Name")
    private String name;
//    @Column(name = "Link")
//    private Link link;

    public Tweet() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Long getTweetUserId() {
        return tweetUserId;
    }

    public void setTweetUserId(Long tweetUserId) {
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

//    public Link getLink() {
//        return link;
//    }
//
//    public void setLink(Link link) {
//        this.link = link;
//    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", text='" + text + '\'' +
                ", tweetUserId='" + tweetUserId + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", screenName='" + screenName + '\'' +
//                ", link=" + link +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.tweetId);
        dest.writeString(this.text);
        dest.writeValue(this.tweetUserId);
        dest.writeString(this.profileImageUrl);
        dest.writeString(this.screenName);
//        dest.writeParcelable(this.link, flags);
    }

    private Tweet(Parcel in) {
        this.tweetId = (Long) in.readValue(Long.class.getClassLoader());
        this.text = in.readString();
        this.tweetUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.profileImageUrl = in.readString();
        this.screenName = in.readString();
//        this.link = in.readParcelable(Link.class.getClassLoader());
    }

    public static Creator<Tweet> CREATOR = new Creator<Tweet>() {
        public Tweet createFromParcel(Parcel source) {
            return new Tweet(source);
        }

        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };
}
