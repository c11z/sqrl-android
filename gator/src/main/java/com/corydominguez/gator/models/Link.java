package com.corydominguez.gator.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Table(name = "Link")
public class Link extends Model implements Parcelable {
    @Column(name = "LinkId", index = true, unique = true)
    private Long linkId;
    @Column(name = "Url", unique = true)
    private String url;
    @Column(name = "TwitterUrl")
    private String twitterUrl;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "Domain")
    private String domain;
    @Column(name = "HeroImageUrl")
    private String heroImageUrl;
    @Column(name = "Excerpt")
    private String excerpt;
    @Column(name = "CreatedAt")
    private Date createdAt;
    @Column(name = "UpdatedAt", index = true)
    private Date updatedAt;
    @Column(name = "IsBookmarked")
    private Boolean isBookmarked;

    DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    public Link() {
        super();
    }

    public List<Tweet> tweets() {
        return getMany(Tweet.class, "Link");
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getHeroImageUrl() {
        return heroImageUrl;
    }

    public void setHeroImageUrl(String heroImageUrl) {
        this.heroImageUrl = heroImageUrl;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getIsBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(Boolean isBookmarked) {
        this.isBookmarked = isBookmarked;
    }



    @JsonAnySetter
    public void anySetter(String key, Object value) {
        if (key.equals("createdAt")) {
            try {
                setCreatedAt(parseDate((String) value));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            setUpdatedAt(new Date());
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        return df.parse(dateString);
    }


    @Override
    public String toString() {
        return "Link{" +
                "linkId=" + linkId +
                ", url='" + url + '\'' +
                ", twitterUrl='" + twitterUrl + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", domain='" + domain + '\'' +
                ", heroImageUrl='" + heroImageUrl + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isBookmarked=" + isBookmarked +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.linkId);
        dest.writeString(this.url);
        dest.writeString(this.twitterUrl);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.domain);
        dest.writeString(this.heroImageUrl);
        dest.writeString(this.excerpt);
        dest.writeSerializable(this.createdAt);
        dest.writeSerializable(this.updatedAt);
        dest.writeValue(this.isBookmarked);
    }

    private Link(Parcel in) {
        this.linkId = (Long) in.readValue(Long.class.getClassLoader());
        this.url = in.readString();
        this.twitterUrl = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.domain = in.readString();
        this.heroImageUrl = in.readString();
        this.excerpt = in.readString();
        this.createdAt = (Date) in.readSerializable();
        this.updatedAt = (Date) in.readSerializable();
        this.isBookmarked = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static Creator<Link> CREATOR = new Creator<Link>() {
        public Link createFromParcel(Parcel source) {
            return new Link(source);
        }

        public Link[] newArray(int size) {
            return new Link[size];
        }
    };
}
