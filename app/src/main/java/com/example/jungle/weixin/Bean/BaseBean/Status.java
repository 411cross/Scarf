package com.example.jungle.weixin.Bean.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class Status implements Serializable {

    private long id;
    private long mid;
    private String idstr;
    private String created_at;
    private String text;
    private String source;
    private String thumbnail_pic;
    private String bmiddle_pic;
    private String original_pic;
    private Geo geo;
    private User user;
    private Status retweeted_status;
    private int reposts_count;
    private int comments_count;
    private int attitudes_count; // 赞数
    private List<PicURL> pic_urls = new ArrayList<>();
    private List<String> pic_ids = new ArrayList<>();
    private List<PicURL> pics = new ArrayList<>();
    private boolean isLongText;
    private boolean favorited;
    private boolean liked;

    public Status(long id, long mid, String idstr, String created_at, String text, String source, String thumbnail_pic, String bmiddle_pic, String original_pic, Geo geo, User user, Status retweeted_status, int reposts_count, int comments_count, int attitudes_count, List<PicURL> pic_urls, List<String> pic_ids, List<PicURL> pics, boolean isLongText, boolean favorited, boolean liked) {
        this.id = id;
        this.mid = mid;
        this.idstr = idstr;
        this.created_at = created_at;
        this.text = text;
        this.source = source;
        this.thumbnail_pic = thumbnail_pic;
        this.bmiddle_pic = bmiddle_pic;
        this.original_pic = original_pic;
        this.geo = geo;
        this.user = user;
        this.retweeted_status = retweeted_status;
        this.reposts_count = reposts_count;
        this.comments_count = comments_count;
        this.attitudes_count = attitudes_count;
        this.pic_urls = pic_urls;
        this.pic_ids = pic_ids;
        this.pics = pics;
        this.isLongText = isLongText;
        this.favorited = favorited;
        this.liked = liked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public void setThumbnail_pic(String thumbnail_pic) {
        this.thumbnail_pic = thumbnail_pic;
    }

    public String getBmiddle_pic() {
        return bmiddle_pic;
    }

    public void setBmiddle_pic(String bmiddle_pic) {
        this.bmiddle_pic = bmiddle_pic;
    }

    public String getOriginal_pic() {
        return original_pic;
    }

    public void setOriginal_pic(String original_pic) {
        this.original_pic = original_pic;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getRetweeted_status() {
        return retweeted_status;
    }

    public void setRetweeted_status(Status retweeted_status) {
        this.retweeted_status = retweeted_status;
    }

    public int getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(int reposts_count) {
        this.reposts_count = reposts_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getAttitudes_count() {
        return attitudes_count;
    }

    public void setAttitudes_count(int attitudes_count) {
        this.attitudes_count = attitudes_count;
    }

    public List<PicURL> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<PicURL> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public List<String> getPic_ids() {
        return pic_ids;
    }

    public void setPic_ids(List<String> pic_ids) {
        this.pic_ids = pic_ids;
    }

    public List<PicURL> getPics() {
        return pics;
    }

    public void setPics(List<PicURL> pics) {
        this.pics = pics;
    }

    public boolean isLongText() {
        return isLongText;
    }

    public void setLongText(boolean longText) {
        isLongText = longText;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
