package ru.dima.myblog.boot.service;

import ru.dima.myblog.boot.model.Commentary;
import java.util.Optional;

public interface CommentaryManagerService {

    void createCommentary(long postId, Commentary commentary);

    Optional<Commentary> findCommentaryByPostAndCommentaryId(long postId, long commentaryId);

    void updateCommentary(long postId, long commentaryId, Commentary updatedCommentary);

    void deleteCommentary(long postId, long commentaryId);
}
