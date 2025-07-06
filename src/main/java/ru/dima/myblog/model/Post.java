package ru.dima.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private long id;
    private String heading;
    private String body;
    private long likes;
    private Blob image;
    //Буду взаимодействовать с ними через геттеры и сеттеры, без отдельных методов.
    private ArrayList<String> comments;
    private ArrayList<String> tags;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && likes == post.likes && Objects.equals(heading, post.heading) && Objects.equals(body, post.body) && Objects.equals(image, post.image) && Objects.equals(comments, post.comments) && Objects.equals(tags, post.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heading, body, likes, image, comments, tags);
    }
}