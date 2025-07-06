package ru.dima.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commentary {

    private long id;
    private long postId;
    private String text;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Commentary that = (Commentary) o;
        return id == that.id && postId == that.postId && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, text);
    }
}