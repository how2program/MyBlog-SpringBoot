package ru.dima.myblog.dao;

import ru.dima.myblog.model.Commentary;
import java.util.List;
import java.util.Optional;

public interface CommentaryManagerDao {

    void createCommentary(long postId, Commentary commentary);

    List<Commentary> findAllCommentaries(long postId);

    Optional<Commentary> findCommentaryByPostAndCommentaryId(long postId, long commentaryId);

    void updateCommentary(long postId, long commentaryId, Commentary updatedCommentary);

    void deleteCommentary(long postId, long commentaryId);
}
