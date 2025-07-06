package ru.dima.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private byte[] image;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", heading='" + heading + '\'' +
                ", body='" + body + '\'' +
                ", likes=" + likes +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && likes == post.likes && Objects.equals(heading, post.heading) && Objects.equals(body, post.body) && Objects.deepEquals(image, post.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heading, body, likes, Arrays.hashCode(image));
    }

}