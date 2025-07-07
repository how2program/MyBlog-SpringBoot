package ru.dima.myblog.model;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Post {

    private long id;
    private String heading;
    private String body;
    private Blob image;
    private long likes;
    private List<Tag> tags;
    private String tagsInString;

//    private ArrayList<Commentary> comments = new ArrayList<>();

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
//
//    public List<Commentary> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Commentary> comments) {
//        this.comments = comments;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public String getTagsInString() {
        return tagsInString;
    }

    public void setTagsInString(String tagsInString) {
        this.tagsInString = tagsInString;
    }
}