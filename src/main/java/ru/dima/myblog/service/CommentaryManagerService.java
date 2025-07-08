package ru.dima.myblog.service;

import ru.dima.myblog.model.Commentary;
import java.util.Optional;

public interface CommentaryManagerService {

    void createCommentary(long postId, Commentary commentary);

    Optional<Commentary> findCommentaryByPostAndCommentaryId(long postId, long commentaryId);

    void updateCommentary(long postId, long commentaryId, Commentary updatedCommentary);
}
