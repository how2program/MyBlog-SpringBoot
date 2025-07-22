package ru.dima.myblog.boot.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Post {

    private long id;
    private String heading;
    private String body;
    private byte[] image;
    private long likes;
    private List<Tag> tags;
    private String tagsInString;
    private List<Commentary> comments = new ArrayList<>();
    private LocalDateTime localDateTime;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTagsInString() {
        return tagsInString;
    }

    public void setTagsInString(String tagsInString) {
        this.tagsInString = tagsInString;
    }

    public List<Commentary> getCommentaries() {
        return comments;
    }
    public void setCommentaries(List<Commentary> comments) {
        this.comments = comments;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && likes == post.likes && Objects.equals(heading, post.heading) && Objects.equals(body, post.body) && Objects.deepEquals(image, post.image) && Objects.equals(tags, post.tags) && Objects.equals(tagsInString, post.tagsInString) && Objects.equals(comments, post.comments) && Objects.equals(localDateTime, post.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heading, body, Arrays.hashCode(image), likes, tags, tagsInString, comments, localDateTime);
    }
}